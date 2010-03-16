package test;

import entities.RichedWord;
import entities.UseCase;

import wordtokenizer.UseCaseTokenizer;

import java.util.List;


public class TestUseCaseTokenizer {
    public static void main(String[] args) {
        UseCase uc = new UseCase();
        uc.setName("Use Case 1 . Use Case");
        uc.setDescription(
            "this use case is activated when the customer pays a bill . The bill . . .");
        uc.setActor("Customer");
        uc.setPriority("Low");
        uc.setBasicFlow("The user logs into the system customer.");
        uc.setBasicFlow(
            "The user selects the option bill pay at the menu. He click the link");

        UseCaseTokenizer uct = new UseCaseTokenizer();
        List<RichedWord> tokens = uct.tokenizeUseCase(uc);

        for (RichedWord tok : tokens) {
            System.out.println("Word: " + tok.getWord());
            System.out.println("Section: " + tok.getAttribute("SECTION"));
            System.out.println("Ocurrences: " + tok.getAttribute("OCURRENCES") + "\n***\n");
        }
    }
}
