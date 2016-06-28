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
				bid[i] = users[i].getBid(currentBid, bid[i], t);
				demand += bid[i];
			}
			auctionResult.append("\nRound " +(int)(currentBid-4) + "\nPrice: " + currentBid + "\t Demand: " + Snippet.round(demand) + "\t Supply: " + Snippet.round(dailySupply) + "\n");
			for (int i = 0; i < users.length; i++) {
				payout = 0;
				distribution = 0;
				
				if (bid[i] != 0) {
					if (demand - bid[i] < dailySupply) {
						securedAmount = dailySupply-(demand-bid[i])-users[i].gainedElectricity();
						if (securedAmount + users[i].gainedElectricity() > bid[i]) {
							securedAmount = bid[i] - users[i].gainedElectricity();
						}
						distribution = securedAmount;
						payout = currentBid;
					}
				} else {
					users[i].resetAuction();
				}
				users[i].clinchElectricity(distribution, payout);
				auctionResult.append(String.format("%-12s\t", users[i].getUID()) + String.format("%-12s\t", " Bid: " +bid[i]) + "Clinched Electricity: " + String.format("%-15s\t", users[i].gainedElectricity())+ "Total Payout: " +users[i].payout() + "\n");
			}
			currentBid += 1;
		} while (Snippet.round(demand) > Snippet.round(dailySupply));
		auctionResult.append("\nFinal Results: \n");
		auctionResult.append("Last Price: " + (currentBid-1) + "\n");
		auctionHistory.append("Last Price: " + (currentBid-1) + "\n");
		for (int i = 0; i < users.length; i++) {
			users[i].addExpenses(users[i].payout());
			finalDistribution += users[i].gainedElectricity();
			auctionResult.append(String.format("%-12s\t", users[i].getUID()) + "Electricity Needs: " + Snippet.round(users[i].getDailyNeeds()) + "\t Current Electricity: " + users[i].getCurrentElectricity() + "\t Gained Electricity: " +users[i].gainedElectricity()+ " \tTotal Payout: " +users[i].payout() + "\n");
			users[i].addElectricity(users[i].gainedElectricity());
			auctionHistory.append(String.format("%-12s\t", users[i].getUID()) + "Electricity Needs: " + Snippet.round(users[i].getDailyNeeds()) + "\t Current Electricity: " + users[i].getCurrentElectricity() + "\t Gained Electricity: " +users[i].gainedElectricity()+ " \tTotal Payout: " +users[i].payout() + "\n");
		}
		
		auctionResult.append("Demand: " + Snippet.round(finalDistribution) + "\t Supply: "  + dailySupply + "\n\n");
		auctionHistory.append("Demand: " + Snippet.round(finalDistribution) + "\t Supply: "  + dailySupply + "\n\n");
	}
}
