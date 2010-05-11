package filters;

import java.util.ArrayList;
import java.util.List;

import entities.RichedWord;


public class FilterLowerCase extends Filter {

	
	public FilterLowerCase() {
	}
	
	public List<RichedWord> filter(List<RichedWord> list) {

		List<RichedWord> result = new ArrayList<RichedWord>();
		for (RichedWord rw : list) {
			String originalWord = rw.getWord();
	 		rw.setWord(originalWord.toLowerCase());
	 		result.add(rw);
	 		if (!originalWord.equals(rw.getWord()))
	 		logger.info("Filtro LowerCase: " + originalWord + " -> " + rw.getWord()+ ".");
 		}	
	 	return result;
	}
}
