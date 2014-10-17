package edu.cmu.trustprocessor;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TrustModelWeights {
	
	public static double Pw;
	public static double Cw;
		
		
	public static double alphaArticle;
	public static double alphaBook;
	public static double alphaInCollection;
	public static double alphaInProceeding;
	public static double alphaMasterThesis;
	public static double alphaPhdThesis;
	public static double alphaProceeding;
	public static double alphaWWW;

	public static double sigmaRetweet;

	public static int recentYears;				
	public static int intermediateYears;
		
	public static double tRecent;
	public static double tIntermediate;
	public static double tOld;
	
	public TrustModelWeights(){
		Properties properties = new Properties();
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("trustmodel.properties");
		System.out.println(inputStream);
		try {
			properties.load(inputStream);
			Pw = Double.parseDouble(properties.get("Pw").toString().trim());
			Cw = Double.parseDouble(properties.get("Cw").toString().trim());
			alphaArticle = Double.parseDouble(properties.get("alphaArticle").toString().trim());
			alphaInCollection = Double.parseDouble(properties.get("alphaInCollection").toString().trim());
			alphaInProceeding = Double.parseDouble(properties.get("alphaInProceeding").toString().trim());
			alphaMasterThesis = Double.parseDouble(properties.get("alphaMasterThesis").toString().trim());
			alphaPhdThesis = Double.parseDouble(properties.get("alphaPhdThesis").toString().trim());
			alphaProceeding = Double.parseDouble(properties.get("alphaProceeding").toString().trim());
			alphaWWW = Double.parseDouble(properties.get("alphaWWW").toString().trim());
			alphaBook = Double.parseDouble(properties.get("alphaBook").toString().trim());
			
			sigmaRetweet = Double.parseDouble(properties.get("sigmaRetweet").toString().trim());
			
			recentYears = Integer.parseInt(properties.get("recentYears").toString().trim().trim());
			intermediateYears = Integer.parseInt(properties.get("intermediateYears").toString().trim());
			tRecent = Double.parseDouble(properties.get("tRecent").toString().trim());
			tIntermediate = Double.parseDouble(properties.get("tIntermediate").toString().trim());
			tOld = Double.parseDouble(properties.get("tOld").toString().trim());			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
