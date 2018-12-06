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
	private int id;
	
	@ApiModelProperty(example="task test 1")
	private String task;
	
	@ApiModelProperty(example="1544055826168")
	private Long createdOn;
	
	@ApiModelProperty(example="1544055826168")
	private Long updatedOn;
	
	@ApiModelProperty(example="false")
	private Boolean isDone;
	
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="taskId", referencedColumnName="id")
	private List<MapTodo> mapTodos;

	@ApiModelProperty(hidden=true)
	public List<Integer> getReferenceIds() {
		return mapTodos.stream().map(m -> m.getReferenceId()).collect(Collectors.toList());
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public Long getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Long createdOn) {
		this.createdOn = createdOn;
	}
	public Long getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Long updatedOn) {
		this.updatedOn = updatedOn;
	}
	public Boolean getIsDone() {
		return isDone;
	}
	public void setIsDone(Boolean isDone) {
		this.isDone = isDone;
	}
	
}