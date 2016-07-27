/**
 * 
 */
package agent;

/**
 * @author pa1g15
 *
 */
public class Task implements Comparable<Task> {
	
	/**
	 * 
	 */
	private double value;
	private double needs;
	private boolean completed = false; 
	
	public Task(double value, double needs) {
		this.value = value;
		this.needs = needs;
	}
	
	public double getValue() {
		return value;
	}
	
	public double getNeeds() {
		return needs;
	}
	
	public void finishTask() {
		this.completed = true;
	}
	
	public boolean isComplete() {
		return completed;
	}
	
	@Override
    public int compareTo(Task task) {
		double compareValue = ((Task) task).getValue();
        /* For Ascending order*/
		if (this.value == compareValue) {
			return 0;
		}
		return this.value > compareValue ? -1 : 1;

        /* For Descending order do like this */
		// return this.value < compareValue ? -1 : 1;
    }
}
