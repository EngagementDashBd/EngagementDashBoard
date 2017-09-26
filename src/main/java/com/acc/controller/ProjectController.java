package com.acc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.acc.entity.EmployeeProject;
import com.acc.entity.Location;
import com.acc.entity.Menu;
import com.acc.entity.Program;
import com.acc.entity.Project;
import com.acc.entity.ResourceMaster;
import com.acc.entity.RoleName;
import com.acc.entity.Roles;
import com.acc.service.EmployeeServiceFacade;
import com.acc.service.InnovationServiceFacade;
import com.acc.service.MenuServiceFacade;
import com.acc.service.ProgramServiceFacade;
import com.acc.service.ProjectServiceFacade;
import com.acc.service.RolesFacade;
//import com.acc.service.ServiceFacade;
@Controller
public class ProjectController {
	/*@Autowired
	ServiceFacade serviceImpl;*/
	@Autowired
	ProjectServiceFacade projectserviceImpl;
	@Autowired
	EmployeeServiceFacade employeeServiceImpl;
	@Autowired
	ProgramServiceFacade programserviceImpl;
	@Autowired
	RolesFacade roleService;
	@Autowired
	MenuServiceFacade menuService;
	@Autowired
	InnovationServiceFacade innovationServiceImpl;
	@RequestMapping("/addNewProject.htm")
	public ModelAndView addNewProject(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView modelandview = new ModelAndView();
		HttpSession session = request.getSession();
		ResourceMaster resource = (ResourceMaster)session.getAttribute("resource");
		String creatorName = resource.getEmployeeName();
		String projectName = request.getParameter("projectName");
		String projectDescription = request.getParameter("projectDescription");
		Integer programId = Integer.parseInt(request.getParameter("programId"));		
		int count = projectserviceImpl.addNewProject(projectName, projectDescription,creatorName,programId);
		List<Project> projects = new ArrayList<Project>();
		projects = projectserviceImpl.getAllProjects();
		List<Program> programs = programserviceImpl.getAllProjectPrograms();
		modelandview.addObject("programs", programs);
		session.setAttribute("projects", projects);
		modelandview.setViewName("projectDetails");
		if(count == 1)
		  modelandview.addObject("code", "success");
		else
			modelandview.addObject("code", "failure");
		return modelandview;
	}
	@RequestMapping("/getAllPrograms.htm")
	public ModelAndView getAllProjectPrograms(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView modelandview = new ModelAndView();
		List<Program> programs = programserviceImpl.getAllProjectPrograms();
		modelandview.addObject("programs", programs);
		modelandview.setViewName("project");
		return modelandview;
	}
	@RequestMapping("/redirect.htm")
	public ModelAndView pageRedirect(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView modelandview = new ModelAndView();
		HttpSession session = request.getSession();
		ResourceMaster resource = (ResourceMaster)session.getAttribute("resource");
		Long supervisorId=resource.getSupervisorId();
		String SupervisorEid= employeeServiceImpl.getSupervisorEid(supervisorId);
		String pageName = request.getParameter("pageName");
		String page = request.getParameter("page");
		if(page == null)
		{
			
		}
		else if(page.equalsIgnoreCase("menu") || page.equalsIgnoreCase("role"))
		{
			List<Menu> menuList = menuService.getAllMenu();
			modelandview.addObject("menu", menuList);
			List<Roles> roles = roleService.getAllRoles();
			modelandview.addObject("roles", roles);
		}
		
		if(pageName.equalsIgnoreCase("adddevelopers"))
		{
			modelandview.addObject("ideaId", request.getParameter("ideaId"));
			modelandview.addObject("ideaDescription", request.getParameter("ideaDescription"));
		}
		modelandview.addObject("Supervisorname", SupervisorEid);
		modelandview.setViewName(pageName);
		return modelandview;
	}
	@RequestMapping("/allProjectDetails.htm")
	public ModelAndView allProjectDetails(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView modelandview = new ModelAndView();
	//	ArrayList<ResourceMaster> allprojectDetailsData = new ArrayList<ResourceMaster>();
		List<Project> projects  = new ArrayList<Project>(); 
		projects = projectserviceImpl.getAllProjects();
		HttpSession session = request.getSession();
		modelandview.setViewName("projectDetails");
		session.setAttribute("projects", projects);
		
		return modelandview;
	}
	
