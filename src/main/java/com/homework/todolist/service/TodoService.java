/**
 * 
 */
package com.homework.todolist.service;

import java.util.ArrayList;
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
import com.homework.todolist.model.pojo.GetTodoResponse;
import com.homework.todolist.model.pojo.SaveTodoParameter;
import com.querydsl.core.QueryResults;


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
	 * get todos using querydsl
	 * 
	 * @since 2018. 12. 10.
	 * @author Incheol Jung
	 * @param parameter
	 * @return
	 */
	public GetTodoResponse getTodos(GetTodoParameter parameter) {
		GetTodoResponse response = new GetTodoResponse();
		QueryResults<Todo> result = todoRepository.findTodos(parameter);
		response.setItems(result.getResults());
		response.setTotalCount(result.getTotal());
		return response;
	}
	
	/**
	 * 
	 * save todo (check reference ids and if todo want to be done, check reference ids are done)
	 * 
	 * @since 2018. 12. 10.
	 * @author Incheol Jung
	 * @param todoId
	 * @param parameter
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public Todo saveTodo(Integer todoId, SaveTodoParameter parameter) throws Exception {
		Todo todo = new Todo();
		if(todoId != null) {
			todo = todoRepository.findOneByTodoId(todoId);
		}
		
		boolean checkExistIds = CollectionUtils.isEmpty(parameter.getReferenceIds()) ? true : this.checkExistIds(parameter.getReferenceIds());
		
		if(checkExistIds) {
			this.checkIsDone4ReferenceIds(parameter.getIsDone(), parameter.getReferenceIds());
			todo = this.saveTodo(todo, parameter);

			if(todoId != null) {
				mapTodoRepository.deleteByTodoId(todo.getTodoId());
			}
			
			return this.saveMapTodos(todo, parameter.getReferenceIds());
		}else {
			throw new Exception("these Reference Ids are not allowed");
		}
	}
	
	/**
	 * 
	 * save maptodos using referenceIds
	 * 
	 * @since 2018. 12. 10.
	 * @author Incheol Jung
	 * @param todo
	 * @param referenceIds
	 * @return
	 */
	private Todo saveMapTodos(Todo todo, List<Integer> referenceIds) {
		if(!CollectionUtils.isEmpty(referenceIds)) {
			List<MapTodo> list = new ArrayList<MapTodo>();
			for(Integer referenceId: referenceIds) {
				MapTodo mapTodo = new MapTodo();
				mapTodo.setTodoId(todo.getTodoId());
				mapTodo.setReferenceId(referenceId);
				list.add(mapTodo);
			}
			mapTodoRepository.saveAll(list);
			todo.setMapTodos(list);
		}
		return todo;
	}
	
	/**
	 * 
	 * check isDone of reference tasks
	 * 
	 * @since 2018. 12. 10.
	 * @author Incheol Jung
	 * @param isDone
	 * @param referenceIds
	 * @throws Exception
	 */
	private void checkIsDone4ReferenceIds(boolean isDone, List<Integer> referenceIds) throws Exception {
		if(isDone && !CollectionUtils.isEmpty(referenceIds)) {
			boolean referenceIsDone = todoRepository.checkisDonewithReferenceIds(referenceIds);
			if(referenceIsDone != true) {
				throw new Exception("Sorry, Reference tasks are done yet");
			}
		}
	}
	
	/**
	 * 
	 * save todo
	 * 
	 * @since 2018. 12. 10.
	 * @author Incheol Jung
	 * @param todo
	 * @param parameter
	 * @return
	 */
	private Todo saveTodo(Todo todo, SaveTodoParameter parameter) {
		todo.setCreatedDate(parameter.getCreatedDate());
		todo.setUpdatedDate(parameter.getUpdatedDate());
		todo.setTask(parameter.getTask());
		todo.setIsDone(parameter.getIsDone());
		
		return todoRepository.save(todo);
	}
	
	/**
	 * 
	 * check ids are exists
	 * 
	 * @since 2018. 12. 10.
	 * @author Incheol Jung
	 * @param todoIds
	 * @return
	 */
	private boolean checkExistIds(List<Integer> todoIds) {
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
}