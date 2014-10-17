package cmu.edu.userinterface;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.sun.tools.javac.util.List;

import edu.cmu.database.DbOperations;
import edu.cmu.ml.NaiveBayesFileCreator;

public class PrecisionTest {
	//From scratch
	public static void main(String args[]) throws IOException, ClassNotFoundException, SQLException{
		DbOperations db = new DbOperations();
		BufferedReader br = null;	
		String author1;
		String author2;
		String sCurrentLine;
		String line = null;
		ArrayList<String> s = new ArrayList<String>();
		br = new BufferedReader(new FileReader("Au2010.csv"));
		
		while ((sCurrentLine = br.readLine()) != null) {
			sCurrentLine = sCurrentLine.replaceAll("\"","");
			s.add(sCurrentLine);
			System.out.println(sCurrentLine);
		}
		
		int count = 0;
		for(int i =0; i< s.size()-1;i++){
			for(int j=i+1;j< s.size();j++){
				author1 = s.get(i);
				author2 = s.get(j);		
				ResultSet result = db.callDatabaseQuery("SELECT * FROM coauthor.Coauthors WHERE (Author = '" + author1 +"' and Coauthor = '"+ author2+"') or (Author = '"+author2+"' and Coauthor = '"+author1+"') and Year >2011;");
				while(result.next()){
					count += 1;
				}		
			}
		}
		
		Runtime r = Runtime.getRuntime();
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("TwoPrecison2.txt", false)));
		PrintWriter writer2 = new PrintWriter(new BufferedWriter(new FileWriter("Names2.txt", false)));
		
		for(int i =0; i< s.size()-1;i++){
			for(int j=i+1;j< s.size();j++){
				author1 = s.get(i);
				author2 = s.get(j);		
				NaiveBayesFileCreator blah = new NaiveBayesFileCreator("ds.txt");
				blah.writeLineForDemo(writer, author1, author2, 2013);
				writer2.append(author1+ author2+"\n");
				Process p = r.exec("python TestNBayes.py Train.txt TwoPrecison2.txt precision_result.txt Feature.txt");			
				writer2.flush();
			}
		}	
		//writer.append(count + "");
		writer.close();			
		System.out.println(count);
	}
		//--------------------------
//		public static void main(String args[]) throws IOException{
//			//PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("TwoPrecison.txt", false)));
//			//PrintWriter writer2 = new PrintWriter(new BufferedWriter(new FileWriter("Names.txt", false)));
//			Runtime r = Runtime.getRuntime();
//			Process p = r.exec("python TestNBayes.py Train.txt TwoPrecison2.txt RePrecs2.txt Feature.txt");
//			System.out.println("Done!!!");
//		}	
}
