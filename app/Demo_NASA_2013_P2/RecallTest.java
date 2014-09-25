package Demo_NASA_2013_P2;

import java.io.IOException;

public class RecallTest {
	//This is the recall test of algorithm
	public static void main(String args[]) throws IOException{
		Runtime r = Runtime.getRuntime();
		Process p = r.exec("python BayesNow.py Train.txt Recall_test.txt Recall_result.txt Feature.txt");
		System.out.println("The recall result is generated in Recall_result.txt.");
	}	
}
