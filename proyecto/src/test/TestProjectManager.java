package test;

import input.Project;
import input.ProjectManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import utils.LoggerUtils;
import wordtokenizer.Tokenizer;
import algorithms.Algorithm;
import algorithms.OntologyAlgorithm;
import entities.EARichedWordComparator;
import entities.QualityAttributeInterface;
import entities.QualityAttributeThemeInterface;
import entities.RichedWord;
import entities.UCRichedWordComparator;
import entities.UseCaseInterface;
import filters.FilterManager;

public class TestProjectManager {

	/**
	 * @param args
	 */

	public static void main(String[] args) {

		// con esto te retorna una lista de todos los casos de uso que estan en el XML
		
		ProjectManager pm = new ProjectManager();
		pm.loadProject("resources//proyecto.xml", "resources//mapping.xml");
		Project p = pm.getProject();
		System.out.println(p.getUseCases());
	
		QualityAttributeThemeInterface qat = (p.getQATs()).get(0);
		
		// OBTENGO LA LISTA DE PALBRABS PARA TODOS LOS CASOS DE USO
		Tokenizer tokenizer = new Tokenizer();		// CREO LA LISTA 
	
		List<RichedWord> tokensUseCase = new ArrayList<RichedWord>();
		// RECORRO TODOS LOS CASOS DE USO y LOS "TOKENIZO"
		for (UseCaseInterface uc : qat.getUseCases()) {
			tokensUseCase.addAll(tokenizer.tokenize(uc));
		}
       
        FilterManager fm= new FilterManager("resources/stopWordsList.txt","resources/useCaseWeight.properties");

		fm.setUseCaseFilters( new UCRichedWordComparator());
		List<RichedWord> tokensUseCaseFiltered = fm.runFilters(tokensUseCase);
		
		LoggerUtils.logList(tokensUseCaseFiltered, "Lista Casos de Uso Filtrados");
		
		List<RichedWord> tokensEA = new ArrayList<RichedWord>();
		// AGARRO EL EARLY ASPECT DEL PRIMER QAT .
		tokensEA.addAll(tokenizer.tokenize((p.getQATs()).get(0).getEarlyAspect()));
		fm.setEarlyAspectFilters(new EARichedWordComparator());
		List<RichedWord> tokensEAFiltered = fm.runFilters(tokensEA);
		
		LoggerUtils.logList(tokensEAFiltered, "Lista Early Aspects Filtrados");
		        
        Algorithm algorithm = new OntologyAlgorithm("file:resources/ontology.owl","file:resources/ontology.repository", fm);
        algorithm.setUseCaseFactor(Double.valueOf(0.5));
        Map<QualityAttributeInterface,Double> map= algorithm.getQualityAttributePertenence(tokensUseCaseFiltered,tokensEAFiltered);
	}
}
