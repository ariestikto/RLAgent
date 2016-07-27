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
	private double leftoverElectricity;
	private int totalTask;
	private int finishedTask;
	
	public Performance() {
		this.maxValue = 0;
		this.gainedValue = 0;
		this.maxSpending = 0;
		this.actualSpending = 0;
		this.leftoverElectricity = 0;
		this.totalTask = 0;
		this.finishedTask = 0;
	}
	
	public double getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(double maxValue) {
		this.maxValue = maxValue;
	}

	public double getGainedValue() {
		return gainedValue;
	}

	public void setGainedValue(double gainedValue) {
		this.gainedValue = gainedValue;
	}

	public double getMaxSpending() {
		return maxSpending;
	}

	public void setMaxSpending(double maxSpending) {
		this.maxSpending = maxSpending;
	}

	public double getActualSpending() {
		return actualSpending;
	}

	public void setActualSpending(double actualSpending) {
		this.actualSpending = actualSpending;
	}

	public double getLeftoverElectricity() {
		return leftoverElectricity;
	}

	public void setLeftoverElectricity(double leftoverElectricity) {
		this.leftoverElectricity = leftoverElectricity;
	}

	public int getTotalTask() {
		return totalTask;
	}

	public void setTotalTask(int totalTask) {
		this.totalTask = totalTask;
	}

	public int getFinishedTask() {
		return finishedTask;
	}

	public void setFinishedTask(int finishedTask) {
		this.finishedTask = finishedTask;
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
//		System.out.println(-maxSpending + " < " + (score + maxSpending) + " < " + maxScore);
//		System.out.println(maxScore);
		return normalizedScore;
	}
	
	public double lostValue() {
		return (maxValue - gainedValue);
	}
}
