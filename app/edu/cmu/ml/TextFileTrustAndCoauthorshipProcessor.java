package edu.cmu.ml;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import edu.cmu.database.DbOperations;
import edu.cmu.trustprocessor.TimeScale;
import edu.cmu.trustprocessor.TrustModelWeights;

public class TextFileTrustAndCoauthorshipProcessor {
	private String fileName;
	private TextFilePublicationParser publications;
	private TimeScale timescale;
	private static DbOperations db;
	
	private TrustModelWeights trustModelWeights;
	private double alphaArticle;
	private double alphaBook;
	private double alphaInCollection;
	private double alphaInProceeding;
	private double alphaMasterThesis;
	private double alphaPhdThesis;
	private double alphaProceeding;
	private double alphaWWW;
	
	public TextFileTrustAndCoauthorshipProcessor(String fileName) throws IOException, ClassNotFoundException, SQLException {
		super();
		this.fileName = fileName;
		this.publications = new TextFilePublicationParser(fileName);
		trustModelWeights = new TrustModelWeights();
		this.timescale = new TimeScale();
		this.db = new DbOperations();
		
		alphaArticle =trustModelWeights.alphaArticle;
		alphaBook = trustModelWeights.alphaBook;
		alphaInCollection = trustModelWeights.alphaInCollection;
		alphaInProceeding = trustModelWeights.alphaInProceeding;
		alphaMasterThesis=trustModelWeights.alphaMasterThesis;
		alphaPhdThesis = trustModelWeights.alphaPhdThesis;
		alphaProceeding = trustModelWeights.alphaProceeding;
		alphaWWW= trustModelWeights.alphaWWW;
	}
	
	
	public TextFilePublicationParser getPublications() {
		return publications;
	}


	public void setPublications(TextFilePublicationParser publications) {
		this.publications = publications;
	}


	/**
	 * Function to be called in order to calculate the coauthor distance before a 
	 * particular year
	 * @param authorName1
	 * @param authorName2
	 * @param year
	 * @return returns 1,2 or 999 if answer is greater than 2
	 * @throws SQLException 
	 */
	public int getCoauthorDistanceBeforeYear (String authorName1, String authorName2, int year) throws SQLException {
		ResultSet result = db.callDatabaseQuery("SELECT DISTINCT Coauthor FROM coauthor.Coauthors WHERE Author = '" + authorName1 + "' AND Year<" + year + ";");
		ArrayList<String> coauthors = new ArrayList<String>();
		while(result.next()) {
			coauthors.add(result.getString("Coauthor"));
			if(result.getString("Coauthor").equalsIgnoreCase(authorName2))
				return 1;
		}
		
		for(String authorName: coauthors) {
			result = db.callDatabaseQuery("SELECT DISTINCT Coauthor FROM coauthor.Coauthors WHERE Author = '" + authorName + "' AND Year<" + year + ";");
			while(result.next()) {
				if(result.getString("Coauthor").equalsIgnoreCase(authorName2))
					return 2;
			}
		}
		
		Random r = new Random(); 
		int choice = r.nextInt(3)+1;
		return choice;	
	}
	
	/**
	 * Function to be called in order to calculate the trust value for 
	 * an author before a particular year
	 * @param name
	 * @param year
	 * @return
	 * @throws SQLException 
	 */
	public Double calculateTrustBeforeYear(String name, int year) throws SQLException {
		Double publicationTrust = 0.0;
		Double coauthorshipTrust = 0.0;
		
		publicationTrust = calculatePublicationTrust(name, year);
		coauthorshipTrust = calculateCoauthorshipTrust(name, year);
		
		return publicationTrust + coauthorshipTrust;
	}

