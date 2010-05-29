package filters;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import entities.RichedWord;

public class FilterOcurrences extends Filter {

	private static final String OCURRENCES = "OCURRENCES";
	private Comparator<RichedWord> comparator;
	
	
	public FilterOcurrences(Comparator<RichedWord>  comp) {
		this.comparator = comp;
	}

	public List<RichedWord> filter(List<RichedWord> list) {

		List<RichedWord> result = new ArrayList<RichedWord>();
		for (RichedWord rw : list) {
			
			int indice = 0;
			int found = 0;
			while (indice<result.size()) {
				RichedWord rw2 = result.get(indice); 
				if (comparator.compare(rw, rw2)==0) {
					found=1;
					break;
				}
				indice++;
			} 
			
			if (found == 0) {
				rw.setAttribute(OCURRENCES,  new Integer(1));
				result.add(new RichedWord(rw.getWord(), rw.getAttributes()));
			} else {
				RichedWord rwRes = result.get(indice);
				Integer ocurrencias = (Integer) rwRes.getAttribute(OCURRENCES);
				rwRes.setAttribute(OCURRENCES, ++ocurrencias);
			}
		}
		return result;
	}
}
