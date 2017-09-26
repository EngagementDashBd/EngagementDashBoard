/*package com.acc.service;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

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

public interface ServiceFacade {
	public ResourceMaster searchEmployee(String enterpriseId);
	public ResourceMaster loginEmployee(String enterpriseId,String password);
	public int signupEmployee(String enterpriseId, String password);
	public int calendarDataStore(long employeeId, int year, String month, Map<Integer, String> shiftData, String flag);
	//public ArrayList<ResourceMaster> approve(long employeeId);
	public CalendarData getCalendarData(long employeeId,int fortnight, String month, int year);
	public List<CalendarData> getCalendarData(long employeeId,String startDate,String endDate);
	//public ArrayList<ResourceMaster> allEmployeeDetails();
	public ArrayList<Integer> generateReport(String month, int year, int fortnight, long employeeId);
	//public int addNewEmployee(ResourceMaster resource, String creatorName);
	public int addNewProject(String projectName,String projectDescription,String creatorName,Integer programId);
	public   Map<String,Integer> statistics();
	public List<Project> getAllProjects();
	//public List<ResourceMaster> getEmployeeDetailsByProject(Integer projectId);
	//public ArrayList<ResourceMaster> allSupervisorDetails();
	public int approveTimesheet(Long employeeId, int fortnight, String month, int year);
	public int rejectTimesheet(Long employeeId, int fortnight, String month, int year);
	//public int deleteEmployee(String enterpriseId);
	public String getSupervisorName(Long supervisorId);
	public String getProjectName(Integer projectId);
	//public int updateSupervisor(Long employeeId, Long supervisorId);
	public int addPortfolioDetails(Portfolio portfolio, String creatorName);
	public ArrayList<Portfolio> getPortfolioDetails();
	public int addProgram(Program program,String creatorName);
	public List<Program> getAllProjectPrograms();
	//public int updateEmployee(ResourceMaster resource);
	public Portfolio getPortfolio(Integer portfolioId);
	public int updatePortfolio(Portfolio portfolio);
	public int deletePortfolio(Integer portfolioId);
	public List<Technology> getAllTechnology();
	public int deleteTechnology(Integer technologyId);
	public int addTechnology(Technology technology);
	public int deleteProgram(Integer programId);
	public Program getProgram(Integer programId);
	public String getProgramName(Integer programId);
	public String getPortfolioName(Integer portfolioId);
	public int updateProgram(Program program);
	public int deleteProject(Integer id);
	public int UpdateProjectDetails(Project project);
	public Project getProject(Integer id);
	//public int insertPersonaldetails(Long ContactNo,String PassportNo,String PanNo, String enterpriseId);
	//public List<String> checkSupervisor(String Name);
	public List<CareerLevel> getAllCareerLevels();
	public int updateContact(Long employeeId,Long contactNo);
	public String getTeamName(Integer projectId);
	public EmployeeProject getEmployeeProjectRoleDetails(Long employeeId);
	public EmployeeProject getEmployeeRoleOffDetails(Long employeeId);
	public EmployeeHub getEmployeeOnHub(Long employeeId);
	public List<ProjectLocation> getAllProjectLocationDetails();
	public int assignProject(EmployeeProject employeeProject,String creatorName,Integer locationId);
}
*/