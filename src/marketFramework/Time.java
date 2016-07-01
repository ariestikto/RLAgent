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
	
	public Time() {
		this.day = 0;
		this.dayName = "Sunday";
		this.weather = 1;
		randomizeWeather();
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
	public void randomizeWeather() {
		int random = 1 + (int)(Math.random() * ((1000 - 1) + 1));
		if (random < 810) {
			// 1 = sunny weather
			this.weather = 1;
		} else if (random < 960) {
			// 2 = rainy weather
			this.weather = 2;
		} else if (random < 980) {
			// 3 = foggy weather
			this.weather = 3;
		} else {
			// 4 = snow weather
			this.weather = 4;
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
			weatherName =  "Fog";
			break;
		case 4:
			weatherName =  "Snow";
			break;
		}
		return weatherName;
	}
	public void advanceTime() {
		this.day += 1;
		randomizeWeather();
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
