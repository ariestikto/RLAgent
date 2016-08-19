package marketFramework;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import auctionSimulation.Auction;
import userSimulation.Car;
import userSimulation.User;

public class QLearningMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (args.length >= 1)
			Parameter.FEEDBACK_FREQUENCY = Double.parseDouble(args[0]);
//			Parameter.ELECTRICITIY_REWARD_MULTIPLIER = Integer.parseInt(args[0]);
//		if (args.length >= 2)
//			Parameter.PAYOUT_REWARD_MULTIPLIER = Integer.parseInt(args[1]);
//		if (args.length >= 3)
//			Parameter.PATTERN = Short.parseShort(args[2]);
//		if (args.length >= 4)
//			Parameter.EPSILON_PARAMETER = Double.parseDouble(args[3]);
		int timeJump = 500;
		User[] users = Snippet.createUsers();
		User[] copyUsers = new User[Parameter.AUCTION_PARTICIPANT];
		User agent = users[0];
		Auction a = new Auction();
		Time t = new Time();
		double reward = 0;
		double lastTenReward = 0;
		double bestReward = 0;
		double bestLastTenReward = 0;
		List<Double> rewards = new ArrayList<Double>();
		List<Double> trends = new ArrayList<Double>();
		List<Double> bestRewards = new ArrayList<Double>();
		List<Double> bestTrends = new ArrayList<Double>();
//		String fileName = "Avg_Performance (" + Parameter.ELECTRICITIY_REWARD_MULTIPLIER + ", " + Parameter.PAYOUT_REWARD_MULTIPLIER + ").xls";
		String fileName = "Avg_Performance (" + Parameter.FEEDBACK_FREQUENCY + ").xls";
//		String fileName = "Random_Performance.xls";
//		String fileName = "Test.xls";
		String fileName2 = "Avg_BestQPerformance.xls";
		int data = 1000;
		boolean comparator = false;
		
		for (int row = 0; row < data; row++) {
			rewards = new ArrayList<Double>();
			trends = new ArrayList<Double>();
			reward = 0;
			lastTenReward = 0;
			
			if (comparator) {
				bestRewards = new ArrayList<Double>();
				bestTrends = new ArrayList<Double>();
				bestReward = 0;
				bestLastTenReward = 0;
			}
			t = new Time();
			users = Snippet.createUsers();
			User bestAgent = new User(1, 5, new Car(3));
			for (int i = 0; i < users.length; i++) {
				if (users[i].getStrategy() == 5) {
					agent = users[i];
				}
				if (comparator) {
					copyUsers[i] = users[i];
				}
			}
			if (comparator) {
				bestAgent.copyUser(agent);
			}
			
			
			for (int i = 0; i < timeJump; i++) {
				t.advanceTime();
				Snippet.startOfDay(users, t);

				if (comparator) {
					bestAgent.copyUser(agent);
					bestAgent.getPerformance().setMaxSpending(30*(bestAgent.getCar().getBatteryCapacity()-bestAgent.getCurrentElectricity()));
					for (int j = 0; j < copyUsers.length; j++) {
						if (copyUsers[j].getStrategy() == 5) {
							copyUsers[j] = bestAgent;
						}
					}
					a.runBackgroundAuction(copyUsers, t);
					bestAgent.useElectricity();
				}
				
				a.runFastAuction(users, t);
				
				Snippet.endOfDay(users, t);
				
				reward = agent.getPerformance().normalizedScore();
//				System.out.println(reward);
				rewards.add(reward);
				
				if (comparator) {
					bestReward = bestAgent.getPerformance().normalizedScore();
//					System.out.println(bestReward);
//					System.out.println("======================");
					bestRewards.add(bestReward);
				}
				
				lastTenReward = 0;
				bestLastTenReward = 0;
				if (t.getDay() > 6) {
					for (int j = 1; j < 8; j++) {
						lastTenReward += rewards.get(rewards.size() - j);
						if (comparator) {
							bestLastTenReward += bestRewards.get(bestRewards.size() - j);
						}
					}
					rewards.remove(0);
					trends.add(lastTenReward/7);
					if (comparator) {
						bestRewards.remove(0);
						bestTrends.add(bestLastTenReward/7);
					}
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
			if (comparator) {
				try {
		            // Assume default encoding.
		            FileWriter fileWriter =
		                new FileWriter(fileName2, true);

		            // Always wrap FileWriter in BufferedWriter.
		            BufferedWriter bufferedWriter =
		                new BufferedWriter(fileWriter);

		            // Note that write() does not automatically
		            // append a newline character.
		            
		            for (Double temp : bestTrends) {
		            	bufferedWriter.write(temp + "\t");
					}
		            bufferedWriter.write("\n");
		            // Always close files.
		            bufferedWriter.close();
		        } catch(IOException ex) {}
			}
		}
		System.out.println("=========================FINISHED====================================");
	}

}
