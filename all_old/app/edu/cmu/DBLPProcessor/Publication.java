/**
 * 
 */
package edu.cmu.DBLPProcessor;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author NASA-Trust-Team
 *
 */
@XmlRootElement(name="publication")
public class Publication 
{   
	private int id;
	private String type;
	private int citationcount;
	private List<Integer> author = new ArrayList<Integer>();
	private List<String> authornames = new ArrayList<String>();
	private List<Integer> editor = new ArrayList<Integer>();
	private List<String> editornames = new ArrayList<String>();
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
	private List<String> cite = new ArrayList<String>();
	private String publisher;
	private String note;
	private List<String> crossref = new ArrayList<String>();
	private String isbn;
	private String series;
	private String school;
	private String chapter;
	private String mdate;
	private String key;
	private String reviewid;
	private String rating;
	private List<String> field = new ArrayList<String>();
	
	public Publication() {
		super();
	}
	
	public Publication(Article article) {
		super();
		this.id = article.getId();
		this.type = "article";
		this.citationcount = article.getCited();
		if(article.getAuthor()!=null)
		{
			this.authornames = article.getAuthor();
			List<String> getAuthor = article.getAuthor();
			for(int i=0; i<getAuthor.size(); i++)
				this.author.add(DBLPParser.mapUserNameId.get(getAuthor.get(i)));
		}
		if(article.getEditor()!=null) 
		{
			this.editornames = article.getEditor();
			List<String> getAuthor = article.getEditor();
			for(int i=0; i<getAuthor.size(); i++)
				this.author.add(DBLPParser.mapUserNameId.get(getAuthor.get(i)));
		}
		if(article.getTitle() != null)this.title = article.getTitle();
		if(article.getBooktitle()!=null) this.booktitle = article.getBooktitle();
		if(article.getPages()!=null) this.pages = article.getPages();
		if(article.getYear()!=null) this.year = article.getYear();
		if(article.getAddress()!=null) this.address = article.getAddress();
		if(article.getJournal()!=null) this.journal = article.getJournal();
		if(article.getVolume()!=null) this.volume = article.getVolume();
		if(article.getNumber()!=null) this.number = article.getNumber();
		if(article.getMonth()!=null) this.month = article.getMonth();
		if(article.getUrl()!=null) this.url = article.getUrl();
		if(article.getEe()!=null) this.ee = article.getEe();
		if(article.getCdrom()!=null) this.cdrom = article.getCdrom();
		
		if(article.getCite()!=null)
		{
			List<String> citelist = article.getCite();
			for(int i=0; i<citelist.size(); i++)
			{	
				//venkatesh change, it was giving null pointer exception here, so added an if statement
				if(DBLPParser.mapKeyTitle.get(citelist.get(i)) != null)
					this.cite.add(DBLPParser.mapKeyTitle.get(citelist.get(i)));
			}
		}
		if(article.getPublisher()!=null)this.publisher = article.getPublisher();
		if(article.getNote()!=null) this.note = article.getNote();
		if(article.getCrossref()!=null) 
		{
			List<String> citelist = article.getCrossref();
			for(int i=0; i<citelist.size(); i++)
			{
				this.cite.add(DBLPParser.mapKeyTitle.get(citelist.get(i)));
			}
		}
		if(article.getIsbn()!=null) this.isbn = article.getIsbn();
		if(article.getSeries()!=null) this.series = article.getSeries();
		if(article.getSchool()!=null) this.school = article.getSchool();
		if(article.getChapter()!=null) this.chapter = article.getChapter();
		if(article.getMdate()!=null) this.mdate = article.getMdate();
		if(article.getKey()!=null)this.key = article.getKey();
		if(article.getReviewid()!=null) this.reviewid = article.getReviewid();
		if(article.getRating()!=null) this.rating = article.getRating();
		
		if(article.getField()!=null)
		{
			List<String> fieldlist = article.getField();
			for(int i=0; i<fieldlist.size(); i++){
				this.field.add(fieldlist.get(i));
			}
		}
	}

