/**
 * 
 */
package com.homework.todolist.model.pojo;

import java.util.List;

import com.homework.todolist.model.Todo;

/**
 * @author Incheol Jung
 */
public class GetTodoResponse {
	private List<Todo> items;
	private Long totalCount;
	
	public List<Todo> getItems() {
		return items;
	}
	public void setItems(List<Todo> items) {
		this.items = items;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
}