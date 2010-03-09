package entities;

import java.util.List;


/**
 * Clase que representa a un QualityAttributeTheme. Este esta formado por
 * un conjunto de casos de uso, un early aspect reladciona a esos casos de uso 
 * y un atributo de calidad. 
 *
 * @author fbertoni
 * @
  */
public class QualityAttributeTheme implements QualityAttributeThemeInterface {
    /**
     * Atributo de calidad.
     */
    private QualityAttributeInterface qualityAttribute;

    /**
     * Early Aspect (Aspecto temprano)
     */
    private EarlyAspectInterface earlyAspect;

    /**
     * Listado de casos de uso
     */
    private List<UseCaseInterface> useCases;

    /**
     * Crea una nueva instancia de un objeto de tipo QualityAttributeTheme.
     */
    public QualityAttributeTheme() {
        qualityAttribute = null;
        earlyAspect = null;
        useCases = null;
    }

    /**
     * Devuelve el atributo de calidad
     *
     * @return atributo de calidad
     */
    public QualityAttributeInterface getQualityAttribute() {
        return qualityAttribute;
    }

    /**
     * Setea el atriburo de calidad
     *
     * @param qualityAttribute atributo de calidad a setear
     */
    public void setQualityAttribute(QualityAttributeInterface qualityAttribute) {
        this.qualityAttribute = qualityAttribute;
    }

    /**
     * Devuelve el Early Aspect
     *
     * @return Early Aspect
     */
    public EarlyAspectInterface getEarlyAspect() {
        return earlyAspect;
    }

    /**
     * Setea el early aspect
     *
     * @param earlyAspect Early Aspect
     */
    public void setEarlyAspect(EarlyAspectInterface earlyAspect) {
        this.earlyAspect = earlyAspect;
    }

    /**
     * Devuelve el listado de casos de uso relacionados con este early aspect
     *
     * @return listado de casos de uso 
     */
    public List<UseCaseInterface> getUseCases() {
        return useCases;
    }

    /**
     * Setea el listado de casos de uso
     *
     * @param useCases listado de casos de uso
     */
    public void setUseCases(List<UseCaseInterface> useCases) {
        this.useCases = useCases;
    }
}
