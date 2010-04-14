package algorithms;

import entities.QualityAttributeThemeInterface;
import entities.RichedWord;

import java.util.List;


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$
  */
public interface Algorithm {
    /**
     * DOCUMENT ME!
     *
     * @param words DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    QualityAttributeThemeInterface getQualityAttributeTheme(
        List<RichedWord> words);
}
