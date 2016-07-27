/**
 * 
 */
package agent;

import userSimulation.UserSatisfaction;

/**
 * @author pa1g15
 *
 */
public class Reward {
	
	/**
	 * 
	 */
	public static int RewardPatternA(UserSatisfaction satisfactionLevel) {
		int score  = 0;
		int electricityFeedback = satisfactionLevel.getElectricityFeedback();
		int payoutFeedback = satisfactionLevel.getPayoutFeedback();
		
		switch (electricityFeedback) {
		case 5:
			score += 10;
			break;
		case 4:
			score += 7;
			break;
		case 3:
			score += 1;
			break;
		case 2:
			score += -10;
			break;
		case 1:
			score += -20;
			break;
		default:
			score += 0;
			break;
		}
		
		switch (payoutFeedback) {
		case 5:
			score += 2;
			break;
		case 4:
			score += 1;
			break;
		case 3:
			score += 0;
			break;
		case 2:
			score += -1;
			break;
		case 1:
			score += -2;
			break;
		default:
			score += 0;
			break;
		}
		return score;
	}
	
	public static int RewardPatternB(UserSatisfaction satisfactionLevel) {
		int score  = 0;
		int electricityFeedback = satisfactionLevel.getElectricityFeedback();
		int payoutFeedback = satisfactionLevel.getPayoutFeedback();
		
		switch (electricityFeedback) {
		case 5:
			score += 15;
			break;
		case 4:
			score += 8;
			break;
		case 3:
			score += 1;
			break;
		case 2:
			score += -10;
			break;
		case 1:
			score += -15;
			break;
		default:
			score += 0;
			break;
		}
		
		switch (payoutFeedback) {
		case 5:
			score += 5;
			break;
		case 4:
			score += 3;
			break;
		case 3:
			score += 0;
			break;
		case 2:
			score += -3;
			break;
		case 1:
			score += -10;
			break;
		default:
			score += 0;
			break;
		}
		return score;
	}
	public static int RewardPatternC(UserSatisfaction satisfactionLevel) {
		int score  = 0;
		int electricityFeedback = satisfactionLevel.getElectricityFeedback();
		int payoutFeedback = satisfactionLevel.getPayoutFeedback();
		
		switch (electricityFeedback) {
		case 5:
			score += 20;
			break;
		case 4:
			score += 15;
			break;
		case 3:
			score += 0;
			break;
		case 2:
			score += -10;
			break;
		case 1:
			score += -15;
			break;
		default:
			score += 0;
			break;
		}
		
		switch (payoutFeedback) {
		case 5:
			score += 5;
			break;
		case 4:
			score += 3;
			break;
		case 3:
			score += 0;
			break;
		case 2:
			score += -1;
			break;
		case 1:
			score += -2;
			break;
		default:
			score += 0;
			break;
		}
		return score;
	}
}
