/**
 * 
 */
package com.homework.todolist.service;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.homework.todolist.dao.MapTodoRepository;
import com.homework.todolist.dao.TodoRepository;
import com.homework.todolist.model.MapTodo;
import com.homework.todolist.model.Todo;
import com.homework.todolist.model.pojo.GetTodoParameter;


/**
 * @author Incheol Jung
 */
@Service
public class TodoService {
	
	@Autowired
	private TodoRepository todoRepository;
	
	
	@Autowired
	private MapTodoRepository mapTodoRepository;
	
	/**
	 * 
	 * Get TodoList
	 * 
	 * @since 2018. 12. 5.
	 * @author Incheol Jung
	 * @param parameter
	 * @return
	 */
	public List<Todo> getTodos(GetTodoParameter parameter) {
		List<Todo> result = todoRepository.findTodos(parameter);
		return result;
	}
	
	
	
	/**
	 * 
	 * save Todo
	 * 
	 * @since 2018. 12. 5.
	 * @author Incheol Jung
	 * @param todo
	 * @return
	 */
	@Transactional
	public String saveTodo(Todo todo) {
		todoRepository.save(todo);
		return "sucess";
	}
	
	/**
	 * 
	 * finish Todo
	 * 
	 * @since 2018. 12. 5.
	 * @author Incheol Jung
	 * @param id
	 * @param isDone
	 * @return
	 */
	@Transactional
	public String finishTodo(Integer id, boolean isDone) {
		String message = "success";
		Todo todo = todoRepository.findOneById(id);
		if(todo!= null) {
			todo.setIsDone(isDone);
			todoRepository.save(todo);
		}else {
			message = "fail, task id is not exists";
		}
		return message;
	}
	
	/**
	 * 
	 * check TaskIds
	 * 
	 * @since 2018. 12. 5.
	 * @author Incheol Jung
	 * @param Ids
	 * @return
	 */
	private boolean checkTaskIds(List<Integer> Ids) {
		return Ids.size() == todoRepository.countByIdIn(Ids);
	}
	
	/**
	 * 
	 * add ReferenceId
	 * 
	 * @since 2018. 12. 5.
	 * @author Incheol Jung
	 * @param id
	 * @param referenceId
	 * @return
	 */
	@Transactional
	public String addReferenceId(Integer id, Integer referenceId) {
		String message = "success";
		if(this.checkTaskIds(Arrays.asList(id, referenceId))) {
			MapTodo mapTodo = mapTodoRepository.findOneByTaskIdAndReferenceId(id, referenceId);
			
			if(mapTodo == null) {
				mapTodo = new MapTodo();
				mapTodo.setTodoId(id);
				mapTodo.setReferenceId(referenceId);
				
				mapTodoRepository.save(mapTodo);
				
			}else {
				message = "fail, already exists";
			}
		}else {
			message = "fail, task id is not exists";
		}
		
		return message;
	}
	
	/**
	 * 
	 * delete ReferenceId
	 * 
	 * @since 2018. 12. 5.
	 * @author Incheol Jung
	 * @param id
	 * @param referenceId
	 * @return
	 */
	@Transactional
	public String deleteReferenceId(Integer id, Integer referenceId) {
		String message = "success";
		Integer count =  mapTodoRepository.deleteByTaskIdAndReferenceId(id, referenceId);
		
		if(count == 0) {
			message = "fail, there are no id, referenceId";
		}
		
		return message;
	}
}