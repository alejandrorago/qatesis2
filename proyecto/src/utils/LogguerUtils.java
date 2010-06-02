package utils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import wordtokenizer.Tokenizer;
import entities.RichedWord;

public class LogguerUtils {
	
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

}