	private Double calculateCoauthorshipTrust(String authorName, int year) throws SQLException {
		double coauthorshipTrust = 0.0;
		
		int numberOfRecentCoauthorships = getNumberOfCoauthorsInTimeRange(authorName, year - timescale.getRecentYears(), year);
		int numberOfIntermediateCoauthorships = getNumberOfCoauthorsInTimeRange(authorName, year - timescale.getIntermediateYears(), timescale.getRecentYears());
		int numberOfOldCoauthorships = getNumberOfCoauthorsInTimeRange(authorName, 1800 , year - timescale.getIntermediateYears());
		
		coauthorshipTrust = timescale.gettRecent() * numberOfRecentCoauthorships
						  + timescale.gettIntermediate() * numberOfIntermediateCoauthorships
						  + timescale.gettOld() * numberOfOldCoauthorships;
		return coauthorshipTrust;
	}

	private Double calculatePublicationTrust(String authorName, int year) throws SQLException {
		Double publicationTrust = 0.0;
		
		int numOfArticleRecent = getCountPublicationsBetweenYears(authorName, "article", year - timescale.getRecentYears(), year);
		int numOfArticleIntermediate = getCountPublicationsBetweenYears(authorName, "article", year - timescale.getIntermediateYears(), timescale.getRecentYears());
		int numOfArticleOld = getCountPublicationsBetweenYears(authorName, "article", 1800 , year - timescale.getIntermediateYears());
		
		int numOfBooksRecent = getCountPublicationsBetweenYears(authorName, "book", year - timescale.getRecentYears(), year);
		int numOfBooksIntermediate = getCountPublicationsBetweenYears(authorName, "book", year - timescale.getIntermediateYears(), timescale.getRecentYears());
		int numOfBooksOld = getCountPublicationsBetweenYears(authorName, "book", 1800 , year - timescale.getIntermediateYears());
		
		int numOfIncollectionRecent = getCountPublicationsBetweenYears(authorName, "incollections", year - timescale.getRecentYears(), year);
		int numOfIncollectionIntermediate = getCountPublicationsBetweenYears(authorName, "incollections", year - timescale.getIntermediateYears(), timescale.getRecentYears());
		int numOfIncollectionOld = getCountPublicationsBetweenYears(authorName, "incollections", 1800 , year - timescale.getIntermediateYears());
		
		int numOfInProceedingRecent = getCountPublicationsBetweenYears(authorName, "inproceedings", year - timescale.getRecentYears(), year);
		int numOfInProceedingIntermediate = getCountPublicationsBetweenYears(authorName, "inproceedings", year - timescale.getIntermediateYears(), timescale.getRecentYears());
		int numOfInProceedingOld = getCountPublicationsBetweenYears(authorName, "inproceedings", 1800 , year - timescale.getIntermediateYears());
		
		int numOfMasterThesisRecent = getCountPublicationsBetweenYears(authorName, "mastersthesis", year - timescale.getRecentYears(), year);
		int numOfMasterThesisIntermediate = getCountPublicationsBetweenYears(authorName, "mastersthesis", year - timescale.getIntermediateYears(), timescale.getRecentYears());
		int numOfMasterThesisOld = getCountPublicationsBetweenYears(authorName, "mastersthesis", 1800 , year - timescale.getIntermediateYears());
		
		int numOfPhdThesisRecent = getCountPublicationsBetweenYears(authorName, "phdthesis", year - timescale.getRecentYears(), year);
		int numOfPhdThesisIntermediate = getCountPublicationsBetweenYears(authorName, "phdthesis", year - timescale.getIntermediateYears(), timescale.getRecentYears());
		int numOfPhdThesisOld = getCountPublicationsBetweenYears(authorName, "phdthesis", 1800 , year - timescale.getIntermediateYears());
		
		int numOfProceedingRecent = getCountPublicationsBetweenYears(authorName, "proceedings", year - timescale.getRecentYears(), year);
		int numOfProceedingIntermediate = getCountPublicationsBetweenYears(authorName, "proceedings", year - timescale.getIntermediateYears(), timescale.getRecentYears());
		int numOfProceedingOld = getCountPublicationsBetweenYears(authorName, "proceedings", 1800 , year - timescale.getIntermediateYears());
		
		int numOfWWWRecent = getCountPublicationsBetweenYears(authorName, "www", year - timescale.getRecentYears(), year);
		int numOfWWWIntermediate = getCountPublicationsBetweenYears(authorName, "www", year - timescale.getIntermediateYears(), timescale.getRecentYears());
		int numOfWWWOld = getCountPublicationsBetweenYears(authorName, "www", 1800 , year - timescale.getIntermediateYears());
		
		int numberOfCitationsA = getTotalCitationsOfAuthorByType(authorName, "article", year);
		int numberOfCitationsB = getTotalCitationsOfAuthorByType(authorName, "book", year);
		int numberOfCitationsIC = getTotalCitationsOfAuthorByType(authorName, "incollections", year);
		int numberOfCitationsIP = getTotalCitationsOfAuthorByType(authorName, "inproceedings", year);
		int numberOfCitationsM = getTotalCitationsOfAuthorByType(authorName, "mastersthesis", year);
		int numberOfCitationsPH = getTotalCitationsOfAuthorByType(authorName, "phdthesis", year);
		int numberOfCitationsP = getTotalCitationsOfAuthorByType(authorName, "proceedings", year);
		int numberOfCitationsW = getTotalCitationsOfAuthorByType(authorName, "www", year);
		
		publicationTrust = alphaArticle * (timescale.gettRecent() * numOfArticleRecent
										 + timescale.gettIntermediate() * numOfArticleIntermediate
										 + timescale.gettOld() * numOfArticleOld
										 + numberOfCitationsA)
						+  alphaBook * (timescale.gettRecent() * numOfBooksRecent
								 		 + timescale.gettIntermediate() * numOfBooksIntermediate
								 		 + timescale.gettOld() * numOfBooksOld
								 		 + numberOfCitationsB)
						+  alphaInCollection * (timescale.gettRecent() * numOfIncollectionRecent
								 		 + timescale.gettIntermediate() * numOfIncollectionIntermediate
								 		 + timescale.gettOld() * numOfIncollectionOld
								 		 + numberOfCitationsIC)
						+  alphaInProceeding * (timescale.gettRecent() * numOfInProceedingRecent
								 		 + timescale.gettIntermediate() * numOfInProceedingIntermediate
								 		 + timescale.gettOld() * numOfInProceedingOld
								 		 + numberOfCitationsIP)
						+  alphaPhdThesis * (timescale.gettRecent() * numOfPhdThesisRecent
								 		 + timescale.gettIntermediate() * numOfPhdThesisIntermediate
								 		 + timescale.gettOld() * numOfPhdThesisOld
								 		 + numberOfCitationsPH)
						+  alphaMasterThesis * (timescale.gettRecent() * numOfMasterThesisRecent
								 		 + timescale.gettIntermediate() * numOfMasterThesisIntermediate
								 		 + timescale.gettOld() * numOfMasterThesisOld
								 		 + numberOfCitationsM)
						+  alphaProceeding * (timescale.gettRecent() * numOfProceedingRecent
								 		 + timescale.gettIntermediate() * numOfProceedingIntermediate
								 		 + timescale.gettOld() * numOfProceedingOld
								 		 + numberOfCitationsP)
						+  alphaWWW * (timescale.gettRecent() * numOfWWWRecent
								 		 + timescale.gettIntermediate() * numOfWWWIntermediate
								 		 + timescale.gettOld() * numOfWWWOld
								 		 + numberOfCitationsW);
		
		return publicationTrust;
	}

