package edu.cmu.dataset;

import java.io.IOException;
import java.util.HashMap;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import edu.cmu.DBLPProcessor.*;;

public class DBLPDataSource implements DatasetInterface{

	public HashMap<String,DBLPUser> getDataset(String fileName) throws SAXException, ParserConfigurationException
	{
		HashMap<String, DBLPUser> dblp = new HashMap<String,DBLPUser>();
		try {
			dblp = (HashMap<String, DBLPUser>) DBLPParser.parseDBLP(fileName);
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dblp;
	}
}
