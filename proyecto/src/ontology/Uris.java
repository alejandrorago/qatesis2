package ontology;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$
  */
public class Uris {
    /**
     * DOCUMENT ME!
     */
    public static final String qualityAttributeUri = "http://www.owl-ontologies.com/unnamed.owl#QualityAttribute";

    /**
     * DOCUMENT ME!
     */
    public static final String qualityAttributeName = "http://www.owl-ontologies.com/unnamed.owl#qualityAttributeName";

    /**
     * DOCUMENT ME!
     */
    public static final String concreteSourceUri = "http://www.owl-ontologies.com/unnamed.owl#ConcreteSource";

    /**
     * DOCUMENT ME!
     */
    public static final String concreteStimulusUri = "http://www.owl-ontologies.com/unnamed.owl#ConcreteStimulus";

    /**
     * DOCUMENT ME!
     */
    public static final String concreteEnviromentUri = "http://www.owl-ontologies.com/unnamed.owl#ConcreteEnviroment";

    /**
     * DOCUMENT ME!
     */
    public static final String concreteArtifactUri = "http://www.owl-ontologies.com/unnamed.owl#ConcreteArtifact";

    /**
     * DOCUMENT ME!
     */
    public static final String concreteResponseUri = "http://www.owl-ontologies.com/unnamed.owl#ConcreteResponse";

    /**
     * DOCUMENT ME!
     */
    public static final String concreteResponseMeasureUri = "http://www.owl-ontologies.com/unnamed.owl#ConcreteResponseMeasure";

    /**
     * DOCUMENT ME!
     */
    public static final String concreteScenarioHasSourceUri = "http://www.owl-ontologies.com/unnamed.owl#concreteScenarioHasSource";

    /**
     * DOCUMENT ME!
     */
    public static final String concreteScenarioHasStimlusUri = "http://www.owl-ontologies.com/unnamed.owl#concreteScenarioHasStimlus";

    /**
     * DOCUMENT ME!
     */
    public static final String concreteScenarioHasEnviromentUri = "http://www.owl-ontologies.com/unnamed.owl#concreteScenarioHasEnviroment";

    /**
     * DOCUMENT ME!
     */
    public static final String concreteScenarioHasArtifactUri = "http://www.owl-ontologies.com/unnamed.owl#concreteScenarioHasArtifact";

    /**
     * DOCUMENT ME!
     */
    public static final String concreteScenarioHasResponseUri = "http://www.owl-ontologies.com/unnamed.owl#concreteScenarioHasResponse";

    /**
     * DOCUMENT ME!
     */
    public static final String concreteScenarioHasResponseMeasureUri = "http://www.owl-ontologies.com/unnamed.owl#concreteScenarioHasResponseMeasure";

    /**
     * DOCUMENT ME!
     */
    public static final String type = "http://www.w3.org/1999/02/22-rdf-syntax-ns#type";

    /**
     * DOCUMENT ME!
     */
    public static final String isSpecificof = "http://www.owl-ontologies.com/unnamed.owl#isSpecificof";

    /**
     * DOCUMENT ME!
     */
    public static final String concreteScenarioPartDescription = "http://www.owl-ontologies.com/unnamed.owl#concreteScenarioPartDescription";

    /**
     * DOCUMENT ME!
     */
    public static final String formatedInstanceUri = "http://www.owl-ontologies.com/unnamed.owl#ontology_formated_Instance_";

    /**
     * DOCUMENT ME!
     *
     * @param concreteXUri DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static String getScenarioHasUriFromConcretePartUri(
        String concreteXUri) {
        if (concreteXUri.equals(Uris.concreteSourceUri)) {
            return Uris.concreteScenarioHasSourceUri;
        }

        if (concreteXUri.equals(Uris.concreteStimulusUri)) {
            return Uris.concreteScenarioHasStimlusUri;
        }

        if (concreteXUri.equals(Uris.concreteEnviromentUri)) {
            return Uris.concreteScenarioHasEnviromentUri;
        }

        if (concreteXUri.equals(Uris.concreteArtifactUri)) {
            return Uris.concreteScenarioHasArtifactUri;
        }

        if (concreteXUri.equals(Uris.concreteResponseUri)) {
            return Uris.concreteScenarioHasResponseUri;
        }

        return concreteScenarioHasResponseMeasureUri;
    }
}
