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
    	
        List<RichedWord> list = new ArrayList<RichedWord>();
        RichedWord administr = new RichedWord("administr");
        administr.setAttribute("OCURRENCES", Integer.valueOf(9));
        list.add(administr);
        RichedWord student = new RichedWord("student");
        student.setAttribute("OCURRENCES", Integer.valueOf(1));
        student.setAttribute("WEIGHT", Integer.valueOf(10));
        list.add(student);
        
        Algorithm algorithm = new OntologyAlgorithm("file:resources/ontology.owl","file:resources/ontology.repository");
        Map<QualityAttributeInterface,Double> map= algorithm.getQualityAttributePertenence(list,null);
        MapUtils.imprimirMap(map);
    }
}
