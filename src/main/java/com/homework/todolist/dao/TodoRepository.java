/**
 * 
 */
package com.homework.todolist.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.homework.todolist.model.Todo;

/**
 * @author Incheol Jung
 */
public interface TodoRepository extends CrudRepository<Todo, Integer>, CustomTodoRepository{
	Page<Todo> findByTaskContainingIgnoreCase(String task, Pageable request);
	boolean existsByTodoIdIn(List<Integer> countByTodoIdIns);
	Integer countByTodoIdIn(List<Integer> Ids);
	Todo findOneByTodoId(Integer countByTodoIdIn);
}