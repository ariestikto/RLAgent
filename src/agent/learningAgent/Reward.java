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
	public static int RewardPatternA(User user) {
		int reward = 0;
		if (user.getCurrentElectricity() < user.getDailyNeeds()) {
			//	not enough
			reward = -20;
		} else if (user.payout() > user.getBudget()*1.05) {
			// too expensive
			reward = -10;
		} else if (user.getCurrentElectricity() > (int) (user.getDailyNeeds() + 1)) {
			// too much
			reward = -1;
		} else if (user.payout()*0.9 > user.getExpenses()){
			// cheap
			reward = 80;
		} else {
			// just enough
			reward = 50;
		}
		return reward;
	}
}
