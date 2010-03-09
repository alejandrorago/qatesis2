package ontology;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Uris {
	
	public static final String  qualityAttributeUri = "http://www.owl-ontologies.com/unnamed.owl#QualityAttribute";
	public static final String  qualityAttributeName = "http://www.owl-ontologies.com/unnamed.owl#qualityAttributeName";
	
	
	public static final String  concreteSourceUri = "http://www.owl-ontologies.com/unnamed.owl#ConcreteSource";
	public static final String  concreteStimulusUri = "http://www.owl-ontologies.com/unnamed.owl#ConcreteStimulus";
	public static final String  concreteEnviromentUri = "http://www.owl-ontologies.com/unnamed.owl#ConcreteEnviroment";
	public static final String  concreteArtifactUri = "http://www.owl-ontologies.com/unnamed.owl#ConcreteArtifact";
	public static final String  concreteResponseUri = "http://www.owl-ontologies.com/unnamed.owl#ConcreteResponse";
	public static final String  concreteResponseMeasureUri = "http://www.owl-ontologies.com/unnamed.owl#ConcreteResponseMeasure";

	
	public static final String  concreteScenarioHasSourceUri = "http://www.owl-ontologies.com/unnamed.owl#concreteScenarioHasSource";
	public static final String  concreteScenarioHasStimlusUri = "http://www.owl-ontologies.com/unnamed.owl#concreteScenarioHasStimlus";
	public static final String  concreteScenarioHasEnviromentUri = "http://www.owl-ontologies.com/unnamed.owl#concreteScenarioHasEnviroment";
	public static final String  concreteScenarioHasArtifactUri = "http://www.owl-ontologies.com/unnamed.owl#concreteScenarioHasArtifact";
	public static final String  concreteScenarioHasResponseUri = "http://www.owl-ontologies.com/unnamed.owl#concreteScenarioHasResponse";
	public static final String  concreteScenarioHasResponseMeasureUri = "http://www.owl-ontologies.com/unnamed.owl#concreteScenarioHasResponseMeasure";

	public static final String  concreteSourceName = "http://www.owl-ontologies.com/unnamed.owl#concreteSourceName";
	public static final String  concreteStimulusName = "http://www.owl-ontologies.com/unnamed.owl#concreteStimulusName";
	public static final String  concreteEnviromentName = "http://www.owl-ontologies.com/unnamed.owl#concreteEnviromentName";
	public static final String  concreteArtifactName = "http://www.owl-ontologies.com/unnamed.owl#concreteArtifactName";
	public static final String  concreteResponseName = "http://www.owl-ontologies.com/unnamed.owl#concreteResponseName";
	public static final String  concreteResponseMeasureName = "http://www.owl-ontologies.com/unnamed.owl#concreteResponseMeasureName";

	public static final String  type = "http://www.w3.org/1999/02/22-rdf-syntax-ns#type";
	public static final String  isSpecificof = "http://www.owl-ontologies.com/unnamed.owl#isSpecificof";

	
	public static Map<String, String> concreteXNameANDconcreteScenarioHasXUri(){
		
		Map<String,String> uris= new HashMap<String,String>();
		uris.put(Uris.concreteSourceName,concreteScenarioHasSourceUri);
		uris.put(Uris.concreteStimulusName,concreteScenarioHasStimlusUri);
		uris.put(Uris.concreteEnviromentName,concreteScenarioHasEnviromentUri);
		uris.put(Uris.concreteArtifactName,concreteScenarioHasArtifactUri);
		uris.put(Uris.concreteResponseName,concreteScenarioHasResponseUri);
		uris.put(Uris.concreteResponseMeasureName,concreteScenarioHasResponseMeasureUri);
		return uris;
	}
	
	public static String getScenarioHasUriFromConcretePartUri(String concreteXUri){
		
		if (concreteXUri.equals(Uris.concreteSourceUri)){
			return Uris.concreteScenarioHasSourceUri;
		}
		if (concreteXUri.equals(Uris.concreteStimulusUri)){
			return Uris.concreteScenarioHasStimlusUri;
		}
		if (concreteXUri.equals(Uris.concreteEnviromentUri)){
			return Uris.concreteScenarioHasEnviromentUri;
		}
		if (concreteXUri.equals(Uris.concreteArtifactUri)){
			return Uris.concreteScenarioHasArtifactUri;
		}
		if (concreteXUri.equals(Uris.concreteResponseUri)){
			return Uris.concreteScenarioHasResponseUri;
		}
		return concreteScenarioHasResponseMeasureUri;
	}
	
}
