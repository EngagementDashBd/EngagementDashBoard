/*package com.acc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.ModelAndView;

import com.acc.constants.Months;
import com.acc.entity.Authority;
import com.acc.entity.CalendarData;
import com.acc.entity.CareerLevel;
import com.acc.entity.DayData;
import com.acc.entity.EmployeeHub;
import com.acc.entity.EmployeeProject;
import com.acc.entity.Location;
import com.acc.entity.Portfolio;
import com.acc.entity.Program;
import com.acc.entity.Project;
import com.acc.entity.ProjectLocation;
//import com.acc.entity.Project;
import com.acc.entity.ResourceMaster;
import com.acc.entity.Technology;
import com.acc.entity.Timesheet;
import com.acc.helper.DatabaseConnection;
@Repository
public class DaoImplementation extends AbstractDao implements DaoFacade {
	
	
	PreparedStatement statement = null;
	public ResourceMaster searchEmployee(String enterpriseId) throws ClassNotFoundException, SQLException {
		
		Session session=getSession();
		Query query=session.createQuery("select e from  ResourceMaster e where e.enterpriseId=:entId and e.active=:active");
		query.setParameter("active", true);
		query.setParameter("entId", enterpriseId);
		List<ResourceMaster> empList=query.list();
		ResourceMaster resource = new ResourceMaster();

		for(ResourceMaster resources:empList)
		{
			resource = resources;
		}
		return resource;
	}
	public ResourceMaster loginEmployee(String enterpriseId, String password)
			throws ClassNotFoundException, SQLException {
		
		Session session=getSession();
		Query query=session.createQuery("select e from  ResourceMaster e where e.enterpriseId=:entId and e.password=:pass and e.active=:active");
		query.setParameter("entId", enterpriseId);
		query.setParameter("pass",password);
		query.setParameter("active", true);
		ResourceMaster resource = new ResourceMaster();
		List<ResourceMaster> empList=query.list();
		for(ResourceMaster resources:empList)
		{
			resource = resources;
		}
		return resource;
	}
	public int signupEmployee(String enterpriseId, String password) throws ClassNotFoundException, SQLException {
		int count = 0;
		Session session=getSession();Query query=session.createQuery("update ResourceMaster r set r.password=:password where r.enterpriseId=:enterpriseid");
		query.setParameter("enterpriseid", enterpriseId);
		query.setParameter("password",password);
		count = query.executeUpdate();
		
		return count;
	}

	public ArrayList<ResourceMaster> approve(Long employeeId) throws ClassNotFoundException, SQLException {
		ArrayList<ResourceMaster> employeeObjects = new ArrayList<ResourceMaster>();
		Session session=getSession();
		Query query=session.createQuery("select e from  ResourceMaster e where e.supervisorId=:supId and e.active=:active");
		query.setParameter("supId", employeeId);
		query.setParameter("active", true);
		List<ResourceMaster> empList=query.list();
		for(ResourceMaster resource:empList)
		{
			employeeObjects.add(resource);
		}
		
		return employeeObjects;
	}
	public CalendarData getCalendarData(Long employeeId, int fortnight, String month, int year) throws ClassNotFoundException, SQLException {
		CalendarData calendarData = new CalendarData();
		calendarData.setEmployeeId(employeeId);
		calendarData.setMonth(month);
		calendarData.setYear(year);
		List<DayData> dayData = new ArrayList<DayData>();
		Session session=getSession();
			Query query=session.createQuery("select e from  Timesheet e where employeeId=:empId and month=:month and year=:year");
			query.setParameter("empId", employeeId);
			query.setParameter("month", month);
			query.setParameter("year", year);
			List<Timesheet> timesheet=query.list();
			for(Timesheet time : timesheet)
			{
				if(fortnight == 1 && time.getDate() <= 15 || fortnight == 2 && time.getDate() > 15 || fortnight == 3)
				{
					DayData day_Data = new DayData();
					day_Data.setDate(time.getDate());
					day_Data.setShift(time.getShift());
					dayData.add(day_Data);
					System.out.println("fortnight :"+fortnight );
					System.out.println("Date " + time.getDate());
				}
			}
			
		
		calendarData.setDayData(dayData);
		return calendarData;
	}
	public ArrayList<ResourceMaster> allEmployeeDetails() throws ClassNotFoundException, SQLException {
		ArrayList<ResourceMaster> allEmployeesData = new ArrayList<ResourceMaster>();
		Session session=getSession();
		Query query=session.createQuery("select e from  ResourceMaster e where e.active=:active");
		query.setParameter("active", true);
		List<ResourceMaster> empList=query.list();
		for(ResourceMaster resource:empList)
		{
			allEmployeesData.add(resource);
		}
		return allEmployeesData;
	}
	public ArrayList<Integer> generateReport(String month, int year, int fortnight, long employeeId) throws ClassNotFoundException, SQLException {
		ArrayList<Integer> reportData = new ArrayList<Integer>();
		int aCount = 0, bCount = 0, cCount = 0;
		String shift="";
		Session session=getSession();
		Query query = null;
		
		if(fortnight == 1)
		{
			query = session.createQuery("select e from  Timesheet e where employeeId=:empid and month=:month and year=:year and (date between 1 and 15)");
		}
		else if(fortnight == 2)
		{
			query = session.createQuery("select e from  Timesheet e where employeeId=:empid and month=:month and year=:year and (date > 15)");
		}
		else if(fortnight == 3)
		{
			query=session.createQuery("select e from  Timesheet e where employeeId=:empid and month=:month and year=:year");
		}
		query.setParameter("empid", employeeId);
		query.setParameter("month", month);
		query.setParameter("year", year);
		List<Timesheet> empList=query.list();
		for(Timesheet shiftData : empList)
		{
			shift = shiftData.getShift();
			if("a".equals(shift))
				aCount++;
			if("b".equals(shift))
				bCount++;
			if("c".equals(shift))
				cCount++;				
		}
		reportData.add(aCount);
		reportData.add(bCount);
		reportData.add(cCount);
		return reportData;
	}
	public int addNewEmployee(ResourceMaster resource, String creatorName) throws ClassNotFoundException, SQLException {
		Session session=getSession();
		int count = 0;
		Query query = session.createQuery("select e from ResourceMaster e where e.employeeId=:empId or e.enterpriseId=:enterpriseId and e.active=:active");
		query.setParameter("empId", resource.getEmployeeId());
		query.setParameter("enterpriseId", resource.getEnterpriseId());
		query.setParameter("active", true);
		List<ResourceMaster> empList=query.list();
		if(empList.isEmpty())
		{
			String[] roles = resource.getRoles();
			
			ResourceMaster resourceMaster = new ResourceMaster();
			resourceMaster.setEmployeeId(resource.getEmployeeId());
			resourceMaster.setEnterpriseId(resource.getEnterpriseId());
			resourceMaster.setEmployeeName(resource.getEmployeeName());
			resourceMaster.setPassword("login");
			resourceMaster.setLocationId(resource.getLocationId());
			resourceMaster.setLevel(resource.getLevel());
			resourceMaster.setSupervisorId(resource.getSupervisorId());
			resourceMaster.setTechnologyId(resource.getTechnologyId());
			resourceMaster.setDefaultShift("a");
			resourceMaster.setCreatedBy(creatorName);
			resourceMaster.setProjectId(resource.getProjectId());
			resourceMaster.setActive(true);
			resourceMaster.setDefaultPassword(true);
			session.save(resourceMaster);
			for(int i = 0; i < roles.length; i++)
			{
				Authority authority = new Authority();
				authority.setEmployeeId(resource.getEmployeeId().toString());
				authority.setAuthority(roles[i]);
				session.save(authority);
			}
			if(resourceMaster.getProjectId() != null)
			{
			EmployeeProject empProject = new EmployeeProject();
			empProject.setEmployeeId(resource.getEmployeeId());
			empProject.setCreatedBy(creatorName);
			empProject.setLanId(employeeProject.getLanId());
			empProject.setRoleName(employeeProject.getRoleName());
			empProject.setRoleEndDate(employeeProject.getRoleEndDate());
			empProject.setRoleStartDate(employeeProject.getRoleStartDate());
			empProject.setActive(true);
			empProject.setRecommendToHold(false);
			empProject.setProjectId(resource.getProjectId());
			empProject.setLcr(employeeProject.getLcr());
			
			session.save(empProject);
			}
			count = 1;
		}
		return count;
	}
	
	public int addNewProject(String projectName, String projectDescription, String creatorName,Integer programId)throws ClassNotFoundException, SQLException {
		Session session=getSession();
		int count = 0;
		Query query=session.createQuery("select e from  Project e where e.projectName=:projectName");
		query.setParameter("projectName", projectName);
		List<Project> ProjectList=query.list();
		if(ProjectList.isEmpty())
		{	
			Project project = new Project();
			project.setProjectName(projectName);
			project.setProjectDescription(projectDescription);
			project.setCreatedBy(creatorName);
			project.setProgramId(programId);
			session.save(project);
			count = 1;
		}
		return count;
		
	}
	public Map<String, Integer> statistics() throws ClassNotFoundException, SQLException {
		  String[] monthName = { "january", "february", "march", "april", "may", "june", "july",
			        "august", "september", "october", "november", "december" };
		  int aCount = 0, bCount = 0, cCount = 0;
		  String shift=null;
		  Calendar cal = Calendar.getInstance();
		  String month = monthName[cal.get(Calendar.MONTH)];
		  int year = cal.get(Calendar.YEAR);
		  Session session=getSession();
		  Query query=session.createQuery("select e from  Timesheet e where month=:month and year=:year");
		  query.setParameter("month", month);
		  query.setParameter("year", year);
		  List<Timesheet> empList=query.list();
		  for(Timesheet shiftData : empList)
		  {
				shift = shiftData.getShift();
				if("a".equals(shift))
					aCount++;
				if("b".equals(shift))
					bCount++;
				if("c".equals(shift))
					cCount++;				
		  }
		  Map<String,Integer> shiftCount = new HashMap<String, Integer>();	  
		  shiftCount.put("shiftA", aCount);
		  shiftCount.put("shiftB", bCount);
		  shiftCount.put("shiftC", cCount);
		return shiftCount;
	}
	public List<Project> getAllProjects() throws ClassNotFoundException, SQLException {
		 Session session=getSession();
		  Query query=session.createQuery("select e from Project e ");
		  List<Project> allProjectsData = query.list();
		return allProjectsData;
	}
	public List<ResourceMaster> getEmployeeDetailsByProject(Integer projectId) throws ClassNotFoundException, SQLException {
		Session session=getSession();
		List<ResourceMaster> employeeObjects = new ArrayList<ResourceMaster>();
		Query query=session.createQuery("select e from ResourceMaster e where projectId=:projId and e.active=:active");
		 query.setParameter("projId", projectId);
		 query.setParameter("active", true);
		 employeeObjects = query.list();
		return employeeObjects;
	}
	public ArrayList<ResourceMaster> allSupervisorDetails()throws ClassNotFoundException,SQLException
	{		Session session=getSession();
	System.out.println("dao");
		ArrayList<ResourceMaster> supervisors = new ArrayList<ResourceMaster>();
		//Query query=session.createQuery("select e from  ResourceMaster e where e.designation=:tl or e.designation=:am or e.designation=:m and e.active=:active ");
		Query query=session.createQuery("select e from  ResourceMaster e where e.careerLevel>=9 and e.active=:active ");
		//query.setParameter("tl", "TL");
		//query.setParameter("am", "AM");
		//query.setParameter("m", "M");
		query.setParameter("active", true);
		List<ResourceMaster> supervisorList=query.list();
		for(ResourceMaster resource:supervisorList)
		{
			supervisors.add(resource);
		}
		
		return supervisors;
	}
	public int approveTimesheet(Long employeeId, int fortnight, String month, int year) throws ClassNotFoundException, SQLException {
		int count = 0;
		Session session=getSession();
		Query query = null;
		if(fortnight == 1)
		{
			query = session.createQuery("update Timesheet t set t.status=:status where t.employeeId=:employeeId and t.month=:month and t.year=:year and (t.date between 1 and 15)");
		}
		else if(fortnight == 2)
		{
			query = session.createQuery("update Timesheet t set t.status=:status where t.employeeId=:employeeId and t.month=:month and t.year=:year and (t.date > 15)");
		}
		query.setParameter("status", "approved");
		query.setParameter("employeeId", employeeId);
		query.setParameter("month", month);
		query.setParameter("year", year);
		count = query.executeUpdate();
		return count;
	}
	public int rejectTimesheet(Long employeeId, int fortnight, String month, int year) throws ClassNotFoundException, SQLException {
		int count = 0;
		Session session=getSession();
		Query query = null;
		if(fortnight == 1)
		{
			query = session.createQuery("update Timesheet t set t.status=:status where t.employeeId=:employeeId and t.month=:month and t.year=:year and (t.date between 1 and 15)");
		}
		else if(fortnight == 2)
		{
			query = session.createQuery("update Timesheet t set t.status=:status where t.employeeId=:employeeId and t.month=:month and t.year=:year and (t.date > 15)");
		}
		query.setParameter("status", "rejected");
		query.setParameter("employeeId", employeeId);
		query.setParameter("month", month);
		query.setParameter("year", year);
		count = query.executeUpdate();
		return count;
	}

	public int deleteEmployee(String enterpriseId) throws ClassNotFoundException, SQLException {
		Session session=getSession();
		int count = 0;
		Query query=session.createQuery("update ResourceMaster e set e.active=:active where e.enterpriseId=:enterpriseId  ");
		query.setParameter("enterpriseId", enterpriseId);
		query.setParameter("active", false);
		count = query.executeUpdate();
		return count;
	} 
	public String getSupervisorName(Long supervisorId) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		String supervisorName="";
		List<ResourceMaster> employee = new ArrayList<ResourceMaster>();
		Session session=getSession();
		Query query=session.createQuery("select e from  ResourceMaster e where e.employeeId=:employeeId and e.active=:active");
		query.setParameter("employeeId",supervisorId);
		query.setParameter("active", true);
		employee = query.list();
		for(ResourceMaster resource:employee)
		{
			supervisorName = resource.getEmployeeName();
			System.out.println(supervisorName);
		}
		
		
		return supervisorName;
	}
	public String getProjectName(Integer projectId) throws ClassNotFoundException, SQLException {
		String projectName="";
		List<Project> projects = new ArrayList<Project>();
		Session session=getSession();
		Query query=session.createQuery("select e from  Project e where e.id=:projectId ");
		query.setParameter("projectId",projectId);
		projects = query.list();
		for(Project project:projects)
		{
			projectName = project.getProjectName();
			System.out.println(projectName);
		}
		
		
		return projectName;
	
	}
	public int updateSupervisor(Long employeeId, Long supervisorId) throws ClassNotFoundException, SQLException {
		int count = 0;
		Session session = getSession();
		Query query=session.createQuery("update ResourceMaster r set r.supervisorId=:supervisorId where r.employeeId=:employeeId");
		query.setParameter("supervisorId", supervisorId);
		query.setParameter("employeeId",employeeId);
		count = query.executeUpdate();
		return count;
	}
	public int addPortfolioDetails(Portfolio portfolio,String creatorName) throws ClassNotFoundException, SQLException {
		int count = 0;
		Session session = getSession();
		String portfolioName = portfolio.getPortfolioName();
		Query query=session.createQuery("select e from  Portfolio e where e.portfolioName=:portfolioName");
		query.setParameter("portfolioName", portfolioName);
		List<Portfolio> portfolioList = new ArrayList<Portfolio>();
		portfolioList = query.list();
		if(portfolioList.isEmpty())
		{
			Portfolio newPortfolio = new Portfolio();
			newPortfolio.setPortfolioName(portfolio.getPortfolioName());
			newPortfolio.setPortfolioDescription(portfolio.getPortfolioDescription());
		
			newPortfolio.setCreatedBy(creatorName);
			session.save(newPortfolio);
			count=1;
		}
		
		return count;
	}
	public ArrayList<Portfolio> getPortfolioDetails() throws ClassNotFoundException, SQLException {
		Session session=getSession();
		ArrayList<Portfolio> portfolios = new ArrayList<Portfolio>();
		  Query query=session.createQuery("select e from Portfolio e");
		  portfolios = (ArrayList<Portfolio>) query.list();
		return portfolios;
	}
	public int addProgram(Program program,String creatorName) throws ClassNotFoundException, SQLException {
		Session session=getSession();
		Program newProgram = new Program();
		newProgram.setProgramName(program.getProgramName());
		newProgram.setDescription(program.getDescription());
		newProgram.setPortfolioId(program.getPortfolioId());
		newProgram.setCreatedBy(creatorName);
		session.save(newProgram);
		return 1;
	}
	public List<Program> getAllProjectPrograms()throws ClassNotFoundException, SQLException {
		Session session=getSession();
		List<Program> programs = new ArrayList<Program>();
		  Query query=session.createQuery("select e from Program e");
		  programs =  query.list();
		return programs;
		
	}
	public int updateEmployee(ResourceMaster resource,String creatorName) throws ClassNotFoundException, SQLException {
		Session session=getSession();
		int count = 0;
		Query query=session.createQuery("update ResourceMaster r set  r.level=:careerLevel,r.supervisorId=:supervisorId, r.technologyId=:technologyId, r.projectId=:projectId where r.employeeId=:employeeId");
		query.setParameter("supervisorId", resource.getSupervisorId());
		query.setParameter("careerLevel", resource.getLevel());
		query.setParameter("technologyId", resource.getTechnologyId());
		query.setParameter("projectId", resource.getProjectId());
		query.setParameter("employeeId",resource.getEmployeeId());
		count = query.executeUpdate();
		Query query2 = session.createQuery("select e from EmployeeProject e where e.employeeId=:employeeId");
		query2.setParameter("employeeId", resource.getEmployeeId());
		List<EmployeeProject> emp = query2.list();
		if(emp.isEmpty())
		{
			EmployeeProject empProj = new EmployeeProject();
			empProj.setProjectId(employeeProject.getProjectId());
			empProj.setEmployeeId(employeeProject.getEmployeeId());
			if(employeeProject.getRoleStartDate().equals(inputDate))
				empProj.setRoleStartDate(null);
			else
				empProj.setRoleStartDate(employeeProject.getRoleStartDate());
			if(employeeProject.getRoleEndDate().equals(inputDate))
				empProj.setRoleEndDate(null);
			else
				empProj.setRoleEndDate(employeeProject.getRoleEndDate());
			
			empProj.setRoleName(employeeProject.getRoleName());
			empProj.setLcr(employeeProject.getLcr());
			empProj.setLanId(employeeProject.getLanId());
			empProj.setRecommendToHold(false);
			empProj.setCreatedBy(creatorName);
			empProj.setActive(true);
			session1.save(empProj);
			count2 = 1;
		}
		else
		{
		Query query1=session1.createQuery("update EmployeeProject r set  r.roleStartDate=:roleStartDate,r.roleEndDate=:roleEndDate,r.roleName=:roleName,r.lanId=:lanId,r.projectId=:projectId,r.lcr=:lcr where r.employeeId=:employeeId");
		query1.setParameter("lcr",employeeProject.getLcr());
		query1.setParameter("roleStartDate",employeeProject.getRoleStartDate());
		query1.setParameter("roleEndDate",employeeProject.getRoleEndDate());
		query1.setParameter("lanId",employeeProject.getLanId());
		query1.setParameter("projectId",employeeProject.getProjectId());
		query1.setParameter("roleName",employeeProject.getRoleName());
		query1.setParameter("employeeId",resource.getEmployeeId());
		count2 = query1.executeUpdate();
		}
	
			count = 1;
		return count;
	}
	public Portfolio getPortfolio(Integer portfolioId) throws ClassNotFoundException, SQLException {
		List<Portfolio> portfoliolist = new ArrayList<Portfolio>();
		Session session=getSession();
		Query query=session.createQuery("select e from  Portfolio e where e.portfolioId=:portfolioId ");
		query.setParameter("portfolioId",portfolioId);
		portfoliolist = query.list();
		Portfolio portfolio = new Portfolio();
		for(Portfolio portfolios:portfoliolist)
		{
			portfolio = portfolios;
		}
		
		
		return portfolio;
	}
	public int updatePortfolio(Portfolio portfolio) throws ClassNotFoundException, SQLException {
		Session session=getSession();
		int count = 0;
		Query query=session.createQuery("update Portfolio r set  r.portfolioName=:portfolioName, r.portfolioDescription=:portfolioDescription where r.portfolioId=:portfolioId");
		query.setParameter("portfolioName", portfolio.getPortfolioName());
		query.setParameter("portfolioDescription", portfolio.getPortfolioDescription());
		query.setParameter("portfolioId", portfolio.getPortfolioId());
		count = query.executeUpdate();
		return count;
	}
	public int deletePortfolio(Integer portfolioId) throws ClassNotFoundException, SQLException {
		int count = 0;
		Session session=getSession();
		Query query=session.createQuery("delete from Portfolio p where p.portfolioId=:portfolioId");
		query.setParameter("portfolioId",portfolioId);
		count = query.executeUpdate();
		return count;
	}
	public List<Technology> getAllTechnology() throws ClassNotFoundException, SQLException {
		List<Technology> technologylist= new ArrayList<Technology>();
		Session session=getSession();
		Query query=session.createQuery("select e from  Technology e ");
		technologylist = query.list();
		return technologylist;
	}
	public int deleteTechnology(Integer technologyId) throws ClassNotFoundException, SQLException {
		int count = 0;
		Session session=getSession();
		Query query=session.createQuery("delete from Technology t where t.technologyId=:technologyId");
		query.setParameter("technologyId",technologyId);
		count = query.executeUpdate();
		return count;
	}
	public int addTechnology(Technology technology) throws ClassNotFoundException, SQLException {
		Session session=getSession();
		int count = 0;
		Query query = session.createQuery("select e from Technology e where e.technologyName=:technologyName");
		query.setParameter("technologyName", technology.getTechnologyName());
		List<Technology> technologyList=query.list();
		if(technologyList.isEmpty())
		{
			Technology newtechnology = new Technology();
			newtechnology.setTechnologyName(technology.getTechnologyName());
			newtechnology.setTechnologyDescription(technology.getTechnologyDescription());
			session.save(newtechnology);
			count = 1;
		}
		return count;
	}
	public int deleteProgram(Integer programId) throws ClassNotFoundException, SQLException {
		int count = 0;
		Session session=getSession();
		Query query=session.createQuery("delete from Program p where p.programId=:programId");
		query.setParameter("programId",programId);
		count = query.executeUpdate();
		return count;
	}
	public Program getProgram(Integer programId) throws ClassNotFoundException, SQLException {
		List<Program> Programlist = new ArrayList<Program>();
		Session session=getSession();
		Query query=session.createQuery("select e from  Program e where e.programId=:programId ");
		query.setParameter("programId",programId);
		Programlist = query.list();
		Program program = new Program();
		for(Program programs:Programlist)
		{
			program = programs;
		}
		
		
		return program;
	}
	public Project getProject(Integer id) throws ClassNotFoundException, SQLException {
		
		List<Project> projectlist = new ArrayList<Project>();
		Session session=getSession();
		Query query=session.createQuery("select e from Project e where e.id=:id ");
		query.setParameter("id",id);
		projectlist = query.list();
		Project project = new Project();
		for(Project projects:projectlist )
		{
			 project = projects;
			 
		}
		return project;
}

public int UpdateProjectDetails(Project project) throws ClassNotFoundException, SQLException {
	Session session=getSession();
	int count = 0;
	Query query=session.createQuery("update Project p set  p.projectName=:projectName, p.programId=:programId, p.projectDescription=:projectDescription where p.id=:id");
	query.setParameter("projectName", project.getProjectName());
	query.setParameter("projectDescription", project.getProjectDescription());
	query.setParameter("programId", project.getProgramId());

	query.setParameter("id", project.getId());
	count = query.executeUpdate();
	return count;
}
	public String getProgramName(Integer programId) throws ClassNotFoundException, SQLException {
		String programName="";
		List<Program> programs = new ArrayList<Program>();
		Session session=getSession();
		Query query=session.createQuery("select e from  Program e where e.programId=:programId ");
		query.setParameter("programId",programId);
		programs = query.list();
		for(Program program:programs)
		{
			programName = program.getProgramName();
			
		}
		
		
		return programName;
	}
	public String getPortfolioName(Integer portfolioId) throws ClassNotFoundException, SQLException {
		String portfolioName="";
		List<Portfolio> portfolios = new ArrayList<Portfolio>();
		Session session=getSession();
		Query query=session.createQuery("select e from  Portfolio e where e.portfolioId=:portfolioId ");
		query.setParameter("portfolioId",portfolioId);
		portfolios = query.list();
		for(Portfolio portfolio:portfolios)
		{
			portfolioName = portfolio.getPortfolioName();
			
		}
		
		
		return portfolioName;
	}
	public int updateProgram(Program program) throws ClassNotFoundException, SQLException {
		Session session=getSession();
		int count = 0;
		Query query=session.createQuery("update Program r set  r.programName=:programName, r.description=:programDescription, r.portfolioId=:portfolioId where r.programId=:programId");
		query.setParameter("programName", program.getProgramName());
		query.setParameter("programDescription", program.getDescription());
		query.setParameter("portfolioId", program.getPortfolioId());
		query.setParameter("programId", program.getProgramId());

		count = query.executeUpdate();
		return count;
	}
	public int deleteProject(Integer id) throws ClassNotFoundException, SQLException {
		Session session=getSession();
		int count = 0;
		Query query=session.createQuery("delete from Project p where p.id=:id");
		query.setParameter("id",id);
	
		count = query.executeUpdate();
		return count;
}
	public int insertPersonaldetails(Long ContactNo,String PassportNo,String PanNo, String enterpriseId) throws ClassNotFoundException,SQLException{
		int count;
		Session session=getSession();
		Query query=session.createQuery("update ResourceMaster r set r.contactNo=:contactNo,r.passportNo=:passportNo,r.panNo=:panNo where r.enterpriseId=:enterpriseid");
		query.setParameter("contactNo", ContactNo);
		query.setParameter("passportNo",PassportNo);
		query.setParameter("panNo",PanNo);
		query.setParameter("enterpriseid",enterpriseId);

		count = query.executeUpdate();
		return count;
	}
	public List<String> checkSupervisor(String Name, int careerLevel) throws ClassNotFoundException, SQLException
	{
		Session session=getSession();		
		
		List<String> superVisorList= new ArrayList<String>();
		Query query=session.createQuery("select e from  ResourceMaster e where e.enterpriseId LIKE :eid and ( e.level <=:careerLevel )");	
		query.setParameter("eid",'%'+ Name+ '%');
		query.setParameter("careerLevel",careerLevel-1);
		List<ResourceMaster> employeeList=query.list();
		for(ResourceMaster resource:employeeList)
		{	
			superVisorList.add(resource.getEnterpriseId());
		}
		
		return superVisorList; 
	}

	public List<CareerLevel> getAllCareerLevels() {
		List<CareerLevel> allCareerLevels = new ArrayList<CareerLevel>();
		Session session=getSession();
		Query query = session.createQuery("select e from CareerLevel e");
		allCareerLevels = query.list();
		return allCareerLevels;
	}

	public int updateContact(Long employeeId, Long contactNo) throws ClassNotFoundException, SQLException {
		Session session=getSession();
		int count = 0;
		Query query=session.createQuery("update ResourceMaster e set e.contactNo=:contactNo where e.employeeId=:employeeId  ");
		query.setParameter("employeeId", employeeId);
		query.setParameter("contactNo", contactNo);
		count = query.executeUpdate();
		return count;	
	}
	public String getTeamName(Integer projectId) {
		Session session=getSession();
		String teamName = "";
		String programName = "";
		String protfolioName = "";
		int portfolioId = 0;
		Query query = session.createQuery("select e from Program e where e.programId in (select p.programId from Project p where p.id=:projectId)");
		query.setParameter("projectId", projectId);
		List<Program> programs = query.list();
		for(Program program : programs)
		{
			programName = program.getProgramName();
			portfolioId = program.getPortfolioId();
			
		}
		query = session.createQuery("select e from Portfolio e where e.portfolioId=:portfolioId");
		query.setParameter("portfolioId", portfolioId);
		List<Portfolio> portfolios = query.list();
		for(Portfolio portfolio : portfolios)
		{
			protfolioName = portfolio.getPortfolioName();
		}
		teamName = protfolioName + " "+ programName;
		return teamName;
	}
	public EmployeeProject getEmployeeProjectRoleDetails(Long employeeId) throws ClassNotFoundException, SQLException {
		Session session=getSession();
		Query query=session.createQuery("select e from  EmployeeProject e where e.employeeId=:employeeId ");
		query.setParameter("employeeId", employeeId);
		
		EmployeeProject employeeProject=new EmployeeProject();
		List<EmployeeProject> employeesProjects=query.list();
		for(EmployeeProject employeeProjects:employeesProjects)
		{
			employeeProject = employeeProjects;
		}
		return employeeProject;
	}
	public int recommendToHoldEmployee(Long employeeId, String potentialFutureRole, Date roleEndDate, String creator)
			throws ClassNotFoundException, SQLException {
		int count = 0;
		Session session=getSession();
		Session session1=getSession();
		Query query1=session.createQuery("update EmployeeProject e set e.recommendToHold=:recommendToHold where e.employeeId=:employeeId");
		query1.setParameter("employeeId", employeeId);
		query1.setParameter("recommendToHold", true);
		count = query1.executeUpdate();
		EmployeeHub employeeHub = new EmployeeHub();
		employeeHub.setEmployeeId(employeeId);
		employeeHub.setHubStartDate(roleEndDate);
		employeeHub.setPotentialFutureRole(potentialFutureRole);
		employeeHub.setCreatedBy(creator);
		session1.save(employeeHub);
		return count;
	}
	public EmployeeProject getEmployeeRoleOffDetails(Long employeeId) throws ClassNotFoundException, SQLException {
		Session session=getSession();
		Query query=session.createQuery("select e from  EmployeeProject e where e.employeeId=:employeeId and e.recommendToHold=:recommendToHold");
		query.setParameter("employeeId", employeeId);
		query.setParameter("recommendToHold", false);
		EmployeeProject employeeProject=new EmployeeProject();
		List<EmployeeProject> employeesProjects=query.list();
		for(EmployeeProject employeeProjects:employeesProjects)
		{
			employeeProject = employeeProjects;
		}
		return employeeProject;
		
	}
	public EmployeeHub getEmployeeOnHub(Long employeeId) throws ClassNotFoundException, SQLException {
		Session session=getSession();
		Query query=session.createQuery("select e from  EmployeeHub e where e.employeeId=:employeeId");
		query.setParameter("employeeId", employeeId);
		
		EmployeeHub employeehub=new EmployeeHub();
		List<EmployeeHub> employeesHubs=query.list();
		for(EmployeeHub employeeHubs:employeesHubs)
		{
			employeehub = employeeHubs;
		}
		return employeehub;
	}
	public List<CalendarData> getCalendarData(Long employeeId, String startDate, String endDate) {
		CalendarData calendarData1 = new CalendarData();
		CalendarData calendarData2 = new CalendarData();
		List<CalendarData> calendarDataList = new ArrayList<CalendarData>();
		String[] monthName = { "january", "february", "march", "april", "may", "june", "july",
		        "august", "september", "october", "november", "december" };
		List<DayData> dayData = new ArrayList<DayData>();
		List<DayData> dayData2 = new ArrayList<DayData>();
		
		int startingDate = Integer.parseInt(startDate.substring(0, startDate.indexOf('/')));
		int startingMonth = Integer.parseInt(startDate.substring(startDate.indexOf('/') + 1,startDate.lastIndexOf('/')));
		String startMonth = monthName[startingMonth - 1];
		int startYear = Integer.parseInt(startDate.substring(startDate.lastIndexOf('/') + 1));
	
		int endingDate = Integer.parseInt(endDate.substring(0, endDate.indexOf('/')));
		int endingMonth = Integer.parseInt(endDate.substring(endDate.indexOf('/') + 1,endDate.lastIndexOf('/')));
		String endMonth = monthName[endingMonth - 1];
		int endYear = Integer.parseInt(endDate.substring(endDate.lastIndexOf('/') + 1));
		String firstDateRange, secondDateRange;
		System.out.println(endMonth + endYear);
		firstDateRange = "(t.date between " + startingDate + " and 30)";
		secondDateRange = "(t.date between 1 and " + endingDate+")";
		calendarData1.setEmployeeId(employeeId);
		calendarData1.setMonth(startMonth);
		calendarData1.setYear(startYear);
		String firstQuery = "select t from Timesheet t where t.employeeId=:employeeId and t.month=:month and t.year=:year and " + firstDateRange;
		Session session=getSession();
		Query query=session.createQuery(firstQuery);
		query.setParameter("employeeId", employeeId);
		query.setParameter("month", startMonth);
		query.setParameter("year", startYear);
		List<Timesheet> timesheet=query.list();
		for(Timesheet time : timesheet)
		{
			DayData day_Data = new DayData();
			day_Data.setDate(time.getDate());
			day_Data.setShift(time.getShift());
			dayData.add(day_Data);
		}
		calendarData1.setDayData(dayData);
		
		calendarData2.setEmployeeId(employeeId);
		calendarData2.setMonth(endMonth);
		calendarData2.setYear(endYear);
		String secondQuery = "select t from Timesheet t where t.employeeId=:employeeId and t.month=:month and t.year=:year and " + secondDateRange;
		Query query2=session.createQuery(secondQuery);
		query2.setParameter("employeeId", employeeId);
		query2.setParameter("month", endMonth);
		query2.setParameter("year", endYear);
		List<Timesheet> timesheet2=query2.list();
		for(Timesheet time2 : timesheet2)
		{
			DayData day_Data2 = new DayData();
			day_Data2.setDate(time2.getDate());
			day_Data2.setShift(time2.getShift());
			dayData2.add(day_Data2);
		}
		calendarData2.setDayData(dayData2);
		
		for(DayData d : calendarData2.getDayData())
		{
			System.out.println("Date : "+d.getDate() + "shift : " + d.getShift());
		}
		calendarDataList.add(calendarData1);
		calendarDataList.add(calendarData2);
		return calendarDataList;
	}
	public List<ProjectLocation> getAllProjectLocationDetails() {
		List<ProjectLocation> projectLocationDetails = new ArrayList<ProjectLocation>();
		Session session=getSession();
		Query query=session.createQuery("select e from  ProjectLocation e");
		projectLocationDetails =query.list();
		return projectLocationDetails;
	}
	public int assignProject(EmployeeProject employeeProject,String creatorName,Integer locationId) {
		int count = 0;
		Session session=getSession();
		Query query = session.createQuery("update ResourceMaster e set e.projectId=:projectId, e.locationId=:locationId where e.employeeId=:employeeId");
		query.setParameter("projectId",employeeProject.getProjectId() );
		query.setParameter("locationId",locationId);
		query.setParameter("employeeId",employeeProject.getEmployeeId());
		query.executeUpdate();
		Query query2 = session.createQuery("select e from EmployeeProject e where e.employeeId=:employeeId");
		query2.setParameter("employeeId", employeeProject.getEmployeeId());
		List<EmployeeProject> emp = query2.list();
		if(emp.isEmpty())
		{
			EmployeeProject empProj = new EmployeeProject();
			empProj.setProjectId(employeeProject.getProjectId());
			empProj.setEmployeeId(employeeProject.getEmployeeId());
			empProj.setRoleStartDate(employeeProject.getRoleStartDate());
			empProj.setRoleEndDate(employeeProject.getRoleEndDate());			
			empProj.setRoleName(employeeProject.getRoleName());
			empProj.setLcr(employeeProject.getLcr());
			empProj.setLanId(employeeProject.getLanId());
			empProj.setRecommendToHold(false);
			empProj.setCreatedBy(creatorName);
			empProj.setActive(true);
			session.save(empProj);
			count = 1;
		}
		else
		{
		Query query1 = session.createQuery("update EmployeeProject r set  r.roleStartDate=:roleStartDate,r.roleEndDate=:roleEndDate,r.roleName=:roleName,r.lanId=:lanId,r.projectId=:projectId,r.lcr=:lcr where r.employeeId=:employeeId");
		query1.setParameter("lcr",employeeProject.getLcr());
		query1.setParameter("roleStartDate",employeeProject.getRoleStartDate());
		query1.setParameter("roleEndDate",employeeProject.getRoleEndDate());
		query1.setParameter("lanId",employeeProject.getLanId());
		query1.setParameter("projectId",employeeProject.getProjectId());
		query1.setParameter("roleName",employeeProject.getRoleName());
		query1.setParameter("employeeId",employeeProject.getEmployeeId());
		count = query1.executeUpdate();
		}
		
		
		
		
		EmployeeProject empProj = new EmployeeProject();
		empProj.setProjectId(employeeProject.getProjectId());
		empProj.setEmployeeId(employeeProject.getEmployeeId());
		empProj.setRoleEndDate(employeeProject.getRoleEndDate());
		empProj.setRoleStartDate(employeeProject.getRoleStartDate());
		empProj.setRoleName(employeeProject.getRoleName());
		empProj.setLcr(employeeProject.getLcr());
		empProj.setLanId(employeeProject.getLanId());
		empProj.setRecommendToHold(false);
		empProj.setCreatedBy(creatorName);
		empProj.setActive(true);
		session.save(empProj);
		count = 1;
		return count;
	}
	public List<Location> getAllLocations() {
		Session session = getSession();
		Query query = session.createQuery("select e from Location e");
		List<Location> locations = query.list();
		return locations;
	}
	public int changePassword(ResourceMaster resource, String password) {
		int count = 0;
		Session session = getSession();
		Query query = session.createQuery("update ResourceMaster e set e.password=:password, e.defaultPassword=:defaultPassword where e.employeeId=:employeeId");
		query.setParameter("password",password );
		query.setParameter("defaultPassword",false);
		query.setParameter("employeeId",resource.getEmployeeId());
		count = query.executeUpdate();
		return count;
	}
	

}
*/