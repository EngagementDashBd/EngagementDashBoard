/*package com.acc.dao;


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
import com.acc.entity.Location;
import com.acc.entity.Portfolio;
import com.acc.entity.Program;
import com.acc.entity.Project;
import com.acc.entity.ProjectLocation;
import com.acc.entity.ResourceMaster;
import com.acc.entity.Technology;

public interface DaoFacade {
	public ResourceMaster searchEmployee(String enterpriseId) throws ClassNotFoundException, SQLException;
	public ResourceMaster loginEmployee(String enterpriseId,String password) throws ClassNotFoundException, SQLException;
	public int signupEmployee(String enterpriseId, String password) throws ClassNotFoundException, SQLException;
	public int calendarDataStore(Long employeeId, int year, String month, Map<Integer, String>  shiftData, String flag)throws ClassNotFoundException,SQLException;
	public ArrayList<ResourceMaster> approve(Long employeeId)throws ClassNotFoundException,SQLException;
	public CalendarData getCalendarData(Long employeeId, int fortnight, String month, int year)throws ClassNotFoundException,SQLException;
	public List<CalendarData> getCalendarData(Long employeeId,String startDate,String endDate);
	public ArrayList<ResourceMaster> allEmployeeDetails()throws ClassNotFoundException,SQLException;
	public ArrayList<Integer> generateReport(String month, int year, int fortnight, long employeeId)throws ClassNotFoundException,SQLException;
	public int addNewEmployee(ResourceMaster resource, String creatorName)throws ClassNotFoundException,SQLException;
	public int addNewProject(String projectName,String projectDescription,String creatorName,Integer programId)throws ClassNotFoundException,SQLException;
	public  Map<String,Integer> statistics()throws ClassNotFoundException,SQLException;
	public List<Project> getAllProjects()throws ClassNotFoundException,SQLException;
	public List<ResourceMaster> getEmployeeDetailsByProject(Integer projectId)throws ClassNotFoundException,SQLException;
	public ArrayList<ResourceMaster> allSupervisorDetails()throws ClassNotFoundException,SQLException;
	public int approveTimesheet(Long employeeId, int fortnight, String month, int year)throws ClassNotFoundException,SQLException;
	public int rejectTimesheet(Long employeeId, int fortnight, String month, int year)throws ClassNotFoundException,SQLException;
	public int deleteEmployee(String enterpriseId)throws ClassNotFoundException,SQLException;
	public String getSupervisorName(Long supervisorId) throws ClassNotFoundException, SQLException;
	public String getProjectName(Integer projectId)throws ClassNotFoundException, SQLException;
	public int updateSupervisor(Long employeeId, Long supervisorId)throws ClassNotFoundException, SQLException;
	public int addPortfolioDetails(Portfolio portfolio, String creatorName)throws ClassNotFoundException, SQLException;
	public ArrayList<Portfolio> getPortfolioDetails()throws ClassNotFoundException,SQLException;
	public int addProgram(Program program,String creatorName)throws ClassNotFoundException,SQLException;
	public List<Program> getAllProjectPrograms()throws ClassNotFoundException,SQLException;
	public int updateEmployee(ResourceMaster resource,String creatorName)throws ClassNotFoundException,SQLException;
	public Portfolio getPortfolio(Integer portfolioId)throws ClassNotFoundException,SQLException;
	public int updatePortfolio(Portfolio portfolio)throws ClassNotFoundException,SQLException;
	public int deletePortfolio(Integer portfolioId)throws ClassNotFoundException,SQLException;
	public List<Technology> getAllTechnology()throws ClassNotFoundException,SQLException;
	public int deleteTechnology(Integer technologyId)throws ClassNotFoundException,SQLException;
	public int addTechnology(Technology technology)throws ClassNotFoundException,SQLException;
	public int deleteProgram(Integer programId)throws ClassNotFoundException,SQLException;
	public Program getProgram(Integer programId)throws ClassNotFoundException,SQLException;
	public String getProgramName(Integer programId)throws ClassNotFoundException, SQLException;
	public String getPortfolioName(Integer portfolioId)throws ClassNotFoundException, SQLException;
	public int updateProgram(Program program)throws ClassNotFoundException, SQLException;
	public int deleteProject(Integer id)throws ClassNotFoundException, SQLException;
	public int UpdateProjectDetails(Project project) throws ClassNotFoundException, SQLException;
	public Project getProject(Integer  id)  throws ClassNotFoundException, SQLException;
	public int insertPersonaldetails(Long ContactNo,String PassportNo,String PanNo, String enterpriseId)throws ClassNotFoundException, SQLException;
	public List<String> checkSupervisor(String Name,int careerLevel) throws ClassNotFoundException, SQLException;
	public List<CareerLevel> getAllCareerLevels();
	public int updateContact(Long employeeId,Long contactNo)throws ClassNotFoundException, SQLException;
	public String getTeamName(Integer projectId);
	public EmployeeProject getEmployeeProjectRoleDetails(Long employeeId)throws ClassNotFoundException, SQLException;
	public int recommendToHoldEmployee(Long employeeId, String potentialFutureRole, Date roleEndDate,String creator)throws ClassNotFoundException, SQLException;
	public EmployeeProject getEmployeeRoleOffDetails(Long employeeId)throws ClassNotFoundException, SQLException;
	public EmployeeHub getEmployeeOnHub(Long employeeId)throws ClassNotFoundException, SQLException;
	public List<ProjectLocation> getAllProjectLocationDetails(); 
	public int assignProject(EmployeeProject employeeProject,String creatorName,Integer locationId);
	public List<Location> getAllLocations();
	public int changePassword(ResourceMaster resource, String password);
}
*/