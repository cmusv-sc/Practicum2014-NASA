package edu.cmu.jung;

/**
 * @author NASA-Trust-Team
 * 
 */

import edu.cmu.trustprocessor.*;
import edu.cmu.DBLPProcessor.*;

public class Node {
	private DBLPUser user;
	private int level = 1;
	private boolean visited = false;
	
	private static int count;
	private int id;

	private DBLPTrustModel dblpTrustModel;
	
	private String name;
	private long dblpUserId;
	private double popularity;
	
	public Node(DBLPUser user) {
		super();
		this.user = user;
	}

	public DBLPUser getUser() {
		return user;
	}

	public void setUser(DBLPUser user) {
		this.user = user;
	}
	
	public void setLevel(int passedLevel) {
		this.level = passedLevel;
	}
	
	public int getLevel() {
		return level;
	}
	
	public boolean isThisPassedNode(DBLPUser user) {
		if(this.user == user)
			return true;
		else
			return false;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	
	public DBLPTrustModel getDblpTrustModel() {
		return dblpTrustModel;
	}

	public void setDblpTrustModel(DBLPTrustModel dblpTrustModel) {
		this.dblpTrustModel = dblpTrustModel;
	}
	
	public long getDBLPUserId() {
		return dblpUserId;
	}

	public void setDBLPUserId(long dblpUserId) {
		this.dblpUserId = dblpUserId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = new String(name);
	}

	public double getPopularity() {
		return popularity;
	}

	public void setPopularity(double popularity) {
		this.popularity = popularity;
	}

	public String toString() {
		DBLPKnowledgeFactor dblpKnowledgeFactor = dblpTrustModel
				.getDblpKnowledgeFactor();
		DBLPSocialFactor dblpSocialFactor = dblpTrustModel
				.getDblpSocialFactor();

		KPaperPublished kPaperPublished = dblpKnowledgeFactor
				.getkPaperPublished();
		KCoauthorship kCoauthorship = dblpSocialFactor.getkCoauthorship();
		// KCitePower kCitePower = kPaperPublished.getkCitePower();
		System.out.println("Trust Value for Charles: "
				+ dblpTrustModel.getTrustValue());

		System.out.println("Value of KPaperPublished: "
				+ kPaperPublished.getFinalKPaperPublished());
		// KCoauthorship kCoauthorship =
		// dblpTrustProcessor.calculateKCoauthorship(51);
		// System.out.println(kCoauthorship.getRecentCoauthorshipNCOX());
		// System.out.println(kCoauthorship.getIntermediateCoauthorshipNCOX());
		// System.out.println(kCoauthorship.getOldCoauthorshipNCOX());
		// System.out.println(kCoauthorship.getTimeScaledCoauthorship());
		System.out.println("Value of coauthorships: "
				+ kCoauthorship.getTimeScaledCoauthorship());
		System.out.println("Set of coauthorship edges: "
				+ kCoauthorship.getCoauthorIdToSocialFactorFromCoauthor()
						.values());
		
		return "<html>Name:" + getName() + "<br>Trust Value:" + getPopularity()
				+ "<br>Knowledge Factor:"
				+ kPaperPublished.getFinalKPaperPublished()
				+ "<br>Social Factor:"
				+ kCoauthorship.getTimeScaledCoauthorship();		
	}
	
}