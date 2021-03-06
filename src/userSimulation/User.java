/**
 * 
 */
package userSimulation;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import agent.Action;
import agent.Bid;
import agent.Performance;
import agent.Task;
import agent.dynamicProgramming.ProbabilityDatabase;
import agent.dynamicProgramming.ValueIteration;
import agent.qLearning.QLearning;
import marketFramework.Parameter;
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
	private double avNeeds = 0;
	private double stdev = 0;
	private Bid bid = new Bid();
	private List<ElectricityBundle> clinched = new ArrayList<ElectricityBundle>();
	
//	EV user only
	private Car car = new Car(0);
	private boolean isEVUser = false;
	private boolean isAlternate = false;
	private List<Task> taskList = new ArrayList<Task>();
	private QLearning agent = new QLearning(Parameter.AGENT_LEARNING_RATE, Parameter.AGENT_DISCOUNT_FACTOR, Parameter.EPSILON_PARAMETER);
	private Performance performance = new Performance();

	//	EV user constructor
	public User(int userType, int userStrategy, Car car) {
		UID = UUID.randomUUID().toString().replaceAll("-", "").substring(0,7);
		this.userType = userType;
		this.userStrategy = userStrategy;
		this.car = car;
		this.isEVUser = true;
	}
	//	Other EV user constructor
	public User(int userType, double avNeeds, double stdev, Car car) {
		UID = UUID.randomUUID().toString().replaceAll("-", "").substring(0,7);
		this.userType = userType;
		this.userStrategy = 2;
		this.avNeeds = avNeeds;
		this.stdev = stdev;
		this.car = car;
		this.isEVUser = false;
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
	
	public QLearning getAgent() {
		return agent;
	}
	
	public Performance getPerformance() {
		return performance;
	}
	
	public boolean isAlternate() {
		return isAlternate;
	}
	
	public double getBid(double currentBid, double lastBid, Time t) {
		bid.calculateBid(currentBid, lastBid, this, t);
		return Snippet.round(bid.getAmount());
	}
	
	public double getDPBid(double currentBid, ProbabilityDatabase DB, ValueIteration DPAgent, Time t) {
		bid.searchDPBid(currentBid, this, DB, DPAgent, t);
		return Snippet.round(bid.getAmount());
	}
	
	public double getSimulationBid(double currentBid, Action simulationAction) {
		bid.simulationBid(currentBid, this, simulationAction);
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
	
	public void resetElectricity() {
		this.currentElectricity = 0;
	}
	
	public void useElectricity() {
		double needs = 0;
		double totalValue = 0;
		double gainedValue = 0;
		if (isEVUser) {
			this.performance.setFinishedTask(0);		
			if (taskList.size() > 0) {
//				System.out.println("=========================");
				for (Task temp : taskList) {
					needs = Snippet.round(temp.getNeeds()*car.getConsumption());
					totalValue += temp.getValue();
//					System.out.println(temp.getValue());
					if (Snippet.round(currentElectricity) - needs >= 0) {
						this.currentElectricity -= needs;
						temp.finishTask();
						gainedValue += temp.getValue();
						this.performance.setFinishedTask(performance.getFinishedTask()+1);					
					}
			    }
			}
			this.performance.setTotalTask(taskList.size());
			this.performance.setMaxValue(totalValue);
			this.performance.setGainedValue(gainedValue);
			this.performance.setLeftoverElectricity(this.currentElectricity);
		} else {
			this.currentElectricity = 0;
		}
		this.currentElectricity = Snippet.round(currentElectricity);
		this.performance.setActualSpending(this.payout());
		
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
			UserModel_old.EVUserA(t, this, preferences, task);
			break;
		case 2:
			UserModel_old.EVUserB(t, this, preferences, task);
			break;
		case 3:
			UserModel_old.CompanyBuyer(preferences);
			break;
		case 4:
			UserModel_old.OtherUser(t, preferences);
			break;
		case 5:
			UserModel_old.TestCase1EVUser(t, this, preferences, task);
			break;
		case 6:
			UserModel_old.TestCase2EVUser(t, this, preferences, task);
			break;
		case 7:
			UserModel_old.CompanyBuyerB(preferences);
			break;
		case 8:
			UserModel.EVUSer_1600(t, this, preferences, task);
			break;
		case 9:
			UserModel.EVUSer_4041(t, this, preferences, task);
			break;
		case 10:
			UserModel.EVUSer_3729(t, this, preferences, task);
			break;
		case 11:
			UserModel.EVUSer_Other(t, this, preferences, avNeeds, stdev);
			break;
		}
		
		this.performance.setMaxSpending(30*(car.getBatteryCapacity()-currentElectricity));
		this.taskList = task;
		this.dailyNeeds = preferences.getAmount();
		this.unitBudget = preferences.getUnitPrice();
	}
	
	public void copyUser(User user) {
		this.userType = user.userType;
		this.userStrategy = user.userStrategy;
		this.dailyNeeds = user.dailyNeeds;
		this.unitBudget = user.unitBudget;
		this.currentElectricity = user.currentElectricity;
		this.currentExpenses = user.currentExpenses;
		this.bid = new Bid(bid.getAmount());
		this.clinched  = new ArrayList<ElectricityBundle>();
		
//		EV user only
		this.car = user.car;
		this.isEVUser = user.isEVUser;
		this.isAlternate = true;
		this.taskList = new ArrayList<Task>();
		for (Task temp : user.taskList) {
			Task task = new Task(temp.getValue(), temp.getNeeds());
			this.taskList.add(task);
		}
		this.agent = user.agent;
		this.performance = new Performance();
	}
}
