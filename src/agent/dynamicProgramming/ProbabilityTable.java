package agent.dynamicProgramming;

import agent.Action;
import agent.State;

public class ProbabilityTable {

	private State state;
	private Action action;
	private double reward;
	private State nextState;
	private double probability;
	
	public ProbabilityTable(State state, Action action, double reward, State nextState) {
		this.state = state;
		this.action = action;
		this.reward = reward;
		this.nextState = nextState;
		this.probability = 0;
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

	public State getNextState() {
		return nextState;
	}

	public double getProbability() {
		return probability;
	}

	public void addProbability(int trial) {
		this.probability += 1;
	}
	
	public boolean isEqual(ProbabilityTable table) {
		boolean equal = (state.isEqual(table.getState())) && (action.isEqual(table.getAction())) && (reward == table.getReward()) && (nextState.isEqual(table.getNextState()));
		return equal;
	}
}
