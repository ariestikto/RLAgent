/**
 * 
 */
package marketFramework;
/**
 * @author pa1g15
 *
 */
public class Time {
	private int day;
	private String dayName;
	private int weather;
	private int nextWeather;
	
	public Time() {
		this.day = 0;
		this.dayName = "Sunday";
		randomizeNextWeather();
		this.weather = 1;
	}	
	public int getDay() {
		return day;
	}
	
	public String getNextDayName() {
		String dayName = "";
		switch ((day+1)%7) {
		case 0:
			dayName = "Sunday";
			break;
		case 1:
			dayName = "Monday";
			break;
		case 2:
			dayName = "Tuesday";
			break;
		case 3:
			dayName = "Wednesday";
			break;
		case 4:
			dayName = "Thursday";
			break;
		case 5:
			dayName = "Friday";
			break;
		case 6:
			dayName = "Saturday";
			break;
		}
		return dayName;
	}
	public String getDayName() {
		return dayName;
	}
	public int getWeather() {
		return weather;
	}
	public int getNextWeather() {
		return nextWeather;
	}
	public void randomizeNextWeather() {
		double random = Math.random();
		if (random > 0.25) {
			// 1 = sunny weather
			this.nextWeather = 1;
		} else if (random > 0.05) {
			// 2 = rainy weather
			this.nextWeather = 2;
		} else {
			// 3 = snow weather
			this.nextWeather = 3;
		}
	}
	public String getWeatherName() {
		String weatherName = "";
		switch (weather) {
		case 1:
			weatherName =  "Sunny";
			break;
		case 2:
			weatherName =  "Rainy";
			break;
		case 3:
			weatherName =  "Snow";
			break;
		}
		return weatherName;
	}
	public String getNextWeatherName() {
		String weatherName = "";
		switch (nextWeather) {
		case 1:
			weatherName =  "Sunny";
			break;
		case 2:
			weatherName =  "Rainy";
			break;
		case 3:
			weatherName =  "Snow";
			break;
		}
		return weatherName;
	}
	public void advanceTime() {
		this.day += 1;
		this.weather = nextWeather;
		randomizeNextWeather();
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
