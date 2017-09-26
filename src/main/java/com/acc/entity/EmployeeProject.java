package com.acc.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="employee_project")
public class EmployeeProject implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4848009996149139261L;
	@Column(name = "lan_id")
	String lanId;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	Integer id;
	@Column(name = "employee_id")
	Long employeeId;
	@Column(name = "project_id")
	Integer projectId;	
	@Column(name = "role_name")
    String roleName;
	@Column(name = "recommended_to_hold")
    Boolean recommendToHold;
	@Column(name = "created_by")
    String createdBy;
	@Column(name = "active")
	Boolean active;
	@Column(name = "role_start_date")
	Date roleStartDate;
	@Column(name = "role_end_date")
	Date roleEndDate;
	@Column(name = "lcr")
	float lcr;

	public String getLanId() {
		return lanId;
	}
	public void setLanId(String lanId) {
		this.lanId = lanId;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public Boolean getRecommendToHold() {
		return recommendToHold;
	}
	public void setRecommendToHold(Boolean recommendToHold) {
		this.recommendToHold = recommendToHold;
	}
	
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public Date getRoleStartDate() {
		return roleStartDate;
	}
	public void setRoleStartDate(Date roleStartDate) {
		this.roleStartDate = roleStartDate;
	}
	public Date getRoleEndDate() {
		return roleEndDate;
	}
	public void setRoleEndDate(Date roleEndDate) {
		this.roleEndDate = roleEndDate;
	}
	public float getLcr() {
		return lcr;
	}
	public void setLcr(float lcr) {
		this.lcr = lcr;
	}
	
	

	
}
