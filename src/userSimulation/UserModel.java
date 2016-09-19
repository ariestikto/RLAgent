package userSimulation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.apache.commons.math3.distribution.NormalDistribution;

import agent.Task;
import marketFramework.Snippet;
import marketFramework.Time;
/**
 * @author pa1g15
 *
 */
public class UserModel {
	
	private static final double J1_Val = Math.random();
	private static final double J2_Val = Math.random();
	private static final double J3_Val = Math.random();
	private static final double J4_Val = Math.random();
	private static final double J5_Val = Math.random();
	private static final double J6_Val = Math.random();
	private static final double J7_Val = Math.random();
	private static final double J8_Val = Math.random();
	private static final double J9_Val = Math.random();
	private static final double J10_Val = Math.random();
	private static final double J11_Val = Math.random();
	private static final double J12_Val = Math.random();
	private static final double J13_Val = Math.random();
	private static final double J14_Val = Math.random();
	private static final double J15_Val = Math.random();
	private static final double J16_Val = Math.random();
	private static final double J17_Val = Math.random();
	private static final double J18_Val = Math.random();
	private static final double J19_Val = Math.random();
	private static final double average_el = 9.37;
	private static final double average_petrol = 14.6;
			
			
	public static void EVUSer_1600(Time time, User user, ElectricityBundle preferences, List<Task> taskList) {
		// necessary variables
		// user with utility 60% average, 20% higher, 20% lower
		double dayNeeds = 0; //miles
		double dayBudget = 0; //p/miles
		String day;
		double consumption = user.getCar().getConsumption();
		int totalJourney = 0;
		double budget_parameter = (average_el*consumption)/average_petrol;
		
		// possible journey (distance in miles)
		// Monday (0-4)
		double M_J1 = Snippet.normDist(3.1);
		double M_J2 = Snippet.normDist(1.8);
		double M_J3 = Snippet.normDist(0.8);
		double M_J4 = Snippet.normDist(8.38);
		double M_J5 = Snippet.normDist(5.83);
		double M_J6 = Snippet.normDist(2.34);
		double M_J7 = Snippet.normDist(0.48);
		double M_J8 = Snippet.normDist(0.23);
		double M_J9 = Snippet.normDist(1.26);
		double M_J10 = Snippet.normDist(0.92);
		double M_J11 = Snippet.normDist(0.4);
		
		double M_J1_Val = 0;
		double M_J2_Val = 0;
		double M_J3_Val = 0;
		double M_J4_Val = 0;
		double M_J5_Val = 0;
		double M_J6_Val = 0;
		double M_J7_Val = 0;
		double M_J8_Val = 0;
		double M_J9_Val = 0;
		double M_J10_Val = 0;
		double M_J11_Val = 0;
		
		if (J1_Val < 0.6) {
			M_J1_Val = M_J1*14.6;
		} else if (J1_Val < 0.8) {
			M_J1_Val = M_J1*11.7;
		} else {
			M_J1_Val = M_J1*17.52;
		}
		if (J2_Val < 0.6) {
			M_J2_Val = M_J2*14.6;
		} else if (J2_Val < 0.8) {
			M_J2_Val = M_J2*11.7;
		} else {
			M_J2_Val = M_J2*17.52;
		}
		if (J3_Val < 0.6) {
			M_J3_Val = M_J3*14.6;
		} else if (J3_Val < 0.8) {
			M_J3_Val = M_J3*11.7;
		} else {
			M_J3_Val = M_J3*17.52;
		}
		if (J4_Val < 0.6) {
			M_J4_Val = M_J4*14.6;
		} else if (J4_Val < 0.8) {
			M_J4_Val = M_J4*11.7;
		} else {
			M_J4_Val = M_J4*17.52;
		}
		if (J5_Val < 0.6) {
			M_J5_Val = M_J5*14.6;
		} else if (J5_Val < 0.8) {
			M_J5_Val = M_J5*11.7;
		} else {
			M_J5_Val = M_J5*17.52;
		}
		if (J6_Val < 0.6) {
			M_J6_Val = M_J6*14.6;
		} else if (J6_Val < 0.8) {
			M_J6_Val = M_J6*11.7;
		} else {
			M_J6_Val = M_J6*17.52;
		}
		if (J7_Val < 0.6) {
			M_J7_Val = M_J7*14.6;
		} else if (J7_Val < 0.8) {
			M_J7_Val = M_J7*11.7;
		} else {
			M_J7_Val = M_J7*17.52;
		}
		if (J8_Val < 0.6) {
			M_J8_Val = M_J8*14.6;
		} else if (J8_Val < 0.8) {
			M_J8_Val = M_J8*11.7;
		} else {
			M_J8_Val = M_J8*17.52;
		}
		if (J9_Val < 0.6) {
			M_J9_Val = M_J9*14.6;
		} else if (J9_Val < 0.8) {
			M_J9_Val = M_J9*11.7;
		} else {
			M_J9_Val = M_J9*17.52;
		}
		if (J10_Val < 0.6) {
			M_J10_Val = M_J10*14.6;
		} else if (J10_Val < 0.8) {
			M_J10_Val = M_J10*11.7;
		} else {
			M_J10_Val = M_J10*17.52;
		}
		if (J11_Val < 0.6) {
			M_J11_Val = M_J11*14.6;
		} else if (J11_Val < 0.8) {
			M_J11_Val = M_J11*11.7;
		} else {
			M_J11_Val = M_J11*17.52;
		}
		double mondayJourney[] = new double[11];
		double mondayJourneyValue[] = new double[11];
		mondayJourney[0] = M_J1;
		mondayJourney[1] = M_J2;
		mondayJourney[2] = M_J3;
		mondayJourney[3] = M_J4;
		mondayJourney[4] = M_J5;
		mondayJourney[5] = M_J6;
		mondayJourney[6] = M_J7;
		mondayJourney[7] = M_J8;
		mondayJourney[8] = M_J9;
		mondayJourney[9] = M_J10;
		mondayJourney[10] = M_J11;
		
		mondayJourneyValue[0] = M_J1_Val;
		mondayJourneyValue[1] = M_J2_Val;
		mondayJourneyValue[2] = M_J3_Val;
		mondayJourneyValue[3] = M_J4_Val;
		mondayJourneyValue[4] = M_J5_Val;
		mondayJourneyValue[5] = M_J6_Val;
		mondayJourneyValue[6] = M_J7_Val;
		mondayJourneyValue[7] = M_J8_Val;
		mondayJourneyValue[8] = M_J9_Val;
		mondayJourneyValue[9] = M_J10_Val;
		mondayJourneyValue[10] = M_J11_Val;
		
		//Tuesday (0-3)
		double T_J1 = Snippet.normDist(0.86);
		double T_J2 = Snippet.normDist(3.48);
		double T_J3 = Snippet.normDist(0.58);
		double T_J4 = Snippet.normDist(2.18);
		double T_J5 = Snippet.normDist(26.14);
		double T_J6 = Snippet.normDist(3.76);
		double T_J7 = Snippet.normDist(2.17);
		double T_J8 = Snippet.normDist(52.8);
		double T_J9 = Snippet.normDist(7.2);
		double T_J10 = Snippet.normDist(33.64);
		double T_J11 = Snippet.normDist(8.46);
		double T_J12 = Snippet.normDist(10.98);
		double T_J13 = Snippet.normDist(0.29);
		double T_J14 = Snippet.normDist(0.48);
		double T_J15 = Snippet.normDist(2);
		
		double T_J1_Val = 0;
		double T_J2_Val = 0;
		double T_J3_Val = 0;
		double T_J4_Val = 0;
		double T_J5_Val = 0;
		double T_J6_Val = 0;
		double T_J7_Val = 0;
		double T_J8_Val = 0;
		double T_J9_Val = 0;
		double T_J10_Val = 0;
		double T_J11_Val = 0;
		double T_J12_Val = 0;
		double T_J13_Val = 0;
		double T_J14_Val = 0;
		double T_J15_Val = 0;
		
		if (J1_Val < 0.6) {
			T_J1_Val = T_J1*14.6;
		} else if (J1_Val < 0.8) {
			T_J1_Val = T_J1*11.7;
		} else {
			T_J1_Val = T_J1*17.52;
		}
		if (J2_Val < 0.6) {
			T_J2_Val = T_J2*14.6;
		} else if (J2_Val < 0.8) {
			T_J2_Val = T_J2*11.7;
		} else {
			T_J2_Val = T_J2*17.52;
		}
		if (J3_Val < 0.6) {
			T_J3_Val = T_J3*14.6;
		} else if (J3_Val < 0.8) {
			T_J3_Val = T_J3*11.7;
		} else {
			T_J3_Val = T_J3*17.52;
		}
		if (J4_Val < 0.6) {
			T_J4_Val = T_J4*14.6;
		} else if (J4_Val < 0.8) {
			T_J4_Val = T_J4*11.7;
		} else {
			T_J4_Val = T_J4*17.52;
		}
		if (J5_Val < 0.6) {
			T_J5_Val = T_J5*14.6;
		} else if (J5_Val < 0.8) {
			T_J5_Val = T_J5*11.7;
		} else {
			T_J5_Val = T_J5*17.52;
		}
		if (J6_Val < 0.6) {
			T_J6_Val = T_J6*14.6;
		} else if (J6_Val < 0.8) {
			T_J6_Val = T_J6*11.7;
		} else {
			T_J6_Val = T_J6*17.52;
		}
		if (J7_Val < 0.6) {
			T_J7_Val = T_J7*14.6;
		} else if (J7_Val < 0.8) {
			T_J7_Val = T_J7*11.7;
		} else {
			T_J7_Val = T_J7*17.52;
		}
		if (J8_Val < 0.6) {
			T_J8_Val = T_J8*14.6;
		} else if (J8_Val < 0.8) {
			T_J8_Val = T_J8*11.7;
		} else {
			T_J8_Val = T_J8*17.52;
		}
		if (J9_Val < 0.6) {
			T_J9_Val = T_J9*14.6;
		} else if (J9_Val < 0.8) {
			T_J9_Val = T_J9*11.7;
		} else {
			T_J9_Val = T_J9*17.52;
		}
		if (J10_Val < 0.6) {
			T_J10_Val = T_J10*14.6;
		} else if (J10_Val < 0.8) {
			T_J10_Val = T_J10*11.7;
		} else {
			T_J10_Val = T_J10*17.52;
		}
		if (J11_Val < 0.6) {
			T_J11_Val = T_J11*14.6;
		} else if (J11_Val < 0.8) {
			T_J11_Val = T_J11*11.7;
		} else {
			T_J11_Val = T_J11*17.52;
		}
		if (J12_Val < 0.6) {
			T_J12_Val = T_J12*14.6;
		} else if (J12_Val < 0.8) {
			T_J12_Val = T_J12*11.7;
		} else {
			T_J12_Val = T_J12*17.52;
		}
		if (J13_Val < 0.6) {
			T_J13_Val = T_J13*14.6;
		} else if (J13_Val < 0.8) {
			T_J13_Val = T_J13*11.7;
		} else {
			T_J13_Val = T_J13*17.52;
		}
		if (J14_Val < 0.6) {
			T_J14_Val = T_J14*14.6;
		} else if (J14_Val < 0.8) {
			T_J14_Val = T_J14*11.7;
		} else {
			T_J14_Val = T_J14*17.52;
		}
		if (J15_Val < 0.6) {
			T_J15_Val = T_J15*14.6;
		} else if (J15_Val < 0.8) {
			T_J15_Val = T_J15*11.7;
		} else {
			T_J15_Val = T_J15*17.52;
		}

		double tuesdayJourney[] = new double[15];
		double tuesdayJourneyValue[] = new double[15];
		tuesdayJourney[0] = T_J1;
		tuesdayJourney[1] = T_J2;
		tuesdayJourney[2] = T_J3;
		tuesdayJourney[3] = T_J4;
		tuesdayJourney[4] = T_J5;
		tuesdayJourney[5] = T_J6;
		tuesdayJourney[6] = T_J7;
		tuesdayJourney[7] = T_J8;
		tuesdayJourney[8] = T_J9;
		tuesdayJourney[9] = T_J10;
		tuesdayJourney[10] = T_J11;
		tuesdayJourney[11] = T_J12;
		tuesdayJourney[12] = T_J13;
		tuesdayJourney[13] = T_J14;
		tuesdayJourney[14] = T_J15;
		
		tuesdayJourneyValue[0] = T_J1_Val;
		tuesdayJourneyValue[1] = T_J2_Val;
		tuesdayJourneyValue[2] = T_J3_Val;
		tuesdayJourneyValue[3] = T_J4_Val;
		tuesdayJourneyValue[4] = T_J5_Val;
		tuesdayJourneyValue[5] = T_J6_Val;
		tuesdayJourneyValue[6] = T_J7_Val;
		tuesdayJourneyValue[7] = T_J8_Val;
		tuesdayJourneyValue[8] = T_J9_Val;
		tuesdayJourneyValue[9] = T_J10_Val;
		tuesdayJourneyValue[10] = T_J11_Val;
		tuesdayJourneyValue[11] = T_J12_Val;
		tuesdayJourneyValue[12] = T_J13_Val;
		tuesdayJourneyValue[13] = T_J14_Val;
		tuesdayJourneyValue[14] = T_J15_Val;
		
		//Wednesday (0-5)
		double W_J1 = Snippet.normDist(3.2);
		double W_J2 = Snippet.normDist(4.02);
		double W_J3 = Snippet.normDist(11.74);				
		double W_J4 = Snippet.normDist(27.02);
		double W_J5 = Snippet.normDist(0.96);
		double W_J6 = Snippet.normDist(1.36);
		double W_J7 = Snippet.normDist(20.86);
		double W_J8 = Snippet.normDist(8.45);
		double W_J9 = Snippet.normDist(28.88);
		double W_J10 = Snippet.normDist(15.67);
		double W_J11 = Snippet.normDist(1.98);
		
		double W_J1_Val = 0;
		double W_J2_Val = 0;
		double W_J3_Val = 0;
		double W_J4_Val = 0;
		double W_J5_Val = 0;
		double W_J6_Val = 0;
		double W_J7_Val = 0;
		double W_J8_Val = 0;
		double W_J9_Val = 0;
		double W_J10_Val = 0;
		double W_J11_Val = 0;
		
		if (J1_Val < 0.6) {
			W_J1_Val = W_J1*14.6;
		} else if (J1_Val < 0.8) {
			W_J1_Val = W_J1*11.7;
		} else {
			W_J1_Val = W_J1*17.52;
		}
		if (J2_Val < 0.6) {
			W_J2_Val = W_J2*14.6;
		} else if (J2_Val < 0.8) {
			W_J2_Val = W_J2*11.7;
		} else {
			W_J2_Val = W_J2*17.52;
		}
		if (J3_Val < 0.6) {
			W_J3_Val = W_J3*14.6;
		} else if (J3_Val < 0.8) {
			W_J3_Val = W_J3*11.7;
		} else {
			W_J3_Val = W_J3*17.52;
		}
		if (J4_Val < 0.6) {
			W_J4_Val = W_J4*14.6;
		} else if (J4_Val < 0.8) {
			W_J4_Val = W_J4*11.7;
		} else {
			W_J4_Val = W_J4*17.52;
		}
		if (J5_Val < 0.6) {
			W_J5_Val = W_J5*14.6;
		} else if (J5_Val < 0.8) {
			W_J5_Val = W_J5*11.7;
		} else {
			W_J5_Val = W_J5*17.52;
		}
		if (J6_Val < 0.6) {
			W_J6_Val = W_J6*14.6;
		} else if (J6_Val < 0.8) {
			W_J6_Val = W_J6*11.7;
		} else {
			W_J6_Val = W_J6*17.52;
		}
		if (J7_Val < 0.6) {
			W_J7_Val = W_J7*14.6;
		} else if (J7_Val < 0.8) {
			W_J7_Val = W_J7*11.7;
		} else {
			W_J7_Val = W_J7*17.52;
		}
		if (J8_Val < 0.6) {
			W_J8_Val = W_J8*14.6;
		} else if (J8_Val < 0.8) {
			W_J8_Val = W_J8*11.7;
		} else {
			W_J8_Val = W_J8*17.52;
		}
		if (J9_Val < 0.6) {
			W_J9_Val = W_J9*14.6;
		} else if (J9_Val < 0.8) {
			W_J9_Val = W_J9*11.7;
		} else {
			W_J9_Val = W_J9*17.52;
		}
		if (J10_Val < 0.6) {
			W_J10_Val = W_J10*14.6;
		} else if (J10_Val < 0.8) {
			W_J10_Val = W_J10*11.7;
		} else {
			W_J10_Val = W_J10*17.52;
		}
		if (J11_Val < 0.6) {
			W_J11_Val = W_J11*14.6;
		} else if (J11_Val < 0.8) {
			W_J11_Val = W_J11*11.7;
		} else {
			W_J11_Val = W_J11*17.52;
		}
		
		double wednesdayJourney[] = new double[11];
		double wednesdayJourneyValue[] = new double[11];
		wednesdayJourney[0] = W_J1;
		wednesdayJourney[1] = W_J2;
		wednesdayJourney[2] = W_J3;
		wednesdayJourney[3] = W_J4;
		wednesdayJourney[4] = W_J5;
		wednesdayJourney[5] = W_J6;
		wednesdayJourney[6] = W_J7;
		wednesdayJourney[7] = W_J8;
		wednesdayJourney[8] = W_J9;
		wednesdayJourney[9] = W_J10;
		wednesdayJourney[10] = W_J11;
		
		wednesdayJourneyValue[0] = W_J1_Val;
		wednesdayJourneyValue[1] = W_J2_Val;
		wednesdayJourneyValue[2] = W_J3_Val;
		wednesdayJourneyValue[3] = W_J4_Val;
		wednesdayJourneyValue[4] = W_J5_Val;
		wednesdayJourneyValue[5] = W_J6_Val;
		wednesdayJourneyValue[6] = W_J7_Val;
		wednesdayJourneyValue[7] = W_J8_Val;
		wednesdayJourneyValue[8] = W_J9_Val;
		wednesdayJourneyValue[9] = W_J10_Val;
		wednesdayJourneyValue[10] = W_J11_Val;
		
		//Thursday (0-4)
		double TH_J1 = Snippet.normDist(3.24);
		double TH_J2 = Snippet.normDist(3.02);
		double TH_J3 = Snippet.normDist(2.02);
		double TH_J4 = Snippet.normDist(0.8);
		double TH_J5 = Snippet.normDist(1.34);
		double TH_J6 = Snippet.normDist(0.26);
		double TH_J7 = Snippet.normDist(0.45);
		double TH_J8 = Snippet.normDist(0.64);
		double TH_J9 = Snippet.normDist(1.63);
		double TH_J10 = Snippet.normDist(0.43);
		double TH_J11 = Snippet.normDist(9.59);
		
		double TH_J1_Val = 0;
		double TH_J2_Val = 0;
		double TH_J3_Val = 0;
		double TH_J4_Val = 0;
		double TH_J5_Val = 0;
		double TH_J6_Val = 0;
		double TH_J7_Val = 0;
		double TH_J8_Val = 0;
		double TH_J9_Val = 0;
		double TH_J10_Val = 0;
		double TH_J11_Val = 0;
		
		if (J1_Val < 0.6) {
			TH_J1_Val = TH_J1*14.6;
		} else if (J1_Val < 0.8) {
			TH_J1_Val = TH_J1*11.7;
		} else {
			TH_J1_Val = TH_J1*17.52;
		}
		if (J2_Val < 0.6) {
			TH_J2_Val = TH_J2*14.6;
		} else if (J2_Val < 0.8) {
			TH_J2_Val = TH_J2*11.7;
		} else {
			TH_J2_Val = TH_J2*17.52;
		}
		if (J3_Val < 0.6) {
			TH_J3_Val = TH_J3*14.6;
		} else if (J3_Val < 0.8) {
			TH_J3_Val = TH_J3*11.7;
		} else {
			TH_J3_Val = TH_J3*17.52;
		}
		if (J4_Val < 0.6) {
			TH_J4_Val = TH_J4*14.6;
		} else if (J4_Val < 0.8) {
			TH_J4_Val = TH_J4*11.7;
		} else {
			TH_J4_Val = TH_J4*17.52;
		}
		if (J5_Val < 0.6) {
			TH_J5_Val = TH_J5*14.6;
		} else if (J5_Val < 0.8) {
			TH_J5_Val = TH_J5*11.7;
		} else {
			TH_J5_Val = TH_J5*17.52;
		}
		if (J6_Val < 0.6) {
			TH_J6_Val = TH_J6*14.6;
		} else if (J6_Val < 0.8) {
			TH_J6_Val = TH_J6*11.7;
		} else {
			TH_J6_Val = TH_J6*17.52;
		}
		if (J7_Val < 0.6) {
			TH_J7_Val = TH_J7*14.6;
		} else if (J7_Val < 0.8) {
			TH_J7_Val = TH_J7*11.7;
		} else {
			TH_J7_Val = TH_J7*17.52;
		}
		if (J8_Val < 0.6) {
			TH_J8_Val = TH_J8*14.6;
		} else if (J8_Val < 0.8) {
			TH_J8_Val = TH_J8*11.7;
		} else {
			TH_J8_Val = TH_J8*17.52;
		}
		if (J9_Val < 0.6) {
			TH_J9_Val = TH_J9*14.6;
		} else if (J9_Val < 0.8) {
			TH_J9_Val = TH_J9*11.7;
		} else {
			TH_J9_Val = TH_J9*17.52;
		}
		if (J10_Val < 0.6) {
			TH_J10_Val = TH_J10*14.6;
		} else if (J10_Val < 0.8) {
			TH_J10_Val = TH_J10*11.7;
		} else {
			TH_J10_Val = TH_J10*17.52;
		}
		if (J11_Val < 0.6) {
			TH_J11_Val = TH_J11*14.6;
		} else if (J11_Val < 0.8) {
			TH_J11_Val = TH_J11*11.7;
		} else {
			TH_J11_Val = TH_J11*17.52;
		}
		
		double thursdayJourney[] = new double[11];
		double thursdayJourneyValue[] = new double[11];
		thursdayJourney[0] = TH_J1;
		thursdayJourney[1] = TH_J2;
		thursdayJourney[2] = TH_J3;
		thursdayJourney[3] = TH_J4;
		thursdayJourney[4] = TH_J5;
		thursdayJourney[5] = TH_J6;
		thursdayJourney[6] = TH_J7;
		thursdayJourney[7] = TH_J8;
		thursdayJourney[8] = TH_J9;
		thursdayJourney[9] = TH_J10;
		thursdayJourney[10] = TH_J11;
		
		thursdayJourneyValue[0] = TH_J1_Val;
		thursdayJourneyValue[1] = TH_J2_Val;
		thursdayJourneyValue[2] = TH_J3_Val;
		thursdayJourneyValue[3] = TH_J4_Val;
		thursdayJourneyValue[4] = TH_J5_Val;
		thursdayJourneyValue[5] = TH_J6_Val;
		thursdayJourneyValue[6] = TH_J7_Val;
		thursdayJourneyValue[7] = TH_J8_Val;
		thursdayJourneyValue[8] = TH_J9_Val;
		thursdayJourneyValue[9] = TH_J10_Val;
		thursdayJourneyValue[10] = TH_J11_Val;
		
		//Friday (0-3)
		double F_J1 = Snippet.normDist(0.88);
		double F_J2 = Snippet.normDist(3.16);
		double F_J3 = Snippet.normDist(3.8);
		double F_J4 = Snippet.normDist(3.9);
		double F_J5 = Snippet.normDist(3);
		double F_J6 = Snippet.normDist(26.62);
		double F_J7 = Snippet.normDist(1.74);
		double F_J8 = Snippet.normDist(31.9);
		double F_J9 = Snippet.normDist(3.05);
		double F_J10 = Snippet.normDist(26.93);
		double F_J11 = Snippet.normDist(19.89);
		double F_J12 = Snippet.normDist(19.31);
		double F_J13 = Snippet.normDist(23.3);
		double F_J14 = Snippet.normDist(6.07);
		double F_J15 = Snippet.normDist(35.34);
		
		double F_J1_Val = 0;
		double F_J2_Val = 0;
		double F_J3_Val = 0;
		double F_J4_Val = 0;
		double F_J5_Val = 0;
		double F_J6_Val = 0;
		double F_J7_Val = 0;
		double F_J8_Val = 0;
		double F_J9_Val = 0;
		double F_J10_Val = 0;
		double F_J11_Val = 0;
		double F_J12_Val = 0;
		double F_J13_Val = 0;
		double F_J14_Val = 0;
		double F_J15_Val = 0;
		
		if (J1_Val < 0.6) {
			F_J1_Val = F_J1*14.6;
		} else if (J1_Val < 0.8) {
			F_J1_Val = F_J1*11.7;
		} else {
			F_J1_Val = F_J1*17.52;
		}
		if (J2_Val < 0.6) {
			F_J2_Val = F_J2*14.6;
		} else if (J2_Val < 0.8) {
			F_J2_Val = F_J2*11.7;
		} else {
			F_J2_Val = F_J2*17.52;
		}
		if (J3_Val < 0.6) {
			F_J3_Val = F_J3*14.6;
		} else if (J3_Val < 0.8) {
			F_J3_Val = F_J3*11.7;
		} else {
			F_J3_Val = F_J3*17.52;
		}
		if (J4_Val < 0.6) {
			F_J4_Val = F_J4*14.6;
		} else if (J4_Val < 0.8) {
			F_J4_Val = F_J4*11.7;
		} else {
			F_J4_Val = F_J4*17.52;
		}
		if (J5_Val < 0.6) {
			F_J5_Val = F_J5*14.6;
		} else if (J5_Val < 0.8) {
			F_J5_Val = F_J5*11.7;
		} else {
			F_J5_Val = F_J5*17.52;
		}
		if (J6_Val < 0.6) {
			F_J6_Val = F_J6*14.6;
		} else if (J6_Val < 0.8) {
			F_J6_Val = F_J6*11.7;
		} else {
			F_J6_Val = F_J6*17.52;
		}
		if (J7_Val < 0.6) {
			F_J7_Val = F_J7*14.6;
		} else if (J7_Val < 0.8) {
			F_J7_Val = F_J7*11.7;
		} else {
			F_J7_Val = F_J7*17.52;
		}
		if (J8_Val < 0.6) {
			F_J8_Val = F_J8*14.6;
		} else if (J8_Val < 0.8) {
			F_J8_Val = F_J8*11.7;
		} else {
			F_J8_Val = F_J8*17.52;
		}
		if (J9_Val < 0.6) {
			F_J9_Val = F_J9*14.6;
		} else if (J9_Val < 0.8) {
			F_J9_Val = F_J9*11.7;
		} else {
			F_J9_Val = F_J9*17.52;
		}
		if (J10_Val < 0.6) {
			F_J10_Val = F_J10*14.6;
		} else if (J10_Val < 0.8) {
			F_J10_Val = F_J10*11.7;
		} else {
			F_J10_Val = F_J10*17.52;
		}
		if (J11_Val < 0.6) {
			F_J11_Val = F_J11*14.6;
		} else if (J11_Val < 0.8) {
			F_J11_Val = F_J11*11.7;
		} else {
			F_J11_Val = F_J11*17.52;
		}
		if (J12_Val < 0.6) {
			F_J12_Val = F_J12*14.6;
		} else if (J12_Val < 0.8) {
			F_J12_Val = F_J12*11.7;
		} else {
			F_J12_Val = F_J12*17.52;
		}
		if (J13_Val < 0.6) {
			F_J13_Val = F_J13*14.6;
		} else if (J13_Val < 0.8) {
			F_J13_Val = F_J13*11.7;
		} else {
			F_J13_Val = F_J13*17.52;
		}
		if (J14_Val < 0.6) {
			F_J14_Val = F_J14*14.6;
		} else if (J14_Val < 0.8) {
			F_J14_Val = F_J14*11.7;
		} else {
			F_J14_Val = F_J14*17.52;
		}
		if (J15_Val < 0.6) {
			F_J15_Val = F_J15*14.6;
		} else if (J15_Val < 0.8) {
			F_J15_Val = F_J15*11.7;
		} else {
			F_J15_Val = F_J15*17.52;
		}
		
		double fridayJourney[] = new double[15];
		double fridayJourneyValue[] = new double[15];
		fridayJourney[0] = F_J1;
		fridayJourney[1] = F_J2;
		fridayJourney[2] = F_J3;
		fridayJourney[3] = F_J4;
		fridayJourney[4] = F_J5;
		fridayJourney[5] = F_J6;
		fridayJourney[6] = F_J7;
		fridayJourney[7] = F_J8;
		fridayJourney[8] = F_J9;
		fridayJourney[9] = F_J10;
		fridayJourney[10] = F_J11;
		fridayJourney[11] = F_J12;
		fridayJourney[12] = F_J13;
		fridayJourney[13] = F_J14;
		fridayJourney[14] = F_J15;
		
		fridayJourneyValue[0] = F_J1_Val;
		fridayJourneyValue[1] = F_J2_Val;
		fridayJourneyValue[2] = F_J3_Val;
		fridayJourneyValue[3] = F_J4_Val;
		fridayJourneyValue[4] = F_J5_Val;
		fridayJourneyValue[5] = F_J6_Val;
		fridayJourneyValue[6] = F_J7_Val;
		fridayJourneyValue[7] = F_J8_Val;
		fridayJourneyValue[8] = F_J9_Val;
		fridayJourneyValue[9] = F_J10_Val;
		fridayJourneyValue[10] = F_J11_Val;
		fridayJourneyValue[11] = F_J12_Val;
		fridayJourneyValue[12] = F_J13_Val;
		fridayJourneyValue[13] = F_J14_Val;
		fridayJourneyValue[14] = F_J15_Val;
		
		//Saturday (0-3)
		double S_J1 = Snippet.normDist(2);
		double S_J2 = Snippet.normDist(6.78);
		double S_J3 = Snippet.normDist(3.2);
		double S_J4 = Snippet.normDist(22.9);
		double S_J5 = Snippet.normDist(3.53);
		double S_J6 = Snippet.normDist(0.23);
		double S_J7 = Snippet.normDist(0.49);
		
		double S_J1_Val = 0;
		double S_J2_Val = 0;
		double S_J3_Val = 0;
		double S_J4_Val = 0;
		double S_J5_Val = 0;
		double S_J6_Val = 0;
		double S_J7_Val = 0;
		
		if (J1_Val < 0.6) {
			S_J1_Val = S_J1*14.6;
		} else if (J1_Val < 0.8) {
			S_J1_Val = S_J1*11.7;
		} else {
			S_J1_Val = S_J1*17.52;
		}
		if (J2_Val < 0.6) {
			S_J2_Val = S_J2*14.6;
		} else if (J2_Val < 0.8) {
			S_J2_Val = S_J2*11.7;
		} else {
			S_J2_Val = S_J2*17.52;
		}
		if (J3_Val < 0.6) {
			S_J3_Val = S_J3*14.6;
		} else if (J3_Val < 0.8) {
			S_J3_Val = S_J3*11.7;
		} else {
			S_J3_Val = S_J3*17.52;
		}
		if (J4_Val < 0.6) {
			S_J4_Val = S_J4*14.6;
		} else if (J4_Val < 0.8) {
			S_J4_Val = S_J4*11.7;
		} else {
			S_J4_Val = S_J4*17.52;
		}
		if (J5_Val < 0.6) {
			S_J5_Val = S_J5*14.6;
		} else if (J5_Val < 0.8) {
			S_J5_Val = S_J5*11.7;
		} else {
			S_J5_Val = S_J5*17.52;
		}
		if (J6_Val < 0.6) {
			S_J6_Val = S_J6*14.6;
		} else if (J6_Val < 0.8) {
			S_J6_Val = S_J6*11.7;
		} else {
			S_J6_Val = S_J6*17.52;
		}
		if (J7_Val < 0.6) {
			S_J7_Val = S_J7*14.6;
		} else if (J7_Val < 0.8) {
			S_J7_Val = S_J7*11.7;
		} else {
			S_J7_Val = S_J7*17.52;
		}
		
		double saturdayJourney[] = new double[7];
		double saturdayJourneyValue[] = new double[7];
		saturdayJourney[0] = S_J1;
		saturdayJourney[1] = S_J2;
		saturdayJourney[2] = S_J3;
		saturdayJourney[3] = S_J4;
		saturdayJourney[4] = S_J5;
		saturdayJourney[5] = S_J6;
		saturdayJourney[6] = S_J7;
				
		saturdayJourneyValue[0] = S_J1_Val;
		saturdayJourneyValue[1] = S_J2_Val;
		saturdayJourneyValue[2] = S_J3_Val;
		saturdayJourneyValue[3] = S_J4_Val;
		saturdayJourneyValue[4] = S_J5_Val;
		saturdayJourneyValue[5] = S_J6_Val;
		saturdayJourneyValue[6] = S_J7_Val;
				
		//Sunday (0-3)
		double SU_J1 = Snippet.normDist(7.94);
		double SU_J2 = Snippet.normDist(2.12);
		double SU_J3 = Snippet.normDist(12.98);
		
		double SU_J1_Val = 0;
		double SU_J2_Val = 0;
		double SU_J3_Val = 0;

		if (J1_Val < 0.6) {
			SU_J1_Val = SU_J1*14.6;
		} else if (J1_Val < 0.8) {
			SU_J1_Val = SU_J1*11.7;
		} else {
			SU_J1_Val = SU_J1*17.52;
		}
		if (J2_Val < 0.6) {
			SU_J2_Val = SU_J2*14.6;
		} else if (J2_Val < 0.8) {
			SU_J2_Val = SU_J2*11.7;
		} else {
			SU_J2_Val = SU_J2*17.52;
		}
		if (J3_Val < 0.6) {
			SU_J3_Val = SU_J3*14.6;
		} else if (J3_Val < 0.8) {
			SU_J3_Val = SU_J3*11.7;
		} else {
			SU_J3_Val = SU_J3*17.52;
		}
		
		double sundayJourney[] = new double[3];
		double sundayJourneyValue[] = new double[3];
		sundayJourney[0] = SU_J1;
		sundayJourney[1] = SU_J2;
		sundayJourney[2] = SU_J3;
				
		sundayJourneyValue[0] = SU_J1_Val;
		sundayJourneyValue[1] = SU_J2_Val;
		sundayJourneyValue[2] = SU_J3_Val;
		
		day = time.getDayName();
		// add task for the day
		Random random = new Random();
		
		switch (day) {
		case "Monday":
			totalJourney = random.nextInt(4 - 0 + 1);
			if (totalJourney > 0) {
				ArrayList<Integer> journeyLabel = new ArrayList<Integer>();
			    for (int i=1; i<12; i++) {
			    	journeyLabel.add(new Integer(i));
			    }
			    Collections.shuffle(journeyLabel);
			    for (int i=0; i<totalJourney; i++) {
					dayNeeds += mondayJourney[journeyLabel.get(i)-1];
					dayBudget += mondayJourneyValue[journeyLabel.get(i)-1]*budget_parameter;
					taskList.add(new Task(mondayJourneyValue[journeyLabel.get(i)-1], mondayJourney[journeyLabel.get(i)-1]));
				}
			}
			break;
		case "Tuesday":
			totalJourney = random.nextInt(3 - 0 + 1);
			if (totalJourney > 0) {
				ArrayList<Integer> journeyLabel = new ArrayList<Integer>();
			    for (int i=1; i<16; i++) {
			    	journeyLabel.add(new Integer(i));
			    }
			    Collections.shuffle(journeyLabel);
			    for (int i=0; i<totalJourney; i++) {
					dayNeeds += tuesdayJourney[journeyLabel.get(i)-1];
					dayBudget += tuesdayJourneyValue[journeyLabel.get(i)-1]*budget_parameter;
					taskList.add(new Task(tuesdayJourneyValue[journeyLabel.get(i)-1], tuesdayJourney[journeyLabel.get(i)-1]));
				}
			}
			break;
		case "Wednesday":
			totalJourney = random.nextInt(5 - 0 + 1);
			if (totalJourney > 0) {
				ArrayList<Integer> journeyLabel = new ArrayList<Integer>();
			    for (int i=1; i<12; i++) {
			    	journeyLabel.add(new Integer(i));
			    }
			    Collections.shuffle(journeyLabel);
			    for (int i=0; i<totalJourney; i++) {
					dayNeeds += wednesdayJourney[journeyLabel.get(i)-1];
					dayBudget += wednesdayJourneyValue[journeyLabel.get(i)-1]*budget_parameter;
					taskList.add(new Task(wednesdayJourneyValue[journeyLabel.get(i)-1], wednesdayJourney[journeyLabel.get(i)-1]));
				}
			}
			break;
		case "Thursday":
			totalJourney = random.nextInt(4 - 0 + 1);
			if (totalJourney > 0) {
				ArrayList<Integer> journeyLabel = new ArrayList<Integer>();
			    for (int i=1; i<12; i++) {
			    	journeyLabel.add(new Integer(i));
			    }
			    Collections.shuffle(journeyLabel);
			    for (int i=0; i<totalJourney; i++) {
					dayNeeds += thursdayJourney[journeyLabel.get(i)-1];
					dayBudget += thursdayJourneyValue[journeyLabel.get(i)-1]*budget_parameter;
					taskList.add(new Task(thursdayJourneyValue[journeyLabel.get(i)-1], thursdayJourney[journeyLabel.get(i)-1]));
				}
			}
			break;
		case "Friday":
			totalJourney = random.nextInt(3 - 0 + 1);
			if (totalJourney > 0) {
				ArrayList<Integer> journeyLabel = new ArrayList<Integer>();
			    for (int i=1; i<16; i++) {
			    	journeyLabel.add(new Integer(i));
			    }
			    Collections.shuffle(journeyLabel);
			    for (int i=0; i<totalJourney; i++) {
					dayNeeds += fridayJourney[journeyLabel.get(i)-1];
					dayBudget += fridayJourneyValue[journeyLabel.get(i)-1]*budget_parameter;
					taskList.add(new Task(fridayJourneyValue[journeyLabel.get(i)-1], fridayJourney[journeyLabel.get(i)-1]));
				}
			}
			break;
		case "Saturday:":
			totalJourney = random.nextInt(3 - 0 + 1);
			if (totalJourney > 0) {
				ArrayList<Integer> journeyLabel = new ArrayList<Integer>();
			    for (int i=1; i<8; i++) {
			    	journeyLabel.add(new Integer(i));
			    }
			    Collections.shuffle(journeyLabel);
			    for (int i=0; i<totalJourney; i++) {
					dayNeeds += saturdayJourney[journeyLabel.get(i)-1];
					dayBudget += saturdayJourneyValue[journeyLabel.get(i)-1]*budget_parameter;
					taskList.add(new Task(saturdayJourneyValue[journeyLabel.get(i)-1], saturdayJourney[journeyLabel.get(i)-1]));
				}
			}
			break;
		case "Sunday:":
			totalJourney = random.nextInt(3 - 0 + 1);
			if (totalJourney > 0) {
				ArrayList<Integer> journeyLabel = new ArrayList<Integer>();
			    for (int i=1; i<4; i++) {
			    	journeyLabel.add(new Integer(i));
			    }
			    Collections.shuffle(journeyLabel);
			    for (int i=0; i<totalJourney; i++) {
					dayNeeds += sundayJourney[journeyLabel.get(i)-1];
					dayBudget += sundayJourneyValue[journeyLabel.get(i)-1]*budget_parameter;
					taskList.add(new Task(sundayJourneyValue[journeyLabel.get(i)-1], sundayJourney[journeyLabel.get(i)-1]));
				}
			}
			break;
		}
		
		switch (time.getWeather()) {
		case 2: //rainy
			dayNeeds *= 1.15;
			dayBudget *= 1.15;
			for (Task temp : taskList) {
				temp.setValue(temp.getValue()*1.15);
				temp.setNeeds(temp.getNeeds()*1.15);
		    }
			break;
		case 3: //snow
			dayNeeds *= 1.3;
			dayBudget *= 1.3;
			for (Task temp : taskList) {
				temp.setValue(temp.getValue()*1.3);
				temp.setNeeds(temp.getNeeds()*1.3);
		    }
			break;
		}
		
		preferences.setAmount(dayNeeds*consumption);
		if (dayNeeds > 0) {
			preferences.setUnitPrice(dayBudget/(dayNeeds*consumption));
		} else {
			preferences.setUnitPrice(0);
		}
		Collections.sort(taskList);
//		for (Task temp : taskList) {
//			System.out.println(temp.getValue() + ": " + temp.getNeeds());
//	    }
//		System.out.println("==============================================");
	}
	
