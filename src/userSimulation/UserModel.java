/**
 * 
 */
package userSimulation;

import marketFramework.Snippet;
import marketFramework.Time;

import java.util.List;
import java.util.Collections;

import org.apache.commons.math3.distribution.NormalDistribution;

import agent.Task;

import java.util.ArrayList;
/**
 * @author pa1g15
 *
 */
public class UserModel {
	public static void EVUserA(Time time, User user, ElectricityBundle preferences, List<Task> taskList) {
		// necessary variables
		double dayNeeds = 0; //km
		double dayBudget = 0; //p/km
		String task;
		String day;
		List<String> plan = new ArrayList<String>();
		double consumption = user.getCar().getConsumption();
		
		// user parameter
		double highBudget = 18.6;
		double normalBudget = 13.9;
		double lowBudget = 7.1;
		double highMultiplier = 15;
		double normalMultiplier = 13;
		double lowMultiplier = 10;
		
		// available task list
		double taskWork = Snippet.normDist(40);
		double taskLunch = Snippet.normDist(10 + (Math.random() * ((15 - 10) + 1)));
		double taskDinner = Snippet.normDist(17 + (Math.random() * ((20 - 17) + 1)));
		double taskHospital = Snippet.normDist(20);
		double taskShopping = Snippet.normDist(30);
		double taskHangout = Snippet.normDist(20 + (Math.random() * ((25 - 20) + 1)));
		double taskTrip = Snippet.normDist(150 + (Math.random() * ((200 - 150) + 1)));
		
		// task value function
		double workValue = taskWork*normalBudget*consumption*normalMultiplier;
		double lunchValue = taskLunch*normalBudget*consumption*normalMultiplier;
		double dinnerValue = taskDinner*normalBudget*consumption*normalMultiplier;
		double hospitalValue = taskHospital*highBudget*consumption*highMultiplier;
		double shoppingValue = taskShopping*normalBudget*consumption*normalMultiplier;
		double hangoutValue = taskHangout*lowBudget*consumption*lowMultiplier;
		double tripValue = taskTrip*lowBudget*consumption*lowMultiplier;
		
		day = time.getDayName();
		// add task for the day
		switch (day) {
		case "Monday":
		case "Tuesday":
		case "Wednesday":
		case "Thursday":
		case "Friday":
			if (Math.random() < 0.95) {
				plan.add("work");
				if (Math.random() < 0.65) {
					plan.add("shopping");
					if (Math.random() < 0.8) {
						plan.add("lunch");
					}
				} else if (Math.random() < 0.9) {
					plan.add("hangout");
				} else {
					if (Math.random() < 0.3) {
						plan.add("lunch");
					}
				}
				if (Math.random() < 0.8) {
					plan.add("lunch");
				}
			} else if (Math.random() < 0.03) {
				plan.add("hospital");
				if (Math.random() < 0.8) {
					plan.add("lunch");
				}
			} else if (Math.random() < 0.02) {
				if (Math.random() < 0.5) {
					plan.add("shopping");
					if (Math.random() < 0.8) {
						plan.add("lunch");
					}
				} else if (Math.random() < 0.8) {
					plan.add("hangout");
					if (Math.random() < 0.8) {
						plan.add("lunch");
					}
				} else if (Math.random() < 0.9) {
					plan.add("trip");
				} else {
					if (Math.random() < 0.3) {
						plan.add("lunch");
					}
				}
			} else {
				if (Math.random() < 0.3) {
					plan.add("lunch");
				}
			}
			if (Math.random() < 0.5) {
				plan.add("dinner");
			}
			break;
		case "Saturday":
		case "Sunday":
			if (Math.random() < 0.5) {
				plan.add("shopping");
				if (Math.random() < 0.8) {
					plan.add("lunch");
				}
			} else if (Math.random() < 0.3) {
				plan.add("hangout");
				if (Math.random() < 0.8) {
					plan.add("lunch");
				}
			} else if (Math.random() < 0.1) {
				plan.add("trip");
			} else if (Math.random() < 0.08){
				plan.add("hospital");
				if (Math.random() < 0.8) {
					plan.add("lunch");
				}
			} else {
				if (Math.random() < 0.3) {
					plan.add("lunch");
				}
			}
			if (Math.random() < 0.5) {
				plan.add("dinner");
			}
			break;
		}
		
		// allocate needs and budget for the day
		for (int i = 0; i < plan.size(); i++) {
			task = plan.get(i);
			switch (time.getWeather()) {
			case 1: //sunny
				if (task == "work") {
					dayNeeds += taskWork;
					dayBudget += normalBudget*taskWork;
					taskList.add(new Task(workValue, taskWork));
				} else if (task == "hospital") {
					dayNeeds += taskHospital;
					dayBudget += highBudget*taskHospital;
					taskList.add(new Task(hospitalValue, taskHospital));
				} else if (task == "shopping") {
					dayNeeds += taskShopping;
					dayBudget += normalBudget*taskShopping;
					taskList.add(new Task(shoppingValue, taskShopping));
				} else if (task == "hangout") {
					dayNeeds += taskHangout;
					dayBudget += lowBudget*taskHangout;
					taskList.add(new Task(hangoutValue, taskHangout));
				} else if (task == "trip") {
					dayNeeds += taskTrip;
					dayBudget += lowBudget*taskTrip;
					taskList.add(new Task(tripValue, taskTrip));
				} else if (task == "dinner") {
					dayNeeds += taskDinner;
					dayBudget += normalBudget*taskDinner;
					taskList.add(new Task(dinnerValue, taskDinner));
				} else if (task == "lunch") {
					dayNeeds += taskLunch;
					dayBudget += lowBudget*taskLunch;
					taskList.add(new Task(lunchValue, taskLunch));
				}
				break;
			case 2: //rainy
				if (task == "work") {
					dayNeeds += taskWork*1.2;
					dayBudget += normalBudget*taskWork*1.2;
					taskList.add(new Task(workValue*1.2, taskWork*1.2));
				} else if (task == "hospital") {
					dayNeeds += taskHospital*1.2;
					dayBudget += highBudget*taskHospital*1.2;
					taskList.add(new Task(hospitalValue*1.2, taskHospital*1.2));
				} else if (task == "shopping") {
					dayNeeds += taskShopping*1.2;
					dayBudget += normalBudget*taskShopping*1.2;
					taskList.add(new Task(shoppingValue*1.2, taskShopping*1.2));
				} else if (task == "hangout") {
					if (Math.random() > 0.2) {
						dayNeeds += taskHangout*1.2;
						dayBudget += lowBudget*taskHangout*1.2;
						taskList.add(new Task(hangoutValue*1.2, taskHangout*1.2));
					}
				} else if (task == "trip") {
					if (Math.random() > 0.2) {
						dayNeeds += taskTrip*1.2;
						dayBudget += lowBudget*taskTrip*1.2;
						taskList.add(new Task(tripValue*1.2, taskTrip*1.2));
					}
				} else if (task == "dinner") {
					dayNeeds += taskDinner*1.2;
					dayBudget += normalBudget*taskDinner*1.2;
					taskList.add(new Task(dinnerValue*1.2, taskDinner*1.2));
				} else if (task == "lunch") {
					if (Math.random() > 0.2) {
						dayNeeds += taskLunch*1.2;
						dayBudget += lowBudget*taskLunch*1.2;
						taskList.add(new Task(lunchValue*1.2, taskLunch*1.2));
					}
				}
				break;
			case 3: //snow
				if (task == "work") {
					if (Math.random() > 0.1) {
						dayNeeds += taskWork*1.5;
						dayBudget += normalBudget*taskWork*1.5;
						taskList.add(new Task(workValue*1.5, taskWork*1.5));
					}
				} else if (task == "hospital") {
					dayNeeds += taskHospital*1.5;
					dayBudget += highBudget*taskHospital*1.5;
					taskList.add(new Task(hospitalValue*1.5, taskHospital*1.5));
				} else if (task == "shopping") {
					if (Math.random() > 0.1) {
						dayNeeds += taskShopping*1.5;
						dayBudget += normalBudget*taskShopping*1.5;
						taskList.add(new Task(shoppingValue*1.5, taskShopping*1.5));
					}
				} else if (task == "hangout") {
					if (Math.random() > 0.8) {
						dayNeeds += taskHangout*1.5;
						dayBudget += lowBudget*taskHangout*1.5;
						taskList.add(new Task(hangoutValue*1.5, taskHangout*1.5));
					}
				} else if (task == "trip") {
					if (Math.random() > 0.8) {
						dayNeeds += taskTrip*1.5;
						dayBudget += lowBudget*taskTrip*1.5;
						taskList.add(new Task(tripValue*1.5, taskTrip*1.5));
					}
				} else if (task == "dinner") {
					if (Math.random() > 0.1) {
						dayNeeds += taskDinner*1.5;
						dayBudget += normalBudget*taskDinner*1.5;
						taskList.add(new Task(dinnerValue, taskDinner*1.5));
					}
				} else if (task == "lunch") {
					if (Math.random() > 0.8) {
						dayNeeds += taskLunch*1.5;
						dayBudget += lowBudget*taskLunch*1.5;
						taskList.add(new Task(lunchValue*1.5, taskLunch*1.5));
					}
				}
				break;
			}
		}
		preferences.setAmount(dayNeeds*consumption);
		if (dayNeeds > 0) {
			preferences.setUnitPrice(dayBudget/dayNeeds);
		} else {
			preferences.setUnitPrice(0);
		}
		Collections.sort(taskList);
	}
	public static void EVUserB(Time time, User user, ElectricityBundle preferences, List<Task> taskList) {
		
	}
	public static void TestCase1EVUser(Time time, User user, ElectricityBundle preferences, List<Task> taskList) {
		// necessary variables
		double dayNeeds = 0; //km
		double dayBudget = 0; //p/km
		String task;
		String day;
		List<String> plan = new ArrayList<String>();
		double consumption = user.getCar().getConsumption();
		
		// user parameter
		double highBudget = 5;
		double lowBudget = 0;
		
		double highMultiplier = 80;
		double lowMultiplier = 40;
		
		// available task list
		double taskWork = 6/consumption;
		double taskTrip = 12/consumption;
		
		// task value function
		double workValue = taskWork*highMultiplier;
		double tripValue = taskTrip*lowMultiplier;
		
		day = time.getDayName();
		// set task for the day
		switch (day) {
		case "Monday":
		case "Tuesday":
		case "Wednesday":
		case "Thursday":
		case "Friday":
		case "Saturday":
			plan.add("work");
			break;
		case "Sunday":
			plan.add("trip");
			break;
		}
		
		// allocate needs and budget for the day
		for (int i = 0; i < plan.size(); i++) {
			task = plan.get(i);
			if (task == "work") {
				dayNeeds += taskWork;
				dayBudget += highBudget*taskWork;
				taskList.add(new Task(workValue, taskWork));
			} else if (task == "trip") {
				dayNeeds += taskTrip;
				dayBudget += lowBudget*taskTrip;
				taskList.add(new Task(tripValue, taskTrip));
			}
		}
		preferences.setAmount(dayNeeds*consumption);
		if (dayNeeds > 0) {
			preferences.setUnitPrice(dayBudget/dayNeeds);
		} else {
			preferences.setUnitPrice(0);
		}
		Collections.sort(taskList);
	}
	public static void TestCase2EVUser(Time time, User user, ElectricityBundle preferences, List<Task> taskList) {
		// necessary variables
		double dayNeeds = 0; //km
		double dayBudget = 0; //p/km
		String task;
		String day;
		List<String> plan = new ArrayList<String>();
		double consumption = user.getCar().getConsumption();
		
		// user parameter
		double highBudget = 23;
		
		double highMultiplier = 20;
		
		// available task list
		double taskWork = 16.36/consumption;
		
		// task value function
		double workValue = taskWork*highMultiplier;
		
		day = time.getDayName();
		// set task for the day
		switch (day) {
		case "Monday":
		case "Tuesday":
		case "Wednesday":
		case "Thursday":
		case "Friday":
		case "Saturday":
		case "Sunday":
			plan.add("work");
			break;
		}
		
		// allocate needs and budget for the day
		for (int i = 0; i < plan.size(); i++) {
			task = plan.get(i);
			if (task == "work") {
				dayNeeds += taskWork;
				dayBudget += highBudget*taskWork;
				taskList.add(new Task(workValue, taskWork));
			}
		}
		preferences.setAmount(dayNeeds*consumption);
		if (dayNeeds > 0) {
			preferences.setUnitPrice(dayBudget/dayNeeds);
		} else {
			preferences.setUnitPrice(0);
		}
		Collections.sort(taskList);
	}
	public static void CompanyBuyer(ElectricityBundle preferences) {
		NormalDistribution needs = new NormalDistribution(30, 2);
		NormalDistribution unitBudget = new NormalDistribution(25, 2);
		preferences.setAmount(needs.sample());
		preferences.setUnitPrice(unitBudget.sample());
	}
	public static void CompanyBuyerB(ElectricityBundle preferences) {
		preferences.setAmount(10);
		preferences.setUnitPrice(17);
	}
	public static void OtherUser(Time time, ElectricityBundle preferences) {
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
	}
}
