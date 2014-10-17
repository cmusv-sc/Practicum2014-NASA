/**
 * 
 */
package edu.cmu.DBLPProcessor;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author NASA-Trust-Team
 *
 */

@XmlRootElement(name="dblp")
public class DBLPElement 
{   

	private List<Article> articleList;
	private List<Book> bookList;
	private List<Incollection> incollectionList;
	private List<Inproceedings> inproceedingsList;
	private List<Mastersthesis> mastersthesisList;
	private List<Phdthesis> phdthesisList;
	private List<Proceedings> proceedingsList;
	private List<Www> wwwList;

	@XmlElement(name="article")
	public List<Article> getArticleList() {
		return articleList;
	}

	public void setArticleList(List<Article> articleList) {
		this.articleList = articleList;
	}

	@XmlElement(name="book")
	public List<Book> getBookList() {
		return bookList;
	}

	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}

	@XmlElement(name="incollection")
	public List<Incollection> getIncollectionList() {
		return incollectionList;
	}

	public void setIncollectionList(List<Incollection> incollectionList) {
		this.incollectionList = incollectionList;
	}

	@XmlElement(name="inproceedings")
	public List<Inproceedings> getInproceedingsList() {
		return inproceedingsList;
	}

	public void setInproceedingsList(List<Inproceedings> inproceedingsList) {
		this.inproceedingsList = inproceedingsList;
	}

	@XmlElement(name="mastersthesis")
	public List<Mastersthesis> getMastersthesisList() {
		return mastersthesisList;
	}

	public void setMastersthesisList(List<Mastersthesis> mastersthesisList) {
		this.mastersthesisList = mastersthesisList;
	}

	@XmlElement(name="phdthesis")
	public List<Phdthesis> getPhdthesisList() {
		return phdthesisList;
	}

	public void setPhdthesisList(List<Phdthesis> phdthesisList) {
		this.phdthesisList = phdthesisList;
	}

	@XmlElement(name="proceedings")
	public List<Proceedings> getProceedingsList() {
		return proceedingsList;
	}

	public void setProceedingsList(List<Proceedings> proceedingsList) {
		this.proceedingsList = proceedingsList;
	}

	@XmlElement(name="www")
	public List<Www> getWwwList() {
		return wwwList;
	}

	public void setWwwList(List<Www> wwwList) {
		this.wwwList = wwwList;
	}
}