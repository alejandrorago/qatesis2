package entities;

import java.util.List;
import java.util.Map;


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$
  */
public abstract class AbstractEntityFactory {
    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public abstract UseCaseInterface createUseCase();

    /**
     * DOCUMENT ME!
     *
     * @param name DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public abstract QualityAttributeInterface creatQualityAttribute(String name);

    /**
     * DOCUMENT ME!
     *
     * @param useCases DOCUMENT ME!
     * @param map DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public abstract QualityAttributeThemeInterface creatQualityAttributeTheme(
        List<UseCaseInterface> useCases,
        Map<QualityAttributeInterface, Double> map);

    /**
     * DOCUMENT ME!
     *
     * @param word DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public abstract RichedWordInterface creatRichedWord(String word);
}
