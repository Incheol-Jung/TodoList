/**
 * 
 */
package com.homework.todolist.dao;

import org.springframework.data.repository.CrudRepository;

import com.homework.todolist.model.Todo;

/**
 * @author Incheol Jung
 */
public interface TodoRepository extends CrudRepository<Todo, Integer>{
	
}