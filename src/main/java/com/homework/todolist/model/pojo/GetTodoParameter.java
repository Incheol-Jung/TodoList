/**
 * 
 */
package com.homework.todolist.model.pojo;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Incheol Jung
 */
public class GetTodoParameter {
	@ApiModelProperty(example="")
	private String task;
	@ApiModelProperty(example="1544055826168")
	private Long createdDate;
	@ApiModelProperty(example="1544055826168")
	private Long updatedDate;
	@ApiModelProperty(example="10")
	private Integer pageSize;
	@ApiModelProperty(example="1")
	private Integer pageIndex;
	
	public String getTask() {
		return task == null ? "" : task;
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
	public Integer getPageSize() {
		return (pageSize == null || pageSize<0) ? 10 : pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageIndex() {
		return (pageIndex==null || pageIndex<0) ? 0 : pageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
}