package entities;

import java.util.List;

public class Relation {

	private int earlyAspectId;
	private List<Integer> useCaseIds;
	
	public void setEarlyAspectId(int earlyAspectId) {
		this.earlyAspectId = earlyAspectId;
	}
	public int getEarlyAspectId() {
		return earlyAspectId;
	}
	public void setUseCaseIds(List<Integer> useCaseIds) {
		this.useCaseIds = useCaseIds;
	}
	public List<Integer> getUseCaseIds() {
		return useCaseIds;
	}
	
	
	
	
}
