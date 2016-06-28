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
		
		users[0] = new User(1, 5, new Car(4));
		users[1] = new User(2, 2, new Car(1));
		users[2] = new User(3, 3);
		users[3] = new User(4, 1);
		users[4] = new User(4, 4);
		
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
			users[i].resetAuction();
			users[i].generatePreferences(t);
		}
	}
	public static void endOfDay (User[] users) {
		for (int i = 0; i < users.length; i++) {
			users[i].useElectricity();
			users[i].resetExpense();
		}
	}
}
