package com.acc.entity;

import java.io.Serializable;
import java.util.Iterator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "resourcetasks")
public class ResourceTaskAndVocation implements Iterable<ResourceTaskAndVocation>,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4118750066933442014L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ResourceTask_id")
	Integer R_ID;
	
	@Column(name = "WORK_id")
	Integer workId;
	
	@Column(name = "resource_Name")
	String resourceName;
	
	@Column(name = "task_Update")
	String taskUpdate;
	
	@Column(name = "vacation_Date_To")
	String vocationDateTo;
	
	@Column(name = "vacation_Date_From")
	String vocationDateFrom;



	public Integer getR_ID() {
		return R_ID;
	}
	public void setR_ID(Integer r_ID) {
		R_ID = r_ID;
	}
	public Integer getWorkId() {
		return workId;
	}
	public void setWorkId(Integer workId) {
		this.workId = workId;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getTaskUpdate() {
		return taskUpdate;
	}
	public void setTaskUpdate(String taskUpdate) {
		this.taskUpdate = taskUpdate;
	}
	public String getVocationDateTo() {
		return vocationDateTo;
	}
	public void setVocationDateTo(String vocationDateTo) {
		this.vocationDateTo = vocationDateTo;
	}
	public String getVocationDateFrom() {
		return vocationDateFrom;
	}
	public void setVocationDateFrom(String vocationDateFrom) {
		this.vocationDateFrom = vocationDateFrom;
	}
	public Iterator<ResourceTaskAndVocation> iterator() {
		// TODO -generated method stub
		return null;
	}
	
	

	

	}






