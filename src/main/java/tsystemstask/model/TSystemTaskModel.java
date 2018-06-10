package tsystemstask.model;

public class TSystemTaskModel {
	private int id;
	private long durationInMillis;
	
	public TSystemTaskModel() {}
	
	public TSystemTaskModel(int id, long durationInMillis) {
		this.id = id;
		this.durationInMillis = durationInMillis;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getDuration() {
		return durationInMillis;
	}
	public void setDuration(long durationInMillis) {
		this.durationInMillis = durationInMillis;
	}
}
