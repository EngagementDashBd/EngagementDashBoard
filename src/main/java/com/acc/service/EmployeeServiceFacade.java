package com.acc.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.acc.entity.EmployeeHub;
import com.acc.entity.EmployeeProject;
import com.acc.entity.Location;
import com.acc.entity.ResourceMaster;
import com.acc.entity.RoleName;

public interface EmployeeServiceFacade {
	public ArrayList<ResourceMaster> approve(long employeeId);
	public ArrayList<ResourceMaster> allEmployeeDetails();
	public int addNewEmployee(ResourceMaster resource, String password, String creatorName);
	public List<ResourceMaster> getEmployeeDetailsByProject(Integer projectId);
	public ArrayList<ResourceMaster> allSupervisorDetails();
	public int deleteEmployee(String enterpriseId);
	public int updateSupervisor(Long employeeId, Long supervisorId);
	public int updateEmployee(ResourceMaster resource,String creatorName);
	public int insertPersonaldetails(Long ContactNo,String PassportNo,String PanNo, String enterpriseId);
	public List<String> checkSupervisor(String Name, int careerLevel);
	public int recommendToHoldEmployee(Long employeeId, String potentialFutureRole,Date roleEndDate,String creator);
	public List<Location> getAllLocations();
	public int changePassword(ResourceMaster resource, String password);
	public ResourceMaster searchEmployee(String enterpriseId);
	public String getSupervisorName(Long supervisorId);
	public int holdOrRoleOff(Long employeeId, String potentialFutureRole, Date roleOffDate);
	public EmployeeHub getEmployeeOnHub(Long employeeId);
	public int updateContact(Long employeeId,Long contactNo);
	public Integer getEmpLocationIdByName(String locationName);
	public Integer uploadEmployeePicture(byte[] imageData, Long employeeId);
	public List<RoleName> getAllRoleNames();
	public String getEmployeeEntId(Long employeeId);
	public List<String> getRoles(Long employeeId);
}

