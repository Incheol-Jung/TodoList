/**
 * 
 */
package com.homework.todolist.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.springframework.util.CollectionUtils;

import io.swagger.annotations.ApiModelProperty;



/**
 * @author Incheol Jung
 */
@Entity
public class Todo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(example="1")
	private int todoId;
	
	@ApiModelProperty(example="task test 1")
	private String task;
	
	@ApiModelProperty(example="1544055826168")
	private Long createdDate;
	
	@ApiModelProperty(example="1544055826168")
	private Long updatedDate;
	
	@ApiModelProperty(example="false")
	private Boolean isDone;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="todoId", insertable = false, updatable = false)
	private List<MapTodo> mapTodos;

	public List<Integer> getReferenceIds() {
		return CollectionUtils.isEmpty(mapTodos) ? new ArrayList<Integer>() : mapTodos.stream().map(m -> m.getReferenceId()).collect(Collectors.toList());
	}
	
	public int getTodoId() {
		return todoId;
	}
	public void setTodoId(int todoId) {
		this.todoId = todoId;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public Long getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Long createdDate) {
		this.createdDate = createdDate;
	}
	public Long getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Long updatedDate) {
		this.updatedDate = updatedDate;
	}
	public Boolean getIsDone() {
		return isDone;
	}
	public void setIsDone(Boolean isDone) {
		this.isDone = isDone;
	}
}