/**
 * 
 */
package com.homework.todolist.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.homework.todolist.dao.MapTodoRepository;
import com.homework.todolist.dao.TodoRepository;
import com.homework.todolist.model.MapTodo;
import com.homework.todolist.model.Todo;
import com.homework.todolist.model.pojo.GetTodoParameter;
import com.homework.todolist.model.pojo.SaveTodo;


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
	 * @throws Exception 
	 */
	@Transactional
	public Todo saveTodo(Integer todoId, SaveTodo parameter) throws Exception {
		Todo todo = new Todo();
		if(todoId != null) {
			todo = todoRepository.findOneByTodoId(todoId);
		}
		
		boolean check = CollectionUtils.isEmpty(parameter.getReferenceIds()) ? 
							true : this.checkExistIds(parameter.getReferenceIds());
		
		if(check) {
			if(parameter.getIsDone()) {
				boolean referenceIsDone = todoRepository.checkisDone(todo.getTodoId());
				if(referenceIsDone != true) {
					throw new Exception("Sorry, Reference tasks are done yet");
				}
			}
			
			todo.setCreatedDate(parameter.getCreatedDate());
			todo.setUpdatedDate(parameter.getUpdatedDate());
			todo.setTask(parameter.getTask());
			todo.setIsDone(parameter.getIsDone());
			todoRepository.save(todo);
			
			if(todoId != null) mapTodoRepository.deleteByTodoId(todo.getTodoId());
			
			if(!CollectionUtils.isEmpty(parameter.getReferenceIds())) {
				List<MapTodo> list = new ArrayList<MapTodo>();
				for(Integer referenceId: parameter.getReferenceIds()) {
					MapTodo mapTodo = new MapTodo();
					mapTodo.setTodoId(todo.getTodoId());
					mapTodo.setReferenceId(referenceId);
					list.add(mapTodo);
				}
				mapTodoRepository.saveAll(list);
				todo.setMapTodos(list);
			}
			
			return todo;
		}else {
			throw new Exception("Check Reference Ids");
		}
	}
	
	
	public boolean checkExistIds(List<Integer> todoIds) {
		Integer count = todoRepository.countByTodoIdIn(todoIds);
		return (todoIds.size() == count);
	}
	
	
	/**
	 * 
	 * delete Todo
	 * 
	 * @since 2018. 12. 9.
	 * @author Incheol Jung
	 * @param todoId
	 * @return
	 */
	@Transactional
	public String deleteTodo(Integer todoId) {
		todoRepository.deleteById(todoId);
		return "success";
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
		Todo todo = todoRepository.findOneByTodoId(id);
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
		return Ids.size() == todoRepository.countByTodoIdIn(Ids);
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
			MapTodo mapTodo = mapTodoRepository.findOneByTodoIdAndReferenceId(id, referenceId);
			
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
		Integer count =  mapTodoRepository.deleteByTodoIdAndReferenceId(id, referenceId);
		
		if(count == 0) {
			message = "fail, there are no id, referenceId";
		}
		
		return message;
	}
}