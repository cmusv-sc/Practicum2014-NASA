/**
 * 
 */
package edu.cmu.DBLPProcessor;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author NASA-Trust-Team
 *
 */
@XmlRootElement(name="article")
public class Article extends DBLPElement
{
	private static int count;
	private int id;
	private int cited;
	private List<String> author;
	private List<String> editor;
	private String title;
	private String booktitle;
	private String pages;
	private String year;
	private String address;
	private String journal;
	private String volume;
	private String number;
	private String month;
	private String url;
	private String ee;
	private String cdrom;
	private List<String> cite;
	private String publisher;
	private String note;
	private List<String> crossref;
	private String isbn;
	private String series;
	private String school;
	private String chapter;
	private String mdate;
	private String key;
	private String reviewid;
	private String rating;
	private List<String> field;
	
	public Article() {
		super();
		id = ++count;
		cited=0;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getCited() {
		return cited;
	}
	
	public void setCited(int cited) {
		this.cited = cited;
	}
	
	@XmlElement(name="author")
	public List<String> getAuthor() {
		return author;
	}
	public void setAuthor(List<String> author) {
		this.author = author;
	}
	
	@XmlElement(name="editor")
	public List<String> getEditor() {
		return editor;
	}

	public void setEditor(List<String> editor) {
		this.editor = editor;
	}
	
	@XmlElement(name="title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	@XmlElement(name="booktitle")
	public String getBooktitle() {
		return booktitle;
	}

	public void setBooktitle(String booktitle) {
		this.booktitle = booktitle;
	}
	
	@XmlElement(name="pages")
	public String getPages() {
		return pages;
	}

	public void setPages(String pages) {
		this.pages = pages;
	}
	
	@XmlElement(name="year")
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	
	@XmlElement(name="address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@XmlElement(name="journal")
	public String getJournal() {
		return journal;
	}
	public void setJournal(String journal) {
		this.journal = journal;
	}

	@XmlElement(name="volume")
	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	@XmlElement(name="number")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	@XmlElement(name="month")
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	
	@XmlElement(name="url")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@XmlElement(name="ee")
	public String getEe() {
		return ee;
	}

	public void setEe(String ee) {
		this.ee = ee;
	}

	@XmlElement(name="cdrom")
	public String getCdrom() {
		return cdrom;
	}

	public void setCdrom(String cdrom) {
		this.cdrom = cdrom;
	}

	@XmlElement(name="cite")
	public List<String> getCite() {
		return cite;
	}

	public void setCite(List<String> cite) {
		this.cite = cite;
	}

	@XmlElement(name="publisher")
	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	@XmlElement(name="note")
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@XmlElement(name="crossref")
	public List<String> getCrossref() {
		return crossref;
	}

	public void setCrossref(List<String> crossref) {
		this.crossref = crossref;
	}

	@XmlElement(name="isbn")
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@XmlElement(name="series")
	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	@XmlElement(name="school")
	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	@XmlElement(name="chapter")
	public String getChapter() {
		return chapter;
	}

	public void setChapter(String chapter) {
		this.chapter = chapter;
	}
	
	@XmlAttribute(name="mdate")
	public String getMdate()
	{
		return mdate;
	}
	public void setMdate(String mdate)
	{
		this.mdate = mdate;
	}
	
	@XmlAttribute(name="key")
	public String getKey()
	{
		return key;
	}
	public void setKey(String key)
	{
		this.key = key;
	}
	
	@XmlAttribute(name="reviewid")
	public String getReviewid()
	{
		return reviewid;
	}
	public void setReviewid(String reviewid)
	{
		this.reviewid = reviewid;
	}
	
	@XmlAttribute(name="rating")
	public String getRating()
	{
		return rating;
	}
	public void setRating(String rating)
	{
		this.rating = rating;
	}
	
	@XmlElement(name="field")
	public List<String> getField() {
		return field;
	}

	public void setField(List<String> field) {
		this.field = field;
	}	
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Article) {
			if(this.getTitle().equals(((Article) o).getTitle()))
				return true;
		}
		return false;
	}
}