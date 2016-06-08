/**
 * 
 */
package auctionSimulation;

import org.apache.commons.math3.distribution.NormalDistribution;
import marketFramework.Snippet;
import userSimulation.User;

/**
 * @author pa1g15
 *
 */
public class Auction {
	private double dailySupply;
	private double currentBid;
	
	public Auction(double dailySupply, double currentBid) {
		NormalDistribution supplyDist = new NormalDistribution(dailySupply, 1);
		
		this.dailySupply = Snippet.round(supplyDist.sample(),2);;
		this.currentBid = currentBid;
	}

	public double getDailySupply() {
		return dailySupply;
	}

	public double getCurrentBid() {
		return currentBid;
	}
	
	public void runAuction(User[] users){
		double demand;
		double securedAmount;
		double[] payout = new double[users.length];
		double[] distribution = new double[users.length];
		double[] bid = new double[users.length];
		double finalDistribution = 0;
 		
		System.out.println("Supply: " + dailySupply);
		do {
			demand  = 0;
			for (int i = 0; i < users.length; i++) {
				bid[i] = users[i].getBid(currentBid);
				demand += Snippet.round(bid[i],2);
			}
			System.out.println("\nRound " +(currentBid-4) + ", Demand: " + demand + ", Price: " + currentBid);
			for (int i = 0; i < users.length; i++) {
				if (demand - bid[i] < dailySupply) {
					securedAmount = Snippet.round(dailySupply-(demand-bid[i])-distribution[i],2);
					distribution[i] += Snippet.round(securedAmount,2);
					payout[i] += Snippet.round(currentBid*securedAmount,2);
				}
				System.out.println((i+1)+". Bid: " +bid[i]+ ",\tClinched Electricity: " +distribution[i]+ ",\tTotal Payout: " +payout[i]);
			}
			currentBid += 1;
		} while (demand > dailySupply);
		for (int i = 0; i < distribution.length; i++) {
			finalDistribution += distribution[i];
		}
		System.out.println("Distributed Electricity: " + finalDistribution);
	}
}
