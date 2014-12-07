package models;

public class School extends MetaData {
	
	private int schoolId;
	private String schoolName;
	private String schoolLocation;
	private double relevance;

	public int getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getSchoolLocation() {
		return schoolLocation;
	}

	public void setSchoolLocation(String schoolLocation) {
		this.schoolLocation = schoolLocation;
	}

	public double getRelevance() {
		return relevance;
	}

	public void setRelevance(double relevance) {
		this.relevance = relevance;
	}

	public School() {
		super();
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof School) {
			if(this.getSchoolName().equals(((School) o).getSchoolName()))
				return true;
		}
		return false;
	}

}
