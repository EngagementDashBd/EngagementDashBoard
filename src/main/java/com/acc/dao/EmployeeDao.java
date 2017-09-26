package com.acc.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.acc.entity.EmployeeHub;
import com.acc.entity.Location;
import com.acc.entity.ResourceMaster;
import com.acc.entity.RoleName;

public interface EmployeeDao {
	public ResourceMaster searchEmployee(String enterpriseId) throws ClassNotFoundException, SQLException;
	//public int signupEmployee(String enterpriseId, String password) throws ClassNotFoundException, SQLException;
	public ArrayList<ResourceMaster> allEmployeeDetails()throws ClassNotFoundException,SQLException;
	public int addNewEmployee(ResourceMaster resource, String creatorName)throws ClassNotFoundException,SQLException;
//	public List<ResourceMaster> getEmployeeDetailsByProject(Integer projectId)throws ClassNotFoundException,SQLException;
	public ArrayList<ResourceMaster> allSupervisorDetails()throws ClassNotFoundException,SQLException;
	public int deleteEmployee(String enterpriseId)throws ClassNotFoundException,SQLException;
	public int updateEmployee(ResourceMaster resource,String creatorName)throws ClassNotFoundException,SQLException;
	public ArrayList<ResourceMaster> approve(Long employeeId)throws ClassNotFoundException,SQLException;
	public List<ResourceMaster> getEmployeeDetailsByProject(Integer projectId)throws ClassNotFoundException,SQLException;
	public int insertPersonaldetails(Long ContactNo,String PassportNo,String PanNo, String enterpriseId)throws ClassNotFoundException, SQLException;
	public int recommendToHoldEmployee(Long employeeId, String potentialFutureRole, Date roleEndDate,String creator)throws ClassNotFoundException, SQLException;
	public int changePassword(ResourceMaster resource, String password);
	public List<Location> getAllLocations();
	public List<String> checkSupervisor(String Name, int careerLevel) throws ClassNotFoundException, SQLException;
	public int updateSupervisor(Long employeeId, Long supervisorId) throws ClassNotFoundException, SQLException;
	public String getSupervisorName(Long supervisorId) throws ClassNotFoundException, SQLException;
	public int holdOrRoleOff(Long employeeId, String potentialFutureRole, Date roleOffDate);
	public int updateContact(Long employeeId,Long contactNo)throws ClassNotFoundException, SQLException;
	public EmployeeHub getEmployeeOnHub(Long employeeId)throws ClassNotFoundException, SQLException;
	public Integer getEmpLocationIdByName(String locationName);
	public Integer uploadEmployeePicture(byte[] imageData, Long employeeId);
	public List<RoleName> getAllRoleNames();
	public String getSupervisorEid(Long supervisorId);
	public List<String> getRoles(Long employeeId);
}
