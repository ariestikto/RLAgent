/**
 * 
 */
package agent.reinforcementLearning;

import agent.Action;
import agent.State;

/**
 * @author pa1g15
 *
 */
public class QFunction {

	/**
	 * 
	 */
	private State state;
	private Action action;
	private double reward;
	
	public QFunction(State state, Action action) {
		this.state = state;
		this.action = action;
		this.reward = 0;
	}
	
	public QFunction(State state, Action action, double reward) {
		this.state = state;
		this.action = action;
		this.reward = reward;
	}
	
	public State getState() {
		return state;
	}

	public Action getAction() {
		return action;
	}
	
	public double getReward() {
		return reward;
	}

	public void setReward(double reward) {
		this.reward = reward;
	}
}
