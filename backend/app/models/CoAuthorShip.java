package models;

import java.util.ArrayList;
import java.util.List;

public class CoAuthorShip {

	private long coauthorshipid;
	private int userid;
	private int coauthorid;
	private int count;
	private String authorName;
	private String coauthorName;
	private List<String> date = new ArrayList<String>();
	private List<Publication> publicationList = new ArrayList<Publication>();
	
	public long getCoauthorshipid() {
		return coauthorshipid;
	}

	public void setCoauthorshipid(long coauthorshipid) {
		this.coauthorshipid = coauthorshipid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getCoauthorid() {
		return coauthorid;
	}

	public void setCoauthorid(int coauthorid) {
		this.coauthorid = coauthorid;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getCoauthorName() {
		return coauthorName;
	}

	public void setCoauthorName(String coauthorName) {
		this.coauthorName = coauthorName;
	}

	public List<String> getDate() {
		return date;
	}

	public void setDate(List<String> date) {
		this.date = date;
	}

	public List<Publication> getPublicationList() {
		if(publicationList==null){
			return new ArrayList<Publication>();
		}
		return publicationList;
	}

	public void setPublicationList(List<Publication> publicationList) {
		this.publicationList = publicationList;
	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof CoAuthorShip) {
			if((this.userid == ((CoAuthorShip) o).getUserid()) && (this.coauthorid == ((CoAuthorShip) o).getCoauthorid())) {
				return true;
			}
		}
		return false;
	}
}
