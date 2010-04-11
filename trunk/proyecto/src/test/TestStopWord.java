package test;

import java.util.List;

import wordtokenizer.StopWordsAnalizer;



public class TestStopWord {
	
	
	public static void main(String[] args) {

		
		StopWordsAnalizer sp = new StopWordsAnalizer("resources/stopWordsList.txt");
		String descripcion = "the user is an operator or a student";
		String[] a = descripcion.split(" ");
		List<String> rdo = sp.analize(a);
		
		List<String> stopWords = sp.getStopWords();

		System.out.println("StopWords:");
		for (int i=0; i<stopWords.size(); i++) {
			System.out.println(stopWords.get(i));
		}
		
		System.out.println("\n\nResultado:");
		for (int i=0; i<rdo.size(); i++) {
			System.out.println(rdo.get(i));
		}
		
	}

}
