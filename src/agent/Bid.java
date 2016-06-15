/**
 * 
 */
package agent;

import marketFramework.Market;
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
			this.amount = randomBid(currentBid, dailyNeeds - currentElectricity, lastBid, unitBudget);
			break;
		case 2:
			this.amount = SpendBudgetBid(currentBid, dailyNeeds - currentElectricity, unitBudget);
			break;
		case 3:
			this.amount = MaxBid(currentBid, dailyNeeds - currentElectricity, unitBudget);
			break;
		case 4:
			this.amount = AllOrNothingBid(currentBid, dailyNeeds - currentElectricity, unitBudget);
			break;
		}
	}
	public static double randomBid(double currentBid, double dailyNeeds, double lastBid, double unitBudget) {
//		strategy 1
		double bid = 0;
		if (currentBid == Market.START_PRICE) {
			bid = dailyNeeds;
		} else {
			do {
				bid = Math.random()*lastBid;
			} while (bid*currentBid > unitBudget*dailyNeeds);
			if (bid < 0) {
				bid = 0;
			}
		}
		
		return bid;
	}
	public static double SpendBudgetBid(double currentBid, double dailyNeeds, double unitBudget) {
//		strategy 2
		double bid = 0;
		double deficit = dailyNeeds;
		double budget = unitBudget*deficit;

		if (deficit > 0) {
			if (deficit < budget/currentBid) {
				bid = deficit;
			} else {
				bid = budget/currentBid;
			}
		}
		return bid;
	}
	public static double MaxBid(double currentBid, double dailyNeeds, double unitBudget) {
//		strategy 3
		double bid = 0;
		bid = dailyNeeds;
		while (bid*currentBid > unitBudget*dailyNeeds) {
			bid *= 0.9;
		}
		return bid;
	}
	public static double AllOrNothingBid(double currentBid, double dailyNeeds, double unitBudget) {
//		strategy 4
		double bid = 0;
		if (currentBid > unitBudget) {
			bid = 0;
		} else {
			bid = dailyNeeds;
		}
		return bid;
	}
	
}
