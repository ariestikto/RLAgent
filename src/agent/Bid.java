/**
 * 
 */
package agent;

import agent.dynamicProgramming.ProbabilityDatabase;
import agent.dynamicProgramming.ValueIteration;
import marketFramework.Parameter;
import marketFramework.Time;
import userSimulation.User;
/**
 * @author pa1g15
 *
 */
public class Bid {
	private double amount; 
	
	public Bid() {
		this.amount = 0;
	}
	public Bid(double amount) {
		this.amount = amount;
	}
	
	public double getAmount() {
		return amount;
	}
		
	public void calculateBid(double currentBid, double lastBid, User user, Time t) {
		switch (user.getStrategy()) {
		case 1:
			this.amount = randomBid(currentBid, user.getDailyNeeds() - user.getCurrentElectricity(), lastBid, user.getBudget());
			break;
		case 2:
			this.amount = TruthfulBid(currentBid, user.getDailyNeeds() - user.getCurrentElectricity(), user.getBudget());
			break;
		case 3:
			this.amount = MaxBid(currentBid, user.getDailyNeeds() - user.getCurrentElectricity(), user.getBudget());
			break;
		case 4:
			this.amount = AllOrNothingBid(currentBid, user.getDailyNeeds() - user.getCurrentElectricity(), user.getBudget());
			break;
		case 5:
			this.amount = RLBid(currentBid, user);
			break;
		}
		if (amount < user.clinchedElectricity()) {
			this.amount = user.clinchedElectricity();
		}
	}
	
	public void simulationBid(double currentBid, User user, Action simulationAction) {
		this.amount = DBBuildBid(currentBid, user, simulationAction);
		
		if (amount < user.clinchedElectricity()) {
			this.amount = user.clinchedElectricity();
		}
	}
	
	public void searchDPBid(double currentBid, User user, ProbabilityDatabase DB, ValueIteration DPAgent, Time t) {
		State state= new State();
		double currentLevel = user.getCurrentElectricity()/user.getCar().getBatteryCapacity();
		if (currentLevel > 0.99) {
			state.setState(t.getDayName(), t.getWeather(), 5);
		} else if (currentLevel > 0.75) {
			state.setState(t.getDayName(), t.getWeather(), 4);
		} else if (currentLevel > 0.5) {
			state.setState(t.getDayName(), t.getWeather(), 3);
		} else if (currentLevel > 0.25) {
			state.setState(t.getDayName(), t.getWeather(), 2);
		} else {
			state.setState(t.getDayName(), t.getWeather(), 1);
		}
		
		this.amount = DPBid(currentBid, user, DB, DPAgent, state);
		
		if (amount < user.clinchedElectricity()) {
			this.amount = user.clinchedElectricity();
		}
	}
	
	public static double randomBid(double currentBid, double dailyNeeds, double lastBid, double budget) {
//		strategy 1
		double bid = 0;
		if (currentBid == Parameter.START_PRICE) {
			bid = dailyNeeds;
		} else {
			do {
				bid = Math.random()*lastBid;
			} while (bid*currentBid > budget);
			if (bid < 0) {
				bid = 0;
			}
		}
		
		return bid;
	}
	public static double TruthfulBid(double currentBid, double dailyNeeds, double budget) {
//		strategy 2
		double bid = 0;
		double deficit = dailyNeeds;

		if (deficit > 0) {
			if (deficit < budget/currentBid) {
				bid = deficit;
			} else {
				bid = budget/currentBid;
			}
		}
		return bid;
	}
	public static double MaxBid(double currentBid, double dailyNeeds, double budget) {
//		strategy 3
		double bid = 0;
		bid = dailyNeeds;
		while (bid*currentBid > budget) {
			bid *= 0.8;
		}
		return bid;
	}
	public static double AllOrNothingBid(double currentBid, double dailyNeeds, double budget) {
//		strategy 4
		double bid = 0;
		if (currentBid > budget/dailyNeeds) {
			bid = 0;
		} else {
			bid = dailyNeeds;
		}
		return bid;
	}
	
