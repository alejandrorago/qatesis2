package test;

import java.util.regex.Pattern;

import input.WordReader;

public class TestInputWord {

	/**
	 * @param args
	 */

	public static void main(String[] args) {

		String filename = "resources//prueba.doc";
		WordReader analizador = new WordReader(filename);
		try {
			analizador.analize();
		} catch (Exception e2) {
			System.out.println("Error" + e2.toString());
		}

		String aaa = analizador.getText();
		System.out.println(aaa);
//		
//		String[] paragraphs = analizador.getParagraphs();
//		System.out.println("Word Document has " + paragraphs.length
//				+ " paragraphs");
//		for (int i = 0; i < paragraphs.length; i++) {
//			String illegals = "[]$^*\"'^’+%&/()“=?!_#½{}\\|-~`;,´.:<>\n\r";
//			String pattern = "[" + Pattern.quote(illegals) + "]";
//
//			String text = paragraphs[i];
//			String result = text.replaceAll(pattern, "");
//			System.out.println(paragraphs[i]);
//		}

	}

}