	public Publication(Book book) {
		super();
		this.id = book.getId();
		this.type = "book";
		this.citationcount = book.getCited();
		if(book.getAuthor()!=null)
		{
			this.authornames = book.getAuthor();
			List<String> getAuthor = book.getAuthor();
			for(int i=0; i<getAuthor.size(); i++)
				this.author.add(DBLPParser.mapUserNameId.get(getAuthor.get(i)));
		}
		if(book.getEditor()!=null) 
		{
			this.editornames = book.getEditor();
			List<String> getAuthor = book.getEditor();
			for(int i=0; i<getAuthor.size(); i++)
				this.author.add(DBLPParser.mapUserNameId.get(getAuthor.get(i)));
		}
		if(book.getTitle() != null)this.title = book.getTitle();
		if(book.getBooktitle()!=null) this.booktitle = book.getBooktitle();
		if(book.getPages()!=null) this.pages = book.getPages();
		if(book.getYear()!=null) this.year = book.getYear();
		if(book.getAddress()!=null) this.address = book.getAddress();
		if(book.getJournal()!=null) this.journal = book.getJournal();
		if(book.getVolume()!=null) this.volume = book.getVolume();
		if(book.getNumber()!=null) this.number = book.getNumber();
		if(book.getMonth()!=null) this.month = book.getMonth();
		if(book.getUrl()!=null) this.url = book.getUrl();
		if(book.getEe()!=null) this.ee = book.getEe();
		if(book.getCdrom()!=null) this.cdrom = book.getCdrom();
		if(book.getCite()!=null)
		{
			List<String> citelist = book.getCite();
			for(int i=0; i<citelist.size(); i++)
			{
				this.cite.add(DBLPParser.mapKeyTitle.get(citelist.get(i)));
			}
		}
		if(book.getPublisher()!=null)this.publisher = book.getPublisher();
		if(book.getNote()!=null) this.note = book.getNote();
		if(book.getCrossref()!=null) 
		{
			List<String> citelist = book.getCrossref();
			for(int i=0; i<citelist.size(); i++)
			{
				this.cite.add(DBLPParser.mapKeyTitle.get(citelist.get(i)));
			}
		}
		if(book.getIsbn()!=null) this.isbn = book.getIsbn();
		if(book.getSeries()!=null) this.series = book.getSeries();
		if(book.getSchool()!=null) this.school = book.getSchool();
		if(book.getChapter()!=null) this.chapter = book.getChapter();
		if(book.getMdate()!=null) this.mdate = book.getMdate();
		if(book.getKey()!=null)this.key = book.getKey();
		if(book.getReviewid()!=null) this.reviewid = book.getReviewid();
		if(book.getRating()!=null) this.rating = book.getRating();
		if(book.getField()!=null)
		{
			List<String> fieldlist = book.getField();
			for(int i=0; i<fieldlist.size(); i++){
				this.field.add(fieldlist.get(i));
			}
		}
	}
	
