package tsystemstask.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import tsystemstask.service.TaskService;

@RunWith(SpringRunner.class)
@ContextConfiguration
public class TaskControllerTest {
	@Configuration
	static class TaskControllerTestConfiguration{
		@Bean
		public TaskService taskService() {
			return Mockito.mock(TaskService.class);
		}
		
		@Bean
		public TaskController taskController() {
			return new TaskController();
		}
	}
	
	@Autowired
	private TaskController taskController;
	
	@Autowired
	private TaskService taskService;
	
	private MockMvc mvc;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void createTaskTest() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(this.taskController).build();
		String json = "{\"id\": 1,\"duration\": 300000}";
		
		mvc.perform(post("/addtask")
						.contentType(MediaType.APPLICATION_JSON)
						.content(json)).andExpect(status().isCreated());
	}
	
	
/*	@Test
	public void createTaskExceptionTest() throws Exception {
		//when(taskService.addTask(Matchers.any())).thenThrow(Exception.class);
		mvc = MockMvcBuilders.standaloneSetup(this.taskController).build();
		String json = "{\"id\": 2,\"duration\": 10000}";
		
		mvc.perform(post("/addtask")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)).andExpect(status().isUnprocessableEntity());
	}*/
	
	@Before
	public void setUpStatus1() throws Exception {		
		Mockito.when(taskService.getStatus(1)).thenReturn(50.55);
	}
	
	@Test
	public void getStatusSuccessTest() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(this.taskController).build();
		mvc.perform(get("/getstatus/{id}", 1)).andExpect(status().isOk());
	}
	
/*	@Before
	public void setUpStatus2() throws Exception {		
		Mockito.when(taskService.getStatus(2)).thenThrow(Exception.class);
	}
	
	@Test
	public void getStatusNotFoundTest() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(this.taskController).build();
		mvc.perform(get("/getstatus/{id}", 2)).andExpect(status().isNotFound());
	}*/
}
