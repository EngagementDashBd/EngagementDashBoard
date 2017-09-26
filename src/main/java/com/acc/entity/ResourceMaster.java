package com.acc.entity;



import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="employee")
@NamedQueries({@NamedQuery(name="getUser",query="select r from ResourceMaster r where r.enterpriseId=:enterpriseId")})


public class ResourceMaster implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -352327355158266595L;
	@Id
	@Column(name = "employee_id")
	private Long employeeId;
	@Column(name = "enterprise_id")
	private String enterpriseId;
	@Column(name = "employee_name")
	private String employeeName;
	@Column(name = "password")
	private String password;
	@Column(name= "location_id")
	private Integer locationId;
	
	@Column(name = "career_level")
	private Integer level;
	@Column(name = "supervisor_id")
	private Long supervisorId;
	@Column(name = "default_password")
	private Boolean defaultPassword;
	
	
	@Transient
	private String supervisorEnterpriseId;
	
	@Column(name = "technology_id")
	private Integer technologyId;
	@Column(name = "default_shift")
	private String defaultShift;
	
	@Column(name = "created_by")
	private String createdBy;
	@Column(name = "project_id")
	private Integer projectId;
	@Column(name = "active")
	private Boolean active;
	@Column(name = "pan_no")
	private String panNo;
	@Column(name = "passport_no")
	private String passportNo;
	@Column(name = "contact_no")
	private Long contactNo;
	@Transient
	private String[] roles;
	@Column(name = "role_on_date")
	private Date roleOnDate;
	@Column(name = "role_off_date")
	private Date roleOffDate;
	@Column(name = "employee_status")
	private String employeeStatus;
	@Column(name = "hcsc_mail_id")
	private String hcscMailId;
	@Column(name = "employee_photo")
	private byte[] employeePhoto;
	@Transient
	private String base64Jmage;
	
	public String getBase64Jmage() {
		return base64Jmage;
	}
	public void setBase64Jmage(String base64Jmage) {
		this.base64Jmage = base64Jmage;
	}
	public byte[] getEmployeePhoto() {
		return employeePhoto;
	}
	public void setEmployeePhoto(byte[] employeePhoto) {
		this.employeePhoto = employeePhoto;
	}
	public String getEmployeeStatus() {
		return employeeStatus;
	}
	public void setEmployeeStatus(String employeeStatus) {
		this.employeeStatus = employeeStatus;
	}
	public Date getRoleOffDate() {
		return roleOffDate;
	}
	public void setRoleOffDate(Date roleOffDate) {
		this.roleOffDate = roleOffDate;
	}
	public String[] getRoles() {
		return roles;
	}
	public void setRoles(String[] roles) {
		this.roles = roles;
	}
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getDefaultPassword() {
		return defaultPassword;
	}
	public void setDefaultPassword(Boolean defaultPassword) {
		this.defaultPassword = defaultPassword;
	}
	public Integer getLocationId() {
		return locationId;
	}
	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}
	public String getSupervisorEnterpriseId() {
		return supervisorEnterpriseId;
	}
	public void setSupervisorEnterpriseId(String supervisorEnterpriseId) {
		this.supervisorEnterpriseId = supervisorEnterpriseId;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Long getSupervisorId() {
		return supervisorId;
	}
	public void setSupervisorId(Long supervisorId) {
		this.supervisorId = supervisorId;
	}
	
	public Integer getTechnologyId() {
		return technologyId;
	}
	public void setTechnologyId(Integer technologyId) {
		this.technologyId = technologyId;
	}
	public String getDefaultShift() {
		return defaultShift;
	}
	public void setDefaultShift(String defaultShift) {
		this.defaultShift = defaultShift;
	}
	public String getPanNo() {
		return panNo;
	}
	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}
	public String getPassportNo() {
		return passportNo;
	}
	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}
	public Long getContactNo() {
		return contactNo;
	}
	public void setContactNo(Long contactNo) {
		this.contactNo = contactNo;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	
	public Date getRoleOnDate() {
		return roleOnDate;
	}
	public void setRoleOnDate(Date roleOnDate) {
		this.roleOnDate = roleOnDate;
	}
	public String getHcscMailId() {
		return hcscMailId;
	}
	public void setHcscMailId(String hcscMailId) {
		this.hcscMailId = hcscMailId;
	}


	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(insertable=false,updatable=false,name="career_level")
	private CareerLevel careerLevel;
	public CareerLevel getCareerLevel() {
		return careerLevel;
	}
	public void setCareerLevel(CareerLevel careerLevel) {
		this.careerLevel = careerLevel;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(insertable=false,updatable=false,name="project_id")
	private Project project;
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(insertable=false,updatable=false,name="location_id")
	private Location location;
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(insertable=false,updatable=false,name="technology_id")
	private Technology technology;
	public Technology getTechnology() {
		return technology;
	}
	public void setTechnology(Technology technology) {
		this.technology = technology;
	}
	
	

	}


