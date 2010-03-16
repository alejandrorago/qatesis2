package input;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import entities.UseCase;

public class Project {
	
	private String name;
	private List<UseCase> useCases;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<UseCase> getUseCases() {
		return useCases;
	}
	public void setUseCases(List<UseCase> useCases) {
		this.useCases = useCases;
	}

	public List<String> getActors() {
		List<String> actors = new ArrayList<String>(); 
		Iterator<UseCase> i = useCases.iterator();
		while (i.hasNext())  {
			UseCase u = (UseCase) i.next();
			String a = u.getActor();
			if (actors.contains(a)==false) 
			actors.add(u.getActor());
		}
		return actors;
	}
	
}
