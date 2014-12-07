package trustprocessor;

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
		Pw = Double.parseDouble("0.3");
		Cw = Double.parseDouble("0.5");
		alphaArticle = Double.parseDouble("0.075");
		alphaInCollection = Double.parseDouble("0.15");
		alphaInProceeding = Double.parseDouble("0.175");
		alphaMasterThesis = Double.parseDouble("0.1");
		alphaPhdThesis = Double.parseDouble("0.125");
		alphaProceeding = Double.parseDouble("0.2");
		alphaWWW = Double.parseDouble("0.05");
		alphaBook = Double.parseDouble("0.125");
		
		sigmaRetweet = Double.parseDouble("0.2");
		
		recentYears = Integer.parseInt("6");
		intermediateYears = Integer.parseInt("6");
		tRecent = Double.parseDouble("0.5");
		tIntermediate = Double.parseDouble("0.3");
		tOld = Double.parseDouble("0.2");	
	}

}
