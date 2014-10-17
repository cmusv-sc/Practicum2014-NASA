package edu.cmu.trustprocessor;

public class KPaperPublished {
	
	
	private double finalKPaperPublished;
	
	private KCitePower kCitePower;
	
	private double Pw=TrustModelWeights.Pw;
		
	public KCitePower getkCitePower() {
		return kCitePower;
	}
	public void setkCitePower(KCitePower kCitePower) {
		this.kCitePower = kCitePower;
	}
	
	public double getFinalKPaperPublished() {
		return finalKPaperPublished;
	}
	public void setFinalKPaperPublished(double finalKPaperPublished) {
		this.finalKPaperPublished = finalKPaperPublished;
	}
	
	
	public double getPw() {
		return Pw;
	}
	public void setPw(double pw) {
		Pw = pw;
	}


	private double alphaArticle =TrustModelWeights.alphaArticle;
	private double alphaBook = TrustModelWeights.alphaBook;
	private double alphaInCollection = TrustModelWeights.alphaInCollection;
	private double alphaInProceeding = TrustModelWeights.alphaInProceeding;
	private double alphaMasterThesis=TrustModelWeights.alphaMasterThesis;
	private double alphaPhdThesis = TrustModelWeights.alphaPhdThesis;
	private double alphaProceeding = TrustModelWeights.alphaProceeding;
	private double alphaWWW=TrustModelWeights.alphaWWW;
	
	private int numOfArticleRecentNOX = 0;
	private int numOfArticleIntermediateNXY = 0;
	private int numOfArticleOldNYZ = 0;
	
	private int numOfBooksRecentNOX = 0;
	private int numOfBooksIntermediateNXY = 0;
	private int numOfBooksOldNYZ = 0;
	
	private int numOfIncollectionRecentNOX = 0;
	private int numOfIncollectionIntermediateNXY = 0;
	private int numOfIncollectionOldNYZ = 0;
	
	private int numOfInProceedingRecentNOX = 0;
	private int numOfInProceedingIntermediateNXY = 0;
	private int numOfInProceedingOldNYZ = 0;
	
	private int numOfMasterThesisRecentNOX = 0;
	private int numOfMasterThesisIntermediateNXY = 0;
	private int numOfMasterThesisOldNYZ = 0;
	
	private int numOfPhdThesisRecentNOX = 0;
	private int numOfPhdThesisIntermediateNXY = 0;
	private int numOfPhdThesisOldNYZ = 0;
	
	public int getNumOfPhdThesisRecentNOX() {
		return numOfPhdThesisRecentNOX;
	}
	public void setNumOfPhdThesisRecentNOX(int numOfPhdThesisRecentNOX) {
		this.numOfPhdThesisRecentNOX = numOfPhdThesisRecentNOX;
	}
	public int getNumOfPhdThesisIntermediateNXY() {
		return numOfPhdThesisIntermediateNXY;
	}
	public void setNumOfPhdThesisIntermediateNXY(int numOfPhdThesisIntermediateNXY) {
		this.numOfPhdThesisIntermediateNXY = numOfPhdThesisIntermediateNXY;
	}
	public int getNumOfPhdThesisOldNYZ() {
		return numOfPhdThesisOldNYZ;
	}
	public void setNumOfPhdThesisOldNYZ(int numOfPhdThesisOldNYZ) {
		this.numOfPhdThesisOldNYZ = numOfPhdThesisOldNYZ;
	}
	private int numOfProceedingRecentNOX = 0;
	private int numOfProceedingIntermediateNXY = 0;
	private int numOfProceedingOldNYZ = 0;
	
