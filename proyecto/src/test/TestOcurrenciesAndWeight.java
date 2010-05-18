package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import utils.MapUtils;

import algorithms.Algorithm;
import algorithms.OntologyAlgorithm;
import entities.QualityAttributeInterface;
import entities.RichedWord;

/*
 * Clase para testear una lista de palabras con sus pesos y numero de ocurrencias
 * 
 */
//TODO Esto parece que anda joya, pero bien se podrias testear un poco mas
public class TestOcurrenciesAndWeight {
    public static void main(String[] args) {
    	
        List<RichedWord> listCase = new ArrayList<RichedWord>();
        RichedWord administr = new RichedWord("administr");
        administr.setAttribute("OCURRENCES", Integer.valueOf(1));
        listCase.add(administr);
        
        List<RichedWord> listAspect = new ArrayList<RichedWord>();
        RichedWord student = new RichedWord("student");
        student.setAttribute("OCURRENCES", Integer.valueOf(1));
        student.setAttribute("WEIGHT", Integer.valueOf(1));
        RichedWord administr2 = new RichedWord("administr");
        administr2.setAttribute("OCURRENCES", Integer.valueOf(1));
        listAspect.add(student);
        listAspect.add(administr2);
        
        Algorithm algorithm = new OntologyAlgorithm("file:resources/ontology.owl","file:resources/ontology.repository");
        algorithm.setUseCaseFactor(Double.valueOf(0.75));
        Map<QualityAttributeInterface,Double> map= algorithm.getQualityAttributePertenence(listCase,listAspect);
        MapUtils.imprimirMap(map);
    }
}
