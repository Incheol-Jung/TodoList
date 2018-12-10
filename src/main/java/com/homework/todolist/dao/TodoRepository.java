/**
 * 
 */
package com.homework.todolist.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.homework.todolist.model.Todo;

/**
 * @author Incheol Jung
 */
public interface TodoRepository extends CrudRepository<Todo, Integer>, CustomTodoRepository{
	Integer countByTodoIdIn(List<Integer> Ids);
	Todo findOneByTodoId(Integer countByTodoIdIn);
}