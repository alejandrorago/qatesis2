package entities;

import java.util.Comparator;

public class EARichedWordComparator implements Comparator<RichedWord> {

	public int compare(RichedWord rw1, RichedWord rw2) {

		String word1 = rw1.getWord();
		String word2 = rw2.getWord();
		
		if (word1.equalsIgnoreCase(word2)) 
			return 0;
		else
			return -1;
	}

}