package entities;

import java.util.List;

public abstract class AbstractEntityFactory {

	public abstract UseCaseInterface createUseCase();
	public abstract EarlyAspectInterface creatEarlyAspect(String verb, String noun);
	public abstract QualityAttributeInterface creatQualityAttribute(String name);
	public abstract QualityAttributeThemeInterface creatQualityAttributeTheme(List<UseCaseInterface> useCases, EarlyAspectInterface earlyAspect, QualityAttributeInterface qualityAttribute);
	public abstract RichedWordInterface creatRichedWord(String word);
}
