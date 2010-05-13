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
				rw.setAttribute(OCURRENCES, new Integer(1));
				result.add(rw);
			} else {
				rw = result.get(indice);
				Integer ocurrencias = (Integer) rw.getAttribute(OCURRENCES);
				rw.setAttribute(OCURRENCES, ++ocurrencias);
			}

		}
		list=result;
		return list;
	}

}
