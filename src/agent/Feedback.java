/**
 * 
 */
package agent;

import userSimulation.User;
import userSimulation.UserSatisfaction;

/**
 * @author pa1g15
 *
 */
public class Feedback {
	
	/**
	 * 
	 */
	public static int FeedbackPatternA(User user, UserSatisfaction satisfactionLevel) {
		int score  = 0;
		int electricityFeedback = satisfactionLevel.getElectricityFeedback();
		int payoutFeedback = satisfactionLevel.getPayoutFeedback();
		
		switch (electricityFeedback) {
		case 5:
			score += 25;
			break;
		case 4:
			score += 10;
			break;
		case 3:
			score += 0;
			break;
		case 2:
			score += -10;
			break;
		case 1:
			score += -30;
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
	
	public static int FeedbackPatternB(User user, UserSatisfaction satisfactionLevel) {
		int score  = 0;
		int electricityFeedback = satisfactionLevel.getElectricityFeedback();
		int payoutFeedback = satisfactionLevel.getPayoutFeedback();
		
		switch (electricityFeedback) {
		case 5:
			score += 15;
			break;
		case 4:
			score += 10;
			break;
		case 3:
			score += 0;
			break;
		case 2:
			score += -20;
			break;
		case 1:
			score += -25;
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
}
