package wordtokenizer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import entities.RichedWord;
import entities.UseCaseInterface;

/**
 * @author Sebastián Villanueva
 * 
 */
public class UseCaseTokenizer {

	private static final String NAME = "NAME";
	private static final String DESCRIPTION = "DESCRIPTION";
	private static final String BASICFLOW = "BASICFLOW";
	private static final String ACTOR = "ACTOR";
	private static final String PRIORITY = "PRIORITY";
	private static final String TRIGGUER = "TRIGGUER";
	private static final String ALTERNATIVEFLOW = "ALTERNATIVEFLOW";
	private static final String POSTCONDITIONS = "POSTCONDITIONS";
	private static final String SPECIALREQUERIMENT = "SPECIALREQUERIMENT";
	private static final String PRECONDITIONS = "PRECONDITIONS";

    private static final String SECTION = "SECTION";


	static final Logger logger = Logger.getLogger(UseCaseTokenizer.class);

	public UseCaseTokenizer() {
		super();
	}

	/**
	 * 
	 * Genera una lista de palabras y sus atributos (RichedWords) a partir de un
	 * caso de uso. Para ello recorre las diferentes secciones del mismo.
	 * 
	 * @param UseCase
	 * @return Lista de RichedWords
	 */
	public List<RichedWord> tokenizeUseCase(UseCaseInterface uc) {

		logger.info("Tokenizing Use Case:" + uc.getName() + ".");

		List<RichedWord> result = new ArrayList<RichedWord>();
		result.addAll(tokenize(uc.getName(), NAME));
		result.addAll(tokenize(uc.getDescription(), DESCRIPTION));
		result.addAll(tokenize(uc.getActor(), ACTOR));
		result.addAll(tokenize(uc.getPriority(), PRIORITY));
		result.addAll(tokenize(uc.getTrigger(), TRIGGUER));
		result.addAll(tokenizeList(uc.getBasicFlow(), BASICFLOW));
		result.addAll(tokenizeList(uc.getAlternativeFlow(), ALTERNATIVEFLOW));
		result.addAll(tokenizeList(uc.getSpecialRequirement(), SPECIALREQUERIMENT));
		result.addAll(tokenizeList(uc.getPreconditions(), PRECONDITIONS));
		result.addAll(tokenizeList(uc.getPostconditions(), POSTCONDITIONS));

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
	private List<RichedWord> tokenizeList(List<String> list,
			String section) {

		for (Iterator<String> i = list.iterator(); i.hasNext();) {
			String word = (String) i.next();
			return tokenize(word.toLowerCase(), section);
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
	private List<RichedWord> tokenize(String text, String section) {
		
		List<RichedWord> result = new ArrayList<RichedWord>();
		if (text != null) {
			logger.info("Tokenizing Section: " + section + ".");
			String[] list = text.split("[^a-zA-Z0-9]");
			for (int i = 0; i < list.length; i++) {
				RichedWord rw = new RichedWord(list[i]);
				if (section != null)
					rw.setAttribute(SECTION, section);
				result.add(rw);
			}
		}
		return result;
		
	}
}
