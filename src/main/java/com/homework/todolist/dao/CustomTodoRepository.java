package com.homework.todolist.dao;

import java.util.List;

import com.homework.todolist.model.Todo;
import com.homework.todolist.model.pojo.GetTodoParameter;
import com.querydsl.core.QueryResults;

public interface CustomTodoRepository {
	QueryResults<Todo> findTodos(GetTodoParameter parameter);
	boolean checkisDonewithTodoId(Integer todoId);
	boolean checkisDonewithReferenceIds(List<Integer> referenceIds);
}
