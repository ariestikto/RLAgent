/**
 * 
 */
package agent.learningAgent;

import java.util.List;

import marketFramework.Market;
import java.util.ArrayList;
import userSimulation.User;
/**
 * @author pa1g15
 *
 */
public class ReinforcementLearning {

	private List<QFunction> Q;
	private double learningRate;
	private double discountFactor;
	private double epsilon;
	
	//	RL agent with e-greedy policy
	public ReinforcementLearning(double learningRate, double discountFactor, double epsilon) {
		this.learningRate = learningRate;
		this.discountFactor = discountFactor;
		this.epsilon = epsilon;
		this.Q = new ArrayList<QFunction>();
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

	public void addQVal(QFunction Q, int value) {
		Q.setValue(value);
		this.Q.add(Q);
	}
	
	public int getQSize() {
		return Q.size();
	}
	
	public Action bestAction(State s, User user) {
		Action a = randomAction(s, user);
		int value = -999;
		if (Q.size() > 0) {
			for (QFunction temp : Q) {
				if (s.isEqual(temp.getState())) {
					if (temp.getValue() > value) {
						a = temp.getAction();
					}
				}
		    }
		}
		return a;
	}
	
	public QFunction findState(State s, Action a) {
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
	
	public void updateQVal(State s, Action a, double value) {
		for (QFunction temp : Q) {
			if (s.isEqual(temp.getState()) && a.isEqual(temp.getAction())) {
				temp.setValue(value);
			}
		}
	}
	
	public Action randomAction(State s, User user) {
		Action a = new Action();
		int bid = (int) (0 + (Math.random() * ((user.getCar().getBatteryCapacity() - 0) + 1)));
		int unitBudget = (int) (5 + (Math.random() * ((Market.MAXIMUM_BID - 5) + 1)));
		if ((bid - user.getCurrentElectricity()) > 0) {
			bid = (int) (bid-user.getCurrentElectricity());
			a.setBidAmount(bid);
		} else {
			unitBudget = 0;
			a.setBidAmount(0);
		}
		a.setBudget(bid*unitBudget);
		return a;
	}
}
