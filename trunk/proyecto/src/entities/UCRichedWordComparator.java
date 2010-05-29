package entities;

import java.util.Comparator;

public class UCRichedWordComparator implements Comparator<RichedWord> {

	public int compare(RichedWord rw1, RichedWord rw2) {


		String word1 = rw1.getWord();
		String word2 = rw2.getWord();
		String section1 = (String) rw1.getAttribute("SECTION");
		String section2 = (String) rw2.getAttribute("SECTION");

		if (word1.equalsIgnoreCase(word2)
				&& section1.equalsIgnoreCase(section2))
			return 0;
		else
			return -1;
		
	}

}