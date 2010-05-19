package entities;

import java.util.List;
import java.util.Map;

public abstract class AbstractEntityFactory {

	public abstract UseCaseInterface createUseCase();
	public abstract EarlyAspectInterface creatEarlyAspect(int id, String name, List<Integer> listUC, List<EarlyAspectPairInterface> listPairs);
	public abstract EarlyAspectPairInterface creatEarlyAspectPair(String verb, String noun);
	public abstract QualityAttributeInterface creatQualityAttribute(String name);
	public abstract QualityAttributeThemeInterface creatQualityAttributeTheme(List<UseCaseInterface> useCases, EarlyAspectInterface earlyAspect, Map<QualityAttributeInterface, Double> map);
	public abstract RichedWordInterface creatRichedWord(String word);
}
