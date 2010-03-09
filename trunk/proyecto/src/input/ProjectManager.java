package input;

import java.io.FileReader;

import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.xml.Unmarshaller;
import org.xml.sax.InputSource;

public class ProjectManager {

	private Project project;

	public ProjectManager() {
	}

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

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
}
