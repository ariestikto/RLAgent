/**
 * 
 */
package marketFramework;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.apache.commons.math3.distribution.NormalDistribution;
import userSimulation.Car;
import userSimulation.User;
/**
 * @author pa1g15
 *
 */
public class Snippet {
	public static double round(double value) {

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(2, RoundingMode.HALF_UP);
	    if (bd.doubleValue() > 0.01) {
	    	return bd.doubleValue();
		} else {
			return 0;
		}
	    
	}
	public static User[] createUsers() {
		User[] users = new User[Market.AUCTION_PARTICIPANT];
		
		users[0] = new User(2, 5, new Car(3));
		users[1] = new User(6, 2);
		users[2] = new User(3, 3);
		users[3] = new User(4, 1);
		users[4] = new User(1, 3, new Car(2));
		users[5] = new User(1, 2, new Car(5));
		users[6] = new User(2, 1, new Car(6));
		users[7] = new User(4, 4);
		users[8] = new User(4, 2);
		users[9] = new User(2, 2, new Car(3));
		return users;
	}
	public static double normDist(double mean) {
		if (mean > 0) {
			NormalDistribution dist = new NormalDistribution(mean, 1);
			return round(dist.sample());
		} else {
			return 0;
		}
	}
	public static void startOfDay(User[] users, Time t) {
		for (int i = 0; i < users.length; i++) {
			users[i].resetExpense();
			users[i].resetAuction();
			users[i].generatePreferences(t);
			if (users[i].getStrategy() == 5) {
				users[i].getAgent().takeAction(users[i], t);
			}
		}
	}
	public static void endOfDay (User[] users, double reward, Time t) {
		for (int i = 0; i < users.length; i++) {
			users[i].useElectricity();
			if (users[i].getStrategy() == 5) {
				users[i].getAgent().evaluateAction(users[i], t.getNextWeather());
//				System.out.println("SA Pair: " + users[i].getAgent().getQSize());
			}
		}
	}
	public static double agentPerformance (User agent) {
		double electricityAccuracy = 0;
		double spendingAccuracy = 0;
		double performance = 0;
		
		if (agent.getDailyNeeds() < agent.getCurrentElectricity()) {
			electricityAccuracy = 1;
		} else {
			electricityAccuracy = agent.getCurrentElectricity()/agent.getDailyNeeds();
		}
		if (agent.getBudget() > agent.payout()) {
			spendingAccuracy = 1;
		} else {
			spendingAccuracy = agent.getBudget()/agent.payout();
		}
		
		performance = electricityAccuracy*spendingAccuracy;
		return performance;
	}
}
