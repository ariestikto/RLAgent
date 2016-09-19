/**
 * 
 */
package agent;

import marketFramework.Parameter;
import userSimulation.User;
import userSimulation.UserSatisfaction;

/**
 * @author pa1g15
 *
 */
public class Reward {
	
	/**
	 * 
	 */
	public static int RewardPatternA(UserSatisfaction satisfactionLevel) {
		int score  = 0;
		int electricityFeedback = satisfactionLevel.getElectricityFeedback();
		int payoutFeedback = satisfactionLevel.getPayoutFeedback();
		
		switch (electricityFeedback) {
		case 5:
			score += 10;
			break;
		case 4:
			score += 7;
			break;
		case 3:
			score += 1;
			break;
		case 2:
			score += -10;
			break;
		case 1:
			score += -20;
			break;
		default:
			score += 0;
			break;
		}
		
		switch (payoutFeedback) {
		case 5:
			score += 2;
			break;
		case 4:
			score += 1;
			break;
		case 3:
			score += 0;
			break;
		case 2:
			score += -1;
			break;
		case 1:
			score += -2;
			break;
		default:
			score += 0;
			break;
		}
		return score;
	}
	
	public static int RewardPatternB(UserSatisfaction satisfactionLevel) {
		int score  = 0;
		int electricityFeedback = satisfactionLevel.getElectricityFeedback();
		int payoutFeedback = satisfactionLevel.getPayoutFeedback();
		
		switch (electricityFeedback) {
		case 5:
			score += 15;
			break;
		case 4:
			score += 8;
			break;
		case 3:
			score += 1;
			break;
		case 2:
			score += -10;
			break;
		case 1:
			score += -15;
			break;
		default:
			score += 0;
			break;
		}
		
		switch (payoutFeedback) {
		case 5:
			score += 5;
			break;
		case 4:
			score += 3;
			break;
		case 3:
			score += 0;
			break;
		case 2:
			score += -3;
			break;
		case 1:
			score += -10;
			break;
		default:
			score += 0;
			break;
		}
		return score;
	}
	public static int RewardPatternC(UserSatisfaction satisfactionLevel) {
		int score  = 0;
		int electricityFeedback = satisfactionLevel.getElectricityFeedback();
		int payoutFeedback = satisfactionLevel.getPayoutFeedback();
		
		switch (electricityFeedback) {
		case 5:
			score += 20;
			break;
		case 4:
			score += 15;
			break;
		case 3:
			score += 0;
			break;
		case 2:
			score += -10;
			break;
		case 1:
			score += -15;
			break;
		default:
			score += 0;
			break;
		}
		
		switch (payoutFeedback) {
		case 5:
			score += 5;
			break;
		case 4:
			score += 3;
			break;
		case 3:
			score += 0;
			break;
		case 2:
			score += -1;
			break;
		case 1:
			score += -2;
			break;
		default:
			score += 0;
			break;
		}
		return score;
	}
	
	public static double RewardPatternSlider(UserSatisfaction satisfactionLevel) {
		double score = 0;
		double scoreEL  = 0;
		double scorePayout = 0;
		int electricityFeedback = satisfactionLevel.getElectricityFeedback();
		int payoutFeedback = satisfactionLevel.getPayoutFeedback();
		
		switch (electricityFeedback) {
		case 5:
			scoreEL = 1;
			break;
		case 4:
			scoreEL = 0.5;
			break;
		case 3:
			scoreEL = 0;
			break;
		case 2:
			scoreEL = -1;
			break;
		case 1:
			scoreEL = -1.5;
			break;
		default:
			scoreEL += 0;
			break;
		}
		
		switch (payoutFeedback) {
		case 5:
			scorePayout = 1;
			break;
		case 4:
			scorePayout = 0.5;
			break;
		case 3:
			scorePayout = 0;
			break;
		case 2:
			scorePayout = -1;
			break;
		case 1:
			scorePayout = -1.5;
			break;
		default:
			scorePayout = 0;
			break;
		}
		
		scoreEL *= Parameter.ELECTRICITIY_REWARD_MULTIPLIER;
		scorePayout *= Parameter.PAYOUT_REWARD_MULTIPLIER;
		score = scoreEL + scorePayout;
		return score;
	}
	
	public static double RewardPatternMixed(UserSatisfaction satisfactionLevel) {
		double score = 0;
		double scoreEL  = 0;
		double scorePayout = 0;
		int electricityFeedback = satisfactionLevel.getElectricityFeedback();
		int payoutFeedback = satisfactionLevel.getPayoutFeedback();
		
		switch (electricityFeedback) {
		case 5:
			scoreEL = 1;
			break;
		case 4:
			scoreEL = 0.5;
			break;
		case 3:
			scoreEL = 0;
			break;
		case 2:
			scoreEL = -0.5;
			break;
		case 1:
			scoreEL = -1;
			break;
		default:
			scoreEL += 0;
			break;
		}
		
		switch (payoutFeedback) {
		case 5:
			scorePayout = 1;
			break;
		case 4:
			scorePayout = 0.5;
			break;
		case 3:
			scorePayout = 0;
			break;
		case 2:
			scorePayout = -0.5;
			break;
		case 1:
			scorePayout = -1;
			break;
		default:
			scorePayout = 0;
			break;
		}
		
		scoreEL *= Parameter.ELECTRICITIY_REWARD_MULTIPLIER;
		scorePayout *= Parameter.PAYOUT_REWARD_MULTIPLIER;
		score = scoreEL + scorePayout;
		return score;
	}
	public static double RealRewardPattern(User user) {
		return (user.getPerformance().normalizedScore()*1000)-750;
		
	}
}
