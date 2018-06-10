package tsystemstask.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tsystemstask.domain.TSystemTask;
import tsystemstask.model.TSystemTaskModel;
import tsystemstask.repository.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService {
	@Autowired
	private TaskRepository taskRepo;
	
	@Override
	public boolean addTask(TSystemTaskModel taskModel) throws Exception {
		boolean inserted = false;
		
		if(taskRepo.findById(taskModel.getId()).isPresent())
			throw new Exception("task already exists with id: "+ taskModel.getId());
		
		if(taskModel.getId() != 0 && taskModel.getDuration() > 0) {
			long currenTime = System.currentTimeMillis();			
			TSystemTask task = new TSystemTask(taskModel.getId(), currenTime, 
										currenTime + taskModel.getDuration(), taskModel.getDuration());
			taskRepo.save(task);
			inserted = true;
		}
		return inserted;
	}

	@Override
	public double getStatus(int id) throws Exception {
		Optional<TSystemTask> optional = taskRepo.findById(id);
		long currentTime = System.currentTimeMillis();
		double completePercentage = 0;
		if(optional.isPresent()) {
			TSystemTask task = optional.get();
			if(currentTime >= task.getCreationTime() && 
					currentTime <= task.getEndTime())				
				completePercentage = 100.0 - (((double) (task.getEndTime() - currentTime) / task.getDuration()) * 100.0);
			else
				completePercentage = 100.0;				
		}
		else
			throw new Exception("task not started with id: "+id);
		return completePercentage;
	}
}