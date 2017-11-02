package com.acc.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acc.dao.EmployeeDao;
import com.acc.entity.EmployeeHub;
import com.acc.entity.EmployeeProject;
import com.acc.entity.Location;
import com.acc.entity.ResourceMaster;
import com.acc.entity.RoleName;
@Service
public class EmployeeServiceImplementation implements EmployeeServiceFacade {
	@Autowired
	EmployeeDao empdao;
	
	ResourceMaster resource = new ResourceMaster();
	@Transactional(readOnly=true)
	public ArrayList<ResourceMaster> approve(long employeeId) {
		ArrayList<ResourceMaster> employeeObjects = new ArrayList<ResourceMaster>();
		try{
			employeeObjects = empdao.approve(employeeId);
			
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return employeeObjects;
	}
	@Transactional(readOnly=true)
	public ArrayList<ResourceMaster> allEmployeeDetails() {
		ArrayList<ResourceMaster> allEmployeesData = new ArrayList<ResourceMaster>();
		System.out.println("AllEmpDetails");
		try{
			allEmployeesData = empdao.allEmployeeDetails();
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return allEmployeesData;
	}
	
	@Transactional(readOnly=true)
	public int addNewEmployee(ResourceMaster resource, String creatorName) {
		int count = 0;
		try{
			count = empdao.addNewEmployee(resource,creatorName);
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return count;
	}
	
	@Transactional(readOnly=true)
	public List<ResourceMaster> getEmployeeDetailsByProject(Integer projectId) {
		List<ResourceMaster> employeeObjects = new ArrayList<ResourceMaster>();
		try{
			employeeObjects = empdao.getEmployeeDetailsByProject(projectId);
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return employeeObjects;
	}
	
	@Transactional(readOnly=true)
	public ArrayList<ResourceMaster> allSupervisorDetails()
	{
		System.out.println("service");
		ArrayList<ResourceMaster> supervisors = new ArrayList<ResourceMaster>();
		try{
			supervisors = empdao.allSupervisorDetails();
			
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return supervisors;
	}
	
	@Transactional
	public int deleteEmployee(String enterpriseId) {
		// TODO Auto-generated method stub
		int count = 0;
		try{
			count = empdao.deleteEmployee(enterpriseId);
			
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return count;
	}
	
	@Transactional
	public int updateSupervisor(Long employeeId, Long supervisorId) {
		int count = 0;
		try
		{
			count = empdao.updateSupervisor(employeeId, supervisorId);
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return count;
	}
	
	@Transactional
	public int updateEmployee(ResourceMaster resource,String creatorName)
	{
		int count = 0;
		try{
			count = empdao.updateEmployee(resource,creatorName);
			
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return count;
		
	}
	
	@Transactional
	public int insertPersonaldetails(Long ContactNo,String PassportNo,String PanNo,String enterpriseId){
		int count = 0;
		try
		{
			count =empdao.insertPersonaldetails(ContactNo,PassportNo,PanNo,enterpriseId);
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return count;
		
	}
	
	@Transactional(readOnly=true)
	public List<String> checkSupervisor(String Name, int careerLevel) {
		
		List<String> str = new ArrayList<String>();
		try{
			 str = empdao.checkSupervisor(Name,careerLevel);
			 System.out.println("Inside the checkSuper"+str);
			
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return str;
	}
	@Transactional
	public int recommendToHoldEmployee(Long employeeId, String potentialFutureRole,Date roleEndDate,String creator) {
		int count = 0;
		try
		{
			count =  empdao.recommendToHoldEmployee(employeeId, potentialFutureRole,roleEndDate,creator);
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return count;
		
	}
	@Transactional(readOnly=true)
	public List<Location> getAllLocations() {
		List<Location> locations =empdao.getAllLocations();
		return locations;
	}
	@Transactional
	public int changePassword(ResourceMaster resource, String password) {
		int count = empdao.changePassword(resource, password);
		return count;
	}
	@Transactional
	public ResourceMaster searchEmployee(String enterpriseId) {
		
		try{
			resource = empdao.searchEmployee(enterpriseId);
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return resource;
	}
	
	@Transactional(readOnly=true)
	public String getSupervisorName(Long supervisorId) {
		
		 String supervisorName = null;
		try
		{
			supervisorName = empdao.getSupervisorName(supervisorId);
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return supervisorName;
	}
	@Transactional
	public int holdOrRoleOff(Long employeeId, String potentialFutureRole, Date roleOffDate) {
		int count = 0;
		count = empdao.holdOrRoleOff(employeeId, potentialFutureRole, roleOffDate);
		return count;
	}

	
	@Transactional
	public int updateContact(Long employeeId, Long contactNo) {
		int count = 0;
		try{
			count =empdao.updateContact(employeeId, contactNo);
					
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return count;
		}
	@Transactional
	public EmployeeHub getEmployeeOnHub(Long employeeId) {
		EmployeeHub employeeHub=new EmployeeHub();
		try{
			employeeHub = empdao.getEmployeeOnHub(employeeId);
			
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return employeeHub;
		

	}
	@Transactional(readOnly=true)
	public Integer getEmpLocationIdByName(String locationName) {
		Integer locationId = null;
		locationId = empdao.getEmpLocationIdByName(locationName);
		return locationId;
	}
	@Transactional
	public Integer uploadEmployeePicture(byte[] imageData, Long employeeId) {
		int count = 0;
		count = empdao.uploadEmployeePicture(imageData, employeeId);
		return count;
	}
	@Transactional(readOnly=true)
	public List<RoleName> getAllRoleNames() {
		List<RoleName> roleNames = empdao.getAllRoleNames();		
		return roleNames;
	}
	@Transactional
	public String getEmployeeEntId(Long employeeId) {
		 String employeeEntId = null;
		 employeeEntId = empdao.getEmployeeEntId(employeeId);
		 return employeeEntId;
	}
	@Transactional
	public List<String> getRoles(Long employeeId) {
		List<String> roles = empdao.getRoles(employeeId);
		return roles;
	}

}
