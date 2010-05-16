package ontology;

import java.util.List;
import java.util.Map;

import entities.QualityAttributeInterface;

public interface QualityAttributeBelongable {
	Map<QualityAttributeInterface,Double> getWordPertenence(String word);
    List<QualityAttributeInterface> loadQualityAttributes();

}
