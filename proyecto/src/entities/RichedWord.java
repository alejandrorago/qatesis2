package entities;

import java.util.HashMap;
import java.util.Map;


/**
 * Esta clase representa una palabra, junto con atributos que esta posee.
 * De esta manera, posee dos variables de instancia.  Una variable de tipo
 * String que corresponde a la palabra en cuestion, y un HashMap, en donde la
 * clave es el nombre de atributo de esa palabra, y el valor es el valor de
 * ese atributo.  Esta clase se utiliza principalmente despues de extraer las
 * palabras de un caso de Uso. Cada  una de estas palabras podria tener
 * ciertos atributos como pondereacion, ocurrencia (cantidad  de veces que
 * aparece en el caso de uso, etc). Asi, en este clase se pueden almacenar
 * esos atributos,  relacionandolos con la palabra a la que pertencen.
 *
 * @author fbertoni
 * @version $Revision$
 */
public class RichedWord implements RichedWordInterface {
    /** Palabra */
    private String word;

    /** HashMap que contiene la lista de atributos de esa palabra */
    private Map<String, Object> attributes;

/**
     * Crea un nuevo objeto de tipo RichedWord
     *
     * @param word palabra
     */
    public RichedWord(String word) {
        this.word = word;
        this.attributes = new HashMap<String, Object>();
    }

    /**
     * Devuelve la palabra
     *
     * @return palabra
     */
    public String getWord() {
        return word;
    }

    /**
     * Setea la palabra
     *
     * @param word palabra a setear
     */
    public void setWord(String word) {
        this.word = word;
    }

    /**
     * Devuelve el listado de atributos en formato hashmap
     *
     * @return map con los atributos de la palabra
     */
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }

    /**
     * Setea los atributos de la palabra, eliminando los existentes.
     *
     * @param attributes map con los atributos de la palabra
     */
    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    /**
     * Devuelve el valor del atributo pasado como parametro
     *
     * @param key nombre del atributo
     *
     * @return valor del atributo
     */
    public Object getAttribute(String key) {
        return this.attributes.get(key);
    }

    /**
     * Agrega un atributo al map, si este existe lo reemplzaza
     *
     * @param key nombre del atributo
     * @param value valor del atrobuto
     */
    public void setAttribute(String key, String value) {
        this.attributes.put(key, value);
    }

    /**
     * Remueve un atributo de la lista de atributos
     *
     * @param key nombre del atributo a remover
     */
    public void removeAttribute(String key) {
        this.attributes.remove(key);
    }
}