	public Publication(Incollection incollection) {
		super();
		this.id = incollection.getId();
		this.type = "incollection";
		this.citationcount = incollection.getCited();
		if(incollection.getAuthor()!=null)
		{
			this.authornames = incollection.getAuthor();
			List<String> getAuthor = incollection.getAuthor();
			for(int i=0; i<getAuthor.size(); i++)
				this.author.add(DBLPParser.mapUserNameId.get(getAuthor.get(i)));
		}
		if(incollection.getEditor()!=null) 
		{
			this.editornames = incollection.getEditor();
			List<String> getAuthor = incollection.getEditor();
			for(int i=0; i<getAuthor.size(); i++)
				this.author.add(DBLPParser.mapUserNameId.get(getAuthor.get(i)));
		}
		if(incollection.getTitle() != null)this.title = incollection.getTitle();
		if(incollection.getBooktitle()!=null) this.booktitle = incollection.getBooktitle();
		if(incollection.getPages()!=null) this.pages = incollection.getPages();
		if(incollection.getYear()!=null) this.year = incollection.getYear();
		if(incollection.getAddress()!=null) this.address = incollection.getAddress();
		if(incollection.getJournal()!=null) this.journal = incollection.getJournal();
		if(incollection.getVolume()!=null) this.volume = incollection.getVolume();
		if(incollection.getNumber()!=null) this.number = incollection.getNumber();
		if(incollection.getMonth()!=null) this.month = incollection.getMonth();
		if(incollection.getUrl()!=null) this.url = incollection.getUrl();
		if(incollection.getEe()!=null) this.ee = incollection.getEe();
		if(incollection.getCdrom()!=null) this.cdrom = incollection.getCdrom();
		if(incollection.getCite()!=null)
		{
			List<String> citelist = incollection.getCite();
			for(int i=0; i<citelist.size(); i++)
			{
				this.cite.add(DBLPParser.mapKeyTitle.get(citelist.get(i)));
			}
		}
		if(incollection.getPublisher()!=null)this.publisher = incollection.getPublisher();
		if(incollection.getNote()!=null) this.note = incollection.getNote();
		if(incollection.getCrossref()!=null) 
		{
			List<String> citelist = incollection.getCrossref();
			for(int i=0; i<citelist.size(); i++)
			{
				this.cite.add(DBLPParser.mapKeyTitle.get(citelist.get(i)));
			}
		}
		if(incollection.getIsbn()!=null) this.isbn = incollection.getIsbn();
		if(incollection.getSeries()!=null) this.series = incollection.getSeries();
		if(incollection.getSchool()!=null) this.school = incollection.getSchool();
		if(incollection.getChapter()!=null) this.chapter = incollection.getChapter();
		if(incollection.getMdate()!=null) this.mdate = incollection.getMdate();
		if(incollection.getKey()!=null)this.key = incollection.getKey();
		if(incollection.getReviewid()!=null) this.reviewid = incollection.getReviewid();
		if(incollection.getRating()!=null) this.rating = incollection.getRating();
		if(incollection.getField()!=null) this.field = incollection.getField();
	}
	
	public Publication(Inproceedings inproceedings) {
		super();
		this.id = inproceedings.getId();
		this.type = "inproceedings";
		this.citationcount = inproceedings.getCited();
		if(inproceedings.getAuthor()!=null)
		{
			this.authornames = inproceedings.getEditor();
			List<String> getAuthor = inproceedings.getAuthor();
			for(int i=0; i<getAuthor.size(); i++)
				this.author.add(DBLPParser.mapUserNameId.get(getAuthor.get(i)));
		}
		if(inproceedings.getEditor()!=null) 
		{
			this.editornames = inproceedings.getEditor();
			List<String> getAuthor = inproceedings.getEditor();
			for(int i=0; i<getAuthor.size(); i++)
				this.author.add(DBLPParser.mapUserNameId.get(getAuthor.get(i)));
		}
		if(inproceedings.getTitle() != null)this.title = inproceedings.getTitle();
		if(inproceedings.getBooktitle()!=null) this.booktitle = inproceedings.getBooktitle();
		if(inproceedings.getPages()!=null) this.pages = inproceedings.getPages();
		if(inproceedings.getYear()!=null) this.year = inproceedings.getYear();
		if(inproceedings.getAddress()!=null) this.address = inproceedings.getAddress();
		if(inproceedings.getJournal()!=null) this.journal = inproceedings.getJournal();
		if(inproceedings.getVolume()!=null) this.volume = inproceedings.getVolume();
		if(inproceedings.getNumber()!=null) this.number = inproceedings.getNumber();
		if(inproceedings.getMonth()!=null) this.month = inproceedings.getMonth();
		if(inproceedings.getUrl()!=null) this.url = inproceedings.getUrl();
		if(inproceedings.getEe()!=null) this.ee = inproceedings.getEe();
		if(inproceedings.getCdrom()!=null) this.cdrom = inproceedings.getCdrom();
		if(inproceedings.getCite()!=null)
		{
			List<String> citelist = inproceedings.getCite();
			for(int i=0; i<citelist.size(); i++)
			{
				this.cite.add(DBLPParser.mapKeyTitle.get(citelist.get(i)));
			}
		}
		if(inproceedings.getPublisher()!=null)this.publisher = inproceedings.getPublisher();
		if(inproceedings.getNote()!=null) this.note = inproceedings.getNote();
		if(inproceedings.getCrossref()!=null) 
		{
			List<String> citelist = inproceedings.getCrossref();
			for(int i=0; i<citelist.size(); i++)
			{
				this.cite.add(DBLPParser.mapKeyTitle.get(citelist.get(i)));
			}
		}
		if(inproceedings.getIsbn()!=null) this.isbn = inproceedings.getIsbn();
		if(inproceedings.getSeries()!=null) this.series = inproceedings.getSeries();
		if(inproceedings.getSchool()!=null) this.school = inproceedings.getSchool();
		if(inproceedings.getChapter()!=null) this.chapter = inproceedings.getChapter();
		if(inproceedings.getMdate()!=null) this.mdate = inproceedings.getMdate();
		if(inproceedings.getKey()!=null)this.key = inproceedings.getKey();
		if(inproceedings.getReviewid()!=null) this.reviewid = inproceedings.getReviewid();
		if(inproceedings.getRating()!=null) this.rating = inproceedings.getRating();
		if(inproceedings.getField()!=null) this.field = inproceedings.getField();
	}

