/**
 * 
 */
package userSimulation;

import java.util.UUID;
import java.util.List;
import java.util.ArrayList;
import marketFramework.Snippet;
import marketFramework.Time;
	
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
	private List<ElectricityBundle> clinched = new ArrayList<ElectricityBundle>();
	
//	EV user only
	private Car car;
	private boolean isEVUser = false;
	private boolean isShop = false;
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
	public void setShop(int isShop) {
		if (isShop == 1) {
			this.isShop = true;
		} else {
			this.isShop = false;
		}
	}
	public boolean getShop() {
		return isShop;
	}
	public double getCurrentElectricity() {
		return currentElectricity;
	}
	public Car getCar() {
		return car;
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
	public void resetAuction() {
		this.clinched.clear();
	}
	public void clinchElectricity(double amount, double price) {
		ElectricityBundle clinch = new ElectricityBundle(amount, price);
		this.clinched.add(clinch);
	}
	public double gainedElectricity() {
		double electricity = 0;
		if(clinched.isEmpty()) {
			return 0;
		}
		for (ElectricityBundle temp : clinched) {
			electricity += temp.getAmount();
	    }
		return Snippet.round(electricity);
	}
	public double payout() {
		double payout = 0;
		if(clinched.isEmpty()) {
			return 0;
		}
		for (ElectricityBundle temp : clinched) {
			payout += temp.getUnitPrice()*temp.getAmount();
	    }
		return Snippet.round(payout);
	}
	public void auctionResult(double finalBid) {
		double amount = 0;
		int i = 0;
		double difference = 0;
		if (this.gainedElectricity() > finalBid) {
			while (amount < finalBid) {
				amount += this.clinched.get(i).getAmount();
				i += 1;
			}
				this.clinched.subList(i, this.clinched.size()).clear();
				if (!this.clinched.isEmpty()) {
					difference = this.gainedElectricity() - finalBid;
					ElectricityBundle last = this.clinched.get(this.clinched.size()-1);
					last.setAmount(last.getAmount()-difference);
				}
		}
	}
	public void generatePreferences(Time time) {
		ElectricityBundle preferences = new ElectricityBundle();
		preferences = UserModel.EVUserA(time, this);
		switch (this.userType) {
		case 1:
			this.preferences[0] = preferences.getAmount();
			this.preferences[1] = preferences.getUnitPrice()/this.preferences[0];
			this.setDailyPreferences();
			break;
		}
	}
}
