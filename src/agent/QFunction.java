/**
 * 
 */
package agent;

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
	private int value;
	
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
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
