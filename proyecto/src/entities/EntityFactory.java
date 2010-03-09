package entities;

import java.util.List;


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
     * Factory de entidades.
     *
     * @param verb DOCUMENT ME!
     * @param noun DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public EarlyAspectInterface creatEarlyAspect(String verb, String noun) {
        EarlyAspect earlyAspect = new EarlyAspect(noun, verb);

        return earlyAspect;
    }

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
     * @param earlyAspect DOCUMENT ME!
     * @param qualityAttribute DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public QualityAttributeThemeInterface creatQualityAttributeTheme(
        List<UseCaseInterface> useCases, EarlyAspectInterface earlyAspect,
        QualityAttributeInterface qualityAttribute) {
        QualityAttributeTheme qualityAttributeTheme = new QualityAttributeTheme();
        qualityAttributeTheme.setEarlyAspect(earlyAspect);
        qualityAttributeTheme.setQualityAttribute(qualityAttribute);
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
     * @param value DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public RichedWordInterface creatRichedWord(String word) {
        RichedWord richedWord = new RichedWord(word);

        return richedWord;
    }
}
