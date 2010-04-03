package test;

import input.Project;
import input.ProjectManager;

import java.util.ArrayList;
import java.util.List;

import wordtokenizer.UseCaseTokenizer;
import entities.RichedWord;
import entities.UseCase;

public class TestSeba {

	/**
	 * @param args
	 */

	public static void main(String[] args) {
		
		// LEO EL ARCHIVO @PROYECTO.XML@ donde estan los casos de uso
		
		ProjectManager pm = new ProjectManager();
		pm.loadProject(".//src//input//proyecto.xml");
		Project p = pm.getProject();
		
		
		// OBTENGO LA LISTA DE TOODS LOS CASOS DE USO
		
		List<UseCase> casosDeUso = p.getUseCases();
		
		// OBTENGO LA LISTA DE PALBRABS PARA TODOS LOS CASOS DE USO
		UseCaseTokenizer uct = new UseCaseTokenizer();

		// CREO LA LISTA 
		List<RichedWord> tokens = new ArrayList<RichedWord>();
		
		// RECORRO TODOS LOS CASOS DE USO y LOS "TOKENIZO"

		for (UseCase uc : casosDeUso) {
			uct.tokenizeUseCase(uc, tokens);
		}
		
		// MUESTRO LA SALIDA
		
	    for (RichedWord tok : tokens) {
            System.out.println("Word: " + tok.getWord());
            System.out.println("Section: " + tok.getAttribute("SECTION"));
            System.out.println("Ocurrences: " + tok.getAttribute("OCURRENCES") + "\n***\n");
        }
    	
	}

}
