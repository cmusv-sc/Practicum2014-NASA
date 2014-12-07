package edu.cmu.dblp.model;

public class Conference extends MetaData {
	
	private int conferenceId;
	private String conferenceName;
	private String conferenceLocation;
	private double relevance;

	public int getConferenceId() {
		return conferenceId;
	}

	public void setConferenceId(int conferenceId) {
		this.conferenceId = conferenceId;
	}

	public String getConferenceName() {
		return conferenceName;
	}

	public void setConferenceName(String conferenceName) {
		this.conferenceName = conferenceName;
	}

	public String getConferenceLocation() {
		return conferenceLocation;
	}

	public void setConferenceLocation(String conferenceLocation) {
		this.conferenceLocation = conferenceLocation;
	}

	public double getRelevance() {
		return relevance;
	}

	public void setRelevance(double relevance) {
		this.relevance = relevance;
	}

	public Conference() {
		super();
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Conference) {
			if(this.getConferenceName().equals(((Conference) o).getConferenceName()))
				return true;
		}
		return false;
	}

}
