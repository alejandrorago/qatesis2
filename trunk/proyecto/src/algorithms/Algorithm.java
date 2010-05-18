package algorithms;

import entities.QualityAttributeInterface;
import entities.RichedWord;

import java.util.List;
import java.util.Map;


/**
 * Interface Algorithm. 
 *
 */
public interface Algorithm {
    /**
     * Dada una lista de palabras extraidas de los casos de uso y otra extraidas de un early aspect el algoritmo
     * devuevle un map con los atributos de calidad como clave y un numero entr 0 y 1 que indica el grado
     * de pertenencia de los conjuntos de palabras con cada atributo.
     * La suma de los valores es igual a 1
     * 
     * @param useCaseWordsList lista de palabras enriquecidas extraidas de los casos de uso
     * @param earlyAspectWordsList lista de palabras enriquecidas extraidas del early aspect
     *
     * @return DOCUMENT ME!
     */
    Map<QualityAttributeInterface, Double> getQualityAttributePertenence(
        List<RichedWord> useCaseWordsList, List<RichedWord> earlyAspectWordsList);
    
    public Double getUseCaseFactor(); 
	public void setUseCaseFactor(Double useCaseFactor);

}