	@RequestMapping("/deleteProject.htm")
	public ModelAndView deleteProject(HttpServletRequest request,HttpServletResponse response )
	{
		
		ModelAndView modelandview = new ModelAndView();
		List<Project> projects = new ArrayList<Project>(); 
		Integer Id = Integer.parseInt(request.getParameter("id"));
		int count = projectserviceImpl.deleteProject(Id);
		HttpSession session = request.getSession();
		projects =  projectserviceImpl.getAllProjects();
		session.setAttribute("projects", projects);
		if(count == 1)
		{
			
			modelandview.addObject("deletecode","success");
		}
		else
		{
			modelandview.addObject("deletecode","failure");
		}
		modelandview.setViewName("projectDetails");
		return modelandview;
	
	}
	@RequestMapping("/fetchProjectDetails.htm")
	public ModelAndView fetchProjectDetails(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView modelandview = new ModelAndView();
		HttpSession session = request.getSession();
		Integer id = Integer.parseInt(request.getParameter("id"));
		Project project = projectserviceImpl.getProject(id);
		String programName =programserviceImpl.getProgramName(project.getProgramId());
		List<Program> programs = programserviceImpl.getAllProjectPrograms();
		session.setAttribute("programs", programs);
		session.setAttribute("project", project);
		session.setAttribute("programName", programName);
		modelandview.setViewName("updateProject");
		return modelandview;
	}
	@RequestMapping("/updateProjectDetails.htm")
	public ModelAndView UpdateProjectDetails(@ModelAttribute Project project, HttpServletRequest request, HttpServletResponse response )
	{
		int count = 0;
		ModelAndView modelandview = new ModelAndView();
		HttpSession session = request.getSession();
		count = projectserviceImpl.UpdateProjectDetails(project);
		ArrayList<Project> projects = new ArrayList<Project>();
		 projects = (ArrayList<Project>)  projectserviceImpl.getAllProjects();
		session.setAttribute("projects",projects);
		
		
		modelandview.setViewName("projectDetails");
		if(count == 1)
		{
			
			modelandview.addObject("updatecode","success");
		}
		else
		{
			modelandview.addObject("updatecode","failure");
		}
		return modelandview;
	}
	@RequestMapping("/assignProject.htm")
	public ModelAndView assignProject(@ModelAttribute EmployeeProject employeeProject,HttpServletRequest request, HttpServletResponse response )
	{
		ModelAndView modelandview = new ModelAndView();
		HttpSession session = request.getSession();
		ResourceMaster resource = (ResourceMaster)session.getAttribute("resource");
		String creatorName = resource.getEmployeeName();
		Integer locationId = Integer.valueOf(request.getParameter("locationId"));
		int count =projectserviceImpl.assignProject(employeeProject,creatorName,locationId);
		
		Map<Long,EmployeeProject> employeeAndProject = new HashMap<Long,EmployeeProject>();
		ArrayList<ResourceMaster> employees = new ArrayList<ResourceMaster>();
		employees = employeeServiceImpl.allEmployeeDetails();
		for(ResourceMaster employee:employees)
		{
			String supervisorName = employeeServiceImpl.getSupervisorName(employee.getSupervisorId());
			
			EmployeeProject empproj = new EmployeeProject();
			empproj = projectserviceImpl.getEmployeeProjectRoleDetails(employee.getEmployeeId());
			employeeAndProject.put(employee.getEmployeeId(),empproj);
		}
		session.setAttribute("employees",employees);
		session.setAttribute("employeeAndProject", employeeAndProject);	
		modelandview.setViewName("employeeDetails");
		return modelandview;
	}
	@RequestMapping("fetchProject.htm")
	public ModelAndView fetchProject(HttpServletRequest request, HttpServletResponse response )
	{
		ModelAndView modelandview = new ModelAndView();		
		String enterpriseId = request.getParameter("enterpriseid");
		ResourceMaster resourceMaster =employeeServiceImpl.searchEmployee(enterpriseId);
		HttpSession session = request.getSession();
		EmployeeProject empproj = projectserviceImpl.getEmployeeProjectRoleDetails(resourceMaster.getEmployeeId());
		List<Location> locations = employeeServiceImpl.getAllLocations();
		session.setAttribute("locations", locations);
		modelandview.addObject("resourceMaster", resourceMaster);
		if(empproj.getProjectId() != null)
		{
			
			modelandview.addObject("employeeProject",empproj);
		}
		modelandview.addObject("history","yes");
		List<RoleName> roleNames = employeeServiceImpl.getAllRoleNames();
		session.setAttribute("roleNames", roleNames);
		modelandview.setViewName("assignProject");
		return modelandview;
	}
	@RequestMapping("/getProjectHistory.htm")
	public ModelAndView getProjectHistory(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView modelandview = new ModelAndView();		
		Long employeeId = Long.valueOf(request.getParameter("employeeId"));
		Map<Integer,String> projectAndName = new HashMap<Integer,String>();
		List<EmployeeProject> employeeProjects = projectserviceImpl.getProjectHistory(employeeId);
		for(EmployeeProject project : employeeProjects)
		{
			String projectName = projectserviceImpl.getProjectName(project.getProjectId());
			projectAndName.put(project.getProjectId(),projectName);
		}
		modelandview.addObject("projects", employeeProjects);
		modelandview.addObject("projectName", projectAndName);
		modelandview.setViewName("projectHistory");
		return modelandview;
	}
	

}
