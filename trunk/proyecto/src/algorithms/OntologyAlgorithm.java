package algorithms;

import entities.QualityAttributeInterface;
import entities.RichedWord;

import ontology.OntologyManager;
import ontology.QualityAttributeBelongable;

import org.apache.log4j.Logger;

import utils.MapUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * Implementacion de un algoritmo que utiliza una ontologia.
 */
public class OntologyAlgorithm implements Algorithm {
    /** Logger */
    static final Logger logger = Logger.getLogger(OntologyAlgorithm.class);

    /**
     * Se instancia a un OntologyManager. Se utiliza para cargar los
     * atributos de calidad y el grado de pertenencia que tenga una palabra
     * con respecto a los atributos de calidad
     */
    private QualityAttributeBelongable qabelongable;
    
    private Double useCaseFactor;

/**
     * Creates a new OntologyAlgorithm object.
     *
     * @param owlFilePath DOCUMENT ME!
     * @param repositoryFilePath DOCUMENT ME!
     */
    public OntologyAlgorithm(String owlFilePath, String repositoryFilePath) {
        this.qabelongable = new OntologyManager(owlFilePath, repositoryFilePath);
        this.useCaseFactor = Double.valueOf(1.0);
    }

    /**
     * Devuelve un QualityAttributeTheme a partir de una lista de
     * palabras y de un EarlyAspect
     *
     * @param useCaseWordsList Listado de palabras
     * @param earlyAspectWordsList Aspecto temprano
     *
     * @return Quality Attribute Theme
     */
    @Override
    public Map<QualityAttributeInterface, Double> getQualityAttributePertenence(
        List<RichedWord> useCaseWordsList, List<RichedWord> earlyAspectWordsList) {
        
    	//Map de los casos de uso
    	Map<QualityAttributeInterface, Double> useCaseMap = this.getAttributesMap(useCaseWordsList);
    	
    	//Si useCaseFactor=1, no hace falta el analisis de las palabras del EarlyAspect
    	if (!this.useCaseFactor.equals(Double.valueOf(1.0))){
    		//Map del earlyAspect
    		Map<QualityAttributeInterface, Double> earlyApectMap = this.getAttributesMap(earlyAspectWordsList);
    		

    		if (MapUtils.areAllValuesZero(useCaseMap)) 
    			return earlyApectMap;
    		
    		if (MapUtils.areAllValuesZero(earlyApectMap)) 
    			return useCaseMap;
    		
    		useCaseMap = MapUtils.multiplyMapByFactor(useCaseMap, useCaseFactor);
    		earlyApectMap = MapUtils.multiplyMapByFactor(earlyApectMap, 1.0 - useCaseFactor);
    		
    		Map<QualityAttributeInterface, Double> totalMap = MapUtils.addMaps(useCaseMap, earlyApectMap);
    		return totalMap;
        }
    	return useCaseMap;
    }

    /**
     * Metodo que devuelve el grado de pertenencia de una lista de
     * palabras, con los distintos atributos de calidad. En el map que
     * devuelve, cada clave es un atributo de calidad y el valor de la clave
     * un numero entre 0 y 1 indicando el grado de pertenecia de la lista con
     * ese atributo.
     *
     * @param words listado de palabras
     *
     * @return map en donde cada clave es un atributo de calidad, y el valor de
     *         la clava un numero entre 0 y 1 indicando el grado de pertenecia
     *         de la lista con ese atributo.
     */

    private Map<QualityAttributeInterface, Double> getAttributesMap(
        List<RichedWord> words) {
        Map<QualityAttributeInterface, Double> wordMap = null;

        //Recupera los atributos de calidad declarados en la ontologia
        Map<QualityAttributeInterface, Double> totalMap = MapUtils.convertAttributesLisToMap(qabelongable.loadQualityAttributes());

        Iterator<RichedWord> richedWordIterator = words.iterator();
        RichedWord richedWord = null;
        Integer totalWords = 0;

        while (richedWordIterator.hasNext()) {
            richedWord = richedWordIterator.next();
            wordMap = qabelongable.getWordPertenence(richedWord.getWord());

            //Puede ser que la palabra no pertenezca a la ontologia, por lo que no 
            //se tiene en cuenta.
            if (wordMap != null) {
                wordMap = MapUtils.convertMapToPromedy(wordMap);

                //Ahora hay que tener en cuenta el peso y las ocurrencias de la palabra
                Integer weight = this.getWordWeight(richedWord);

                //Cada valor del map se multiplica por weight
                wordMap = MapUtils.multiplyMapByFactor(wordMap, Double.valueOf(weight));

                totalMap = MapUtils.addMaps(totalMap, wordMap);
                totalWords = totalWords + weight;
            }
        }
        
        //Si totalWords es igual a cero, significa que ninguna de las palabras se encuentra en la ontologia
        if (totalWords!=0)
        	totalMap = MapUtils.divideTotal(totalMap, totalWords);

        return totalMap;
    }

    /**
     * Calcula el "peso total" de una palabra. Para eso multiplica el peso de la pabra por la cantidad
     * de ocurrencias. Si el peso es igual a cero, devuelve el numero de ocurrencias.
     * Se supone que el numero de ocurrencias es mayor que cero.
     *
     * @param richedWord palabra enriqeucida
     *
     * @return multiplicaciond el peso por las ocurrencias
     */
    Integer getWordWeight(RichedWord richedWord) {
        Integer weight = (Integer) richedWord.getAttribute(RichedWord.WEIGHT);
        Integer ocurrencies = (Integer) richedWord.getAttribute(RichedWord.OCURRENCES);

        //Si se tiene en cuenta el peso de la palabra y el nunmero de ocurrencias, ninguna de estos valores
        //deberia ser cero. El numero de ocurrencias, al menos, siempre es mayor o igual a cero.

        //Si el peso es cero, que se devuelva el numero de ocurrencias
        if ((weight == null) || (weight == 0)) {
            return ocurrencies;
        }
        
        return weight * ocurrencies;
    }

    /**
     * Devuelve el QualityAttributeBelongable
     *
     * @return QualityAttributeBelongable
     */
    public QualityAttributeBelongable getQabelongable() {
        return qabelongable;
    }

    /**
     * Setea el QualityAttributeBelongable
     *
     * @param qabelongable parametro a setear
     */
    public void setQabelongable(QualityAttributeBelongable qabelongable) {
        this.qabelongable = qabelongable;
    }

    /**
     * Metodo main
     *
     * @param args arreglo de parametros
     */
    public static void main(String[] args) {
        Algorithm algorithm = new OntologyAlgorithm("file:resources/ontology.repository",
                "file:resources/ontology.owl");

        List<RichedWord> list = new ArrayList<RichedWord>();
        list.add(new RichedWord("User2"));
        list.add(new RichedWord("Stimulus1"));

        Map<QualityAttributeInterface, Double> qt = algorithm.getQualityAttributePertenence(null,
                null);

        //System.out.println(qt.toString());
    }
    
    
    
    public Double getUseCaseFactor() {
		return useCaseFactor;
	}

	public void setUseCaseFactor(Double useCaseFactor) {
		if(useCaseFactor>Double.valueOf(0.0) && useCaseFactor<=Double.valueOf(1.0)){
			this.useCaseFactor = useCaseFactor;
		}
	}

	public Map<QualityAttributeInterface, Double> combineMaps(Map<QualityAttributeInterface, Double> earlyAspectMap, Map<QualityAttributeInterface, Double> useCasesMap){
    	
    	return null;
    }
}
