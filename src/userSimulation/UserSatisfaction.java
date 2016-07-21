package userSimulation;

import agent.Performance;
public class UserSatisfaction {

	private int electricityFeedback;
	private int payoutFeedback;
	
	public UserSatisfaction() {
		// TODO Auto-generated constructor stub
		this.electricityFeedback = 3;
		this.payoutFeedback = 3;
	}

	public int getElectricityFeedback() {
		return electricityFeedback;
	}

	public void setElectricityFeedback(int electricityFeedback) {
		this.electricityFeedback = electricityFeedback;
	}

	public int getPayoutFeedback() {
		return payoutFeedback;
	}

	public void setPayoutFeedback(int payoutFeedback) {
		this.payoutFeedback = payoutFeedback;
	}
	
	public void generateFeedbackPatternA(User user) {
		//	fair feedback
		Performance p = user.getPerformance();
		double FEEDBACK_FREQUENCY = 0.7;
		double surplus = p.getActualSpending() - user.getBudget(); 
		if (p.lostValue() > 0) {
			if (p.lostValue() > 500) {
				this.electricityFeedback = 1;
			} else {
				this.electricityFeedback = 2;
			}
		} else {
			if (p.getGainedValue() > 1000) {
				this.electricityFeedback = 5;
			} else {
				this.electricityFeedback = 4;
			}
		}
		
		if (surplus > 20) {
			this.payoutFeedback = 5;
		} else if (surplus > -10) {
			this.payoutFeedback = 4;
		} else if (surplus > -50) {
			this.payoutFeedback = 3;
		} else if (surplus > -100) {
			this.payoutFeedback = 2;
		} else {
			this.payoutFeedback = 1;
		}
		
		if (Math.random() > FEEDBACK_FREQUENCY) {
			this.payoutFeedback = 3;
			this.electricityFeedback = 3;
		}
	}
	
	public void generateFeedbackPatternB(User user) {
		// punish trip failure
		Performance p = user.getPerformance();
		double FEEDBACK_FREQUENCY = 0.7;
		double surplus = p.getActualSpending() - user.getBudget(); 
		if (p.lostValue() > 0) {
			if (p.lostValue() > 100) {
				this.electricityFeedback = 1;
			} else {
				this.electricityFeedback = 2;
			}
		} else {
			if (p.getGainedValue() > 1500) {
				this.electricityFeedback = 5;
			} else {
				this.electricityFeedback = 4;
			}
		}
		
		if (surplus > 20) {
			this.payoutFeedback = 5;
		} else if (surplus > -10) {
			this.payoutFeedback = 4;
		} else if (surplus > -50) {
			this.payoutFeedback = 3;
		} else if (surplus > -100) {
			this.payoutFeedback = 2;
		} else {
			this.payoutFeedback = 1;
		}
		
		if (Math.random() > FEEDBACK_FREQUENCY) {
			this.payoutFeedback = 3;
			this.electricityFeedback = 3;
		}
	}
	
	public void generateFeedbackPatternC(User user) {
		// cheapskate
		Performance p = user.getPerformance();
		double FEEDBACK_FREQUENCY = 0.7;
		double surplus = p.getActualSpending() - user.getBudget(); 
		if (p.lostValue() > 0) {
			if (p.lostValue() > 500) {
				this.electricityFeedback = 1;
			} else {
				this.electricityFeedback = 2;
			}
		} else {
			if (p.getGainedValue() > 1000) {
				this.electricityFeedback = 5;
			} else {
				this.electricityFeedback = 4;
			}
		}
		
		if (surplus > 100) {
			this.payoutFeedback = 5;
		} else if (surplus > 20) {
			this.payoutFeedback = 4;
		} else if (surplus > 0) {
			this.payoutFeedback = 3;
		} else if (surplus > -10) {
			this.payoutFeedback = 2;
		} else {
			this.payoutFeedback = 1;
		}
		
		if (Math.random() > FEEDBACK_FREQUENCY) {
			this.payoutFeedback = 3;
			this.electricityFeedback = 3;
		}
	}
}
