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
import agent.learningAgent.TaskValue;
import agent.learningAgent.Action;
import agent.learningAgent.QFunction;
import agent.learningAgent.ReinforcementLearning;
import agent.learningAgent.Reward;
import agent.learningAgent.State;
	
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
	private boolean isShop = false;
	private int reward = 0;
	private double dailyValue = 0;
	private State lastState = new State();
	private Action lastAction = new Action();
	private List<TaskValue> taskList = new ArrayList<TaskValue>();
	private ReinforcementLearning agent =  new ReinforcementLearning(0.3, 0.9, 0.3);
	
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
		return Snippet.round(currentElectricity);
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
	public double getBid(double currentBid, double lastBid, Time t) {
		bid.calculateBid(currentBid, lastBid, this, agent, t);
		return Snippet.round(bid.getAmount());
	}
	public ReinforcementLearning getAgent() {
		return agent;
	}
	public State getState() {
		return lastState;
	}
	public Action getAction() {
		return lastAction;
	}
	public int getMaxTaskValue() {
		int value = 0;
		if (taskList.size() > 0) {
			for (TaskValue temp : taskList) {
				value += temp.getValue();
		    }
		}
		return value;
	}
	public int getReward() {
		return reward;
	}
	public double getMaxDailyValue() {
		return Snippet.round(dailyValue);
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
		if (isEVUser) {
			reward = Reward.RewardPatternA(this);
			if (taskList.size() > 0) {
				for (TaskValue temp : taskList) {
					needs = Snippet.round(temp.getNeeds()*car.getConsumption());
					if (Snippet.round(currentElectricity) - needs >= 0) {
						this.currentElectricity -= needs;
						this.dailyValue += needs;
					}
			    }
				if (agent.getQSize() > 0) {
					this.agent.updateQVal(lastState, lastAction, Snippet.round(dailyValue));
				}
			}
		} else {
			this.currentElectricity = 0;
		}
		this.currentElectricity = Snippet.round(currentElectricity);
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
	public void generatePreferences(Time t) {
		ElectricityBundle preferences = new ElectricityBundle();
		List<TaskValue> task = new ArrayList<TaskValue>();
		switch (this.userType) {
		case 1:
			UserModel.EVUserA(t, this, preferences, task);
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
		}
		
		if (userStrategy == 5) {
			State s = new State(t.getDayName(), t.getWeather(), currentElectricity);
			Action a = new Action();
			
			// Re-evaluate Q
			if (!lastState.isEmpty()) {
				agent.updateQVal(lastState, lastAction, (agent.findState(lastState, lastAction).getValue() + agent.getLearningRate()*(reward + agent.getDiscountFactor()*agent.findState(s, agent.bestAction(s, this)).getValue() - agent.findState(lastState, lastAction).getValue())));
			}
			// epsilon greedy algorithm
			// =========================
			if (Math.random() > agent.getEpsilon()) {
				a = agent.bestAction(s, this);
			} else {
				a = agent.randomAction(s, this);
			}
			
			QFunction Q = new QFunction(s, a);
			// -------------------------
			if (!agent.isQExist(s, a)) {
				agent.addQVal(Q, 0);
			}
			this.lastState = s;
			this.lastAction = a;
			System.out.println("Bid: " + lastAction.getBidAmount() + "\t Unit: " + lastAction.getUnitBudget() + "\t Total: " + lastAction.getBudget());
		}
		
		this.dailyValue = 0;
		this.taskList = task;
		this.dailyNeeds = preferences.getAmount();
		this.unitBudget = preferences.getUnitPrice();
	}
}
