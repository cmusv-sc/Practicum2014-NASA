package edu.cmu.jung;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Stroke;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.collections15.Transformer;
import org.xml.sax.SAXException;

import edu.cmu.DBLPProcessor.Coauthorship;
import edu.cmu.DBLPProcessor.DBLPParser;
import edu.cmu.DBLPProcessor.DBLPUser;
import edu.cmu.dataset.DBLPDataSource;
import edu.cmu.dataset.DatasetInterface;
import edu.cmu.jung.Node;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.shortestpath.DijkstraShortestPath;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

/**
 *
 * @author NASA-Trust-team
 */

public class CoAuthorGraph {

	static int edgeCount = 0;  
	Graph<Node, Edge> g;
	//DirectedGraph<Node, Edge> g;
	List<Node> nodes = new ArrayList<Node>();
	HashMap<String,DBLPUser> dblp;

	@SuppressWarnings("unchecked")
	public CoAuthorGraph() throws SAXException, ParserConfigurationException {   
		DatasetInterface dblpDataset = new DBLPDataSource();
		dblp = dblpDataset.getDataset("dblp.xml");		
	}
	
	@SuppressWarnings("unchecked")
	public CoAuthorGraph(String fileName) throws SAXException, ParserConfigurationException {   
		DatasetInterface dblpDataset = new DBLPDataSource();
		dblp = dblpDataset.getDataset(fileName);		
	}
	
	/** Constructs an example directed graph with our vertex and edge classes 
	 * @throws JAXBException */
	public void constructGraph() throws JAXBException {
		g = new SparseMultigraph<Node, Edge>();
		//g = new DirectedSparseMultigraph<Node, Edge>();
		createNodes();
		createEdges();     
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

	private int getNodeFromAuthor(DBLPUser author) {
		for(int i = 0; i<nodes.size(); i++)
			if(nodes.get(i).isThisPassedNode(author))
				return i;

		return 0;
	}
	
	
	public Graph<Node, Edge> getGraph() {
		return g;
	}

	public void setGraph(Graph<Node, Edge> g) {
		this.g = g;
	}
	
	
	public List<Node> getNodes() {
		return nodes;
	}

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}

	public static Double getTimeDependantPathDistanceBetweenNodes(String author1Name, String author2Name, int year, CoAuthorGraph graph) throws JAXBException, IOException, SAXException, ParserConfigurationException {
		//DBLPParser.getPriorPublicationsXML("dblp_example.xml", year, "modified_dblp.xml");
//		CoAuthorGraph graph = new CoAuthorGraph("modified_dblp.xml");
//		graph.constructGraph();
//		
		List<Node> listOfNodes = graph.getNodes();
		DijkstraShortestPath<Node,Edge> alg = new DijkstraShortestPath(graph.getGraph());
		
		Node firstNode = null;
		Node secondNode = null;
		
		for(Node node: listOfNodes) {
			String nodeAuthorName = node.getUser().getName();
			if(nodeAuthorName.equalsIgnoreCase(author1Name))
				firstNode = node;
			if(nodeAuthorName.equalsIgnoreCase(author2Name))
				secondNode = node;
		}
		
		if((firstNode==null) || (secondNode==null))
			return 0.0;
		else
			return (Double) alg.getDistance(firstNode, secondNode);
	}
	
	
	public HashMap<String, DBLPUser> getDblp() {
		return dblp;
	}

	public void setDblp(HashMap<String, DBLPUser> dblp) {
		this.dblp = dblp;
	}

	/**
	 * @param args the command line arguments
	 * @throws JAXBException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	public static void main(String[] args) throws JAXBException, SAXException, ParserConfigurationException {
		//CoAuthorGraph myApp = new CoAuthorGraph("modified_dblp.xml");
		CoAuthorGraph myApp = new CoAuthorGraph("dblp_example.xml");
		myApp.constructGraph();
		//System.out.println(myApp.g.toString());
		
		// This builds the graph
		Layout<Node, Edge> layout = new CircleLayout<Node, Edge>(myApp.g);
		layout.setSize(new Dimension(690,690));
		//BasicVisualizationServer<Node,Edge> vv = new BasicVisualizationServer<Node,Edge>(layout);
		VisualizationViewer<Node, Edge> vv = new VisualizationViewer<Node, Edge>(layout);
		vv.setPreferredSize(new Dimension(700,700));       
		
		// Setup up a new vertex to paint transformer...
		Transformer<Integer,Paint> vertexPaint = new Transformer<Integer,Paint>() {
			public Paint transform(Integer i) {
				return Color.RED;
			}
		};  
		
		// Set up a new stroke Transformer for the edges
		float dash[] = {10.0f};
		final Stroke edgeStroke = new BasicStroke(1.0f, BasicStroke.CAP_BUTT,
				BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);
		Transformer<String, Stroke> edgeStrokeTransformer = new Transformer<String, Stroke>() {
			public Stroke transform(String s) {
				return edgeStroke;
			}
		};
		
		//            vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
		//            vv.getRenderContext().setEdgeStrokeTransformer(edgeStrokeTransformer);
		
		
		vv.setVertexToolTipTransformer(new Transformer<Node, String>() {
			public String transform(Node e) {
				return "Name: " + e.getUser().getName() + ", ID = " + e.getUser().getId()  + ",\n Number of co-authors: " + e.getUser().countCoauthorship();
			}
		});
		
		//vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<Edge>());
		vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);        

		JFrame frame = new JFrame("Co-authorship Graph View");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(vv);
		frame.pack();
		frame.setVisible(true);     

	}

}