package com.homework.todolist.dao;

import java.util.List;

import com.homework.todolist.model.Todo;
import com.homework.todolist.model.pojo.GetTodoParameter;

public interface CustomTodoRepository {
	List<Todo> findTodos(GetTodoParameter parameter);
	boolean checkisDone(Integer todoId);
}