	public static void EVUSer_4041(Time time, User user, ElectricityBundle preferences, List<Task> taskList) {
		// necessary variables
		// user with utility 30% average, 50% higher, 20% lower
		double dayNeeds = 0; //miles
		double dayBudget = 0; //p/miles
		String day;
		double consumption = user.getCar().getConsumption();
		int totalJourney = 0;
		
		// possible journey (distance in miles)
		// Monday (0-1)
		double M_J1 = Snippet.normDist(52.44);
		double M_J2 = Snippet.normDist(20.93);
		double M_J3 = Snippet.normDist(14.08);
		double M_J4 = Snippet.normDist(0.3);
		double M_J5 = Snippet.normDist(0.79);
		
		double M_J1_Val = 0;
		double M_J2_Val = 0;
		double M_J3_Val = 0;
		double M_J4_Val = 0;
		double M_J5_Val = 0;
		
		if (J1_Val < 0.3) {
			M_J1_Val = M_J1*14.6;
		} else if (J1_Val < 0.5) {
			M_J1_Val = M_J1*11.7;
		} else {
			M_J1_Val = M_J1*17.52;
		}
		if (J2_Val < 0.3) {
			M_J2_Val = M_J2*14.6;
		} else if (J2_Val < 0.5) {
			M_J2_Val = M_J2*11.7;
		} else {
			M_J2_Val = M_J2*17.52;
		}
		if (J3_Val < 0.3) {
			M_J3_Val = M_J3*14.6;
		} else if (J3_Val < 0.5) {
			M_J3_Val = M_J3*11.7;
		} else {
			M_J3_Val = M_J3*17.52;
		}
		if (J4_Val < 0.3) {
			M_J4_Val = M_J4*14.6;
		} else if (J4_Val < 0.5) {
			M_J4_Val = M_J4*11.7;
		} else {
			M_J4_Val = M_J4*17.52;
		}
		if (J5_Val < 0.3) {
			M_J5_Val = M_J5*14.6;
		} else if (J5_Val < 0.5) {
			M_J5_Val = M_J5*11.7;
		} else {
			M_J5_Val = M_J5*17.52;
		}
		
		double mondayJourney[] = new double[5];
		double mondayJourneyValue[] = new double[5];
		mondayJourney[0] = M_J1;
		mondayJourney[1] = M_J2;
		mondayJourney[2] = M_J3;
		mondayJourney[3] = M_J4;
		mondayJourney[4] = M_J5;
		
		mondayJourneyValue[0] = M_J1_Val;
		mondayJourneyValue[1] = M_J2_Val;
		mondayJourneyValue[2] = M_J3_Val;
		mondayJourneyValue[3] = M_J4_Val;
		mondayJourneyValue[4] = M_J5_Val;
		
		//Tuesday (0-4)
		double T_J1 = Snippet.normDist(42.09);
		double T_J2 = Snippet.normDist(55.85);
		double T_J3 = Snippet.normDist(5.85);
		double T_J4 = Snippet.normDist(28.54);
		double T_J5 = Snippet.normDist(1.47);
		double T_J6 = Snippet.normDist(1.43);
		double T_J7 = Snippet.normDist(2.45);
		double T_J8 = Snippet.normDist(1.79);
		double T_J9 = Snippet.normDist(52.97);
		double T_J10 = Snippet.normDist(7.62);
		double T_J11 = Snippet.normDist(1.3);

		double T_J1_Val = 0;
		double T_J2_Val = 0;
		double T_J3_Val = 0;
		double T_J4_Val = 0;
		double T_J5_Val = 0;
		double T_J6_Val = 0;
		double T_J7_Val = 0;
		double T_J8_Val = 0;
		double T_J9_Val = 0;
		double T_J10_Val = 0;
		double T_J11_Val = 0;
		
		if (J1_Val < 0.3) {
			T_J1_Val = T_J1*14.6;
		} else if (J1_Val < 0.5) {
			T_J1_Val = T_J1*11.7;
		} else {
			T_J1_Val = T_J1*17.52;
		}
		if (J2_Val < 0.3) {
			T_J2_Val = T_J2*14.6;
		} else if (J2_Val < 0.5) {
			T_J2_Val = T_J2*11.7;
		} else {
			T_J2_Val = T_J2*17.52;
		}
		if (J3_Val < 0.3) {
			T_J3_Val = T_J3*14.6;
		} else if (J3_Val < 0.5) {
			T_J3_Val = T_J3*11.7;
		} else {
			T_J3_Val = T_J3*17.52;
		}
		if (J4_Val < 0.3) {
			T_J4_Val = T_J4*14.6;
		} else if (J4_Val < 0.5) {
			T_J4_Val = T_J4*11.7;
		} else {
			T_J4_Val = T_J4*17.52;
		}
		if (J5_Val < 0.3) {
			T_J5_Val = T_J5*14.6;
		} else if (J5_Val < 0.5) {
			T_J5_Val = T_J5*11.7;
		} else {
			T_J5_Val = T_J5*17.52;
		}
		if (J6_Val < 0.3) {
			T_J6_Val = T_J6*14.6;
		} else if (J6_Val < 0.5) {
			T_J6_Val = T_J6*11.7;
		} else {
			T_J6_Val = T_J6*17.52;
		}
		if (J7_Val < 0.3) {
			T_J7_Val = T_J7*14.6;
		} else if (J7_Val < 0.5) {
			T_J7_Val = T_J7*11.7;
		} else {
			T_J7_Val = T_J7*17.52;
		}
		if (J8_Val < 0.3) {
			T_J8_Val = T_J8*14.6;
		} else if (J8_Val < 0.5) {
			T_J8_Val = T_J8*11.7;
		} else {
			T_J8_Val = T_J8*17.52;
		}
		if (J9_Val < 0.3) {
			T_J9_Val = T_J9*14.6;
		} else if (J9_Val < 0.5) {
			T_J9_Val = T_J9*11.7;
		} else {
			T_J9_Val = T_J9*17.52;
		}
		if (J10_Val < 0.3) {
			T_J10_Val = T_J10*14.6;
		} else if (J10_Val < 0.5) {
			T_J10_Val = T_J10*11.7;
		} else {
			T_J10_Val = T_J10*17.52;
		}
		if (J11_Val < 0.3) {
			T_J11_Val = T_J11*14.6;
		} else if (J11_Val < 0.5) {
			T_J11_Val = T_J11*11.7;
		} else {
			T_J11_Val = T_J11*17.52;
		}
		
		double tuesdayJourney[] = new double[11];
		double tuesdayJourneyValue[] = new double[11];
		tuesdayJourney[0] = T_J1;
		tuesdayJourney[1] = T_J2;
		tuesdayJourney[2] = T_J3;
		tuesdayJourney[3] = T_J4;
		tuesdayJourney[4] = T_J5;
		tuesdayJourney[5] = T_J6;
		tuesdayJourney[6] = T_J7;
		tuesdayJourney[7] = T_J8;
		tuesdayJourney[8] = T_J9;
		tuesdayJourney[9] = T_J10;
		tuesdayJourney[10] = T_J11;

		tuesdayJourneyValue[0] = T_J1_Val;
		tuesdayJourneyValue[1] = T_J2_Val;
		tuesdayJourneyValue[2] = T_J3_Val;
		tuesdayJourneyValue[3] = T_J4_Val;
		tuesdayJourneyValue[4] = T_J5_Val;
		tuesdayJourneyValue[5] = T_J6_Val;
		tuesdayJourneyValue[6] = T_J7_Val;
		tuesdayJourneyValue[7] = T_J8_Val;
		tuesdayJourneyValue[8] = T_J9_Val;
		tuesdayJourneyValue[9] = T_J10_Val;
		tuesdayJourneyValue[10] = T_J11_Val;
		
		//Wednesday (0-3)
		double W_J1 = Snippet.normDist(42.13);
		double W_J2 = Snippet.normDist(0.75);
		double W_J3 = Snippet.normDist(35.67);				
		double W_J4 = Snippet.normDist(93.35);
		double W_J5 = Snippet.normDist(53.45);
		double W_J6 = Snippet.normDist(2.85);
		double W_J7 = Snippet.normDist(21.06);
		double W_J8 = Snippet.normDist(77.53);
		double W_J9 = Snippet.normDist(10.73);
		double W_J10 = Snippet.normDist(12.43);
		
		double W_J1_Val = 0;
		double W_J2_Val = 0;
		double W_J3_Val = 0;
		double W_J4_Val = 0;
		double W_J5_Val = 0;
		double W_J6_Val = 0;
		double W_J7_Val = 0;
		double W_J8_Val = 0;
		double W_J9_Val = 0;
		double W_J10_Val = 0;
		
		if (J1_Val < 0.3) {
			W_J1_Val = W_J1*14.6;
		} else if (J1_Val < 0.5) {
			W_J1_Val = W_J1*11.7;
		} else {
			W_J1_Val = W_J1*17.52;
		}
		if (J2_Val < 0.3) {
			W_J2_Val = W_J2*14.6;
		} else if (J2_Val < 0.5) {
			W_J2_Val = W_J2*11.7;
		} else {
			W_J2_Val = W_J2*17.52;
		}
		if (J3_Val < 0.3) {
			W_J3_Val = W_J3*14.6;
		} else if (J3_Val < 0.5) {
			W_J3_Val = W_J3*11.7;
		} else {
			W_J3_Val = W_J3*17.52;
		}
		if (J4_Val < 0.3) {
			W_J4_Val = W_J4*14.6;
		} else if (J4_Val < 0.5) {
			W_J4_Val = W_J4*11.7;
		} else {
			W_J4_Val = W_J4*17.52;
		}
		if (J5_Val < 0.3) {
			W_J5_Val = W_J5*14.6;
		} else if (J5_Val < 0.5) {
			W_J5_Val = W_J5*11.7;
		} else {
			W_J5_Val = W_J5*17.52;
		}
		if (J6_Val < 0.3) {
			W_J6_Val = W_J6*14.6;
		} else if (J6_Val < 0.5) {
			W_J6_Val = W_J6*11.7;
		} else {
			W_J6_Val = W_J6*17.52;
		}
		if (J7_Val < 0.3) {
			W_J7_Val = W_J7*14.6;
		} else if (J7_Val < 0.5) {
			W_J7_Val = W_J7*11.7;
		} else {
			W_J7_Val = W_J7*17.52;
		}
		if (J8_Val < 0.3) {
			W_J8_Val = W_J8*14.6;
		} else if (J8_Val < 0.5) {
			W_J8_Val = W_J8*11.7;
		} else {
			W_J8_Val = W_J8*17.52;
		}
		if (J9_Val < 0.3) {
			W_J9_Val = W_J9*14.6;
		} else if (J9_Val < 0.5) {
			W_J9_Val = W_J9*11.7;
		} else {
			W_J9_Val = W_J9*17.52;
		}
		if (J10_Val < 0.3) {
			W_J10_Val = W_J10*14.6;
		} else if (J10_Val < 0.5) {
			W_J10_Val = W_J10*11.7;
		} else {
			W_J10_Val = W_J10*17.52;
		}
		
		double wednesdayJourney[] = new double[10];
		double wednesdayJourneyValue[] = new double[10];
		wednesdayJourney[0] = W_J1;
		wednesdayJourney[1] = W_J2;
		wednesdayJourney[2] = W_J3;
		wednesdayJourney[3] = W_J4;
		wednesdayJourney[4] = W_J5;
		wednesdayJourney[5] = W_J6;
		wednesdayJourney[6] = W_J7;
		wednesdayJourney[7] = W_J8;
		wednesdayJourney[8] = W_J9;
		wednesdayJourney[9] = W_J10;
		
		wednesdayJourneyValue[0] = W_J1_Val;
		wednesdayJourneyValue[1] = W_J2_Val;
		wednesdayJourneyValue[2] = W_J3_Val;
		wednesdayJourneyValue[3] = W_J4_Val;
		wednesdayJourneyValue[4] = W_J5_Val;
		wednesdayJourneyValue[5] = W_J6_Val;
		wednesdayJourneyValue[6] = W_J7_Val;
		wednesdayJourneyValue[7] = W_J8_Val;
		wednesdayJourneyValue[8] = W_J9_Val;
		wednesdayJourneyValue[9] = W_J10_Val;
		
		//Thursday (0-4)
		double TH_J1 = Snippet.normDist(43.9);
		double TH_J2 = Snippet.normDist(6);
		double TH_J3 = Snippet.normDist(40.41);
		double TH_J4 = Snippet.normDist(31.86);
		double TH_J5 = Snippet.normDist(2.42);
		double TH_J6 = Snippet.normDist(43.22);
		double TH_J7 = Snippet.normDist(15.65);
		double TH_J8 = Snippet.normDist(4.34);
		double TH_J9 = Snippet.normDist(4.41);
		double TH_J10 = Snippet.normDist(35.16);
		double TH_J11 = Snippet.normDist(54.22);
		double TH_J12 = Snippet.normDist(22.23);
		double TH_J13 = Snippet.normDist(1.65);
		double TH_J14 = Snippet.normDist(37.44);
		double TH_J15 = Snippet.normDist(25.8);
		
		double TH_J1_Val = 0;
		double TH_J2_Val = 0;
		double TH_J3_Val = 0;
		double TH_J4_Val = 0;
		double TH_J5_Val = 0;
		double TH_J6_Val = 0;
		double TH_J7_Val = 0;
		double TH_J8_Val = 0;
		double TH_J9_Val = 0;
		double TH_J10_Val = 0;
		double TH_J11_Val = 0;
		double TH_J12_Val = 0;
		double TH_J13_Val = 0;
		double TH_J14_Val = 0;
		double TH_J15_Val = 0;
		
		if (J1_Val < 0.3) {
			TH_J1_Val = TH_J1*14.6;
		} else if (J1_Val < 0.5) {
			TH_J1_Val = TH_J1*11.7;
		} else {
			TH_J1_Val = TH_J1*17.52;
		}
		if (J2_Val < 0.3) {
			TH_J2_Val = TH_J2*14.6;
		} else if (J2_Val < 0.5) {
			TH_J2_Val = TH_J2*11.7;
		} else {
			TH_J2_Val = TH_J2*17.52;
		}
		if (J3_Val < 0.3) {
			TH_J3_Val = TH_J3*14.6;
		} else if (J3_Val < 0.5) {
			TH_J3_Val = TH_J3*11.7;
		} else {
			TH_J3_Val = TH_J3*17.52;
		}
		if (J4_Val < 0.3) {
			TH_J4_Val = TH_J4*14.6;
		} else if (J4_Val < 0.5) {
			TH_J4_Val = TH_J4*11.7;
		} else {
			TH_J4_Val = TH_J4*17.52;
		}
		if (J5_Val < 0.3) {
			TH_J5_Val = TH_J5*14.6;
		} else if (J5_Val < 0.5) {
			TH_J5_Val = TH_J5*11.7;
		} else {
			TH_J5_Val = TH_J5*17.52;
		}
		if (J6_Val < 0.3) {
			TH_J6_Val = TH_J6*14.6;
		} else if (J6_Val < 0.5) {
			TH_J6_Val = TH_J6*11.7;
		} else {
			TH_J6_Val = TH_J6*17.52;
		}
		if (J7_Val < 0.3) {
			TH_J7_Val = TH_J7*14.6;
		} else if (J7_Val < 0.5) {
			TH_J7_Val = TH_J7*11.7;
		} else {
			TH_J7_Val = TH_J7*17.52;
		}
		if (J8_Val < 0.3) {
			TH_J8_Val = TH_J8*14.6;
		} else if (J8_Val < 0.5) {
			TH_J8_Val = TH_J8*11.7;
		} else {
			TH_J8_Val = TH_J8*17.52;
		}
		if (J9_Val < 0.3) {
			TH_J9_Val = TH_J9*14.6;
		} else if (J9_Val < 0.5) {
			TH_J9_Val = TH_J9*11.7;
		} else {
			TH_J9_Val = TH_J9*17.52;
		}
		if (J10_Val < 0.3) {
			TH_J10_Val = TH_J10*14.6;
		} else if (J10_Val < 0.5) {
			TH_J10_Val = TH_J10*11.7;
		} else {
			TH_J10_Val = TH_J10*17.52;
		}
		if (J11_Val < 0.3) {
			TH_J11_Val = TH_J11*14.6;
		} else if (J11_Val < 0.5) {
			TH_J11_Val = TH_J11*11.7;
		} else {
			TH_J11_Val = TH_J11*17.52;
		}
		if (J12_Val < 0.3) {
			TH_J12_Val = TH_J12*14.6;
		} else if (J12_Val < 0.5) {
			TH_J12_Val = TH_J12*11.7;
		} else {
			TH_J12_Val = TH_J12*17.52;
		}
		if (J13_Val < 0.3) {
			TH_J13_Val = TH_J13*14.6;
		} else if (J13_Val < 0.5) {
			TH_J13_Val = TH_J13*11.7;
		} else {
			TH_J13_Val = TH_J13*17.52;
		}
		if (J14_Val < 0.3) {
			TH_J14_Val = TH_J14*14.6;
		} else if (J14_Val < 0.5) {
			TH_J14_Val = TH_J14*11.7;
		} else {
			TH_J14_Val = TH_J14*17.52;
		}
		if (J15_Val < 0.3) {
			TH_J15_Val = TH_J15*14.6;
		} else if (J15_Val < 0.5) {
			TH_J15_Val = TH_J15*11.7;
		} else {
			TH_J15_Val = TH_J15*17.52;
		}
		
		double thursdayJourney[] = new double[15];
		double thursdayJourneyValue[] = new double[15];
		thursdayJourney[0] = TH_J1;
		thursdayJourney[1] = TH_J2;
		thursdayJourney[2] = TH_J3;
		thursdayJourney[3] = TH_J4;
		thursdayJourney[4] = TH_J5;
		thursdayJourney[5] = TH_J6;
		thursdayJourney[6] = TH_J7;
		thursdayJourney[7] = TH_J8;
		thursdayJourney[8] = TH_J9;
		thursdayJourney[9] = TH_J10;
		thursdayJourney[10] = TH_J11;
		thursdayJourney[11] = TH_J12;
		thursdayJourney[12] = TH_J13;
		thursdayJourney[13] = TH_J14;
		thursdayJourney[14] = TH_J15;
		
		thursdayJourneyValue[0] = TH_J1_Val;
		thursdayJourneyValue[1] = TH_J2_Val;
		thursdayJourneyValue[2] = TH_J3_Val;
		thursdayJourneyValue[3] = TH_J4_Val;
		thursdayJourneyValue[4] = TH_J5_Val;
		thursdayJourneyValue[5] = TH_J6_Val;
		thursdayJourneyValue[6] = TH_J7_Val;
		thursdayJourneyValue[7] = TH_J8_Val;
		thursdayJourneyValue[8] = TH_J9_Val;
		thursdayJourneyValue[9] = TH_J10_Val;
		thursdayJourneyValue[10] = TH_J11_Val;
		thursdayJourneyValue[11] = TH_J12_Val;
		thursdayJourneyValue[12] = TH_J13_Val;
		thursdayJourneyValue[13] = TH_J14_Val;
		thursdayJourneyValue[14] = TH_J15_Val;
		
		//Friday (0-2)
		double F_J1 = Snippet.normDist(54.14);
		double F_J2 = Snippet.normDist(42.86);
		double F_J3 = Snippet.normDist(4.31);
		double F_J4 = Snippet.normDist(42.18);
		double F_J5 = Snippet.normDist(5.98);
		double F_J6 = Snippet.normDist(10.07);
		double F_J7 = Snippet.normDist(47.8);
		double F_J8 = Snippet.normDist(16.29);
		double F_J9 = Snippet.normDist(19.47);
		double F_J10 = Snippet.normDist(2.12);
		double F_J11 = Snippet.normDist(1.94);
		
		double F_J1_Val = 0;
		double F_J2_Val = 0;
		double F_J3_Val = 0;
		double F_J4_Val = 0;
		double F_J5_Val = 0;
		double F_J6_Val = 0;
		double F_J7_Val = 0;
		double F_J8_Val = 0;
		double F_J9_Val = 0;
		double F_J10_Val = 0;
		double F_J11_Val = 0;
		
		if (J1_Val < 0.3) {
			F_J1_Val = F_J1*14.6;
		} else if (J1_Val < 0.5) {
			F_J1_Val = F_J1*11.7;
		} else {
			F_J1_Val = F_J1*17.52;
		}
		if (J2_Val < 0.3) {
			F_J2_Val = F_J2*14.6;
		} else if (J2_Val < 0.5) {
			F_J2_Val = F_J2*11.7;
		} else {
			F_J2_Val = F_J2*17.52;
		}
		if (J3_Val < 0.3) {
			F_J3_Val = F_J3*14.6;
		} else if (J3_Val < 0.5) {
			F_J3_Val = F_J3*11.7;
		} else {
			F_J3_Val = F_J3*17.52;
		}
		if (J4_Val < 0.3) {
			F_J4_Val = F_J4*14.6;
		} else if (J4_Val < 0.5) {
			F_J4_Val = F_J4*11.7;
		} else {
			F_J4_Val = F_J4*17.52;
		}
		if (J5_Val < 0.3) {
			F_J5_Val = F_J5*14.6;
		} else if (J5_Val < 0.5) {
			F_J5_Val = F_J5*11.7;
		} else {
			F_J5_Val = F_J5*17.52;
		}
		if (J6_Val < 0.3) {
			F_J6_Val = F_J6*14.6;
		} else if (J6_Val < 0.5) {
			F_J6_Val = F_J6*11.7;
		} else {
			F_J6_Val = F_J6*17.52;
		}
		if (J7_Val < 0.3) {
			F_J7_Val = F_J7*14.6;
		} else if (J7_Val < 0.5) {
			F_J7_Val = F_J7*11.7;
		} else {
			F_J7_Val = F_J7*17.52;
		}
		if (J8_Val < 0.3) {
			F_J8_Val = F_J8*14.6;
		} else if (J8_Val < 0.5) {
			F_J8_Val = F_J8*11.7;
		} else {
			F_J8_Val = F_J8*17.52;
		}
		if (J9_Val < 0.3) {
			F_J9_Val = F_J9*14.6;
		} else if (J9_Val < 0.5) {
			F_J9_Val = F_J9*11.7;
		} else {
			F_J9_Val = F_J9*17.52;
		}
		if (J10_Val < 0.3) {
			F_J10_Val = F_J10*14.6;
		} else if (J10_Val < 0.5) {
			F_J10_Val = F_J10*11.7;
		} else {
			F_J10_Val = F_J10*17.52;
		}
		if (J11_Val < 0.3) {
			F_J11_Val = F_J11*14.6;
		} else if (J11_Val < 0.5) {
			F_J11_Val = F_J11*11.7;
		} else {
			F_J11_Val = F_J11*17.52;
		}
		
		double fridayJourney[] = new double[11];
		double fridayJourneyValue[] = new double[11];
		fridayJourney[0] = F_J1;
		fridayJourney[1] = F_J2;
		fridayJourney[2] = F_J3;
		fridayJourney[3] = F_J4;
		fridayJourney[4] = F_J5;
		fridayJourney[5] = F_J6;
		fridayJourney[6] = F_J7;
		fridayJourney[7] = F_J8;
		fridayJourney[8] = F_J9;
		fridayJourney[9] = F_J10;
		fridayJourney[10] = F_J11;
		
		fridayJourneyValue[0] = F_J1_Val;
		fridayJourneyValue[1] = F_J2_Val;
		fridayJourneyValue[2] = F_J3_Val;
		fridayJourneyValue[3] = F_J4_Val;
		fridayJourneyValue[4] = F_J5_Val;
		fridayJourneyValue[5] = F_J6_Val;
		fridayJourneyValue[6] = F_J7_Val;
		fridayJourneyValue[7] = F_J8_Val;
		fridayJourneyValue[8] = F_J9_Val;
		fridayJourneyValue[9] = F_J10_Val;
		fridayJourneyValue[10] = F_J11_Val;
		
		//Saturday (0-4)
		double S_J1 = Snippet.normDist(15.39);
		double S_J2 = Snippet.normDist(9.95);
		double S_J3 = Snippet.normDist(10.29);
		double S_J4 = Snippet.normDist(4.4);
		double S_J5 = Snippet.normDist(14.73);
		double S_J6 = Snippet.normDist(2.86);
		double S_J7 = Snippet.normDist(21.01);
		double S_J8 = Snippet.normDist(5.64);
		double S_J9 = Snippet.normDist(5.32);
		double S_J10 = Snippet.normDist(0.89);
		double S_J11 = Snippet.normDist(13.41);
		
		double S_J1_Val = 0;
		double S_J2_Val = 0;
		double S_J3_Val = 0;
		double S_J4_Val = 0;
		double S_J5_Val = 0;
		double S_J6_Val = 0;
		double S_J7_Val = 0;
		double S_J8_Val = 0;
		double S_J9_Val = 0;
		double S_J10_Val = 0;
		double S_J11_Val = 0;
		
		if (J1_Val < 0.3) {
			S_J1_Val = S_J1*14.6;
		} else if (J1_Val < 0.5) {
			S_J1_Val = S_J1*11.7;
		} else {
			S_J1_Val = S_J1*17.52;
		}
		if (J2_Val < 0.3) {
			S_J2_Val = S_J2*14.6;
		} else if (J2_Val < 0.5) {
			S_J2_Val = S_J2*11.7;
		} else {
			S_J2_Val = S_J2*17.52;
		}
		if (J3_Val < 0.3) {
			S_J3_Val = S_J3*14.6;
		} else if (J3_Val < 0.5) {
			S_J3_Val = S_J3*11.7;
		} else {
			S_J3_Val = S_J3*17.52;
		}
		if (J4_Val < 0.3) {
			S_J4_Val = S_J4*14.6;
		} else if (J4_Val < 0.5) {
			S_J4_Val = S_J4*11.7;
		} else {
			S_J4_Val = S_J4*17.52;
		}
		if (J5_Val < 0.3) {
			S_J5_Val = S_J5*14.6;
		} else if (J5_Val < 0.5) {
			S_J5_Val = S_J5*11.7;
		} else {
			S_J5_Val = S_J5*17.52;
		}
		if (J6_Val < 0.3) {
			S_J6_Val = S_J6*14.6;
		} else if (J6_Val < 0.5) {
			S_J6_Val = S_J6*11.7;
		} else {
			S_J6_Val = S_J6*17.52;
		}
		if (J7_Val < 0.3) {
			S_J7_Val = S_J7*14.6;
		} else if (J7_Val < 0.5) {
			S_J7_Val = S_J7*11.7;
		} else {
			S_J7_Val = S_J7*17.52;
		}
		if (J8_Val < 0.3) {
			S_J8_Val = S_J8*14.6;
		} else if (J8_Val < 0.5) {
			S_J8_Val = S_J8*11.7;
		} else {
			S_J8_Val = S_J8*17.52;
		}
		if (J9_Val < 0.3) {
			S_J9_Val = S_J9*14.6;
		} else if (J9_Val < 0.5) {
			S_J9_Val = S_J9*11.7;
		} else {
			S_J9_Val = S_J9*17.52;
		}
		if (J10_Val < 0.3) {
			S_J10_Val = S_J10*14.6;
		} else if (J10_Val < 0.5) {
			S_J10_Val = S_J10*11.7;
		} else {
			S_J10_Val = S_J10*17.52;
		}
		if (J11_Val < 0.3) {
			S_J11_Val = S_J11*14.6;
		} else if (J11_Val < 0.5) {
			S_J11_Val = S_J11*11.7;
		} else {
			S_J11_Val = S_J11*17.52;
		}
		
		double saturdayJourney[] = new double[11];
		double saturdayJourneyValue[] = new double[11];
		saturdayJourney[0] = S_J1;
		saturdayJourney[1] = S_J2;
		saturdayJourney[2] = S_J3;
		saturdayJourney[3] = S_J4;
		saturdayJourney[4] = S_J5;
		saturdayJourney[5] = S_J6;
		saturdayJourney[6] = S_J7;
		saturdayJourney[7] = S_J8;
		saturdayJourney[8] = S_J9;
		saturdayJourney[9] = S_J10;
		saturdayJourney[10] = S_J11;
				
		saturdayJourneyValue[0] = S_J1_Val;
		saturdayJourneyValue[1] = S_J2_Val;
		saturdayJourneyValue[2] = S_J3_Val;
		saturdayJourneyValue[3] = S_J4_Val;
		saturdayJourneyValue[4] = S_J5_Val;
		saturdayJourneyValue[5] = S_J6_Val;
		saturdayJourneyValue[6] = S_J7_Val;
		saturdayJourneyValue[7] = S_J8_Val;
		saturdayJourneyValue[8] = S_J9_Val;
		saturdayJourneyValue[9] = S_J10_Val;
		saturdayJourneyValue[10] = S_J11_Val;
		
		//Sunday (0-5)
		double SU_J1 = Snippet.normDist(9.5);
		double SU_J2 = Snippet.normDist(2.48);
		double SU_J3 = Snippet.normDist(1.92);
		double SU_J4 = Snippet.normDist(34.35);
		double SU_J5 = Snippet.normDist(4.11);
		double SU_J6 = Snippet.normDist(17.24);
		double SU_J7 = Snippet.normDist(7.44);
		double SU_J8 = Snippet.normDist(5.13);
		double SU_J9 = Snippet.normDist(6.6);
		double SU_J10 = Snippet.normDist(0.33);
		double SU_J11 = Snippet.normDist(0.95);
		double SU_J12 = Snippet.normDist(20.66);
		double SU_J13 = Snippet.normDist(1.66);
		double SU_J14 = Snippet.normDist(7.13);
		double SU_J15 = Snippet.normDist(7.78);
		double SU_J16 = Snippet.normDist(20.42);
		double SU_J17 = Snippet.normDist(0.39);
		double SU_J18 = Snippet.normDist(12.34);
		double SU_J19 = Snippet.normDist(2.9);
		
		double SU_J1_Val = 0;
		double SU_J2_Val = 0;
		double SU_J3_Val = 0;
		double SU_J4_Val = 0;
		double SU_J5_Val = 0;
		double SU_J6_Val = 0;
		double SU_J7_Val = 0;
		double SU_J8_Val = 0;
		double SU_J9_Val = 0;
		double SU_J10_Val = 0;
		double SU_J11_Val = 0;
		double SU_J12_Val = 0;
		double SU_J13_Val = 0;
		double SU_J14_Val = 0;
		double SU_J15_Val = 0;
		double SU_J16_Val = 0;
		double SU_J17_Val = 0;
		double SU_J18_Val = 0;
		double SU_J19_Val = 0;
		
		if (J1_Val < 0.3) {
			SU_J1_Val = SU_J1*14.6;
		} else if (J1_Val < 0.5) {
			SU_J1_Val = SU_J1*11.7;
		} else {
			SU_J1_Val = SU_J1*17.52;
		}
		if (J2_Val < 0.3) {
			SU_J2_Val = SU_J2*14.6;
		} else if (J2_Val < 0.5) {
			SU_J2_Val = SU_J2*11.7;
		} else {
			SU_J2_Val = SU_J2*17.52;
		}
		if (J3_Val < 0.3) {
			SU_J3_Val = SU_J3*14.6;
		} else if (J3_Val < 0.5) {
			SU_J3_Val = SU_J3*11.7;
		} else {
			SU_J3_Val = SU_J3*17.52;
		}
		if (J4_Val < 0.3) {
			SU_J4_Val = SU_J4*14.6;
		} else if (J4_Val < 0.5) {
			SU_J4_Val = SU_J4*11.7;
		} else {
			SU_J4_Val = SU_J4*17.52;
		}
		if (J5_Val < 0.3) {
			SU_J5_Val = SU_J5*14.6;
		} else if (J5_Val < 0.5) {
			SU_J5_Val = SU_J5*11.7;
		} else {
			SU_J5_Val = SU_J5*17.52;
		}
		if (J6_Val < 0.3) {
			SU_J6_Val = SU_J6*14.6;
		} else if (J6_Val < 0.5) {
			SU_J6_Val = SU_J6*11.7;
		} else {
			SU_J6_Val = SU_J6*17.52;
		}
		if (J7_Val < 0.3) {
			SU_J7_Val = SU_J7*14.6;
		} else if (J7_Val < 0.5) {
			SU_J7_Val = SU_J7*11.7;
		} else {
			SU_J7_Val = SU_J7*17.52;
		}
		if (J8_Val < 0.3) {
			SU_J8_Val = SU_J8*14.6;
		} else if (J8_Val < 0.5) {
			SU_J8_Val = SU_J8*11.7;
		} else {
			SU_J8_Val = SU_J8*17.52;
		}
		if (J9_Val < 0.3) {
			SU_J9_Val = SU_J9*14.6;
		} else if (J9_Val < 0.5) {
			SU_J9_Val = SU_J9*11.7;
		} else {
			SU_J9_Val = SU_J9*17.52;
		}
		if (J10_Val < 0.3) {
			SU_J10_Val = SU_J10*14.6;
		} else if (J10_Val < 0.5) {
			SU_J10_Val = SU_J10*11.7;
		} else {
			SU_J10_Val = SU_J10*17.52;
		}
		if (J11_Val < 0.3) {
			SU_J11_Val = SU_J11*14.6;
		} else if (J11_Val < 0.5) {
			SU_J11_Val = SU_J11*11.7;
		} else {
			SU_J11_Val = SU_J11*17.52;
		}
		if (J12_Val < 0.3) {
			SU_J12_Val = SU_J12*14.6;
		} else if (J12_Val < 0.5) {
			SU_J12_Val = SU_J12*11.7;
		} else {
			SU_J12_Val = SU_J12*17.52;
		}
		if (J13_Val < 0.3) {
			SU_J13_Val = SU_J13*14.6;
		} else if (J13_Val < 0.5) {
			SU_J13_Val = SU_J13*11.7;
		} else {
			SU_J13_Val = SU_J13*17.52;
		}
		if (J14_Val < 0.3) {
			SU_J14_Val = SU_J14*14.6;
		} else if (J14_Val < 0.5) {
			SU_J14_Val = SU_J14*11.7;
		} else {
			SU_J14_Val = SU_J14*17.52;
		}
		if (J15_Val < 0.3) {
			SU_J15_Val = SU_J15*14.6;
		} else if (J15_Val < 0.5) {
			SU_J15_Val = SU_J15*11.7;
		} else {
			SU_J15_Val = SU_J15*17.52;
		}
		if (J16_Val < 0.3) {
			SU_J16_Val = SU_J16*14.6;
		} else if (J16_Val < 0.5) {
			SU_J16_Val = SU_J16*11.7;
		} else {
			SU_J16_Val = SU_J16*17.52;
		}
		if (J17_Val < 0.3) {
			SU_J17_Val = SU_J17*14.6;
		} else if (J17_Val < 0.5) {
			SU_J17_Val = SU_J17*11.7;
		} else {
			SU_J17_Val = SU_J17*17.52;
		}
		if (J18_Val < 0.3) {
			SU_J18_Val = SU_J18*14.6;
		} else if (J18_Val < 0.5) {
			SU_J18_Val = SU_J18*11.7;
		} else {
			SU_J18_Val = SU_J18*17.52;
		}
		if (J19_Val < 0.3) {
			SU_J19_Val = SU_J19*14.6;
		} else if (J19_Val < 0.5) {
			SU_J19_Val = SU_J19*11.7;
		} else {
			SU_J19_Val = SU_J19*17.52;
		}
		
		double sundayJourney[] = new double[19];
		double sundayJourneyValue[] = new double[19];
		sundayJourney[0] = SU_J1;
		sundayJourney[1] = SU_J2;
		sundayJourney[2] = SU_J3;
		sundayJourney[3] = SU_J4;
		sundayJourney[4] = SU_J5;
		sundayJourney[5] = SU_J6;
		sundayJourney[6] = SU_J7;
		sundayJourney[7] = SU_J8;
		sundayJourney[8] = SU_J9;
		sundayJourney[9] = SU_J10;
		sundayJourney[10] = SU_J11;
		sundayJourney[11] = SU_J12;
		sundayJourney[12] = SU_J13;
		sundayJourney[13] = SU_J14;
		sundayJourney[14] = SU_J15;
		sundayJourney[15] = SU_J16;
		sundayJourney[16] = SU_J17;
		sundayJourney[17] = SU_J18;
		sundayJourney[18] = SU_J19;
				
		sundayJourneyValue[0] = SU_J1_Val;
		sundayJourneyValue[1] = SU_J2_Val;
		sundayJourneyValue[2] = SU_J3_Val;
		sundayJourneyValue[3] = SU_J4_Val;
		sundayJourneyValue[4] = SU_J5_Val;
		sundayJourneyValue[5] = SU_J6_Val;
		sundayJourneyValue[6] = SU_J7_Val;
		sundayJourneyValue[7] = SU_J8_Val;
		sundayJourneyValue[8] = SU_J9_Val;
		sundayJourneyValue[9] = SU_J10_Val;
		sundayJourneyValue[10] = SU_J11_Val;
		sundayJourneyValue[11] = SU_J12_Val;
		sundayJourneyValue[12] = SU_J13_Val;
		sundayJourneyValue[13] = SU_J14_Val;
		sundayJourneyValue[14] = SU_J15_Val;
		sundayJourneyValue[15] = SU_J16_Val;
		sundayJourneyValue[16] = SU_J17_Val;
		sundayJourneyValue[17] = SU_J18_Val;
		sundayJourneyValue[18] = SU_J19_Val;
		
		day = time.getDayName();
		// add task for the day
		Random random = new Random();
		
		switch (day) {
		case "Monday":
			totalJourney = random.nextInt(1 - 0 + 1);
			if (totalJourney > 0) {
				ArrayList<Integer> journeyLabel = new ArrayList<Integer>();
			    for (int i=1; i<6; i++) {
			    	journeyLabel.add(new Integer(i));
			    }
			    Collections.shuffle(journeyLabel);
			    for (int i=0; i<totalJourney; i++) {
					dayNeeds += mondayJourney[journeyLabel.get(i)-1];
					dayBudget += mondayJourneyValue[journeyLabel.get(i)-1];
					taskList.add(new Task(mondayJourneyValue[journeyLabel.get(i)-1], mondayJourney[journeyLabel.get(i)-1]));
				}
			}
			break;
		case "Tuesday":
			totalJourney = random.nextInt(4 - 0 + 1);
			if (totalJourney > 0) {
				ArrayList<Integer> journeyLabel = new ArrayList<Integer>();
			    for (int i=1; i<12; i++) {
			    	journeyLabel.add(new Integer(i));
			    }
			    Collections.shuffle(journeyLabel);
			    for (int i=0; i<totalJourney; i++) {
					dayNeeds += tuesdayJourney[journeyLabel.get(i)-1];
					dayBudget += tuesdayJourneyValue[journeyLabel.get(i)-1];
					taskList.add(new Task(tuesdayJourneyValue[journeyLabel.get(i)-1], tuesdayJourney[journeyLabel.get(i)-1]));
				}
			}
			break;
		case "Wednesday":
			totalJourney = random.nextInt(3 - 0 + 1);
			if (totalJourney > 0) {
				ArrayList<Integer> journeyLabel = new ArrayList<Integer>();
			    for (int i=1; i<11; i++) {
			    	journeyLabel.add(new Integer(i));
			    }
			    Collections.shuffle(journeyLabel);
			    for (int i=0; i<totalJourney; i++) {
					dayNeeds += wednesdayJourney[journeyLabel.get(i)-1];
					dayBudget += wednesdayJourneyValue[journeyLabel.get(i)-1];
					taskList.add(new Task(wednesdayJourneyValue[journeyLabel.get(i)-1], wednesdayJourney[journeyLabel.get(i)-1]));
				}
			}
			break;
		case "Thursday":
			totalJourney = random.nextInt(4 - 0 + 1);
			if (totalJourney > 0) {
				ArrayList<Integer> journeyLabel = new ArrayList<Integer>();
			    for (int i=1; i<16; i++) {
			    	journeyLabel.add(new Integer(i));
			    }
			    Collections.shuffle(journeyLabel);
			    for (int i=0; i<totalJourney; i++) {
					dayNeeds += thursdayJourney[journeyLabel.get(i)-1];
					dayBudget += thursdayJourneyValue[journeyLabel.get(i)-1];
					taskList.add(new Task(thursdayJourneyValue[journeyLabel.get(i)-1], thursdayJourney[journeyLabel.get(i)-1]));
				}
			}
			break;
		case "Friday":
			totalJourney = random.nextInt(2 - 0 + 1);
			if (totalJourney > 0) {
				ArrayList<Integer> journeyLabel = new ArrayList<Integer>();
			    for (int i=1; i<12; i++) {
			    	journeyLabel.add(new Integer(i));
			    }
			    Collections.shuffle(journeyLabel);
			    for (int i=0; i<totalJourney; i++) {
					dayNeeds += fridayJourney[journeyLabel.get(i)-1];
					dayBudget += fridayJourneyValue[journeyLabel.get(i)-1];
					taskList.add(new Task(fridayJourneyValue[journeyLabel.get(i)-1], fridayJourney[journeyLabel.get(i)-1]));
				}
			}
			break;
		case "Saturday:":
			totalJourney = random.nextInt(4 - 0 + 1);
			if (totalJourney > 0) {
				ArrayList<Integer> journeyLabel = new ArrayList<Integer>();
			    for (int i=1; i<12; i++) {
			    	journeyLabel.add(new Integer(i));
			    }
			    Collections.shuffle(journeyLabel);
			    for (int i=0; i<totalJourney; i++) {
					dayNeeds += saturdayJourney[journeyLabel.get(i)-1];
					dayBudget += saturdayJourneyValue[journeyLabel.get(i)-1];
					taskList.add(new Task(saturdayJourneyValue[journeyLabel.get(i)-1], saturdayJourney[journeyLabel.get(i)-1]));
				}
			}
			break;
		case "Sunday:":
			totalJourney = random.nextInt(5 - 0 + 1);
			if (totalJourney > 0) {
				ArrayList<Integer> journeyLabel = new ArrayList<Integer>();
			    for (int i=1; i<20; i++) {
			    	journeyLabel.add(new Integer(i));
			    }
			    Collections.shuffle(journeyLabel);
			    for (int i=0; i<totalJourney; i++) {
					dayNeeds += sundayJourney[journeyLabel.get(i)-1];
					dayBudget += sundayJourneyValue[journeyLabel.get(i)-1];
					taskList.add(new Task(sundayJourneyValue[journeyLabel.get(i)-1], sundayJourney[journeyLabel.get(i)-1]));
				}
			}
			break;
		}
		
		switch (time.getWeather()) {
		case 2: //rainy
			dayNeeds *= 1.15;
			dayBudget *= 1.15;
			for (Task temp : taskList) {
				temp.setValue(temp.getValue()*1.15);
				temp.setNeeds(temp.getNeeds()*1.15);
		    }
			break;
		case 3: //snow
			dayNeeds *= 1.3;
			dayBudget *= 1.3;
			for (Task temp : taskList) {
				temp.setValue(temp.getValue()*1.3);
				temp.setNeeds(temp.getNeeds()*1.3);
		    }
			break;
		}
		
		preferences.setAmount(dayNeeds*consumption);
		if (dayNeeds > 0) {
			preferences.setUnitPrice(dayBudget/(dayNeeds*consumption));
		} else {
			preferences.setUnitPrice(0);
		}
		Collections.sort(taskList);
	}
	
