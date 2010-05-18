package utils;

import entities.QualityAttributeInterface;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * Utilidades para el manejo de maps
 *
 * @author fbertoni
 * @version $Revision$
 */
public class MapUtils {
    /** Logger */
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
    public static Map<QualityAttributeInterface, Double> addMaps(
        Map<QualityAttributeInterface, Double> map1,
        Map<QualityAttributeInterface, Double> map2) {
        Iterator<QualityAttributeInterface> qaIterator = map1.keySet()
                                                                .iterator();
        QualityAttributeInterface qaInterface = null;

        while (qaIterator.hasNext()) {
            qaInterface = qaIterator.next();
            map1.put(qaInterface,
            		map1.get(qaInterface) + map2.get(qaInterface));
        }

        return map1;
    }

    /**
     * El map pasado como parametro contiene cada atributo de calidad
     * como clave y las ocurrencias de escenarios que hubo para cada atributo.
     * Este metodo calcula el porcentaje de total que representa cada uno de
     * esos escenarios y los almacena para cada atributo
     *
     * @param map con atributos de calidad como clave y un double como valor
     *
     * @return map con el porcentaje de total para cada atributo
     */
    public static Map<QualityAttributeInterface, Double> convertMapToPromedy(
        Map<QualityAttributeInterface, Double> map) {
        //Si el map es null, se retorna null
        if (map == null) {
            return null;
        }

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

    /**
     * Esta funcion maultiplica cada valor del map por la variable
     * factor.
     *
     * @param map con QualityAttributeInterface como clave y una variable de
     *        tipo Double como valor
     * @param factor Double por el que se multiplica cada valor del map
     *
     * @return map con los valores multiplicados por la variable factor
     */
    public static Map<QualityAttributeInterface, Double> multiplyMapByFactor(
        Map<QualityAttributeInterface, Double> map, Double factor) {
        QualityAttributeInterface qa = null;
        Iterator<QualityAttributeInterface> iterator = map.keySet().iterator();

        while (iterator.hasNext()) {
            qa = iterator.next();
            map.put(qa, map.get(qa) * factor);
        }

        return map;
    }
    

    public static Map<QualityAttributeInterface, Double> divideMapByFactor(
        Map<QualityAttributeInterface, Double> map, Double factor) {
        QualityAttributeInterface qa = null;
        Iterator<QualityAttributeInterface> iterator = map.keySet().iterator();

        while (iterator.hasNext()) {
            qa = iterator.next();
            map.put(qa, map.get(qa) / factor);
        }

        return map;
    }

    /**
     * Toma como parametro una lista de QualittyAtributesInterfaces.
     * Itera sobre la lista y va a gregando cada  uno de ellos, como clave a
     * un HasMap, con valor 0.0
     *
     * @param list lista de atributos de calidad
     *
     * @return Map con cada atributo de calidad como clave y el double 0.0 como
     *         valor
     */
    public static Map<QualityAttributeInterface, Double> convertAttributesLisToMap(
        List<QualityAttributeInterface> list) {
        Map<QualityAttributeInterface, Double> map = new HashMap<QualityAttributeInterface, Double>();

        for (QualityAttributeInterface qa : list) {
            map.put(qa, Double.valueOf(0.0));
        }

        return map;
    }
}
