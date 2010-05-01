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
	 		String analizedWord = steemer.stemmer(rw.getWord());
	 		rw.setWord(analizedWord);
	 		result.add(rw);
 		}
	 	return result;
	}
}
