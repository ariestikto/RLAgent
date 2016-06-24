/**
 * 
 */
package agent;

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
	
	public ReinforcementLearning() {
		this.Q = new ArrayList<QFunction>();
	}
	
	public void addQVal(QFunction Q, int value) {
		Q.setValue(value);
		this.Q.add(Q);
	}
	
	public Action bestAction(State s) {
		Action a = new Action();
		int value = 0;
		for (QFunction temp : Q) {
			if (s.isEqual(temp.getState())) {
				if (temp.getValue() > value) {
					a = temp.getAction();
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
	
	public Action randomAction(State s, User user) {
		Action a = new Action();
		double bid = 0 + (Math.random() * ((user.getCar().getBatteryCapacity() - 0) + 1));
		double unitBudget = 5 + (Math.random() * ((Market.MAXIMUM_BID - 5) + 1));
		a.setBidAmount(bid);
		a.setBudget(bid*unitBudget);
		return this.findState(s, a).getAction();
	}
}
