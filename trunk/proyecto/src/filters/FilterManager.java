package filters;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import entities.RichedWord;


public class FilterManager {

	private String stopWordsFile;
	private String weightFile;
	
	List<Filter> filters;

	public FilterManager(String stopWordsFile, String weightFile) {
		this.stopWordsFile = stopWordsFile;
		this.weightFile = weightFile;
		filters = new ArrayList<Filter>();
	}
	
	public void setOntologyFilters() {
		this.filters.clear();
		this.addFilter(new FilterLowerCase());
		this.addFilter(new FilterStopWords(stopWordsFile));
		this.addFilter(new FilterStemming());
	}

	public void setUseCaseFilters(Comparator<RichedWord> comp) {
		this.filters.clear();
		this.addFilter(new FilterLowerCase());
		this.addFilter(new FilterStopWords(stopWordsFile));
		this.addFilter(new FilterStemming());
		this.addFilter(new FilterOcurrences(comp));
		this.addFilter(new FilterWeight(weightFile));
	}
	
	public void setEarlyAspectFilters(Comparator<RichedWord> comp) {
		this.filters.clear();
		this.addFilter(new FilterLowerCase());
		this.addFilter(new FilterStopWords(stopWordsFile));
		this.addFilter(new FilterStemming());
		this.addFilter(new FilterOcurrences(comp));
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

	public String getStopWordsFile() {
		return stopWordsFile;
	}

	public void setStopWordsFile(String stopWordsFile) {
		this.stopWordsFile = stopWordsFile;
	}

	public String getWeightFile() {
		return weightFile;
	}

	public void setWeightFile(String weightFile) {
		this.weightFile = weightFile;
	}

	
}
