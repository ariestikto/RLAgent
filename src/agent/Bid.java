/**
 * 
 */
package agent;

import marketFramework.Market;
import marketFramework.Time;
import userSimulation.User;
import agent.learningAgent.Action;
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
		
	public void calculateBid(double currentBid, double lastBid, User user, Time t) {
		switch (user.getStrategy()) {
		case 1:
			this.amount = randomBid(currentBid, user.getDailyNeeds() - user.getCurrentElectricity(), lastBid, user.getBudget());
			break;
		case 2:
			this.amount = TruthfulBid(currentBid, user.getDailyNeeds() - user.getCurrentElectricity(), user.getBudget());
			break;
		case 3:
			this.amount = MaxBid(currentBid, user.getDailyNeeds() - user.getCurrentElectricity(), user.getBudget());
			break;
		case 4:
			this.amount = AllOrNothingBid(currentBid, user.getDailyNeeds() - user.getCurrentElectricity(), user.getBudget());
			break;
		case 5:
			this.amount = RLBid(currentBid, user);
			break;
		}
		if (amount < user.clinchedElectricity()) {
			this.amount = user.clinchedElectricity();
		}
	}
	public static double randomBid(double currentBid, double dailyNeeds, double lastBid, double budget) {
//		strategy 1
		double bid = 0;
		if (currentBid == Market.START_PRICE) {
			bid = dailyNeeds;
		} else {
			do {
				bid = Math.random()*lastBid;
			} while (bid*currentBid > budget);
			if (bid < 0) {
				bid = 0;
			}
		}
		
		return bid;
	}
	public static double TruthfulBid(double currentBid, double dailyNeeds, double budget) {
//		strategy 2
		double bid = 0;
		double deficit = dailyNeeds;

		if (deficit > 0) {
			if (deficit < budget/currentBid) {
				bid = deficit;
			} else {
				bid = budget/currentBid;
			}
		}
		return bid;
	}
	public static double MaxBid(double currentBid, double dailyNeeds, double budget) {
//		strategy 3
		double bid = 0;
		bid = dailyNeeds;
		while (bid*currentBid > budget) {
			bid *= 0.8;
		}
		return bid;
	}
	public static double AllOrNothingBid(double currentBid, double dailyNeeds, double budget) {
//		strategy 4
		double bid = 0;
		if (currentBid > budget) {
			bid = 0;
		} else {
			bid = dailyNeeds;
		}
		return bid;
	}
	
	public static double RLBid(double currentBid, User user) {
//		strategy 5
		Action a = user.getAgent().getLastStateAction().getAction();
		double bid = 0;
		double maxPrice = 0;
		double deficit = 0;
		double totalBudget = 0;
		int aim = a.getAddedAmountLevel();
		int budget = a.getBudgetLevel();
		
		switch (aim) {
		case 1:
			deficit = 0;
			break;
		case 2:
			deficit = user.getCar().getBatteryCapacity()*0.25 - user.getCurrentElectricity();
			break;
		case 3:
			deficit = user.getCar().getBatteryCapacity()*0.5 - user.getCurrentElectricity();
			break;
		case 4:
			deficit = user.getCar().getBatteryCapacity()*0.75 - user.getCurrentElectricity();
			break;
		case 5:
			deficit = user.getCar().getBatteryCapacity() - user.getCurrentElectricity();
			break;
		}
		
		switch (budget) {
		case 1:
			maxPrice = 10;
			break;
		case 2:
			maxPrice = 15;
			break;
		case 3:
			maxPrice = 20;
			break;
		case 4:
			maxPrice = 25;
			break;
		}

		totalBudget = deficit*maxPrice;
		if (deficit > 0) {
			if (deficit < totalBudget/currentBid) {
				bid = deficit;
			} else {
				bid = totalBudget/currentBid;
			}
		}
		return bid;
	}
}
