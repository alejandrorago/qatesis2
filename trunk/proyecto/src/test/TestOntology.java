package test;

import java.util.Map;

import ontology.OntologyManager;
import ontology.QualityAttributeBelongable;

import org.apache.log4j.Logger;

import utils.MapUtils;

import entities.QualityAttributeInterface;

public class TestOntology {
	
    static final Logger logger = Logger.getLogger(TestOntology.class);

	/*
	 * CASO 1
	 * En este caso de test existe un solo escenario de uso con una palabra "response" 
	 * que es un ResponseMeasure de un esecenario de calidad que se relaciona con Performance.
	 */
    
    /*
	 * Se chequea el metodo wordPertenence por la palabra "response"
	 */
	public static void caso11(){
		
		QualityAttributeBelongable qualityAttributeBelongable = new OntologyManager("file:resources/caso1.owl","file:resources/caso1.repository");
		Map<QualityAttributeInterface,Double> map = qualityAttributeBelongable.getWordPertenence("response");
		MapUtils.imprimirMap(map);
	}
	/*
	 * Se chequea el metodo wordPertenence por la palabra "pan", que no existe en la ontologia
	 */	
	public static void caso12(){
		
		QualityAttributeBelongable qualityAttributeBelongable = new OntologyManager("file:resources/caso1.owl","file:resources/caso1.repository");
		Map<QualityAttributeInterface,Double> map = qualityAttributeBelongable.getWordPertenence("pan");
		if(map==null){
			System.out.println("El map es null");
		}
	}

	/*
	 * CASO 2
	 * En este caso de test existe un solo escenario de uso con una palabra "response1 response2" 
	 * que es un ResponseMeasure de un esecenario de calidad que se relaciona con Performance.
	 */
	
	/*
	 * Se chequea el metodo wordPertenence por la palabra "response2"
	 */	
	public static void caso21(){
		
		QualityAttributeBelongable qualityAttributeBelongable = new OntologyManager("file:resources/caso2.owl","file:resources/caso2.repository");
		Map<QualityAttributeInterface,Double> map = qualityAttributeBelongable.getWordPertenence("response2");
		if(map==null){
			System.out.println("El map es null");
		}else{
			MapUtils.imprimirMap(map);
		}
	}
		/*
		 * CASO 3
		 * En este caso de test existen 3 escenarios.
		 * Dos de ellos tiene como enviroment a "response1 response3" y ambos se relacionan con el atributo de calidad
		 * Aviability.
		 * El restante tiene como response a "response1 response2", y se relaciona con el atributo performance.
		 */
		
		/*
		 * Se chequea el metodo wordPertenence por la palabra "response1". Como esta aparece en 2 escenarios
		 * como un enviroment, deberia ser considerada como una instancia de concreteEnviroment.
		 */	
		public static void caso31(){
			
			QualityAttributeBelongable qualityAttributeBelongable = new OntologyManager("file:resources/caso3.owl","file:resources/caso3.repository");
			Map<QualityAttributeInterface,Double> map = qualityAttributeBelongable.getWordPertenence("response1");
			if(map==null){
				System.out.println("El map es null");
			}else{
				MapUtils.imprimirMap(map);
			}
	}
		
		/*
		 * Se chequea el metodo wordPertenence por la palabra "response1". Como esta aparece en 2 escenarios
		 * como un enviroment, deberia ser considerada como una instancia de concreteEnviroment.
		 */	
		public static void caso32(){
			
			QualityAttributeBelongable qualityAttributeBelongable = new OntologyManager("file:resources/caso3.owl","file:resources/caso3.repository");
			Map<QualityAttributeInterface,Double> map = qualityAttributeBelongable.getWordPertenence("response2");
			if(map==null){
				System.out.println("El map es null");
			}else{
				MapUtils.imprimirMap(map);
			}
	}
		
	/*
	 * CASO 3
	 * En este caso de test existen 3 escenarios.
	 * Dos de ellos tiene como enviroment a "response1 response3". Uno de ellos relaciona con el atributo de calidad
	 * Aviability, mientras que el otro con Modificability.
	 * El restante tiene como response a "response1 response2", y se relaciona con el atributo performance.
	 */
		
	/*
	 * Se chequea el metodo wordPertenence por la palabra "response1". Como esta aparece en 2 escenarios
	 * como un enviroment, deberia ser considerada como una instancia de concreteEnviroment.
	 * Ademas, el map deberia relacionar la palabra con los atributos de Modificability y Aviability, con 
	 * valor de 1 en ambos casos
	 */	
	public static void caso41(){
		
		QualityAttributeBelongable qualityAttributeBelongable = new OntologyManager("file:resources/caso4.owl","file:resources/caso4.repository");
		Map<QualityAttributeInterface,Double> map = qualityAttributeBelongable.getWordPertenence("response1");
		if(map==null){
			System.out.println("El map es null");
		}else{
			MapUtils.imprimirMap(map);
		}
	}

	public static void main(String[] args) {
		TestOntology.caso41();
	}

}
