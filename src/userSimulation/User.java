/**
 * 
 */
package userSimulation;

import java.util.UUID;
import java.util.List;
import java.util.ArrayList;
import marketFramework.Snippet;
import marketFramework.Time;
import agent.Bid;
	
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
	private double[] preferences = new double []{0, 0};
	private Bid bid = new Bid();
	private List<ElectricityBundle> clinched = new ArrayList<ElectricityBundle>();
	
//	EV user only
	private Car car = new Car(0);
	private boolean isEVUser = false;
	private boolean isShop = false;
//	EV user constructor
	public User(int userType, int userStrategy, Car car) {
		UID = UUID.randomUUID().toString().replaceAll("-", "").substring(0,7);
		this.userType = userType;
		this.userStrategy = userStrategy;
		this.car = car;
		this.isEVUser = true;
	}
//	other constructor
	public User(int userType, int userStrategy) {
		UID = UUID.randomUUID().toString().replaceAll("-", "").substring(0,7);
		this.userType = userType;
		this.userStrategy = userStrategy;
	}
	
	public String getUID() {
		return UID;
	}
	public int getStrategy () {
		return userStrategy;
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
	public double getDailyNeeds() {
		return dailyNeeds;
	}
	public double getBudget() {
		return Snippet.round(dailyNeeds*unitBudget);
	}
	public void setDailyPreferences() {
		this.dailyNeeds = Snippet.normDist(preferences[0]);
		this.unitBudget = Snippet.normDist(preferences[1]);
	}
	public double getBid(double currentBid, double lastBid) {
		bid.calculateBid(currentBid, lastBid, this);
		return Snippet.round(bid.getAmount());
	}
	public void addElectricity(double electricity) {
		this.currentElectricity += electricity;
		if (isEVUser) {
			if (currentElectricity > car.getBatteryCapacity()) {
				this.currentElectricity = car.getBatteryCapacity();
			}
		}
	}
	public void useElectricity() {
		if (isEVUser) {
			this.currentElectricity -= this.dailyNeeds;
			if (currentElectricity < 0) {
				this.currentElectricity = 0;
			}
		} else {
			this.currentElectricity = 0;
		}
	}
	public void addExpenses(double expense) {
		this.currentExpenses += expense;
	}
	public void resetExpense() {
		this.currentExpenses = 0;
	}
	public double getExpenses() {
		return currentExpenses;
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
		switch (this.userType) {
		case 1:
			preferences = UserModel.EVUserA(time, this);
			break;
		case 2:
			preferences = UserModel.EVUserB(time, this);
			break;
		case 3:
			preferences = UserModel.CompanyBuyer();
			break;
		case 4:
			preferences = UserModel.OtherUser(time);
			break;
		}
		this.preferences[0] = preferences.getAmount();
		this.preferences[1] = preferences.getUnitPrice();
		this.setDailyPreferences();
	}
}
