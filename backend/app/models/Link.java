package models;

// // Model representing the links in the graphs being visualized
public class Link {
	
	private String source;
	private String target;
	private long value;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}
	
	public long getValue() {
		return value;
	}
	
	public void setValue(long value) {
		this.value = value;
	}
	
	public Link(){
	}

	public Link(String source, String target, long value) {
		this.source = source;
		this.target = target;
		this.value = value;
	}

}
