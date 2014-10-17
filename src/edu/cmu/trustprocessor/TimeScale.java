package edu.cmu.trustprocessor;

public class TimeScale {
	
	private int recentYears = TrustModelWeights.recentYears;				// numberOfYearsRecent
	private int intermediateYears = TrustModelWeights.intermediateYears;
	// private int oldYearsNYZ = 
	
	private final double tRecent = TrustModelWeights.tRecent;
	

	
	public int getRecentYears() {
		return recentYears;
	}

	public void setRecentYears(int recentYears) {
		this.recentYears = recentYears;
	}

	public int getIntermediateYears() {
		return intermediateYears;
	}

	public void setIntermediateYears(int intermediateYears) {
		this.intermediateYears = intermediateYears;
	}

	private final double tIntermediate = TrustModelWeights.tIntermediate;
	private final double tOld = TrustModelWeights.tOld;

	public double gettRecent() {
		return tRecent;
	}

	public double gettIntermediate() {
		return tIntermediate;
	}

	public double gettOld() {
		return tOld;
	}

}
