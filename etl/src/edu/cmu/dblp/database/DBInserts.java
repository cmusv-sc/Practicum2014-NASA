package edu.cmu.dblp.database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import edu.cmu.dblp.model.Author;
import edu.cmu.dblp.model.Book;
import edu.cmu.dblp.model.BookChapter;
import edu.cmu.dblp.model.BookChapterData;
import edu.cmu.dblp.model.Conference;
import edu.cmu.dblp.model.ConferencePaper;
import edu.cmu.dblp.model.Journal;
import edu.cmu.dblp.model.JournalArticle;
import edu.cmu.dblp.model.MetaData;
import edu.cmu.dblp.model.PhdThesis;
import edu.cmu.dblp.model.Publication;
import edu.cmu.dblp.model.School;//Importing all the models
import edu.cmu.dblp.model.WebPage;

public class DBInserts {
	/*
	 * This method inserts the data into database tables based on the type of the publication object
	 */
	private static final Logger logger = Logger.getLogger( DBInserts.class.getName() );

	static HashMap<String, Integer> authors = null;
	static HashMap<String, School> schools = null;
	static HashMap<String, Journal> journals = null;

	static HashMap<String, Integer> citations = null;

	int publicationCounter = 0;
	int authorCounter = 0;
	int publicationAuthorCounter = 0;
	int bookCounter = 0;
	int bookChapterCounter = 0;
	int bookChapterDataCounter = 0;
	int conferenceCounter = 0;
	int conferencePaperCounter = 0;
	int journalCounter = 0;
	int journalArticleCounter = 0;
	int schoolCounter = 0;
	int phdThesisCounter = 0;
	int webPageCounter = 0;

	static BufferedWriter publicationFile = null;
	static BufferedWriter authorFile = null;
	static BufferedWriter publicationAuthorFile = null;

	static BufferedWriter bookFile = null;
	static BufferedWriter bookChapterFile = null;
	static BufferedWriter bookChapterDataFile = null;
	static BufferedWriter conferenceFile = null;
	static BufferedWriter conferencePaperFile = null;
	static BufferedWriter journalFile = null;
	static BufferedWriter journalArticleFile = null;
	static BufferedWriter schoolFile = null;
	static BufferedWriter phdThesisFile = null;
	static BufferedWriter webPageFile = null;
	
	static BufferedReader citationsFile = null;

	static int citationCounter = 0;


