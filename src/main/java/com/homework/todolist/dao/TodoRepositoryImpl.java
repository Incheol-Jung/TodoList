package com.homework.todolist.dao;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.homework.todolist.model.QMapTodo;
import com.homework.todolist.model.QTodo;
import com.homework.todolist.model.Todo;
import com.homework.todolist.model.pojo.GetTodoParameter;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;

public class TodoRepositoryImpl extends QuerydslRepositorySupport implements CustomTodoRepository{
	
	public TodoRepositoryImpl() {
        super(Todo.class);
    }
	
	
	
	@Override
	public List<Todo> findTodos(GetTodoParameter parameter){
		QTodo todo = QTodo.todo;
		QMapTodo mapTodo = QMapTodo.mapTodo;
		
		BooleanBuilder condition = new BooleanBuilder();
		condition.and(todo.task.contains(parameter.getTask()));
		if(parameter.getCreatedDate() != null) {
			condition.and(todo.createdDate.between(parameter.getCreatedDate(),parameter.getCreatedDate()+ 60 * 60 * 24 * 1000));
		}
		if(parameter.getUpdatedDate() != null) {
			condition.and(todo.updatedDate.between(parameter.getUpdatedDate(),parameter.getUpdatedDate()+ 60 * 60 * 24 * 1000));
		}
		
		QueryResults<Todo> result = from(todo)
				.where(condition)
                .offset(parameter.getPageNumber()) 
                .limit(parameter.getPageSize()) 
                .fetchResults();
		
		return result.getResults();
	}
	
	@Override
	public boolean checkisDone(Integer todoId) {
		QTodo todo2 = QTodo.todo;
		QMapTodo mapTodo = QMapTodo.mapTodo;

		Long count = from(mapTodo)
						.where(mapTodo.todoId.eq(todoId))
						.innerJoin(todo2)
						.on(mapTodo.referenceId.eq(todo2.todoId).and(todo2.isDone.eq(false)))
						.fetchCount();
		return count <= 0;
	}
}