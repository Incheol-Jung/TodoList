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
	Page<Todo> findByTaskContainingIgnoreCaseAndCreatedOnAndUpdatedOn(String task, Long createdOn, Long updatedOn, Pageable request);
	Page<Todo> findByTaskContainingIgnoreCase(String task, Pageable request);
	boolean existsByIdIn(List<Integer> Ids);
	Integer countByIdIn(List<Integer> Ids);
	Todo findOneById(Integer id);
}