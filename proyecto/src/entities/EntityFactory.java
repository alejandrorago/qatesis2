package entities;

import java.util.List;
import java.util.Map;


/**
 * Factory de entidades, siguiendo el patron de diseño AbstractFactory,
 * extendiendo de la clase abstracta AbstractEntityFactory. Hasta ahora crea
 * instancias de EarlyAspect, UseCase y QualityAttribute,  devolviendo
 * interfaces que las mismas implementan.
 *
 * @author fbertoni
 */
public class EntityFactory extends AbstractEntityFactory {
    /**
     * DOCUMENT ME!
     *
     * @param name DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public QualityAttributeInterface creatQualityAttribute(String name) {
        QualityAttribute qualityAttribute = new QualityAttribute(name);

        return qualityAttribute;
    }

    /**
     * DOCUMENT ME!
     *
     * @param useCases DOCUMENT ME!
     * @param map DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public QualityAttributeThemeInterface creatQualityAttributeTheme(
        List<UseCaseInterface> useCases,
        Map<QualityAttributeInterface, Double> map) {
        QualityAttributeTheme qualityAttributeTheme = new QualityAttributeTheme();
        qualityAttributeTheme.setMap(map);
        qualityAttributeTheme.setUseCases(useCases);

        return qualityAttributeTheme;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public UseCaseInterface createUseCase() {
        UseCase useCase = new UseCase();

        return useCase;
    }

    /**
     * DOCUMENT ME!
     *
     * @param word DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public RichedWordInterface creatRichedWord(String word) {
        RichedWord richedWord = new RichedWord(word);

        return richedWord;
    }
}
