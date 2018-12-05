/**
 * 
 */
package com.homework.todolist.model;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * @author Incheol Jung
 */
@Entity
public class Todo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String task;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedOn;
	
	private Boolean isDone;
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="taskId", referencedColumnName="id")
	private List<MapTodo> mapTodos;
	
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

	public Boolean getIsDone() {
		return isDone;
	}
	public void setIsDone(Boolean isDone) {
		this.isDone = isDone;
	}
	
}