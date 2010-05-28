package filters;

import java.util.ArrayList;
import java.util.List;

import entities.RichedWord;




public class FilterManager {

    private static final String SECTION = "SECTION";
	List<Filter> filters;

	public FilterManager() {
		filters = new ArrayList<Filter>();
	}
	
	public void setOntologyFilters(String stopWordsFile) {
		this.addFilter(new FilterLowerCase());
		this.addFilter(new FilterStopWords(stopWordsFile));
		this.addFilter(new FilterStemming());
	}

	public void setUseCaseFilters(String stopWordsFile, String weightFile) {
		this.addFilter(new FilterLowerCase());
		this.addFilter(new FilterStopWords(stopWordsFile));
		this.addFilter(new FilterStemming());
		this.addFilter(new FilterWeight(weightFile));
	}
	
	public String[] splitList(String text) {
		
		if (text != null) {
			return text.split("[^a-zA-Z0-9]");
		}
		return new String[0];
	}
	
	public List<RichedWord> createList( String text, String section) {
		List<RichedWord> result = new ArrayList<RichedWord>();
		String[] list = splitList(text);
		for (int i = 0; i < list.length; i++) {
				RichedWord rw = new RichedWord(list[i]);
				if (section!=null)
					rw.setAttribute(SECTION, section);
				result.add(rw);
		}
		return result;
	}


	public void runFilters(List<RichedWord> result, String text, String section) {

		List<RichedWord> resultFilters = createList(text, section);
		for (Filter f : filters) {
			resultFilters = f.filter(resultFilters);
		}
		result.addAll(resultFilters);
		Filter a = new FilterOcurrences();
		result = a.filter(result);
		
	}

	public void runFilters(List<RichedWord> result, String text) {

		List<RichedWord> resultFilters = createList(text, null);
		for (Filter f : filters) {
			f.filter(resultFilters);
		}
		result.addAll(resultFilters);
		
	}
	
	
	public String runFiltersWord(String word) {
		List<RichedWord> result = createList(word, null);
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
