package models;

public class BookChapterData extends MetaData {
	
	private int bookChapterDataId;
	private String bookChapterName;
	private double relevance;

	public int getBookChapterDataId() {//Changed the name of the getter to getBookChapterDataId from getBookChapterData
		return bookChapterDataId;
	}

	public void setBookChapterDataId(int bookChapterDataId) {
		this.bookChapterDataId = bookChapterDataId;
	}

	public String getBookChapterName() {
		return bookChapterName;
	}

	public void setBookChapterName(String bookChapterName) {
		this.bookChapterName = bookChapterName;
	}

	public double getRelevance() {
		return relevance;
	}

	public void setRelevance(double relevance) {
		this.relevance = relevance;
	}

	public BookChapterData() {
		super();
	}
	
	/*@Override
	public boolean equals(Object o) {
		if(o instanceof BookChapterData) {
			if(this.getBookChapterName().equals(((BookChapterData) o).getBookChapterName()))
				return true;
		}
		return false;
	}*/

}
