package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import algorithms.Algorithm;
import algorithms.OntologyAlgorithm;

import utils.MapUtils;
import wordtokenizer.UseCaseTokenizer;
import input.Project;
import input.ProjectManager;
import entities.QualityAttributeInterface;
import entities.QualityAttributeThemeInterface;
import entities.RichedWord;
import entities.UseCaseInterface;

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
	
		QualityAttributeThemeInterface qat = (p.getQATs()).get(0);
		
		// OBTENGO LA LISTA DE PALBRABS PARA TODOS LOS CASOS DE USO
		UseCaseTokenizer uct = new UseCaseTokenizer("resources//stopWordsList.txt", "resources//useCaseWeights.properties");
		// CREO LA LISTA 
		List<RichedWord> tokensUseCase = new ArrayList<RichedWord>();
		// RECORRO TODOS LOS CASOS DE USO y LOS "TOKENIZO"
		for (UseCaseInterface uc : qat.getUseCases()) {
			uct.tokenizeUseCase(uc, tokensUseCase);
		}
		
        for (RichedWord tok : tokensUseCase) {
            System.out.println("Word: " + tok.getWord());
            System.out.println("Section: " + tok.getAttribute("SECTION"));
            System.out.println("Peso: " + tok.getAttribute("WEIGHT"));
            System.out.println("Ocurrences: " + tok.getAttribute("OCURRENCES") + "\n***\n");
        }
		
		
        //TODO Esto hay que hacer el tokenizer
		List<RichedWord> listAspect = new ArrayList<RichedWord>();
        RichedWord student = new RichedWord("student");
        student.setAttribute("OCURRENCES", Integer.valueOf(1));
        student.setAttribute("WEIGHT", Integer.valueOf(1));
        RichedWord administr2 = new RichedWord("administr");
        administr2.setAttribute("OCURRENCES", Integer.valueOf(1));
        listAspect.add(student);
        listAspect.add(administr2);
		//Hasta aca cambiar por el tokenizer
        
        Algorithm algorithm = new OntologyAlgorithm("file:resources/ontology.owl","file:resources/ontology.repository");
        algorithm.setUseCaseFactor(Double.valueOf(1.0));
        Map<QualityAttributeInterface,Double> map= algorithm.getQualityAttributePertenence(tokensUseCase,listAspect);
        MapUtils.imprimirMap(map);
	
	}

}
