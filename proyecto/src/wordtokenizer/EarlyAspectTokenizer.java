package wordtokenizer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import entities.EarlyAspectInterface;
import entities.EarlyAspectPair;
import entities.EarlyAspectPairInterface;
import entities.RichedWord;

/**
 * @author Sebastián Villanueva
 * 
 */
public class EarlyAspectTokenizer {


	static final Logger logger = Logger.getLogger(EarlyAspectTokenizer.class);

	public EarlyAspectTokenizer() {
		super();
	}

	/**
	 * 
	 * Genera una lista de palabras y sus atributos (RichedWords) a partir de un
	 * Early Aspect. 
	 * 
	 * @param EarlyAspect
	 * @return Lista de RichedWords
	 */
	public List<RichedWord> tokenize(EarlyAspectInterface ea) {

		logger.info("Tokenizing Use Case:" + ea.getName() + ".");

		List<RichedWord> result = new ArrayList<RichedWord>();
		result.addAll(doTokenize(ea.getName()));
		result.addAll(doTokenize(ea.getPairs()));

		return result;
	}

	/**
	 * 
	 * Se utiliza para las secciones del caso de uso que son una lista de
	 * elementos. Se recorre la lista y se invoca al metodo tokenize con cada
	 * elemento.
	 * 
	 * @param list
	 * @param section
	 * @return
	 */
	private List<RichedWord> doTokenize(List<EarlyAspectPairInterface> list){

		for (Iterator<EarlyAspectPairInterface> i = list.iterator(); i.hasNext();) {
			EarlyAspectPair ea = (EarlyAspectPair) i.next();
			String word = ea.getObject() + " " + ea.getVerb();			
			return doTokenize(word);
		}
		return new ArrayList<RichedWord>();
	}

	/**
	 * 
	 * Arma la lista de palabras agregando el valor de la palabra, la sección y
	 * el número de ocurrencias.
	 * 
	 * @param sectionwords
	 * @param section
	 * @return
	 */
	private List<RichedWord> doTokenize(String text) {
		
		List<RichedWord> result = new ArrayList<RichedWord>();
		if (text != null) {
			String[] list = text.split("[^a-zA-Z0-9]");
			for (int i = 0; i < list.length; i++) {
				RichedWord rw = new RichedWord(list[i]);
			result.add(rw);
			}
		}
		return result;
	}
}
