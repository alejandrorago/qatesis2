package algorithms;

import java.util.List;

import entities.EarlyAspect;
import entities.QualityAttributeTheme;
import entities.RichedWord;

public interface Algorithm {
	QualityAttributeTheme getQualityAttributeTheme(List<RichedWord> words, EarlyAspect earlyAspect);
}
