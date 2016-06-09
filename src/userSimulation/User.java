/**
 * 
 */
package userSimulation;

import java.util.UUID;
import marketFramework.Snippet;
	
/**
 * @author pa1g15
 *
 */
public class User {
	private String UID;
	private int userType;
	private int userStrategy;
	private double dailyNeeds = 0;
	private double unitBudget = 0;
	private double currentElectricity = 0;
	private double currentExpenses = 0;
	private double[] preferences;
	private Bid bid;
	
//	EV user only
	private Car car;
	private boolean isEVUser = false;
	
//	EV user constructor
	public User(double dailyDistance, double unitBudget, int userType, int userStrategy, Car car) {
		UID = UUID.randomUUID().toString().replaceAll("-", "").substring(0,7);
		this.userType = userType;
		this.userStrategy = userStrategy;
		this.preferences = new double []{dailyDistance, unitBudget};
		this.car = car;
		this.isEVUser = true;
		this.bid = new Bid();
		this.setDailyPreferences();
	}
//	other constructor
	public User(double dailyNeeds, double unitBudget, int userType, int userStrategy) {
		UID = UUID.randomUUID().toString().replaceAll("-", "").substring(0,7);
		this.userType = userType;
		this.userStrategy = userStrategy;
		this.preferences = new double []{dailyNeeds, unitBudget};
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
		if (isEVUser) {
			this.dailyNeeds = Snippet.normDist(preferences[0]*this.car.getConsumption());
		} else {
			this.dailyNeeds = Snippet.normDist(preferences[0]);
		}
		this.unitBudget = Snippet.normDist(preferences[1]);
	}
	public double getBid(double currentBid, double lastBid) {
		bid.calculateBid(userStrategy, currentBid, lastBid, currentElectricity, dailyNeeds, unitBudget);
		return Snippet.round(bid.getAmount());
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
