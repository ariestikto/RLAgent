 /**
 * 
 */
package agent.qLearning;

import java.util.List;

import agent.Action;
import agent.Reward;
import agent.State;

import java.util.ArrayList;
import userSimulation.User;
import userSimulation.UserSatisfaction;
import marketFramework.Parameter;
import marketFramework.Time;
/**
 * @author pa1g15
 *
 */
public class QLearning {

	private List<QFunction> Q;
	private double rewardSignal = 0;
	private double learningRate;
	private double discountFactor;
	private double epsilon;
	private QFunction lastStateAction;
	private QFunction bestStateAction;
	private boolean best = false;
	
	//	RL agent with e-greedy policy
	public QLearning(double learningRate, double discountFactor, double epsilon) {
		this.learningRate = learningRate;
		this.discountFactor = discountFactor;
		this.epsilon = epsilon;
		this.Q = new ArrayList<QFunction>();
	}
	
	public double getRewardSignal() {
		return rewardSignal;
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
	
	public QFunction getLastStateAction() {
		return lastStateAction;
	}
	
	public QFunction getBestStateAction() {
		return bestStateAction;
	}
	public Action bestAction(State s, User user) {
		Action a = randomAction(s, user);
		double reward = 0;
		if (Q.size() > 0) {
			for (QFunction temp : Q) {
				if (s.isEqual(temp.getState())) {
//					System.out.println(temp.getReward());
					if ((temp.getReward() > reward) || ((temp.getReward() == reward) && (Math.random() > 0.5))) {
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
		int amountLevel = 1 + (int)(Math.random() * (((6-s.getElectricityLevel()) - 1) + 1));
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
			
			if (currentLevel > 0.99) {
				s.setState(t.getDayName(), t.getWeather(), 5);
			} else if (currentLevel > 0.75) {
				s.setState(t.getDayName(), t.getWeather(), 4);
			} else if (currentLevel > 0.5) {
				s.setState(t.getDayName(), t.getWeather(), 3);
			} else if (currentLevel > 0.25) {
				s.setState(t.getDayName(), t.getWeather(), 2);
			} else {
				s.setState(t.getDayName(), t.getWeather(), 1);
			}
			
			// epsilon greedy algorithm
			// =========================
			if (Math.random() > epsilon) {
				a = bestAction(s, user);
				best = true;
//				System.out.println("BEST==============================");
			} else {
				a = randomAction(s, user);
				best = false;
//				System.out.println("RANDOM==============================");
			}
			// ===============================
			this.lastStateAction = findSAPair(s, a);
			this.bestStateAction = findSAPair(s, bestAction(s, user));
			if (best) {
				this.bestStateAction = lastStateAction;
			}
			
			
			
			
	}
	public void evaluateAction(User user, int nextWeather) {
		double QReward = 0;
		int autoReward = 0;
		UserSatisfaction satisfactionLevel = new UserSatisfaction();
		satisfactionLevel.generateFeedbackPatternPH(user);
//		switch (Parameter.PATTERN) {
//		case 1:
//			satisfactionLevel.generateFeedbackPattern_1(user);
//			break;
//		case 2:
//			satisfactionLevel.generateFeedbackPattern_2(user);
//			break;
//		case 3:
//			satisfactionLevel.generateFeedbackPattern_3(user);
//			break;
//		case 4:
//			satisfactionLevel.generateFeedbackPattern_4(user);
//			break;
//		case 5:
//			satisfactionLevel.generateFeedbackPattern_5(user);
//			break;
//		}
		autoReward = (int) user.payout()/30;
		this.rewardSignal = -(Parameter.AUTO_REWARD*autoReward) + Reward.RewardPatternMixed(satisfactionLevel);
//		System.out.println("Reward: " + -autoReward + " + " + Reward.RewardPatternMixed(satisfactionLevel));
//		this.rewardSignal = Reward.RealRewardPattern(user);
		QFunction lastQ = findSAPair(lastStateAction.getState(), lastStateAction.getAction());
		QFunction bestNextQ = findSAPair(lastStateAction.getState().nextState(user, nextWeather), bestAction(lastStateAction.getState().nextState(user, nextWeather), user));
		QReward = lastQ.getReward() + learningRate*(rewardSignal + discountFactor*bestNextQ.getReward() - lastQ.getReward());
//		System.out.println(QReward + " = " + lastQ.getReward() + " + " + learningRate + "(" + rewardSignal + " + " + discountFactor + "x" + bestNextQ.getReward() + " - " + lastQ.getReward() + ")");
		updateQ(lastStateAction.getState(), lastStateAction.getAction(), QReward);
	}
}
