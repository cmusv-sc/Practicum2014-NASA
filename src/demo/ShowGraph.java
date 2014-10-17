package demo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.collections15.Transformer;
import org.xml.sax.SAXException;

import edu.cmu.jung.Edge;
import edu.cmu.jung.Node;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.visualization.VisualizationImageServer;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

/**
 * Servlet implementation class ShowGraph
 */
@WebServlet(
		urlPatterns = { "/ShowGraph" }, 
		initParams = { 
				@WebInitParam(name = "topic", value = "")
		})
public class ShowGraph extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowGraph() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h1>" + "Filter DBLP" + "</h1>");
		out.println("<form action='ShowGraph' method='POST'>Topic: <input type='text' name='topic_name'><input type='submit' value='Submit' /></form>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("image/jpeg");
		String filename = "";

		// Actual logic goes here.
		System.out.print(request.getParameter("topic_name"));
		
		if(request.getParameter("topic_name") == null){
			response.getWriter().write("Must specify topic!");
		}
		else if(request.getParameter("topic_name").toLowerCase().contains("machine")){
			filename = "dblp_example_ml";
		}
		else if(request.getParameter("topic_name").toLowerCase().contains("cloud")){
			filename = "dblp_example_cloud";
		}
		else if(request.getParameter("topic_name").toLowerCase().contains("big")){
			filename = "big_data";
		}
		else{
			filename = "dblp_example";
		}
		
		try {
			exportPic(filename);
			
			File f = new File("/home/mustafa/images/" + filename + ".png");
			BufferedImage bi = ImageIO.read(f);
			OutputStream outs = response.getOutputStream();
			ImageIO.write(bi, "jpg", outs);
			outs.close();
			
		} catch (HeadlessException e) {
			
		}
	}
	
	
	public static void exportPic(String path){
		CoAuthorGraph myApp = null;
		
		try {
			
			myApp = new CoAuthorGraph("/home/mustafa/xml/" + path + ".xml");
			myApp.constructGraph();
		} catch (SAXException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//System.out.println(myApp.g.toString());
		catch (JAXBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

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
		vv.setVertexToolTipTransformer(new Transformer<Node, String>() {
			public String transform(Node e) {
				return "Name: " + e.getUser().getName() + ", ID = " + e.getUser().getId()  + ",\n Number of co-authors: " + e.getUser().countCoauthorship();
			}
		});
		vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);
		
		
		// Create the VisualizationImageServer
		// vv is the VisualizationViewer containing my graph
		VisualizationImageServer<Node, Edge> vis =
		    new VisualizationImageServer<Node, Edge>(vv.getGraphLayout(),
		        vv.getGraphLayout().getSize());

		// Configure the VisualizationImageServer the same way
		// you did your VisualizationViewer. In my case e.g.

		vis.setBackground(Color.WHITE);

		// Create the buffered image
		BufferedImage image = (BufferedImage) vis.getImage(
		    new Point2D.Double(vv.getGraphLayout().getSize().getWidth() / 2,
		    vv.getGraphLayout().getSize().getHeight() / 2),
		    new Dimension(vv.getGraphLayout().getSize()));

		// Write image to a png file
		File outputfile = new File("/home/mustafa/images/" + path + ".png");

		try {
		    ImageIO.write(image, "png", outputfile);
		} catch (IOException e) {
		    // Exception handling
		}
	}

}