	private int numOfWWWRecentNOX = 0;
	private int numOfWWWIntermediateNXY = 0;
	private int numOfWWWOldNYZ = 0;
	public int getNumOfArticleRecentNOX() {
		return numOfArticleRecentNOX;
	}
	public void setNumOfArticleRecentNOX(int numOfArticleRecentNOX) {
		this.numOfArticleRecentNOX = numOfArticleRecentNOX;
	}
	public int getNumOfArticleIntermediateNXY() {
		return numOfArticleIntermediateNXY;
	}
	public void setNumOfArticleIntermediateNXY(int numOfArticleIntermediateNXY) {
		this.numOfArticleIntermediateNXY = numOfArticleIntermediateNXY;
	}
	public int getNumOfArticleOldNYZ() {
		return numOfArticleOldNYZ;
	}
	public void setNumOfArticleOldNYZ(int numOfArticleOldNYZ) {
		this.numOfArticleOldNYZ = numOfArticleOldNYZ;
	}
	public int getNumOfBooksRecentNOX() {
		return numOfBooksRecentNOX;
	}
	public void setNumOfBooksRecentNOX(int numOfBooksRecentNOX) {
		this.numOfBooksRecentNOX = numOfBooksRecentNOX;
	}
	public int getNumOfBooksIntermediateNXY() {
		return numOfBooksIntermediateNXY;
	}
	public void setNumOfBooksIntermediateNXY(int numOfBooksIntermediateNXY) {
		this.numOfBooksIntermediateNXY = numOfBooksIntermediateNXY;
	}
	public int getNumOfBooksOldNYZ() {
		return numOfBooksOldNYZ;
	}
	public void setNumOfBooksOldNYZ(int numOfBooksOldNYZ) {
		this.numOfBooksOldNYZ = numOfBooksOldNYZ;
	}
	public int getNumOfIncollectionRecentNOX() {
		return numOfIncollectionRecentNOX;
	}
	public void setNumOfIncollectionRecentNOX(int numOfIncollectionRecentNOX) {
		this.numOfIncollectionRecentNOX = numOfIncollectionRecentNOX;
	}
	public int getNumOfIncollectionIntermediateNXY() {
		return numOfIncollectionIntermediateNXY;
	}
	public void setNumOfIncollectionIntermediateNXY(
			int numOfIncollectionIntermediateNXY) {
		this.numOfIncollectionIntermediateNXY = numOfIncollectionIntermediateNXY;
	}
	public int getNumOfIncollectionOldNYZ() {
		return numOfIncollectionOldNYZ;
	}
	public void setNumOfIncollectionOldNYZ(int numOfIncollectionOldNYZ) {
		this.numOfIncollectionOldNYZ = numOfIncollectionOldNYZ;
	}
	public int getNumOfInProceedingRecentNOX() {
		return numOfInProceedingRecentNOX;
	}
	public void setNumOfInProceedingRecentNOX(int numOfInProceedingRecentNOX) {
		this.numOfInProceedingRecentNOX = numOfInProceedingRecentNOX;
	}
	public int getNumOfInProceedingIntermediateNXY() {
		return numOfInProceedingIntermediateNXY;
	}
	public void setNumOfInProceedingIntermediateNXY(
			int numOfInProceedingIntermediateNXY) {
		this.numOfInProceedingIntermediateNXY = numOfInProceedingIntermediateNXY;
	}
	public int getNumOfInProceedingOldNYZ() {
		return numOfInProceedingOldNYZ;
	}
	public void setNumOfInProceedingOldNYZ(int numOfInProceedingOldNYZ) {
		this.numOfInProceedingOldNYZ = numOfInProceedingOldNYZ;
	}
	public int getNumOfMasterThesisRecentNOX() {
		return numOfMasterThesisRecentNOX;
	}
	public void setNumOfMasterThesisRecentNOX(int numOfMasterThesisRecentNOX) {
		this.numOfMasterThesisRecentNOX = numOfMasterThesisRecentNOX;
	}
	public int getNumOfMasterThesisIntermediateNXY() {
		return numOfMasterThesisIntermediateNXY;
	}
	public void setNumOfMasterThesisIntermediateNXY(
			int numOfMasterThesisIntermediateNXY) {
		this.numOfMasterThesisIntermediateNXY = numOfMasterThesisIntermediateNXY;
	}
	public int getNumOfMasterThesisOldNYZ() {
		return numOfMasterThesisOldNYZ;
	}
	public void setNumOfMasterThesisOldNYZ(int numOfMasterThesisOldNYZ) {
		this.numOfMasterThesisOldNYZ = numOfMasterThesisOldNYZ;
	}
	public int getNumOfProceedingRecentNOX() {
		return numOfProceedingRecentNOX;
	}
	public void setNumOfProceedingRecentNOX(int numOfProceedingRecentNOX) {
		this.numOfProceedingRecentNOX = numOfProceedingRecentNOX;
	}
	public int getNumOfProceedingIntermediateNXY() {
		return numOfProceedingIntermediateNXY;
	}
	public void setNumOfProceedingIntermediateNXY(int numOfProceedingIntermediateNXY) {
		this.numOfProceedingIntermediateNXY = numOfProceedingIntermediateNXY;
	}
	public int getNumOfProceedingOldNYZ() {
		return numOfProceedingOldNYZ;
	}
	public void setNumOfProceedingOldNYZ(int numOfProceedingOldNYZ) {
		this.numOfProceedingOldNYZ = numOfProceedingOldNYZ;
	}
	public int getNumOfWWWRecentNOX() {
		return numOfWWWRecentNOX;
	}
	public void setNumOfWWWRecentNOX(int numOfWWWRecentNOX) {
		this.numOfWWWRecentNOX = numOfWWWRecentNOX;
	}
	public int getNumOfWWWIntermediateNXY() {
		return numOfWWWIntermediateNXY;
	}
	public void setNumOfWWWIntermediateNXY(int numOfWWWIntermediateNXY) {
		this.numOfWWWIntermediateNXY = numOfWWWIntermediateNXY;
	}
	public int getNumOfWWWOldNYZ() {
		return numOfWWWOldNYZ;
	}
	public void setNumOfWWWOldNYZ(int numOfWWWOldNYZ) {
		this.numOfWWWOldNYZ = numOfWWWOldNYZ;
	}
	

	public double getAlphaArticle() {
		return alphaArticle;
	}

	public void setAlphaArticle(double alphaArticle) {
		this.alphaArticle = alphaArticle;
	}

	public double getAlphaBook() {
		return alphaBook;
	}

	public void setAlphaBook(double alphaBook) {
		this.alphaBook = alphaBook;
	}

	public double getAlphaInCollection() {
		return alphaInCollection;
	}

	public void setAlphaInCollection(double alphaInCollection) {
		this.alphaInCollection = alphaInCollection;
	}

	public double getAlphaInProceeding() {
		return alphaInProceeding;
	}

	public void setAlphaInProceeding(double alphaInProceeding) {
		this.alphaInProceeding = alphaInProceeding;
	}

	public double getAlphaMasterThesis() {
		return alphaMasterThesis;
	}

	public void setAlphaMasterThesis(double alphaMasterThesis) {
		this.alphaMasterThesis = alphaMasterThesis;
	}

	public double getAlphaPhdThesis() {
		return alphaPhdThesis;
	}

	public void setAlphaPhdThesis(double alphaPhdThesis) {
		this.alphaPhdThesis = alphaPhdThesis;
	}

	public double getAlphaProceeding() {
		return alphaProceeding;
	}

	public void setAlphaProceeding(double alphaProceeding) {
		this.alphaProceeding = alphaProceeding;
	}

	public double getAlphaWWW() {
		return alphaWWW;
	}

	public void setAlphaWWW(double alphaWWW) {
		this.alphaWWW = alphaWWW;
	}

}
