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
	private String dayName;
	private int weather;
	
	public Time() {
		this.day = 1;
		this.dayName = "Monday";
		this.weather = 1;
		setWeather();
	}	
	public int getDay() {
		return day;
	}
	public String getDayName() {
		return dayName;
	}
	
	public void setWeather() {
		int random = 1 + (int)(Math.random() * ((100 - 1) + 1));
		if (random < 81) {
			// 1 = sunny weather
			this.weather = 1;
		} else if (random < 96) {
			// 2 = rainy weather
			this.weather = 2;
		} else if (random < 98) {
			// 3 = foggy weather
			this.weather = 3;
		} else {
			// 4 = snow weather
			this.weather = 4;
		}
	}
	public void advanceTime(User[] users) {
			this.day += 1;
			setWeather();
			switch (day%7) {
			case 0:
				this.dayName = "Sunday";
				break;
			case 1:
				this.dayName = "Monday";
				break;
			case 2:
				this.dayName = "Tuesday";
				break;
			case 3:
				this.dayName = "Wednesday";
				break;
			case 4:
				this.dayName = "Thursday";
				break;
			case 5:
				this.dayName = "Friday";
				break;
			case 6:
				this.dayName = "Saturday";
				break;
			}
	}
}
