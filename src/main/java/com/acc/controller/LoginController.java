package com.acc.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.xmlbeans.impl.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.acc.dto.PrivilegesDTO;
import com.acc.entity.EmployeeProject;
import com.acc.entity.Project;
import com.acc.entity.ResourceMaster;
import com.acc.entity.Technology;
import com.acc.service.EmployeeServiceFacade;
import com.acc.service.ProjectServiceFacade;
import com.acc.service.TechnologyServiceFacade;
//import com.acc.service.ServiceFacade;
import com.acc.service.usermanagement.PrivilegesService;

@SuppressWarnings("unused")
@Controller
public class LoginController{
	
	@Autowired
	private UserDetailsService userDetailsService;
  /*  @Autowired
	ServiceFacade serv;*/
	@Autowired
	EmployeeServiceFacade empserv;
	@Autowired
	ProjectServiceFacade projectserviceImpl;
	@Autowired
	private PrivilegesService privilegesService;
	@Autowired
	TechnologyServiceFacade technologyserviceImpl;
	
	
	@RequestMapping(value={"/login.htm"}, method=RequestMethod.GET)
	public String pageLogin(Model model,HttpServletRequest request){
			return "Login";
		
	}
	@RequestMapping("/idCheck.htm")
	public ModelAndView idCheck(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ModelAndView modelandview = new ModelAndView();
		ResourceMaster resource = new ResourceMaster();
		String enterpriseId = request.getParameter("enterpriseId").toLowerCase();
		resource = empserv.searchEmployee(enterpriseId);
	    String password = resource.getPassword();
		if(resource.getEmployeeName() != null)
		{
			if(StringUtils.isEmpty(password))
			{
				modelandview.setViewName("Signup");
			}
			else
			{
				modelandview.setViewName("Login");
			}
			modelandview.addObject("resource", resource);
		}
		else
		{
			modelandview.setViewName("index");
			modelandview.addObject("Error_Message", "you cant login");
		}
		return modelandview;
	}
	@RequestMapping("/loginSignup.htm")
	public ModelAndView loginSignup(HttpServletRequest request, HttpServletResponse response, Principal user) throws Exception
	{
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>)
				authentication.getAuthorities();
				 
		List<String> authoritiesList=new ArrayList<String>();
		for(GrantedAuthority authority:authorities){
			authoritiesList.add(authority.getAuthority());
		}
		List<PrivilegesDTO> menus=privilegesService.getMenuList(authoritiesList);
		

		Authentication a = SecurityContextHolder.getContext().getAuthentication();
	    UserDetails currentUserDetails = (UserDetails) a.getPrincipal();
		ModelAndView modelandview = new ModelAndView();
		ResourceMaster resource = new ResourceMaster();
		resource=empserv.searchEmployee(currentUserDetails.getUsername());
		if(resource.getEmployeePhoto() != null)
		{
			//byte[] encodeBase64 = Base64.getEncoder().encode(resource.getEmployeePhoto());/*encode(resource.getEmployeePhoto());*/
			byte[] encodeBase64 = Base64.encode(resource.getEmployeePhoto());
			String base64Encoded = new String(encodeBase64,"UTF-8");
			resource.setBase64Jmage(base64Encoded);
		}
		HttpSession session=request.getSession();
		session.setAttribute("resource", resource);
		ArrayList<ResourceMaster> supervisors = new ArrayList<ResourceMaster>();
		supervisors = empserv.allSupervisorDetails();
		List<Project> projects = new ArrayList<Project>();
		projects = projectserviceImpl.getAllProjects();
		List<Technology> technologies = new ArrayList<Technology>();
		Map<Long,EmployeeProject> employeeAndProject = new HashMap<Long,EmployeeProject>();
		ArrayList<ResourceMaster> employees = new ArrayList<ResourceMaster>();
		Map<Long,String> employeeSupervisor = new HashMap<Long,String>();
		employees = empserv.allEmployeeDetails();
		
		for(ResourceMaster employee:employees)
		{
			String supervisorName = empserv.getSupervisorName(employee.getSupervisorId());
			employeeSupervisor.put(employee.getEmployeeId(),supervisorName);
			EmployeeProject empproj = new EmployeeProject();
			empproj = projectserviceImpl.getEmployeeProjectRoleDetails(employee.getEmployeeId());
			employeeAndProject.put(employee.getEmployeeId(),empproj);
		}		
		technologies =technologyserviceImpl.getAllTechnology();
		session.setAttribute("supervisors", supervisors);
		session.setAttribute("projects", projects);
		session.setAttribute("employeeSupervisor", employeeSupervisor);
		session.setAttribute("technologies", technologies);
		
		session.setAttribute("employees", employees);
		
		session.setAttribute("employeeAndProject", employeeAndProject);
				
		Long supervisorId = resource.getSupervisorId();
		Integer projectId = resource.getProjectId();
		if(projectId!=null)
		{
		String projectName = projectserviceImpl.getProjectName(projectId);
		session.setAttribute("projectName", projectName);
		}
		String supervisorName = empserv.getSupervisorName(supervisorId);
		modelandview.addObject("resource", resource);
		session.setAttribute("supervisorName", supervisorName);
		if(resource.getDefaultPassword())
			modelandview.setViewName("changePassword");
		else
			modelandview.setViewName("myProfile");
		session.setAttribute("menuList", menus);

		return modelandview;
	}
	
}
