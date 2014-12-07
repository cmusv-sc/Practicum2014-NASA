package models;

// Corresponds to incollection entity in DBLP
public class BookChapter extends Publication{
	
	/* `bookChapterId` int(11) NOT NULL AUTO_INCREMENT,
  `bookChapterDataId` int(11) DEFAULT NULL,
  `publicationId` int(11) DEFAULT NULL,
  `pages` varchar(50) DEFAULT NULL,
  */

	private int bookChapterId;
	private int bookChapterDataId;
	private int publicationId;
	private String pages;
	
	public int getBookChapterId() {
		return bookChapterId;
	}

	public void setBookChapterId(int bookChapterId) {
		this.bookChapterId = bookChapterId;
	}
	
	public int getBookChapterDataId() {
		return bookChapterDataId;
	}

	public void setBookChapterDataId(int bookChapterDataId) {
		this.bookChapterDataId = bookChapterDataId;
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

	public BookChapter(){
		super();
	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof BookChapter) {
			if(this.getPublicationTitle().equals(((BookChapter) o).getPublicationTitle()))
				return true;
		}
		return false;
	}
}
