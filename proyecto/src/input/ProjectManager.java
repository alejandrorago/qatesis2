package input;

import java.io.FileReader;

import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.xml.Unmarshaller;
import org.xml.sax.InputSource;

/**
 * The Class ProjectManager.
 * 
 * @author Sebastian Villanueva
 * 
 */
public class ProjectManager {

	private Project project;

	/**
	 * Instantiates a new project manager.
	 */
	public ProjectManager() {
	}

	/**
	 * Load project.
	 *
	 * @param file the file
	 * @return the project
	 */

	public Project loadProject(String file) {
		Mapping mapping = new Mapping();
		try {
			mapping.loadMapping(getClass().getResource("mapping.xml"));
			Unmarshaller unmar = new Unmarshaller(mapping);
			Project p = (Project) unmar.unmarshal(new InputSource(
					new FileReader(file)));
			this.setProject(p);
			return this.getProject();

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	/**
	 * Gets the project.
	 *
	 * @return the project
	 */
	public Project getProject() {
		return project;
	}

	/**
	 * Sets the project.
	 *
	 * @param project the new project
	 */
	public void setProject(Project project) {
		this.project = project;
	}
}
