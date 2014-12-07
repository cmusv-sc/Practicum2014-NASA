package trustprocessor;
import java.util.List;

public class CoauthorshipEdge {
	
	private long mappingId;
	private long userId;
	private long coauthorId;
	private List<Integer> coauthorshipDates;
	
	public long getMappingId() {
		return mappingId;
	}
	public void setMappingId(long mappingId) {
		this.mappingId = mappingId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getCoauthorId() {
		return coauthorId;
	}
	public void setCoauthorId(long coauthorId) {
		this.coauthorId = coauthorId;
	}
	public List<Integer> getCoauthorshipDates() {
		return coauthorshipDates;
	}
	public void setCoauthorshipDates(List<Integer> coauthorshipDates) {
		this.coauthorshipDates = coauthorshipDates;
	}
	

}
