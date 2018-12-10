package com.homework.todolist.dao;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.CollectionUtils;

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
	
	/**
	 * 
	 * select * from todos where createdDate and updatedDate and task;
	 * 
	 * @since 2018. 12. 10.
	 * @author Incheol Jung
	 * @param parameter
	 * @return
	 */
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
	
	/**
	 * 
	 * select count(*) from todo where todo in referenceIds and isDone = true
	 * 
	 * @since 2018. 12. 10.
	 * @author Incheol Jung
	 * @param referenceIds
	 * @return
	 */
	@Override
	public boolean checkisDonewithReferenceIds(List<Integer> referenceIds) {
		if(CollectionUtils.isEmpty(referenceIds)) return true;
		
		Long count = from(todo)
						.where(todo.todoId.in(referenceIds).and(todo.isDone.eq(false)))
						.fetchCount();
		return count <= 0;
	}
}