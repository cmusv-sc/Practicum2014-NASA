package controllers;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;

import edu.cmu.jung.CoAuthorGraph;
import edu.cmu.jung.Edge;
import edu.cmu.jung.UserCoAuthorSubgraph;
import edu.uci.ics.jung.algorithms.layout.Layout;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Stroke;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.JFrame;

import org.apache.commons.collections15.Transformer;
import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.SAXException;

import edu.cmu.DBLPProcessor.Coauthorship;
import edu.cmu.DBLPProcessor.DBLPParser;
import edu.cmu.DBLPProcessor.DBLPUser;
import edu.cmu.DBLPProcessor.Publication;
import edu.cmu.dataset.DBLPDataSource;
import edu.cmu.dataset.DatasetInterface;
import edu.cmu.jung.Node;
import edu.cmu.trustprocessor.DBLPTrustProcessor;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

public class Application extends Controller {

	public static Result index() {
		return ok(index.render(""));
	}

	public static class Show {
		@Required public String name;
		@Required public Integer level;
		public Integer renderGraph;
	}

	public static JSONArray myGraph(String name, Integer level, Integer renderGraph) throws JAXBException, SAXException, ParserConfigurationException {
		UserCoAuthorSubgraph myApp = new UserCoAuthorSubgraph();
		JSONArray result;

		result = myApp.constructGraph(name,level);

		//System.out.println(result);

		// This builds the graph
		if(renderGraph != 0) {
			Layout<Node, Edge> layout = new CircleLayout<Node, Edge>(myApp.g);
			layout.setSize(new Dimension(650,650));
			VisualizationViewer<Node, Edge> vv = new VisualizationViewer<Node, Edge>(layout);
			vv.setPreferredSize(new Dimension(700,700));       

			// Setup up a new vertex to paint transformer...
			Transformer<Node,Paint> vertexPaint = new Transformer<Node,Paint>() {
				public Paint transform(Node e) {
					if(e.getLevel() == 0)
						return Color.GREEN;
					else
						return Color.RED;
				}
			};  

			// Set up a new stroke Transformer for the edges
			float dash[] = {10.0f};
			final Stroke edgeStroke = new BasicStroke(1.0f, BasicStroke.CAP_BUTT,
					BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);
			Transformer<Edge, Stroke> edgeStrokeTransformer = new Transformer<Edge, Stroke>() {
				public Stroke transform(Edge e) {
					return edgeStroke;
				}
			};	

			vv.setVertexToolTipTransformer(new Transformer<Node, String>() {
				public String transform(Node e) {
					return "Name: " + e.getUser().getName() ;
				}
			});

			vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
			//vv.getRenderContext().setEdgeArrowStrokeTransformer(edgeStroke);
			vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);        

			JFrame frame = new JFrame("Co-authorship Graph View");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().add(vv);
			frame.pack();
			frame.setVisible(true);     

		}