	public static void EVUSer_3729(Time time, User user, ElectricityBundle preferences, List<Task> taskList) {
		// necessary variables
		// user with utility 30% average, 20% higher, 50% lower
		double dayNeeds = 0; //miles
		double dayBudget = 0; //p/miles
		String day;
		double consumption = user.getCar().getConsumption();
		int totalJourney = 0;
		
		// possible journey (distance in miles)
		// Monday (0-2)
		double M_J1 = Snippet.normDist(69.96);
		double M_J2 = Snippet.normDist(82.6);
		double M_J3 = Snippet.normDist(68.83);
		double M_J4 = Snippet.normDist(10.85);
		double M_J5 = Snippet.normDist(10.22);
		double M_J6 = Snippet.normDist(42.23);
		double M_J7 = Snippet.normDist(4.79);
		double M_J8 = Snippet.normDist(3.25);
		
		double M_J1_Val = 0;
		double M_J2_Val = 0;
		double M_J3_Val = 0;
		double M_J4_Val = 0;
		double M_J5_Val = 0;
		double M_J6_Val = 0;
		double M_J7_Val = 0;
		double M_J8_Val = 0;
		
		if (J1_Val < 0.3) {
			M_J1_Val = M_J1*14.6;
		} else if (J1_Val < 0.8) {
			M_J1_Val = M_J1*11.7;
		} else {
			M_J1_Val = M_J1*17.52;
		}
		if (J2_Val < 0.3) {
			M_J2_Val = M_J2*14.6;
		} else if (J2_Val < 0.8) {
			M_J2_Val = M_J2*11.7;
		} else {
			M_J2_Val = M_J2*17.52;
		}
		if (J3_Val < 0.3) {
			M_J3_Val = M_J3*14.6;
		} else if (J3_Val < 0.8) {
			M_J3_Val = M_J3*11.7;
		} else {
			M_J3_Val = M_J3*17.52;
		}
		if (J4_Val < 0.3) {
			M_J4_Val = M_J4*14.6;
		} else if (J4_Val < 0.8) {
			M_J4_Val = M_J4*11.7;
		} else {
			M_J4_Val = M_J4*17.52;
		}
		if (J5_Val < 0.3) {
			M_J5_Val = M_J5*14.6;
		} else if (J5_Val < 0.8) {
			M_J5_Val = M_J5*11.7;
		} else {
			M_J5_Val = M_J5*17.52;
		}
		if (J6_Val < 0.3) {
			M_J6_Val = M_J6*14.6;
		} else if (J6_Val < 0.8) {
			M_J6_Val = M_J6*11.7;
		} else {
			M_J6_Val = M_J6*17.52;
		}
		if (J7_Val < 0.3) {
			M_J7_Val = M_J7*14.6;
		} else if (J7_Val < 0.8) {
			M_J7_Val = M_J7*11.7;
		} else {
			M_J7_Val = M_J7*17.52;
		}
		if (J8_Val < 0.3) {
			M_J8_Val = M_J8*14.6;
		} else if (J8_Val < 0.8) {
			M_J8_Val = M_J8*11.7;
		} else {
			M_J8_Val = M_J8*17.52;
		}
		
		double mondayJourney[] = new double[8];
		double mondayJourneyValue[] = new double[8];
		mondayJourney[0] = M_J1;
		mondayJourney[1] = M_J2;
		mondayJourney[2] = M_J3;
		mondayJourney[3] = M_J4;
		mondayJourney[4] = M_J5;
		mondayJourney[5] = M_J6;
		mondayJourney[6] = M_J7;
		mondayJourney[7] = M_J8;
		
		mondayJourneyValue[0] = M_J1_Val;
		mondayJourneyValue[1] = M_J2_Val;
		mondayJourneyValue[2] = M_J3_Val;
		mondayJourneyValue[3] = M_J4_Val;
		mondayJourneyValue[4] = M_J5_Val;
		mondayJourneyValue[5] = M_J6_Val;
		mondayJourneyValue[6] = M_J7_Val;
		mondayJourneyValue[7] = M_J8_Val;
		
		//Tuesday (0-2)
		double T_J1 = Snippet.normDist(77.51);
		double T_J2 = Snippet.normDist(83.77);
		double T_J3 = Snippet.normDist(33.75);
		double T_J4 = Snippet.normDist(25.86);
		double T_J5 = Snippet.normDist(11.97);
		double T_J6 = Snippet.normDist(0.23);
		double T_J7 = Snippet.normDist(4.76);
		double T_J8 = Snippet.normDist(3.42);
		double T_J9 = Snippet.normDist(78.85);
		double T_J10 = Snippet.normDist(67.75);
		double T_J11 = Snippet.normDist(9.71);
		double T_J12 = Snippet.normDist(2.05);
		double T_J13 = Snippet.normDist(2.7);

		double T_J1_Val = 0;
		double T_J2_Val = 0;
		double T_J3_Val = 0;
		double T_J4_Val = 0;
		double T_J5_Val = 0;
		double T_J6_Val = 0;
		double T_J7_Val = 0;
		double T_J8_Val = 0;
		double T_J9_Val = 0;
		double T_J10_Val = 0;
		double T_J11_Val = 0;
		double T_J12_Val = 0;
		double T_J13_Val = 0;
		
		if (J1_Val < 0.3) {
			T_J1_Val = T_J1*14.6;
		} else if (J1_Val < 0.8) {
			T_J1_Val = T_J1*11.7;
		} else {
			T_J1_Val = T_J1*17.52;
		}
		if (J2_Val < 0.3) {
			T_J2_Val = T_J2*14.6;
		} else if (J2_Val < 0.8) {
			T_J2_Val = T_J2*11.7;
		} else {
			T_J2_Val = T_J2*17.52;
		}
		if (J3_Val < 0.3) {
			T_J3_Val = T_J3*14.6;
		} else if (J3_Val < 0.8) {
			T_J3_Val = T_J3*11.7;
		} else {
			T_J3_Val = T_J3*17.52;
		}
		if (J4_Val < 0.3) {
			T_J4_Val = T_J4*14.6;
		} else if (J4_Val < 0.8) {
			T_J4_Val = T_J4*11.7;
		} else {
			T_J4_Val = T_J4*17.52;
		}
		if (J5_Val < 0.3) {
			T_J5_Val = T_J5*14.6;
		} else if (J5_Val < 0.8) {
			T_J5_Val = T_J5*11.7;
		} else {
			T_J5_Val = T_J5*17.52;
		}
		if (J6_Val < 0.3) {
			T_J6_Val = T_J6*14.6;
		} else if (J6_Val < 0.8) {
			T_J6_Val = T_J6*11.7;
		} else {
			T_J6_Val = T_J6*17.52;
		}
		if (J7_Val < 0.3) {
			T_J7_Val = T_J7*14.6;
		} else if (J7_Val < 0.8) {
			T_J7_Val = T_J7*11.7;
		} else {
			T_J7_Val = T_J7*17.52;
		}
		if (J8_Val < 0.3) {
			T_J8_Val = T_J8*14.6;
		} else if (J8_Val < 0.8) {
			T_J8_Val = T_J8*11.7;
		} else {
			T_J8_Val = T_J8*17.52;
		}
		if (J9_Val < 0.3) {
			T_J9_Val = T_J9*14.6;
		} else if (J9_Val < 0.8) {
			T_J9_Val = T_J9*11.7;
		} else {
			T_J9_Val = T_J9*17.52;
		}
		if (J10_Val < 0.3) {
			T_J10_Val = T_J10*14.6;
		} else if (J10_Val < 0.8) {
			T_J10_Val = T_J10*11.7;
		} else {
			T_J10_Val = T_J10*17.52;
		}
		if (J11_Val < 0.3) {
			T_J11_Val = T_J11*14.6;
		} else if (J11_Val < 0.8) {
			T_J11_Val = T_J11*11.7;
		} else {
			T_J11_Val = T_J11*17.52;
		}
		if (J12_Val < 0.3) {
			T_J12_Val = T_J12*14.6;
		} else if (J12_Val < 0.8) {
			T_J12_Val = T_J12*11.7;
		} else {
			T_J12_Val = T_J12*17.52;
		}
		if (J13_Val < 0.3) {
			T_J13_Val = T_J13*14.6;
		} else if (J13_Val < 0.8) {
			T_J13_Val = T_J13*11.7;
		} else {
			T_J13_Val = T_J13*17.52;
		}
		
		double tuesdayJourney[] = new double[13];
		double tuesdayJourneyValue[] = new double[13];
		tuesdayJourney[0] = T_J1;
		tuesdayJourney[1] = T_J2;
		tuesdayJourney[2] = T_J3;
		tuesdayJourney[3] = T_J4;
		tuesdayJourney[4] = T_J5;
		tuesdayJourney[5] = T_J6;
		tuesdayJourney[6] = T_J7;
		tuesdayJourney[7] = T_J8;
		tuesdayJourney[8] = T_J9;
		tuesdayJourney[9] = T_J10;
		tuesdayJourney[10] = T_J11;
		tuesdayJourney[11] = T_J12;
		tuesdayJourney[12] = T_J13;
		
		tuesdayJourneyValue[0] = T_J1_Val;
		tuesdayJourneyValue[1] = T_J2_Val;
		tuesdayJourneyValue[2] = T_J3_Val;
		tuesdayJourneyValue[3] = T_J4_Val;
		tuesdayJourneyValue[4] = T_J5_Val;
		tuesdayJourneyValue[5] = T_J6_Val;
		tuesdayJourneyValue[6] = T_J7_Val;
		tuesdayJourneyValue[7] = T_J8_Val;
		tuesdayJourneyValue[8] = T_J9_Val;
		tuesdayJourneyValue[9] = T_J10_Val;
		tuesdayJourneyValue[10] = T_J11_Val;
		tuesdayJourneyValue[11] = T_J12_Val;
		tuesdayJourneyValue[12] = T_J13_Val;
		
		//Wednesday (0-1)
		double W_J1 = Snippet.normDist(2.12);
		double W_J2 = Snippet.normDist(33.97);
		double W_J3 = Snippet.normDist(67.78);
		double W_J4 = Snippet.normDist(28.24);
		double W_J5 = Snippet.normDist(20.65);
		double W_J6 = Snippet.normDist(7.36);
		double W_J7 = Snippet.normDist(1.31);
		double W_J8 = Snippet.normDist(1.72);
		double W_J9 = Snippet.normDist(0.39);
		
		double W_J1_Val = 0;
		double W_J2_Val = 0;
		double W_J3_Val = 0;
		double W_J4_Val = 0;
		double W_J5_Val = 0;
		double W_J6_Val = 0;
		double W_J7_Val = 0;
		double W_J8_Val = 0;
		double W_J9_Val = 0;
		
		if (J1_Val < 0.3) {
			W_J1_Val = W_J1*14.6;
		} else if (J1_Val < 0.8) {
			W_J1_Val = W_J1*11.7;
		} else {
			W_J1_Val = W_J1*17.52;
		}
		if (J2_Val < 0.3) {
			W_J2_Val = W_J2*14.6;
		} else if (J2_Val < 0.8) {
			W_J2_Val = W_J2*11.7;
		} else {
			W_J2_Val = W_J2*17.52;
		}
		if (J3_Val < 0.3) {
			W_J3_Val = W_J3*14.6;
		} else if (J3_Val < 0.8) {
			W_J3_Val = W_J3*11.7;
		} else {
			W_J3_Val = W_J3*17.52;
		}
		if (J4_Val < 0.3) {
			W_J4_Val = W_J4*14.6;
		} else if (J4_Val < 0.8) {
			W_J4_Val = W_J4*11.7;
		} else {
			W_J4_Val = W_J4*17.52;
		}
		if (J5_Val < 0.3) {
			W_J5_Val = W_J5*14.6;
		} else if (J5_Val < 0.8) {
			W_J5_Val = W_J5*11.7;
		} else {
			W_J5_Val = W_J5*17.52;
		}
		if (J6_Val < 0.3) {
			W_J6_Val = W_J6*14.6;
		} else if (J6_Val < 0.8) {
			W_J6_Val = W_J6*11.7;
		} else {
			W_J6_Val = W_J6*17.52;
		}
		if (J7_Val < 0.3) {
			W_J7_Val = W_J7*14.6;
		} else if (J7_Val < 0.8) {
			W_J7_Val = W_J7*11.7;
		} else {
			W_J7_Val = W_J7*17.52;
		}
		if (J8_Val < 0.3) {
			W_J8_Val = W_J8*14.6;
		} else if (J8_Val < 0.8) {
			W_J8_Val = W_J8*11.7;
		} else {
			W_J8_Val = W_J8*17.52;
		}
		if (J9_Val < 0.3) {
			W_J9_Val = W_J9*14.6;
		} else if (J9_Val < 0.8) {
			W_J9_Val = W_J9*11.7;
		} else {
			W_J9_Val = W_J9*17.52;
		}
		
		double wednesdayJourney[] = new double[9];
		double wednesdayJourneyValue[] = new double[9];
		wednesdayJourney[0] = W_J1;
		wednesdayJourney[1] = W_J2;
		wednesdayJourney[2] = W_J3;
		wednesdayJourney[3] = W_J4;
		wednesdayJourney[4] = W_J5;
		wednesdayJourney[5] = W_J6;
		wednesdayJourney[6] = W_J7;
		wednesdayJourney[7] = W_J8;
		wednesdayJourney[8] = W_J9;
		
		wednesdayJourneyValue[0] = W_J1_Val;
		wednesdayJourneyValue[1] = W_J2_Val;
		wednesdayJourneyValue[2] = W_J3_Val;
		wednesdayJourneyValue[3] = W_J4_Val;
		wednesdayJourneyValue[4] = W_J5_Val;
		wednesdayJourneyValue[5] = W_J6_Val;
		wednesdayJourneyValue[6] = W_J7_Val;
		wednesdayJourneyValue[7] = W_J8_Val;
		wednesdayJourneyValue[8] = W_J9_Val;
		
		//Thursday (0-2)
		double TH_J1 = Snippet.normDist(69.04);
		double TH_J2 = Snippet.normDist(9.08);
		double TH_J3 = Snippet.normDist(73.42);
		double TH_J4 = Snippet.normDist(68.21);
		double TH_J5 = Snippet.normDist(10.17);
		double TH_J6 = Snippet.normDist(27.26);
		double TH_J7 = Snippet.normDist(87.49);
		
		double TH_J1_Val = 0;
		double TH_J2_Val = 0;
		double TH_J3_Val = 0;
		double TH_J4_Val = 0;
		double TH_J5_Val = 0;
		double TH_J6_Val = 0;
		double TH_J7_Val = 0;
		
		if (J1_Val < 0.3) {
			TH_J1_Val = TH_J1*14.6;
		} else if (J1_Val < 0.8) {
			TH_J1_Val = TH_J1*11.7;
		} else {
			TH_J1_Val = TH_J1*17.52;
		}
		if (J2_Val < 0.3) {
			TH_J2_Val = TH_J2*14.6;
		} else if (J2_Val < 0.8) {
			TH_J2_Val = TH_J2*11.7;
		} else {
			TH_J2_Val = TH_J2*17.52;
		}
		if (J3_Val < 0.3) {
			TH_J3_Val = TH_J3*14.6;
		} else if (J3_Val < 0.8) {
			TH_J3_Val = TH_J3*11.7;
		} else {
			TH_J3_Val = TH_J3*17.52;
		}
		if (J4_Val < 0.3) {
			TH_J4_Val = TH_J4*14.6;
		} else if (J4_Val < 0.8) {
			TH_J4_Val = TH_J4*11.7;
		} else {
			TH_J4_Val = TH_J4*17.52;
		}
		if (J5_Val < 0.3) {
			TH_J5_Val = TH_J5*14.6;
		} else if (J5_Val < 0.8) {
			TH_J5_Val = TH_J5*11.7;
		} else {
			TH_J5_Val = TH_J5*17.52;
		}
		if (J6_Val < 0.3) {
			TH_J6_Val = TH_J6*14.6;
		} else if (J6_Val < 0.8) {
			TH_J6_Val = TH_J6*11.7;
		} else {
			TH_J6_Val = TH_J6*17.52;
		}
		if (J7_Val < 0.3) {
			TH_J7_Val = TH_J7*14.6;
		} else if (J7_Val < 0.8) {
			TH_J7_Val = TH_J7*11.7;
		} else {
			TH_J7_Val = TH_J7*17.52;
		}
		
		double thursdayJourney[] = new double[7];
		double thursdayJourneyValue[] = new double[7];
		thursdayJourney[0] = TH_J1;
		thursdayJourney[1] = TH_J2;
		thursdayJourney[2] = TH_J3;
		thursdayJourney[3] = TH_J4;
		thursdayJourney[4] = TH_J5;
		thursdayJourney[5] = TH_J6;
		thursdayJourney[6] = TH_J7;
		
		thursdayJourneyValue[0] = TH_J1_Val;
		thursdayJourneyValue[1] = TH_J2_Val;
		thursdayJourneyValue[2] = TH_J3_Val;
		thursdayJourneyValue[3] = TH_J4_Val;
		thursdayJourneyValue[4] = TH_J5_Val;
		thursdayJourneyValue[5] = TH_J6_Val;
		thursdayJourneyValue[6] = TH_J7_Val;
		
		//Friday (0-3)
		double F_J1 = Snippet.normDist(76.74);
		double F_J2 = Snippet.normDist(76.58);
		double F_J3 = Snippet.normDist(11.45);
		double F_J4 = Snippet.normDist(86.87);
		double F_J5 = Snippet.normDist(6.61);
		double F_J6 = Snippet.normDist(16.16);
		double F_J7 = Snippet.normDist(67.14);
		double F_J8 = Snippet.normDist(6.16);
		
		double F_J1_Val = 0;
		double F_J2_Val = 0;
		double F_J3_Val = 0;
		double F_J4_Val = 0;
		double F_J5_Val = 0;
		double F_J6_Val = 0;
		double F_J7_Val = 0;
		double F_J8_Val = 0;
		
		if (J1_Val < 0.3) {
			F_J1_Val = F_J1*14.6;
		} else if (J1_Val < 0.8) {
			F_J1_Val = F_J1*11.7;
		} else {
			F_J1_Val = F_J1*17.52;
		}
		if (J2_Val < 0.3) {
			F_J2_Val = F_J2*14.6;
		} else if (J2_Val < 0.8) {
			F_J2_Val = F_J2*11.7;
		} else {
			F_J2_Val = F_J2*17.52;
		}
		if (J3_Val < 0.3) {
			F_J3_Val = F_J3*14.6;
		} else if (J3_Val < 0.8) {
			F_J3_Val = F_J3*11.7;
		} else {
			F_J3_Val = F_J3*17.52;
		}
		if (J4_Val < 0.3) {
			F_J4_Val = F_J4*14.6;
		} else if (J4_Val < 0.8) {
			F_J4_Val = F_J4*11.7;
		} else {
			F_J4_Val = F_J4*17.52;
		}
		if (J5_Val < 0.3) {
			F_J5_Val = F_J5*14.6;
		} else if (J5_Val < 0.8) {
			F_J5_Val = F_J5*11.7;
		} else {
			F_J5_Val = F_J5*17.52;
		}
		if (J6_Val < 0.3) {
			F_J6_Val = F_J6*14.6;
		} else if (J6_Val < 0.8) {
			F_J6_Val = F_J6*11.7;
		} else {
			F_J6_Val = F_J6*17.52;
		}
		if (J7_Val < 0.3) {
			F_J7_Val = F_J7*14.6;
		} else if (J7_Val < 0.8) {
			F_J7_Val = F_J7*11.7;
		} else {
			F_J7_Val = F_J7*17.52;
		}
		if (J8_Val < 0.3) {
			F_J8_Val = F_J8*14.6;
		} else if (J8_Val < 0.8) {
			F_J8_Val = F_J8*11.7;
		} else {
			F_J8_Val = F_J8*17.52;
		}
		
		double fridayJourney[] = new double[8];
		double fridayJourneyValue[] = new double[8];
		fridayJourney[0] = F_J1;
		fridayJourney[1] = F_J2;
		fridayJourney[2] = F_J3;
		fridayJourney[3] = F_J4;
		fridayJourney[4] = F_J5;
		fridayJourney[5] = F_J6;
		fridayJourney[6] = F_J7;
		fridayJourney[7] = F_J8;
		
		fridayJourneyValue[0] = F_J1_Val;
		fridayJourneyValue[1] = F_J2_Val;
		fridayJourneyValue[2] = F_J3_Val;
		fridayJourneyValue[3] = F_J4_Val;
		fridayJourneyValue[4] = F_J5_Val;
		fridayJourneyValue[5] = F_J6_Val;
		fridayJourneyValue[6] = F_J7_Val;
		fridayJourneyValue[7] = F_J8_Val;
		
		//Saturday (0-4)
		double S_J1 = Snippet.normDist(21.26);
		double S_J2 = Snippet.normDist(4.33);
		double S_J3 = Snippet.normDist(7.41);
		double S_J4 = Snippet.normDist(25.08);
		double S_J5 = Snippet.normDist(11.25);
		double S_J6 = Snippet.normDist(7.13);
		double S_J7 = Snippet.normDist(4.57);
		double S_J8 = Snippet.normDist(14.22);
		double S_J9 = Snippet.normDist(34.14);
		double S_J10 = Snippet.normDist(31.03);
		double S_J11 = Snippet.normDist(2.73);
		double S_J12 = Snippet.normDist(36.81);
		
		double S_J1_Val = 0;
		double S_J2_Val = 0;
		double S_J3_Val = 0;
		double S_J4_Val = 0;
		double S_J5_Val = 0;
		double S_J6_Val = 0;
		double S_J7_Val = 0;
		double S_J8_Val = 0;
		double S_J9_Val = 0;
		double S_J10_Val = 0;
		double S_J11_Val = 0;
		double S_J12_Val = 0;
		
		if (J1_Val < 0.3) {
			S_J1_Val = S_J1*14.6;
		} else if (J1_Val < 0.8) {
			S_J1_Val = S_J1*11.7;
		} else {
			S_J1_Val = S_J1*17.52;
		}
		if (J2_Val < 0.3) {
			S_J2_Val = S_J2*14.6;
		} else if (J2_Val < 0.8) {
			S_J2_Val = S_J2*11.7;
		} else {
			S_J2_Val = S_J2*17.52;
		}
		if (J3_Val < 0.3) {
			S_J3_Val = S_J3*14.6;
		} else if (J3_Val < 0.8) {
			S_J3_Val = S_J3*11.7;
		} else {
			S_J3_Val = S_J3*17.52;
		}
		if (J4_Val < 0.3) {
			S_J4_Val = S_J4*14.6;
		} else if (J4_Val < 0.8) {
			S_J4_Val = S_J4*11.7;
		} else {
			S_J4_Val = S_J4*17.52;
		}
		if (J5_Val < 0.3) {
			S_J5_Val = S_J5*14.6;
		} else if (J5_Val < 0.8) {
			S_J5_Val = S_J5*11.7;
		} else {
			S_J5_Val = S_J5*17.52;
		}
		if (J6_Val < 0.3) {
			S_J6_Val = S_J6*14.6;
		} else if (J6_Val < 0.8) {
			S_J6_Val = S_J6*11.7;
		} else {
			S_J6_Val = S_J6*17.52;
		}
		if (J7_Val < 0.3) {
			S_J7_Val = S_J7*14.6;
		} else if (J7_Val < 0.8) {
			S_J7_Val = S_J7*11.7;
		} else {
			S_J7_Val = S_J7*17.52;
		}
		if (J8_Val < 0.3) {
			S_J8_Val = S_J8*14.6;
		} else if (J8_Val < 0.8) {
			S_J8_Val = S_J8*11.7;
		} else {
			S_J8_Val = S_J8*17.52;
		}
		if (J9_Val < 0.3) {
			S_J9_Val = S_J9*14.6;
		} else if (J9_Val < 0.8) {
			S_J9_Val = S_J9*11.7;
		} else {
			S_J9_Val = S_J9*17.52;
		}
		if (J10_Val < 0.3) {
			S_J10_Val = S_J10*14.6;
		} else if (J10_Val < 0.8) {
			S_J10_Val = S_J10*11.7;
		} else {
			S_J10_Val = S_J10*17.52;
		}
		if (J11_Val < 0.3) {
			S_J11_Val = S_J11*14.6;
		} else if (J11_Val < 0.8) {
			S_J11_Val = S_J11*11.7;
		} else {
			S_J11_Val = S_J11*17.52;
		}
		if (J12_Val < 0.3) {
			S_J12_Val = S_J12*14.6;
		} else if (J12_Val < 0.8) {
			S_J12_Val = S_J12*11.7;
		} else {
			S_J12_Val = S_J12*17.52;
		}
		
		double saturdayJourney[] = new double[12];
		double saturdayJourneyValue[] = new double[12];
		saturdayJourney[0] = S_J1;
		saturdayJourney[1] = S_J2;
		saturdayJourney[2] = S_J3;
		saturdayJourney[3] = S_J4;
		saturdayJourney[4] = S_J5;
		saturdayJourney[5] = S_J6;
		saturdayJourney[6] = S_J7;
		saturdayJourney[7] = S_J8;
		saturdayJourney[8] = S_J9;
		saturdayJourney[9] = S_J10;
		saturdayJourney[10] = S_J11;
		saturdayJourney[11] = S_J12;
				
		saturdayJourneyValue[0] = S_J1_Val;
		saturdayJourneyValue[1] = S_J2_Val;
		saturdayJourneyValue[2] = S_J3_Val;
		saturdayJourneyValue[3] = S_J4_Val;
		saturdayJourneyValue[4] = S_J5_Val;
		saturdayJourneyValue[5] = S_J6_Val;
		saturdayJourneyValue[6] = S_J7_Val;
		saturdayJourneyValue[7] = S_J8_Val;
		saturdayJourneyValue[8] = S_J9_Val;
		saturdayJourneyValue[9] = S_J10_Val;
		saturdayJourneyValue[10] = S_J11_Val;
		saturdayJourneyValue[11] = S_J12_Val;
		
		//Sunday (0-2)
		double SU_J1 = Snippet.normDist(9.9);
		double SU_J2 = Snippet.normDist(19.75);
		double SU_J3 = Snippet.normDist(16.55);
		double SU_J4 = Snippet.normDist(32.53);
		double SU_J5 = Snippet.normDist(4.53);
		double SU_J6 = Snippet.normDist(10.54);
		double SU_J7 = Snippet.normDist(21.51);
		double SU_J8 = Snippet.normDist(26.79);
		
		double SU_J1_Val = 0;
		double SU_J2_Val = 0;
		double SU_J3_Val = 0;
		double SU_J4_Val = 0;
		double SU_J5_Val = 0;
		double SU_J6_Val = 0;
		double SU_J7_Val = 0;
		double SU_J8_Val = 0;
		
		if (J1_Val < 0.3) {
			SU_J1_Val = SU_J1*14.6;
		} else if (J1_Val < 0.8) {
			SU_J1_Val = SU_J1*11.7;
		} else {
			SU_J1_Val = SU_J1*17.52;
		}
		if (J2_Val < 0.3) {
			SU_J2_Val = SU_J2*14.6;
		} else if (J2_Val < 0.8) {
			SU_J2_Val = SU_J2*11.7;
		} else {
			SU_J2_Val = SU_J2*17.52;
		}
		if (J3_Val < 0.3) {
			SU_J3_Val = SU_J3*14.6;
		} else if (J3_Val < 0.8) {
			SU_J3_Val = SU_J3*11.7;
		} else {
			SU_J3_Val = SU_J3*17.52;
		}
		if (J4_Val < 0.3) {
			SU_J4_Val = SU_J4*14.6;
		} else if (J4_Val < 0.8) {
			SU_J4_Val = SU_J4*11.7;
		} else {
			SU_J4_Val = SU_J4*17.52;
		}
		if (J5_Val < 0.3) {
			SU_J5_Val = SU_J5*14.6;
		} else if (J5_Val < 0.8) {
			SU_J5_Val = SU_J5*11.7;
		} else {
			SU_J5_Val = SU_J5*17.52;
		}
		if (J6_Val < 0.3) {
			SU_J6_Val = SU_J6*14.6;
		} else if (J6_Val < 0.8) {
			SU_J6_Val = SU_J6*11.7;
		} else {
			SU_J6_Val = SU_J6*17.52;
		}
		if (J7_Val < 0.3) {
			SU_J7_Val = SU_J7*14.6;
		} else if (J7_Val < 0.8) {
			SU_J7_Val = SU_J7*11.7;
		} else {
			SU_J7_Val = SU_J7*17.52;
		}
		if (J8_Val < 0.3) {
			SU_J8_Val = SU_J8*14.6;
		} else if (J8_Val < 0.8) {
			SU_J8_Val = SU_J8*11.7;
		} else {
			SU_J8_Val = SU_J8*17.52;
		}
		
		double sundayJourney[] = new double[8];
		double sundayJourneyValue[] = new double[8];
		sundayJourney[0] = SU_J1;
		sundayJourney[1] = SU_J2;
		sundayJourney[2] = SU_J3;
		sundayJourney[3] = SU_J4;
		sundayJourney[4] = SU_J5;
		sundayJourney[5] = SU_J6;
		sundayJourney[6] = SU_J7;
		sundayJourney[7] = SU_J8;
				
		sundayJourneyValue[0] = SU_J1_Val;
		sundayJourneyValue[1] = SU_J2_Val;
		sundayJourneyValue[2] = SU_J3_Val;
		sundayJourneyValue[3] = SU_J4_Val;
		sundayJourneyValue[4] = SU_J5_Val;
		sundayJourneyValue[5] = SU_J6_Val;
		sundayJourneyValue[6] = SU_J7_Val;
		sundayJourneyValue[7] = SU_J8_Val;
		
		day = time.getDayName();
		// add task for the day
		Random random = new Random();
		
		switch (day) {
		case "Monday":
			totalJourney = random.nextInt(2 - 0 + 1);
			if (totalJourney > 0) {
				ArrayList<Integer> journeyLabel = new ArrayList<Integer>();
			    for (int i=1; i<9; i++) {
			    	journeyLabel.add(new Integer(i));
			    }
			    Collections.shuffle(journeyLabel);
			    for (int i=0; i<totalJourney; i++) {
					dayNeeds += mondayJourney[journeyLabel.get(i)-1];
					dayBudget += mondayJourneyValue[journeyLabel.get(i)-1];
					taskList.add(new Task(mondayJourneyValue[journeyLabel.get(i)-1], mondayJourney[journeyLabel.get(i)-1]));
				}
			}
			break;
		case "Tuesday":
			totalJourney = random.nextInt(2 - 0 + 1);
			if (totalJourney > 0) {
				ArrayList<Integer> journeyLabel = new ArrayList<Integer>();
			    for (int i=1; i<14; i++) {
			    	journeyLabel.add(new Integer(i));
			    }
			    Collections.shuffle(journeyLabel);
			    for (int i=0; i<totalJourney; i++) {
					dayNeeds += tuesdayJourney[journeyLabel.get(i)-1];
					dayBudget += tuesdayJourneyValue[journeyLabel.get(i)-1];
					taskList.add(new Task(tuesdayJourneyValue[journeyLabel.get(i)-1], tuesdayJourney[journeyLabel.get(i)-1]));
				}
			}
			break;
		case "Wednesday":
			totalJourney = random.nextInt(1 - 0 + 1);
			if (totalJourney > 0) {
				ArrayList<Integer> journeyLabel = new ArrayList<Integer>();
			    for (int i=1; i<10; i++) {
			    	journeyLabel.add(new Integer(i));
			    }
			    Collections.shuffle(journeyLabel);
			    for (int i=0; i<totalJourney; i++) {
					dayNeeds += wednesdayJourney[journeyLabel.get(i)-1];
					dayBudget += wednesdayJourneyValue[journeyLabel.get(i)-1];
					taskList.add(new Task(wednesdayJourneyValue[journeyLabel.get(i)-1], wednesdayJourney[journeyLabel.get(i)-1]));
				}
			}
			break;
		case "Thursday":
			totalJourney = random.nextInt(2 - 0 + 1);
			if (totalJourney > 0) {
				ArrayList<Integer> journeyLabel = new ArrayList<Integer>();
			    for (int i=1; i<8; i++) {
			    	journeyLabel.add(new Integer(i));
			    }
			    Collections.shuffle(journeyLabel);
			    for (int i=0; i<totalJourney; i++) {
					dayNeeds += thursdayJourney[journeyLabel.get(i)-1];
					dayBudget += thursdayJourneyValue[journeyLabel.get(i)-1];
					taskList.add(new Task(thursdayJourneyValue[journeyLabel.get(i)-1], thursdayJourney[journeyLabel.get(i)-1]));
				}
			}
			break;
		case "Friday":
			totalJourney = random.nextInt(3 - 0 + 1);
			if (totalJourney > 0) {
				ArrayList<Integer> journeyLabel = new ArrayList<Integer>();
			    for (int i=1; i<9; i++) {
			    	journeyLabel.add(new Integer(i));
			    }
			    Collections.shuffle(journeyLabel);
			    for (int i=0; i<totalJourney; i++) {
					dayNeeds += fridayJourney[journeyLabel.get(i)-1];
					dayBudget += fridayJourneyValue[journeyLabel.get(i)-1];
					taskList.add(new Task(fridayJourneyValue[journeyLabel.get(i)-1], fridayJourney[journeyLabel.get(i)-1]));
				}
			}
			break;
		case "Saturday:":
			totalJourney = random.nextInt(4 - 0 + 1);
			if (totalJourney > 0) {
				ArrayList<Integer> journeyLabel = new ArrayList<Integer>();
			    for (int i=1; i<13; i++) {
			    	journeyLabel.add(new Integer(i));
			    }
			    Collections.shuffle(journeyLabel);
			    for (int i=0; i<totalJourney; i++) {
					dayNeeds += saturdayJourney[journeyLabel.get(i)-1];
					dayBudget += saturdayJourneyValue[journeyLabel.get(i)-1];
					taskList.add(new Task(saturdayJourneyValue[journeyLabel.get(i)-1], saturdayJourney[journeyLabel.get(i)-1]));
				}
			}
			break;
		case "Sunday:":
			totalJourney = random.nextInt(2 - 0 + 1);
			if (totalJourney > 0) {
				ArrayList<Integer> journeyLabel = new ArrayList<Integer>();
			    for (int i=1; i<9; i++) {
			    	journeyLabel.add(new Integer(i));
			    }
			    Collections.shuffle(journeyLabel);
			    for (int i=0; i<totalJourney; i++) {
					dayNeeds += sundayJourney[journeyLabel.get(i)-1];
					dayBudget += sundayJourneyValue[journeyLabel.get(i)-1];
					taskList.add(new Task(sundayJourneyValue[journeyLabel.get(i)-1], sundayJourney[journeyLabel.get(i)-1]));
				}
			}
			break;
		}
		
		switch (time.getWeather()) {
		case 2: //rainy
			dayNeeds *= 1.15;
			dayBudget *= 1.15;
			for (Task temp : taskList) {
				temp.setValue(temp.getValue()*1.15);
				temp.setNeeds(temp.getNeeds()*1.15);
		    }
			break;
		case 3: //snow
			dayNeeds *= 1.3;
			dayBudget *= 1.3;
			for (Task temp : taskList) {
				temp.setValue(temp.getValue()*1.3);
				temp.setNeeds(temp.getNeeds()*1.3);
		    }
			break;
		}
		
		preferences.setAmount(dayNeeds*consumption);
		if (dayNeeds > 0) {
			preferences.setUnitPrice(dayBudget/(dayNeeds*consumption));
		} else {
			preferences.setUnitPrice(0);
		}
		Collections.sort(taskList);
	}
	
	public static void EVUSer_Other(Time time, User user, ElectricityBundle preferences, double avNeeds, double stdev) {
		double dayNeeds = 0;
		double dayBudget = 0;
		double consumption = user.getCar().getConsumption();
		
		NormalDistribution needs = new NormalDistribution(avNeeds, stdev);
		NormalDistribution unitBudget = new NormalDistribution(14.6, 3);
		
		dayNeeds = needs.sample();
		dayBudget = dayNeeds*unitBudget.sample();
		
		switch (time.getWeather()) {
		case 2: //rainy
			dayNeeds *= 1.15;
			dayBudget *= 1.15;
			break;
		case 3: //snow
			dayNeeds *= 1.3;
			dayBudget *= 1.3;
			break;
		}
		
		if (dayNeeds > 0) {
			preferences.setAmount(dayNeeds*consumption);
			preferences.setUnitPrice(dayBudget/dayNeeds);
		} else {
			preferences.setAmount(0);
			preferences.setUnitPrice(0);
		}
	}
}
