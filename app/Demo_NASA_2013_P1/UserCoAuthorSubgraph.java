package Demo_NASA_2013_P1;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.collections15.Transformer;
import org.hibernate.validator.constraints.Length;
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
import edu.uci.ics.jung.algorithms.filters.KNeighborhoodFilter;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;
import edu.cmu.jung.*;
/**
 *
 * @author NASA-Trust-team
 */

public class UserCoAuthorSubgraph {
	public static int GENERATE_FULL_SUBGRAPH = 999;
	static int edgeCount = 0;  
	public Graph<Node, Edge> g;
	//DirectedGraph<Node, Edge> g;
	List<Node> nodes = new ArrayList<Node>();
	HashMap<String,DBLPUser> dblp;

	public UserCoAuthorSubgraph() throws SAXException, ParserConfigurationException {   
		DatasetInterface dblpDataset = new DBLPDataSource();
		dblp = dblpDataset.getDataset("dblp_example.xml");		
	}
	
	public UserCoAuthorSubgraph(String fileName) throws SAXException, ParserConfigurationException {
		DatasetInterface dblpDataset = new DBLPDataSource();
		dblp = dblpDataset.getDataset(fileName);
	}

	/** Constructs an example directed graph with our vertex and edge classes 
	 * @throws JAXBException */
	public JSONArray constructGraph(String name, int noOfLevels) throws JAXBException {
		g = new SparseMultigraph<Node, Edge>();
		//g = new DirectedSparseMultigraph<Node, Edge>();
		JSONArray result = createNodesAndEdges(name, noOfLevels);
		//JSONArray result = createEdges();  
		return result;
	}

	private JSONArray createNodesAndEdges(String name, int noOfLevels) throws JAXBException {
		String key = name;	
		JSONArray resultJson = new JSONArray();
		DBLPUser inputAuthor = dblp.get(key);
		System.out.println(inputAuthor.getId());
		Node firstNode = new Node(inputAuthor);

		return createNodesAndEdgesHelper(null, firstNode, null, 0, noOfLevels, resultJson);
		
		//createNodes();
		//createEdges();
		// TODO: KNeighborhoodFilter<Node, Edge> coAuthorSubgraph = new KNeighborhoodFilter<Node, Edge>(inputAuthor, noOfLevels, 0);
	}
	
	private void createNodes() throws JAXBException {
		Iterator<String> it = dblp.keySet().iterator();
		while (it.hasNext())
		{
			String key = it.next();
			DBLPUser author = dblp.get(key);
			Node currentNode = new Node(author);
			nodes.add(currentNode);
		}
	}

	private void createEdges() throws JAXBException {
		Iterator<String> it = dblp.keySet().iterator();
		while (it.hasNext())
		{
			String key = it.next();
			DBLPUser author = dblp.get(key);
			int startingNodeNumber, endingNodeNumber;
			startingNodeNumber = getNodeFromAuthor(author);
			for(Coauthorship c: author.getCoAuthors()) {
				DBLPUser coauthor = DBLPParser.getDBLPUserFromID(c.getCoauthorid());
				endingNodeNumber = getNodeFromAuthor(coauthor);				
				g.addEdge(new Edge(),nodes.get(startingNodeNumber), nodes.get(endingNodeNumber), EdgeType.UNDIRECTED);
			}	
		}
	}
	
