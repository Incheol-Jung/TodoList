/**
 * 
 */
package com.homework.todolist.model.pojo;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;

/**
 * @author Incheol Jung
 */
public class GetTodoParameter {
	private String task;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) 
	private Date createdOn;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) 
	private Date updatedOn;
	private Integer pageSize;
	private Integer pageNumber;
	
	public String getTask() {
		return task == null ? "" : task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public Date getCreatedOn() {
//		return StringUtils.isEmpty(createdOn) ? null : Date.valueOf(createdOn);
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public Date getUpdatedOn() {
//		return StringUtils.isEmpty(updatedOn) ? null : Date.valueOf(updatedOn);
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
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