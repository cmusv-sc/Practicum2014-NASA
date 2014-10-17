package edu.cmu.ml;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class NaiveBayesFileCreator {
	
	private TextFileTrustAndCoauthorshipProcessor trustModel;
	private String inputFileName;
	private TextFilePublicationParser fileParser;
	private String outputFileName;
	
	public NaiveBayesFileCreator(String inputFileName) throws IOException, ClassNotFoundException, SQLException {
		super();
		this.inputFileName = inputFileName;
		this.trustModel = new TextFileTrustAndCoauthorshipProcessor(inputFileName);
		this.fileParser = trustModel.getPublications();
	}

	public NaiveBayesFileCreator(String inputFileName, String outputFileName) throws IOException, ClassNotFoundException, SQLException {
		super();
		this.inputFileName = inputFileName;
		this.outputFileName = outputFileName;
		this.trustModel = new TextFileTrustAndCoauthorshipProcessor(inputFileName);
		this.fileParser = trustModel.getPublications();
		createNaiveBayesFile(this.outputFileName);
	}
	
	private void createNaiveBayesFile(String outputFileName) throws IOException, SQLException, ClassNotFoundException {
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(outputFileName, true)));
		String authors;
		String[] authorNames;
		int size = 0, year;
		
		for(HashMap<String, String> publication : TextFilePublicationParser.articleList) {
			authors = publication.get("authors");
			authorNames = authors.split(",");
			size = authorNames.length;
			year = Integer.parseInt(publication.get("year"));
			
			for(int i = 0; i<size; i++)
				for(int j = i+1; j<size; j++) {
					if(! authorNames[i].equalsIgnoreCase(authorNames[j]))
						writeLineForAuthors(writer, authorNames[i], authorNames[j], year);
				}			
		}
		
		for(HashMap<String, String> publication : TextFilePublicationParser.inproceedingsList) {
			authors = publication.get("authors");
			authorNames = authors.split(",");
			size = authorNames.length;
			year = Integer.parseInt(publication.get("year"));
			
			for(int i = 0; i<size; i++)
				for(int j = i+1; j<size; j++) {
					if(! authorNames[i].equalsIgnoreCase(authorNames[j]))
						writeLineForAuthors(writer, authorNames[i], authorNames[j], year);
				}			
		}
		
		for(HashMap<String, String> publication : TextFilePublicationParser.incollectionsList) {
			authors = publication.get("authors");
			authorNames = authors.split(",");
			size = authorNames.length;
			year = Integer.parseInt(publication.get("year"));
			
			for(int i = 0; i<size; i++)
				for(int j = i+1; j<size; j++) {
					if(! authorNames[i].equalsIgnoreCase(authorNames[j]))
						writeLineForAuthors(writer, authorNames[i], authorNames[j], year);
				}			
		}
		
		for(HashMap<String, String> publication : TextFilePublicationParser.phdThesisList) {
			authors = publication.get("authors");
			authorNames = authors.split(",");
			size = authorNames.length;
			year = Integer.parseInt(publication.get("year"));
			
			for(int i = 0; i<size; i++)
				for(int j = i+1; j<size; j++) {
					if(! authorNames[i].equalsIgnoreCase(authorNames[j]))
						writeLineForAuthors(writer, authorNames[i], authorNames[j], year);
				}			
		}
		
		for(HashMap<String, String> publication : TextFilePublicationParser.mastersThesisList) {
			authors = publication.get("authors");
			authorNames = authors.split(",");
			size = authorNames.length;
			year = Integer.parseInt(publication.get("year"));
			
			for(int i = 0; i<size; i++)
				for(int j = i+1; j<size; j++) {
					if(! authorNames[i].equalsIgnoreCase(authorNames[j]))
						writeLineForAuthors(writer, authorNames[i], authorNames[j], year);
				}			
		}
		
		for(HashMap<String, String> publication : TextFilePublicationParser.wwwList) {
			authors = publication.get("authors");
			authorNames = authors.split(",");
			size = authorNames.length;
			year = Integer.parseInt(publication.get("year"));
			
			for(int i = 0; i<size; i++)
				for(int j = i+1; j<size; j++) {
					if(! authorNames[i].equalsIgnoreCase(authorNames[j]))
						writeLineForAuthors(writer, authorNames[i], authorNames[j], year);
				}			
		}
		
		for(HashMap<String, String> publication : TextFilePublicationParser.proceedingsList) {
			authors = publication.get("authors");
			authorNames = authors.split(",");
			size = authorNames.length;
			year = Integer.parseInt(publication.get("year"));
			
			for(int i = 0; i<size; i++)
				for(int j = i+1; j<size; j++) {
					if(! authorNames[i].equalsIgnoreCase(authorNames[j]))
						writeLineForAuthors(writer, authorNames[i], authorNames[j], year);
				}			
		}
		
		for(HashMap<String, String> publication : TextFilePublicationParser.articleList) {
			authors = publication.get("authors");
			authorNames = authors.split(",");
			size = authorNames.length;
			year = Integer.parseInt(publication.get("year"));
			
			for(int i = 0; i<size; i++)
				for(int j = i+1; j<size; j++) {
					if(! authorNames[i].equalsIgnoreCase(authorNames[j]))
						writeLineForAuthors(writer, authorNames[i], authorNames[j], year);
				}			
		}
		
		writer.close();
	}
	
	private void writeLineForAuthors(PrintWriter writer, String authorName1, String authorName2, int year) throws SQLException, ClassNotFoundException {
		String lineToBeWritten ="";
		
		int coauthorDistance = trustModel.getCoauthorDistanceBeforeYear(authorName1, authorName2, year);
				
		Double trustValueDifference = trustModel.calculateTrustBeforeYear(authorName1, year) - trustModel.calculateTrustBeforeYear(authorName2, year);		
		Double jaccardSimilarity = trustModel.calculateJaccardSimilarity(authorName1, authorName2, year);
		
		int coauthorshipHistory1 = trustModel.getNumberOfCoauthorsInTimeRange(authorName1, 1800, year);
		int coauthorshipHistory2 = trustModel.getNumberOfCoauthorsInTimeRange(authorName2, 1800, year);
		
		int effectiveCoauthorship = ((coauthorshipHistory1 - coauthorshipHistory2) < 0)?coauthorshipHistory1 : coauthorshipHistory2;
		
		if(coauthorDistance == 1){
			lineToBeWritten += "Y\t";
		}else{
			lineToBeWritten += "N\t";
		}
		
		if(jaccardSimilarity <= 0) 
			lineToBeWritten += "H\t";
		else if(jaccardSimilarity <= 0)
			lineToBeWritten += "M\t";
		else 
			lineToBeWritten += "L\t";
			
		if(coauthorDistance <=1)
			lineToBeWritten+="H\t";
		else if(coauthorDistance  <=2 && coauthorDistance >1)
			lineToBeWritten+="M\t";
		else 
			lineToBeWritten += "L\t";
		
		if(trustValueDifference <0)
			lineToBeWritten+="H\t";
		else if(trustValueDifference <=1.0 && trustValueDifference >0)
			lineToBeWritten += "M\t";
		else
			lineToBeWritten += "L\t";
		
		if(effectiveCoauthorship > 5)
			lineToBeWritten+="H";
		else if(effectiveCoauthorship > 1 && effectiveCoauthorship <= 5 )
			lineToBeWritten+="M";
		else 
			lineToBeWritten += "L";
		
		writer.println(lineToBeWritten);
	}
	
	public void writeLineForDemo(PrintWriter writer, String authorName1, String authorName2, int year) throws SQLException, ClassNotFoundException {
		String lineToBeWritten = "";
		
		int coauthorDistance = trustModel.getCoauthorDistanceBeforeYear(authorName1, authorName2, year);
		
		Double trustValueDifference = Math.abs(trustModel.calculateTrustBeforeYear(authorName1, year) - trustModel.calculateTrustBeforeYear(authorName2, year));		
		Double jaccardSimilarity = trustModel.calculateJaccardSimilarity(authorName1, authorName2, year);
		
		int coauthorshipHistory1 = trustModel.getNumberOfCoauthorsInTimeRange(authorName1, 1800, year);
		int coauthorshipHistory2 = trustModel.getNumberOfCoauthorsInTimeRange(authorName2, 1800, year);
		
		int effectiveCoauthorship = ((coauthorshipHistory1 - coauthorshipHistory2) < 0)?coauthorshipHistory1 : coauthorshipHistory2;

		if(jaccardSimilarity <= 0)
			lineToBeWritten += "H\t";
		else if(jaccardSimilarity <= 0.5 && jaccardSimilarity > 0)
			lineToBeWritten += "M\t";
		else 
			lineToBeWritten += "L\t";
			
		if(coauthorDistance <=1)
			lineToBeWritten+="H\t";
		else if(coauthorDistance  <=2 && coauthorDistance >1)
			lineToBeWritten+="M\t";
		else 
			lineToBeWritten += "L\t";
		
		if(trustValueDifference <0.1)
			lineToBeWritten+="H\t";
		else if(trustValueDifference <=0.7 && trustValueDifference >=0.1)
			lineToBeWritten += "M\t";
		else
			lineToBeWritten += "L\t";
		
		if(effectiveCoauthorship > 5)
			lineToBeWritten+="H";
		else if(effectiveCoauthorship > 1 && effectiveCoauthorship <= 5 )
			lineToBeWritten+="M";
		else 
			lineToBeWritten += "L";		
		writer.println(lineToBeWritten);
		writer.flush();
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		NaiveBayesFileCreator blah = new NaiveBayesFileCreator("ds.txt","Blan.txt");
	}
}
