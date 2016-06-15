/**
 * 
 */
package userSimulation;

import agent.BidStrategy;
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
		
	public void calculateBid(int userStrategy, double currentBid, double lastBid, double currentElectricity, double dailyNeeds, double unitBudget) {
		switch (userStrategy) {
		case 1:
			this.amount = BidStrategy.randomBid(currentBid, dailyNeeds, lastBid);
			break;
		case 2:
			this.amount = BidStrategy.SpendBudgetBid(currentBid, dailyNeeds, unitBudget);
			break;
		case 3:
			this.amount = BidStrategy.MaxBid(dailyNeeds);
			break;
		case 4:
			this.amount = BidStrategy.AllOrNothingBid(currentBid, dailyNeeds, unitBudget);
			break;
		}
	}
}
