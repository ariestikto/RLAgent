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
public class Reward {

	/**
	 * 
	 */
	public static double RewardPatternA(User user) {
		double reward  = 0;
		double addedReward = 0;
		int satisfactoryLevel = 0;
//		int randomElectricityFeedback = 1 + (int)(Math.random() * ((1000 - 1) + 1));
		
		// electricity
		satisfactoryLevel = UserSatisfaction.userSatisfactory(user);
		switch (satisfactoryLevel) {
		case 1: // very low
//			randomElectricityFeedback *= 1.5;
			addedReward = -2;
			break;
		case 2: // low
//			randomElectricityFeedback *= 1.5;
			addedReward = -1;
			break;
		case 3: // normal
			addedReward = 3;
			break;
		case 4: // high
			addedReward = 10;
			break;
		}
		
//		if (randomElectricityFeedback > 300) {
			reward += addedReward;
			reward += (user.getBudget() - user.payout())/50;
//		}
		return reward;
	}
}
