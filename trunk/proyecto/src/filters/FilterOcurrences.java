package filters;

import java.util.ArrayList;
import java.util.List;

import entities.RichedWord;

public class FilterOcurrences extends Filter {

	private static final String OCURRENCES = "OCURRENCES";

	public FilterOcurrences() {
	}

	public List<RichedWord> filter(List<RichedWord> list) {

		List<RichedWord> result = new ArrayList<RichedWord>();
		for (RichedWord rw : list) {
			int indice = result.indexOf(rw);
			if (indice < 0) {
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
