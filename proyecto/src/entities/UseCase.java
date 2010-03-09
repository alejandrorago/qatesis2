package entities;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sebastian Villanueva
 *
 */

@SuppressWarnings("unchecked")

public class UseCase implements UseCaseInterface {

	private int id;
	private String name;
	private String description;
	private String actor;
	private String priority;
	private String trigger;
	private List alternativeFlow;
	private List basicFlow;
	private List specialRequirement;
	private List preconditions;
	private List postconditions;
	
	public UseCase() {
		this.basicFlow = new ArrayList();
		this.alternativeFlow = new ArrayList();
		this.specialRequirement = new ArrayList();
		this.preconditions = new ArrayList();
		this.postconditions = new ArrayList();
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}
		/**
		 * @param name the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}
		/**
		 * @return the description
		 */
		public String getDescription() {
			return description;
		}
		/**
		 * @param description the description to set
		 */
		public void setDescription(String description) {
			this.description = description;
		}
		/**
		 * @return the actor
		 */
		public String getActor() {
			return actor;
		}
		/**
		 * @param actor the actor to set
		 */
		public void setActor(String actor) {
			this.actor = actor;
		}
		/**
		 * @return the priority
		 */
		public String getPriority() {
			return priority;
		}
		/**
		 * @param priority the priority to set
		 */
		public void setPriority(String priority) {
			this.priority = priority;
		}
		/**
		 * @return the trigger
		 */
		public String getTrigger() {
			return trigger;
		}
		/**
		 * @param trigger the trigger to set
		 */
		public void setTrigger(String trigger) {
			this.trigger = trigger;
		}
		/**
		 * @return the basicFlow
		 */
		public List getBasicFlow() {
			return basicFlow;
		}
		/**
		 * @param basicFlow the basicFlow to set
		 */
		public void setBasicFlow(String basicFlow) {
			this.basicFlow.add(basicFlow);
		}
		/**
		 * @return the alternativeFlow
		 */
		public List getAlternativeFlow() {
			return alternativeFlow;
		}
		/**
		 * @param alternativeFlow the alternativeFlow to set
		 */
		public void setAlternativeFlow(String alternativeFlow) {
			this.alternativeFlow.add(alternativeFlow);
		}
		/**
		 * @return the specialRequirement
		 */
		public List getSpecialRequirement() {
			return specialRequirement;
		}
		/**
		 * @param specialRequirement the specialRequirement to set
		 */
		public void setSpecialRequirement(String especialRequirement) {
			this.specialRequirement.add(especialRequirement);
		}
		/**
		 * @return the preconditions
		 */
		public List getPreconditions() {
			return preconditions;
		}
		/**
		 * @param preconditions the preconditions to set
		 */
		public void setPreconditions(String preconditions) {
			this.preconditions.add(preconditions);
		}
		/**
		 * @return the postconditions
		 */
		public List getPostconditions() {
			return postconditions;
		}
		/**
		 * @param postconditions the postconditions to set
		 */
		public void setPostconditions(String postconditions) {
			this.postconditions.add(postconditions);
		}
}
