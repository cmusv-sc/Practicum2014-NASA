package edu.cmu.dblp.model;

//Corresponds to www entity in DBLP
public class WebPage extends Publication {

	private int webPageId;
	private int publicationId;
	private String url;
	private String accessDate;
	
	public int getWebPageId() {
		return webPageId;
	}

	public void setWebPageId(int webPageId) {
		this.webPageId = webPageId;
	}

	public int getPublicationId() {
		return publicationId;
	}

	public void setPublicationId(int publicationId) {
		this.publicationId = publicationId;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

	public String getAccessDate() {
		return accessDate;
	}

	public void setAccessDate(String accessDate) {
		this.accessDate = accessDate;
	}

	public WebPage(){
		super();
	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof WebPage) {
			if(this.getPublicationTitle().equals(((WebPage) o).getPublicationTitle()))
				return true;
		}
		return false;
	}
}
