package com.acc.dao;

import java.sql.SQLException;
import java.util.List;

import com.acc.entity.EmployeeProject;
import com.acc.entity.Portfolio;
import com.acc.entity.Project;
import com.acc.entity.ProjectLocation;

public interface ProjectDao {
	public int addNewProject(String projectName,String projectDescription,String creatorName,Integer programId)throws ClassNotFoundException,SQLException;
	public List<Project> getAllProjects()throws ClassNotFoundException,SQLException;
	public String getProjectName(Integer projectId)throws ClassNotFoundException, SQLException;
	public int deleteProject(Integer id)throws ClassNotFoundException, SQLException;
	public int UpdateProjectDetails(Project project) throws ClassNotFoundException, SQLException;
	public Project getProject(Integer  id)  throws ClassNotFoundException, SQLException;
	public EmployeeProject getEmployeeProjectRoleDetails(Long employeeId)throws ClassNotFoundException, SQLException;
	public EmployeeProject getEmployeeRoleOffDetails(Long employeeId)throws ClassNotFoundException, SQLException;
	public List<ProjectLocation> getAllProjectLocationDetails(); 
	public int assignProject(EmployeeProject employeeProject,String creatorName,Integer locationId);
	public List<EmployeeProject> getProjectHistory(Long employeeId);
	public Integer getProjectIdByName(String name);
	
}
