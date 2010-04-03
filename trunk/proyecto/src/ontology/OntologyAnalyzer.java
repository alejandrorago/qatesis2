/**
 * 
 */
package ontology;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

import entities.AbstractEntityFactory;
import entities.EntityFactory;
import entities.QualityAttributeInterface;
import entities.RichedWord;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Clase Ontology Manager
 *
 * @author fran
 */
public class OntologyAnalyzer implements QualityAttributeBelongable {
    /** DOCUMENT ME! */
    private static final Logger logger = Logger.getLogger(OntologyAnalyzer.class);

    /** DOCUMENT ME! */
    protected OntModel ontModel;

/**
     * Construcora de la clase
     */
    public OntologyAnalyzer(String owlFilePath, String repositoryFilePath) {
        this.loadOntology(owlFilePath, repositoryFilePath);
    }

    /**
     * Carga la ontologia en memoria, desde archivo. En este caso los
     * archivos son: file:resources/ontology.repository y
     * file:resources/ontology.owl
     *
     * @param owlFilePath DOCUMENT ME!
     * @param repositoryFilePath DOCUMENT ME!
     */
    public void loadOntology(String owlFilePath, String repositoryFilePath) {
        this.ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM,
                null);
        this.ontModel.getDocumentManager()
                     .addAltEntry(repositoryFilePath, owlFilePath);
        this.ontModel.read(repositoryFilePath);
    }

    /**
     * Dado un valor literal, este metodo devuelve el recurso que se
     * relaciona con ese valor. La idea es que  dada una palabra, se infiera a
     * que parte de un escenario concreto se refiere. Por la tanto, el recurso
     * que se devuelve es una instancia de concreteSource, concreteStimulus,
     * concreteEnviroment, etc. Puede pasar que haya dos instancias que se
     * relacionen con esa palabra. Por ejemplo, hay un Stimulus que tiene
     * nombre "User" y hay un Source que tiene el nombre "User". En esta caso,
     * se toma la instancia con la que se relacionen mas escenarios concretos.
     * Es decir, si 10 escenarios tienen como fuente a "User" y solo 2 como
     * estimulo a "user", se devuelve la instancia q es de tipo Source.
     *
     * @param word palabra
     *
     * @return instancias que se relaciona con el parametro, null si no hay
     *         ninguna
     */

    //Se usa
    public Resource getWordMeaning(String word) {
        Resource actualResource = null;
        int actualCount = -1;

        Property auxProperty = null;
        Resource instance = null;
        Resource instanceType = null;
        int auxCount;
        ResIterator instances = this.ontModel.listSubjectsWithProperty(this.ontModel.getProperty(
                    Uris.concreteScenarioPartDescription), word);

        /*
         * en instances estan todas las instancias que se relacionan con la palabra
         */
        while (instances.hasNext()) {
            instance = instances.next();
            instanceType = this.getType(instance);
            auxProperty = this.ontModel.getProperty(Uris.getScenarioHasUriFromConcretePartUri(
                        instanceType.getURI()));
            auxCount = this.ontModel.listSubjectsWithProperty(auxProperty,
                    instance).toList().size();

            if (auxCount > actualCount) {
                actualCount = auxCount;
                actualResource = instance;
            }
        }

        return actualResource;
    }

    /**
     * Todas las instancias de la ontologia tienen la propiedad
     * http://www.w3.org/1999/02/22-rdf-syntax-ns#type  (en adelante llamada
     * simplemente "type"). Esta las relaciona con la clase de su tipo.
     * Ejmplo: instancia1 se relaciona con ConcreteScenarioSource mediante la
     * propiedad type.Esto indica que instancia1 es  de tipo
     * ConcreteScenerioSource.
     *
     * @param resource algun recurso de una instancia dentro de la ontologia
     *
     * @return recurso que relaciona con el parametro medianta la propiedad
     *         type (http://www.w3.org/1999/02/22-rdf-syntax-ns#type)
     */

    //se usa
    public Resource getType(Resource resource) {
        Resource typeResource = null;
        Statement statement = resource.getProperty(this.ontModel.getProperty(
                    Uris.type));

        if (statement != null) {
            typeResource = this.ontModel.getResource(statement.getObject()
                                                              .asNode().getURI());
        }

        return typeResource;
    }

    /**
     * Esta funcion primera consulta todas las instancias que existan
     * en la ontologia que sean de tipo qualityAttribute
     * (http://www.owl-ontologies.com/unnamed.owl#QualityAttribute). Luego
     * recupera el nombre de cada una de ellas, q es el valor literal
     * relacionado mediante la propiedad qualityAttributeName
     * (http://www.owl-ontologies.com/unnamed.owl#qualityAttributeName). Con
     * cada nombre de los atributos de calidad, se crea una instancia y se
     * alamanca en el map como clave, poniendo asociando cada clave con el
     * valor cero
     *
     * @return DOCUMENT ME!
     */
    public Map<QualityAttributeInterface, Double> loadQualityAttributes() {
        AbstractEntityFactory factory = new EntityFactory();

        Map<QualityAttributeInterface, Double> map = new HashMap<QualityAttributeInterface, Double>();
        Property qualityAttributeNameProperty = this.ontModel.getProperty(Uris.qualityAttributeName);
        ResIterator qualityAttributeIterator = this.ontModel.listResourcesWithProperty(this.ontModel.getProperty(
                    Uris.type),
                this.ontModel.getResource(Uris.qualityAttributeUri));

        Resource qualityAttributeResource = null;
        String qualityAttributeName = null;

        while (qualityAttributeIterator.hasNext()) {
            qualityAttributeResource = qualityAttributeIterator.next();
            qualityAttributeName = qualityAttributeResource.listProperties(qualityAttributeNameProperty)
                                                           .next().getString();
            map.put(factory.creatQualityAttribute(qualityAttributeName),
                Double.valueOf(0.0));
        }

        return map;
    }

    /**
     * Cada escenario se relaciona con un atributo de calidad. Este
     * metodo devuelve el nombre de ese atributo, basandose en el escenario
     * pasado como parametro. Se utliza la propiedad
     * http://www.owl-ontologies.com/unnamed.owl#isSpecificof
     *
     * @param scenario escenario concreto del que se quiere averiguar el
     *        atributo de calidad
     *
     * @return nombre del atributo de calidad
     */
    public String getQualityAttributeNameFromConcreteScenario(Resource scenario) {
        Property iSpecificOfProperty = this.ontModel.getProperty(Uris.isSpecificof);
        Property qualityAttributeNameProperty = this.ontModel.getProperty(Uris.qualityAttributeName);

        String qualityAttributeInscanceUri = scenario.listProperties(iSpecificOfProperty)
                                                     .nextStatement().getObject()
                                                     .asNode().getURI();
        Resource qualityAttributeInstanceResource = this.ontModel.getResource(qualityAttributeInscanceUri);

        String qualityAttributeName = qualityAttributeInstanceResource.listProperties(qualityAttributeNameProperty)
                                                                      .next()
                                                                      .getString();

        return qualityAttributeName;
    }

    /**
     * Funcion principal de la clase. Dada una palabra, realiza los
     * siguientes pasos. 1) Dada la plabra, chequea en la ontologia con que
     * parte de un escenario concreto se corresponde (Fuente de
     * estimulo,Estimulo, Ambiente, Artefacto, Respuesta o tiempo de
     * respuesta).En caso de que se corresponda con mas de uno, toma en cuenta
     * la que se relacione con la mayor cantidad de instancias de escenarios.
     * Todo esto lo realiza el método getWordMeaning.
     *
     * @param word palabra a analizar
     *
     * @return map que relaciona los atributos de calidad con la cantidad de
     *         escenarios que se relacionan con esa palabra. Null si la
     *         palabra no se identifica c como perteneciente a ninguna parte
     *         de un escenario concreto
     */

    //TODO terminar la explicacion
    @Override
    public Map<QualityAttributeInterface, Double> getWordPertenence(String word) {
        logger.info("Palabra entrante: " + word);

        AbstractEntityFactory entityFactory = new EntityFactory();
        Map<QualityAttributeInterface, Double> map = this.loadQualityAttributes();

        Resource scenarioPart = this.getWordMeaning(word); //Devuelve un numero de instancia, ejemplo intance_0

        //Si scenarioPart es igual a null, significa q esa palabra no se encuentra presente en la ontologia
        if (scenarioPart != null) {
            Resource scenarioPartType = this.getType(scenarioPart); //Devueve el tipo de esa instancia, ejemplo, http://www.owl-ontologies.com/unnamed.owl#ConcreteSource
            logger.info("La palabra " + word +
                " se reconoce como perteneciente a una una instancia de " +
                scenarioPartType.getURI());

            //A partir de ese concreteSource, se necesita saber el  Uri de la propiedad con la que se relaciona con las instancias
            //de escenarios. Siguiendo el ejemplo, el metodo nos devolveria el uri http://www.owl-ontologies.com/unnamed.owl#concreteScenarioHasSource
            String concreteScenarioHasXUri = Uris.getScenarioHasUriFromConcretePartUri(scenarioPartType.getURI());
            Property concreteScenarioHasXProperty = this.ontModel.getProperty(concreteScenarioHasXUri);

            //Con los datos que tenemos averiguamos todos los escenarios que se relacionan con scenario part, mediante 
            //la propiedad concreteScenarioHasXProperty
            ResIterator scenariosIterator = this.ontModel.listSubjectsWithProperty(concreteScenarioHasXProperty,
                    scenarioPart);
            Resource scenario = null;
            QualityAttributeInterface qualityAttribute = null;

            int cantScenarios = 0;
            while (scenariosIterator.hasNext()) {
            	cantScenarios++;
            	scenario = scenariosIterator.next();
                qualityAttribute = entityFactory.creatQualityAttribute(this.getQualityAttributeNameFromConcreteScenario(
                            scenario));
                map.put(qualityAttribute,
                    map.get(qualityAttribute) + Double.valueOf(1.0));
            }

            /*Si cantScenarios es cero, quiere decir que hay una instancia de alguna parte de un scenario que se relaciona
            con la palabra, pero esa parte no se relaciona con ningun escenario. Es decir, existe una instancia de
            concreteSource, pero esta no esta relacionada con ninguna instancia de escenario.
            En este caso se deberia volver null, pues la palabra no deberia ser contabilizada como valida. Igualmente este caso no deberia
            ocurrir parcticamente nunca.
             */
            if (cantScenarios==0){
            	return null;
            }
        } else {
            logger.info("La palabra " + word +
                " no existe dentro del a ontologia como parte de un escenario");
            return null; 
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

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public OntModel getOntModel() {
        return ontModel;
    }

    /**
     * DOCUMENT ME!
     *
     * @param ontModel DOCUMENT ME!
     */
    public void setOntModel(OntModel ontModel) {
        this.ontModel = ontModel;
    }

    public static void main(String[] args) {
  	
    }
}
