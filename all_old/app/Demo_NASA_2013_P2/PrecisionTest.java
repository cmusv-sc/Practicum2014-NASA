package Demo_NASA_2013_P2;

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
		//Test the precision of algorithm based on data
		public static void main(String args[]) throws IOException{			
			Runtime r = Runtime.getRuntime();
			Process p = r.exec("python TestNBayes.py Train.txt Test_Precision.txt precision_result.txt Feature.txt");
			System.out.println("The precision report is generated in precision_result.txt.");
		}	
}