	public Publication(Mastersthesis mastersthesis) {
		super();
		this.id = mastersthesis.getId();
		this.type = "mastersthesis";
		this.citationcount = mastersthesis.getCited();
		if(mastersthesis.getAuthor()!=null)
		{
			this.authornames = mastersthesis.getAuthor();
			List<String> getAuthor = mastersthesis.getAuthor();
			for(int i=0; i<getAuthor.size(); i++)
				this.author.add(DBLPParser.mapUserNameId.get(getAuthor.get(i)));
		}
		if(mastersthesis.getEditor()!=null) 
		{
			this.authornames = mastersthesis.getEditor();
			List<String> getAuthor = mastersthesis.getEditor();
			for(int i=0; i<getAuthor.size(); i++)
				this.author.add(DBLPParser.mapUserNameId.get(getAuthor.get(i)));
		}
		if(mastersthesis.getTitle() != null)this.title = mastersthesis.getTitle();
		if(mastersthesis.getBooktitle()!=null) this.booktitle = mastersthesis.getBooktitle();
		if(mastersthesis.getPages()!=null) this.pages = mastersthesis.getPages();
		if(mastersthesis.getYear()!=null) this.year = mastersthesis.getYear();
		if(mastersthesis.getAddress()!=null) this.address = mastersthesis.getAddress();
		if(mastersthesis.getJournal()!=null) this.journal = mastersthesis.getJournal();
		if(mastersthesis.getVolume()!=null) this.volume = mastersthesis.getVolume();
		if(mastersthesis.getNumber()!=null) this.number = mastersthesis.getNumber();
		if(mastersthesis.getMonth()!=null) this.month = mastersthesis.getMonth();
		if(mastersthesis.getUrl()!=null) this.url = mastersthesis.getUrl();
		if(mastersthesis.getEe()!=null) this.ee = mastersthesis.getEe();
		if(mastersthesis.getCdrom()!=null) this.cdrom = mastersthesis.getCdrom();
		if(mastersthesis.getCite()!=null)
		{
			List<String> citelist = mastersthesis.getCite();
			for(int i=0; i<citelist.size(); i++)
			{
				this.cite.add(DBLPParser.mapKeyTitle.get(citelist.get(i)));
			}
		}
		if(mastersthesis.getPublisher()!=null)this.publisher = mastersthesis.getPublisher();
		if(mastersthesis.getNote()!=null) this.note = mastersthesis.getNote();
		if(mastersthesis.getCrossref()!=null) 
		{
			List<String> citelist = mastersthesis.getCrossref();
			for(int i=0; i<citelist.size(); i++)
			{
				this.cite.add(DBLPParser.mapKeyTitle.get(citelist.get(i)));
			}
		}
		if(mastersthesis.getIsbn()!=null) this.isbn = mastersthesis.getIsbn();
		if(mastersthesis.getSeries()!=null) this.series = mastersthesis.getSeries();
		if(mastersthesis.getSchool()!=null) this.school = mastersthesis.getSchool();
		if(mastersthesis.getChapter()!=null) this.chapter = mastersthesis.getChapter();
		if(mastersthesis.getMdate()!=null) this.mdate = mastersthesis.getMdate();
		if(mastersthesis.getKey()!=null)this.key = mastersthesis.getKey();
		if(mastersthesis.getReviewid()!=null) this.reviewid = mastersthesis.getReviewid();
		if(mastersthesis.getRating()!=null) this.rating = mastersthesis.getRating();
		if(mastersthesis.getField()!=null) this.field = mastersthesis.getField();
	}