	private JSONArray createNodesAndEdgesHelper(Node previousNode, Node currentNode, List<Publication> publicationList, int currentLevel, int noOfLevels, JSONArray resultJson) throws JAXBException {
		currentNode.setVisited(true);
		currentNode.setLevel(currentLevel);
		String edgeName = "";

		if((currentLevel > noOfLevels) && (noOfLevels != GENERATE_FULL_SUBGRAPH))
			return resultJson;

		nodes.add(currentNode);
		DBLPUser currentAuthor = currentNode.getUser();

		if(previousNode != null) {
			JSONObject singleEdge = new JSONObject();

			//create a new edge containing the relevant information
			Edge edge = new Edge();
			edge.setStartNode(previousNode);
			edge.setEndNode(currentNode);

//			Iterator<Publication> iterator = publicationList.iterator();
//			while(iterator.hasNext()) {
//				edgeName += iterator.next().getTitle() + ";";
//			}
//			edgeName = edgeName.substring(0, edgeName.length()-1);
			
			edgeName = publicationList.get(0).getTitle();

			edge.setPublicationName(edgeName);

			//and use it to fill the information for the return JSON
			singleEdge.put("startingNode", previousNode.getUser().getName());
			singleEdge.put("endingNode", currentAuthor.getName());
			singleEdge.put("publicationTitle", edge.getPublicationName());
			g.addEdge(edge , previousNode, currentNode, EdgeType.UNDIRECTED);

			//add JSON object to the result
			resultJson.put(singleEdge);
		}

		List<Coauthorship> listOfCoauthors = currentAuthor.getCoAuthors();

//		for(int i =0;i<c.size();i++){
//			for(Entry<String, Integer> entry : DBLPParser.mapUserNameId.entrySet()){
//				if(entry.getValue() == c.get(i).getCoauthorid()){
//					DBLPUser coauthor = dblp.get(entry.getKey());
//					
//					Edge dummyEdge = new Edge();
//					dummyEdge.setStartNode(currentNode);
//					dummyEdge.setEndNode(new Node(coauthor));
//					dummyEdge.setPublicationName(edgeName);
//					
//					if((getNodeFromAuthor(coauthor)==-1) /*|| ((getNodeFromAuthor(coauthor)!= -1) && (!g.containsEdge(dummyEdge)))*/) {
//						Node coauthorNode = new Node(coauthor);
//						//and use it to recursively call the function
//						createNodesAndEdgesHelper(currentNode, coauthorNode, c.get(i).getPublicationList(), currentLevel+1, noOfLevels, resultJson);
//					}
//				}
//			}
//		}
		
		for(Coauthorship c: listOfCoauthors) {
			DBLPUser coauthor = DBLPParser.getDBLPUserFromName(c.getCoauthorName());
			
			Edge dummyEdge = new Edge();
			dummyEdge.setStartNode(currentNode);
			dummyEdge.setEndNode(new Node(coauthor));
			dummyEdge.setPublicationName(edgeName);
			
			if((getNodeFromAuthor(coauthor)==-1) /*|| ((getNodeFromAuthor(coauthor)!= -1) && (!g.containsEdge(dummyEdge)))*/) {
				Node coauthorNode = new Node(coauthor);
				//and use it to recursively call the function
				createNodesAndEdgesHelper(currentNode, coauthorNode, c.getPublicationList(), currentLevel+1, noOfLevels, resultJson);
			}
		}	
		
		return resultJson;
	}

	private int getNodeFromAuthor(DBLPUser author) {
		for(int i = 0; i<nodes.size(); i++)
			if(nodes.get(i).isThisPassedNode(author))
				return i;

		return -1;
	}

	/**
	 * @param args the command line arguments
	 * @throws JAXBException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	public static void main(String[] args) throws JAXBException, SAXException, ParserConfigurationException {
		
		String inputAuthor, fileName;
		int noOfLevels;
		JSONArray result;

		Scanner input = new Scanner(System.in);
		
		System.out.println("\nEnter author's name- ");
		inputAuthor = input.nextLine();

		//System.out.println("Enter name of file- ");
		fileName = "dblp_example.xml";
		
		System.out.println("Enter number of sub levels of coauthorship- ");
		noOfLevels = input.nextInt();	
		
		UserCoAuthorSubgraph myApp = new UserCoAuthorSubgraph(fileName);
		
		result = myApp.constructGraph(inputAuthor, noOfLevels);

		System.out.println(result);	
		// This builds the graph
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

		vv.setEdgeToolTipTransformer(new Transformer<Edge, String>() {
			public String transform(Edge e) {
				return e.getPublicationName();
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
}

