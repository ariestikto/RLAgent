/**
 * 
 */
package marketFramework;
import userSimulation.User;
/**
 * @author pa1g15
 *
 */
public class Time {
	private int day;
	
	public Time() {
		this.day = 1;
	}	
	public int getDay() {
		return day;
	}

	public void advanceTime(User[] users) {
			this.day += 1;
	}
}
