package models;

// Corresponds to article entity in DBLP
public class JournalArticle extends Publication {

	private int journalArticleId;
	private int journalId;
	private int publicationId;
	private String pages;
	private String volume;
	private String columns;
	private String month;
	private String url;
	
	public int getJournalArticleId() {
		return journalArticleId;
	}

	public void setJournalArticleId(int journalArticleId) {
		this.journalArticleId = journalArticleId;
	}

	public int getJournalId() {
		return journalId;
	}

	public void setJournalId(int journalId) {
		this.journalId = journalId;
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

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getColumns() {
		return columns;
	}

	public void setColumns(String columns) {
		this.columns = columns;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof JournalArticle) {
			if(this.getPublicationTitle().equals(((JournalArticle) o).getPublicationTitle()))
				return true;
		}
		return false;
	}
}
