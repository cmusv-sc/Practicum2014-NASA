package edu.cmu.dblp.model;

public class AuthorPublicationMap {
	
	private int authorPublicationMapId;
	private int publicationId;
	private int authorId;

	public int getAuthorPublicationMapId() {
		return authorPublicationMapId;
	}

	public void setAuthorPublicationMapId(int authorPublicationMapId) {
		this.authorPublicationMapId = authorPublicationMapId;
	}

	public int getPublicationId() {
		return publicationId;
	}

	public void setPublicationId(int publicationId) {
		this.publicationId = publicationId;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public AuthorPublicationMap() {
		super();
	}

}
