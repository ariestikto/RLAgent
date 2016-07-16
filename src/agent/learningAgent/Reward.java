/**
 * 
 */
package agent.learningAgent;

import java.util.List;
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
//		double reward  = 0;
//		List<Task> taskList = user.getTask();
//
//		for (Task temp : taskList) {
//			if (temp.isFinished()) {
//				reward += temp.getValue();
//			} else {
//				reward -= temp.getValue();
//			}
//	    }
//		return reward;
		double reward = 0;
		int total;
		List<Task> taskList = user.getTask();

		for (Task temp : taskList) {
			if (temp.isFinished()) {
				reward += temp.getValue();
			}
	    }
		if (user.payout()!=0) {
			reward = reward*user.getBudget()/user.payout();
		}
		total = (int) reward;
		return total;
	}
}
