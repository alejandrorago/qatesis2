package entities;

import java.util.List;

public class EarlyAspect implements EarlyAspectInterface {

	private int id;
	private String name;
	private List<EarlyAspectPairInterface> pairs;
	

	public EarlyAspect() {
		
	}
	
	public EarlyAspect(int id, String name, List<EarlyAspectPairInterface> list) {
		this.id = id;
		this.name = name;
		this.pairs = list;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<EarlyAspectPairInterface> getPairs() {
		return pairs;
	}

	public void setPairs(List<EarlyAspectPairInterface> pairs) {
		this.pairs = pairs;
	}
	
}
