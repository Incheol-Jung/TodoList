/**
 * 
 */
package com.homework.todolist.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Incheol Jung
 */
@Entity
public class Todo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String task;
	private Long createdOn;
	private Long updatedOn;
	private Boolean isDone;
	
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