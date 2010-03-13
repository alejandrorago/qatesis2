package algorithms;

import entities.EarlyAspect;
import entities.QualityAttributeInterface;
import entities.QualityAttributeTheme;
import entities.RichedWord;

import ontology.QualityAttributeBelongable;

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
    private QualityAttributeBelongable qabelongable;

    /**
     * Devuelve un QualityAttributeTheme a partir de una lista de palabras y de un EarlyAspect
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
        Map<QualityAttributeInterface, Double> wordMap = null;
        Map<QualityAttributeInterface, Double> totalMap = qabelongable.loadQualityAttributes();

        Iterator<RichedWord> richedWordIterator = words.iterator();
        String word = null;

        while (richedWordIterator.hasNext()) {
            word = richedWordIterator.next().getWord();
            wordMap = qabelongable.getWordPertenence(word);
            totalMap = addTotal(totalMap, wordMap);
        }

        
        return null;
    }

    /**
     * Suma a cada valor de cada atributo de calidad de totalMap, el valor que se encuentra en el mismo atributo del map wordmap
     *
     * @param totalMap map al que se le suma el valor
     * @param wordMap map de donde se saca el valor de cada clave, y se le suma al valor de la misma clave perteneciente a totalMap
     *
     * @return map con los valores sumados de ambos maps
     */
    private  Map<QualityAttributeInterface, Double> addTotal(
        Map<QualityAttributeInterface, Double> totalMap,
        Map<QualityAttributeInterface, Double> wordMap) {
        Iterator<QualityAttributeInterface> qaIterator = wordMap.keySet()
                                                                .iterator();
        QualityAttributeInterface qaInterface = null;
                
        while (qaIterator.hasNext()) {
            qaInterface = qaIterator.next();
            totalMap.put(qaInterface,
                totalMap.get(qaInterface) + wordMap.get(qaInterface));
        }

        return totalMap;
    }
    
    private  Map<QualityAttributeInterface, Double> divideTotal(
            Map<QualityAttributeInterface, Double> totalMap,
            Double totalWords) {
            
    		Iterator<QualityAttributeInterface> qaIterator = totalMap.keySet()
                                                                    .iterator();
            QualityAttributeInterface qaInterface = null;

            while (qaIterator.hasNext()) {
                qaInterface = qaIterator.next();
                totalMap.put(qaInterface,
                    totalMap.get(qaInterface)/Integer.valueOf(2));
            }

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
}
