package edu.cmu.dblp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import edu.cmu.dblp.constants.Publication;
import edu.cmu.dblp.database.DBInserts;
import edu.cmu.dblp.model.Book;
import edu.cmu.dblp.model.BookChapter;
import edu.cmu.dblp.model.BookChapterData;
import edu.cmu.dblp.model.Conference;
import edu.cmu.dblp.model.ConferencePaper;
import edu.cmu.dblp.model.Journal;
import edu.cmu.dblp.model.JournalArticle;
import edu.cmu.dblp.model.PhdThesis;
import edu.cmu.dblp.model.School;
import edu.cmu.dblp.model.WebPage;



public class Main {

	private static final Logger logger = Logger.getLogger( Main.class.getName() );

	private static String dblpExample = "/Users/ironstone/Downloads/dblp/dblp.xml";


	public static void main(String[] args) {
		FileHandler fh;
		try {
			fh = new FileHandler("logs/etl_xml.log");
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();  
			fh.setFormatter(formatter);
			logger.setUseParentHandlers(false);
		} catch (SecurityException e) {  
			e.printStackTrace();  
		} catch (IOException e) {  
			e.printStackTrace();  
		}
		logger.info("Loading DBLP XML has started!!!!");
		if(args.length > 0){
			if(args[0].equals("server")){
				readXML(args[1]);
			}
			else if(args[0].equals("local")){
				readXML(dblpExample);
			}
		}
		else{
			readXML(dblpExample);
		}
	}

	public static void printCounter(int count, Calendar start, Calendar end){
		int level = 10000;
		if(count % level == 0){
			System.out.println("Inserted Entries so far: " + String.valueOf(count));
			logger.info("Inserted Entries so far: " + String.valueOf(count));
			long timeMilis = end.getTimeInMillis() -  start.getTimeInMillis();
//			System.out.println("Insertions per second: " + String.valueOf(count / ((timeMilis) / 1000)));
//			logger.info("Insertions per second: " + String.valueOf(count / ((timeMilis) / 1000)));
			System.out.println("Elapsed time so far: " + String.valueOf((timeMilis / (1000 * 60))) + " min " + String.valueOf((timeMilis / 1000) % 60) + " sec");
			logger.info("Elapsed time so far: " + String.valueOf((timeMilis / (1000 * 60))) + " min " + String.valueOf((timeMilis / 1000) % 60) + " sec");
		}
	}

