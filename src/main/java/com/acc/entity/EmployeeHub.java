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
@Table(name="employee_hub")
public class EmployeeHub implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1874291180290141613L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
    Integer id;
	@Column(name = "employee_id")
	Long employeeId;
	@Column(name = "created_by")
    String createdBy;
	@Column(name = "hub_start_date")
	Date hubStartDate;
	@Column(name = "hub_end_date")
	Date hubEndDate;
	@Column(name = "Updated_by")
	String updatedBy;
	@Column(name = "potential_future_role")
	String potentialFutureRole;
	@Column(name = "active")
	Boolean active;
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
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
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getHubStartDate() {
		return hubStartDate;
	}
	public void setHubStartDate(Date hubStartDate) {
		this.hubStartDate = hubStartDate;
	}
	public Date getHubEndDate() {
		return hubEndDate;
	}
	public void setHubEndDate(Date hubEndDate) {
		this.hubEndDate = hubEndDate;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getPotentialFutureRole() {
		return potentialFutureRole;
	}
	public void setPotentialFutureRole(String potentialFutureRole) {
		this.potentialFutureRole = potentialFutureRole;
	}
	
	
}
