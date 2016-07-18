/**
 * 
 */
package agent.learningAgent;

import userSimulation.User;
/**
 * @author pa1g15
 *
 */
public class Reward {

	/**
	 * 
	 */
	public static double RewardPatternA(User user) {
		double allTask = 0;
		double maxReward = 0;
		double minReward = -2;
		double reward = 0;
		double performance = 0;
		
		for (Task temp : user.getTask()) {
			allTask += temp.getValue();
		}
		if (allTask > 50) {
			maxReward = 5;
			minReward = -7;
		} else if (allTask > 0) {
			maxReward = 3;
			minReward = -5;
		}
		reward = Feedback.FeedbackPatternA(user);
		performance = (reward-minReward)/(maxReward-minReward);
		return performance;
	}
}
