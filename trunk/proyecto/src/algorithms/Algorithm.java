package algorithms;

import entities.EarlyAspect;
import entities.QualityAttributeThemeInterface;
import entities.RichedWord;

import java.util.List;


/**
 * Interface Algorithm. 
 *
 */
public interface Algorithm {
    /**
     * Dada una lista de palabras y un caso de uso, el algoritmo debe
     * devolver un Quality Attribute Theme.
     *
     * @return Quality Attribute Theme
     */
    QualityAttributeThemeInterface getQualityAttributeTheme(
        List<RichedWord> words, EarlyAspect earlyAspect);
}
