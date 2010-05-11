package filters;

import java.util.List;
import org.apache.log4j.Logger;
import entities.RichedWord;

/**
 * 
 * Clase abstracta que representa un Filtro.
 *
 * @author Sebastian Villanueva
 */

public abstract class Filter {

    protected static final Logger logger = Logger.getLogger(Filter.class);
	
    public abstract List<RichedWord> filter(List<RichedWord> list);
	
}
