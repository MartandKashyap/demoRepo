package tsystemstask.service;

import tsystemstask.model.TSystemTaskModel;

public interface TaskService {
	boolean addTask(TSystemTaskModel taskModel) throws Exception;
	double getStatus(int id) throws Exception;
}
