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
	public static User[] createUsers_old() {
		User[] users = new User[Parameter.AUCTION_PARTICIPANT];
		
		users[0] = new User(1, 5, new Car(3));
		users[1] = new User(7, 2);
		users[2] = new User(3, 3);
		users[3] = new User(4, 1);
		users[4] = new User(1, 3, new Car(2));
		users[5] = new User(1, 2, new Car(5));
		users[6] = new User(1, 1, new Car(6));
		users[7] = new User(4, 4);
		users[8] = new User(4, 2);
		users[9] = new User(1, 2, new Car(3));
		users[10] = new User(1, 2, new Car(1));
		users[11] = new User(3, 2);

		return users;
	}
	public static User[] testCaseUsers() {
		User[] users = new User[Parameter.AUCTION_PARTICIPANT];
		
		users[0] = new User(6, 5, new Car(3));
		users[1] = new User(7, 4);
		
		return users;
	}
	public static User[] createUsers() {
		User[] users = new User[Parameter.AUCTION_PARTICIPANT];
		
		users[0] = new User(8, 5, new Car(5));
		users[1] = new User(11, 2.4, 3.7, new Car(1));
		users[2] = new User(11, 5.1, 4.1, new Car(2));
		users[3] = new User(11, 1.38, 1.5, new Car(4));
		users[4] = new User(11, 4.1, 6.4, new Car(5));
		users[5] = new User(11, 17.01, 16.94, new Car(6));
		users[6] = new User(11, 17.62, 20.23, new Car(7));
		users[7] = new User(11, 12.8, 12.93, new Car(3));
		users[8] = new User(11, 5.43, 7.41, new Car(2));
		users[9] = new User(11, 7.94, 7.27, new Car(5));
		
		return users;
	}
	public static User[] DBBuilderUsers() {
		User[] users = new User[Parameter.AUCTION_PARTICIPANT];
		
		users[0] = new User(1, 6, new Car(3));
		users[1] = new User(7, 2);
		users[2] = new User(3, 3);
		users[3] = new User(4, 1);
		users[4] = new User(1, 3, new Car(2));
		users[5] = new User(1, 2, new Car(5));
		users[6] = new User(1, 1, new Car(6));
		users[7] = new User(4, 4);
		users[8] = new User(4, 2);
		users[9] = new User(1, 2, new Car(3));
		users[10] = new User(1, 2, new Car(1));
		users[11] = new User(3, 2);

		return users;
	}
	public static double normDist(double mean) {
		double sample = 0;
		if (mean > 0) {
			NormalDistribution dist = new NormalDistribution(mean, 1);
			while (sample <= 0) {
				sample = round(dist.sample());
			}
			return sample;
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
//				System.out.println(users[i].getUID() + ": "+ users[i].getDailyNeeds());
				users[i].getAgent().takeAction(users[i], t);
			}
		}
	}
	public static void endOfDay (User[] users, Time t) {
		for (int i = 0; i < users.length; i++) {
			users[i].useElectricity();
			if (users[i].getStrategy() == 5) {
				users[i].getAgent().evaluateAction(users[i], t.getNextWeather());
			}
		}
	}
	public static void endOfSimulation (User[] users) {
		for (int i = 0; i < users.length; i++) {
			users[i].useElectricity();
		}
	}
	public static void resetUser (User[] users) {
		for (int i = 0; i < users.length; i++) {
			users[i].resetElectricity();
			users[i].resetExpense();
		}
	}
}
