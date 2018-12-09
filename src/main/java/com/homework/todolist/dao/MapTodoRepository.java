/**
 * 
 */
package com.homework.todolist.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.homework.todolist.model.MapTodo;

/**
 * @author Incheol Jung
 */
public interface MapTodoRepository extends CrudRepository<MapTodo, Integer>{
	List<MapTodo> findByTodoId(Integer todoId);
	MapTodo findOneByTodoIdAndReferenceId(Integer todoId, Integer referenceId);
	Integer deleteByTodoIdAndReferenceId(Integer todoId, Integer referenceId);
	Integer deleteByTodoId(Integer todoId);
}