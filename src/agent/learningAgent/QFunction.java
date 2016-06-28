/**
 * 
 */
package agent.learningAgent;

/**
 * @author pa1g15
 *
 */
public class QFunction {

	/**
	 * 
	 */
	private State state;
	private Action action;
	private double value;
	
	public QFunction(State state, Action action) {
		this.state = state;
		this.action = action;
		this.value = 0;
	}
	
	public State getState() {
		return state;
	}

	public Action getAction() {
		return action;
	}
	
	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
}
