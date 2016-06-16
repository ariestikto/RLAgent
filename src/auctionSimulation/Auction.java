/**
 * 
 */
package auctionSimulation;

import java.awt.TextArea;

import org.apache.commons.math3.distribution.NormalDistribution;

import marketFramework.Market;
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
	
	public Auction() {
		restartAuction();
	}
	public void restartAuction() {
		NormalDistribution supplyDist = new NormalDistribution(Market.ELECTRICITY_SUPPLY, 1);
		
		this.dailySupply = Snippet.round(supplyDist.sample());;
		this.currentBid = Market.START_PRICE;
	}
	public double getDailySupply() {
		return dailySupply;
	}

	public double getCurrentBid() {
		return currentBid;
	}
	
	public void runAuction(User[] users, Time t, TextArea auctionResult, TextArea auctionHistory){
		auctionResult.setText("");
		auctionHistory.append(String.format("%-20s\t", "Day: " + t.getDay() + ", " + t.getDayName()) + "\t Weather: " + t.getWeatherName() + "\n");
		restartAuction();
		
		double demand;
		double securedAmount = 0;
		double[] bid = new double[users.length];
		double payout = 0;
		double distribution = 0;
		double finalDistribution = 0;
 		
		do {
			demand  = 0;
			for (int i = 0; i < users.length; i++) {
				bid[i] = users[i].getBid(currentBid, bid[i]);
				demand += bid[i];
			}
//			System.out.println("\nRound " +(currentBid-4) + ", Demand: " + Snippet.round(demand) + ", Price: " + currentBid);
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
//				System.out.println((i+1)+". Bid: " +bid[i]+ ",\tClinched Electricity: " +users[i].gainedElectricity()+ ",\tTotal Payout: " +users[i].payout());
			}
			currentBid += 1;
		} while (demand > dailySupply);
		auctionResult.append("Final Results: \n");
		auctionResult.append("Last Price: " + (currentBid-1) + "\n");
		auctionHistory.append("Last Price: " + (currentBid-1) + "\n");
		for (int i = 0; i < users.length; i++) {
			users[i].auctionResult(bid[i]);
			users[i].addElectricity(users[i].gainedElectricity());
			users[i].addExpenses(users[i].payout());
			finalDistribution += users[i].gainedElectricity();
			auctionResult.append(String.format("%-10s\t", users[i].getUID()) + "Electricity Needs: " + users[i].getDailyNeeds() + "\t Gained Electricity: " +users[i].gainedElectricity()+ " \tTotal Payout: " +users[i].payout() + "\n");
			auctionHistory.append(String.format("%-10s\t", users[i].getUID()) + "Electricity Needs: " + users[i].getDailyNeeds() + "\t Gained Electricity: " +users[i].gainedElectricity()+ " \tTotal Payout: " +users[i].payout() + "\n");
		}
		
		auctionResult.append("Demand: " + Snippet.round(finalDistribution) + "\t Supply: "  + dailySupply + "\n\n");
		auctionHistory.append("Demand: " + Snippet.round(finalDistribution) + "\t Supply: "  + dailySupply + "\n\n");
	}
}
