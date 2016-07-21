/**
 * 
 */
package agent;

/**
 * @author pa1g15
 *
 */
public class Performance {

	/**
	 * 
	 */
	private double maxValue;
	private double gainedValue;
	private double maxSpending;
	private double actualSpending;
	
	public Performance() {
		this.maxValue = 0;
		this.gainedValue = 0;
		this.maxSpending = 0;
		this.actualSpending = 0;
	}
	
	public void setMaxValue(double maxValue) {
		this.maxValue = maxValue;
	}

	public void setGainedValue(double gainedValue) {
		this.gainedValue = gainedValue;
	}

	public void setMaxSpending(double maxSpending) {
		this.maxSpending = maxSpending;
	}

	public void setActualSpending(double actualSpending) {
		this.actualSpending = actualSpending;
	}
	
	public double getMaxValue() {
		return maxValue;
	}

	public double getGainedValue() {
		return gainedValue;
	}

	public double getMaxSpending() {
		return maxSpending;
	}

	public double getActualSpending() {
		return actualSpending;
	}

	private double score() {
		double score = 0;
		score = gainedValue - actualSpending;
		return score;
	}
	
	public double normalizedScore() {
		double normalizedScore = 1;
		// min-max difference -> minSpending -- maxValue -> 0 -- maxValue + maxSpending
		double maxScore = maxValue + maxSpending;
		double score = score();
		if (maxScore > 0) {
			normalizedScore = (score + maxSpending)/maxScore;
		}
		return normalizedScore;
	}
	
	public double lostValue() {
		return (maxValue - gainedValue);
	}
}
