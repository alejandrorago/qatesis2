package ontology;

import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

import filters.FilterLowerCase;
import filters.FilterManager;
import filters.FilterStemming;
import filters.FilterStopWords;

import ontology.OntologyAnalyzer;

import org.apache.log4j.Logger;

import wordtokenizer.StemmerIngles;
import wordtokenizer.StopWordsAnalizer;


/**
 * Clase Ontology Manager  Esta clase extiende de OntologyAnalyzer.
 * Principalmente agrega la funcion de formatear la ontologia, en la
 * constructora de la clase, para  que todos los conceptos sean palabras
 * atomicas.
 */
public class OntologyManager extends OntologyAnalyzer {

    /**
     * Logger
     */
    private static final Logger logger = Logger.getLogger(OntologyManager.class);

    /**
     * Numero de instancia. Se va aumentando cada vez que se crea una
     * instancia nueva en la ontologi
     */
    Integer instanceNumber;
    
    FilterManager fm;

    /**
     * Creates a new OntologyManager object.
     *
     * @param owlFilePath archivo .owl de la ontologia
     * @param repositoryFilePath archivo .repository de la ontologia
     */
    public OntologyManager(String owlFilePath, String repositoryFilePath) {
        super(owlFilePath, repositoryFilePath);
        this.instanceNumber = Integer.valueOf(0);

        
        //TODO pasar el fm como parametro o algo asi, mas adelante
        fm = new FilterManager();
        fm.addFilter(new FilterLowerCase());
        fm.addFilter(new FilterStopWords("resources/stopWordsList.txt"));
        fm.addFilter(new FilterStemming());
        
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
     * Una instancia de un concepto puede estar compuesta por varias palabras. Esta funcion
     * separa a cada una de esas palabras, crea una instancia para cada una y las relaciona
     * con el o los conceptos con que la instancia original estaba relacionada.
     * 
     * Supongamos que una instancia de Stimulus, Instancia-98, esta formada por "palabra1 palabra2". Esta funcion
     * crea una instancia nueva para palabra1 y otra para palabra2. Ademas rrelaciona estas instancias
     * con todas las instancias que estaba relacionada Instancia-98.
     *
     * @param instance instancia a ser formateada.
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

            	word = fm.runFiltersWord(words[i]);

                if (word!= null) {

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
        }

        eliminateStatements(instance);
    }
    /**
     * Elimina todas las sentencias de la ontologia en la que el recurso pasado como parametro aprece en el subject o en
     * el object
     * @param instance instancia por la que se realiza la consulta y borrado
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

    /**
     * Todas las instancias de escenarios concretos que apunten a oldInstance 
     * deberian apuntar ahora a newInstance
     *
     * @param oldInstance instancia anterior
     * @param newInstance instancia nueva
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


    /**
     * Devuelve la instancia de tipo instanceType que se relaciona con literalValue, si es que esa instancia está previamente formateada.
     * Caso contrario devuelve null
     *
     * @param instanceType tipo de la instancia
     * @param literalValue valor con que se relaciona la instancia
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
     * Crea una nueva instancia en la ontologia, del tipo pasado como parametro.
     * 
     * @param instanceType tipo de la instancia a crear
     *
     * @return instancia creada
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
     * Crea una sentencia cuyo predicado es de el uriType.
     * 
     * @param instance subject de la sentencia a crear
     * @param literalValue object de la sentencia a crear
     */
    public void addLiteralPropertyToResource(Resource instance,
        String literalValue) {
        Statement typeStatement = this.ontModel.createStatement(instance,
                this.ontModel.getProperty(Uris.concreteScenarioPartDescription),
                literalValue);
        this.ontModel.add(typeStatement);
    }
}
