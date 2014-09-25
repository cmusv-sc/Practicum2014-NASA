package edu.cmu.DBLPProcessor;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.sax.SAXSource;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class FullDBLPDataToTextConverter {
	private static ArrayList<DBLPElement> dblpdata = new ArrayList<DBLPElement>();

	public FullDBLPDataToTextConverter() {
		super();
	}

	private static void addDblpDataElement(String fileName) throws JAXBException, SAXException, ParserConfigurationException, FileNotFoundException {
		JAXBContext jaxbContext = JAXBContext.newInstance(DBLPElement.class);

		SAXParserFactory spf = SAXParserFactory.newInstance();
		XMLReader xmlReader = spf.newSAXParser().getXMLReader();
		//System.out.println(Play.application().path().getAbsolutePath());
		InputSource inputSource = new InputSource(new FileReader(fileName));
		SAXSource source = new SAXSource(xmlReader, inputSource);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		dblpdata.add((DBLPElement) jaxbUnmarshaller.unmarshal(source));
	}

	private static void addDblpDataInTextFormat(DBLPElement dblpElement) throws IOException {
		List<Article> articles = (List<Article>)dblpElement.getArticleList();
		if(articles!=null)
		{
			System.out.println("article : "+articles.size());
			for(Article article: articles) {
				Publication publication = new Publication(article);
				appendPublicationDataToFile(publication);
			}
		}

		List<Book> books = (List<Book>)dblpElement.getBookList();
		if(books!=null)
		{
			System.out.println("book : "+books.size());
			for(Book book: books) {
				Publication publication = new Publication(book);
				appendPublicationDataToFile(publication);
			}
		}

		List<Incollection> incollections = (List<Incollection>)dblpElement.getIncollectionList();
		if(incollections!=null)
		{
			System.out.println("incollection : "+incollections.size());
			for(Incollection incollection: incollections) {
				Publication publication = new Publication(incollection);
				appendPublicationDataToFile(publication);
			}
		}

		List<Inproceedings> inproceedings = (List<Inproceedings>)dblpElement.getInproceedingsList();
		if(inproceedings!=null)
		{
			System.out.println("inproceeding : "+inproceedings.size());
			for(Inproceedings inproceeding: inproceedings) {
				Publication publication = new Publication(inproceeding);
				appendPublicationDataToFile(publication);
			}
		}

		List<Mastersthesis> masterstheses = (List<Mastersthesis>)dblpElement.getMastersthesisList();
		if(masterstheses!=null)
		{
			System.out.println("masterthesis : "+masterstheses.size());
			for(Mastersthesis mastersthesis: masterstheses) {
				Publication publication = new Publication(mastersthesis);
				appendPublicationDataToFile(publication);		
			}
		}

		List<Phdthesis> phdtheses = (List<Phdthesis>)dblpElement.getPhdthesisList();
		if(phdtheses!=null)
		{
			System.out.println("phdthesis : "+phdtheses.size());
			for(Phdthesis phdthesis: phdtheses) {
				Publication publication = new Publication(phdthesis);
				appendPublicationDataToFile(publication);
			}
		}

		List<Proceedings> proceedings = (List<Proceedings>)dblpElement.getProceedingsList();
		if(proceedings!=null)
		{
			System.out.println("proceedings : "+proceedings.size());
			for(Proceedings proceeding: proceedings) {
				Publication publication = new Publication(proceeding);
				appendPublicationDataToFile(publication);
			}
		}

		List<Www> websites = (List<Www>)dblpElement.getWwwList();
		if(websites!=null)
		{
			System.out.println("www : "+websites.size());
			for(Www www: websites) {
				Publication publication = new Publication(www);
				appendPublicationDataToFile(publication);
			}
		}	
	}

	private static void appendPublicationDataToFile(Publication publication) throws IOException {
		String fileName = "dblpdata.txt";
		
		String lineToBeWritten = publication.getTitle() + "|" +
				publication.getType() + "|" +
				publication.getCitationcount() + "|" +
				convertListToString(publication.getAuthornames()) + "|" +
				convertListToString(publication.getEditornames()) + "|" +
				publication.getYear() + "|" +
				publication.getBooktitle() + "|" +
				publication.getAddress() + "|" +
				publication.getPages() + "|" +
				publication.getJournal() + "|" +
				publication.getMonth() + "|" +
				publication.getVolume() + "|" +
				publication.getNumber() + "|" +
				publication.getUrl() + "|" +
				publication.getEe() + "|" +
				publication.getCdrom() + "|" +
				convertListToString(publication.getCite()) + "|" +
				publication.getPublisher() + "|" +
				publication.getNote() + "|" +
				convertListToString(publication.getCrossref()) + "|" +
				publication.getIsbn() + "|" +
				publication.getSeries() + "|" +
				publication.getSchool() + "|" +
				publication.getChapter() + "|" +
				publication.getMdate() + "|" +
				publication.getKey() + "|" +
				publication.getReviewid() + "|" +
				publication.getKey();
		
		List<String> authors = publication.getAuthornames();
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
	    String title = publication.getTitle();
		if((authors!=null) && (authors.size()>0)) {
			for(String author: authors) {
				out.println(author + "_" + title + "|" + lineToBeWritten);
			}
		}
		else {
			out.println("" + "|" + lineToBeWritten);
		}
		
		
		out.close();
	}

	private static String convertListToString(List<String> stringList) {
		String convertedString = "";
		
		if((stringList!=null) && (stringList.size() > 0)) {
			for(String string : stringList)
				convertedString = convertedString + string + ',';
			
			convertedString = convertedString.substring(0, convertedString.length() - 1);
		}
		return convertedString;
	}

	public static void main(String args[]) throws JAXBException, SAXException, ParserConfigurationException, IOException {
		for(int i = 81; i<=100 ; i++) {
			int mb = 1024*1024;

			//Getting the runtime reference from system
			Runtime runtime = Runtime.getRuntime();

			//System.out.println("##### Heap utilization statistics [MB] #####");

			//Print used memory
			System.out.println("Used Memory:"
					+ (runtime.totalMemory() - runtime.freeMemory()) / mb);

			//Print free memory
			System.out.println("Free Memory:"
					+ runtime.freeMemory() / mb);

			//Print total available memory
			System.out.println("Total Memory:" + runtime.totalMemory() / mb);

			//Print Maximum available memory
			System.out.println("Max Memory:" + runtime.maxMemory() / mb);

			addDblpDataElement("xmlfiles/split_dblp_"+i+".xml");
			addDblpDataInTextFormat(dblpdata.get(dblpdata.size()-1));
			System.out.println(i);
		}
		System.out.println("Aasdf");
	}

}
