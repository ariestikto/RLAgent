/**
 * 
 */
package agent.learningAgent;

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
	
	public State() {
		this.day = "0";
		this.weather = 0;
	}
	
	public State(String day, int weather) {
		this.day = day;
		this.weather = weather;
	}

	public String getDay() {
		return day;
	}
	
	public void setDay(String day) {
		this.day = day;
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
	
	public void setWeather(int weather) {
		this.weather = weather;
	}
	
	public boolean isEqual(State s) {
		return ((day == s.getDay()) && (weather == s.getWeather()));
	}
	
	public boolean isEmpty() {
		return ((day == "0") && (weather == 0));
	}
}
