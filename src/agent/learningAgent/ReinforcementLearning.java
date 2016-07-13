/**
 * 
 */
package agent.learningAgent;

import java.util.List;

import java.util.ArrayList;
import userSimulation.User;
import marketFramework.Time;
/**
 * @author pa1g15
 *
 */
public class ReinforcementLearning {

	private List<QFunction> Q;
	private double reward = 0;
	private double learningRate;
	private double discountFactor;
	private double epsilon;
	private QFunction lastStateAction;
	
	//	RL agent with e-greedy policy
	public ReinforcementLearning(double learningRate, double discountFactor, double epsilon) {
		this.learningRate = learningRate;
		this.discountFactor = discountFactor;
		this.epsilon = epsilon;
		this.Q = new ArrayList<QFunction>();
	}
	
	public double getReward() {
		return reward;
	}
	
	public double getLearningRate() {
		return learningRate;
	}

	public double getDiscountFactor() {
		return discountFactor;
	}

	public double getEpsilon() {
		return epsilon;
	}
	
	public int getQSize() {
		return Q.size();
	}
	
	public void setLastStateAction(QFunction Q) {
		this.lastStateAction = Q;
	}
	
	public QFunction getLastStateAction() {
		return lastStateAction;
	}
	
	public Action bestAction(State s, User user) {
		Action a = randomAction(s, user);
		double reward = 0;
		if (Q.size() > 0) {
			for (QFunction temp : Q) {
				if (s.isEqual(temp.getState())) {
					if (temp.getReward() > reward) {
						a = temp.getAction();
						reward = temp.getReward();
					}
				}
		    }
		}
		return a;
	}
	
	public QFunction findSAPair(State s, Action a) {
		QFunction QVal = new QFunction(s, a);
		for (QFunction temp : Q) {
			if (s.isEqual(temp.getState()) && a.isEqual(temp.getAction())) {
				QVal = temp;
			}
		}
		return QVal;
	}
	
	public boolean isQExist(State s, Action a) {
		for (QFunction temp : Q) {
			if (s.isEqual(temp.getState()) && a.isEqual(temp.getAction())) {
				return true;
			}
		}
		return false;
	}
	
	public void updateQ(State s, Action a, double reward) {
		if (isQExist(s, a)) {
			for (QFunction temp : Q) {
				if (s.isEqual(temp.getState()) && a.isEqual(temp.getAction())) {
					temp.setReward(reward);
				}
			}
		} else {
			this.Q.add(new QFunction(s, a, reward));
		}
		
	}
	
	public Action randomAction(State s, User user) {
		Action a = new Action();
		int amountLevel = s.getElectricityLevel() + (int)(Math.random() * ((5 - s.getElectricityLevel()) + 1));
		int budgetLevel = 1 + (int)(Math.random() * ((4 - 1) + 1));
		if (amountLevel == 1) {
			budgetLevel = 1;
		}
		a.setAction(amountLevel, budgetLevel);
		return a;
	}
	
	public void takeAction(User user, Time t) {
			double currentLevel = user.getCurrentElectricity()/user.getCar().getBatteryCapacity();
			State s = new State();
			Action a = new Action();
			
			if (currentLevel > 0.875) {
				s.setState(t.getDayName(), t.getWeather(), 5);
			} else if (currentLevel > 0.625) {
				s.setState(t.getDayName(), t.getWeather(), 4);
			} else if (currentLevel > 0.375) {
				s.setState(t.getDayName(), t.getWeather(), 3);
			} else if (currentLevel > 0.125) {
				s.setState(t.getDayName(), t.getWeather(), 2);
			} else {
				s.setState(t.getDayName(), t.getWeather(), 1);
			}
			
			// epsilon greedy algorithm
			// =========================
			if (Math.random() > getEpsilon()) {
			//	System.out.println("Best");
				a = bestAction(s, user);
			} else {
			//	System.out.println("Random");
				a = randomAction(s, user);
			}
			// ===============================
			this.lastStateAction = findSAPair(s, a);
	}
	
	public void evaluateAction(User user, int nextWeather) {
		this.reward = Reward.RewardPatternA(user);
		double QReward = 0;
		QFunction lastQ = findSAPair(lastStateAction.getState(), lastStateAction.getAction());
		QFunction bestNextQ = findSAPair(lastStateAction.getState().nextState(lastStateAction.getAction(), user, nextWeather), bestAction(lastStateAction.getState().nextState(lastStateAction.getAction(), user, nextWeather), user));
		QReward = lastQ.getReward() + learningRate*(reward + discountFactor*bestNextQ.getReward() - lastQ.getReward());
//		System.out.println(QReward + " = " + lastQ.getReward() + " + " + learningRate + "(" + reward + " + " + discountFactor + "x" + bestNextQ.getReward() + " - " + lastQ.getReward() + ")");
		updateQ(lastStateAction.getState(), lastStateAction.getAction(), QReward);
	}
}
