/**
 * 
 */
package agent.learningAgent;

/**
 * @author pa1g15
 *
 */
public class TaskValue implements Comparable<TaskValue> {
	
	/**
	 * 
	 */
	private int value;
	private double needs;
	
	public TaskValue(int value, double needs) {
		this.value = value;
		this.needs = needs;
	}
	
	public int getValue() {
		return value;
	}
	
	public double getNeeds() {
		return needs;
	}
	
	@Override
    public int compareTo(TaskValue task) {
        int compareValue = ((TaskValue) task).getValue();
        /* For Ascending order*/
        return this.value - compareValue;

        /* For Descending order do like this */
        //  return compareValue - this.value;
    }
}
