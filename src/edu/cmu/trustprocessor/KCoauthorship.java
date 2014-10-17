package edu.cmu.trustprocessor;

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
	
	
	
//	private double recentCoauthorshipNCOX;
//	private double intermediateCoauthorshipNCOX;
//	private double oldCoauthorshipNCOX;
//	
//	public double getRecentCoauthorshipNCOX() {
//		return recentCoauthorshipNCOX;
//	}
//	public void setRecentCoauthorshipNCOX(double recentCoauthorshipNCOX) {
//		this.recentCoauthorshipNCOX = recentCoauthorshipNCOX;
//	}
//	public double getIntermediateCoauthorshipNCOX() {
//		return intermediateCoauthorshipNCOX;
//	}
//	public void setIntermediateCoauthorshipNCOX(double intermediateCoauthorshipNCOX) {
//		this.intermediateCoauthorshipNCOX = intermediateCoauthorshipNCOX;
//	}
//	public double getOldCoauthorshipNCOX() {
//		return oldCoauthorshipNCOX;
//	}
//	public void setOldCoauthorshipNCOX(double oldCoauthorshipNCOX) {
//		this.oldCoauthorshipNCOX = oldCoauthorshipNCOX;
//	}
//	public double getTimeScaledCoauthorship() {
//		return timeScaledCoauthorship;
//	}
//	public void setTimeScaledCoauthorship(double timeScaledCoauthorship) {
//		this.timeScaledCoauthorship = timeScaledCoauthorship;
//	}
	
	
}
