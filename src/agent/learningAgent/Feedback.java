/**
 * 
 */
package agent.learningAgent;

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
		int satisfactoryLevel = 0;
		double excessBudget = user.payout() - user.getBudget();
		
		// electricity
		satisfactoryLevel = UserSatisfaction.userSatisfactory(user);
		switch (satisfactoryLevel) {
		case 5: // very satisfied
			score = 5;
			break;
		case 4: // satisfied
			score = 3;
			break;
		case 3: // neutral
			score = 0;
			break;
		case 2: // dissatisfied
			score = -3;
			break;
		case 1: // very dissatisfied
			score = -5;
			break;
		}
		if (excessBudget > 100) {
			score += -2;
		} else if (excessBudget > 50) {
			score += -1;
		}
		return score;
	}
}
