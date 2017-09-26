package com.acc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "projects")
public class Project implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3037067863325724661L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "project_id")
	Integer id;	
	@Column(name = "project_name")
	String projectName;
	@Column(name = "description")
	String projectDescription;
	@Column(name = "program_id")
	Integer programId;	
	@Column(name = "created_by")
	String createdBy;
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectDescription() {
		return projectDescription;
	}
	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProgramId() {
		return programId;
	}
	public void setProgramId(Integer programId) {
		this.programId = programId;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(insertable=false,updatable=false,name="program_id")
	private Program program;
	public Program getProgram() {
		return program;
	}
	public void setProgram(Program program) {
		this.program = program;
	}
	
	
	
}
