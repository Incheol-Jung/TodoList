/**
 * 
 */
package com.homework.todolist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.homework.todolist.dao.TodoRepository;
import com.homework.todolist.model.Todo;
import com.homework.todolist.model.pojo.GetTodoParameter;

/**
 * @author Incheol Jung
 */
@Service
public class TodoService {
	
	@Autowired
	private TodoRepository todoRepository;
	
	public List<Todo> getTodoList(GetTodoParameter parameter) {
		
		Pageable req = PageRequest.of(parameter.getPageNumber()-1, parameter.getPageSize());
		Page<Todo> result = todoRepository.findByTaskContainingIgnoreCase(parameter.getTask(), req);
//		results.setRecCnt(result.getTotalElements());
		return result.getContent();
	}
	
	public String saveTodo(Todo todo) {
		todoRepository.save(todo);
		return "sucess";
	}
}