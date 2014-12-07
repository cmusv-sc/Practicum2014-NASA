package models;

import java.util.List;

// Corresponds to book entity in DBLP
public class Book extends Publication{

	private int bookId;
	private int publicationId;
	private List<String> editors;
	private String pages;
	private String publisherAddress;
	private String month;
	private String url;
	private String publisher;
	private String isbn;
	private String series;
	
	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getPublicationId(){
		return publicationId;
	}
	
	public void setPublicationId(int publicationId){
		this.publicationId = publicationId;
	}
	
	public List<String> getEditors() {
		return editors;
	}

	public void setEditors(List<String> editors) {
		this.editors = editors;
	}

	public String getPages() {
		return pages;
	}

	public void setPages(String pages) {
		this.pages = pages;
	}

	public String getPublisherAddress() {
		return publisherAddress;
	}

	public void setPublisherAddress(String publisherAddress) {
		this.publisherAddress = publisherAddress;
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

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public Book(){
		super();
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Book) {
			if(this.getPublicationTitle().equals(((Book) o).getPublicationTitle()))
				return true;
		}
		return false;
	}
}
