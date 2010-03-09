package test;

import java.util.List;

import wordtokenizer.StopWordsAnalizer;



public class TestStopWord {
	
	
	public static void main(String[] args) {

		
		StopWordsAnalizer sp = new StopWordsAnalizer();
		String descripcion = "the user is an operator or a student";
		String[] a = descripcion.split(" ");
		List rdo = sp.analize(a);
		
	}

}
