package test;

import java.util.List;

import wordtokenizer.UseCaseTokenizer;
import entities.RichedWord;
import entities.UseCase;



public class TestUseCaseTokenizer {
	
	
	public static void main(String[] args) {

		

		UseCase uc = new UseCase();
		uc.setName("Caso de Uso Uso 1");
		uc.setDescription("this use case is activated when the customer pays a bill");
		uc.setActor("Customer");
		uc.setPriority("Low");
		uc.setBasicFlow("The user logs into the system customer."); 
		uc.setBasicFlow("The user selects the option bill pay at the menu. He click the link"); 
		
		List<RichedWord> tokens = UseCaseTokenizer.tokenizeUseCase(uc);
		
		for (RichedWord tok : tokens) {
			System.out.println("Word: " + tok.getWord());
			System.out.println("Section: " + tok.getAttribute("SECTION"));
			System.out.println("Ocurrences: " + tok.getAttribute("OCURRENCES"));
			
		}
			
		
		
	}

}
