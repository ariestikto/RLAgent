package agent.dynamicProgramming;

import agent.State;

public class ValueFunction {
	
	private State state;
	private double value;
	
	public ValueFunction(State state) {
		this.state = state;
		this.value = 0;
	}
	
	public State getState() {
		return state;
	}
	
	public void setState(State state) {
		this.state = state;
	}
	
	public double getValue() {
		return value;
	}
	
	public void setValue(double value) {
		this.value = value;
	}
	
	public boolean isEqual(State s) {
		return state.isEqual(s);
	}
}
