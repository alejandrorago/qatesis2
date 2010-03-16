package entities;

import java.util.List;

public interface UseCaseInterface {

	public int getId();
	public void setId(int id);
	public String getName();
	public void setName(String name) ;
	public String getDescription();
	public void setDescription(String description);
	public String getActor();
	public void setActor(String actor);
	public String getPriority();
	public void setPriority(String priority) ;
	public String getTrigger();
	public void setTrigger(String trigger);
	public List<String> getBasicFlow() ;
	public void setBasicFlow(String basicFlow);
	public List<String> getAlternativeFlow();
	public void setAlternativeFlow(String alternativeFlow);
	public List<String> getSpecialRequirement();
	public void setSpecialRequirement(String especialRequirement);
	public List<String> getPreconditions();
	public void setPreconditions(String preconditions);
	public List<String> getPostconditions();
	public void setPostconditions(String postconditions);

}
