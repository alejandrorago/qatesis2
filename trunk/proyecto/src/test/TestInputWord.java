package test;

import input.WordReader;

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

		System.out.println(analizador.getText());
		
		System.out.println("\nCaso de Uso:"); 
		System.out.println(analizador.getUsecase().toString());			
	}

}
