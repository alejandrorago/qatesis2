/**
 * 
 */
package ontology;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

import entities.QualityAttribute;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * DOCUMENT ME!
 *
 * @author fran
 */
public class OntologyManager implements QualityAttributeBelongable {
    /** DOCUMENT ME! */
    private OntModel ontModel;

    /**
     * DOCUMENT ME!
     */
    public OntologyManager() {
    	this.loadOntology();
    }

    /**
     * DOCUMENT ME!
     */
    public void loadOntology() {
        this.ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM,
                null);
        this.ontModel.getDocumentManager()
                     .addAltEntry("file:resources/ontology.repository",
            "file:resources/ontology.owl");
        this.ontModel.read("file:resources/ontology.repository");
    }


    /**
     *NO SE USA!!!!
     */
    public boolean hasInstanceValue(String word) {
        Property concreteSourceName = this.ontModel.getProperty(Uris.concreteSourceName);
        NodeIterator auxRdfNode = this.ontModel.listObjectsOfProperty(concreteSourceName);

        Literal auxLiteral;

        while (auxRdfNode.hasNext()) {
            RDFNode rdfNode = auxRdfNode.next();

            if (rdfNode.isLiteral()) {
                String literalValue = (String) rdfNode.asNode().getLiteralValue();

                if (literalValue.equalsIgnoreCase(word.trim())) {
                    return true;
                }
            }
        }

        return false;
    }


    /**
     * Devuelve un recurso que se relacione con el valor literal pasado como parametro, mediante la propiedad
     * de uri pasada como parametro.
     * En caso de haber mas de una instancia, devuelve la primera que encuentra.
     * En caso de que no haya ninguna instancia, devuelve null
     *
     * @param propertyUri DOCUMENT ME!
     * @param literal DOCUMENT ME!
     *
     * @return recurso que se relaciona con el string literal mediante la Property con uri igual a propertyUri, null en caso contrario
     * 
     */
    public Resource getInstanceFromResource(String propertyUri, String literal) {
        Property property = this.ontModel.getProperty(propertyUri);
        ResIterator instancias = this.ontModel.listSubjectsWithProperty(property,
                literal);
        Resource instancia = null;

        //Si la instancia existe, tiene que ser unica
        if (instancias.hasNext()) {
            instancia = instancias.nextResource();
        }

        return instancia;
    }

    
    /**
     * Dado un valor literal, este metodo devuelve el recurso que se relaciona con ese valor. La idea es que 
     * dada una palabra, se infiera a que parte de un escenario concreto se refiere. Por la tanto, el recurso
     * que se devuelve es una instancia de concreteSource, concreteStimulus, concreteEnviroment, etc.
     * Puede pasar que haya dos instancias que se relacionen con esa palabra. Por ejemplo, hay un Stimulus que tiene nombre
     * "User" y hay un Source que tiene el nombre "User". En esta caso, se toma la instancia con la que se relacionen
     * mas escenarios concretos. Es decir, si 10 escenarios tienen como fuente a "User" y solo 2 como estimulo a "user",
     * se devuelve la instancia q es de tipo Source.
     *
     * @param word palabra
     *
     * @return instancias que se relaciona con el parametro, null si no hay ninguna
     */
    public Resource getWordMeaning(String word) {
        Resource actualResource = null;
        int actualCount = -1;

        String auxUri;
        Property auxProperty = null;
        Resource auxResource = null;
        int auxCount;

        /*
         * El metodo concreteXNameconcreteScenarioHasXUri nos devuelve los uris de dos propiedades:
         * la que relaciona una parte del escenario con su valor literal(ej: sourceHasName) y la que relaciona
         * esa instancia con un la del escenario (ej: concreteScenarioHasSource).
         * La ides es utilizar la primer propiedad para verificar si existe alguna instancia de alguna parte del escenario
         * que se relacione con la palabra en cuestion. Si existe alguna, chequeamos cuantos escenarios se relacionan con
         * esa instancia mediante la segunda propiedad.
         * De esta manera, devolvemos el recurso que se relaciona con el mayor nuemero de escenarios.
         */
        Map<String, String> map = Uris.concreteXNameANDconcreteScenarioHasXUri();
        Iterator<String> iterator = map.keySet().iterator();

        while (iterator.hasNext()) {
            auxUri = iterator.next();
            auxResource = this.getInstanceFromResource(auxUri, word);

             //se chequea que exista alguna instancia con esa palabra
             if (auxResource != null) {
                /*
                 * La instancia existe, es decir, hay alguna instancia de algua parte de un escenario (Source, Stimules,
                 * Enviroment, etc) que se relaciona con la palabra
                 */
                auxProperty = this.ontModel.getProperty(map.get(auxUri));
                /*
                * auxCount representa la cantidad de escenarios que se relacionan con la instancia que se relaciona
                * con word
                */
                auxCount = this.ontModel.listSubjectsWithProperty(auxProperty,
                        auxResource).toList().size();

                if (auxCount > actualCount) {
                    actualCount = auxCount;
                    actualResource = auxResource;
                }
            }
        }

        return actualResource;
    }

    
    public Resource getType(Resource resource){
    	Resource typeResource = null;
    	Statement statement = resource.getProperty(this.ontModel.getProperty(Uris.type));
  	    if (statement != null){
  	    	typeResource = this.ontModel.getResource(statement.getObject().asNode().getURI());
  	    }
    	return typeResource;
    }
    
    public Map<QualityAttribute, Integer> loadQualityAttributes(){

    	Map<QualityAttribute, Integer> map = new HashMap<QualityAttribute, Integer>(); 
    	Property qualityAttributeNameProperty =this.ontModel.getProperty(Uris.qualityAttributeName);
    	ResIterator qualityAttributeIterator = this.ontModel.listResourcesWithProperty(this.ontModel.getProperty(Uris.type), this.ontModel.getResource(Uris.qualityAttributeUri));
    	
    	Resource qualityAttributeResource = null;
    	String qualityAttributeName = null;
    	
    	while (qualityAttributeIterator.hasNext()){
    		qualityAttributeResource = qualityAttributeIterator.next();
    		qualityAttributeName = qualityAttributeResource.listProperties(qualityAttributeNameProperty).next().getString();
    		map.put(new QualityAttribute(qualityAttributeName), 0);
    	}
    	
    	return map;
    }
    
    
    /**
     * Lista por consola todas las sentencia de la ontologia.
     */
    public void listarOntologia() {
        Statement b = null;
        RDFNode rdfNode;
        StmtIterator stmIterator = this.ontModel.listStatements();

        while (stmIterator.hasNext()) {
            Statement a = stmIterator.nextStatement();
            System.out.println(a.getSubject().getURI());
            System.out.println(a.getPredicate().getURI());

            Literal auxLiteral;
            rdfNode = a.getObject();

            if (rdfNode.isLiteral()) {
                auxLiteral = a.getLiteral();
                System.out.println("Un literal:" + auxLiteral.getString());
            } else {
                System.out.println(a.getResource().getURI());
            }

            System.out.println("---------------------------------------");
        }
    }
	public String getAttributeNameFromConcreteScenario(Resource scenario){
		
		Property iSpecificOfProperty = this.ontModel.getProperty(Uris.isSpecificof);
		Property qualityAttributeNameProperty = this.ontModel.getProperty(Uris.qualityAttributeName);
		
		String qualityAttributeInscanceUri = scenario.listProperties(iSpecificOfProperty).nextStatement().getObject().asNode().getURI();
		Resource qualityAttributeInstanceResource = this.ontModel.getResource(qualityAttributeInscanceUri);
		
		String qualityAttributeName = qualityAttributeInstanceResource.listProperties(qualityAttributeNameProperty).next().getString();

		return qualityAttributeName;
	}
    
	@Override
	public Map<QualityAttribute, Integer> getWordPertenence(String word) {
		
		Map<QualityAttribute, Integer> map = this.loadQualityAttributes();
		
		Resource scenarioPart = this.getWordMeaning(word); //Devuelve un numero de instancia, ejemplo intance_0
		
		Resource scenarioPartType = this.getType(scenarioPart); //Devuevel el tipo de esa instancia, ejemplo, http://www.owl-ontologies.com/unnamed.owl#ConcreteSource

		//A partir de ese concreteSource, se averigua el Uri de la propiedad con la que se relaciona con las instancias
		//de escenarios. Siguiendo el ejemplo, el metodo nos devolveria el uri http://www.owl-ontologies.com/unnamed.owl#concreteScenarioHasSource
		String concreteScenarioHasXUri = Uris.getScenarioHasUriFromConcretePartUri(scenarioPartType.getURI());
		Property concreteScenarioHasXProperty= this.ontModel.getProperty(concreteScenarioHasXUri);
		
		//Con los datos que tenemos averiguamos todos los escenarios que se relacionan con scenario part, mediante 
		ResIterator scenariosIterator = this.ontModel.listSubjectsWithProperty(concreteScenarioHasXProperty, scenarioPart);
		Resource scenario = null;
		QualityAttribute qualityAttribute = null;
		
		while (scenariosIterator.hasNext()){
			scenario = scenariosIterator.next();
			qualityAttribute = new QualityAttribute(this.getAttributeNameFromConcreteScenario(scenario));
			map.put(qualityAttribute, map.get(qualityAttribute)+1);
		}
		
		return map;
	}
	
    /**
     * DOCUMENT ME!
     *
     * @param args
     */
    public static void main(String[] args) {
        OntologyManager a = new OntologyManager();
        a.loadOntology();
        a.listarOntologia();
        Map<QualityAttribute,Integer> map = a.getWordPertenence("User");
    }
}
