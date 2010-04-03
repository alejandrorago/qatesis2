package ontology;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

import ontology.OntologyAnalyzer;


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$
 */
public class OntologyManager extends OntologyAnalyzer {
    /** DOCUMENT ME! */
    Integer instanceNumber;
    private static final Logger logger = Logger.getLogger(OntologyManager.class);


/**
     * Creates a new OntologyFormatter object.
     *
     * @param ontmodel DOCUMENT ME!
     */
    public OntologyManager(String owlFilePath, String repositoryFilePath) {
        super(owlFilePath, repositoryFilePath);
        this.instanceNumber = Integer.valueOf(0);
        this.formatModel();
        logger.info("Inicio Ontologia");
        this.listarOntologia();
        logger.info("Fin Ontologia");
    }

    /**
     * Este metodo tomas las instancias de concreteSource,
     * concreteStimulus, etc. Para cada una de ellas recuepera el valor de la
     * descripcion y genera una neuva instancia en la  ontologia para cada una
     * de esas palabras. Luego redirecciona los escenarios q apuntaban a la
     * parte anterior con cada una de estas instancias creadas.
     */
    public void formatModel() {
        //Se recuperan todos los recursos que sean de tipo concreteSource, concreteStimulus, etc
        ResIterator partInstancesIterator = this.ontModel.listSubjectsWithProperty(this.ontModel.getProperty(
                    Uris.concreteScenarioPartDescription));
        Resource instance = null;

        while (partInstancesIterator.hasNext()) {
            instance = partInstancesIterator.next();
            //cada instancia se debe formatear
            this.formatearInstancia(instance);
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param instance DOCUMENT ME!
     */
    public void formatearInstancia(Resource instance) {
        StmtIterator stmIterator = instance.listProperties(this.ontModel.getProperty(
                    Uris.concreteScenarioPartDescription));
        Statement statement = null;
        String[] words = null;
        String word = null;
        Resource formatedInstance = null;

        while (stmIterator.hasNext()) {
            //Deberia haber un solo statement, pero se itera por las dudas
            statement = stmIterator.next();

            //words representa la descripcion original, potencialmente formada por mas de una palabra
            words = statement.getString().split(" ");

            for (int i = 0; i < words.length; i++) {
                word = words[i];
                formatedInstance = this.existeInstanciaFormateada(super.getType(
                            instance), word);

                if (formatedInstance == null) {
                    formatedInstance = this.createInstance(super.getType(
                                instance));
                    this.addLiteralPropertyToResource(formatedInstance, word);
                }

                redirectScenariosToNewPart(instance, formatedInstance);
            }
        }

        eliminateStatements(instance);
    }

    /*
     * Elimina todas las sentencias de la ontologia en la que el recurso pasado como parametro aprece en el subject o en
     * el object
     */
    /**
     * DOCUMENT ME!
     *
     * @param instance DOCUMENT ME!
     */
    public void eliminateStatements(Resource instance) {
        Property property = null;
        Resource resource = null;
        StmtIterator stmIterator = this.ontModel.listStatements(resource,
                property, instance);
        this.ontModel.remove(stmIterator);
        stmIterator = this.ontModel.listStatements(instance, property, resource);
        this.ontModel.remove(stmIterator);
    }

    /*
     * Todas las instancias de escenarios concretos que apunten a oldInstance deberian apuntar ahora a newInstance
     */
    /**
     * DOCUMENT ME!
     *
     * @param oldInstance DOCUMENT ME!
     * @param newInstance DOCUMENT ME!
     */
    public void redirectScenariosToNewPart(Resource oldInstance,
        Resource newInstance) {
        Resource oldInstaceType = super.getType(oldInstance);
        Property scenariosHaspartProperty = this.ontModel.getProperty(Uris.getScenarioHasUriFromConcretePartUri(
                    oldInstaceType.getURI()));
        Resource scenario = null;
        Statement statement = null;

        ResIterator scenariosIterator = this.ontModel.listSubjectsWithProperty(scenariosHaspartProperty,
                oldInstance);

        while (scenariosIterator.hasNext()) {
            scenario = scenariosIterator.next();
            statement = this.ontModel.createStatement(scenario,
                    scenariosHaspartProperty, newInstance);
            this.ontModel.add(statement);
        }
    }

    /*
     * Devuelve la instancia de tipo instanceType que se relaciona con literalValue, si es que esa instancia está previamente formateada.
     * Caso contrario devuelve null
     */
    /**
     * DOCUMENT ME!
     *
     * @param instanceType DOCUMENT ME!
     * @param literalValue DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public Resource existeInstanciaFormateada(Resource instanceType,
        String literalValue) {
        Resource auxInstance = null;
        String auxInstanceUri = null;
        String auxInstanceLiteral = null;
        ResIterator instanceIterator = this.ontModel.listSubjectsWithProperty(this.ontModel.getProperty(
                    Uris.type), instanceType);

        while (instanceIterator.hasNext()) {
            auxInstance = instanceIterator.next();
            /*En este momento hay que chequear dos cosas:
                 * 1)Que la instancia tenga el valor de literalValue mediante la propiedad de uri concreteScenarioPartDescription
                 * 2)Que la instncia ya este previamente formateada
                 *
                 */

            //Chequeamos que la instncia tenga el valor literal de literalValue
            auxInstanceLiteral = auxInstance.listProperties(this.ontModel.getProperty(
                        Uris.concreteScenarioPartDescription)).nextStatement()
                                            .getString();

            if (auxInstanceLiteral.equals(literalValue)) {
                //Chequeamos que la instancia este formateada
                auxInstanceUri = auxInstance.getURI();

                if (auxInstanceUri.contains(Uris.formatedInstanceUri)) {
                    return auxInstance;
                }
            }
        }

        return null;
    }

    /**
     * DOCUMENT ME!
     *
     * @param instanceType DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public Resource createInstance(Resource instanceType) {
        this.instanceNumber = this.instanceNumber + 1;

        Resource newInstance = this.ontModel.createResource(Uris.formatedInstanceUri +
                this.instanceNumber);
        Property typeProperty = this.ontModel.getProperty(Uris.type);
        Statement typeStatement = this.ontModel.createStatement(newInstance,
                typeProperty, instanceType);
        this.ontModel.add(typeStatement);

        return newInstance;
    }

    /**
     * DOCUMENT ME!
     *
     * @param instance DOCUMENT ME!
     * @param literalValue DOCUMENT ME!
     */
    public void addLiteralPropertyToResource(Resource instance,
        String literalValue) {
        Statement typeStatement = this.ontModel.createStatement(instance,
                this.ontModel.getProperty(Uris.concreteScenarioPartDescription),
                literalValue);
        this.ontModel.add(typeStatement);
    }
}
