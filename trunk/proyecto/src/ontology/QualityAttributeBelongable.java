package ontology;

import java.util.Map;

import entities.QualityAttribute;

public interface QualityAttributeBelongable {
	Map<QualityAttribute,Integer> getWordPertenence(String word);
}
