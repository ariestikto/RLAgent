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
	public static int FeedbackPatternA(User user) {
		int score  = 0;
		UserSatisfaction feedback = new UserSatisfaction();
		feedback.generateFeedbackPatternA(user);
		int electricityFeedback = feedback.getElectricityFeedback();
		int payoutFeedback = feedback.getPayoutFeedback();
		
		switch (electricityFeedback) {
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
			score += -5;
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
