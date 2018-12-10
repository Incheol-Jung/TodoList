/**
 * 
 */
package com.example.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import com.homework.todolist.TodoListApplication;
import com.homework.todolist.model.Todo;
import com.homework.todolist.model.pojo.GetTodoParameter;
import com.homework.todolist.model.pojo.GetTodoResponse;
import com.homework.todolist.model.pojo.SaveTodoParameter;
import com.homework.todolist.service.TodoService;

/**
 * @author Incheol Jung
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes={TodoListApplication.class})
//@Sql({"/data.sql"})
public class TodoServiceTest {

	@Autowired
	private TodoService todoService;
	
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
	 */
	@Test
	public void testDeleteTodo() {
		String message = todoService.deleteTodo(1);
		assertEquals(message, "success");
	}

}
