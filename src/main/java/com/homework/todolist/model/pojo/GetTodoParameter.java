/**
 * 
 */
package com.homework.todolist.model.pojo;

import java.util.Date;

/**
 * @author Incheol Jung
 */
public class GetTodoParameter {
	private String task;
	private Date createdOn;
	private Date updatedOn;
	
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
}