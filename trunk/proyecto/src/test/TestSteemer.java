package test;

import wordtokenizer.StemmerIngles;



public class TestSteemer {
	
	
	public static void main(String[] args) {

		
		StemmerIngles sp = new StemmerIngles();
		System.out.println(sp.stemmer("student"));
		System.out.println(sp.stemmer("students"));
		System.out.println(sp.stemmer("studentation"));
		System.out.println(sp.stemmer("hour"));
		System.out.println(sp.stemmer("hours"));
		System.out.println(sp.stemmer("modificability"));
		System.out.println(sp.stemmer("modify"));
		System.out.println(sp.stemmer("modifies"));
		System.out.println(sp.stemmer("modifier"));
		System.out.println(sp.stemmer("secure"));
		System.out.println(sp.stemmer("security"));
		System.out.println(sp.stemmer("secures"));	
		
	}

}
