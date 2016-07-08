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
		int satisfactoryLevel = 0;
		double completedTaskProportion = 0;
		
		for (Task temp : user.getTask()) {
			allTask += temp.getValue();
			if (temp.isFinished()) {
				finishedTask += temp.getValue();
			}
	    }
		if (allTask <= 0) {
			completedTaskProportion = 1;
		} else {
			completedTaskProportion = finishedTask/allTask; 
		}
		if (completedTaskProportion < 0.5) {
			satisfactoryLevel = 1; // very low
		} else if (completedTaskProportion < 0.8) {
			satisfactoryLevel = 2; //low
		} else if (completedTaskProportion < 0.95) {
			satisfactoryLevel = 3; // normal
		} else {
			satisfactoryLevel = 4; // high
		}
		return satisfactoryLevel;
	}
}
