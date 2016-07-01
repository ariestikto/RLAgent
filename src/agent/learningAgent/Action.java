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
	private int addedAmountLevel; // (1: 0, 2: 0.25, 3: 0.5, 4: 0.75, 5: 1)
	private int budgetLevel; // (1: low, 2: medium, 3: high, 4: very high)
	
	public Action() {
		this.addedAmountLevel = 0;
		this.budgetLevel = 0;
	}

	public void setAction(int addedAmountLevel, int budgetLevel) {
		this.addedAmountLevel = addedAmountLevel;
		this.budgetLevel = budgetLevel;
	}
	
	public int getAddedAmountLevel() {
		return addedAmountLevel;
	}
	
	public String getAmountPercentage() {
		String percentage = "0";
		switch (addedAmountLevel) {
		case 2:
			percentage = "1/4";
			break;
		case 3:
			percentage = "1/2";
			break;
		case 4:
			percentage = "3/4";
			break;
		case 5:
			percentage = "Full";
			break;
		}
		return percentage;
	}
	
	public int getBudgetLevel() {
		return budgetLevel;
	}
	
	public String getBudget() {
		String level = "";
		switch (budgetLevel) {
		case 1:
			level = "Low (5-10 p/kWh)";
			break;
		case 2:
			level = "Medium (10-15 p/kWh)";
			break;
		case 3:
			level = "High (15-20 p/kWh)";
			break;
		case 4:
			level = "Very High (20-25 p/kWh)";
			break;
		default:
			break;
		}
		return level;
	}
	public boolean isEmpty() {
		return ((addedAmountLevel == 0) && (budgetLevel == 0));
	}
	public boolean isEqual(Action a) {
		return ((addedAmountLevel == a.getAddedAmountLevel()) && (budgetLevel == a.getBudgetLevel()));
	}
}
