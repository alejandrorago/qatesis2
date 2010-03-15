package wordtokenizer;

import entities.RichedWord;
import entities.UseCase;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * @author Sebasti�n Villanueva
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
    public  List<RichedWord> tokenizeUseCase(UseCase uc) {
        List<RichedWord> result = new ArrayList<RichedWord>();
        result.addAll((List<RichedWord>) tokenize(uc.getName(), NAME));
        result.addAll((List<RichedWord>) tokenize(uc.getDescription(), DESCRIPTION));
        result.addAll((List<RichedWord>) tokenize(uc.getActor(), ACTOR));
        result.addAll((List<RichedWord>) tokenize(uc.getPriority(), PRIORITY));
        result.addAll((List<RichedWord>) tokenize(uc.getTrigger(), TRIGGUER));
        result.addAll((List<RichedWord>) tokenizeList(uc.getBasicFlow(), BASICFLOW));
        result.addAll((List<RichedWord>) tokenizeList(uc.getAlternativeFlow(), ALTERNATIVEFLOW));
        result.addAll((List<RichedWord>) tokenizeList(uc.getSpecialRequirement(), SPECIALREQUERIMENT));
        result.addAll((List<RichedWord>) tokenizeList(uc.getPreconditions(), PRECONDITIONS));
        result.addAll((List<RichedWord>) tokenizeList(uc.getPostconditions(), POSTCONDITIONS));
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
    private  List<RichedWord> tokenizeList(List<String> list,
        String section) {
        List<RichedWord> result = new ArrayList<RichedWord>();

        for (Iterator<String> i = list.iterator(); i.hasNext();) {
            String word = (String) i.next();
            result = tokenize(word.toLowerCase(), section);
        }

        return result;
    }

    /**
     *
     * Arma la lista de palabras agregando el valor de la palabra, la secci�n y el n�mero de ocurrencias.
     *
     * @param sectionwords
     * @param section
     * @return
     */
    private List<RichedWord> tokenize(String sectionwords, String section) {
        
    	List<RichedWord> result = new ArrayList<RichedWord>();
        StopWordsAnalizer stopWordsAnalizer = new StopWordsAnalizer();
        
        if (sectionwords != null) {
            String[] list = sectionwords.split("[\\s,;]+");

            for (int i = 0; i < list.length; i++) {
            	String palabra = list[i];
            	if (!stopWordsAnalizer.isStopWord(palabra)) {
                    RichedWord rw = new RichedWord(this.steemer.stemmer(palabra));
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
            }
        }

        return result;
    }
}
