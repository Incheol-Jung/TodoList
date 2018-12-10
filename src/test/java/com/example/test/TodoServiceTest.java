/**
 * 
 */
package com.example.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import com.homework.todolist.TodoListApplication;
import com.homework.todolist.dao.MapTodoRepository;
import com.homework.todolist.dao.TodoRepository;
import com.homework.todolist.model.Todo;
import com.homework.todolist.model.pojo.GetTodoParameter;
import com.homework.todolist.model.pojo.GetTodoResponse;
import com.homework.todolist.model.pojo.SaveTodoParameter;
import com.homework.todolist.service.TodoService;
import com.querydsl.core.QueryResults;

/**
 * @author Incheol Jung
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes={TodoListApplication.class})
public class TodoServiceTest {

	@Autowired
	private TodoService todoService;
	
	@Mock
	private TodoRepository todoRepository;
	
	@Mock
	private MapTodoRepository mapTodoRepository;
	
	@Before
	public void createMockData() {
		List<Todo> todos = new ArrayList<Todo>();
		Todo todo1 = new Todo();
		todo1.setTask("java");
		
		Todo todo2 = new Todo();
		todo2.setTask("c#");
		
		todos.add(todo1);
		todos.add(todo2);
		
		QueryResults<Todo> result = new QueryResults<Todo>(todos, Long.valueOf(0), Long.valueOf(10), 2);
		when(todoRepository.findTodos(any(GetTodoParameter.class))).thenReturn(result);
	}
	
	/**
	 * Test method for {@link com.homework.todolist.service.TodoService#getTodos(com.homework.todolist.model.pojo.GetTodoParameter)}.
	 */
	@Test
	public void testGetTodos() {
		GetTodoParameter parameter = new GetTodoParameter();
		parameter.setPageIndex(1);
		parameter.setPageSize(10);
		
		GetTodoResponse result = todoService.getTodos(parameter);
		if(!CollectionUtils.isEmpty(result.getItems())) {
			assertNotNull(result.getItems().get(0).getTodoId());
		}
	}
	
	/**
	 * Test method for {@link com.homework.todolist.service.TodoService#saveTodo(java.lang.Integer, com.homework.todolist.model.pojo.SaveTodo)}.
	 * @throws Exception 
	 */
	@Test
	public void testSaveTodo() throws Exception {
		SaveTodoParameter parameter = new SaveTodoParameter();
		parameter.setIsDone(false);
		parameter.setTask("python");
		
		// unit test for insert
		Todo todoByCreate = todoService.saveTodo(null, parameter);
		assertNotNull(todoByCreate);
		
		// unit test for update
		Todo todoByUpdate = todoService.saveTodo(1, parameter);
		assertNotNull(todoByUpdate);
	}

	/**
	 * Test method for {@link com.homework.todolist.service.TodoService#deleteTodo(java.lang.Integer)}.
	 * @throws Exception 
	 */
	@Test
	public void testDeleteTodo() throws Exception {
		String message = todoService.deleteTodo(1);
		assertEquals(message, "success");
	}

}
