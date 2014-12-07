package models;

// Model representing the nodes in the graphs being visualized
public class Node {
	
	private String trustScore;
	private String topic;
	private String name;
	private String list;
	private String id;
	private String type;
	private long citationCount;
	
	

	public String getTrustScore() {
		return trustScore;
	}



	public void setTrustScore(String trustScore) {
		this.trustScore = trustScore;
	}



	public String getTopic() {
		return topic;
	}



	public void setTopic(String topic) {
		this.topic = topic;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getList() {
		return list;
	}



	public void setList(String list) {
		this.list = list;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public long getCitationCount() {
		return citationCount;
	}



	public void setCitationCount(long citationCount) {
		this.citationCount = citationCount;
	}



	public Node(String trustScore, String topic, String name, String list, String id, String type, long citationCount) {
		this.trustScore = trustScore;
		this.topic = topic;
		this.name = name;
		this.list = list;
		this.id = id;
		this.type = type;
		this.citationCount = citationCount;
	}

}
