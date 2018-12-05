//package com.homework.todolist.dao;
//
//import java.util.List;
//
//import javax.persistence.EntityManager;
//
//import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
//
//import com.homework.todolist.model.QMapTodo;
//import com.homework.todolist.model.QTodo;
//import com.homework.todolist.model.Todo;
//import com.mysema.query.jpa.impl.JPAQuery;
//import com.mysema.query.types.Projections;
//
//public class TodoRepositoryImpl extends QuerydslRepositorySupport implements CustomTodoRepository{
//	private EntityManager entityManager;
//		
//	public TodoRepositoryImpl() {
//        super(Todo.class);
//    }
//	
//	@Override
//	public List<Integer> findReferenceIds(){
//		JPAQuery query = new JPAQuery(entityManager);
//		
//		QTodo todo = QTodo.todo;
//		QMapTodo mapTodo = QMapTodo.mapTodo;
//
//		return query.from(todo)
//				.innerJoin(mapTodo)
//				.where(todo.id.eq(mapTodo.taskId))
//				.list(Projections.bean(Integer.class, mapTodo.referenceId));
//	}
//}