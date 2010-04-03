package test;

import input.Project;
import input.ProjectManager;

public class TestProjectManager {

	/**
	 * @param args
	 */

	public static void main(String[] args) {

		// con esto te retorna una lista de todos los casos de uso que estan en el XML
		
		ProjectManager pm = new ProjectManager();
		pm.loadProject(".//src//input//proyecto.xml");
		Project p = pm.getProject();
		System.out.println(p.getUseCases());
	}

}
