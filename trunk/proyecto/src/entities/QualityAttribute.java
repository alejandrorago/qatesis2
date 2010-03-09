package entities;

/**
 * Clase que representa el atributo de calidad. Implementa la interfaz
 * QualityAtttributeInterface.
 *
 * @author fbertoni
 */
public final class QualityAttribute implements QualityAttributeInterface {
    /** Nombre dek atributo de calidad */
    private String name;

/**
     * Crea una nueva instancia de QualityAttribute
     *
     * @param name nombre del atributo de calidad
     */
    public QualityAttribute(String name) {
        this.name = name;
    }

    /**
     * Devuelve el nombre del atributo de calidad
     *
     * @return nombre del atributo de caliadad
     */
    public String getName() {
        return name;
    }

    /**
     * Setea el nombre del atributo de calidad
     *
     * @param name nombre del atributo de calidad
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * DOCUMENT ME!
     *
     * @param o DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public boolean equals(Object o) {
        QualityAttributeInterface qualityAttributeInterface = (QualityAttributeInterface) o;
        String q1 = qualityAttributeInterface.getName();
        String q2 = this.getName();

        if ((q1 != null) && (q2 != null)) {
            if ((q1 != null) && q1.equals(q2)) {
                return true;
            }
        }

        return false;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public int hashCode() {
        return this.getName().hashCode();
    }
}
