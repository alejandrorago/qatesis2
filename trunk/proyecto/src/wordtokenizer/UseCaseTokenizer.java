package wordtokenizer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import entities.RichedWord;
import entities.UseCase;

/**
 * @author Sebastián Villanueva
 *
 */
public class UseCaseTokenizer {

	
	private static final String SECTION = "SECTION";
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
	private static final String OCURRENCES = "OCURRENCES";
	

	/**
	 * 
	 * Genera una lista de palabras y sus atributos (RichedWords) a partir de un caso de uso. Para ello recorre las diferentes secciones del mismo.
	 * 
	 * @param UseCase
	 * @return Lista de RichedWords 
	 */
	public static List<RichedWord> tokenizeUseCase(UseCase uc) {
			
		List<RichedWord> result = new ArrayList<RichedWord>();
		result.addAll((List<RichedWord>) tokenize(uc.getName(),NAME));
		result.addAll((List<RichedWord>) tokenize(uc.getDescription(),DESCRIPTION));
		result.addAll((List<RichedWord>) tokenize(uc.getActor(),ACTOR));
		result.addAll((List<RichedWord>) tokenize(uc.getPriority(),PRIORITY));
		result.addAll((List<RichedWord>) tokenize(uc.getTrigger(),TRIGGUER));
		result.addAll((List<RichedWord>) tokenizeList(uc.getBasicFlow(),BASICFLOW));
		result.addAll((List<RichedWord>) tokenizeList(uc.getAlternativeFlow(),ALTERNATIVEFLOW));
		result.addAll((List<RichedWord>) tokenizeList(uc.getSpecialRequirement(),SPECIALREQUERIMENT));
		result.addAll((List<RichedWord>) tokenizeList(uc.getPreconditions(),PRECONDITIONS));
		result.addAll((List<RichedWord>) tokenizeList(uc.getPostconditions(),POSTCONDITIONS));
		return result;
	}

	/**
	 * 
	 * Se utiliza para las secciones del caso de uso que son una lista de elementos.
	 * Se recorre la lista y se invoca al metodo tokenize con cada elemento.
	 * 
	 * @param list
	 * @param section
	 * @return
	 */
	private static List<RichedWord> tokenizeList(List<String> list, String section) {
		
		List<RichedWord> result = new ArrayList<RichedWord>();
		for (Iterator<String> i = list.iterator(); i.hasNext(); ) {
			String word = (String) i.next();
			result = tokenize(word.toLowerCase(), section);
		}
		return result;
	}

	/**
	 * 
	 * Arma la lista de palabras agregando el valor de la palabra, la sección y el número de ocurrencias.
	 * 
	 * @param sectionwords
	 * @param section
	 * @return
	 */
	private static List<RichedWord> tokenize(String sectionwords, String section) {

		List<RichedWord> result = new ArrayList<RichedWord>();
		StopWordsAnalizer stopWordsAnalizer = new StopWordsAnalizer();
		
		if (sectionwords!=null) { 		
			String[] list = sectionwords.split("[\\s,;]+");
			for (int i = 0; i < list.length; i++) {
				if (!stopWordsAnalizer.isStopWord(list[i])) {
					RichedWord rw = new RichedWord(list[i]);
					rw.setAttribute(SECTION, section);
					int indice = result.indexOf(rw);
						if (indice<0) {
							rw.setAttribute(OCURRENCES, new Integer(1));
							result.add(rw); 
							
						}
						else {
							rw = result.get(indice);
							Integer ocurrencias = (Integer) rw.getAttribute(OCURRENCES);
							if (ocurrencias == null) {
								ocurrencias = new Integer(1);
							} 
							else {
								ocurrencias++;
							}
							rw.setAttribute(OCURRENCES, ocurrencias);
						}
				}
		}
		}
		return result;
	}
		
}
