/**
 * 
 */
package userSimulation;

import agent.learningAgent.Task;

/**
 * @author pa1g15
 *
 */
public class UserSatisfaction {

	/**
	 * 
	 */
	public static int userSatisfactory(User user) {
		int allTask = 0;
		int finishedTask = 0;
		int satisfactoryLevel = 3;
		double lostValue = 0;
		
		for (Task temp : user.getTask()) {
			allTask += temp.getValue();
			if (temp.isFinished()) {
				finishedTask += temp.getValue();
			}
	    }
		if (allTask > 0) {
			lostValue = allTask - finishedTask; 
			if (lostValue == 0) {
				if (finishedTask > 50) {
					satisfactoryLevel = 5;
				} else {
					satisfactoryLevel = 4;
				}
			} else {
				if (lostValue > 50) {
					satisfactoryLevel = 1;
				} else {
					satisfactoryLevel = 2;
				}
			}
		}
		return satisfactoryLevel;
	}
}
