/**
 * 
 */
package userSimulation;

import java.util.UUID;
import org.apache.commons.math3.distribution.NormalDistribution;
import marketFramework.Snippet;
	
/**
 * @author pa1g15
 *
 */
public class User {
	private String UID;
	private double dailyDistance;
	private Car carType;
	private double budget;
	private double currentElectricity;
	private double currentExpenses;
	private double[] preferences; 
	private Bid bid;
	
	public User(double dailyDistance, double budget, Car carType) {
		UID = UUID.randomUUID().toString().replaceAll("-", "").substring(0,7);
		this.preferences = new double []{dailyDistance, budget};
		this.dailyDistance = 0;
		this.carType = carType;
		this.budget = 0;
		this.currentElectricity = 0;
		this.currentExpenses = 0;
		this.bid = new Bid();
		this.setDailyPreferences();
	}
	
	public String getUID() {
		return UID;
	}
	public double getCurrentElectricity() {
		return currentElectricity;
	}
	public void setDailyPreferences() {
		NormalDistribution dist = new NormalDistribution(this.preferences[0], 1);
		NormalDistribution budget = new NormalDistribution(this.preferences[1], 1);
		this.dailyDistance = Snippet.round(dist.sample(),2);
		this.budget = Snippet.round(budget.sample(),2);
	}
	public double getBid(double currentBid) {
		bid.calculateBid(currentBid, currentElectricity, dailyDistance, budget, carType);
		return Snippet.round(bid.getAmount(),2);
	}
	public void addElectricity(double electricity) {
		this.currentElectricity = this.currentElectricity + electricity;
	}
	public void addExpenses(double expense) {
		this.currentExpenses = this.currentExpenses + expense;
	}
	public void resetExpense() {
		this.currentExpenses = 0;
	}
}
