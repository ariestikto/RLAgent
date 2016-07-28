package agent.dynamicProgramming;

import java.util.ArrayList;
import java.util.List;

import agent.Action;
import agent.State;
import marketFramework.Time;
import userSimulation.User;

public class ProbabilityDatabase {
	
	private List<ProbabilityTable> probabilityTable;

	public ProbabilityDatabase() {
		this.probabilityTable = new ArrayList<ProbabilityTable>();
	}
	
	public boolean isExist(ProbabilityTable table) {
		for (ProbabilityTable temp : probabilityTable) {
			if (table.isEqual(temp)) {
				return true;
			}
		}
		return false;
	}
	
	public void updateEntry(ProbabilityTable table) {
		if (isExist(table)) {
			for (ProbabilityTable temp : probabilityTable) {
				if (table.isEqual(temp)) {
					temp.addProbability();
				}
			}
		} else {
			this.probabilityTable.add(table);
		}
	}
	
	public double findProbability(State state, Action action, int reward, State nextState) {
		ProbabilityTable table = new ProbabilityTable(state, action, reward, nextState);
		for (ProbabilityTable temp : probabilityTable) {
			if (table.isEqual(temp)) {
				table = temp;
			}
		}
		return table.getProbability();
	}
	
	public void generateDatabase(User EVuser) {
		State s = new State();
		Time t = new Time();
		double electricity = 0;
		// 7 day, 3 weather, 5 Electricity level
		for (int day = 0; day < 7; day++) {
			for (int weather = 0; weather < 3; weather++) {
				for (int ELLevel = 0; ELLevel < 5; ELLevel++) {
					switch (ELLevel) {
					case 0:
						electricity = 0;
						break;
					case 1:
						electricity = 0.25;					
						break;
					case 2:
						electricity = 0.5;
						break;
					case 3:
						electricity = 0.75;
						break;
					case 4:
						electricity = 1;
						break;
					}
					electricity *= EVuser.getCar().getBatteryCapacity();
					t.resetTime(day, weather);
					s.setState(t.getDayName(), t.getWeather(), (ELLevel+1));
					EVuser.addElectricity(electricity);
				}
			}
		}
	}
}
