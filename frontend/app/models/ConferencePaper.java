package models;

//Corresponds to inproceedings entity in DBLP
public class ConferencePaper extends Publication {

	private int conferencePaperId;
	private int conferenceId;
	private int publicationId;
	private String pages;
	private String url;
	
	public int getConferencePaperId() {
		return conferencePaperId;
	}

	public void setConferencePaperId(int conferencePaperId) {
		this.conferencePaperId = conferencePaperId;
	}

	public int getConferenceId() {
		return conferenceId;
	}

	public void setConferenceId(int conferenceId) {
		this.conferenceId = conferenceId;
	}
	
	public int getPublicationId() {
		return publicationId;
	}

	public void setPublicationId(int publicationId) {
		this.publicationId = publicationId;
	}

	public String getPages() {
		return pages;
	}

	public void setPages(String pages) {
		this.pages = pages;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public ConferencePaper(){
		super();
	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof ConferencePaper) {
			if(this.getPublicationTitle().equals(((ConferencePaper) o).getPublicationTitle()))
				return true;
		}
		return false;
	}
}
