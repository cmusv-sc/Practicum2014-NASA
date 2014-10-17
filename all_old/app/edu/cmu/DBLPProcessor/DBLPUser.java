package edu.cmu.DBLPProcessor;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="dblpuser")
public class DBLPUser {

	public void setId(int id) {
		this.id = id;
	}

	private static int count;
	private int id;
	private String name;
	private List<Publication> publication;
	private List<Long> coauthorship;
	private List<Coauthorship> coAuthors = new ArrayList<Coauthorship>();

	public DBLPUser() {
		super();
		id = ++count;
		name = null;
		publication = new ArrayList<Publication>();
		coauthorship = new ArrayList<Long>();
	}

	@XmlElement(name="id")
	public int getId() {
		return id;
	}

	@XmlElement(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElementWrapper(name="publicationlist")
	@XmlElement(name="publicationid")
	public List<Publication> getPublication() {
		return publication;
	}

	public void setPublication (Publication publication) {
		if(! this.publication.contains(publication))
			this.publication.add(publication);
	}

	public int countPublication(){
		return getPublication().size();
	}

	@XmlElementWrapper(name="coauthorshiplist")
	@XmlElement(name="coauthorshipid")
	public List<Long> getCoauthorship() throws JAXBException {
		Map<Integer,Integer> coauthorids = listCoauthorID();
		Map<String, Integer> coauthornames = listCoauthorNames();
		processCoauthorshipID(coauthorids);
		processCoauthorNames(coauthornames);
		return coauthorship;
	}

	public List<Coauthorship> getCoAuthors() throws JAXBException {
		Map<Integer,Integer> coauthorids = listCoauthorID();
		Map<String, Integer> coauthornames = listCoauthorNames();
		processCoauthorshipID(coauthorids);
		processCoauthorNames(coauthornames);
		return this.coAuthors;
	}

	public void setPublication(List<Publication> publication) {
		this.publication = publication;
	}

	public void setCoauthorship(List<Long> coauthorship) {
		this.coauthorship = coauthorship;
	}

	public void processCoauthorshipID (Map<Integer,Integer> coauthorids) throws JAXBException {
		Iterator<Integer> iterator = coauthorids.keySet().iterator();
		this.coauthorship.clear();
		this.coAuthors.clear();
		while(iterator.hasNext())
		{
			Coauthorship coauthorshipObject = new Coauthorship();
			int coauthorid = iterator.next();
			int count = coauthorids.get(coauthorid);
			coauthorshipObject.setUserid(this.id);
			coauthorshipObject.setCoauthorid(coauthorid);
			coauthorshipObject.setCount(count);
			coauthorshipObject.setAuthorName(name);
			long coauthorshipid = coauthorshipObject.getCoauthorshipid();
			//String filename = "DBLP_XML/coauthorship"+coauthorshipid+".xml";
			//writeXMLCoauthorship(coauthorshipObject, filename);
			for(Publication p:publication) {
				coauthorshipObject.setDate(p.getYear());
			}

			coauthorshipObject.setPublicationList(publication);
			this.coauthorship.add(coauthorshipid);
			this.coAuthors.add(coauthorshipObject);
		}
	}
	
	
	private void processCoauthorNames(Map<String, Integer> coauthornames) {
		Iterator<String> iterator = coauthornames.keySet().iterator();
		while(iterator.hasNext())
		{
			String coauthorname = iterator.next();
			int id = coauthornames.get(coauthorname);
			
			for(int i = 0; i < this.coAuthors.size() ; i++) {
				Coauthorship coauthorshipObject = this.coAuthors.get(i);
				if(coauthorshipObject.getCoauthorid() == id) {
					coauthorshipObject.setCoauthorName(coauthorname);
				}
				this.coAuthors.set(i, coauthorshipObject);
			}
		}
		
	}
	
	public int countCoauthorship(){
		return coauthorship.size();
	}

	public Map<String, Integer> listCoauthorNames() {
		//loop all publication and store coauthor id and his count in hm
		Map<String, Integer> coauthornametoID = new HashMap<String, Integer>();
		if (this.publication!=null)
		{
			for(int i=0; i<publication.size(); i++)
			{

				List<String> authornames = publication.get(i).getAuthornames();
				if(authornames == null) 
					authornames = publication.get(i).getEditornames();
				
				List<Integer> authors_id = publication.get(i).getAuthor();
				if(authors_id==null) authors_id = publication.get(i).getEditor();
				
				for(int j = 0; j< authornames.size() ; j++) {
					String coauthorname = authornames.get(j);
					int coauthorId = authors_id.get(j);
					
					if(coauthorname != this.name) {
						coauthornametoID.put(coauthorname, coauthorId);
					}
				}
			}
		}

		return coauthornametoID;
	}


	@SuppressWarnings("unused")
	private Map<Integer, Integer> listCoauthorID() {

		Map<Integer,Integer> coauthors = new HashMap<Integer,Integer>();
		if (this.publication!=null)
		{
			for(int i=0; i<publication.size(); i++)
			{	
				List<Integer> authors_id = publication.get(i).getAuthor();
				if(authors_id==null) authors_id = publication.get(i).getEditor();
				for(int j=0; j<authors_id.size();j++)
				{
					int coauthorId = authors_id.get(j);
					if(coauthorId!=this.id)
					{
						int count = 1;
						if(coauthors.containsKey(coauthorId)) 
							count = coauthors.get(coauthorId)+1;

						coauthors.put(coauthorId, count);
					}
				}
			}
		}

		return coauthors;
	}
	
	private void writeXMLCoauthorship(Coauthorship coauthorship, String filename) throws JAXBException{
		JAXBContext context = JAXBContext.newInstance(Coauthorship.class);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		File file = new File(filename);
		m.marshal(coauthorship, file);
	}
}
