/**
 * 
 */
package agent.learningAgent;

import userSimulation.User;
/**
 * @author pa1g15
 *
 */
public class State {

	/**
	 * 
	 */
	private String day;
	private int weather;
	private int electricityLevel; // (1: 0, 2: 0.25, 3: 0.5, 4: 0.75, 5: 1)
	
	public State() {
		this.day = "0";
		this.weather = 0;
		this.electricityLevel = 0;
	}
	
	public void setState(String day, int weather, int electricityLevel) {
		this.day = day;
		this.weather = weather;
		this.electricityLevel = electricityLevel;
	}

	public String getDay() {
		return day;
	}

	public int getWeather() {
		return weather;
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

	public double getElectricityLevel() {
		return electricityLevel;
	}

	private void setDay(String day) {
		this.day = day;
	}

	private void setWeather(int weather) {
		this.weather = weather;
	}

	private void setElectricityLevel(int electricityLevel) {
		this.electricityLevel = electricityLevel;
	}

	public State nextState(Action action, User user) {
		State nextState = new State();
		// next day
		switch (day) {
		case "Sunday":
			nextState.setDay("Monday");
			break;
		case "Monday":
			nextState.setDay("Tuesday");
			break;
		case "Tuesday":
			nextState.setDay("Wednesday");
			break;
		case "Wednesday":
			nextState.setDay("Thursday");
			break;
		case "Thursday":
			nextState.setDay("Friday");
			break;
		case "Friday":
			nextState.setDay("Saturday");
			break;
		case "Saturday":
			nextState.setDay("Sunday");
			break;
		}
		
		//next weather 
		int random = 1 + (int)(Math.random() * ((1000 - 1) + 1));
		if (random < 810) {
			// 1 = sunny weather
			nextState.setWeather(1);
		} else if (random < 960) {
			// 2 = rainy weather
			nextState.setWeather(2);
		} else if (random < 980) {
			// 3 = foggy weather
			nextState.setWeather(3);
		} else {
			// 4 = snow weather
			nextState.setWeather(4);
		}
		
		//next electricity level
		double currentLevel = user.getCurrentElectricity()/user.getCar().getBatteryCapacity();
		if (currentLevel > 0.875) {
			nextState.setElectricityLevel(5);
		} else if (currentLevel > 0.625) {
			nextState.setElectricityLevel(4);
		} else if (currentLevel > 0.375) {
			nextState.setElectricityLevel(3);
		} else if (currentLevel > 0.125) {
			nextState.setElectricityLevel(2);
		} else {
			nextState.setElectricityLevel(1);
		}
		
		return nextState;
	}

	public boolean isEqual(State s) {
		return ((day == s.getDay()) && (weather == s.getWeather()) && (electricityLevel == s.getElectricityLevel()));
	}
	
	public boolean isEmpty() {
		return ((day == "0") && (weather == 0) && (electricityLevel == 0));
	}
}
