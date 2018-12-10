/**
 * 
 */
package com.homework.todolist.model.pojo;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Incheol Jung
 */
public class SaveTodoParameter {
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
	
	private List<Integer> referenceIds;

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

	public List<Integer> getReferenceIds() {
		return referenceIds;
	}

	public void setReferenceIds(List<Integer> referenceIds) {
		this.referenceIds = referenceIds;
	}
}
