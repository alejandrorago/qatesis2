package entities;

import java.util.List;


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
    public QualityAttributeInterface getQualityAttribute();

    /**
     * DOCUMENT ME!
     *
     * @param qualityAttribute DOCUMENT ME!
     */
    public void setQualityAttribute(QualityAttributeInterface qualityAttribute);

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
}
