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
    /**
     * Cantidad de ocurrencias de la palabra
     */
    public static final String OCURRENCES = "OCURRENCES";

    /**
     * Peso de la palabra
     */
    public static final String WEIGHT = "WEIGHT";

    /**
     * Seccion del caso de uso en que se encuentra la palabra
     */
    public static final String SECTION = "SECTION";

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
     * @param value valor del atributo
     */
    public void setAttribute(String key, Object value) {
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

    /**
     * DOCUMENT ME!
     *
     * @param o DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    //TODO meterlo en comparator o algo similar para comparar bien
    public boolean equals(Object o) {
        try {
            RichedWord rw = (RichedWord) o;

            return ((rw.getWord().equalsIgnoreCase(this.word)) &&
            (this.getAttribute(RichedWord.SECTION)
                 .equals(rw.getAttribute(RichedWord.SECTION))));
        } catch (NullPointerException e) {
            return false;
        }
    }
    
    public static void main(String[] args){
    	
        RichedWord r1 = new RichedWord("r2");
        r1.setAttribute("SECTION", "seccion1");
        RichedWord r2 = new RichedWord("r1");
        r2.setAttribute("SECTION", "seccion1");
        
        if(r1.equals(r2))
        	System.out.println("Son iguales");
        else
        	System.out.println("Distintas");
    }
}
