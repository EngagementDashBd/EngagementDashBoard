/*package com.acc.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.acc.dao.DaoFacade;
import com.acc.dao.DaoImplementation;
import com.acc.entity.CalendarData;
import com.acc.entity.CareerLevel;
import com.acc.entity.EmployeeHub;
import com.acc.entity.EmployeeProject;
import com.acc.entity.Portfolio;
import com.acc.entity.Program;
import com.acc.entity.Project;
import com.acc.entity.ProjectLocation;
import com.acc.entity.ResourceMaster;
import com.acc.entity.Technology;
@Service
public class ServiceImplementaion implements ServiceFacade{
	@Autowired
	DaoFacade dao;
	ResourceMaster resource = new ResourceMaster();
	@Transactional
	public ResourceMaster searchEmployee(String enterpriseId) {
		
		try{
			resource = dao.searchEmployee(enterpriseId);
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
	
	@Transactional
	public ResourceMaster loginEmployee(String enterpriseId, String password) {
		
		try{
			resource = dao.loginEmployee(enterpriseId,password);
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
	@Transactional
	public int signupEmployee(String enterpriseId, String password) {
		int count = 0;
		try{
			count = dao.signupEmployee(enterpriseId,password);
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
	public ArrayList<ResourceMaster> approve(Long employeeId) {
		ArrayList<ResourceMaster> employeeObjects = new ArrayList<ResourceMaster>();
		try{
			employeeObjects = dao.approve(employeeId);
			
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
	@Transactional
	public CalendarData getCalendarData(long employeeId, int fortnight, String month, int year) {
		CalendarData calendarData = new CalendarData();
		try{
			calendarData = dao.getCalendarData(employeeId, fortnight, month, year);
			
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return calendarData;
	}
	@Transactional
	public ArrayList<ResourceMaster> allEmployeeDetails() {
		ArrayList<ResourceMaster> allEmployeesData = new ArrayList<ResourceMaster>();
		try{
			allEmployeesData = dao.allEmployeeDetails();
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
	@Transactional
	public ArrayList<Integer> generateReport(String month, int year, int fortnight, long employeeId) {
		ArrayList<Integer> reportData = new ArrayList<Integer>();
		try{
			reportData = dao.generateReport(month, year, fortnight, employeeId);
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return reportData;
	}
	@Transactional
	public int addNewEmployee(ResourceMaster resource,EmployeeProject employeeProject, String creatorName) {
		int count = 0;
		try{
			count = dao.addNewEmployee(resource,creatorName);
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
	public int addNewProject(String projectName,String projectDescription,String creatorName,Integer programId) {
		int count = 0;
		try
		{
		 count = dao.addNewProject(projectName, projectDescription, creatorName,programId);
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
	public   Map<String,Integer> statistics() {
		 Map<String,Integer> shiftCount = new HashMap<String, Integer>();
		try
		{
			shiftCount = dao.statistics();
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return shiftCount;
	}
	@Transactional
	public List<Project> getAllProjects() {
		List<Project> allProjectsData = new ArrayList<Project>();
		try{
			allProjectsData = dao.getAllProjects();
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return allProjectsData;
	}
	@Transactional
	public List<ResourceMaster> getEmployeeDetailsByProject(Integer projectId) {
		List<ResourceMaster> employeeObjects = new ArrayList<ResourceMaster>();
		try{
			employeeObjects = dao.getEmployeeDetailsByProject(projectId);
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
	@Transactional
	public ArrayList<ResourceMaster> allSupervisorDetails()
	{
		ArrayList<ResourceMaster> supervisors = new ArrayList<ResourceMaster>();
		try{
			supervisors = dao.allSupervisorDetails();
			
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
			count = dao.deleteEmployee(enterpriseId);
			
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
	public String getSupervisorName(Long supervisorId) {
		
		 String supervisorName = null;
		try
		{
			supervisorName = dao.getSupervisorName(supervisorId);
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
	public String getProjectName(Integer projectId) {
		String projectName = null;
		try
		{
			projectName = dao.getProjectName(projectId);
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return projectName;
		
	}
	@Transactional
	public int updateSupervisor(Long employeeId, Long supervisorId) {
		int count = 0;
		try
		{
			count = dao.updateSupervisor(employeeId, supervisorId);
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
	public int addPortfolioDetails(Portfolio portfolio, String creatorName) {
		int count = 0;
		try
		{
			count = dao.addPortfolioDetails(portfolio,creatorName);
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
	public ArrayList<Portfolio> getPortfolioDetails()
	{
		ArrayList<Portfolio> portfolios = new ArrayList<Portfolio>();
		try{
			portfolios = dao.getPortfolioDetails();
			
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return portfolios;
	}
	@Transactional
	public int addProgram(Program program,String creatorName) {
		int count = 0;
		try{
			count = dao.addProgram(program,creatorName);
			
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
	public List<Program> getAllProjectPrograms() {
		List<Program> programs = new ArrayList<Program>();
		try{
			programs = dao.getAllProjectPrograms();
			
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return programs;
	
	}
	@Transactional
	public int updateEmployee(ResourceMaster resource,EmployeeProject employeeProject,String creatorName)
	{
		int count = 0;
		try{
			count = dao.updateEmployee(resource,employeeProject);
			
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
	public Portfolio getPortfolio(Integer portfolioId) {
		Portfolio portfolio = new Portfolio();
		try{
			portfolio = dao.getPortfolio(portfolioId);
			
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return portfolio;
	}
	@Transactional
	public int updatePortfolio(Portfolio portfolio) {
		int count = 0;
		try{
			count = dao.updatePortfolio(portfolio);
			
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
	public int deletePortfolio(Integer portfolioId) {
		int count = 0;
		try{
			count = dao.deletePortfolio(portfolioId);
			
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
	public List<Technology> getAllTechnology() {
		List<Technology> technologylist = new ArrayList<Technology>();
		try{
			technologylist = dao.getAllTechnology();
			
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return technologylist;
	}
	@Transactional
	public int deleteTechnology(Integer technologyId) {
		int count = 0;
		try{
			count = dao.deleteTechnology(technologyId);
			
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
	public int addTechnology(Technology technology) {
		int count = 0;
		try{
			count = dao.addTechnology(technology);
			
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
	public int deleteProgram(Integer programId) {
		int count = 0;
		try{
			count = dao.deleteProgram(programId);
			
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
	public Program getProgram(Integer programId) {
		Program program = new Program();
		try{
			program = dao.getProgram(programId);
			
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return program;
	}
	@Transactional
	public String getPortfolioName(Integer portfolioId) {
		String portfolioName = null;
		try
		{
			portfolioName = dao.getPortfolioName(portfolioId);
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return portfolioName;
	}
	@Transactional
	public int updateProgram(Program program) {
		int count = 0;
		try{
			count = dao.updateProgram(program);
			
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
	public int deleteProject(Integer id) {
		// TODO Auto-generated method stub
		int count = 0;
		try{
			count = dao.deleteProject(id);
			
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
	public int UpdateProjectDetails(Project project) {
		int count = 0;
		try
		{
			count = dao.UpdateProjectDetails(project);
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
	public Project getProject(Integer id) {
		// TODO Auto-generated method stubint count = 0;
		
		
			Project project=new Project();
			try{
				project = dao.getProject(id);
				
			}
			catch(ClassNotFoundException e)
			{
				e.printStackTrace();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			return project;
		}
	@Transactional
	public int insertPersonaldetails(Long ContactNo,String PassportNo,String PanNo,String enterpriseId){
		int count = 0;
		try
		{
			count = dao.insertPersonaldetails(ContactNo,PassportNo,PanNo,enterpriseId);
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
	public String getProgramName(Integer programId) {
		String programName = null;
		try
		{
			programName = dao.getProgramName(programId);
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return programName;
	}
	@Transactional
	public List<String> checkSupervisor(String Name,int careerLevel) {
		
		List<String> str = new ArrayList<String>();
		try{
			 str = dao.checkSupervisor(Name,careerLevel);
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
	
	@Transactional()
	public List<CareerLevel> getAllCareerLevels() {
		List<CareerLevel> allCareerLevels = new ArrayList<CareerLevel>();
		allCareerLevels = dao.getAllCareerLevels();
		return allCareerLevels;
	}
	@Transactional
	public int updateContact(Long employeeId, Long contactNo) {
		int count = 0;
		try{
			count = dao.updateContact(employeeId, contactNo);
					
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
	public String getTeamName(Integer projectId) {
		String teamName = dao.getTeamName(projectId);
		return teamName;
	}
	@Transactional
	public EmployeeProject getEmployeeProjectRoleDetails(Long employeeId) {
		EmployeeProject employeeProject=new EmployeeProject();
		try{
			employeeProject = dao.getEmployeeProjectRoleDetails(employeeId);
			
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return employeeProject;
	}
	@Transactional
	public EmployeeProject getEmployeeRoleOffDetails(Long employeeId) {
		EmployeeProject employeeProject=new EmployeeProject();
		try{
			employeeProject = dao.getEmployeeRoleOffDetails(employeeId);
			
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return employeeProject;
		
	}
	@Transactional
	public EmployeeHub getEmployeeOnHub(Long employeeId) {
		EmployeeHub employeeHub=new EmployeeHub();
		try{
			employeeHub = dao.getEmployeeOnHub(employeeId);
			
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

	@Transactional
	public List<CalendarData> getCalendarData(long employeeId, String startDate, String endDate) {
	
		List<CalendarData> calendarDataList = dao.getCalendarData(employeeId, startDate, endDate);
		
		return calendarDataList;
	}
	@Transactional
	public List<ProjectLocation> getAllProjectLocationDetails() {
		List<ProjectLocation> projectLocationDetails = dao.getAllProjectLocationDetails();
		return projectLocationDetails;
	}
	@Transactional
	public int assignProject(EmployeeProject employeeProject,String creatorName,Integer locationId) {
		int count = 0;
		count = dao.assignProject(employeeProject,creatorName,locationId);
		return count;
	}

}


*/