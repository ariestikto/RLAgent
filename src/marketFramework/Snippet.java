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
	    return bd.doubleValue();
	}
	public static User[] createUsers() {
		User[] users = new User[Market.AUCTION_PARTICIPANT];
		
		users[0] = new User(73, 16.3, 1, 2, new Car(4));
		users[1] = new User(80, 24.5, 2, 3, new Car(1));
		users[2] = new User(30, 50, 2, 3);
		users[3] = new User(10, 8, 3, 1);
		users[4] = new User(4, 7, 3, 4);
		
		return users;
	}
	public static double normDist(double mean) {
		NormalDistribution dist = new NormalDistribution(mean, 1);
		return round(dist.sample());
	}
}
