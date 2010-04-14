package entities;

import java.util.List;
import java.util.Map;


/**
 * Clase que representa a un QualityAttributeTheme. Este esta formado por
 * un conjunto de casos de uso, un early aspect reladciona a esos casos de uso
 * y un atributo de calidad.
 *
 * @author fbertoni
 */
public class QualityAttributeTheme implements QualityAttributeThemeInterface {
    /** DOCUMENT ME! */
    private Map<QualityAttributeInterface, Double> map;

    /** Listado de casos de uso */
    private List<UseCaseInterface> useCases;

/**
     * Crea una nueva instancia de un objeto de tipo QualityAttributeTheme.
     */
    public QualityAttributeTheme() {
        this.map = null;
        useCases = null;
    }

    /**
     * Devuelve el listado de casos de uso relacionados con este early
     * aspect
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

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public Map<QualityAttributeInterface, Double> getMap() {
        return map;
    }

    /**
     * DOCUMENT ME!
     *
     * @param map DOCUMENT ME!
     */
    public void setMap(Map<QualityAttributeInterface, Double> map) {
        this.map = map;
    }
}
