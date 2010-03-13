package ontology;

import java.util.Map;

import entities.QualityAttributeInterface;

public interface QualityAttributeBelongable {
	Map<QualityAttributeInterface,Double> getWordPertenence(String word);
    Map<QualityAttributeInterface, Double> loadQualityAttributes();

}