	public Publication(Phdthesis phdthesis) {
		super();
		this.id = phdthesis.getId();
		this.type = "phdthesis";
		this.citationcount = phdthesis.getCited();
		if(phdthesis.getAuthor()!=null)
		{
			this.authornames = phdthesis.getAuthor();
			List<String> getAuthor = phdthesis.getAuthor();
			for(int i=0; i<getAuthor.size(); i++)
				this.author.add(DBLPParser.mapUserNameId.get(getAuthor.get(i)));
		}
		if(phdthesis.getEditor()!=null) 
		{
			this.editornames = phdthesis.getEditor();
			List<String> getAuthor = phdthesis.getEditor();
			for(int i=0; i<getAuthor.size(); i++)
				this.author.add(DBLPParser.mapUserNameId.get(getAuthor.get(i)));
		}
		if(phdthesis.getTitle() != null)this.title = phdthesis.getTitle();
		if(phdthesis.getBooktitle()!=null) this.booktitle = phdthesis.getBooktitle();
		if(phdthesis.getPages()!=null) this.pages = phdthesis.getPages();
		if(phdthesis.getYear()!=null) this.year = phdthesis.getYear();
		if(phdthesis.getAddress()!=null) this.address = phdthesis.getAddress();
		if(phdthesis.getJournal()!=null) this.journal = phdthesis.getJournal();
		if(phdthesis.getVolume()!=null) this.volume = phdthesis.getVolume();
		if(phdthesis.getNumber()!=null) this.number = phdthesis.getNumber();
		if(phdthesis.getMonth()!=null) this.month = phdthesis.getMonth();
		if(phdthesis.getUrl()!=null) this.url = phdthesis.getUrl();
		if(phdthesis.getEe()!=null) this.ee = phdthesis.getEe();
		if(phdthesis.getCdrom()!=null) this.cdrom = phdthesis.getCdrom();
		if(phdthesis.getCite()!=null)
		{
			List<String> citelist = phdthesis.getCite();
			for(int i=0; i<citelist.size(); i++)
			{
				this.cite.add(DBLPParser.mapKeyTitle.get(citelist.get(i)));
			}
		}
		if(phdthesis.getPublisher()!=null)this.publisher = phdthesis.getPublisher();
		if(phdthesis.getNote()!=null) this.note = phdthesis.getNote();
		if(phdthesis.getCrossref()!=null) 
		{
			List<String> citelist = phdthesis.getCrossref();
			for(int i=0; i<citelist.size(); i++)
			{
				this.cite.add(DBLPParser.mapKeyTitle.get(citelist.get(i)));
			}
		}
		if(phdthesis.getIsbn()!=null) this.isbn = phdthesis.getIsbn();
		if(phdthesis.getSeries()!=null) this.series = phdthesis.getSeries();
		if(phdthesis.getSchool()!=null) this.school = phdthesis.getSchool();
		if(phdthesis.getChapter()!=null) this.chapter = phdthesis.getChapter();
		if(phdthesis.getMdate()!=null) this.mdate = phdthesis.getMdate();
		if(phdthesis.getKey()!=null)this.key = phdthesis.getKey();
		if(phdthesis.getReviewid()!=null) this.reviewid = phdthesis.getReviewid();
		if(phdthesis.getRating()!=null) this.rating = phdthesis.getRating();
		if(phdthesis.getField()!=null) this.field = phdthesis.getField();
	}

