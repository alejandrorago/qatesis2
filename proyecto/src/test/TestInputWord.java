package test;

import input.WordReader;

public class TestInputWord {

	/**
	 * @param args
	 */

	public static void main(String[] args) {

		String filename = ".//src//test//use_case_template.doc";
		WordReader analizador = new WordReader(filename);
		try {
			analizador.analize();
		} catch (Exception e2) {
			System.out.println("Error" + e2.toString());
		}

		System.out.println(analizador.getText());

	}

}
