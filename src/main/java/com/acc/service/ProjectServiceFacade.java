	package com.acc.service;

import java.util.List;

import com.acc.entity.EmployeeProject;
import com.acc.entity.Portfolio;
import com.acc.entity.Program;
import com.acc.entity.Project;
import com.acc.entity.ProjectLocation;

public interface ProjectServiceFacade {
	public int addNewProject(String projectName,String projectDescription,String creatorName,Integer programId);
	public List<Project> getAllProjects();
	public String getProjectName(Integer projectId);
	public int UpdateProjectDetails(Project project);
	public Project getProject(Integer id);
	//public List<Program> getAllProjectPrograms();
	public EmployeeProject getEmployeeProjectRoleDetails(Long employeeId);
	public EmployeeProject getEmployeeRoleOffDetails(Long employeeId);
	public int deleteProject(Integer id);
	public List<ProjectLocation> getAllProjectLocationDetails();
	public int assignProject(EmployeeProject employeeProject,String creatorName,Integer locationId);
	public List<EmployeeProject> getProjectHistory(Long employeeId);
	public Integer getProjectIdByName(String name);
	
}
