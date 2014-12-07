package trustprocessor;

import java.util.HashMap;

public class KCoauthorship {
	private double timeScaledCoauthorship;
	
	
	private HashMap<Integer,Double> coauthorIdToSocialFactorFromCoauthor = new HashMap<Integer, Double>();

	public double getTimeScaledCoauthorship() {
		return timeScaledCoauthorship;
	}

	public void setTimeScaledCoauthorship(double timeScaledCoauthorship) {
		this.timeScaledCoauthorship = timeScaledCoauthorship;
	}

	public HashMap<Integer, Double> getCoauthorIdToSocialFactorFromCoauthor() {
		return coauthorIdToSocialFactorFromCoauthor;
	}

	public void setCoauthorIdToSocialFactorFromCoauthor(
			HashMap<Integer, Double> coauthorIdToSocialFactorFromCoauthor) {
		this.coauthorIdToSocialFactorFromCoauthor = coauthorIdToSocialFactorFromCoauthor;
	}	
}
