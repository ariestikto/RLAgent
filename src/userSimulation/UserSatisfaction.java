package userSimulation;

import agent.Performance;
public class UserSatisfaction {

	private int electricityFeedback;
	private int payoutFeedback;
	
	public UserSatisfaction() {
		// TODO Auto-generated constructor stub
		this.electricityFeedback = 0;
		this.payoutFeedback = 0;
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
	
	public void noFeedback() {
		this.electricityFeedback = 0;
		this.payoutFeedback = 0;
	}
	
	public void generateFeedbackPattern_1(User user) {
		//	fair feedback
		Performance p = user.getPerformance();
		double FEEDBACK_FREQUENCY = 0.7;
		double surplus = p.getActualSpending() - user.getBudget(); 
		
		noFeedback();
		if (Math.random() <= FEEDBACK_FREQUENCY) {
			// electricity feedback function
			if (p.lostValue() == 0) {
				if (p.getLeftoverElectricity() > 0.25*user.getCar().getBatteryCapacity()) {
					this.electricityFeedback = 5;
				} else if (p.getLeftoverElectricity() > 0.05*user.getCar().getBatteryCapacity()) {
					this.electricityFeedback = 4;
				} else {
					this.electricityFeedback = 3;
				}
			} else {
				if (p.getTotalTask() > 0) {
					if ((double) p.getFinishedTask()/p.getTotalTask() > 0.7) {
						this.electricityFeedback = 2;
					} else {
						this.electricityFeedback = 1;
					}
				}
			}
		
			// expense reward function
			if (surplus > 100) {
				this.payoutFeedback = 5;
			} else if (surplus > 20) {
				this.payoutFeedback = 4;
			} else if (surplus > -15) {
				this.payoutFeedback = 3;
			} else if (surplus > -50) {
				this.payoutFeedback = 2;
			} else {
				this.payoutFeedback = 1;
			}
		}
	}
	
	public void generateFeedbackPattern_2(User user) {
		// punish trip failure, more expense tolerance
		Performance p = user.getPerformance();
		double FEEDBACK_FREQUENCY = 0.8;
		double surplus = p.getActualSpending() - user.getBudget(); 
		
		noFeedback();
		if (Math.random() <= FEEDBACK_FREQUENCY) {
			// electricity feedback function
			if (p.lostValue() == 0) {
				if (p.getLeftoverElectricity() > 0.5*user.getCar().getBatteryCapacity()) {
					this.electricityFeedback = 5;
				} else if (p.getLeftoverElectricity() > 0.3*user.getCar().getBatteryCapacity()) {
					this.electricityFeedback = 4;
				} else {
					this.electricityFeedback = 3;
				}
			} else {
				this.electricityFeedback = 1;
			}
		
			// expense reward function
			if (surplus > 10) {
				this.payoutFeedback = 5;
			} else if (surplus > 0) {
				this.payoutFeedback = 4;
			} else if (surplus > -20) {
				this.payoutFeedback = 3;
			} else if (surplus > -150) {
				this.payoutFeedback = 2;
			} else {
				this.payoutFeedback = 1;
			}
		}
	}
	
	public void generateFeedbackPattern_3(User user) {
		// punish trip failure, more expense tolerance
		Performance p = user.getPerformance();
		double FEEDBACK_FREQUENCY = 0.8;
		double surplus = p.getActualSpending() - user.getBudget(); 
		
		noFeedback();
		if (Math.random() <= FEEDBACK_FREQUENCY) {
			// electricity feedback function
			if (p.lostValue() == 0) {
				if (p.getLeftoverElectricity() > 0.1*user.getCar().getBatteryCapacity()) {
					this.electricityFeedback = 5;
				} else {
					this.electricityFeedback = 4;
				}
			} else {
				if (p.getTotalTask() > 0) {
					if ((double) p.getFinishedTask()/p.getTotalTask() > 0.8) {
						this.electricityFeedback = 3;
					} else if ((double) p.getFinishedTask()/p.getTotalTask() > 0.8) {
						this.electricityFeedback = 2;
					} else {
						this.electricityFeedback = 1;
					}
				}
			}
		
			// expense reward function
			if (surplus > 300) {
				this.payoutFeedback = 5;
			} else if (surplus > 100) {
				this.payoutFeedback = 4;
			} else if (surplus >= 0) {
				this.payoutFeedback = 3;
			} else if (surplus > -10) {
				this.payoutFeedback = 2;
			} else {
				this.payoutFeedback = 1;
			}
		}
	}
	
	public void generateFeedbackPattern_4(User user) {
		//	lazy user
		Performance p = user.getPerformance();
		double FEEDBACK_FREQUENCY_GOOD = 0.3;
		double FEEDBACK_FREQUENCY_BAD = 0.9;
		boolean goodUsage = true;
		boolean goodExpense = true;
		double surplus = p.getActualSpending() - user.getBudget(); 
		
		// electricity feedback function
		if (p.lostValue() == 0) {
			if (p.getLeftoverElectricity() > 0.25*user.getCar().getBatteryCapacity()) {
				this.electricityFeedback = 5;
			} else if (p.getLeftoverElectricity() > 0.05*user.getCar().getBatteryCapacity()) {
				this.electricityFeedback = 4;
			} else {
				this.electricityFeedback = 3;
			}
		} else {
			if (p.getTotalTask() > 0) {
				goodUsage = false;
				if ((double) p.getFinishedTask()/p.getTotalTask() > 0.7) {
					this.electricityFeedback = 2;
				} else {
					this.electricityFeedback = 1;
				}
			}
		}
	
		// expense reward function
		if (surplus > 100) {
			this.payoutFeedback = 5;
		} else if (surplus > 20) {
			this.payoutFeedback = 4;
		} else if (surplus > -15) {
			this.payoutFeedback = 3;
		} else if (surplus > -50) {
			goodExpense = false;
			this.payoutFeedback = 2;
		} else {
			goodExpense = false;
			this.payoutFeedback = 1;
		}
		
		// decide if no feedback
		if (goodUsage) {
			if (Math.random() > FEEDBACK_FREQUENCY_GOOD) {
				this.electricityFeedback = 0;
			}
		} else {
			if (Math.random() > FEEDBACK_FREQUENCY_BAD) {
				this.electricityFeedback = 0;
			}
		}
		if (goodExpense) {
			if (Math.random() > FEEDBACK_FREQUENCY_GOOD) {
				this.payoutFeedback = 0;
			}
		} else {
			if (Math.random() > FEEDBACK_FREQUENCY_BAD) {
				this.payoutFeedback = 0;
			}
		}
	}
	
	public void generateFeedbackPattern_test(User user) {
		//	lazy user
		Performance p = user.getPerformance();
		double surplus = p.getActualSpending() - user.getBudget(); 
		
		// electricity feedback function
		if (p.lostValue() == 0) {
			this.electricityFeedback = 5;
		} else {
			this.electricityFeedback = 1;
		}
	
		// expense reward function
		if (surplus > 100) {
			this.payoutFeedback = 5;
		} else if (surplus > 20) {
			this.payoutFeedback = 4;
		} else if (surplus > -15) {
			this.payoutFeedback = 3;
		} else if (surplus > -50) {
			this.payoutFeedback = 2;
		} else {
			this.payoutFeedback = 1;
		}
	}
	
	public void generateFeedbackPattern_5(User user) {
		//	very lazy user
		Performance p = user.getPerformance();
		double FEEDBACK_FREQUENCY = 0.4;
		double surplus = p.getActualSpending() - user.getBudget(); 
		
		noFeedback();
		if (Math.random() <= FEEDBACK_FREQUENCY) {
			// electricity feedback function
			if (p.lostValue() == 0) {
				if (p.getLeftoverElectricity() > 0.25*user.getCar().getBatteryCapacity()) {
					this.electricityFeedback = 5;
				} else if (p.getLeftoverElectricity() > 0.05*user.getCar().getBatteryCapacity()) {
					this.electricityFeedback = 4;
				} else {
					this.electricityFeedback = 3;
				}
			} else {
				if (p.getTotalTask() > 0) {
					if ((double) p.getFinishedTask()/p.getTotalTask() > 0.7) {
						this.electricityFeedback = 2;
					} else {
						this.electricityFeedback = 1;
					}
				}
			}
		
			// expense reward function
			if (surplus > 100) {
				this.payoutFeedback = 5;
			} else if (surplus > 20) {
				this.payoutFeedback = 4;
			} else if (surplus > -15) {
				this.payoutFeedback = 3;
			} else if (surplus > -50) {
				this.payoutFeedback = 2;
			} else {
				this.payoutFeedback = 1;
			}
		}
	}
	
	public void generateFeedbackPattern_6(User user) {
		//	confused user
		Performance p = user.getPerformance();
		double FEEDBACK_FREQUENCY = 0.7;
		double surplus = p.getActualSpending() - user.getBudget(); 
		
		noFeedback();
		if (Math.random() <= FEEDBACK_FREQUENCY) {
			// electricity feedback function
			if (p.lostValue() == 0) {
				if (p.getLeftoverElectricity() > 0.25*user.getCar().getBatteryCapacity()) {
					this.electricityFeedback = 1;
				} else if (p.getLeftoverElectricity() > 0.05*user.getCar().getBatteryCapacity()) {
					this.electricityFeedback = 2;
				} else {
					this.electricityFeedback = 3;
				}
			} else {
				if (p.getTotalTask() > 0) {
					if ((double) p.getFinishedTask()/p.getTotalTask() > 0.7) {
						this.electricityFeedback = 4;
					} else {
						this.electricityFeedback = 5;
					}
				}
			}
		
			// expense reward function
			if (surplus > 100) {
				this.payoutFeedback = 1;
			} else if (surplus > 20) {
				this.payoutFeedback = 2;
			} else if (surplus > -15) {
				this.payoutFeedback = 3;
			} else if (surplus > -50) {
				this.payoutFeedback = 4;
			} else {
				this.payoutFeedback = 5;
			}
		}
	}
}