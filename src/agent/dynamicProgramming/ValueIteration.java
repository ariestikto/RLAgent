package agent.dynamicProgramming;

import java.util.ArrayList;
import java.util.List;

import agent.State;

public class ValueIteration {

	private double theta;
	private List<ValueFunction> stateValue;
	
	public ValueIteration(double theta) {
		this.theta = theta;
		assignStateValue();
		this.stateValue = new ArrayList<ValueFunction>();
	}
	
	public void assignStateValue() {
		String dayName = "";
		// 7 day, 3 weather, 5 Electricity level
		for (int day = 0; day < 7; day++) {
			for (int weather = 0; weather < 3; weather++) {
				for (int ELLevel = 0; ELLevel < 5; ELLevel++) {
					switch (day) {
					case 0:
						dayName = "Sunday";
						break;
					case 1:
						dayName = "Monday";
						break;
					case 2:
						dayName = "Tuesday";
						break;
					case 3:
						dayName = "Wednesday";
						break;
					case 4:
						dayName = "Thursday";
						break;
					case 5:
						dayName = "Friday";
						break;
					case 6:
						dayName = "Saturday";
						break;
					}
					State s = new State();
					s.setState(dayName, weather, ELLevel);
					ValueFunction Vs = new ValueFunction(s);
					this.stateValue.add(Vs);
				}
			}
		}
	}
	
	public void evaluateState() {
		double delta = 0;
		
	}
}
