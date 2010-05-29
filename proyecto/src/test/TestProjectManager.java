package test;

import input.Project;
import input.ProjectManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import utils.MapUtils;
import wordtokenizer.EarlyAspectTokenizer;
import wordtokenizer.UseCaseTokenizer;
import algorithms.Algorithm;
import algorithms.OntologyAlgorithm;
import entities.QualityAttributeInterface;
import entities.QualityAttributeThemeInterface;
import entities.RichedWord;
import entities.UseCaseInterface;
import filters.FilterManager;

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
		UseCaseTokenizer uct = new UseCaseTokenizer();		// CREO LA LISTA 
		List<RichedWord> tokensUseCase = new ArrayList<RichedWord>();
		// RECORRO TODOS LOS CASOS DE USO y LOS "TOKENIZO"
		for (UseCaseInterface uc : qat.getUseCases()) {
			tokensUseCase.addAll(uct.tokenizeUseCase(uc));
		}
		FilterManager fm = new FilterManager();
		fm.setUseCaseFilters("resources//stopWordsList.txt", "resources//useCaseWeights.properties");
		List<RichedWord> tokensUseCaseFiltered = fm.runFilters(tokensUseCase);
		
		
/*        for (RichedWord tok : tokensUseCaseFiltered) {
            System.out.println("Word: " + tok.getWord());
            System.out.println("Section: " + tok.getAttribute("SECTION"));
            System.out.println("Peso: " + tok.getAttribute("WEIGHT"));
            System.out.println("Ocurrences: " + tok.getAttribute("OCURRENCES") + "\n***\n");
        }
*/		
		
        //TODO Hay que hacer el tokenizer la lista del earlyaspect
		List<RichedWord> tokensEA = new ArrayList<RichedWord>();
		EarlyAspectTokenizer eat = new EarlyAspectTokenizer();		// CREO LA LISTA 
		// AGARRO EL EARLY ASPECT DEL PRIMER QAT .
		tokensEA.addAll(eat.tokenize((p.getQATs()).get(0).getEarlyAspect()));
		fm.setOntologyFilters("resources//stopWordsList.txt");
		List<RichedWord> tokensEAFiltered = fm.runFilters(tokensEA);
		
        
        Algorithm algorithm = new OntologyAlgorithm("file:resources/ontology.owl","file:resources/ontology.repository");
        algorithm.setUseCaseFactor(Double.valueOf(1.0));
        Map<QualityAttributeInterface,Double> map= algorithm.getQualityAttributePertenence(tokensUseCaseFiltered,tokensEAFiltered);
        MapUtils.imprimirMap(map);
	
	}

}
