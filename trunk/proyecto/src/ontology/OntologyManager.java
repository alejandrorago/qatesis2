/**
 * 
 */
package ontology;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

import entities.AbstractEntityFactory;
import entities.EntityFactory;
import entities.QualityAttribute;
import entities.QualityAttributeInterface;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;


/**
 * Clase Ontology Manager
 *
 * @author fran
 */
public class OntologyManager implements QualityAttributeBelongable {
    
	
	static final Logger logger = Logger.getLogger(OntologyManager.class);
	
	
	/** DOCUMENT ME! */
    private OntModel ontModel;

    /**
     * Construcora de la clase
     */
    public OntologyManager() {
        this.loadOntology();
    }

    /**
     * Carga la ontologia en memoria, desde archivo
     */
    public void loadOntology() {
    	this.ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM,
                null);
        this.ontModel.getDocumentManager()
                     .addAltEntry("file:resources/ontology.repository",
            "file:resources/ontology.owl");
        this.ontModel.read("file:resources/ontology.repository");
    }

 
    /**
     * Devuelve un recurso que se relacione con el valor literal pasado
     * como parametro, mediante la propiedad de uri pasada como parametro. En
     * caso de haber mas de una instancia, devuelve la primera que encuentra.
     * En caso de que no haya ninguna instancia, devuelve null
     *
     * @param propertyUri DOCUMENT ME!
     * @param literal DOCUMENT ME!
     *
     * @return recurso que se relaciona con el string literal mediante la
     *         Property con uri igual a propertyUri, null en caso contrario
     */
    public Resource getInstanceFromResource(String propertyUri, String literal) {
        Property property = this.ontModel.getProperty(propertyUri);
        ResIterator instancias = this.ontModel.listSubjectsWithProperty(property,
                literal);
        Resource instancia = null;

        //Si la instancia existe, tiene que ser unica
        if (instancias.hasNext()) {
            instancia = instancias.nextResource();
        }

        return instancia;
    }

    /**
     * Dado un valor literal, este metodo devuelve el recurso que se
     * relaciona con ese valor. La idea es que  dada una palabra, se infiera a
     * que parte de un escenario concreto se refiere. Por la tanto, el recurso
     * que se devuelve es una instancia de concreteSource, concreteStimulus,
     * concreteEnviroment, etc. Puede pasar que haya dos instancias que se
     * relacionen con esa palabra. Por ejemplo, hay un Stimulus que tiene
     * nombre "User" y hay un Source que tiene el nombre "User". En esta caso,
     * se toma la instancia con la que se relacionen mas escenarios concretos.
     * Es decir, si 10 escenarios tienen como fuente a "User" y solo 2 como
     * estimulo a "user", se devuelve la instancia q es de tipo Source.
     *
     * @param word palabra
     *
     * @return instancias que se relaciona con el parametro, null si no hay
     *         ninguna
     */
    public Resource getWordMeaning(String word) {
        Resource actualResource = null;
        int actualCount = -1;

        String auxUri;
        Property auxProperty = null;
        Resource auxResource = null;
        int auxCount;

        /*
         * El metodo concreteXNameconcreteScenarioHasXUri nos devuelve los uris de dos propiedades:
         * la que relaciona una parte del escenario con su valor literal(ej: sourceHasName) y la que relaciona
         * esa instancia con un la del escenario (ej: concreteScenarioHasSource).
         * La ides es utilizar la primer propiedad para verificar si existe alguna instancia de alguna parte del escenario
         * que se relacione con la palabra en cuestion. Si existe alguna, chequeamos cuantos escenarios se relacionan con
         * esa instancia mediante la segunda propiedad.
         * De esta manera, devolvemos el recurso que se relaciona con el mayor nuemero de escenarios.
         */
        Map<String, String> map = Uris.concreteXNameANDconcreteScenarioHasXUri();
        Iterator<String> iterator = map.keySet().iterator();

        while (iterator.hasNext()) {
            auxUri = iterator.next();
            auxResource = this.getInstanceFromResource(auxUri, word);

            //se chequea que exista alguna instancia con esa palabra
            if (auxResource != null) {
                /*
                 * La instancia existe, es decir, hay alguna instancia de algua parte de un escenario (Source, Stimules,
                 * Enviroment, etc) que se relaciona con la palabra
                 */
                auxProperty = this.ontModel.getProperty(map.get(auxUri));
                /*
                * auxCount representa la cantidad de escenarios que se relacionan con la instancia que se relaciona
                * con word
                */
                auxCount = this.ontModel.listSubjectsWithProperty(auxProperty,
                        auxResource).toList().size();

                if (auxCount > actualCount) {
                    actualCount = auxCount;
                    actualResource = auxResource;
                }
            }
        }

        return actualResource;
    }

    /**
     * Todas las instancias de la ontologia tienen la propiedad http://www.w3.org/1999/02/22-rdf-syntax-ns#type 
     * (en adelante llamada simplemente "type"). Esta las relaciona con la clase de su tipo. Ejmplo:
     * instancia1 se relaciona con ConcreteScenarioSource mediante la propiedad type.Esto indica que instancia1 es 
     * de tipo ConcreteScenerioSource.
     *
     * @param resource algun recurso de una instancia dentro de la ontologia 
     *
     * @return recurso que relaciona con el parametro medianta la propiedad type (http://www.w3.org/1999/02/22-rdf-syntax-ns#type)
     */
    public Resource getType(Resource resource) {
        Resource typeResource = null;
        Statement statement = resource.getProperty(this.ontModel.getProperty(
                    Uris.type));

        if (statement != null) {
            typeResource = this.ontModel.getResource(statement.getObject()
                                                              .asNode().getURI());
        }

        return typeResource;
    }

    /**
     * Esta funcion primera consulta todas las instancias que existan en la ontologia que sean de tipo qualityAttribute
     * (http://www.owl-ontologies.com/unnamed.owl#QualityAttribute). Luego recupera el nombre de cada una de ellas, q es el valor
     * literal relacionado mediante la propiedad qualityAttributeName (http://www.owl-ontologies.com/unnamed.owl#qualityAttributeName).
     * Con cada nombre de los atributos de calidad, se crea una instancia y se alamanca en el map como clave, poniendo asociando cada clave con el 
     * valor cero
     *
     * @return DOCUMENT ME!
     */
    public Map<QualityAttributeInterface, Double> loadQualityAttributes() {
        AbstractEntityFactory factory = new EntityFactory();
        
    	Map<QualityAttributeInterface, Double> map = new HashMap<QualityAttributeInterface, Double>();
        Property qualityAttributeNameProperty = this.ontModel.getProperty(Uris.qualityAttributeName);
        ResIterator qualityAttributeIterator = this.ontModel.listResourcesWithProperty(this.ontModel.getProperty(
                    Uris.type),
                    this.ontModel.getResource(Uris.qualityAttributeUri));

        Resource qualityAttributeResource = null;
        String qualityAttributeName = null;

        while (qualityAttributeIterator.hasNext()) {
            qualityAttributeResource = qualityAttributeIterator.next();
            qualityAttributeName = qualityAttributeResource.listProperties(qualityAttributeNameProperty)
                                                           .next().getString();
            map.put(factory.creatQualityAttribute(qualityAttributeName), Double.valueOf(0.0));
        }

        return map;
    }

 

    /**
     * DOCUMENT ME!
     *
     * @param scenario DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getAttributeNameFromConcreteScenario(Resource scenario) {
        Property iSpecificOfProperty = this.ontModel.getProperty(Uris.isSpecificof);
        Property qualityAttributeNameProperty = this.ontModel.getProperty(Uris.qualityAttributeName);

        String qualityAttributeInscanceUri = scenario.listProperties(iSpecificOfProperty)
                                                     .nextStatement().getObject()
                                                     .asNode().getURI();
        Resource qualityAttributeInstanceResource = this.ontModel.getResource(qualityAttributeInscanceUri);

        String qualityAttributeName = qualityAttributeInstanceResource.listProperties(qualityAttributeNameProperty)
                                                                      .next()
                                                                      .getString();

        return qualityAttributeName;
    }

    /**
     * Funcion principal de la clase.
     * Dada una palabra, realiza los siguientes pasos
     * 1) Dada la plabra, cheuqea en la ontologia con que parte de un escenario concreto se corresponde (Fuente de estimulo,Estimulo, Ambiente,
     * 	  Artefacto, Respuesta o tiempo de respuesta). 
     * 	  En caso de que se corresponda con mas de uno, toma en cuenta la que se relacione con la mayor cantidad de instancias de escenaios
     * 
     * 2) Recupera los atributos de calidad que se relacionan con todas las instancias de esa parte del escenario.
     * 
     * 3) Devuelve un map que relaciona todos los atributos de calidad presentes en la ontologia con la suma escenarios recuperados en 2) que se 
     * 	  relacionan con cada atributo de calidad.
     *
     * @param word palabra a analizar
     *
     * @return map que relaciona los atributos de calidad con la cantidad de escenarios que se relacionan con esa palabra.
     */
    @Override
    public Map<QualityAttributeInterface, Double> getWordPertenence(String word) {

    	logger.info("Palabra entrante: " + word);
    	
    	AbstractEntityFactory entityFactory = new EntityFactory(); 
    	Map<QualityAttributeInterface, Double> map = this.loadQualityAttributes();

        Resource scenarioPart = this.getWordMeaning(word); //Devuelve un numero de instancia, ejemplo intance_0
        
        //Si scenarioPart es igual a null, significa q esa palabra no se encuentra presente en la ontologia
        if (scenarioPart!=null){
        	
        	Resource scenarioPartType = this.getType(scenarioPart); //Devueve el tipo de esa instancia, ejemplo, http://www.owl-ontologies.com/unnamed.owl#ConcreteSource
        	logger.info("La palabra " + word + " se reconoce como perteneciente a una una instancia de " + scenarioPartType.getURI());

	        //A partir de ese concreteSource, se averigua el Uri de la propiedad con la que se relaciona con las instancias
	        //de escenarios. Siguiendo el ejemplo, el metodo nos devolveria el uri http://www.owl-ontologies.com/unnamed.owl#concreteScenarioHasSource
	        String concreteScenarioHasXUri = Uris.getScenarioHasUriFromConcretePartUri(scenarioPartType.getURI());
	        Property concreteScenarioHasXProperty = this.ontModel.getProperty(concreteScenarioHasXUri);
	
	        //Con los datos que tenemos averiguamos todos los escenarios que se relacionan con scenario part, mediante 
	        //la propiedad concreteScenarioHasXProperty
	        ResIterator scenariosIterator = this.ontModel.listSubjectsWithProperty(concreteScenarioHasXProperty,
	                scenarioPart);
	        Resource scenario = null;
	        QualityAttributeInterface qualityAttribute = null;
	        //TODO si scenarioIterator esta vacio, quiere decir que hay una instancia de algun scenario que se relaciona
	        //con la palabra, pero esa parte no se relaciona con ningun escenario. Es decir, existe una instancia de 
	        //concreteSource, pero esta no esta relacionada con ninguna instancia de escanario.
	        while (scenariosIterator.hasNext()) {
	            scenario = scenariosIterator.next();
	            qualityAttribute = entityFactory.creatQualityAttribute(this.getAttributeNameFromConcreteScenario(
	                        scenario));
	            map.put(qualityAttribute, map.get(qualityAttribute) + Double.valueOf(1.0));
	        }
        }else
        {
        	logger.info("La palabra " + word + " no se reconoce como perteneciente a ninguna parte de un escenario concreto"  );
        }
        return convertMapToPromedy(map);
    }
    /**
     * Lista por consola todas las sentencia de la ontologia.
     */
    public void listarOntologia() {
        Statement b = null;
        RDFNode rdfNode;
        StmtIterator stmIterator = this.ontModel.listStatements();

        while (stmIterator.hasNext()) {
            Statement a = stmIterator.nextStatement();
            System.out.println(a.getSubject().getURI());
            System.out.println(a.getPredicate().getURI());

            Literal auxLiteral;
            rdfNode = a.getObject();

            if (rdfNode.isLiteral()) {
                auxLiteral = a.getLiteral();
                System.out.println("Un literal:" + auxLiteral.getString());
            } else {
                System.out.println(a.getResource().getURI());
            }

            System.out.println("---------------------------------------");
        }
    }
    
    public Map<QualityAttributeInterface, Double> convertMapToPromedy(Map<QualityAttributeInterface, Double> map){
    	
    	Iterator<Double> values = map.values().iterator();
    	Double total = Double.valueOf(0.0);
    	while (values.hasNext()){total = total + values.next();}
    	
    	Map<QualityAttributeInterface, Double> mapConverted = new HashMap<QualityAttributeInterface, Double>();
    	Iterator<QualityAttributeInterface> iterator = map.keySet().iterator();
    	QualityAttributeInterface qa = null;
    	Double valor = null;
    	while(iterator.hasNext()){
    		qa = iterator.next();
    		valor = map.get(qa)/total;
    		mapConverted.put(qa, valor);
    	}
    	return mapConverted;
    }
    
    /**
     * Imprime un map por pantalla
     * @param map
     */
    public void imprimirMap(Map<QualityAttributeInterface, Double> map){
    	Iterator<QualityAttributeInterface> iterator = map.keySet().iterator();
    	QualityAttributeInterface qa = null;
    	while(iterator.hasNext()){
    		qa = iterator.next();
    		logger.info(qa.getName() + " - " +  map.get(qa).toString());
    	}
    }
    /**
     * DOCUMENT ME!
     *
     * @param args
     */
    public static void main(String[] args) {
        OntologyManager a = new OntologyManager();
        a.loadOntology();
        //a.listarOntologia();
        Map<QualityAttributeInterface, Double> map = a.getWordPertenence("User");
        a.imprimirMap(map);
    }
}
