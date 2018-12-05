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
	private Long createdOn;
	private Long updatedOn;
	private Integer pageSize;
	private Integer pageNumber;
	
	public String getTask() {
		return task == null ? "" : task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public Date getCreatedOn() {
		return new Date(createdOn);
	}
	public void setCreatedOn(Long createdOn) {
		this.createdOn = createdOn;
	}
	public Date getUpdatedOn() {
		return new Date(updatedOn);
	}
	public void setUpdatedOn(Long updatedOn) {
		this.updatedOn = updatedOn;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
}