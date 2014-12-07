package edu.cmu.dblp.model;

public class Journal extends MetaData{
	
	private int journalId;
	private String journalName;
	private double relevance;

	public int getJournalId() {
		return journalId;
	}

	public void setJournalId(int journalId) {
		this.journalId = journalId;
	}

	public String getJournalName() {
		return journalName;
	}

	public void setJournalName(String journalName) {
		this.journalName = journalName;
	}

	public double getRelevance() {
		return relevance;
	}

	public void setRelevance(double relevance) {
		this.relevance = relevance;
	}

	public Journal() {
		super();
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Journal) {
			if(this.getJournalName().equals(((Journal) o).getJournalName()))
				return true;
		}
		return false;
	}

}
