package utils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import wordtokenizer.Tokenizer;
import entities.QualityAttributeInterface;
import entities.RichedWord;

public class LoggerUtils {
	
	private static final Logger logger = Logger.getLogger(Tokenizer.class);


	public static  void logList(List<RichedWord> list, String mensaje) {
		
		logger.info(mensaje +" *** Comienzo");
	
		for (RichedWord tok : list) {
			logger.info("Word: " + tok.getWord());
			Map<String, Object> map = (Map<String, Object>) tok.getAttributes();
			Iterator<String> iterator = map.keySet().iterator(); 
			String key = null;
			while (iterator.hasNext()) {
	            key= iterator.next();
	            logger.info("* " + key + ": " + map.get(key).toString());
				}
			}
		logger.info(mensaje +" *** Fin");
	}
	
    public static void imprimirMap(Map<QualityAttributeInterface, Double> map, String mensaje) {
        Iterator<QualityAttributeInterface> iterator = map.keySet().iterator();
        QualityAttributeInterface qa = null;

        logger.info(mensaje +" *** Comienzo");
        while (iterator.hasNext()) {
            qa = iterator.next();
            logger.info(qa.getName() + " - " + map.get(qa).toString());
        }
        logger.info(mensaje +" *** Fin");
    }

}

