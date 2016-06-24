/**
 * 
 */
package agent;

/**
 * @author pa1g15
 *
 */
public class State {

	/**
	 * 
	 */
	private int day;
	private int weather;
	private double currentElectricity;
	private double totalSpending;
	
	public State() {
		this.day = 0;
		this.weather = 0;
		this.currentElectricity = 0;
		this.totalSpending = 0;
	}
	
	public int getDay() {
		return day;
	}
	
	public void setDay(int day) {
		this.day = day;
	}
	
	public int getWeather() {
		return weather;
	}
	
	public void setWeather(int weather) {
		this.weather = weather;
	}
	
	public double getCurrentElectricity() {
		return currentElectricity;
	}
	
	public void setCurrentElectricity(double currentElectricity) {
		this.currentElectricity = currentElectricity;
	}
	
	public double getTotalSpending() {
		return totalSpending;
	}
	
	public void setTotalSpending(double totalSpending) {
		this.totalSpending = Math.round(totalSpending);
	}
	 
	public boolean isEqual (State s) {
		return ((day == s.getDay()) && (weather == s.getWeather()) && (currentElectricity == s.getCurrentElectricity()) && (totalSpending == s.totalSpending));
	}
}
