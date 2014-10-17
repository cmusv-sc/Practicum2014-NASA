package edu.cmu.trustprocessor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import edu.cmu.DBLPProcessor.DBLPUser;
import edu.cmu.jung.Edge;
import edu.cmu.jung.Node;

public class CoreTrustModelVisualization {
	public CoreTrustModelVisualization() throws SAXException, JAXBException, ParserConfigurationException {
		String path = "";
		List<String> developerList;
		developerList = new ArrayList<String>();
		try {
			File file = new File(path + "users.txt");
			BufferedReader bufferedReader = new BufferedReader(new FileReader(
					file));
			String string;
			while ((string = bufferedReader.readLine()) != null) {
				developerList.add(string);
			}
			bufferedReader.close();
			DBLPTrustProcessor dblpTrustProcessor = new DBLPTrustProcessor();
			Map<String, Node> nodeMap = new HashMap<String, Node>();
			ArrayList<Long> lastTierDblpUsers = new ArrayList<Long>();
			Map<Integer, Edge> edgeMap = new HashMap<Integer, Edge>();
			Map<Integer, DBLPTrustModel> mapDblpTrustForUsers = new HashMap<Integer, DBLPTrustModel>();
			int count = 2;
			int i = 0;
			double maxPopularity = 0;
			while (i < count) {
				List<DBLPTrustModel> dblpTrustForUsers;
				Map<Integer, DBLPTrustModel> previousTiersUsers = new HashMap<Integer, DBLPTrustModel>();
				if (lastTierDblpUsers.size() == 0) {
					dblpTrustForUsers = dblpTrustProcessor
							.expertTrustMatrix(developerList);
				} else {
					dblpTrustForUsers = dblpTrustProcessor
							.trustModelForAuthorIds(lastTierDblpUsers);
					Iterator<Entry<Integer, DBLPTrustModel>> iterator = mapDblpTrustForUsers
							.entrySet().iterator();
					while (iterator.hasNext()) {
						Entry<Integer, DBLPTrustModel> entry = (Entry<Integer, DBLPTrustModel>) iterator
								.next();
						previousTiersUsers
						.put(entry.getKey(), entry.getValue());
					}
				}
				Iterator<DBLPTrustModel> iteratorDblpTrustModel = dblpTrustForUsers
						.iterator();

				while (iteratorDblpTrustModel.hasNext()) {
					DBLPTrustModel dblpTrustModel = iteratorDblpTrustModel
							.next();
					DBLPUser dblpUser = dblpTrustModel.getDblpUser();
					Node node = new Node(dblpUser);

					node.setName(dblpUser.getName());
					node.setDBLPUserId(dblpUser.getId());

					System.out.println(dblpUser.getName());

					TrustModelIntegration integration = new TrustModelIntegration();

					node.setPopularity(integration.getKnowledgeFactorWeight()*(dblpTrustModel.getDblpKnowledgeFactor().getkPaperPublished().getFinalKPaperPublished())+dblpTrustModel.getDblpSocialFactor().getkCoauthorship().getTimeScaledCoauthorship()*integration.getSocialFactorWeight());

					node.setDblpTrustModel(dblpTrustModel);
					nodeMap.put(node.getName(), node);

					if (dblpTrustModel.getTrustValue() > maxPopularity) {
						maxPopularity = dblpTrustModel.getTrustValue();
					}

					mapDblpTrustForUsers.put(dblpUser.getId(), dblpTrustModel);
				}
				iteratorDblpTrustModel = dblpTrustForUsers.iterator();
				while (iteratorDblpTrustModel.hasNext()) {
					DBLPTrustModel dblpTrustModel = iteratorDblpTrustModel
							.next();
					Map<Integer, Double> coauthorMapForNode = dblpTrustModel
							.getDblpSocialFactor().getkCoauthorship()
							.getCoauthorIdToSocialFactorFromCoauthor();
					Node startNode = nodeMap.get(dblpTrustModel.getDblpUser()
							.getName());
					Iterator<Entry<Integer, Double>> iteratorCoauthorMapForNode = coauthorMapForNode
							.entrySet().iterator();
					lastTierDblpUsers = new ArrayList<Long>();
					while (iteratorCoauthorMapForNode.hasNext()) {
						Entry<Integer, Double> pair = (Entry<Integer, Double>) iteratorCoauthorMapForNode
								.next();
						DBLPTrustModel newDblpTrustModel = mapDblpTrustForUsers
								.get(pair.getKey());
						if (newDblpTrustModel != null) {
							Edge edge = new Edge();
							edge.setStartNode(startNode);
							DBLPUser dblpUser = newDblpTrustModel.getDblpUser();
							edge.setEndNode(nodeMap.get(dblpUser.getName()));
							edge.setEdgeWeight(pair.getValue());
							edgeMap.put(edge.getId(), edge);
							if (previousTiersUsers.get(dblpUser.getId()) != null) {
								edge = new Edge();
								edge.setStartNode(nodeMap.get(newDblpTrustModel
										.getDblpUser().getName()));
								edge.setEndNode(startNode);
								edgeMap.put(edge.getId(), edge);
							}
						} else {
							lastTierDblpUsers.add((long) pair.getKey());
						}
					}
				}
				i++;
			}
			//			JungDemo jungDemo = new JungDemo();
			//			Graph<Node, Edge> graph = jungDemo.instantiateGraphCreation(
			//					nodeMap, edgeMap);
			//			jungDemo.projectGraph(graph, maxPopularity);
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public static void main(String[] args) throws SAXException, JAXBException, ParserConfigurationException {
		CoreTrustModelVisualization coreTrustModelVisualization = new CoreTrustModelVisualization();
	}
}