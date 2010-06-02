package wordtokenizer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import utils.LoggerUtils;

import entities.EarlyAspectInterface;
import entities.EarlyAspectPair;
import entities.EarlyAspectPairInterface;
import entities.RichedWord;
import entities.UseCaseInterface;

/**
 * @author Sebastián Villanueva
 * 
 */
public class Tokenizer {

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
    private static final String TYPE = "TYPE";
    private static final String ID = "ID";
    private static final String USECASE = "USECASE";
    private static final String EARLYASPECT = "EARLYASPECT";
    private static final String PAIR = "PAIR";


	private static final Logger logger = Logger.getLogger(Tokenizer.class);

	public Tokenizer() {
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
	public List<RichedWord> tokenize(UseCaseInterface uc) {

		logger.info("Tokenizing Use Case:" + uc.getName() + ".");

		List<RichedWord> result = new ArrayList<RichedWord>();
		result.addAll(doTokenize(uc.getName(), USECASE, Integer.toString(uc.getId()) , NAME));
		result.addAll(doTokenize(uc.getDescription(), USECASE, Integer.toString(uc.getId()),DESCRIPTION));
		result.addAll(doTokenize(uc.getActor(), USECASE, Integer.toString(uc.getId()),ACTOR));
		result.addAll(doTokenize(uc.getPriority(), USECASE, Integer.toString(uc.getId()),PRIORITY));
		result.addAll(doTokenize(uc.getTrigger(), USECASE, Integer.toString(uc.getId()),TRIGGUER));
		result.addAll(doTokenize(uc.getBasicFlow(), USECASE, Integer.toString(uc.getId()),BASICFLOW));
		result.addAll(doTokenize(uc.getAlternativeFlow(),USECASE, Integer.toString(uc.getId()), ALTERNATIVEFLOW));
		result.addAll(doTokenize(uc.getSpecialRequirement(),USECASE, Integer.toString(uc.getId()),SPECIALREQUERIMENT));
		result.addAll(doTokenize(uc.getPreconditions(),USECASE, Integer.toString(uc.getId()),PRECONDITIONS));
		result.addAll(doTokenize(uc.getPostconditions(),USECASE, Integer.toString(uc.getId()),POSTCONDITIONS));
		LoggerUtils.logList(result, "Lista Casos de Uso Entrada");
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
	private List<RichedWord> doTokenize(List<String> list, String type, String id, String section) {

		List<RichedWord> result = new ArrayList<RichedWord>();
		for (Iterator<String> i = list.iterator(); i.hasNext();) {
			String word = (String) i.next();
			result.addAll(doTokenize(word.toLowerCase(),type, id, section));
		}
		return result;
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
	private List<RichedWord> doTokenize(String text, String type, String id, String section) {
		
		List<RichedWord> result = new ArrayList<RichedWord>();
		if (text != null) {
			String[] list = text.split("[^a-zA-Z0-9]");
			for (int i = 0; i < list.length; i++) {
				RichedWord rw = new RichedWord(list[i]);
				rw.setAttribute(TYPE, type);
				rw.setAttribute(ID, id);
				if (section != null)
					rw.setAttribute(SECTION, section);
				result.add(rw);
			}
		}
		return result;
		
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

		logger.info("Tokenizing Early Aspect:" + ea.getName() + ".");
		List<RichedWord> result = new ArrayList<RichedWord>();
		result.addAll(doTokenize(ea.getName(),EARLYASPECT, Integer.toString(ea.getId()) , NAME));
		result.addAll(doTokenize(ea.getPairs(), Integer.toString(ea.getId())));
		LoggerUtils.logList(result, "Lista Early Aspect Entrada");
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
	private List<RichedWord> doTokenize(List<EarlyAspectPairInterface> list, String id){

		List<RichedWord> result = new ArrayList<RichedWord>();
		for (Iterator<EarlyAspectPairInterface> i = list.iterator(); i.hasNext();) {
			EarlyAspectPair ea = (EarlyAspectPair) i.next();
			String word = ea.getObject() + " " + ea.getVerb();			
			result.addAll(doTokenize(word,EARLYASPECT,id,PAIR));
		}
		return result;
	}
	
	
}
