/**
 * 
 */
package marketFramework;

import java.awt.TextArea;

import auctionSimulation.Auction;
import userSimulation.User;
/**
 * @author pa1g15
 *
 */
public class Market {
	public static final double START_PRICE = 5; // p/kWh
	public static final double ELECTRICITY_SUPPLY = 60; // kWh
	public static final int AUCTION_PARTICIPANT = 5;
	public static final int TOTAL_CAR_TYPE = 7;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		User[] users = new User[AUCTION_PARTICIPANT];
		Auction a = new Auction();
		Time t = new Time();
		
		users = Snippet.createUsers();
		for (int i = 0; i < 7; i++) {
			System.out.println("Day: " + (i+1) + ", " + t.getDayName() + ", Weather: " + t.getWeather());
			Snippet.startOfDay(users, t);
//			a.runAuction(users, t);
			Snippet.endOfDay(users);
			t.advanceTime();
		}
	}
}
