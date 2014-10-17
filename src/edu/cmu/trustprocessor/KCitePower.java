package edu.cmu.trustprocessor;

public class KCitePower {

	private double Cw=TrustModelWeights.Cw;
	private double numberOfCitationsA;
	private double numberOfCitationsB;
	private double numberOfCitationsIC;
	private double numberOfCitationsIP;
	private double numberOfCitationsM;
	private double numberOfCitationsPH;
	private double numberOfCitationsP;
	private double numberOfCitationsW;
	
	public double getCw() {
		return Cw;
	}
	public void setCw(double cw) {
		Cw = cw;
	}
	public double getNumberOfCitationsW() {
		return numberOfCitationsW;
	}
	public void setNumberOfCitationsW(double numberOfCitationsW) {
		this.numberOfCitationsW = numberOfCitationsW;
	}
	public double getNumberOfCitationsA() {
		return numberOfCitationsA;
	}
	public void setNumberOfCitationsA(double numberOfCitationsA) {
		this.numberOfCitationsA = numberOfCitationsA;
	}
	
	public double getNumberOfCitationsB() {
		return numberOfCitationsB;
	}
	public void setNumberOfCitationsB(double numberOfCitationsB) {
		this.numberOfCitationsB = numberOfCitationsB;
	}
	
	public double getNumberOfCitationsIC() {
		return numberOfCitationsIC;
	}
	public void setNumberOfCitationsIC(double numberOfCitationsIC) {
		this.numberOfCitationsIC = numberOfCitationsIC;
	}
	
	public double getNumberOfCitationsIP() {
		return numberOfCitationsIP;
	}
	public void setNumberOfCitationsIP(double numberOfCitationsIP) {
		this.numberOfCitationsIP = numberOfCitationsIP;
	}
	
	public double getNumberOfCitationsM() {
		return numberOfCitationsM;
	}
	public void setNumberOfCitationsM(double numberOfCitationsM) {
		this.numberOfCitationsM = numberOfCitationsM;
	}
	
	public double getNumberOfCitationsPH() {
		return numberOfCitationsPH;
	}
	public void setNumberOfCitationsPH(double numberOfCitationsPH) {
		this.numberOfCitationsPH = numberOfCitationsPH;
	}
	
	public double getNumberOfCitationsP() {
		return numberOfCitationsP;
	}
	public void setNumberOfCitationsP(double numberOfCitationsP) {
		this.numberOfCitationsP = numberOfCitationsP;
	}		
}
