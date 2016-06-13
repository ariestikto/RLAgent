/**
 * 
 */
package marketFramework;

import auctionSimulation.Auction;
import userSimulation.User;
/**
 * @author pa1g15
 *
 */
public class Market {
	public static final double START_PRICE = 5; // p/kWh
	public static final double ELECTRICITY_SUPPLY = 50; // kWh
	public static final int AUCTION_PARTICIPANT = 5;
	public static final int TOTAL_CAR_TYPE = 7;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		User[] users = new User[AUCTION_PARTICIPANT];
		Auction a = new Auction(ELECTRICITY_SUPPLY, START_PRICE);
		Time t = new Time();
		
		users = Snippet.createUsers();
		a.runAuction(users, t);
	}
}