	@SuppressWarnings("unchecked")
	public static void readXML(String configFile) {
		Calendar startTime = Calendar.getInstance();
		try {
			// First, create a new XMLInputFactory
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			// Setup a new eventReader
			InputStream in = new FileInputStream(configFile);
			XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
			// read the XML document

			ArrayList<String> unidentifiedElements = new ArrayList<String>();
			int counter = 0;
			// In case there is a problem while iterating the file, set the flag and debug
			int flag = 0;

			while (eventReader.hasNext()) {

				// For Debugging, check which entity has problem via console output
				if(flag == 0 && counter == 10000){
					flag = 1;
					break;
				}

				XMLEvent event = eventReader.nextEvent();

				if (event.isStartElement()) {
					StartElement startElement = event.asStartElement();
					// If we have an item element, we create a new item
					if (startElement.getName().getLocalPart() == (Publication.ARTICLE)) {
						JournalArticle article = new JournalArticle();
						// Create journal object for journal tag of the article
						Journal journal = new Journal();
						// Relevance is 0 by default. This will be cross referenced later and calculated
						journal.setRelevance(0);

						// Read the attributes of the element
						Iterator<Attribute> attributes = startElement.getAttributes();
						while (attributes.hasNext()) {
							Attribute attribute = attributes.next();
							if (attribute.getName().toString().equals(Publication.MDATE)) {
								continue;
							}
							if (attribute.getName().toString().equals(Publication.KEY)) {
								article.setKeywords(attribute.getValue());
							}
						}
						while(eventReader.hasNext()){
							XMLEvent articleEvent = eventReader.nextEvent();
							if (articleEvent.isStartElement()) {
								StartElement articleStartElement = articleEvent.asStartElement();
								if (articleStartElement.getName().getLocalPart() == (Publication.AUTHOR)) {
									articleEvent = eventReader.nextEvent();
									article.getAuthorNames().add(articleEvent.asCharacters().getData());
									continue;
								}
								else if(articleStartElement.getName().getLocalPart() == (Publication.TITLE)){
									String title = "";
									while(eventReader.hasNext()){
										articleEvent = eventReader.nextEvent();
										if (articleEvent.isStartElement()) {
											StartElement italicStartElement = articleEvent.asStartElement();
											if (italicStartElement.getName().getLocalPart() == "i" || 
													italicStartElement.getName().getLocalPart() == "sup") {
												continue;
											}
										}
										else if(articleEvent.isEndElement()){
											EndElement italicEndElement = articleEvent.asEndElement();
											if (italicEndElement.getName().getLocalPart() == "i" || 
													italicEndElement.getName().getLocalPart() == "sup") {
												continue;
											}
											else if(italicEndElement.getName().getLocalPart() == (Publication.TITLE)){
												article.setPublicationTitle(title);
												break;
											}

										}
										else{
											title += articleEvent.asCharacters().getData();
										}
									}
									continue;
								}
								else if(articleStartElement.getName().getLocalPart() == (Publication.YEAR)){
									articleEvent = eventReader.nextEvent();
									article.setYear(articleEvent.asCharacters().getData());
									continue;
								}
								else if(articleStartElement.getName().getLocalPart() == (edu.cmu.dblp.constants.JournalArticle.MONTH)){
									articleEvent = eventReader.nextEvent();
									article.setMonth(articleEvent.asCharacters().getData());
									continue;
								}
								else if(articleStartElement.getName().getLocalPart() == (edu.cmu.dblp.constants.JournalArticle.VOLUME)){
									articleEvent = eventReader.nextEvent();
									article.setVolume(articleEvent.asCharacters().getData());
									continue;
								}
								else if(articleStartElement.getName().getLocalPart() == (edu.cmu.dblp.constants.JournalArticle.JOURNAL)){
									articleEvent = eventReader.nextEvent();
									journal.setJournalName(articleEvent.asCharacters().getData());
									continue;
								}
								else{
									if(!unidentifiedElements.contains("ARTICLE " + articleStartElement.getName().getLocalPart())){
										unidentifiedElements.add("ARTICLE " + articleStartElement.getName().getLocalPart());
									}
								}
							}
							if(articleEvent.isEndElement()){
								EndElement articleEndElement = articleEvent.asEndElement();
								if(articleEndElement.getName().getLocalPart().equals(Publication.ARTICLE)){
									// Save the instance to DB
									article.setType("article");
									DBInserts.getInstance().DBInserts(article, journal);
									counter++;
									printCounter(counter, startTime, Calendar.getInstance());
									break;
								}
							}
						}
					}
					if (startElement.getName().getLocalPart() == (Publication.BOOK)) {
						Book book = new Book();

						// Read the attributes of the element
						Iterator<Attribute> attributes = startElement.getAttributes();
						while (attributes.hasNext()) {
							Attribute attribute = attributes.next();
							if (attribute.getName().toString().equals(Publication.MDATE)) {
								continue;
							}
							if (attribute.getName().toString().equals(Publication.KEY)) {
								book.setKeywords(attribute.getValue());
							}
						}
						while(eventReader.hasNext()){
							XMLEvent bookEvent = eventReader.nextEvent();
							if (bookEvent.isStartElement()) {
								StartElement bookStartElement = bookEvent.asStartElement();
								if (bookStartElement.getName().getLocalPart() == (Publication.AUTHOR)) {
									bookEvent = eventReader.nextEvent();
									book.getAuthorNames().add(bookEvent.asCharacters().getData());
									continue;
								}
								else if(bookStartElement.getName().getLocalPart() == (Publication.TITLE)){
									String title = "";
									while(eventReader.hasNext()){
										bookEvent = eventReader.nextEvent();
										if (bookEvent.isStartElement()) {
											StartElement italicStartElement = bookEvent.asStartElement();
											if (italicStartElement.getName().getLocalPart() == "i" || 
													italicStartElement.getName().getLocalPart() == "sup") {
												continue;
											}
										}
										else if(bookEvent.isEndElement()){
											EndElement italicEndElement = bookEvent.asEndElement();
											if (italicEndElement.getName().getLocalPart() == "i" || 
													italicEndElement.getName().getLocalPart() == "sup") {
												continue;
											}
											else if(italicEndElement.getName().getLocalPart() == (Publication.TITLE)){
												book.setPublicationTitle(title);
												break;
											}

										}
										else{
											title += bookEvent.asCharacters().getData();
										}
									}
									continue;
								}
								else if(bookStartElement.getName().getLocalPart() == (Publication.YEAR)){
									bookEvent = eventReader.nextEvent();
									book.setYear(bookEvent.asCharacters().getData());
									continue;
								}
								else if(bookStartElement.getName().getLocalPart() == (edu.cmu.dblp.constants.Book.PUBLISHER)){
									bookEvent = eventReader.nextEvent();
									book.setPublisher(bookEvent.asCharacters().getData());
									continue;
								}
								else if(bookStartElement.getName().getLocalPart() == (edu.cmu.dblp.constants.Book.ISBN)){
									bookEvent = eventReader.nextEvent();
									book.setIsbn(bookEvent.asCharacters().getData());
									continue;
								}
								else if(bookStartElement.getName().getLocalPart() == (edu.cmu.dblp.constants.Book.URL)){
									bookEvent = eventReader.nextEvent();
									book.setUrl(bookEvent.asCharacters().getData());
									continue;
								}
								else{
									if(!unidentifiedElements.contains("BOOK " + bookStartElement.getName().getLocalPart())){
										unidentifiedElements.add("BOOK " + bookStartElement.getName().getLocalPart());
									}
								}
							}
							if(bookEvent.isEndElement()){
								EndElement bookEndElement = bookEvent.asEndElement();
								if(bookEndElement.getName().getLocalPart().equals(Publication.BOOK)){
									// Save the instance to DB
									book.setType("book");
									DBInserts.getInstance().DBInserts(book, null);
									counter++;
									printCounter(counter, startTime, Calendar.getInstance());
									break;
								}
							}
						}
					}
					if (startElement.getName().getLocalPart() == (Publication.INCOLLECTION)) {
						BookChapter bookChapter = new BookChapter();
						// Create BookChapterData object for booktitle tag of the bookchapter
						BookChapterData bookChapterData = new BookChapterData();
						// Relevance is 0 by default. This will be cross referenced later and calculated
						bookChapterData.setRelevance(0);

						// Read the attributes of the element
						Iterator<Attribute> attributes = startElement.getAttributes();
						while (attributes.hasNext()) {
							Attribute attribute = attributes.next();
							if (attribute.getName().toString().equals(Publication.MDATE)) {
								continue;
							}
							if (attribute.getName().toString().equals(Publication.KEY)) {
								bookChapter.setKeywords(attribute.getValue());
							}
						}
						while(eventReader.hasNext()){
							XMLEvent bookChapterEvent = eventReader.nextEvent();
							if (bookChapterEvent.isStartElement()) {
								StartElement bookChapterStartElement = bookChapterEvent.asStartElement();
								if (bookChapterStartElement.getName().getLocalPart() == (Publication.AUTHOR)) {
									bookChapterEvent = eventReader.nextEvent();
									bookChapter.getAuthorNames().add(bookChapterEvent.asCharacters().getData());
									continue;
								}
								else if(bookChapterStartElement.getName().getLocalPart() == (Publication.TITLE)){
									String title = "";
									while(eventReader.hasNext()){
										bookChapterEvent = eventReader.nextEvent();
										if (bookChapterEvent.isStartElement()) {
											StartElement italicStartElement = bookChapterEvent.asStartElement();
											if (italicStartElement.getName().getLocalPart() == "i" || 
													italicStartElement.getName().getLocalPart() == "sup") {
												continue;
											}
										}
										else if(bookChapterEvent.isEndElement()){
											EndElement italicEndElement = bookChapterEvent.asEndElement();
											if (italicEndElement.getName().getLocalPart() == "i" || 
													italicEndElement.getName().getLocalPart() == "sup") {
												continue;
											}
											else if(italicEndElement.getName().getLocalPart() == (Publication.TITLE)){
												bookChapter.setPublicationTitle(title);
												break;
											}

										}
										else{
											title += bookChapterEvent.asCharacters().getData();
										}

									}
									continue;
								}
								else if(bookChapterStartElement.getName().getLocalPart() == (Publication.YEAR)){
									bookChapterEvent = eventReader.nextEvent();
									bookChapter.setYear(bookChapterEvent.asCharacters().getData());
									continue;
								}
								else if(bookChapterStartElement.getName().getLocalPart() == (edu.cmu.dblp.constants.BookChapter.PAGES)){
									bookChapterEvent = eventReader.nextEvent();
									bookChapter.setPages(bookChapterEvent.asCharacters().getData());
									continue;
								}
								else if(bookChapterStartElement.getName().getLocalPart() == (edu.cmu.dblp.constants.BookChapter.BOOKTITLE)){
									bookChapterEvent = eventReader.nextEvent();
									bookChapterData.setBookChapterName(bookChapterEvent.asCharacters().getData());
									continue;
								}
								else{
									if(!unidentifiedElements.contains("INCOLLECTION " + bookChapterStartElement.getName().getLocalPart())){
										unidentifiedElements.add("INCOLLECTION " + bookChapterStartElement.getName().getLocalPart());
									}
								}
							}
							if(bookChapterEvent.isEndElement()){
								EndElement bookChapterEndElement = bookChapterEvent.asEndElement();
								if(bookChapterEndElement.getName().getLocalPart().equals(Publication.INCOLLECTION)){
									// Save the instance to DB
									bookChapter.setType("bookchapter");
									DBInserts.getInstance().DBInserts(bookChapter, bookChapterData);
									counter++;
									printCounter(counter, startTime, Calendar.getInstance());
									break;
								}
							}
						}
					}
					if (startElement.getName().getLocalPart() == (Publication.INPROCEEDINGS)) {
						ConferencePaper conferencePaper = new ConferencePaper();
						// There is no Conference information in Inproceedings. Therefore, it is set to null here.
						// Conference class is for further use.
						Conference conference = new Conference();

						// Read the attributes of the element
						Iterator<Attribute> attributes = startElement.getAttributes();
						while (attributes.hasNext()) {
							Attribute attribute = attributes.next();
							if (attribute.getName().toString().equals(Publication.MDATE)) {
								continue;
							}
							if (attribute.getName().toString().equals(Publication.KEY)) {
								conferencePaper.setKeywords(attribute.getValue());
							}
						}
						while(eventReader.hasNext()){
							XMLEvent conferencePaperEvent = eventReader.nextEvent();
							if (conferencePaperEvent.isStartElement()) {
								StartElement conferencePaperStartElement = conferencePaperEvent.asStartElement();
								if (conferencePaperStartElement.getName().getLocalPart() == (Publication.AUTHOR)) {
									conferencePaperEvent = eventReader.nextEvent();
									conferencePaper.getAuthorNames().add(conferencePaperEvent.asCharacters().getData());
									continue;
								}
								else if(conferencePaperStartElement.getName().getLocalPart() == (Publication.TITLE)){
									String title = "";
									while(eventReader.hasNext()){
										conferencePaperEvent = eventReader.nextEvent();
										if (conferencePaperEvent.isStartElement()) {
											StartElement italicStartElement = conferencePaperEvent.asStartElement();
											if (italicStartElement.getName().getLocalPart() == "i" || 
													italicStartElement.getName().getLocalPart() == "sup") {
												continue;
											}
										}
										else if(conferencePaperEvent.isEndElement()){
											EndElement italicEndElement = conferencePaperEvent.asEndElement();
											if (italicEndElement.getName().getLocalPart() == "i" || 
													italicEndElement.getName().getLocalPart() == "sup") {
												continue;
											}
											else if(italicEndElement.getName().getLocalPart() == (Publication.TITLE)){
												conferencePaper.setPublicationTitle(title);
												break;
											}

										}
										else{
											title += conferencePaperEvent.asCharacters().getData();
										}
									}
									continue;
								}
								else if(conferencePaperStartElement.getName().getLocalPart() == (Publication.YEAR)){
									conferencePaperEvent = eventReader.nextEvent();
									conferencePaper.setYear(conferencePaperEvent.asCharacters().getData());
									continue;
								}
								else if(conferencePaperStartElement.getName().getLocalPart() == (edu.cmu.dblp.constants.BookChapter.PAGES)){
									conferencePaperEvent = eventReader.nextEvent();
									conferencePaper.setPages(conferencePaperEvent.asCharacters().getData());
									continue;
								}
								else{
									if(!unidentifiedElements.contains("INPROCEEDINGS " + conferencePaperStartElement.getName().getLocalPart())){
										unidentifiedElements.add("INPROCEEDINGS " + conferencePaperStartElement.getName().getLocalPart());
									}
								}
							}
							if(conferencePaperEvent.isEndElement()){
								EndElement conferencePaperEndElement = conferencePaperEvent.asEndElement();
								if(conferencePaperEndElement.getName().getLocalPart().equals(Publication.INPROCEEDINGS)){
									// Save the instance to DB
									conferencePaper.setType("conferencepaper");
									DBInserts.getInstance().DBInserts(conferencePaper, conference);
									counter++;
									printCounter(counter, startTime, Calendar.getInstance());
									break;
								}
							}
						}
					}
					if (startElement.getName().getLocalPart() == (Publication.PHDTHESIS)) {
						PhdThesis phdThesis = new PhdThesis();
						School school = new School();

						// Read the attributes of the element
						Iterator<Attribute> attributes = startElement.getAttributes();
						while (attributes.hasNext()) {
							Attribute attribute = attributes.next();
							if (attribute.getName().toString().equals(Publication.MDATE)) {
								continue;
							}
							if (attribute.getName().toString().equals(Publication.KEY)) {
								phdThesis.setKeywords(attribute.getValue());
							}
						}
						while(eventReader.hasNext()){
							XMLEvent phdThesisEvent = eventReader.nextEvent();
							if (phdThesisEvent.isStartElement()) {
								StartElement phdThesisStartElement = phdThesisEvent.asStartElement();
								if (phdThesisStartElement.getName().getLocalPart() == (Publication.AUTHOR)) {
									phdThesisEvent = eventReader.nextEvent();
									phdThesis.getAuthorNames().add(phdThesisEvent.asCharacters().getData());
									continue;
								}
								else if(phdThesisStartElement.getName().getLocalPart() == (Publication.TITLE)){
									String title = "";
									while(eventReader.hasNext()){
										phdThesisEvent = eventReader.nextEvent();
										if (phdThesisEvent.isStartElement()) {
											StartElement italicStartElement = phdThesisEvent.asStartElement();
											if (italicStartElement.getName().getLocalPart() == "i" || 
													italicStartElement.getName().getLocalPart() == "sup") {
												continue;
											}
										}
										else if(phdThesisEvent.isEndElement()){
											EndElement italicEndElement = phdThesisEvent.asEndElement();
											if (italicEndElement.getName().getLocalPart() == "i" || 
													italicEndElement.getName().getLocalPart() == "sup") {
												continue;
											}
											else if(italicEndElement.getName().getLocalPart() == (Publication.TITLE)){
												phdThesis.setPublicationTitle(title);
												break;
											}

										}
										else{
											title += phdThesisEvent.asCharacters().getData();
										}
									}
									continue;
								}
								else if(phdThesisStartElement.getName().getLocalPart() == (Publication.YEAR)){
									phdThesisEvent = eventReader.nextEvent();
									phdThesis.setYear(phdThesisEvent.asCharacters().getData());
									continue;
								}
								else if(phdThesisStartElement.getName().getLocalPart() == (edu.cmu.dblp.constants.PhdThesis.SCHOOL)){									
									phdThesisEvent = eventReader.nextEvent();
									// Assign school name to be used in DAL
									school.setSchoolName(phdThesisEvent.asCharacters().getData());
									continue;
								}
								else{
									if(!unidentifiedElements.contains("PHDTHESIS " + phdThesisStartElement.getName().getLocalPart())){
										unidentifiedElements.add("PHDTHESIS " + phdThesisStartElement.getName().getLocalPart());
									}
								}
							}
							if(phdThesisEvent.isEndElement()){
								EndElement phdThesisEndElement = phdThesisEvent.asEndElement();
								if(phdThesisEndElement.getName().getLocalPart().equals(Publication.PHDTHESIS)){
									// Save the instance to DB
									phdThesis.setType("phdthesis");
									DBInserts.getInstance().DBInserts(phdThesis, school);
									counter++;
									printCounter(counter, startTime, Calendar.getInstance());
									break;
								}
							}
						}
					}
					if (startElement.getName().getLocalPart() == (Publication.WWW)) {
						WebPage webPage = new WebPage();

						// Read the attributes of the element
						Iterator<Attribute> attributes = startElement.getAttributes();
						while (attributes.hasNext()) {
							Attribute attribute = attributes.next();
							if (attribute.getName().toString().equals(Publication.MDATE)) {
								continue;
							}
							if (attribute.getName().toString().equals(Publication.KEY)) {
								webPage.setKeywords(attribute.getValue());
							}
						}
						while(eventReader.hasNext()){
							XMLEvent webPageEvent = eventReader.nextEvent();
							if (webPageEvent.isStartElement()) {
								StartElement webPageStartElement = webPageEvent.asStartElement();
								if (webPageStartElement.getName().getLocalPart() == (Publication.AUTHOR)) {
									webPageEvent = eventReader.nextEvent();
									webPage.getAuthorNames().add(webPageEvent.asCharacters().getData());
									continue;
								}
								else if(webPageStartElement.getName().getLocalPart() == (Publication.TITLE)){
									String title = "";
									while(eventReader.hasNext()){
										webPageEvent = eventReader.nextEvent();
										if (webPageEvent.isStartElement()) {
											StartElement italicStartElement = webPageEvent.asStartElement();
											if (italicStartElement.getName().getLocalPart() == "i" || 
													italicStartElement.getName().getLocalPart() == "sup") {
												continue;
											}
										}
										else if(webPageEvent.isEndElement()){
											EndElement italicEndElement = webPageEvent.asEndElement();
											if (italicEndElement.getName().getLocalPart() == "i" || 
													italicEndElement.getName().getLocalPart() == "sup") {
												continue;
											}
											else if(italicEndElement.getName().getLocalPart() == (Publication.TITLE)){
												webPage.setPublicationTitle(title);
												break;
											}

										}
										else{
											title += webPageEvent.asCharacters().getData();
										}
									}
									continue;
								}
								else if(webPageStartElement.getName().getLocalPart() == (Publication.YEAR)){
									webPageEvent = eventReader.nextEvent();
									webPage.setYear(webPageEvent.asCharacters().getData());
									continue;
								}
								else if(webPageStartElement.getName().getLocalPart() == (edu.cmu.dblp.constants.PhdThesis.SCHOOL)){									
									webPageEvent = eventReader.nextEvent();
									webPage.setUrl(webPageEvent.asCharacters().getData());
									continue;
								}
								else{
									if(!unidentifiedElements.contains("WWW " + webPageStartElement.getName().getLocalPart())){
										unidentifiedElements.add("WWW " + webPageStartElement.getName().getLocalPart());
									}
								}
							}
							if(webPageEvent.isEndElement()){
								EndElement webPageEndElement = webPageEvent.asEndElement();
								if(webPageEndElement.getName().getLocalPart().equals(Publication.WWW)){
									// Save the instance to DB
									webPage.setType("webpage");
									DBInserts.getInstance().DBInserts(webPage, null);
									counter++;
									printCounter(counter, startTime, Calendar.getInstance());
									break;
								}
							}
						}
					}
				}
				if (event.isEndElement()) {
					printCounter(counter, startTime, Calendar.getInstance());
				}

			}
			DBInserts.finalizeProcess();
			System.out.println("WARNING!! There are missing elements in DBLP XML file! These attributes are defined within their corresponding objects. However, they don't exist in xml! Such these are set to null in objects.");
			System.out.println("Total count of missing elements: " + unidentifiedElements.size());
			System.out.println("ENTITY - ATTRIBUTE");
			for (String element : unidentifiedElements) {
				System.out.println(element);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Calendar endTime = Calendar.getInstance();
		long timeElapsed = endTime.getTimeInMillis() - startTime.getTimeInMillis();
		System.out.println("Total elapsed time: " + String.valueOf((timeElapsed / (1000 * 60))) + " min " + String.valueOf((timeElapsed / 1000) % 60) + " sec");
	}
}