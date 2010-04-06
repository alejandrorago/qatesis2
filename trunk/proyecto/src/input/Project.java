package input;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import entities.UseCase;

/**
 * The Class Project.
 *
 * @author Sebastian Villanueva
 */
public class Project {
	
	private String name;
	private List<UseCase> useCases;
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the use cases.
	 *
	 * @return the list of usecases
	 */
	public List<UseCase> getUseCases() {
		return useCases;
	}
	
	/**
	 * Sets the use cases.
	 *
	 * @param useCases the new use cases
	 */
	public void setUseCases(List<UseCase> useCases) {
		this.useCases = useCases;
	}

	/**
	 * Gets the actors.
	 *
	 * @return the actors
	 */
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
