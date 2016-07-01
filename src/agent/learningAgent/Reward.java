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
		int allTask = 0;
		int finishedTask = 0;
		int reward  = 0;
		double completedTaskProportion = 0;
		double expensesRate = 0;
		int randomElectricityFeedback = 1 + (int)(Math.random() * ((1000 - 1) + 1));
		int randomBudgetFeedback = 1 + (int)(Math.random() * ((1000 - 1) + 1));
		
		for (Task temp : user.getTask()) {
			allTask += temp.getValue();
			if (temp.isFinished()) {
				finishedTask += temp.getValue();
			}
	    }
		
		//	electricity reward signal
		if (allTask > 0) {
			completedTaskProportion = finishedTask/allTask;
			if (completedTaskProportion < 0.5) {
				if (randomElectricityFeedback > 100) {
					reward += -30;
				}
			} else if (completedTaskProportion < 0.95) {
				if (randomElectricityFeedback > 300) {
					reward += -20;
				}
			} else {
				if (randomElectricityFeedback > 700) {
					reward += 10;
				}
			}
		}
		
		// budget reward signal
		if (user.payout() > 0) {
			expensesRate = user.getBudget()/user.payout();
			if (expensesRate < 0.7) {
				if (randomBudgetFeedback > 100) {
					reward += -3;
				}
			} else if (expensesRate < 0.3) {
				if (randomBudgetFeedback > 300) {
					reward += -2;
				}
			} else if (expensesRate < 0.95) {
				if (randomBudgetFeedback > 500) {
					reward += -1;
				}
			} else if (expensesRate < 1.1) {
				reward += 0;
			} else {
				if (randomBudgetFeedback > 700) {
					reward += 1;
				}
			}
		} else if (reward > 0) {
			if (randomBudgetFeedback > 700) {
				reward += 1;
			}
		}
		return reward;
	}
}