	public static double RLBid(double currentBid, User user) {
//		strategy 5
		Action a = user.getAgent().getLastStateAction().getAction();
		if (user.isAlternate()) {
			a = user.getAgent().getBestStateAction().getAction();
		}
		double bid = 0;
		double maxPrice = 0;
		double deficit = 0;
		double totalBudget = 0;
		int aim = a.getAddedAmountLevel();
		int budget = a.getBudgetLevel();
		switch (aim) {
		case 1:
			deficit = 0;
			break;
		case 2:
			deficit = user.getCar().getBatteryCapacity()*0.25;
			break;
		case 3:
			deficit = user.getCar().getBatteryCapacity()*0.5;
			break;
		case 4:
			deficit = user.getCar().getBatteryCapacity()*0.75;
			break;
		case 5:
			deficit = user.getCar().getBatteryCapacity();
			break;
		}
		if (user.getCar().getBatteryCapacity() < (deficit + user.getCurrentElectricity())) {
			deficit = user.getCar().getBatteryCapacity() - user.getCurrentElectricity();
		}
		switch (budget) {
		case 1:
			maxPrice = 8;
			break;
		case 2:
			maxPrice = 15;
			break;
		case 3:
			maxPrice = 23;
			break;
		case 4:
			maxPrice = 30;
			break;
		}
		totalBudget = deficit*maxPrice;
		if (deficit > 0) {
			if (deficit < totalBudget/currentBid) {
				bid = deficit;
			} else {
				bid = totalBudget/currentBid;
			}
		}
		return bid;
		
	}
	
	public static double DBBuildBid(double currentBid, User user, Action a) {
//		strategy 6 in simulation
		double bid = 0;
		double maxPrice = 0;
		double deficit = 0;
		double totalBudget = 0;
		int aim = a.getAddedAmountLevel();
		int budget = a.getBudgetLevel();
		switch (aim) {
		case 1:
			deficit = 0;
			break;
		case 2:
			deficit = user.getCar().getBatteryCapacity()*0.25;
			break;
		case 3:
			deficit = user.getCar().getBatteryCapacity()*0.5;
			break;
		case 4:
			deficit = user.getCar().getBatteryCapacity()*0.75;
			break;
		case 5:
			deficit = user.getCar().getBatteryCapacity();
			break;
		}
		if (user.getCar().getBatteryCapacity() < (deficit + user.getCurrentElectricity())) {
			deficit = user.getCar().getBatteryCapacity() - user.getCurrentElectricity();
		}
		switch (budget) {
		case 1:
			maxPrice = 8;
			break;
		case 2:
			maxPrice = 15;
			break;
		case 3:
			maxPrice = 23;
			break;
		case 4:
			maxPrice = 30;
			break;
		}
		totalBudget = deficit*maxPrice;
		if (deficit > 0) {
			if (deficit < totalBudget/currentBid) {
				bid = deficit;
			} else {
				bid = totalBudget/currentBid;
			}
		}
		return bid;
	}
	
	public static double DPBid(double currentBid, User user, ProbabilityDatabase DB, ValueIteration DPAgent, State state) {
//		strategy 6 real
		double bid = 0;
		double maxPrice = 0;
		double deficit = 0;
		double totalBudget = 0;
		Action a = DPAgent.bestAction(state, DB);
		int aim = a.getAddedAmountLevel();
		int budget = a.getBudgetLevel();
		
		switch (aim) {
		case 1:
			deficit = 0;
			break;
		case 2:
			deficit = user.getCar().getBatteryCapacity()*0.25;
			break;
		case 3:
			deficit = user.getCar().getBatteryCapacity()*0.5;
			break;
		case 4:
			deficit = user.getCar().getBatteryCapacity()*0.75;
			break;
		case 5:
			deficit = user.getCar().getBatteryCapacity();
			break;
		}
		if (user.getCar().getBatteryCapacity() < (deficit + user.getCurrentElectricity())) {
			deficit = user.getCar().getBatteryCapacity() - user.getCurrentElectricity();
		}
		switch (budget) {
		case 1:
			maxPrice = 8;
			break;
		case 2:
			maxPrice = 15;
			break;
		case 3:
			maxPrice = 23;
			break;
		case 4:
			maxPrice = 30;
			break;
		}
		totalBudget = deficit*maxPrice;
		if (deficit > 0) {
			if (deficit < totalBudget/currentBid) {
				bid = deficit;
			} else {
				bid = totalBudget/currentBid;
			}
		}
		return bid;
	}
}
