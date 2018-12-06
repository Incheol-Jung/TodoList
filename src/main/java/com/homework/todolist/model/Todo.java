/**
 * 
 */
package com.homework.todolist.model;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import io.swagger.annotations.ApiModelProperty;



/**
 * @author Incheol Jung
 */
@Entity
public class Todo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="todoId", referencedColumnName="todoId")
	private List<MapTodo> mapTodos;

	@ApiModelProperty(hidden=true)
	public List<Integer> getReferenceIds() {
		return mapTodos.stream().map(m -> m.getReferenceId()).collect(Collectors.toList());
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