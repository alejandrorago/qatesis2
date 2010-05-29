package filters;

import java.util.ArrayList;
import java.util.List;

import entities.RichedWord;


public class FilterManager {

	List<Filter> filters;

	public FilterManager() {
		filters = new ArrayList<Filter>();
	}
	
	public void setOntologyFilters(String stopWordsFile) {
		this.filters.clear();
		this.addFilter(new FilterLowerCase());
		this.addFilter(new FilterStopWords(stopWordsFile));
		this.addFilter(new FilterStemming());
	}

	public void setUseCaseFilters(String stopWordsFile, String weightFile) {
		this.filters.clear();
		this.addFilter(new FilterLowerCase());
		this.addFilter(new FilterStopWords(stopWordsFile));
		this.addFilter(new FilterStemming());
		this.addFilter(new FilterOcurrences());
		this.addFilter(new FilterWeight(weightFile));
	}
	
	public List<RichedWord> runFilters(List<RichedWord> listWords) {
		
		List<RichedWord> resultFilters = new ArrayList<RichedWord>();
		resultFilters.addAll(listWords);
		for (Filter f : filters) {
			resultFilters = f.filter(resultFilters);
		}
		return resultFilters;
	}
	
	
	public String runFiltersWord(String word) {
		List<RichedWord> result = new ArrayList<RichedWord>();
		result.add(new RichedWord(word));
		for (Filter f : filters) {
			f.filter(result);
		}
		if(!result.isEmpty()){
			return ((RichedWord)result.get(0)).getWord();
		}
		return null;
	}
	
	public void addFilter(Filter f) {
		filters.add(f);
	}

	public void removeFilter(Filter f) {
		filters.remove(f);
	}

}
