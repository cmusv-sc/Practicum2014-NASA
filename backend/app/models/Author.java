package models;

public class Author {
	
	private int authorId;
	private String authorName;
	private String institution;

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public Author() {
		super();
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Author) {
			if(this.getAuthorName().equals(((Author) o).getAuthorName()))
				return true;
		}
		return false;
	}

}
