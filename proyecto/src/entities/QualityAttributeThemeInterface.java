package entities;

import java.util.List;
import java.util.Map;


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$
  */
public interface QualityAttributeThemeInterface {
    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public EarlyAspectInterface getEarlyAspect();

    /**
     * DOCUMENT ME!
     *
     * @param earlyAspect DOCUMENT ME!
     */
    public void setEarlyAspect(EarlyAspectInterface earlyAspect);

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public List<UseCaseInterface> getUseCases();

    /**
     * DOCUMENT ME!
     *
     * @param useCases DOCUMENT ME!
     */
    public void setUseCases(List<UseCaseInterface> useCases);

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public Map<QualityAttributeInterface, Double> getMap();

    /**
     * DOCUMENT ME!
     *
     * @param map DOCUMENT ME!
     */
    public void setMap(Map<QualityAttributeInterface, Double> map);
}
