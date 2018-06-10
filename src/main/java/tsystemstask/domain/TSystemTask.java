package tsystemstask.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "task")
public class TSystemTask {
	@Id
	private int taskId;
	
	@Column
	private long creationTime;
	
	@Column
	private long endTime;
	
	@Column
	private long duration;
	
	public TSystemTask() {}

	public TSystemTask(int taskId, long creationTime, long endTime, long duration) {
		super();
		this.taskId = taskId;
		this.creationTime = creationTime;
		this.endTime = endTime;
		this.duration = duration;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public long getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(long creationTime) {
		this.creationTime = creationTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}
}
