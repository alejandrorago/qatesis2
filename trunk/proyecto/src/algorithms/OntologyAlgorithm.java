package algorithms;

import entities.EarlyAspect;
import entities.QualityAttributeInterface;
import entities.QualityAttributeTheme;
import entities.RichedWord;

import ontology.OntologyAnalyzer;
import ontology.QualityAttributeBelongable;

import org.apache.log4j.Logger;

import utils.MapUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * Implementacion de un algoritmo que utiliza una ontologia.
 *
 * @author $author$
 * @version $Revision$
 */
public class OntologyAlgorithm implements Algorithm {
    /**
     * DOCUMENT ME!
     */
    static final Logger logger = Logger.getLogger(OntologyAlgorithm.class);

    /** DOCUMENT ME! */
    private QualityAttributeBelongable qabelongable;

    /**
     * Creates a new OntologyAlgorithm object.
     *
     * @param owlFilePath DOCUMENT ME!
     * @param repositoryFilePath DOCUMENT ME!
     */
    public OntologyAlgorithm(String owlFilePath, String repositoryFilePath) {
        this.qabelongable = new OntologyAnalyzer(owlFilePath, repositoryFilePath);
    }

    /**
     * Devuelve un QualityAttributeTheme a partir de una lista de
     * palabras y de un EarlyAspect
     *
     * @param words DOCUMENT ME!
     * @param earlyAspect DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */

    //TODO darle bolilla al EarlyAspect, ¿como lo usamos?
    @Override
    public QualityAttributeTheme getQualityAttributeTheme(
        List<RichedWord> words, EarlyAspect earlyAspect) {
        Map<QualityAttributeInterface, Double> map = this.getAttributesMap(words);
        MapUtils.imprimirMap(map);

        return null;
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
        Map<QualityAttributeInterface, Double> totalMap = qabelongable.loadQualityAttributes();

        Iterator<RichedWord> richedWordIterator = words.iterator();
        String word = null;
        Integer totalWords = 0;

        while (richedWordIterator.hasNext()) {
            word = richedWordIterator.next().getWord();
            wordMap = MapUtils.convertMapToPromedy(qabelongable.getWordPertenence(
                        word));

            if (wordMap != null) {
                totalMap = MapUtils.addTotal(totalMap, wordMap);
                totalWords++;
            }
        }

        totalMap = MapUtils.divideTotal(totalMap, totalWords);

        return totalMap;
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
     * DOCUMENT ME!
     *
     * @param args DOCUMENT ME!
     */
    public static void main(String[] args) {
        Algorithm algorithm = new OntologyAlgorithm("file:resources/ontology.repository",
                "file:resources/ontology.owl");

        List<RichedWord> list = new ArrayList<RichedWord>();
        list.add(new RichedWord("User2"));
        list.add(new RichedWord("Stimulus1"));

        QualityAttributeTheme qt = algorithm.getQualityAttributeTheme(list, null);
        System.out.println(qt.getQualityAttribute().getName());
        
    }
}
