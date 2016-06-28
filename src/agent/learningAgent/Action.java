/**
 * 
 */
package agent.learningAgent;

/**
 * @author pa1g15
 *
 */
public class Action {

	/**
	 * 
	 */
	private double bidAmount;
	private double budget;
	
	public Action() {
		this.bidAmount = 0;
		this.budget = 0;
	}

	public double getBidAmount() {
		return bidAmount;
	}

	public void setBidAmount(double bidAmount) {
		this.bidAmount = bidAmount;
	}

	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) {
		this.budget = Math.round(budget);
	}

	public boolean isEqual(Action a) {
		return ((bidAmount == a.getBidAmount()) && (budget == a.getBudget()));
	}
}
