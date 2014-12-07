package models;

import java.util.ArrayList;
import java.util.List;

public class DBLPUser {
	
	private int id;
	private String name;
	private List<Publication> publicationList;
	private List<CoAuthorShip> coauthors;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Publication> getPublicationList() {
		return publicationList;
	}
	public void setPublicationList(List<Publication> publicationList) {
		this.publicationList = publicationList;
	}
	public List<CoAuthorShip> getCoauthors() {
		if(coauthors == null){
			return new ArrayList<CoAuthorShip>();
		}
		return coauthors;
	}
	public void setCoauthors(List<CoAuthorShip> coauthors) {
		this.coauthors = coauthors;
	}
	
	

}
