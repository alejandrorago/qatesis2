package wordtokenizer;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import entities.RichedWord;
import entities.UseCase;
import filters.FilterLowerCase;
import filters.FilterManager;
import filters.FilterOcurrences;
import filters.FilterStemming;
import filters.FilterStopWords;

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


	FilterManager filterManager = new FilterManager();

	static final Logger logger = Logger.getLogger(UseCaseTokenizer.class);

	public UseCaseTokenizer(String stopWordsFile) {
		super();

		filterManager.addFilter(new FilterLowerCase());
		filterManager.addFilter(new FilterStopWords(stopWordsFile));
		filterManager.addFilter(new FilterStemming());
		filterManager.addFilter(new FilterOcurrences());

	}

	/**
	 * 
	 * Genera una lista de palabras y sus atributos (RichedWords) a partir de un
	 * caso de uso. Para ello recorre las diferentes secciones del mismo.
	 * 
	 * @param UseCase
	 * @return Lista de RichedWords
	 */
	public void tokenizeUseCase(UseCase uc, List<RichedWord> list) {

		logger.info("Tokenizing Use Case:" + uc.getName() + ".");

		tokenize(list, uc.getName(), NAME);
		tokenize(list, uc.getDescription(), DESCRIPTION);
		tokenize(list, uc.getActor(), ACTOR);
		tokenize(list, uc.getPriority(), PRIORITY);
		tokenize(list, uc.getTrigger(), TRIGGUER);
		tokenizeList(list, uc.getBasicFlow(), BASICFLOW);
		tokenizeList(list, uc.getAlternativeFlow(), ALTERNATIVEFLOW);
		tokenizeList(list, uc.getSpecialRequirement(), SPECIALREQUERIMENT);
		tokenizeList(list, uc.getPreconditions(), PRECONDITIONS);
		tokenizeList(list, uc.getPostconditions(), POSTCONDITIONS);

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
	private void tokenizeList(List<RichedWord> rwlist, List<String> list,
			String section) {

		for (Iterator<String> i = list.iterator(); i.hasNext();) {
			String word = (String) i.next();
			tokenize(rwlist, word.toLowerCase(), section);
		}

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
	private void tokenize(List<RichedWord> result, String text, String section) {

		logger.info("Tokenizing Section: " + section + ".");
		filterManager.runFilters(result, text, section);
	}

}
