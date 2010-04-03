package wordtokenizer;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

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

    StemmerIngles steemer;
   
    static final Logger logger = Logger.getLogger(UseCaseTokenizer.class);
	
    
    public UseCaseTokenizer() {
		super();
		this.steemer = new StemmerIngles();
	}

	/**
     *
     * Genera una lista de palabras y sus atributos (RichedWords) a partir de un caso de uso. Para ello recorre las diferentes secciones del mismo.
     *
     * @param UseCase
     * @return Lista de RichedWords
     */
    public  void tokenizeUseCase(UseCase uc, List<RichedWord> list) {

    	logger.info("Tokenizando Caso de Uso: " + uc.getName());

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
     * Se utiliza para las secciones del caso de uso que son una lista de elementos.
     * Se recorre la lista y se invoca al metodo tokenize con cada elemento.
     *
     * @param list
     * @param section
     * @return
     */
    private  void tokenizeList(List<RichedWord> rwlist, List<String> list,
        String section) {

        for (Iterator<String> i = list.iterator(); i.hasNext();) {
            String word = (String) i.next();
            tokenize(rwlist, word.toLowerCase(), section);
        }

    }

    /**
     *
     * Arma la lista de palabras agregando el valor de la palabra, la sección y el número de ocurrencias.
     *
     * @param sectionwords
     * @param section
     * @return
     */
    private void tokenize(List<RichedWord> result, String sectionwords, String section) {
        
    	StopWordsAnalizer stopWordsAnalizer = new StopWordsAnalizer();
        
        if (sectionwords != null) {
        
        	String[] list = sectionwords.split("[^a-zA-Z0-9]");

            for (int i = 0; i < list.length; i++) {
            	String palabra = list[i].toLowerCase();
            	if (!stopWordsAnalizer.isStopWord(palabra)) {
                   
                	logger.info("> Palabra Antes de Steeming: " + palabra);

                	palabra = this.steemer.stemmer(palabra);
                	RichedWord rw = new RichedWord(palabra);
                    
                	logger.info("> Palabra Pos Steeming: " + palabra);

                	rw.setAttribute(SECTION, section);
                    int indice = result.indexOf(rw);
                    if (indice < 0) {
                        rw.setAttribute(OCURRENCES, new Integer(1));
                        rw.setAttribute(SECTION, section);
                        result.add(rw);
                    } else {
                        rw = result.get(indice);
                        Integer ocurrencias = (Integer) rw.getAttribute(OCURRENCES);
                        rw.setAttribute(OCURRENCES, ++ocurrencias);
                    }
                }
            	else {
            	   	logger.info("> La palabra: " + palabra + " fue detectada como StopWord");
            	}
            		
            }
        }

       }
}
