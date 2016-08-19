package agent.dynamicProgramming;

import java.util.ArrayList;
import java.util.List;

import agent.Action;
import agent.Reward;
import agent.State;
import auctionSimulation.Auction;
import marketFramework.Snippet;
import marketFramework.Time;
import userSimulation.User;
import userSimulation.UserSatisfaction;

public class ProbabilityDatabase {
	
	private List<ProbabilityTable> probabilityTable;
	private int trial;
	
	public ProbabilityDatabase(int trial) {
		this.probabilityTable = new ArrayList<ProbabilityTable>();
		this.trial = trial;
	}
	
	public List<ProbabilityTable> getTable() {
		return probabilityTable;
	}
	
	public int getTrial() {
		return trial;
	}
	
	public boolean isExist(ProbabilityTable entry) {
		for (ProbabilityTable temp : probabilityTable) {
			if (entry.isEqual(temp)) {
				return true;
			}
		}
		return false;
	}
	
	public void updateEntry(ProbabilityTable entry) {
		if (isExist(entry)) {
			for (ProbabilityTable temp : probabilityTable) {
				if (entry.isEqual(temp)) {
					temp.addProbability(trial);
				}
			}
		} else {
			this.probabilityTable.add(entry);
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
	
	public void generateDatabase(User[] users) {
		State state;
		State nextState;
		Action action;
		Time time = new Time();
		Auction auction = new Auction();
		ProbabilityTable newDBEntry;
		UserSatisfaction satisfactionLevel = new UserSatisfaction();
		User EVuser = users[0];
		double reward = 0;
		double electricity = 0;
		for (int i = 0; i < users.length; i++) {
			if (users[i].getStrategy() == 6) {
				EVuser = users[i]; 
			}
		}
		// Search state space
		for (int day = 0; day < 7; day++) {
			for (int weather = 0; weather < 3; weather++) {
				for (int ELLevel = 0; ELLevel < 5; ELLevel++) {
					state = new State();
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
					time.resetTime(day+1, weather);
					state.setState(time.getDayName(), time.getWeather(), (ELLevel+1));
					EVuser.resetElectricity();
					EVuser.addElectricity(electricity);
					
					// Search action space
					for (int addedEL = 0; addedEL < 5; addedEL++) {
						if (addedEL + ELLevel < 6) { // valid action
							for (int budgetLevel = 0; budgetLevel < 4; budgetLevel++) {
								for (int i = 0; i < trial; i++) {
									action = new Action();
									action.setAction(addedEL, budgetLevel);
									
									//Simulate day
									Snippet.startOfDay(users, time);
									auction.simulationAuction(users, time, action);
									Snippet.endOfSimulation(users);
									
									nextState = state.nextState(EVuser, time.getNextWeather());
									satisfactionLevel.generateFeedbackPattern_7(EVuser);
									reward = Reward.RewardPatternSlider(satisfactionLevel);
									newDBEntry = new ProbabilityTable(state, action, reward, nextState);
									updateEntry(newDBEntry);
									
									for (int j = 0; j < users.length; j++) {
										users[j].resetElectricity();
										if (users[j].getStrategy() == 6) {
											EVuser = users[j]; 
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
