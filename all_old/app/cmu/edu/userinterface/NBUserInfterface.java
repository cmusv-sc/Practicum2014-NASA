package cmu.edu.userinterface;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Scanner;

import edu.cmu.ml.NaiveBayesFileCreator;

public class NBUserInfterface {
	public static void main(String args[]) throws IOException, ClassNotFoundException, SQLException{
		Scanner input = new Scanner(System.in);
		String author1;
		String author2;
		System.out.println("Enter author's name- ");
		author1 = input.nextLine();
		System.out.println("Enter another author name- ");
		author2 = input.nextLine();		
		NaiveBayesFileCreator blah = new NaiveBayesFileCreator("ds.txt");
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("TwoAuthor.txt", false)));
		blah.writeLineForDemo(writer, author1, author2, 2013);
		Runtime r = Runtime.getRuntime();
		Process p = r.exec("python TwoUNBayes.py TrA.txt TwoAuthor.txt Result.txt Feature-NameandValues.txt");
		System.out.println("Done!!!");
	}
}
