/**
 * 
 */
package userSimulation;

import marketFramework.Time;
import java.util.List;
import java.util.ArrayList;
/**
 * @author pa1g15
 *
 */
public class UserModel {
	public static ElectricityBundle EVUserA(Time time, User user) {
		double dayNeeds = 0;
		double dayUnitBudget = 0;
		String task;
		ElectricityBundle preferences = new ElectricityBundle();
		String day;
		List<String> consumption = new ArrayList<String>();
		int random1 = 1 + (int)(Math.random() * ((1000 - 1) + 1));
		int random2 = 1 + (int)(Math.random() * ((1000 - 1) + 1));
		int randomWeather1 = 1 + (int)(Math.random() * ((1000 - 1) + 1));
		int randomWeather2 = 1 + (int)(Math.random() * ((1000 - 1) + 1));
		double taskWork = 30;
		double taskHospital = 10;
		double taskShopping = 13;
		double taskHangout = 15 + (Math.random() * ((20 - 15) + 1));
		double taskTrip = 50 + (Math.random() * ((150 - 50) + 1));
		double highBudget = 18.6;
		double normalBudget = 13.9;
		double lowBudget = 7.1;
		
		
		day = time.getDayName();
		// set task for the day
		switch (day) {
		case "Monday":
			user.setShop(0);
		case "Tuesday":
		case "Wednesday":
		case "Thursday":
		case "Friday":
			if (random1 < 951) {
				consumption.add("work");
				if (random2 < 651) {
					consumption.add("shopping"); //light_shopping
				} else if (random2 < 901) {
					consumption.add("hangout");
				} else {
					consumption.add("0");
				}
			} else if (random1 < 971) {
				consumption.add("hospital");
			} else if (random1 < 981) {
				if (random2 < 501) {
					if (user.getShop()) {
						consumption.add("0");
					} else {
						consumption.add("shopping"); //heavy_shopping
						user.setShop(1);
					}
				} else if (random2 < 801) {
					consumption.add("hangout");
				} else if (random2 < 901) {
					consumption.add("trip");
				} else {
					consumption.add("0");
				}
			} else {
				consumption.add("nothing");
			}
			break;
		case "Saturday":
		case "Sunday":
			if (random1 < 501) {
				if (user.getShop()) {
					consumption.add("0");
				} else {
					consumption.add("shopping"); //heavy_shopping
					user.setShop(1);
				}
			} else if (random1 < 801) {
				consumption.add("hangout");
			} else if (random1 < 901) {
				consumption.add("trip");
			} else if (random1 < 921){
				consumption.add("hospital");
			} else {
				consumption.add("0");
			}
			break;
		}
		
		// allocate needs and budget for the day
		for (int i = 0; i < consumption.size(); i++) {
			task = consumption.get(i);
			if (task != "0") {
				switch (time.getWeather()) {
				case 1: //sunny
					if (task == "work") {
						dayNeeds += taskWork;
						dayUnitBudget += normalBudget;
					} else if (task == "hospital") {
						dayNeeds += taskHospital;
						dayUnitBudget += highBudget;
					} else if (task == "shopping") {
						dayNeeds += taskShopping;
						dayUnitBudget += normalBudget;
					} else if (task == "hangout") {
						dayNeeds += taskHangout;
						dayUnitBudget += lowBudget;
					} else if (task == "trip") {
						dayNeeds += taskTrip;
						dayUnitBudget += lowBudget;
					}
					break;
				case 2: //rainy
					if (task == "work") {
						dayNeeds += taskWork*1.2;
						dayUnitBudget += normalBudget;
					} else if (task == "hospital") {
						dayNeeds += taskHospital*1.2;
						dayUnitBudget += highBudget;
					} else if (task == "shopping") {
						dayNeeds += taskShopping*1.2;
						dayUnitBudget += normalBudget;
					} else if (task == "hangout") {
						if (randomWeather1 > 200) {
							dayNeeds += taskHangout*1.2;
							dayUnitBudget += lowBudget;
						}
					} else if (task == "trip") {
						if (randomWeather1 > 200) {
							dayUnitBudget += lowBudget;
						}
					}
					break;
				case 3: //foggy
					if (task == "work") {
						dayNeeds += taskWork;
						dayUnitBudget += normalBudget;
					} else if (task == "hospital") {
						dayNeeds += taskHospital;
						dayUnitBudget += highBudget;
					} else if (task == "shopping") {
						dayNeeds += taskShopping;
						dayUnitBudget += normalBudget;
					} else if (task == "hangout") {
						if (randomWeather1 > 700) {
							dayNeeds += taskHangout;
							dayUnitBudget += lowBudget;
						}
					} else if (task == "trip") {
						if (randomWeather1 > 700) {
							dayNeeds += taskTrip;
							dayUnitBudget += lowBudget;
						}
					}
					break;
				case 4: //snow
					if (task == "work") {
						if (randomWeather2 > 100) {
							dayNeeds += taskWork*1.5;
							dayUnitBudget += normalBudget;
						}
					} else if (task == "hospital") {
						dayNeeds += taskHospital*1.5;
						dayUnitBudget += highBudget;
					} else if (task == "shopping") {
						if (randomWeather2 > 100) {
							dayNeeds += taskShopping*1.5;
							dayUnitBudget += normalBudget;
						}
					} else if (task == "hangout") {
						if (randomWeather1 > 800) {
							dayNeeds += taskHangout*1.5;
							dayUnitBudget += lowBudget;
						}
					} else if (task == "trip") {
						if (randomWeather1 > 800) {
							dayNeeds += taskTrip*1.5;
							dayUnitBudget += lowBudget;
						}
					}
					break;
				}
			}
		}
		dayUnitBudget = dayUnitBudget/dayNeeds;
		preferences.setAmount(dayNeeds);
		preferences.setUnitPrice(dayUnitBudget);
		return preferences;
	}
}
