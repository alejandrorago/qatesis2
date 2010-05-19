package input;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import entities.AbstractEntityFactory;
import entities.EarlyAspectInterface;
import entities.EntityFactory;
import entities.QualityAttributeThemeInterface;
import entities.Relation;
import entities.UseCase;
import entities.UseCaseInterface;

/**
 * The Class Project.
 * 
 * @author Sebastian Villanueva
 */
public class Project {

	private String name;
	private List<UseCaseInterface> useCases;
	private List<EarlyAspectInterface> earlyAspects;
	private List<Relation> relations;

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the use cases.
	 * 
	 * @return the list of usecases
	 */
	public List<UseCaseInterface> getUseCases() {
		return useCases;
	}

	/**
	 * Sets the use cases.
	 * 
	 * @param useCases
	 *            the new use cases
	 */
	public void setUseCases(List<UseCaseInterface> useCases) {
		this.useCases = useCases;
	}

	/**
	 * Gets the actors.
	 * 
	 * @return the actors
	 */
	public List<String> getActors() {
		List<String> actors = new ArrayList<String>();
		Iterator<UseCaseInterface> i = useCases.iterator();
		while (i.hasNext()) {
			UseCase u = (UseCase) i.next();
			String a = u.getActor();
			if (actors.contains(a) == false)
				actors.add(u.getActor());
		}
		return actors;
	}

	/**
	 * @param earlyAspects
	 *            the earlyAspects to set
	 */
	public void setEarlyAspects(List<EarlyAspectInterface> earlyAspects) {
		this.earlyAspects = earlyAspects;
	}

	/**
	 * @return the earlyAspects
	 */
	public List<EarlyAspectInterface> getEarlyAspects() {
		return earlyAspects;
	}

	public void setRelations(List<Relation> relations) {
		this.relations = relations;
	}

	public List<Relation> getRelations() {
		return relations;
	}

	public List<QualityAttributeThemeInterface> getQATs() {

		List<QualityAttributeThemeInterface> qats = new ArrayList<QualityAttributeThemeInterface>();
		AbstractEntityFactory factory = new EntityFactory();

		for (EarlyAspectInterface ea : this.getEarlyAspects()) {
			QualityAttributeThemeInterface qa = factory
					.creatQualityAttributeTheme(this.getEarlyAspectUseCases(ea.getId()), ea, null);
			qats.add(qa);
		}
		return qats;
	}

	private List<UseCaseInterface> getEarlyAspectUseCases(int id) {

		List<UseCaseInterface> result = new ArrayList<UseCaseInterface>();
		Relation relation = null;

		// recorro todo los elementos Relation cargados del archivo
		for (Iterator<Relation> i = relations.iterator(); i.hasNext();) {
			relation = (Relation) i.next();
			// busco el objeto Relation par el id pasado como parametro
			if (relation.getEarlyAspectId() == id)
				break;
		}

		if (relation != null) {
			// obtengo todos los ids de los caso de uso para el early aspect en cuestion cargados a partir del archivo
			for (Iterator<Integer> i = relation.getUseCaseIds().iterator(); i.hasNext();) {
				int ucId = (Integer) i.next();
				// por cada id recupero el objeto UseCase correspondiente y lo agrego a la lista de resultados
				for (Iterator<UseCaseInterface> it = this.getUseCases().iterator(); it.hasNext();) {
					UseCaseInterface uc = (UseCaseInterface) it.next();
					if (ucId == uc.getId()) {
						result.add(uc);
						break;
					}
				}
			}
		}
		return result;
	}
}
