/**
 * 
 */
package agent;

import marketFramework.Market;
/**
 * @author pa1g15
 *
 */
public class BidStrategy {
	public static double randomBid(double currentBid, double dailyNeeds, double lastBid) {
//		strategy 1
		double bid = 0;
		if (currentBid == Market.START_PRICE) {
			bid = dailyNeeds;
		} else {
			bid = lastBid - Math.random()*lastBid;
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
	public static double MaxBid(double dailyNeeds) {
//		strategy 3
		double bid = 0;
		bid = dailyNeeds;
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
