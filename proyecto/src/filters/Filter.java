package filters;

import java.util.List;

import entities.RichedWord;

public abstract class Filter {

	public abstract List<RichedWord> filter(List<RichedWord> list);
	
}
