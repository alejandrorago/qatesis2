package algorithms;

import java.util.List;

import entities.EarlyAspect;
import entities.QualityAttributeThemeInterface;
import entities.RichedWord;

public interface Algorithm {
	QualityAttributeThemeInterface getQualityAttributeTheme(List<RichedWord> words, EarlyAspect earlyAspect);
}
