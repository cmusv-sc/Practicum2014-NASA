package edu.cmu.trustprocessor;

import edu.cmu.DBLPProcessor.*;;

public class DBLPTrustModel {

	public double getTrustValue() {
		return trustValue;
	}

	public void setTrustValue(double trustValue) {
		this.trustValue = trustValue;
	}

	private double trustValue;
	private DBLPUser dblpUser;
	
	public DBLPUser getDblpUser() {
		return dblpUser;
	}

	public void setDblpUser(DBLPUser dblpUser) {
		this.dblpUser = dblpUser;
	}

	private DBLPKnowledgeFactor dblpKnowledgeFactor;
	private DBLPSocialFactor dblpSocialFactor;

	public DBLPKnowledgeFactor getDblpKnowledgeFactor() {
		return dblpKnowledgeFactor;
	}

	public void setDblpKnowledgeFactor(DBLPKnowledgeFactor dblpKnowledgeFactor) {
		this.dblpKnowledgeFactor = dblpKnowledgeFactor;
	}

	public DBLPSocialFactor getDblpSocialFactor() {
		return dblpSocialFactor;
	}

	public void setDblpSocialFactor(DBLPSocialFactor dblpSocialFactor) {
		this.dblpSocialFactor = dblpSocialFactor;
	}	
	

}
