/**
 * 
 */
package com.homework.todolist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homework.todolist.dao.TodoRepository;
import com.homework.todolist.model.Todo;

/**
 * @author Incheol Jung
 */
@Service
public class TodoService {
	
	@Autowired
	private TodoRepository todoRepository;
	
	public List<Todo> getTodo() {
		return (List<Todo>) todoRepository.findAll();
	}
	
	public String saveTodo() {
		Todo todo = new Todo();
		todo.setTask("Test");
		todoRepository.save(todo);
		return "";
	}
}