	private int getTotalCitationsOfAuthorByType(String authorName, String type, int year) throws SQLException {
		int totalCitations = 0;
		ArrayList<String> citations = new ArrayList<String>();
		ResultSet result1 = db.callDatabaseQuery("SELECT PublicationKey FROM coauthor.Publications WHERE Author_Title LIKE '" + authorName + "%' AND Year<" + year + " AND Type = '" + type + "';");
		ResultSet result2;
		while(result1.next()) {
			citations.add(result1.getString("PublicationKey"));
		}
		
		for(String key : citations) {
			result2 = db.callDatabaseQuery("SELECT COUNT(Author_Title) FROM coauthor.Publications WHERE Citations LIKE '%" + key + "%' AND Year<" + year + ";");
			while(result2.next())
				totalCitations+=result2.getInt("COUNT(Author_Title)");
		};
		
		return totalCitations;
	}
	
	public double calculateJaccardSimilarity(String authorName1, String authorName2, int year) throws SQLException{		
		HashSet<String> h1 = new HashSet<String>();
		HashSet<String> h2 = new HashSet<String>();
		
		ResultSet result1 = db.callDatabaseQuery("SELECT Title,Journal FROM coauthor.Publications WHERE Author_Title LIKE '"+ authorName1 + "%' AND Year<" + year + ";");
		while(result1.next()) {
			h1.add(result1.getString("Title"));
			h1.add(result1.getString("Journal"));
		}
		
		ResultSet result2 = db.callDatabaseQuery("SELECT Title,Journal FROM coauthor.Publications WHERE Author_Title LIKE '"+ authorName2 + "%' AND Year<" + year + ";");
		while(result2.next()) {
			h2.add(result2.getString("Title"));
			h2.add(result2.getString("Journal"));
		}
		
		double res = 0;		
		
		if(h1.size()>0 && h2.size() >0) {
			int sizeh1 = h1.size();
			//Retains all elements in h3 that are contained in h2 ie intersection
			h1.retainAll(h2);
			//h1 now contains the intersection of h1 and h2		
			h2.removeAll(h1);
			//h2 now contains unique elements
			System.out.println("Same elements "+ h1);	
			//Union 
			int union = sizeh1 + h2.size();
			int intersection = h1.size();			
			res = (double)intersection/union;
			System.out.println(res);
			return res;
		}
		
		return 0.0;
	}
	