	public Publication(Proceedings proceedings) {
		super();
		this.id = proceedings.getId();
		this.type = "proceedings";
		this.citationcount = proceedings.getCited();
		if(proceedings.getAuthor()!=null)
		{
			this.authornames = proceedings.getAuthor();
			List<String> getAuthor = proceedings.getAuthor();
			for(int i=0; i<getAuthor.size(); i++)
				this.author.add(DBLPParser.mapUserNameId.get(getAuthor.get(i)));
		}
		if(proceedings.getEditor()!=null) 
		{
			this.editornames = proceedings.getEditor();
			List<String> getAuthor = proceedings.getEditor();
			for(int i=0; i<getAuthor.size(); i++)
				this.author.add(DBLPParser.mapUserNameId.get(getAuthor.get(i)));
		}
		if(proceedings.getTitle() != null)this.title = proceedings.getTitle();
		if(proceedings.getBooktitle()!=null) this.booktitle = proceedings.getBooktitle();
		if(proceedings.getPages()!=null) this.pages = proceedings.getPages();
		if(proceedings.getYear()!=null) this.year = proceedings.getYear();
		if(proceedings.getAddress()!=null) this.address = proceedings.getAddress();
		if(proceedings.getJournal()!=null) this.journal = proceedings.getJournal();
		if(proceedings.getVolume()!=null) this.volume = proceedings.getVolume();
		if(proceedings.getNumber()!=null) this.number = proceedings.getNumber();
		if(proceedings.getMonth()!=null) this.month = proceedings.getMonth();
		if(proceedings.getUrl()!=null) this.url = proceedings.getUrl();
		if(proceedings.getEe()!=null) this.ee = proceedings.getEe();
		if(proceedings.getCdrom()!=null) this.cdrom = proceedings.getCdrom();
		if(proceedings.getCite()!=null)
		{
			List<String> citelist = proceedings.getCite();
			for(int i=0; i<citelist.size(); i++)
			{
				this.cite.add(DBLPParser.mapKeyTitle.get(citelist.get(i)));
			}
		}
		if(proceedings.getPublisher()!=null)this.publisher = proceedings.getPublisher();
		if(proceedings.getNote()!=null) this.note = proceedings.getNote();
		if(proceedings.getCrossref()!=null) 
		{
			List<String> citelist = proceedings.getCrossref();
			for(int i=0; i<citelist.size(); i++)
			{
				this.cite.add(DBLPParser.mapKeyTitle.get(citelist.get(i)));
			}
		}
		if(proceedings.getIsbn()!=null) this.isbn = proceedings.getIsbn();
		if(proceedings.getSeries()!=null) this.series = proceedings.getSeries();
		if(proceedings.getSchool()!=null) this.school = proceedings.getSchool();
		if(proceedings.getChapter()!=null) this.chapter = proceedings.getChapter();
		if(proceedings.getMdate()!=null) this.mdate = proceedings.getMdate();
		if(proceedings.getKey()!=null)this.key = proceedings.getKey();
		if(proceedings.getReviewid()!=null) this.reviewid = proceedings.getReviewid();
		if(proceedings.getRating()!=null) this.rating = proceedings.getRating();
		if(proceedings.getField()!=null) this.field = proceedings.getField();
	}

