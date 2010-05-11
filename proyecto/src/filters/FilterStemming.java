package filters;

import java.util.ArrayList;
import java.util.List;

import wordtokenizer.Stemmer;
import wordtokenizer.StemmerIngles;
import entities.RichedWord;


public class FilterStemming extends Filter {

	
	public FilterStemming() {
	}
	
	public List<RichedWord> filter(List<RichedWord> list) {

		Stemmer steemer = new StemmerIngles();
		List<RichedWord> result = new ArrayList<RichedWord>();
		
	 	for (RichedWord rw : list) {
	 		String originalWord = rw.getWord();
	 		rw.setWord(steemer.stemmer(originalWord));
	 		result.add(rw);
	 		if (!originalWord.equals(rw.getWord()))
		 		logger.info("Filtro Stemming: " + originalWord + " -> " + rw.getWord()+ ".");
 		}
	 	return result;
	}
}
