package edu.cmu.ml;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class TextFilePublicationParser {

	public static ArrayList<HashMap<String, String>> articleList = new ArrayList<HashMap<String,String>>();
	public static ArrayList<HashMap<String, String>> bookList = new ArrayList<HashMap<String,String>>();
	public static ArrayList<HashMap<String, String>> phdThesisList = new ArrayList<HashMap<String,String>>();
	public static ArrayList<HashMap<String, String>> mastersThesisList = new ArrayList<HashMap<String,String>>();
	public static ArrayList<HashMap<String, String>> wwwList = new ArrayList<HashMap<String,String>>();
	public static ArrayList<HashMap<String, String>> incollectionsList = new ArrayList<HashMap<String,String>>();
	public static ArrayList<HashMap<String, String>> inproceedingsList = new ArrayList<HashMap<String,String>>();
	public static ArrayList<HashMap<String, String>> proceedingsList = new ArrayList<HashMap<String,String>>();
	private String fileName = null;
	
	public TextFilePublicationParser(String fileName) throws IOException {
		super();
		this.fileName = fileName;
		parseFileAndFillInformation(fileName);
	}

	private void parseFileAndFillInformation(String fileName) throws IOException {
		InputStream inputStream = new FileInputStream(fileName);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		String line;
		String[] fields;
		
		while((line = bufferedReader.readLine()) !=null) {
			fields = line.split("\\|");
			String type = fields[2];
			
			HashMap<String, String> publication = new HashMap<String, String>();
			publication.put("primary_key", fields[0]);
			publication.put("title", fields[1]);
			publication.put("type", fields[2]);
			publication.put("citation_count", fields[3]);
			publication.put("authors", fields[4]);
			publication.put("editors", fields[5]);
			publication.put("year", fields[6]);
			publication.put("book_title", fields[7]);
			publication.put("address", fields[8]);
			publication.put("pages", fields[9]);
			publication.put("journal", fields[10]);
			publication.put("month", fields[11]);
			publication.put("volume", fields[12]);
			publication.put("number", fields[13]);
			publication.put("url", fields[14]);
			publication.put("ee", fields[15]);
			publication.put("cdrom", fields[16]);
			publication.put("citations", fields[17]);
			publication.put("publisher", fields[18]);
			publication.put("note", fields[19]);
			publication.put("crossref", fields[20]);
			publication.put("isbn", fields[21]);
			publication.put("series", fields[22]);
			publication.put("school", fields[23]);
			publication.put("chapter", fields[24]);
			publication.put("mdate", fields[25]);
			publication.put("key", fields[26]);
			publication.put("review_id", fields[27]);
			
			if(type.equals("article")) {
				articleList.add(publication);
			}
			else if(type.contains("book")) {
				bookList.add(publication);
			}
			else if(type.contains("incollection")) {
				incollectionsList.add(publication);		
			}
			else if(type.contains("master")) {
				mastersThesisList.add(publication);
			}
			else if(type.contains("phd")) {
				phdThesisList.add(publication);
			}
			else if(type.contains("inproceed")) {
				inproceedingsList.add(publication);
			}
			else if(type.equals("proceedings")) {
				proceedingsList.add(publication);
			}
			else if(type.equals("www")) {
				wwwList.add(publication);
			}
		}
		
		bufferedReader.close();
	}
	
	public static void main(String[] args) throws IOException {
		TextFilePublicationParser p = new TextFilePublicationParser("cloud.txt");
		System.out.println("sf");
	}
	
}
