package agent.dynamicProgramming;

import java.util.ArrayList;
import java.util.List;

import agent.Action;
import agent.State;

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
}
