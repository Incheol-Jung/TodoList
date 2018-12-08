/**
 * 
 */
package com.homework.todolist.dao;

import org.springframework.data.repository.CrudRepository;

import com.homework.todolist.model.MapTodo;

/**
 * @author Incheol Jung
 */
public interface MapTodoRepository extends CrudRepository<MapTodo, Integer>{
	MapTodo findOneByTodoIdAndReferenceId(Integer todoId, Integer referenceId);
	Integer deleteByTodoIdAndReferenceId(Integer todoId, Integer referenceId);
}