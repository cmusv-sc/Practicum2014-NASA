package models;

//Corresponds to phd-thesis entity in DBLP
public class PhdThesis extends Publication {

	private int phdThesisId;
	private int schoolId;
	private int publicationId;
	private String department;
	private String advisorName;

	public PhdThesis(){
		super();
	}
	
	public int getPhdThesisId() {
		return phdThesisId;
	}

	public void setPhdThesisId(int phdThesisId) {
		this.phdThesisId = phdThesisId;
	}

	public int getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}
	
	public int getPublicationId() {
		return publicationId;
	}

	public void setPublicationId(int publicationId) {
		this.publicationId = publicationId;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getAdvisorName() {
		return advisorName;
	}

	public void setAdvisorName(String advisorName) {//Jisha: Changed the method name to setAdvisorName 
		//from setAdvisorId to avoid confusion
		this.advisorName = advisorName;
	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof PhdThesis) {
			if(this.getPublicationTitle().equals(((PhdThesis) o).getPublicationTitle()))
				return true;
		}
		return false;
	}
}
