package edu.cmu.DBLPProcessor;
//
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import edu.cmu.dataset.*;
//
public class WriteXML {
//
//	/**
//	 * @author NASA-Trust-Team
//	 * @throws JAXBException 
//	 */
	public static void main(String[] args) throws IOException, JAXBException, SAXException, ParserConfigurationException {
		DatasetInterface dblpDataset = new DBLPDataSource();
		HashMap<String,DBLPUser> dblp = dblpDataset.getDataset("dblp_example.xml");
		Iterator<String> it = dblp.keySet().iterator();
		while (it.hasNext())
		{
			String key = it.next();
			DBLPUser author = dblp.get(key);
			//writeAuthor(author);
			//if(author.getId()==116)
				//author.getCoauthorship();
			for(Coauthorship c: author.getCoAuthors()) {
				System.out.print("\nUser ID :" + c.getUserid() + "	CoauthorID: " + c.getCoauthorid());
			}
			
			//System.out.println(author);
		}
				
	}
}
//	
//	public static void writeAuthor(DBLPUser author) throws IOException, JAXBException {
//		FileWriter fileWriter = new FileWriter("DBLP_XML/user"+author.getId()+".xml");
//		PrintWriter printWriter = new PrintWriter(fileWriter);
//		printWriter.println("<user>");
//		printWriter.println("<id>"+author.getId()+"</id>");
//		printWriter.println("<name>"+author.getName()+"</name>");
//		List<Article> article = author.getArticle();
//		if(article.size()!=0)
//		{
//			printWriter.println("<articlelist>");
//			for(int i=0; i<article.size(); i++)
//			{
//				printWriter.println("<articleid>"+article.get(i).getId()+"</articleid>");
//			}
//			printWriter.println("</articlelist>");
//		}
//		List<Book> book = author.getBook();
//		if(book.size()!=0)
//		{
//			printWriter.println("<booklist>");
//			for(int i=0; i<book.size(); i++)
//			{
//				printWriter.println("<bookid>"+book.get(i).getId()+"</bookid>");
//			}
//			printWriter.println("</booklist>");
//		}
//		List<Incollection> incollection = author.getIncollection();
//		if(incollection.size()!=0)
//		{
//			printWriter.println("<incollectionlist>");
//			for(int i=0; i<incollection.size(); i++)
//			{
//				printWriter.println("<incollectionid>"+incollection.get(i).getId()+"</incollectionid>");
//			}
//			printWriter.println("</incollectionlist>");
//		}
//		List<Inproceedings> inproceedings = author.getInproceedings();
//		if(inproceedings.size()!=0)
//		{
//			printWriter.println("<inproceedingslist>");
//			for(int i=0; i<inproceedings.size(); i++)
//			{
//				printWriter.println("<inproceedingsid>"+inproceedings.get(i).getId()+"</inproceedingsid>");
//			}
//			printWriter.println("</inproceedingslist>");
//		}
//		List<Mastersthesis> mastersthesis = author.getMastersthesis();
//		if(mastersthesis.size()!=0)
//		{
//			printWriter.println("<mastersthesislist>");
//			for(int i=0; i<mastersthesis.size(); i++)
//			{
//				printWriter.println("<mastersthesisid>"+mastersthesis.get(i).getId()+"</mastersthesisid>");
//			}
//			printWriter.println("</mastersthesislist>");
//		}
//		List<Phdthesis> phdthesis = author.getPhdthesis();
//		if(phdthesis.size()!=0)
//		{
//			printWriter.println("<phdthesislist>");
//			for(int i=0; i<phdthesis.size(); i++)
//			{
//				printWriter.println("<phdthesisid>"+phdthesis.get(i).getId()+"</phdthesisid>");
//			}
//			printWriter.println("</phdthesislist>");
//		}
//		List<Proceedings> proceedings = author.getProceedings();
//		if(proceedings.size()!=0)
//		{
//			printWriter.println("<proceedingslist>");
//			for(int i=0; i<proceedings.size(); i++)
//			{
//				printWriter.println("<proceedingsid>"+proceedings.get(i).getId()+"</proceedingsid>");
//			}
//			printWriter.println("</proceedingslist>");
//		}
//		List<Www> www = author.getWww();
//		if(www.size()!=0)
//		{
//			printWriter.println("<wwwlist>");
//			for(int i=0; i<www.size(); i++)
//			{
//				printWriter.println("<wwwid>"+www.get(i).getId()+"</wwwid>");
//			}
//			printWriter.println("</wwwlist>");
//		}
//		List<Integer> coauthorship = author.getCoauthorship();
//		if(coauthorship.size()!=0)
//		{
//			printWriter.println("<coauthorshiplist>");
//			for(int i=0; i<coauthorship.size(); i++)
//			{
//				printWriter.println("<coauthorshipid>"+coauthorship.get(i)+"</coauthorshipid>");
//			}
//			printWriter.println("</coauthorshhiplist>");
//		}
//		printWriter.println("</user>");
//		printWriter.flush();
//		printWriter.close();
//		fileWriter.close();
//	}
//}
