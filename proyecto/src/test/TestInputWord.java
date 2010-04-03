package test;

import input.WordReader;
import entities.UseCase;

public class TestInputWord {

	/**
	 * @param args
	 */

	public static void main(String[] args) {

		String filename = "resources//UseCaseSpecificationTemplate.doc";
		WordReader analizador = new WordReader(filename);
		try {
			analizador.analize();
		} catch (Exception e2) {
			System.out.println("Error" + e2.toString());
		}

		String aaa = analizador.getText();
		System.out.println(aaa);
		System.out.println("");
		UseCase a = analizador.getUsecase();
		System.out.println("Name: " + a.getName()+ "Description: "+ a.getDescription() + "Flow: " + a.getBasicFlow());
	
	}

}
