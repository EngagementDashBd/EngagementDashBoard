package com.acc.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

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
@Repository
public class ProjectDaoImplementation extends AbstractDao implements ProjectDao {

	public int addNewProject(String projectName, String projectDescription, String creatorName, Integer programId)
			throws ClassNotFoundException, SQLException {
		Session session = getSession();
		int count = 0;
		Query query = session.createQuery("select e from  Project e where e.projectName=:projectName");
		query.setParameter("projectName", projectName);
		List<Project> ProjectList = query.list();
		if (ProjectList.isEmpty()) {
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

	public List<Project> getAllProjects() throws ClassNotFoundException, SQLException {
		Session session = getSession();
		Query query = session.createQuery("select e from Project e ");
		List<Project> allProjectsData = query.list();
		return allProjectsData;
	}


	public String getProjectName(Integer projectId) throws ClassNotFoundException, SQLException {
		String projectName = "";
		List<Project> projects = new ArrayList<Project>();
		Session session = getSession();
		Query query = session.createQuery("select e from  Project e where e.id=:projectId ");
		query.setParameter("projectId", projectId);
		projects = query.list();
		for (Project project : projects) {
			projectName = project.getProjectName();
			System.out.println(projectName);
		}

		return projectName;

	}

	public int deleteProject(Integer id) throws ClassNotFoundException, SQLException {
		Session session = getSession();
		int count = 0;
		Query query = session.createQuery("delete from Project p where p.id=:id");
		query.setParameter("id", id);

		count = query.executeUpdate();
		return count;
	}


	public int UpdateProjectDetails(Project project) throws ClassNotFoundException, SQLException {
		Session session = getSession();
		int count = 0;
		Query query = session.createQuery(
				"update Project p set  p.projectName=:projectName, p.programId=:programId, p.projectDescription=:projectDescription where p.id=:id");
		query.setParameter("projectName", project.getProjectName());
		query.setParameter("projectDescription", project.getProjectDescription());
		query.setParameter("programId", project.getProgramId());

		query.setParameter("id", project.getId());
		count = query.executeUpdate();
		return count;
	}


	public Project getProject(Integer id) throws ClassNotFoundException, SQLException {

		List<Project> projectlist = new ArrayList<Project>();
		Session session = getSession();
		Query query = session.createQuery("select e from Project e where e.id=:id ");
		query.setParameter("id", id);
		projectlist = query.list();
		Project project = new Project();
		for (Project projects : projectlist) {
			project = projects;

		}
		return project;
	}

	public EmployeeProject getEmployeeProjectRoleDetails(Long employeeId) throws ClassNotFoundException, SQLException {
		Session session = getSession();
		Query query = session.createQuery("select e from  EmployeeProject e where e.employeeId=:employeeId ");
		query.setParameter("employeeId", employeeId);

		EmployeeProject employeeProject = new EmployeeProject();
		List<EmployeeProject> employeesProjects = query.list();
		for (EmployeeProject employeeProjects : employeesProjects) {
			employeeProject = employeeProjects;
		}
		return employeeProject;
	}
	public EmployeeProject getEmployeeRoleOffDetails(Long employeeId) throws ClassNotFoundException, SQLException {
		Session session = getSession();
		Query query = session.createQuery(
				"select e from  EmployeeProject e where e.employeeId=:employeeId and e.recommendToHold=:recommendToHold and e.active=:active");
		query.setParameter("employeeId", employeeId);
		query.setParameter("recommendToHold", false);
		query.setParameter("active",true);
		EmployeeProject employeeProject = new EmployeeProject();
		List<EmployeeProject> employeesProjects = query.list();
		for (EmployeeProject employeeProjects : employeesProjects) {
			employeeProject = employeeProjects;
		}
		return employeeProject;

	}

	public List<ProjectLocation> getAllProjectLocationDetails() {
		List<ProjectLocation> projectLocationDetails = new ArrayList<ProjectLocation>();
		Session session = getSession();
		Query query = session.createQuery("select e from  ProjectLocation e");
		projectLocationDetails = query.list();
		return projectLocationDetails;
	}

	public int assignProject(EmployeeProject employeeProject, String creatorName, Integer locationId) {
		int count = 0;
		Session session = getSession();
		Query query = session.createQuery(
				"update ResourceMaster e set e.projectId=:projectId, e.locationId=:locationId,e.employeeStatus=:employeeStatus where e.employeeId=:employeeId");
		query.setParameter("projectId", employeeProject.getProjectId());
		query.setParameter("locationId", locationId);
		query.setParameter("employeeId", employeeProject.getEmployeeId());
		query.setParameter("employeeStatus","active");
		query.executeUpdate();
		Query query2 = session.createQuery("select e from EmployeeProject e where e.employeeId=:employeeId");
		query2.setParameter("employeeId", employeeProject.getEmployeeId());
		List<EmployeeProject> emp = query2.list();
		if (emp.isEmpty()) {
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
		} else {
			EmployeeProject emp1 = new EmployeeProject();
			for(EmployeeProject e : emp)
				emp1 = e;
			Query query1 = session.createQuery(
					"update EmployeeProject r set  r.roleStartDate=:roleStartDate,r.roleEndDate=:roleEndDate,r.roleName=:roleName,r.lanId=:lanId,r.projectId=:projectId,r.lcr=:lcr,r.active=:active,r.recommendToHold=:recommendToHold where r.employeeId=:employeeId");
			if(employeeProject.getLcr() != 0.0f)
				query1.setParameter("lcr", emp1.getLcr());
			else
				query1.setParameter("lcr", employeeProject.getLcr());
			if(employeeProject.getRoleStartDate() != null)
				query1.setParameter("roleStartDate", employeeProject.getRoleStartDate());
			else
				query1.setParameter("roleStartDate", emp1.getLcr());
			if(employeeProject.getRoleEndDate() != null)
				query1.setParameter("roleEndDate", employeeProject.getRoleEndDate());
			else
				query1.setParameter("roleEndDate", emp1.getRoleEndDate());
			if(employeeProject.getLanId() != null)
				query1.setParameter("lanId", employeeProject.getLanId());
			else
				query1.setParameter("lanId", emp1.getLanId());
			if(employeeProject.getProjectId() != null)
				query1.setParameter("projectId", employeeProject.getProjectId());
			else
				query1.setParameter("projectId", emp1.getProjectId());
			if(employeeProject.getRoleName() != null)
				query1.setParameter("roleName", employeeProject.getRoleName());
			else
				query1.setParameter("roleName", employeeProject.getRoleName());
			query1.setParameter("employeeId", employeeProject.getEmployeeId());
			query1.setParameter("active",true);
			query1.setParameter("recommendToHold", false);
			count = query1.executeUpdate();
		}
		Query query3 = session.createQuery("select e from EmployeeHub e where e.employeeId=:employeeId and e.active=:active");
		query3.setParameter("employeeId", employeeProject.getEmployeeId());
		query3.setParameter("active",true);
		List<EmployeeHub> employeeHub = query3.list();
		if(!employeeHub.isEmpty())
		{
			Calendar calendar = Calendar.getInstance();
			  calendar.setTime(employeeProject.getRoleStartDate());
			  calendar.add(Calendar.DAY_OF_YEAR, 1);
			  Date hubEndDate = calendar.getTime();
			for(EmployeeHub employee : employeeHub)
			{
				Query query4 = session.createQuery("update EmployeeHub e set e.hubEndDate=:hubEndDate where e.employeeId=:employeeId");
				query4.setParameter("employeeId", employee.getEmployeeId());
				query4.setParameter("hubEndDate", hubEndDate);
				query4.executeUpdate();
			}
		}
		return count;
	}

	public List<EmployeeProject> getProjectHistory(Long employeeId) {
		List<EmployeeProject> previousProjects = new ArrayList<EmployeeProject>();
		Session session = getSession();
		Query query = session.createQuery("select e from EmployeeProject e where e.employeeId=:employeeId and e.active=:active");
		query.setParameter("employeeId", employeeId);
		query.setParameter("active", false);
		previousProjects = query.list();
		return previousProjects;
	}

	public Integer getProjectIdByName(String name) {
		Session session = getSession();
		Integer projectId = 0;
		Query query = session.createQuery("select e from Project e where e.projectName=:projectName");
		query.setParameter("projectName",name);
		List<Project> projects = query.list();
		for(Project project : projects)
		{
			projectId = project.getId();
		}
		return projectId;
	}

	


}