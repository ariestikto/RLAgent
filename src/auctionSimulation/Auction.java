/**
 * 
 */
package auctionSimulation;

import org.apache.commons.math3.distribution.NormalDistribution;
import marketFramework.Snippet;
import userSimulation.User;
import marketFramework.Time;
/**
 * @author pa1g15
 *
 */
public class Auction {
	private double dailySupply;
	private double currentBid;
	
	public Auction(double dailySupply, double currentBid) {
		NormalDistribution supplyDist = new NormalDistribution(dailySupply, 1);
		
		this.dailySupply = Snippet.round(supplyDist.sample());;
		this.currentBid = currentBid;
	}

	public double getDailySupply() {
		return dailySupply;
	}

	public double getCurrentBid() {
		return currentBid;
	}
	
	public void runAuction(User[] users, Time t){
		double demand;
		double securedAmount = 0;
		double[] bid = new double[users.length];
		double payout = 0;
		double distribution = 0;
		double finalDistribution = 0;
 		
		System.out.println("Supply: " + dailySupply);
		for (int i = 0; i < users.length; i++) {
			users[i].resetAuction();
			users[i].generatePreferences(t);
		}
		do {
			demand  = 0;
			for (int i = 0; i < users.length; i++) {
				bid[i] = users[i].getBid(currentBid, bid[i]);
				demand += bid[i];
			}
			System.out.println("\nRound " +(currentBid-4) + ", Demand: " + Snippet.round(demand) + ", Price: " + currentBid);
			for (int i = 0; i < users.length; i++) {
				payout = 0;
				distribution = 0;
				
				if (bid[i] != 0) {
					if (demand - bid[i] < dailySupply) {
						securedAmount = dailySupply-(demand-bid[i])-users[i].gainedElectricity();
						distribution = securedAmount;
						payout = currentBid;
					}
				} else {
					users[i].resetAuction();
				}
				users[i].clinchElectricity(distribution, payout);
				System.out.println((i+1)+". Bid: " +bid[i]+ ",\tClinched Electricity: " +users[i].gainedElectricity()+ ",\tTotal Payout: " +users[i].payout());
			}
			currentBid += 1;
		} while (demand > dailySupply);
		System.out.println("\nfinal Results");
		for (int i = 0; i < users.length; i++) {
			users[i].auctionResult(bid[i]);
			finalDistribution += users[i].gainedElectricity();
			System.out.println((i+1)+". Bid: " +bid[i]+ ",\tClinched Electricity: " +users[i].gainedElectricity()+ ",\tTotal Payout: " +users[i].payout());
		}
		System.out.println("Distributed Electricity: " + Snippet.round(finalDistribution));
	}
}