	private int getCountPublicationsBetweenYears(String authorName, String type, int startingYear, int endingYear) throws SQLException {
		ResultSet result = db.callDatabaseQuery("SELECT COUNT(Author_Title) FROM coauthor.Publications WHERE Author_Title LIKE '" + authorName + "%' AND Year>" + startingYear + " AND Year<" + endingYear +  " AND Type = '" + type + "';");
		while(result.next())
			return result.getInt("COUNT(Author_Title)");
		return -100;
	}
	
	public int getNumberOfCoauthorsInTimeRange(String authorName, int startingYear, int endingYear) throws SQLException {
		ResultSet result = db.callDatabaseQuery("SELECT COUNT(DISTINCT Coauthor) FROM coauthor.Coauthors WHERE Author = '" + authorName + "' AND Year >=" + startingYear + " AND Year<=" + endingYear + ";");
		while(result.next())
			return result.getInt("COUNT(DISTINCT Coauthor)");
		return -100;
	}
	
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
//		TextFileTrustAndCoauthorshipProcessor blah = new TextFileTrustAndCoauthorshipProcessor("ds.txt");
//		ArrayList<Double> trust = new ArrayList<Double>();
//		ArrayList<Double> differences = new ArrayList<Double>();
//		ResultSet result = db.callDatabaseQuery("SELECT DISTINCT Author FROM coauthor.Coauthors ;");
//		ArrayList<String> blahasdf = new ArrayList<String>();
//		while(result.next())
//			blahasdf.add(result.getString("Author"));
//		
//		System.out.println(blahasdf.size());
//		for(String author: blahasdf){
//			//System.out.println(blah.calculateTrustBeforeYear(author, 2012));
//			trust.add(blah.calculateTrustBeforeYear(author, 2012));
//		}
//		
//		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("/Users/ShuaiWang/Desktop/Variance.txt", true)));
//		int trustsize = trust.size();
//		for(int i = 0; i<trustsize-1; i++){
//			for(int j = i+1; j<trustsize; j++){
//				writer.print(Math.abs(trust.get(i) - trust.get(j)) + "\n");
//				//System.out.println(Math.abs(trust.get(i) - trust.get(j)));
//			}
//		}		
	}
}
