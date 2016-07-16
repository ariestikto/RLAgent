/**
 * 
 */
package agent.learningAgent;

import marketFramework.Market;
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
		int addedScore = 0;
		int satisfactoryLevel = 0;
		
		// electricity
		satisfactoryLevel = UserSatisfaction.userSatisfactory(user);
		switch (satisfactoryLevel) {
		case 5: // very satisfied
			addedScore = 5;
			break;
		case 4: // satisfied
			addedScore = 3;
			break;
		case 3: // neutral
			addedScore = 0;
			break;
		case 2: // dissatisfied
			addedScore = -3;
			break;
		case 1: // very dissatisfied
			addedScore = -5;
			break;
		}
		if (user.getBudget() < user.payout()) {
			addedScore += -1;
		}
//		if (Math.random() < Market.FEEDBACK_FREQUENCY) {
			score = addedScore;
//		}
		return score;
	}
}
