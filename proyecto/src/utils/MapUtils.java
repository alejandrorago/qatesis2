package utils;

import entities.QualityAttributeInterface;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * Utilidades para el manejo de maps
 *
 * @author fbertoni
 * @version $Revision$
  */
public class MapUtils {
    
	/**
     * Logger
     */
    static final Logger logger = Logger.getLogger(MapUtils.class);

    /**
     * Imprime un map por pantalla
     *
     * @param map
     */
    public static void imprimirMap(Map<QualityAttributeInterface, Double> map) {
        Iterator<QualityAttributeInterface> iterator = map.keySet().iterator();
        QualityAttributeInterface qa = null;

        while (iterator.hasNext()) {
            qa = iterator.next();
            logger.info(qa.getName() + " - " + map.get(qa).toString());
        }
    }

    /**
     * Divide cada uno de los valores del map por la variable Integer
     * pasada como parametro
     *
     * @param totalMap map de atributos de calidad como claves
     * @param totalWords total de palabras que se hab analizado, por los que
     *        hay que dividir cada uno de los valores del map
     *
     * @return map con los valores divididos por totalWords
     */
    public static Map<QualityAttributeInterface, Double> divideTotal(
        Map<QualityAttributeInterface, Double> totalMap, Integer totalWords) {
        Iterator<QualityAttributeInterface> qaIterator = totalMap.keySet()
                                                                 .iterator();
        QualityAttributeInterface qaInterface = null;

        while (qaIterator.hasNext()) {
            qaInterface = qaIterator.next();
            totalMap.put(qaInterface, totalMap.get(qaInterface) / totalWords);
        }

        return totalMap;
    }

    /**
     * Suma a cada valor de cada atributo de calidad de totalMap, el
     * valor que se encuentra en el mismo atributo del map wordmap
     *
     * @param totalMap map al que se le suma el valor
     * @param wordMap map de donde se saca el valor de cada clave, y se le suma
     *        al valor de la misma clave perteneciente a totalMap
     *
     * @return map con los valores sumados de ambos maps
     */
    public static Map<QualityAttributeInterface, Double> addTotal(
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

    /**
     * El map pasado como parametro contiene cada atributo de calidad
     * como clave y las ocurrencias de escenarios que hubo para cada atributo.
     * Este metodo calcula el porcentaje de total que representa cada uno de
     * esos escenarios y los almacena para cada atributo
     *
     * @param map con atributos de calidad como clave y un double como valor
     *
     * @return map con el porcentaje d total para cada atributo
     */
    public static Map<QualityAttributeInterface, Double> convertMapToPromedy(
        Map<QualityAttributeInterface, Double> map) {
        //TODO revisar el caso de que el map sea null, puede venir de antes
    	if (map==null) return null; 
    	Iterator<Double> values = map.values().iterator();
        Double total = Double.valueOf(0.0);

        while (values.hasNext()) {
            total = total + values.next();
        }

        Map<QualityAttributeInterface, Double> mapConverted = new HashMap<QualityAttributeInterface, Double>();
        Iterator<QualityAttributeInterface> iterator = map.keySet().iterator();
        QualityAttributeInterface qa = null;
        Double valor = null;

        while (iterator.hasNext()) {
            qa = iterator.next();
            valor = map.get(qa) / total;
            mapConverted.put(qa, valor);
        }

        return mapConverted;
    }
}