	public Publication(Www www) {
		super();
		this.id = www.getId();
		this.type = "www";
		this.citationcount = www.getCited();
		if(www.getAuthor()!=null)
		{
			this.authornames = www.getAuthor();
			List<String> getAuthor = www.getAuthor();
			for(int i=0; i<getAuthor.size(); i++)
				this.author.add(DBLPParser.mapUserNameId.get(getAuthor.get(i)));
		}
		if(www.getEditor()!=null) 
		{
			this.editornames = www.getEditor();
			List<String> getAuthor = www.getEditor();
			for(int i=0; i<getAuthor.size(); i++)
				this.author.add(DBLPParser.mapUserNameId.get(getAuthor.get(i)));
		}
		if(www.getTitle() != null)this.title = www.getTitle();
		if(www.getBooktitle()!=null) this.booktitle = www.getBooktitle();
		if(www.getPages()!=null) this.pages = www.getPages();
		if(www.getYear()!=null) this.year = www.getYear();
		if(www.getAddress()!=null) this.address = www.getAddress();
		if(www.getJournal()!=null) this.journal = www.getJournal();
		if(www.getVolume()!=null) this.volume = www.getVolume();
		if(www.getNumber()!=null) this.number = www.getNumber();
		if(www.getMonth()!=null) this.month = www.getMonth();
		if(www.getUrl()!=null) this.url = www.getUrl();
		if(www.getEe()!=null) this.ee = www.getEe();
		if(www.getCdrom()!=null) this.cdrom = www.getCdrom();
		if(www.getCite()!=null)
		{
			List<String> citelist = www.getCite();
			for(int i=0; i<citelist.size(); i++)
			{
				this.cite.add(DBLPParser.mapKeyTitle.get(citelist.get(i)));
			}
		}
		if(www.getPublisher()!=null)this.publisher = www.getPublisher();
		if(www.getNote()!=null) this.note = www.getNote();
		if(www.getCrossref()!=null) 
		{
			List<String> citelist = www.getCrossref();
			for(int i=0; i<citelist.size(); i++)
			{
				this.cite.add(DBLPParser.mapKeyTitle.get(citelist.get(i)));
			}
		}
		if(www.getIsbn()!=null) this.isbn = www.getIsbn();
		if(www.getSeries()!=null) this.series = www.getSeries();
		if(www.getSchool()!=null) this.school = www.getSchool();
		if(www.getChapter()!=null) this.chapter = www.getChapter();
		if(www.getMdate()!=null) this.mdate = www.getMdate();
		if(www.getKey()!=null)this.key = www.getKey();
		if(www.getReviewid()!=null) this.reviewid = www.getReviewid();
		if(www.getRating()!=null) this.rating = www.getRating();
		if(www.getField()!=null) this.field = www.getField();
	}

	@XmlElement(name="id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@XmlElement(name="type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@XmlElement(name="citationcount")
	public int getCited() {
		return citationcount;
	}
	
	public void setCited(int cited) {
		this.citationcount = cited;
	}
	
	@XmlElementWrapper(name = "authors")
	@XmlElement(name="author")
	public List<Integer> getAuthor() {
		return author;
	}
	public void setAuthor(List<Integer> author) {
		this.author = author;
	}
	
	@XmlElementWrapper(name = "editors")
	@XmlElement(name="editor")
	public List<Integer> getEditor() {
		return editor;
	}

	public void setEditor(List<Integer> editor) {
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
	
	@XmlElementWrapper(name = "cites")
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

	@XmlElementWrapper(name = "crossrefs")
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
	
	@XmlElement(name="mdate")
	public String getMdate()
	{
		return mdate;
	}
	public void setMdate(String mdate)
	{
		this.mdate = mdate;
	}
	
	@XmlElement(name="key")
	public String getKey()
	{
		return key;
	}
	public void setKey(String key)
	{
		this.key = key;
	}
	
	@XmlElement(name="reviewid")
	public String getReviewid()
	{
		return reviewid;
	}
	public void setReviewid(String reviewid)
	{
		this.reviewid = reviewid;
	}
	
	@XmlElement(name="rating")
	public String getRating()
	{
		return rating;
	}
	public void setRating(String rating)
	{
		this.rating = rating;
	}

	public List<String> getAuthornames() {
		return authornames;
	}

	public void setAuthornames(List<String> authornames) {
		this.authornames = authornames;
	}

	public List<String> getEditornames() {
		return editornames;
	}

	public void setEditornames(List<String> editornames) {
		this.editornames = editornames;
	}

	public int getCitationcount() {
		return citationcount;
	}

	public void setCitationcount(int citationcount) {
		this.citationcount = citationcount;
	}
	
	@XmlElementWrapper(name = "fields")
	@XmlElement(name="field")
	public List<String> getField() {
		return field;
	}

	public void setField(List<String> field) {
		this.field = field;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Publication) {
			if(this.getTitle().equals(((Publication) o).getTitle()))
				return true;
		}
		return false;
	}
}