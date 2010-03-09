package input;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import entities.UseCase;

public class Project {
	
	private String name;
	private List useCases;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List getUseCases() {
		return useCases;
	}
	public void setUseCases(List useCases) {
		this.useCases = useCases;
	}

	public List getActors() {
		List actors = new ArrayList(); 
		Iterator i = useCases.iterator();
		while (i.hasNext())  {
			UseCase u = (UseCase) i.next();
			String a = u.getActor();
			if (actors.contains(a)==false) 
			actors.add(u.getActor());
		}
		return actors;
	}
	
}
