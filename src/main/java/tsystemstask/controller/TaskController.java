package tsystemstask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tsystemstask.model.TSystemTaskModel;
import tsystemstask.service.TaskService;

@RestController
public class TaskController {
	@Autowired
	private TaskService taskService;

	@PostMapping(value = "/addtask")
	public ResponseEntity<String> createTask(@RequestBody TSystemTaskModel model) {
		ResponseEntity<String> responseEntity = null;
		try {
			boolean isAccepted = taskService.addTask(model);			
			responseEntity = new ResponseEntity<>(Boolean.toString(isAccepted), HttpStatus.CREATED);
		} catch (Exception e) {
			responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		}
		return responseEntity;
	}

	@GetMapping(value = "/getstatus/{id}")
	public ResponseEntity<String> getStatus(@PathVariable("id") int taskId) {
		ResponseEntity<String> responseEntity = null;
		double completeStatus = 0;
		try {
			completeStatus = taskService.getStatus(taskId);
			responseEntity = new ResponseEntity<>(Double.toString(completeStatus), HttpStatus.OK);
		} catch (Exception e) {
			responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}
}