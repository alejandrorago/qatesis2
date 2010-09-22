package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import utils.MapUtils;

import algorithms.Algorithm;
import algorithms.OntologyAlgorithm;
import entities.QualityAttributeInterface;
import entities.RichedWord;
import filters.FilterManager;

/*
 * Clase para testear una lista de palabras con sus pesos y numero de ocurrencias
 * 
 */
public class TestOcurrenciesAndWeight {
    public static void main(String[] args) {
    	
        List<RichedWord> listCase = new ArrayList<RichedWord>();
        RichedWord administr = new RichedWord("user2");
        administr.setAttribute(RichedWord.OCURRENCES, Integer.valueOf(1));
        listCase.add(administr);
        RichedWord xxx = new RichedWord("system");
        xxx.setAttribute(RichedWord.OCURRENCES, Integer.valueOf(1));
        listCase.add(xxx);
        
        List<RichedWord> listAspect = new ArrayList<RichedWord>();
        RichedWord student = new RichedWord("student");
        student.setAttribute(RichedWord.OCURRENCES, Integer.valueOf(1));
        student.setAttribute(RichedWord.WEIGHT, Integer.valueOf(1));
        RichedWord administr2 = new RichedWord("stimulus1");
        administr2.setAttribute(RichedWord.OCURRENCES, Integer.valueOf(1));
        listAspect.add(student);
        listAspect.add(administr2);
        
        FilterManager fm= new FilterManager("resources/stopWordsList.txt","resources/config.properties");
        Algorithm algorithm = new OntologyAlgorithm("file:resources/ontology.owl","file:resources/ontology.repository",fm,0.5);
        algorithm.setUseCaseFactor(Double.valueOf(0.50));
        Map<QualityAttributeInterface,Double> map= algorithm.getQualityAttributePertenence(listCase,listAspect);
        MapUtils.imprimirMap(map);
    }
}
