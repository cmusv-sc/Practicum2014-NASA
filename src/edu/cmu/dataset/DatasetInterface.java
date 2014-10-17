package edu.cmu.dataset;

import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

/**
 * @author NASA-Trust-Team
 * 
 */
public interface DatasetInterface {
	public HashMap getDataset(String fileName) throws SAXException, ParserConfigurationException;

}