		return result;

	}

	public static Result generateDummyJson(String name, Integer level) throws SAXException {
		return ok(show.render("[{\"endingNode\":\"Julio Gomis-Tena\",\"startingNode\":\"Javier Chorro\"},{\"endingNode\":\"Marta Monserrat\",\"startingNode\":\"Javier Chorro\"},{\"endingNode\":\"Javier Saiz\",\"startingNode\":\"Javier Chorro\"},{\"endingNode\":\"Jose Maria Ferrero\",\"startingNode\":\"Javier Chorro\"},{\"endingNode\":\"Karen Cardona\",\"startingNode\":\"Javier Chorro\"}]"));
	}

	public static Result formSubmit() throws JAXBException, SAXException, ParserConfigurationException{
		Form<Show> form = Form.form(Show.class).bindFromRequest();
		if(form.hasErrors()) {
			return badRequest(index.render("Errors in form"));
		} else {
			Show data = form.get();
			return ok(
					show.render(myGraph(data.name, data.level, data.renderGraph).toString())
					);
		}
	}

	public static Result getGraphWithRender(String name, Integer level) throws JAXBException, SAXException, ParserConfigurationException{
		return ok(
				show.render(myGraph(name, level, 1).toString())
				);
	}

	public static Result getGraphWithoutRender(String name, Integer level) throws JAXBException, SAXException, ParserConfigurationException{
		return ok(
				show.render(myGraph(name, level, 0).toString())
				);
	}

	public static Result getCoAuthorInformation(String name) throws SAXException, JAXBException, ParserConfigurationException {
		JSONArray result = new JSONArray();
		String key = name;	
		HashMap<String,DBLPUser> dblp;
		DatasetInterface dblpDataset = new DBLPDataSource();
		dblp = dblpDataset.getDataset("dblp_example.xml");

		DBLPUser inputAuthor = dblp.get(key);
		List<Coauthorship> c = inputAuthor.getCoAuthors();

		for(int i = 0; i< c.size() ; i++) {
			JSONObject singleCoAuthor = new JSONObject();

			for(Entry<String, Integer> entry : DBLPParser.mapUserNameId.entrySet()){
				if(entry.getValue() == c.get(i).getCoauthorid()) {
					DBLPUser coauthor = dblp.get(entry.getKey());
					singleCoAuthor.put("Name", coauthor.getName());
					singleCoAuthor.put("ID", coauthor.getId());
					
					Iterator<Publication> iterator = c.get(i).getPublicationList().iterator();
					String publicationList = new String();
					while(iterator.hasNext()) {
						Publication p = iterator.next();
						//if(p.getAuthor().contains(coauthor.getId()) && p.getAuthor().contains(inputAuthor.getId()))
							publicationList += p.getTitle();
					}
					
					singleCoAuthor.put("Publication", publicationList);	
					result.put(singleCoAuthor);
				}

			}
		}

		return ok(
				show.render(result.toString())
				);		
	}

	public static Result getReputation(String name, String topic) {
		Double result = null;
		return ok(
				show.render(result.toString())
				);	
	}

	public static Result getCoAuthorsByTopic(String name, String topics) throws SAXException, JAXBException, ParserConfigurationException {
		JSONArray result = new JSONArray();
		String key = name;	
		String[] topicsArray = topics.split(",");
		HashMap<String,DBLPUser> dblp;
		DatasetInterface dblpDataset = new DBLPDataSource();
		dblp = dblpDataset.getDataset("dblp_example.xml");

		DBLPUser inputAuthor = dblp.get(key);
		List<Coauthorship> c = inputAuthor.getCoAuthors();

		for(int i = 0; i< c.size() ; i++) {
			JSONObject singleCoAuthor = new JSONObject();

			for(Entry<String, Integer> entry : DBLPParser.mapUserNameId.entrySet()){
				if(entry.getValue() == c.get(i).getCoauthorid()) {
					DBLPUser coauthor = dblp.get(entry.getKey());
					singleCoAuthor.put("Name", coauthor.getName());
					singleCoAuthor.put("ID", coauthor.getId());
					
//					Iterator<Publication> iterator = c.get(i).getPublicationList().iterator();
//					String publicationList = new String();
//					while(iterator.hasNext()) {
//						publicationList += iterator.next().getTitle();
//					}
					String publicationList= c.get(i).getPublicationList().get(0).getTitle();
					singleCoAuthor.put("Publication", publicationList);			

					for(int j = 0 ; j<topicsArray.length ; j++) {
						if(singleCoAuthor.get("Publication").toString().indexOf(topicsArray[j]) != -1) {
							result.put(singleCoAuthor);
							break;
						}
					}
				}

			}
		}

		return ok(
				show.render(result.toString())
				);	
	}

	public static Result getCoAuthorsByTopicAndTime(String name, String topics, Long year) throws SAXException, JAXBException, ParserConfigurationException {
		JSONArray result = new JSONArray();
		String key = name;	
		String[] topicsArray = topics.split(",");
		HashMap<String,DBLPUser> dblp;
		DatasetInterface dblpDataset = new DBLPDataSource();
		dblp = dblpDataset.getDataset("dblp_example.xml");

		DBLPUser inputAuthor = dblp.get(key);
		List<Coauthorship> c = inputAuthor.getCoAuthors();

		for(int i = 0; i< c.size() ; i++) {
			JSONObject singleCoAuthor = new JSONObject();

			for(Entry<String, Integer> entry : DBLPParser.mapUserNameId.entrySet()){
				if(entry.getValue() == c.get(i).getCoauthorid()) {
					DBLPUser coauthor = dblp.get(entry.getKey());
					singleCoAuthor.put("Name", coauthor.getName());
					singleCoAuthor.put("ID", coauthor.getId());
					
					Iterator<Publication> iterator = c.get(i).getPublicationList().iterator();
					String publicationList = new String();
					while(iterator.hasNext()) {
						Publication next = iterator.next();
						
						if(Long.valueOf(next.getYear()).longValue() > year) {
							publicationList += next.getTitle();
							publicationList += ";";							
						}
					}
					
					singleCoAuthor.put("Publication", publicationList);			

					for(int j = 0 ; j<topicsArray.length ; j++) {
						if(singleCoAuthor.get("Publication").toString().indexOf(topicsArray[j]) != -1) {
							result.put(singleCoAuthor);
							break;
						}
					}
				}

			}
		}

		return ok(
				show.render(result.toString())
				);	
	}

	public static Result getSocialNetwork(String name) throws JAXBException, SAXException, ParserConfigurationException {
		return ok(
				show.render(myGraph(name, UserCoAuthorSubgraph.GENERATE_FULL_SUBGRAPH, 0).toString())
				);	
	}
	
	public static Result getReputationForAuthor(String name) throws SAXException, JAXBException, ParserConfigurationException, IOException {
		DBLPTrustProcessor trustprocessor = new DBLPTrustProcessor();
		DBLPParser.getFileForNaiveBayesClassification("Modified_dblp_example.xml", "blah");
		Double trust = trustprocessor.getTrustValueFromName(name);
		return ok(
				trust.toString()
				);	
	}

}
