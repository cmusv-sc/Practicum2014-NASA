package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import play.db.DB;

// Corresponds to a wrapper class for all types of publications in DBLP
public class Publication {
	
	private int publicationId;
	private String publicationChannel;
	private int citationCount;
	private List<String> authorNames = new ArrayList<String>();
	private String publicationTitle;
	private String year;
	private String url;
	private String publisher;
	private String note;
	private String keywords;
	private List<String> tags;
	private String type;
	
	public Publication(int id, Connection conn) throws Exception{
		
		
		
		PreparedStatement preparedStatement = util.SQLQueries.getPublicationObject(conn, id );// Query to get all userInformation and set the user object
		ResultSet resultSet = preparedStatement.executeQuery();
		
		resultSet.first();
		
		this.publicationId = id;
		//this.authorNames = resultSet.getString("authorNames");
		this.citationCount = Integer.parseInt(resultSet.getString("citationCount"));
		this.keywords = resultSet.getString("keywords");
		this.note = resultSet.getString("note");
		this.publicationChannel = resultSet.getString("publicationChannel");
		this.publisher = resultSet.getString("publisher");
		this.year = resultSet.getString("year");
		this.publicationTitle =  resultSet.getString("publicationTitle");
		this.type = resultSet.getString("type");
		
		
	}
	
	public int getPublicationId() {
		return publicationId;
	}

	public void setPublicationId(int publicationId) {
		this.publicationId = publicationId;
	}

	public String getPublicationChannel() {
		return publicationChannel;
	}

	public void setPublicationChannel(String publicationChannel) {
		this.publicationChannel = publicationChannel;
	}

	public int getCitationCount() {
		return citationCount;
	}

	public void setCitationCount(int citationCount) {
		this.citationCount = citationCount;
	}

	public List<String> getAuthorNames() {
		return authorNames;
	}

	public void setAuthorNames(List<String> authorNames) {
		this.authorNames = authorNames;
	}

	public String getPublicationTitle() {
		return publicationTitle;
	}

	public void setPublicationTitle(String publicationTitle) {
		this.publicationTitle = publicationTitle;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Publication() {
		super();
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Publication) {
			if(this.getPublicationTitle().equals(((Publication) o).getPublicationTitle()))
				return true;
		}
		return false;
	}
}
