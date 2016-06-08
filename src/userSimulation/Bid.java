/**
 * 
 */
package userSimulation;

/**
 * @author pa1g15
 *
 */
public class Bid {
	private double amount; 
	
	public Bid() {
		this.amount = 0;
	}
	public Bid(double amount) {
		this.amount = amount;
	}
	
	public double getAmount() {
		return amount;
	}
		
	public void calculateBid(double currentBid, double currentElectricity, double dailyDistance, double budget, Car carType) {
		double deficit = (dailyDistance*carType.getConsumption()) - currentElectricity;
		double dailyBudget = budget*deficit;
		//	this is the bidding algorithm
		

		//		-----------------------------
		if (deficit > 0) {
			if (deficit < dailyBudget/currentBid) {
				this.amount = deficit;
			} else {
				this.amount = dailyBudget/currentBid;
			}
		}
	}
}