	public DBInserts(){
		FileHandler fh;  
		try {
			fh = new FileHandler("logs/etl.log");
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();  
			fh.setFormatter(formatter);
			logger.setUseParentHandlers(false);
		} catch (SecurityException e) {  
			e.printStackTrace();  
		} catch (IOException e) {  
			e.printStackTrace();  
		}
		try {
			publicationFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("csv/publication.csv"),"UTF-8"));
			authorFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("csv/author.csv"),"UTF-8"));
			publicationAuthorFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("csv/publicationAuthor.csv"),"UTF-8"));
			bookFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("csv/books.csv"),"UTF-8"));
			bookChapterFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("csv/bookChapter.csv"),"UTF-8"));
			bookChapterDataFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("csv/bookChapterData.csv"),"UTF-8"));
			conferenceFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("csv/conference.csv"),"UTF-8"));
			conferencePaperFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("csv/conferencePaper.csv"),"UTF-8"));
			journalFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("csv/journal.csv"),"UTF-8"));
			journalArticleFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("csv/journalArticle.csv"),"UTF-8"));
			schoolFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("csv/school.csv"),"UTF-8"));
			phdThesisFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("csv/phd.csv"),"UTF-8"));
			webPageFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("csv/webPage.csv"),"UTF-8"));

			citationsFile = new BufferedReader(new InputStreamReader(new FileInputStream("csv/citations.txt")));
		} catch (IOException e) {
			e.printStackTrace();
		}

		authors = new HashMap<String, Integer>();
		schools = new HashMap<String, School>();
		journals = new HashMap<String, Journal>();
		citations = new HashMap<String, Integer>();
	}

	private static DBInserts instance = null;
	public static DBInserts getInstance() {
		if(instance == null) {
			instance = new DBInserts();
			try {
				fillCitationsMap();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return instance;
	}

	public static DBConnection dBConnection = new MySQLConnection();
	public static List < String > explicitColumnNames = new ArrayList < String > ();
	//	public static explicitColumnNames = null;


	public static String removeLastChar(String s) {
		if (s == null || s.length() == 0) {
			return s;
		}
		return s.substring(0, s.length()-1);
	}


	public void DBInserts(Publication publication, MetaData metadata) throws Exception {

		String authorsList = "";

		int publicationId = ++publicationCounter;

		/*-------------Insert into master table publication ends here ----------------------------*/

		if(publication.getAuthorNames().size()==0){
			return;
		}

		for (int i = 0; i < publication.getAuthorNames().size(); i++) {
			//List < Author > author = new DBSelectQueries < Author > (Author.class, dBConnection, explicitColumnNames, "authorName='" + publication.getAuthorNames().get(i).replace("'", "\\'") + "'").getResults();
			int authorId = 0;

			//if (author.isEmpty()) {
			/*-------Insert into author table starts here -----------*/
			Author authorInsert = new Author();

			authorInsert.setAuthorName(publication.getAuthorNames().get(i));
			authorInsert.setInstitution("");//Need to assign actual value if in future we start getting institution from the dataset

			if(authors.containsKey(authorInsert.getAuthorName().toUpperCase())){
				authorId = authors.get(authorInsert.getAuthorName().toUpperCase());
				publicationAuthorFile.write(++publicationAuthorCounter + "@@@" + publicationId + "@@@" + authorId + "\n");
			}
			else{
				authors.put(authorInsert.getAuthorName().toUpperCase(), ++authorCounter);
				publicationAuthorFile.write(++publicationAuthorCounter + "@@@" + publicationId + "@@@" + authorCounter + "\n");
			}
			authorsList += authorInsert.getAuthorName().toUpperCase() + ",";
		}

		if(citations.containsKey(publication.getPublicationTitle())){
			publication.setCitationCount(citations.get(publication.getPublicationTitle()));
			//System.out.println(++citationCounter);
		}

		publicationFile.write(publicationCounter + "@@@" 
				+ publication.getPublicationChannel() 
				+ "@@@" + publication.getCitationCount()
				+ "@@@" + removeLastChar(authorsList)
				+ "@@@" + publication.getPublicationTitle()
				+ "@@@" + publication.getYear()
				+ "@@@" + publication.getUrl()
				+ "@@@" + publication.getPublisher()
				+ "@@@" + publication.getNote()
				+ "@@@" + publication.getKeywords()
				+ "@@@" + publication.getTags()
				+ "@@@" + publication.getType()+ "\n");

		if (publication instanceof Book) {
			String editors = "";
			if(((Book) publication).getEditors() != null){
				for (String editor : ((Book)publication).getEditors()) {
					editors += editor + ",";
				}
			}

			bookFile.write(++bookCounter + "@@@"
					+ publicationId + "@@@"
					+ removeLastChar(editors) + "@@@"
					+ ((Book) publication).getPages() + "@@@"
					+ ((Book) publication).getMonth() + "@@@"
					+ publication.getUrl() + "@@@"
					+ publication.getPublisher() + "@@@"
					+ ((Book) publication).getPublisherAddress() + "@@@"
					+ ((Book) publication).getIsbn() + "@@@"
					+ ((Book) publication).getSeries() + "\n");
		} 
		else if (publication instanceof BookChapter) {
			bookChapterDataFile.write(++bookChapterDataCounter + "@@@"
					+((BookChapterData) metadata).getBookChapterName() + "@@@"
					+ ((BookChapterData) metadata).getRelevance() + "\n");

			bookChapterFile.write(++bookChapterCounter + "@@@"
					+bookChapterDataCounter + "@@@"
					+publicationId + "@@@"
					+((BookChapter) publication).getPages() + "\n");
		} 
		else if (publication instanceof ConferencePaper) {

			conferenceFile.write(++conferenceCounter + "@@@"
					+ ((Conference) metadata).getConferenceName() + "@@@"
					+ ((Conference) metadata).getConferenceLocation() + "@@@"
					+ ((Conference) metadata).getRelevance() + "\n");

			conferencePaperFile.write(++conferencePaperCounter + "@@@"
					+ conferenceCounter + "@@@"
					+ publicationId + "@@@"
					+ ((ConferencePaper) publication).getPages() + "@@@"
					+ publication.getUrl() + "\n");
		} 
		else if (publication instanceof JournalArticle) {
			Journal journal = new Journal();

			journal.setJournalName(((Journal) metadata).getJournalName());
			journal.setRelevance(((Journal) metadata).getRelevance());

			if(journals.containsKey(journal.getJournalName())){
				journal = journals.get(journal.getJournalName());
			}
			else{
				journal.setJournalId(++journalCounter);
				journals.put(journal.getJournalName(), journal);
			}

//			journalFile.write(++journalCounter + "@@@"
//					+ ((Journal) metadata).getJournalName() + "@@@"
//					+ ((Journal) metadata).getRelevance() + "\n");

			journalArticleFile.write(++journalArticleCounter + "@@@"
					+ journal.getJournalId() + "@@@"
					+ publicationId + "@@@"
					+ ((JournalArticle) publication).getPages() + "@@@"
					+ ((JournalArticle) publication).getVolume() + "@@@"
					+ ((JournalArticle) publication).getColumns() + "@@@"
					+ ((JournalArticle) publication).getMonth() + "@@@"
					+ ((JournalArticle) publication).getUrl() + "\n");

		} 
		else if (publication instanceof PhdThesis) {
			School school = new School();

			school.setSchoolName(((School) metadata).getSchoolName());
			school.setSchoolLocation(((School) metadata).getSchoolLocation());
			school.setRelevance(((School) metadata).getRelevance());

			if(schools.containsKey(school.getSchoolName())){
				school = schools.get(school.getSchoolName());
			}
			else{
				school.setSchoolId(++schoolCounter);
				schools.put(school.getSchoolName(), school);
			}

			//			schoolFile.write(++schoolCounter + "@@@"
			//					+ ((School) metadata).getSchoolName() + "@@@"
			//					+ ((School) metadata).getSchoolLocation() + "@@@"
			//					+ ((School) metadata).getRelevance() + "\n");

			phdThesisFile.write(++phdThesisCounter + "@@@"
					+ school.getSchoolId() + "@@@"
					+ publicationId + "@@@"
					+ ((PhdThesis) publication).getDepartment() + "@@@"
					+ ((PhdThesis) publication).getAdvisorName() + "\n");

		} 
		else if (publication instanceof WebPage) {

			webPageFile.write(++webPageCounter + "@@@"
					+ publicationId + "@@@"
					+ publication.getUrl() + "@@@"
					+ ((WebPage) publication).getAccessDate() + "\n");
		}

	}


	public static void finalizeProcess(){
		try {
			Iterator it = authors.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry author = (Map.Entry)it.next();
				authorFile.write(author.getValue() + "@@@" + author.getKey() + "@@@" + "" + "\n");
				it.remove(); // avoids a ConcurrentModificationException
			}

			Iterator itSchool = schools.entrySet().iterator();
			while (itSchool.hasNext()) {
				Map.Entry school = (Map.Entry)itSchool.next();
				schoolFile.write(((School)school.getValue()).getSchoolId() + "@@@"
						+ ((School)school.getValue()).getSchoolName() + "@@@"
						+ ((School)school.getValue()).getSchoolLocation() + "@@@"
						+ ((School)school.getValue()).getRelevance() + "\n");
				itSchool.remove(); // avoids a ConcurrentModificationException
			}


			Iterator itJournal = journals.entrySet().iterator();
			while (itJournal.hasNext()) {
				Map.Entry journal = (Map.Entry)itJournal.next();
				journalFile.write(((Journal)journal.getValue()).getJournalId() + "@@@"
						+ ((Journal)journal.getValue()).getJournalName() + "@@@"
						+ ((Journal)journal.getValue()).getRelevance() + "\n");
				itJournal.remove(); // avoids a ConcurrentModificationException
			}

			publicationAuthorFile.close();
			publicationFile.close();
			authorFile.close();

			bookFile.close();
			bookChapterFile.close();
			bookChapterDataFile.close();
			conferenceFile.close();
			conferencePaperFile.close();
			journalFile.close();
			journalArticleFile.close();
			schoolFile.close();
			phdThesisFile.close();
			webPageFile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void fillCitationsMap() throws IOException{
		String line = "";
		
		int counter = 0;
		String title = "";
		
		while ((line = citationsFile.readLine()) != null) {
			if(line.trim().length() != 0){
				if(line.startsWith("#*")){
					counter = 0;
					title = line.substring(2);
				}
				else if(line.startsWith("#%")){
					counter++;
				}
			}
			else{
				if(!citations.containsKey(title)){
					citations.put(title, counter);
				}
			}
	    }
	}


}