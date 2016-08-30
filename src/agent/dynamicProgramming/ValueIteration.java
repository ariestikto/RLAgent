package agent.dynamicProgramming;

import java.util.ArrayList;
import java.util.List;

import agent.Action;
import agent.State;
import marketFramework.Time;
import userSimulation.User;

public class ValueIteration {

	private double theta;
	private double discountFactor;
	private List<ValueFunction> stateValue;
	
	public ValueIteration(double theta, double discountFactor) {
		this.theta = theta;
		this.discountFactor = discountFactor;
		this.stateValue = new ArrayList<ValueFunction>();
		assignStateValue();
	}
	
	public void assignStateValue() {
		Time time = new Time();
		State s;
		// 7 day, 3 weather, 5 Electricity level
		for (int day = 0; day < 7; day++) {
			for (int weather = 0; weather < 3; weather++) {
				for (int ELLevel = 0; ELLevel < 5; ELLevel++) {
					s = new State();
					time.resetTime(day+1, weather);
					s.setState(time.getDayName(), time.getWeather(), (ELLevel+1));
					ValueFunction Vs = new ValueFunction(s);
					this.stateValue.add(Vs);
				}
			}
		}
	}
	
	public void updateStateValue(State s, double value) {
		for(ValueFunction temp : stateValue) {
			if (s.isEqual(temp.getState())) {
				temp.setValue(value);
			}
		}
	}
	
	public ValueFunction findStateValue(State s) {
		ValueFunction Vs = new ValueFunction(s);
		for(ValueFunction temp : stateValue) {
			if (s.isEqual(temp.getState())) {
				Vs = temp;
			}
		}
		return Vs;
	}
	
	public void evaluateState(User[] users, ProbabilityDatabase DB) {
		double delta = 0;
		Time time = new Time();
		State state;
		Action action;
		double value = 0;
		double maxValue = -9999999;
		double currentValue = 0;
		
		do {
			delta = 0;
			// Search state space
			for (int day = 0; day < 7; day++) {
				for (int weather = 0; weather < 3; weather++) {
					for (int ELLevel = 0; ELLevel < 5; ELLevel++) {
						state = new State();
						maxValue = 0;
						time.resetTime(day+1, weather);
						state.setState(time.getDayName(), time.getWeather(), (ELLevel+1));
						currentValue = findStateValue(state).getValue();
						// Search action space
						for (int addedEL = 0; addedEL < 5; addedEL++) {
							if (addedEL + ELLevel < 6) { // valid action
								for (int budgetLevel = 0; budgetLevel < 4; budgetLevel++) {
									action = new Action();
									action.setAction(addedEL, budgetLevel);
									
									for (ProbabilityTable temp : DB.getTable()) {
										if (state.isEqual(temp.getState()) && action.isEqual(temp.getAction())) {
											value += (temp.getProbability()/DB.getTrial())*(temp.getReward() + (discountFactor * findStateValue(temp.getNextState()).getValue()));
										}
									}
									if (maxValue < value) {
										maxValue = value;
									}
									value = 0;
								}
							}
						}
						updateStateValue(state, maxValue);
						delta = Math.max(delta, Math.abs(currentValue - maxValue));
					}
				}
			}
		} while (delta > theta);
	}
	
	public void checkEntryValue() {
		for (ValueFunction temp : stateValue) {
			System.out.println(temp.getValue());
		}
	}
	
	public Action bestAction(State state, ProbabilityDatabase DB) {
		// Search action space
		double maxValue = -9999999;
		int ELLevel = state.getElectricityLevel()-1;
		Action action;
		Action bestAction = new Action();
		double value = 0;
		
		for (int addedEL = 0; addedEL < 5; addedEL++) {
			if (addedEL + ELLevel < 6) { // valid action
				for (int budgetLevel = 0; budgetLevel < 4; budgetLevel++) {
					action = new Action();
					action.setAction(addedEL, budgetLevel);
					
					for (ProbabilityTable temp : DB.getTable()) {
						if (state.isEqual(temp.getState()) && action.isEqual(temp.getAction())) {
							value += (temp.getProbability()/DB.getTrial())*(temp.getReward() + (discountFactor * findStateValue(temp.getNextState()).getValue()));
						}
					}
					if (maxValue < value) {
						maxValue = value;
						bestAction = action;
					}
					value = 0;
				}
			}
		}
		return bestAction;
	}
}
