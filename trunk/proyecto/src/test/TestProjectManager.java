package test;

import input.Project;
import input.ProjectManager;

public class TestProjectManager {

	/**
	 * @param args
	 */

	public static void main(String[] args) {

		ProjectManager pm = new ProjectManager();
		pm.loadProject(".//src//logica//proyecto.xml");
		Project p = pm.getProject();
		System.out.println(p.getUseCases());
	}

}
