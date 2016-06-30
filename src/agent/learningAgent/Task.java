/**
 * 
 */
package agent.learningAgent;

/**
 * @author pa1g15
 *
 */
public class Task implements Comparable<Task> {
	
	/**
	 * 
	 */
	private int value;
	private double needs;
	private boolean completed = false; 
	
	public Task(int value, double needs) {
		this.value = value;
		this.needs = needs;
	}
	
	public int getValue() {
		return value;
	}
	
	public double getNeeds() {
		return needs;
	}
	
	public void finishTask() {
		this.completed = true;
	}
	
	public boolean isFinished() {
		return completed;
	}
	
	@Override
    public int compareTo(Task task) {
        int compareValue = ((Task) task).getValue();
        /* For Ascending order*/
        return this.value - compareValue;

        /* For Descending order do like this */
        //  return compareValue - this.value;
    }
}
