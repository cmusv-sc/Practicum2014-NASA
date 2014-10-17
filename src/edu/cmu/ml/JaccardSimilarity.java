package edu.cmu.ml;
import java.io.BufferedReader;

import edu.cmu.database.*;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.Map.Entry;

import edu.cmu.database.DbOperations;

/*
 * Jaccard Similarity is a similarity function which is calculated by 
 * first tokenizing the strings into sets and then taking the ratio of
 * (weighted) intersection to their union 
 */

public class JaccardSimilarity {
	private static DbOperations db;
	private static BufferedReader reader;
	private String author1;
	private String author2;
	private int year;
	
	public JaccardSimilarity(String author1, String author2, int year) throws ClassNotFoundException, SQLException {
		super();
		this.author1 = author1;
		this.author2 = author2;
		this.year = year;
		this.db = new DbOperations();	
		//calculateSimilarity(author1, author2, year);
	}

	public double calculateSimilarity(String author1, String author2, int year) throws SQLException{		
		HashSet<String> h1 = new HashSet<String>();
		HashSet<String> h2 = new HashSet<String>();
		
		ResultSet result = db.callDatabaseQuery("SELECT Title,Journal FROM coauthor.Publications WHERE Author_Title LIKE '"+ author1 + "%' AND Year<" + year + ";");
		while(result.next()) {
			h1.add(result.getString("Title"));
			h1.add(result.getString("Journal"));
		}
		
		result = db.callDatabaseQuery("SELECT Title,Journal FROM coauthor.Publications WHERE Author_Title LIKE '"+ author2 + "%' AND Year<" + year + ";");
		while(result.next()) {
			h2.add(result.getString("Title"));
			h2.add(result.getString("Journal"));
		}
		
		double res = 0;		
		int sizeh1 = h1.size();
		//Retains all elements in h3 that are contained in h2 ie intersection
		h1.retainAll(h2);
		//h1 now contains the intersection of h1 and h2		
		h2.removeAll(h1);
		//h2 now contains unique elements
		//Union 
		int union = sizeh1 + h2.size();
		int intersection = h1.size();	
		res = (double)intersection/union;
		//System.out.println(res);
		return res;
	}
}