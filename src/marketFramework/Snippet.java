/**
 * 
 */
package marketFramework;

import java.math.BigDecimal;
import java.math.RoundingMode;
import userSimulation.Car;
import userSimulation.User;
/**
 * @author pa1g15
 *
 */
public class Snippet {
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	public static Car[] createCars() {
		Car[] cars = new Car[Market.TOTAL_CAR_TYPE];
		
		cars[0] = new Car(1);
		cars[1] = new Car(2);
		cars[2] = new Car(3);
		cars[3] = new Car(4);
		cars[4] = new Car(5);
		cars[5] = new Car(6);
		cars[6] = new Car(7);
		
		return cars;
	}
	public static User[] createUsers(){
		User[] users = new User[Market.AUCTION_PARTICIPANT];
		Car[] cars = new Car[Market.TOTAL_CAR_TYPE];
		
		cars = createCars();						
		users[0] = new User(40, 5, cars[6]);
		users[1] = new User(45, 7.1, cars[6]);
		users[2] = new User(55, 9.44, cars[1]);
		users[3] = new User(42, 11.68, cars[5]);
		users[4] = new User(57, 11.68, cars[5]);
		users[5] = new User(51, 13.9, cars[1]);
		users[6] = new User(64, 13.9, cars[3]);
		users[7] = new User(72, 13.9, cars[2]);
		users[8] = new User(68, 16.3, cars[3]);
		users[9] = new User(43, 16.3, cars[3]);
		users[10] = new User(73, 16.3, cars[2]);
		users[11] = new User(61, 18.6, cars[1]);
		users[12] = new User(63, 18.6, cars[2]);
		users[13] = new User(65, 20.8, cars[0]);
		users[14] = new User(70, 24.5, cars[4]);
		users[15] = new User(48, 24.5, cars[4]);
		users[16] = new User(66, 24.5, cars[0]);
		users[17] = new User(80, 24.5, cars[0]);
		users[18] = new User(32, 24.5, cars[0]);
		users[19] = new User(53, 24.5, cars[1]);
		users[20] = new User(61, 11.68, cars[6]);
		users[21] = new User(77, 13.9, cars[1]);
		users[22] = new User(41, 18.6, cars[1]);
		users[23] = new User(55, 20.8, cars[3]);
		users[24] = new User(60, 24.5, cars[3]);
		
		return users;
	}
}
