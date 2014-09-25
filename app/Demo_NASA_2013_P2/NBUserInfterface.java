package Demo_NASA_2013_P2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Scanner;

import edu.cmu.ml.NaiveBayesFileCreator;

public class NBUserInfterface {
	// This is the function takes auhtor names as inputs and show the collaboration probability
	public static void main(String args[]) throws IOException, ClassNotFoundException, SQLException{
		Scanner input = new Scanner(System.in);
		String author1;
		String author2;
		System.out.println("\nPlease enter first author's name- ");
		author1 = input.nextLine();
		System.out.println("\nPlease enter second author's name- ");
		author2 = input.nextLine();		
		NaiveBayesFileCreator nbfc = new NaiveBayesFileCreator("ds.txt");
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("TwoAuthor.txt", false)));
		nbfc.writeLineForDemo(writer, author1, author2, 2013);
		Runtime r = Runtime.getRuntime();
		Process p = r.exec("python TwoUNBayes.py Training.txt TwoAuthor.txt Result.txt Feature.txt");
		System.out.println("The report is generated in Result.txt file.");
	}
}
