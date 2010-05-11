package filters;

import java.util.ArrayList;
import java.util.List;
import wordtokenizer.StopWordsAnalizer;
import entities.RichedWord;


public class FilterStopWords extends Filter {

	private String stopWordsFileName = null;
	
	public FilterStopWords(String filename) {
		this.stopWordsFileName = filename;
	}
	
	public List<RichedWord> filter(List<RichedWord> list) {

	 	StopWordsAnalizer stopWordsAnalizer = new StopWordsAnalizer(stopWordsFileName);
		List<RichedWord> result = new ArrayList<RichedWord>();
		
	 	for (RichedWord rw : list) {
	 		if (!stopWordsAnalizer.isStopWord(rw.getWord())) {
	 			result.add(rw);
	 		}
	 		else {
		 	 logger.info("Filtro StopWords: \"" + rw.getWord()+ "\" es una StopWord.");
	 		}
	 	}
	 	return result;
	}

}
