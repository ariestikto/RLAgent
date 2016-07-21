/**
 * 
 */
package userSimulation;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import agent.Bid;
import agent.Performance;
import agent.Task;
import agent.reinforcementLearning.ReinforcementLearning;
import marketFramework.Market;
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
	private Bid bid = new Bid();
	private List<ElectricityBundle> clinched = new ArrayList<ElectricityBundle>();
	
//	EV user only
	private Car car = new Car(0);
	private boolean isEVUser = false;
	private List<Task> taskList = new ArrayList<Task>();
	private ReinforcementLearning agent = new ReinforcementLearning(Market.AGENT_LEARNING_RATE, Market.AGENT_DISCOUNT_FACTOR, Market.EPSILON_PARAMETER);
	private Performance performance = new Performance();
	
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
	
	public double getDailyNeeds() {
		return dailyNeeds;
	}
	
	public double getBudget() {
		return Snippet.round(dailyNeeds*unitBudget);
	}
	
	public double getCurrentElectricity() {
		return Snippet.round(currentElectricity);
	}
	
	public double getExpenses() {
		return currentExpenses;
	}
	
	public Car getCar() {
		return car;
	}
	
	public List<Task> getTask() {
		return taskList;
	}
	
	public ReinforcementLearning getAgent() {
		return agent;
	}
	
	public Performance getPerformance() {
		return performance;
	}
	
	public double getBid(double currentBid, double lastBid, Time t) {
		bid.calculateBid(currentBid, lastBid, this, t);
		return Snippet.round(bid.getAmount());
	}
	
	public void clinchElectricity(double amount, double price) {
		ElectricityBundle clinch = new ElectricityBundle(amount, price);
		this.clinched.add(clinch);
	}
	
	public double clinchedElectricity() {
		double electricity = 0;
		if(clinched.isEmpty()) {
			return 0;
		}
		for (ElectricityBundle temp : clinched) {
			electricity += temp.getAmount();
	    }
		return Snippet.round(electricity);
	}
	
	public void addElectricity(double electricity) {
		this.currentElectricity += electricity;
		if (isEVUser) {
			if (currentElectricity > car.getBatteryCapacity()) {
				this.currentElectricity = car.getBatteryCapacity();
			}
		}
		this.currentElectricity = Snippet.round(currentElectricity);
	}
	
	public void useElectricity() {
		double needs = 0;
		double totalValue = 0;
		double gainedValue = 0;
		double totalNeeds = 0;
		if (isEVUser) {
			if (taskList.size() > 0) {
				for (Task temp : taskList) {
					totalNeeds += temp.getNeeds();
					needs = Snippet.round(temp.getNeeds()*car.getConsumption());
					totalValue += temp.getValue();
					if (Snippet.round(currentElectricity) - needs >= 0) {
						this.currentElectricity -= needs;
						temp.finishTask();
						gainedValue += temp.getValue();
					}
			    }
				this.performance.setMaxValue(totalValue);
				this.performance.setGainedValue(gainedValue);
			}
		} else {
			this.currentElectricity = 0;
		}
		this.currentElectricity = Snippet.round(currentElectricity);
		this.performance.setActualSpending(payout());
		if (userStrategy == 5) {
			System.out.println(totalNeeds);
		}
		System.out.println(totalNeeds);
	}
	
	public void addExpenses(double expense) {
		this.currentExpenses += expense;
	}
	
	public void resetExpense() {
		this.currentExpenses = 0;
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
	
	public void resetAuction() {
		this.clinched.clear();
	}
	
	public void generatePreferences(Time t) {
		ElectricityBundle preferences = new ElectricityBundle();
		List<Task> task = new ArrayList<Task>();
		switch (this.userType) {
		case 1:
			UserModel.EVUserA(t, this, preferences, task);
			this.performance.setMaxSpending(23*(car.getBatteryCapacity()-currentElectricity));
			break;
		case 2:
			UserModel.EVUserB(t, this, preferences, task);
			break;
		case 3:
			UserModel.CompanyBuyer(preferences);
			break;
		case 4:
			UserModel.OtherUser(t, preferences);
			break;
		case 5:
			UserModel.TestCaseEVUser(t, this, preferences, task);
			break;
		case 6:
			UserModel.CompanyBuyerB(preferences);
			break;
		}
		
		this.taskList = task;
		this.dailyNeeds = preferences.getAmount();
		this.unitBudget = preferences.getUnitPrice();
	}
}
