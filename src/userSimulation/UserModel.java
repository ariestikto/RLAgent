/**
 * 
 */
package userSimulation;

import marketFramework.Snippet;
import marketFramework.Time;
import java.util.List;

import org.apache.commons.math3.distribution.NormalDistribution;

import java.util.ArrayList;
/**
 * @author pa1g15
 *
 */
public class UserModel {
	public static ElectricityBundle EVUserA(Time time, User user) {
		ElectricityBundle preferences = new ElectricityBundle();
		double dayNeeds = 0; //km
		double dayBudget = 0; //p/km
		String task;
		String day;
		List<String> consumption = new ArrayList<String>();
		int random1 = 1 + (int)(Math.random() * ((1000 - 1) + 1));
		int random2 = 1 + (int)(Math.random() * ((1000 - 1) + 1));
		int randomWeather1 = 1 + (int)(Math.random() * ((1000 - 1) + 1));
		int randomWeather2 = 1 + (int)(Math.random() * ((1000 - 1) + 1));
		double taskWork = Snippet.normDist(30);
		double taskHospital = Snippet.normDist(10);
		double taskShopping = Snippet.normDist(13);
		double taskHangout = Snippet.normDist(15 + (Math.random() * ((20 - 15) + 1)));
		double taskTrip = Snippet.normDist(50 + (Math.random() * ((150 - 50) + 1)));
		double highBudget = Snippet.normDist(18.6);
		double normalBudget = Snippet.normDist(13.9);
		double lowBudget = Snippet.normDist(7.1);
		
		
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
				consumption.add("0");
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
						dayBudget += normalBudget*taskWork;
					} else if (task == "hospital") {
						dayNeeds += taskHospital;
						dayBudget += highBudget*taskHospital;
					} else if (task == "shopping") {
						dayNeeds += taskShopping;
						dayBudget += normalBudget*taskShopping;
					} else if (task == "hangout") {
						dayNeeds += taskHangout;
						dayBudget += lowBudget*taskHangout;
					} else if (task == "trip") {
						dayNeeds += taskTrip;
						dayBudget += lowBudget*taskTrip;
					}
					break;
				case 2: //rainy
					if (task == "work") {
						dayNeeds += taskWork*1.2;
						dayBudget += normalBudget*taskWork*1.2;
					} else if (task == "hospital") {
						dayNeeds += taskHospital*1.2;
						dayBudget += highBudget*taskHospital*1.2;
					} else if (task == "shopping") {
						dayNeeds += taskShopping*1.2;
						dayBudget += normalBudget*taskShopping*1.2;
					} else if (task == "hangout") {
						if (randomWeather1 > 200) {
							dayNeeds += taskHangout*1.2;
							dayBudget += lowBudget*taskHangout*1.2;
						}
					} else if (task == "trip") {
						if (randomWeather1 > 200) {
							dayNeeds += taskTrip*1.2;
							dayBudget += lowBudget*taskTrip*1.2;
						}
					}
					break;
				case 3: //foggy
					if (task == "work") {
						dayNeeds += taskWork;
						dayBudget += normalBudget*taskWork;
					} else if (task == "hospital") {
						dayNeeds += taskHospital;
						dayBudget += highBudget*taskHospital;
					} else if (task == "shopping") {
						dayNeeds += taskShopping;
						dayBudget += normalBudget*taskShopping;
					} else if (task == "hangout") {
						if (randomWeather1 > 700) {
							dayNeeds += taskHangout;
							dayBudget += lowBudget*taskHangout;
						}
					} else if (task == "trip") {
						if (randomWeather1 > 700) {
							dayNeeds += taskTrip;
							dayBudget += lowBudget*taskTrip;
						}
					}
					break;
				case 4: //snow
					if (task == "work") {
						if (randomWeather2 > 100) {
							dayNeeds += taskWork*1.5;
							dayBudget += normalBudget*taskWork*1.5;
						}
					} else if (task == "hospital") {
						dayNeeds += taskHospital*1.5;
						dayBudget += highBudget*taskHospital*1.5;
					} else if (task == "shopping") {
						if (randomWeather2 > 100) {
							dayNeeds += taskShopping*1.5;
							dayBudget += normalBudget*taskShopping*1.5;
						}
					} else if (task == "hangout") {
						if (randomWeather1 > 800) {
							dayNeeds += taskHangout*1.5;
							dayBudget += lowBudget*taskHangout*1.5;
						}
					} else if (task == "trip") {
						if (randomWeather1 > 800) {
							dayNeeds += taskTrip*1.5;
							dayBudget += lowBudget*taskTrip*1.5;
						}
					}
					break;
				}
			}
		}
		preferences.setAmount(dayNeeds*user.getCar().getConsumption());
		if (dayNeeds > 0) {
			preferences.setUnitPrice(dayBudget/dayNeeds);
		} else {
			preferences.setUnitPrice(0);
		}
		
		return preferences;
	}
	public static ElectricityBundle EVUserB(Time time, User user) {
		ElectricityBundle preferences = new ElectricityBundle();
		double dayNeeds = 0; //km
		double dayBudget = 0; //p/km
		String task;
		String day;
		List<String> consumption = new ArrayList<String>();
		int random1 = 1 + (int)(Math.random() * ((1000 - 1) + 1));
		int random2 = 1 + (int)(Math.random() * ((1000 - 1) + 1));
		int randomWeather1 = 1 + (int)(Math.random() * ((1000 - 1) + 1));
		int randomWeather2 = 1 + (int)(Math.random() * ((1000 - 1) + 1));
		double taskWork = Snippet.normDist(50);
		double taskHospital = Snippet.normDist(5);
		double taskShopping = Snippet.normDist(7);
		double taskHangout = Snippet.normDist(20 + (Math.random() * ((40 - 20) + 1)));
		double taskTrip = Snippet.normDist(50 + (Math.random() * ((200 - 50) + 1)));
		double highBudget = Snippet.normDist(24.5);
		double normalBudget = Snippet.normDist(18.6);
		double lowBudget = Snippet.normDist(13.9);
		
		
		day = time.getDayName();
		// set task for the day
		switch (day) {
		case "Monday":
			user.setShop(0);
		case "Tuesday":
		case "Wednesday":
		case "Thursday":
		case "Friday":
			if (random1 < 501) {
				consumption.add("work");
			} else if (random1 < 503) {
				consumption.add("hospital");
			} else if (random1 < 533) {
				if (random2 < 501) {
					if (user.getShop()) {
						consumption.add("0");
					} else {
						consumption.add("shopping"); //heavy_shopping
						user.setShop(1);
					}
				} else {
					consumption.add("0");
				}
			} else {
				if (random2 < 201) {
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
			}
			break;
		case "Saturday":
		case "Sunday":
			if (random1 < 201) {
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
						dayBudget += normalBudget*taskWork;
					} else if (task == "hospital") {
						dayNeeds += taskHospital;
						dayBudget += highBudget*taskHospital;
					} else if (task == "shopping") {
						dayNeeds += taskShopping;
						dayBudget += normalBudget*taskShopping;
					} else if (task == "hangout") {
						dayNeeds += taskHangout;
						dayBudget += lowBudget*taskHangout;
					} else if (task == "trip") {
						dayNeeds += taskTrip;
						dayBudget += lowBudget*taskTrip;
					}
					break;
				case 2: //rainy
					if (task == "work") {
						dayNeeds += taskWork*1.1;
						dayBudget += normalBudget*taskWork*1.1;
					} else if (task == "hospital") {
						dayNeeds += taskHospital*1.1;
						dayBudget += highBudget*taskHospital*1.1;
					} else if (task == "shopping") {
						dayNeeds += taskShopping*1.1;
						dayBudget += normalBudget*taskShopping*1.1;
					} else if (task == "hangout") {
						if (randomWeather1 > 50) {
							dayNeeds += taskHangout*1.1;
							dayBudget += lowBudget*taskHangout*1.1;
						}
					} else if (task == "trip") {
						if (randomWeather1 > 50) {
							dayNeeds += taskTrip*1.1;
							dayBudget += lowBudget*taskTrip*1.1;
						}
					}
					break;
				case 3: //foggy
					if (task == "work") {
						dayNeeds += taskWork;
						dayBudget += normalBudget*taskWork;
					} else if (task == "hospital") {
						dayNeeds += taskHospital;
						dayBudget += highBudget*taskHospital;
					} else if (task == "shopping") {
						dayNeeds += taskShopping;
						dayBudget += normalBudget*taskShopping;
					} else if (task == "hangout") {
						if (randomWeather1 > 50) {
							dayNeeds += taskHangout;
							dayBudget += lowBudget*taskHangout;
						}
					} else if (task == "trip") {
						if (randomWeather1 > 50) {
							dayNeeds += taskTrip;
							dayBudget += lowBudget*taskTrip;
						}
					}
					break;
				case 4: //snow
					if (task == "work") {
						if (randomWeather2 > 100) {
							dayNeeds += taskWork*1.3;
							dayBudget += normalBudget*taskWork*1.3;
						}
					} else if (task == "hospital") {
						dayNeeds += taskHospital*1.3;
						dayBudget += highBudget*taskHospital*1.3;
					} else if (task == "shopping") {
						if (randomWeather2 > 100) {
							dayNeeds += taskShopping*1.3;
							dayBudget += normalBudget*taskShopping*1.3;
						}
					} else if (task == "hangout") {
						if (randomWeather1 > 300) {
							dayNeeds += taskHangout*1.3;
							dayBudget += lowBudget*taskHangout*1.3;
						}
					} else if (task == "trip") {
						if (randomWeather1 > 300) {
							dayNeeds += taskTrip*1.3;
							dayBudget += lowBudget*taskTrip*1.3;
						}
					}
					break;
				}
			}
		}
		preferences.setAmount(dayNeeds*user.getCar().getConsumption());
		if (dayNeeds > 0) {
			preferences.setUnitPrice(dayBudget/dayNeeds);
		} else {
			preferences.setUnitPrice(0);
		}
		return preferences;
	}
	public static ElectricityBundle CompanyBuyer() {
		ElectricityBundle preferences = new ElectricityBundle();
		NormalDistribution needs = new NormalDistribution(35, 8);
		NormalDistribution unitBudget = new NormalDistribution(30, 3);
		preferences.setAmount(needs.sample());
		preferences.setUnitPrice(unitBudget.sample());
		return preferences;
	}
	public static ElectricityBundle OtherUser(Time time) {
		ElectricityBundle preferences = new ElectricityBundle();
		double randomNeeds = 0.2 + (Math.random() * ((5 - 0.2) + 1));
		double randomUnitBudget = 6 + (Math.random() * ((14 - 6) + 1));
		switch (time.getWeather()) {
		case 2: //rainy
			randomNeeds *= 1.2;
			randomUnitBudget *= 1.4;
			break;
		case 4: //snow
			randomNeeds *= 1.7;
			randomUnitBudget *= 1.9;
			break;
		}
		preferences.setAmount(randomNeeds);
		preferences.setUnitPrice(randomUnitBudget);
		return preferences;
	}
}
