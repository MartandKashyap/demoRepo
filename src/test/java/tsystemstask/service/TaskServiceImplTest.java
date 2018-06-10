package tsystemstask.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import tsystemstask.domain.TSystemTask;
import tsystemstask.model.TSystemTaskModel;
import tsystemstask.repository.TaskRepository;

@RunWith(SpringRunner.class)
public class TaskServiceImplTest {

	@TestConfiguration
	static class TaskServiceImplTestContextConfiguration{
		@Bean
		public TaskServiceImpl taskService() {
			return new TaskServiceImpl();
		}
	}

	@Autowired
	private TaskService taskService;

	@MockBean
	private TaskRepository taskRepository;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Before
	public void setUp() {
		TSystemTask task = new TSystemTask(1, System.currentTimeMillis(), System.currentTimeMillis()+60000, 60000);
		Optional<TSystemTask> optional = Optional.of(task);

		Mockito.when(taskRepository.findById(task.getTaskId())).thenReturn(optional);
	}
	
	@Test
	public void whenAddTaskWithInvalidId_thenException() throws Exception {
		thrown.expect(Exception.class);
		thrown.expectMessage(is("task already exists with id: 1"));
		
		taskService.addTask(new TSystemTaskModel(1, 0));
	}

	@Test
	public void whenInValidId_thenException() throws Exception {
		thrown.expect(Exception.class);
		thrown.expectMessage(is("task not started with id: 2"));

		taskService.getStatus(2);
	}

	@Test
	public void whenInValidId_thenCompleteStatus() {
		try {
			Thread.sleep(500L);
			double completePercentage = taskService.getStatus(1);
			assertTrue(completePercentage > 0 && completePercentage <= 100);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
