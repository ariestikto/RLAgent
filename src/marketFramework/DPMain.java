package marketFramework;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import agent.dynamicProgramming.ProbabilityDatabase;
import agent.dynamicProgramming.ValueIteration;
import auctionSimulation.Auction;
import userSimulation.User;

public class DPMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int timeJump = 500;
		User[] users = Snippet.DBBuilderUsers();
		User agent = users[0];
		Auction a = new Auction();
		Time t = new Time();
		double reward = 0;
		double lastTenReward = 0;
		List<Double> rewards = new ArrayList<Double>();
		List<Double> trends = new ArrayList<Double>();
		String fileName = "Avg_Performance (Value Iteration) (0.001, 0.9).xls";
		int data = 1000;
		ProbabilityDatabase DB = new ProbabilityDatabase(500);
		ValueIteration DPAgent = new ValueIteration(0.001, 0.9);
		
		DB.generateDatabase(users);
		System.out.println("Probability Database Generated");
		DPAgent.evaluateState(users, DB);
		System.out.println("State Evaluated");
		for (int row = 0; row < data; row++) {
			rewards = new ArrayList<Double>();
			trends = new ArrayList<Double>();
			reward = 0;
			lastTenReward = 0;
			t = new Time();
			users = Snippet.DBBuilderUsers();
			for (int i = 0; i < users.length; i++) {
				if (users[i].getStrategy() == 6) {
					agent = users[i];
				}
			}
			for (int i = 0; i < timeJump; i++) {
				t.advanceTime();
				Snippet.startOfDay(users, t);
				a.runDPAuction(users, t, DB, DPAgent);
				Snippet.endOfDay(users, t);
				reward = agent.getPerformance().normalizedScore();
				rewards.add(reward);
				
				lastTenReward = 0;
				if (t.getDay() > 6) {
					for (int j = 1; j < 8; j++) {
						lastTenReward += rewards.get(rewards.size() - j);
					}
					rewards.remove(0);
					trends.add(lastTenReward/7);
				}
			}
			try {
	            // Assume default encoding.
	            FileWriter fileWriter =
	                new FileWriter(fileName, true);

	            // Always wrap FileWriter in BufferedWriter.
	            BufferedWriter bufferedWriter =
	                new BufferedWriter(fileWriter);

	            // Note that write() does not automatically
	            // append a newline character.
	            
	            for (Double temp : trends) {
	            	bufferedWriter.write(temp + "\t");
				}
	            bufferedWriter.write("\n");
	            // Always close files.
	            bufferedWriter.close();
	        } catch(IOException ex) {}
		}
	}
}
