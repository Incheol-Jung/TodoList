package com.homework.todolist.dao;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.CollectionUtils;

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
	
	private QTodo todo = QTodo.todo;
	private QMapTodo mapTodo = QMapTodo.mapTodo;
	
	@Override
	public QueryResults<Todo> findTodos(GetTodoParameter parameter){
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
                .offset((parameter.getPageIndex()-1)*parameter.getPageSize()) 
                .limit(parameter.getPageIndex()*parameter.getPageSize()) 
                .fetchResults();
		
		return result;
	}
	
	@Override
	public boolean checkisDonewithTodoId(Integer todoId) {
		Long count = from(mapTodo)
						.where(mapTodo.todoId.eq(todoId))
						.innerJoin(todo)
						.on(mapTodo.referenceId.eq(todo.todoId).and(todo.isDone.eq(false)))
						.fetchCount();
		return count <= 0;
	}
	
	@Override
	public boolean checkisDonewithReferenceIds(List<Integer> referenceIds) {
		if(CollectionUtils.isEmpty(referenceIds)) return true;
		
		Long count = from(todo)
						.where(todo.todoId.in(referenceIds).and(todo.isDone.eq(false)))
						.fetchCount();
		return count <= 0;
	}
}