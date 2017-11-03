package com.acc.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.acc.dto.FileUpload;
import com.acc.entity.CareerLevel;
import com.acc.entity.EmployeeHub;
import com.acc.entity.EmployeeProject;
import com.acc.entity.Location;
import com.acc.entity.Project;
import com.acc.entity.ProjectLocation;
import com.acc.entity.ResourceMaster;
import com.acc.entity.RoleName;
import com.acc.entity.Technology;
import com.acc.helper.RandStrGenerator;
import com.acc.mailer.Mailer;
import com.acc.service.CareerLevelServiceFacade;
import com.acc.service.EmployeeServiceFacade;
import com.acc.service.LoginService;
import com.acc.service.ProgramServiceFacade;
import com.acc.service.ProjectServiceFacade;
import com.acc.service.TechnologyServiceFacade;
import com.google.gson.Gson;
@Controller
public class EmployeeController {
	/*@Autowired
	ServiceFacade serviceImpl;*/
	@Autowired
	ProgramServiceFacade programserviceImpl;
	@Autowired
	ProjectServiceFacade projectserviceImpl;
	@Autowired
	EmployeeServiceFacade employeeServiceImpl;
	@Autowired
	LoginService loginserviceimp;
	@Autowired
	TechnologyServiceFacade technologyserviceImpl;
	@Autowired
	CareerLevelServiceFacade careerLevelService;
	
	String defaultPwdMail = "Greetings, You can now access the Engagement DashBoard.  Use the given password to login.  You will be prompted to updated your password immediately.  CurrentPassword : ";
	
	String profilePhotoMail = "You have successfully updated your Profile Picture";
	
	String pwdUpdateMail = "You have successfully updated your password.";
	
	String contactUpdateMail ="You have successfully updated your Contact Details";
	
	String myPersonalDetailsUpdateMail ="Your Personal details have been Updated Successfully";
	
	String supervisorUpdateMail = "Your Supervisor details has been Updated successfully";
	
	//@RequestMapping("/idCheck.htm")
	public ModelAndView idCheck(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ModelAndView modelandview = new ModelAndView();
		ResourceMaster resource = new ResourceMaster();
		String enterpriseId = request.getParameter("enterpriseId");
		resource = employeeServiceImpl.searchEmployee(enterpriseId);
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
	//@RequestMapping("/loginSignup.htm")
	public ModelAndView loginSignup(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ModelAndView modelandview = new ModelAndView();
		ResourceMaster resource = new ResourceMaster();
		String enterpriseId = request.getParameter("enterpriseId");
		String password = request.getParameter("password");
		String flag = request.getParameter("flag");
		if(flag.equals("login") )
		{	
			resource =loginserviceimp .loginEmployee(enterpriseId,password);
		
			if(resource.getEmployeeName() != null)
			{
				HttpSession session=request.getSession();
				session.setAttribute("resource", resource);
				ArrayList<ResourceMaster> supervisors = new ArrayList<ResourceMaster>();
				supervisors = employeeServiceImpl.allSupervisorDetails();
				List<Project> projects = new ArrayList<Project>();
				projects =projectserviceImpl.getAllProjects();
				List<Technology> technologies = new ArrayList<Technology>();
				technologies = technologyserviceImpl.getAllTechnology();
				session.setAttribute("supervisors", supervisors);
				session.setAttribute("projects", projects);
				session.setAttribute("technologies", technologies);
				if(resource.getEmployeeName().equalsIgnoreCase("administrator"))
				{
					modelandview.setViewName("adminPanel");
				}
				else
				{
					Long supervisorId = resource.getSupervisorId();
					Integer projectId = resource.getProjectId();
					if(projectId!=null){
					String projectName = projectserviceImpl.getProjectName(projectId);
					session.setAttribute("projectName", projectName);
					}
					String supervisorName = employeeServiceImpl.getSupervisorName(supervisorId);
					//modelandview.addObject("resource", resource);
					session.setAttribute("supervisorName", supervisorName);
					
					modelandview.setViewName("myProfile");
				}
					
			}
			else
			{
				modelandview.addObject("loginCode", "failure");
				modelandview.setViewName("Login");
			}
		}
		else if(flag.equals("signup"))
		{
			int count = 0;
			count = loginserviceimp.signupEmployee(enterpriseId, password);
			if(count == 1)
			{
				modelandview.addObject("code","success");
				modelandview.setViewName("Login");
			}
			else
			{
				modelandview.addObject("code", "failure");
				modelandview.setViewName("Signup");
			}
		}
		return modelandview;
	}
	@RequestMapping(value = "/allEmployeeDetails.htm")
	public ModelAndView allEmployeeDetails(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView modelandview = new ModelAndView();
		HttpSession session=request.getSession();
		Map<Long,EmployeeProject> employeeAndProject = new HashMap<Long,EmployeeProject>();
		Map<Long,String> employeeSupervisor = new HashMap<Long,String>();
		ArrayList<ResourceMaster> employees = new ArrayList<ResourceMaster>();
		//ArrayList<ResourceMaster> supervisors = new ArrayList<ResourceMaster>();
		List<Project> allProjectsData = new ArrayList<Project>(); 
		employees = employeeServiceImpl.allEmployeeDetails();
		
		allProjectsData =  projectserviceImpl.getAllProjects();
		for(ResourceMaster employee:employees)
		{
			
			String supervisorName = employeeServiceImpl.getSupervisorName(employee.getSupervisorId());
			employeeSupervisor.put(employee.getEmployeeId(),supervisorName);
			EmployeeProject empproj = new EmployeeProject();
			empproj = projectserviceImpl.getEmployeeProjectRoleDetails(employee.getEmployeeId());
			employeeAndProject.put(employee.getEmployeeId(),empproj);
		}		
		session.setAttribute("employees", employees);
		session.setAttribute("projects", allProjectsData);
		session.setAttribute("employees", employees);
		session.setAttribute("employeeSupervisor", employeeSupervisor);
		modelandview.setViewName("employeeDetails");
		return modelandview;
	}
	@RequestMapping(value="/addEmployee.htm",method=RequestMethod.POST)
	public ModelAndView addNewEmployee( ResourceMaster resourceMaster, HttpServletRequest request,HttpServletResponse response )
	{
		String[] roles = resourceMaster.getRoles();
		String[] roleArray = roles[0].split(",");
		resourceMaster.setRoles(roleArray);
		ModelAndView modelandview = new ModelAndView();
		HttpSession session = request.getSession();
		String buttonName = request.getParameter("submit");
		Map<Long,EmployeeProject> employeeAndProject = new HashMap<Long,EmployeeProject>();
		ResourceMaster resource = (ResourceMaster)session.getAttribute("resource");
		String creatorName = resource.getEmployeeName();
		String supervisorEnterpriseId = request.getParameter("supervisorEnterpriseId");
		ResourceMaster supervisor =employeeServiceImpl.searchEmployee(supervisorEnterpriseId);
		resourceMaster.setSupervisorId(supervisor.getEmployeeId());
		String password = RandStrGenerator.getRandomString();
		int count = employeeServiceImpl.addNewEmployee(resourceMaster,password,creatorName);
		ArrayList<ResourceMaster> supervisors = new ArrayList<ResourceMaster>();
		supervisors = employeeServiceImpl.allSupervisorDetails();
		ArrayList<ResourceMaster> employees = new ArrayList<ResourceMaster>();
		employees = employeeServiceImpl.allEmployeeDetails();
		Map<Long,String> employeeSupervisor = new HashMap<Long,String>();
		for(ResourceMaster employee:employees)
		{
			String supervisorName = employeeServiceImpl.getSupervisorName(employee.getSupervisorId());
			employeeSupervisor.put(employee.getEmployeeId(),supervisorName);
			EmployeeProject empproj = new EmployeeProject();
			empproj = projectserviceImpl.getEmployeeProjectRoleDetails(employee.getEmployeeId());
			employeeAndProject.put(employee.getEmployeeId(),empproj);
		}
		session.setAttribute("employeeSupervisor", employeeSupervisor);
		session.setAttribute("employeeAndProject", employeeAndProject);
		session.setAttribute("employees", employees);
		session.setAttribute("supervisors", supervisors);
		if(count == 1)
		{
			modelandview.addObject("code","success");
			String body = defaultPwdMail + password;
			List<String> recipients = new ArrayList<String>();
			List<String> cCopy = new ArrayList<String>();
			recipients.add(resourceMaster.getEnterpriseId());
			String subject = "Update your Password";
			Mailer.triggerMail(body,subject,recipients,cCopy);
		}
		else
		{
			modelandview.addObject("code","failure");
		}
		if(buttonName.equals("Save"))
			modelandview.setViewName("employeeDetails");
		else
		{
			List<Location> locations = employeeServiceImpl.getAllLocations();
			List<RoleName> roleNames = employeeServiceImpl.getAllRoleNames();
			session.setAttribute("roleNames", roleNames);
			session.setAttribute("locations", locations);
			modelandview.addObject("resourceMaster", resourceMaster);
			modelandview.addObject("history","no");
			modelandview.setViewName("assignProject");
		}

		return modelandview;
	}
	@RequestMapping(value = "getEmployeeDetailsByProject.do", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView getEmployeeDetailsByProject(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		ModelAndView modelandview = new ModelAndView();
		HttpSession session = request.getSession();
		Integer projectId = Integer.parseInt(request.getParameter("projectId"));
		List<ResourceMaster> employees = new ArrayList<ResourceMaster>();
		Map<Long,EmployeeProject> employeeAndProject = new HashMap<Long,EmployeeProject>();
		String projectName = projectserviceImpl.getProjectName(projectId);
		Map<Long,String> employeeSupervisor = new HashMap<Long,String>();
		if(projectName.equalsIgnoreCase("HCSC"))
			employees = employeeServiceImpl.allEmployeeDetails();
		else	
			employees = employeeServiceImpl.getEmployeeDetailsByProject(projectId);
		for(ResourceMaster employee:employees)
		{
			String supervisorName = employeeServiceImpl.getSupervisorName(employee.getSupervisorId());
			employeeSupervisor.put(employee.getEmployeeId(),supervisorName);
			EmployeeProject empproj = new EmployeeProject();
			empproj =projectserviceImpl.getEmployeeProjectRoleDetails(employee.getEmployeeId());
			employeeAndProject.put(employee.getEmployeeId(),empproj);
		}
		session.setAttribute("employeeSupervisor", employeeSupervisor);
		session.setAttribute("employeeAndProject", employeeAndProject);
		session.setAttribute("employees", employees);
		modelandview.setViewName("employeeDetails");		
		return modelandview;
	}
	@RequestMapping(value = "/deleteEmployee.htm", method = RequestMethod.GET)
	public ModelAndView deleteEmployee(HttpServletRequest request,HttpServletResponse response )
	{
		ModelAndView modelandview = new ModelAndView();
		HttpSession session = request.getSession();
		ArrayList<ResourceMaster> employees = new ArrayList<ResourceMaster>();
		Map<Long,EmployeeProject> employeeAndProject = new HashMap<Long,EmployeeProject>();
		String enterpriseId = request.getParameter("enterpriseid");
		int count = employeeServiceImpl.deleteEmployee(enterpriseId);
		employees = employeeServiceImpl.allEmployeeDetails();
		Map<Long,String> employeeSupervisor = new HashMap<Long,String>();
		for(ResourceMaster employee:employees)
		{
			String supervisorName =employeeServiceImpl.getSupervisorName(employee.getSupervisorId());
			employeeSupervisor.put(employee.getEmployeeId(),supervisorName);
			EmployeeProject empproj = new EmployeeProject();
			empproj = projectserviceImpl.getEmployeeProjectRoleDetails(employee.getEmployeeId());
			employeeAndProject.put(employee.getEmployeeId(),empproj);
		}
		session.setAttribute("employeeSupervisor", employeeSupervisor);
		modelandview.setViewName("employeeDetails");
		session.setAttribute("employeeAndProject", employeeAndProject);		
		session.setAttribute("employees", employees);
		if(count == 1)
		{
			
			modelandview.addObject("deletecode","success");
		}
		else
		{
			modelandview.addObject("deletecode","failure");
		}
		return modelandview;
	
	} 
	@RequestMapping("/approve.htm")
	public ModelAndView getEmployeeNamesToApprove(HttpServletRequest request,HttpServletResponse response)
	{
		ArrayList<ResourceMaster> employees = new ArrayList<ResourceMaster>();
		ModelAndView modelandview = new ModelAndView();
		HttpSession session=request.getSession();
		ResourceMaster resource = (ResourceMaster)session.getAttribute("resource");
		long employeeId = resource.getEmployeeId();
	
		employees = employeeServiceImpl.approve(employeeId);
		modelandview.addObject("employees", employees);
		modelandview.setViewName("approve");
		return modelandview;

	}
	
	@RequestMapping(value = "/changeSupervisor.htm", method = RequestMethod.POST)
	public ModelAndView changeSupervisor(HttpServletRequest request,HttpServletResponse response )
	{
		ModelAndView modelandview = new ModelAndView();
		String supervisorEnterpriseId = request.getParameter("newSupervisor");
		String currentSupervisor = request.getParameter("currentSupervisor");
		HttpSession session = request.getSession();
		ResourceMaster resourcemaster = (ResourceMaster) session.getAttribute("resource");
		Long employeeId = resourcemaster.getEmployeeId();
		ResourceMaster supervisor = new ResourceMaster();
		supervisor = employeeServiceImpl.searchEmployee(supervisorEnterpriseId);
		String supervisorName = supervisor.getEmployeeName();
		Long supervisorId = supervisor.getEmployeeId();
		if(supervisor.getEmployeeName() != null)
		{
			int count = employeeServiceImpl.updateSupervisor(employeeId, supervisorId);
			if(count == 1)
			{
				String body = supervisorUpdateMail;
				String subject = "Supervisor Update";
				List<String> recipients = new ArrayList<String>();
				List<String> cCopy = new ArrayList<String>();
				recipients.add( resourcemaster.getSupervisorEnterpriseId());
				cCopy.add(resourcemaster.getEnterpriseId());
				Mailer.triggerMail(body,subject,recipients,cCopy);
				modelandview.addObject("code", "success");
				modelandview.addObject("supervisorName", supervisorName);
			}
			else
				modelandview.addObject("code", "failure");
		}
		
		else
		{
			modelandview.addObject("code", "invalidid");
			modelandview.addObject("supervisorName", currentSupervisor);
				
		}
		
		modelandview.setViewName("myProfile");
		return modelandview;
	}
	@RequestMapping("/fetchDetails.htm")
	public ModelAndView fetchDetails(HttpServletRequest request,HttpServletResponse response )
	{
		ModelAndView modelandview = new ModelAndView();
		HttpSession session = request.getSession();
		ResourceMaster user = (ResourceMaster) session.getAttribute("resource");
		String username = user.getEmployeeName();
		ResourceMaster resourceToBeEdited = new ResourceMaster();
		String enterpriseId = request.getParameter("enterpriseid");
		resourceToBeEdited = employeeServiceImpl.searchEmployee(enterpriseId);
		List<Project> projects = new ArrayList<Project>();
		projects =  projectserviceImpl.getAllProjects();
		String supervisorName = employeeServiceImpl.getSupervisorName(resourceToBeEdited.getSupervisorId());
		String ProjectName = projectserviceImpl.getProjectName(resourceToBeEdited.getProjectId());
		EmployeeProject employeeProject = projectserviceImpl.getEmployeeProjectRoleDetails(resourceToBeEdited.getEmployeeId()); 
		modelandview.addObject("projects", projects);
		modelandview.addObject("resourceToBeEdited",resourceToBeEdited);
		modelandview.addObject("supervisorName", supervisorName);
		modelandview.addObject("ProjectName", ProjectName);
		modelandview.addObject("username", username);
		session.setAttribute("employeeProject", employeeProject);	
		modelandview.setViewName("updateEmployee");
		return modelandview;
	
	} 
	@RequestMapping(value = "/updateEmployee.htm", method = RequestMethod.POST)
	public ModelAndView updateEmployee(@ModelAttribute ResourceMaster resource,HttpServletRequest request,HttpServletResponse response )
	{
		int count = 0;
		ModelAndView modelandview = new ModelAndView();
		HttpSession session = request.getSession();
		ResourceMaster user = (ResourceMaster) session.getAttribute("resource");
		String creatorName = user.getEmployeeName();
		Long supervisorId = employeeServiceImpl.searchEmployee(resource.getSupervisorEnterpriseId()).getEmployeeId();
		resource.setSupervisorId(supervisorId);
		count = employeeServiceImpl.updateEmployee(resource,creatorName);
		ArrayList<ResourceMaster> employees = new ArrayList<ResourceMaster>();
		ArrayList<ResourceMaster> employeesUnderSupervisor = new ArrayList<ResourceMaster>();
		List<Project> projects = new ArrayList<Project>(); 
		Map<Long,EmployeeProject> employeeAndProject = new HashMap<Long,EmployeeProject>();
		Map<Long,String> employeeSupervisor = new HashMap<Long,String>();
		String[] roles = user.getRoles();
	
		if (user.getEmployeeName().equalsIgnoreCase("administrator"))
		{
			
			projects = projectserviceImpl.getAllProjects();
			employeesUnderSupervisor = employeeServiceImpl.allEmployeeDetails();
			for(ResourceMaster employee:employeesUnderSupervisor)
			{
				String supervisorName = employeeServiceImpl.getSupervisorName(employee.getSupervisorId());
				employeeSupervisor.put(employee.getEmployeeId(),supervisorName);
				EmployeeProject empproj = new EmployeeProject();
				empproj =projectserviceImpl.getEmployeeProjectRoleDetails(employee.getEmployeeId());
				employeeAndProject.put(employee.getEmployeeId(),empproj);
				employees.add(employee);
			}
			session.setAttribute("employeeSupervisor", employeeSupervisor);
			session.setAttribute("employeeAndProject", employeeAndProject);	
			session.setAttribute("projects", projects);
			session.setAttribute("employees", employees);
			modelandview.setViewName("employeeDetails");
		}
		else
		{
			employeesUnderSupervisor = employeeServiceImpl.approve(user.getEmployeeId());
			for(ResourceMaster employee:employeesUnderSupervisor)
			{
				EmployeeProject empproj = new EmployeeProject();
				empproj =projectserviceImpl.getEmployeeProjectRoleDetails(employee.getEmployeeId());
				employeeAndProject.put(employee.getEmployeeId(),empproj);
				employees.add(employee);
			}
			session.setAttribute("employeeAndProject", employeeAndProject);	
			session.setAttribute("employees", employees);
			modelandview.setViewName("myTeam");	
		}
		System.out.println(count);
		if(count >= 1)
		{
			
			modelandview.addObject("updatecode","success");
		}
		else
		{
			modelandview.addObject("updatecode","failure");
		}
		return modelandview;
	} 
	
	@RequestMapping("employeesUnderSupervisor.htm")
	public ModelAndView employeesUnderSupervisor(HttpServletRequest request,HttpServletResponse response)
	{
		ArrayList<ResourceMaster> employees = new ArrayList<ResourceMaster>();
		ArrayList<ResourceMaster> employeesUnderSupervisor = new ArrayList<ResourceMaster>();
		Map<Long,EmployeeProject> employeeAndProject = new HashMap<Long,EmployeeProject>();
		ModelAndView modelandview = new ModelAndView();
		HttpSession session=request.getSession();
		ResourceMaster resource = (ResourceMaster)session.getAttribute("resource");
		long employeeId = resource.getEmployeeId();	
		employees = employeeServiceImpl.approve(employeeId);
		for(ResourceMaster employee:employees)
		{
			EmployeeProject empproj = new EmployeeProject();
			empproj = projectserviceImpl.getEmployeeProjectRoleDetails(employee.getEmployeeId());
			employeeAndProject.put(employee.getEmployeeId(),empproj);
			//employees.add(employee);
		}
		session.setAttribute("employeeAndProject", employeeAndProject);	
		session.setAttribute("employees", employees);
		modelandview.setViewName("myTeam");
		return modelandview;
	}
	@RequestMapping(value = "getSupervisors.htm", method = RequestMethod.GET)
	public void getSupervisors(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		ArrayList<ResourceMaster> employeeObjects = new ArrayList<ResourceMaster>();
		ArrayList<String> enterpriseIds = new ArrayList<String>();
		employeeObjects = employeeServiceImpl.allSupervisorDetails();
		for(ResourceMaster employeeObject : employeeObjects)
		{
			enterpriseIds.add(employeeObject.getEnterpriseId());
		}
		Gson gson = new Gson();
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.println(gson.toJson(enterpriseIds));
	}
	@RequestMapping(value="/myPersonaldetails.htm",method=RequestMethod.POST)
	public ModelAndView myPersonaldetails(@ModelAttribute ResourceMaster resourceMaster,HttpServletRequest request,HttpServletResponse response )
	{
		ModelAndView modelandview = new ModelAndView();
		HttpSession session = request.getSession();
		Long contactNo;
		String passportNo;
		String panNo;
		ResourceMaster resource = (ResourceMaster)session.getAttribute("resource");
		if(request.getParameter("contactNo").isEmpty())
			contactNo = null;
		else
			contactNo=Long.parseLong(request.getParameter("contactNo"));
		if(request.getParameter("passportNo").isEmpty())
			passportNo = null;
		else
			passportNo=request.getParameter("passportNo");
		if(request.getParameter("panNo").isEmpty())
			panNo = null;
		else
			panNo=request.getParameter("panNo");
		String enterpriseId = resource.getEnterpriseId();
        int count=employeeServiceImpl.insertPersonaldetails(contactNo,passportNo,panNo,enterpriseId);
        if(count == 1)
        {
        	String body = myPersonalDetailsUpdateMail;
			String subject = "PersonalDetails Update";
			List<String> recipients = new ArrayList<String>();
			List<String> cCopy = new ArrayList<String>();
			recipients.add(resource.getEnterpriseId());
			Mailer.triggerMail(body,subject,recipients,cCopy);
        	Long supervisorId = resource.getSupervisorId();
			Integer projectId = resource.getProjectId();
			if(projectId!=null){
				String projectName = projectserviceImpl.getProjectName(projectId);
				session.setAttribute("projectName", projectName);
			}
			String supervisorName =employeeServiceImpl.getSupervisorName(supervisorId);
			session.setAttribute("resource", resource);
			session.setAttribute("supervisorName", supervisorName);
			modelandview.addObject("code","success");
			
			}
		else
		{
			modelandview.addObject("code","failure");
		}
        	modelandview.setViewName("myProfile");
        	return modelandview;
		
	}
	@RequestMapping(value= "/SeachSupervisor.htm",  method = RequestMethod.POST) 
	public  @ResponseBody void performSearch(@RequestParam("SUPID") String chars, HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		System.out.println("search");
		int careerLevel = 0;
		HttpSession session = request.getSession();
		ResourceMaster resourcemaster = (ResourceMaster) session.getAttribute("resource");
		if(request.getParameter("level") == null)
			careerLevel = 0;
		else if(resourcemaster.getEnterpriseId().equalsIgnoreCase("admin"))
			careerLevel = Integer.valueOf(request.getParameter("level"));
		else
			careerLevel = resourcemaster.getLevel();
		PrintWriter out = response.getWriter();
		ArrayList<String> enterpriseIds = new ArrayList<String>();
		enterpriseIds=    (ArrayList<String>) employeeServiceImpl.checkSupervisor(chars,careerLevel);
		Gson gson = new Gson();
		response.setContentType("application/json");
		out.println(gson.toJson(enterpriseIds));
	}



@RequestMapping(value = "/changeContact.htm", method = RequestMethod.POST)
	public ModelAndView changeContact(HttpServletRequest request,HttpServletResponse response )
	{
		HttpSession session = request.getSession();
		ModelAndView modelandview = new ModelAndView();
		String supervisorName = request.getParameter("currentSupervisor");
		Long contactNo = Long.parseLong(request.getParameter("newContact"));
		ResourceMaster resourcemaster = (ResourceMaster) session.getAttribute("resource");
		Long employeeId = resourcemaster.getEmployeeId();
		int count =employeeServiceImpl.updateContact(employeeId, contactNo);
		String enterpriseId = resourcemaster.getEnterpriseId();
		ResourceMaster resource = new ResourceMaster();
		resource = employeeServiceImpl.searchEmployee(enterpriseId);
		session.setAttribute("resource", resource);
			if(count == 1)
			{
				String body = contactUpdateMail;
				String subject = "Update Contact Details";
				List<String> recipients = new ArrayList<String>();
				List<String> cCopy = new ArrayList<String>();
				recipients.add(resource.getEnterpriseId());
				Mailer.triggerMail(body,subject,recipients,cCopy);
				modelandview.addObject("contactcode", "success");
				modelandview.addObject("supervisorName", supervisorName);
			}
		else
		{
			modelandview.addObject("contactcode", "invalidid");
			modelandview.addObject("supervisorName", supervisorName);
				
		}
		
		modelandview.setViewName("myProfile");
		return modelandview;
	}
	@RequestMapping("employeesRoleOff.htm")
	public ModelAndView roleOffEmployees(HttpServletRequest request,HttpServletResponse response )
	{
		ModelAndView modelandview = new ModelAndView();
		HttpSession session=request.getSession();
		modelandview.addObject("roleoffdetails", "yes");
		ArrayList<ResourceMaster> employeesUnderSupervisor = new ArrayList<ResourceMaster>();
		ArrayList<ResourceMaster> employees = new ArrayList<ResourceMaster>();
		Map<Long,EmployeeProject> employeeAndProject = new HashMap<Long,EmployeeProject>();		
		ResourceMaster resource = (ResourceMaster)session.getAttribute("resource");
		long employeeId = resource.getEmployeeId();	
		employeesUnderSupervisor = employeeServiceImpl.approve(employeeId);
		Calendar calendar = Calendar.getInstance();
		Date currentDate = calendar.getTime();
		for(ResourceMaster employee:employeesUnderSupervisor)
		{
			EmployeeProject empproj = new EmployeeProject();
			empproj = projectserviceImpl.getEmployeeRoleOffDetails(employee.getEmployeeId());
			//int roleEndDate = empproj.getRoleEndDate().getDate();
			//int todayDate = currentDate.getDate();
			if(empproj.getRoleEndDate() != null)
			{
				
			
				Integer roleEndMonth = empproj.getRoleEndDate().getMonth();
				Integer currentMonth = currentDate.getMonth();
				Integer roleEndYear = empproj.getRoleEndDate().getYear();
				Integer currentYear = currentDate.getYear();
						
				if(currentYear == roleEndYear)
				{
					if(roleEndMonth -currentMonth <= 1 )
					{
						employeeAndProject.put(employee.getEmployeeId(),empproj);
						employees.add(employee);
					}
				}
			}
			
		}
		
		session.setAttribute("employeeAndProject", employeeAndProject);	
		session.setAttribute("employees", employees);
		modelandview.setViewName("myTeam");
		return modelandview;
	}
	@RequestMapping("/recommendToHold.htm")
	public ModelAndView recommendToHold(HttpServletRequest request,HttpServletResponse response )
	{
		ModelAndView modelandview = new ModelAndView();
		HttpSession session=request.getSession();
		int count = 0;
		ResourceMaster resource = (ResourceMaster)session.getAttribute("resource");
		String empid = request.getParameter("employeeId");
		System.out.println(empid);
		Calendar cal = Calendar.getInstance();
		EmployeeProject emp = new EmployeeProject();
		Long employeeId = Long.parseLong(request.getParameter("employeeId"));
		emp = projectserviceImpl.getEmployeeProjectRoleDetails(employeeId);
		String potentialFutureRole = request.getParameter("potentialFutureRole");
		Date roleEndDate = emp.getRoleEndDate();		 
		   Calendar calendar = Calendar.getInstance();
		  calendar.setTime(roleEndDate);
		  calendar.add(Calendar.DAY_OF_YEAR, 1);
		  Date hubStartDate = calendar.getTime();
		System.out.println(hubStartDate);
		String creator = resource.getEmployeeName();
		count = employeeServiceImpl.recommendToHoldEmployee(employeeId, potentialFutureRole,hubStartDate,creator);
		ArrayList<ResourceMaster> employeesUnderSupervisor = new ArrayList<ResourceMaster>();
		ArrayList<ResourceMaster> employees = new ArrayList<ResourceMaster>();
		Map<Long,EmployeeProject> employeeAndProject = new HashMap<Long,EmployeeProject>();
		
		long userId = resource.getEmployeeId();	
		employeesUnderSupervisor = employeeServiceImpl.approve(userId);
		for(ResourceMaster employee:employeesUnderSupervisor)
		{
			EmployeeProject empproj = new EmployeeProject();
			empproj = projectserviceImpl.getEmployeeProjectRoleDetails(employee.getEmployeeId());
			employeeAndProject.put(employee.getEmployeeId(),empproj);
			employees.add(employee);
		}
		if(count == 1)
			modelandview.addObject("hubcode","success");
		else
			modelandview.addObject("hubcode","failure");
		
		session.setAttribute("employeeAndProject", employeeAndProject);	
		session.setAttribute("employees", employees);
		modelandview.setViewName("myTeam");
		return modelandview;
	}
	@RequestMapping("employeesOnHub.htm")
	public ModelAndView employeesOnHub(HttpServletRequest request,HttpServletResponse response)
	{
		ArrayList<ResourceMaster> employees = new ArrayList<ResourceMaster>();
		ArrayList<ResourceMaster> employeesUnderSupervisor = new ArrayList<ResourceMaster>();
		Map<Long,EmployeeHub> employeeAndHub = new HashMap<Long,EmployeeHub>();
		Map<Long,EmployeeProject> employeeAndProject = new HashMap<Long,EmployeeProject>();
		ModelAndView modelandview = new ModelAndView();
		HttpSession session=request.getSession();
		ResourceMaster resource = (ResourceMaster)session.getAttribute("resource");
		long employeeId = resource.getEmployeeId();	
		if(resource.getEnterpriseId().equalsIgnoreCase("admin"))
			employeesUnderSupervisor = employeeServiceImpl.allEmployeeDetails();
		else
			employeesUnderSupervisor = employeeServiceImpl.approve(employeeId);
		for(ResourceMaster employee:employeesUnderSupervisor)
		{
			
			EmployeeHub emphub = new EmployeeHub();
			emphub = employeeServiceImpl.getEmployeeOnHub(employee.getEmployeeId());
			if(emphub.getEmployeeId() != null)
			{
				EmployeeProject empproj = new EmployeeProject();
				empproj = projectserviceImpl.getEmployeeProjectRoleDetails(emphub.getEmployeeId());
				employeeAndProject.put(emphub.getEmployeeId(),empproj);
				employees.add(employeeServiceImpl.searchEmployee(employee.getEnterpriseId()));
				employeeAndHub.put(employee.getEmployeeId(),emphub);
			}
			
		}
		List<ProjectLocation> projectLocationDetails = projectserviceImpl.getAllProjectLocationDetails();
		session.setAttribute("projectLocationDetails",projectLocationDetails);
		session.setAttribute("employeeAndProject", employeeAndProject);	
		session.setAttribute("employeeAndHub", employeeAndHub);	
		session.setAttribute("employees", employees);
		modelandview.setViewName("employeeHub");
		return modelandview;
	}
	@RequestMapping("changePassword.htm")
	public ModelAndView changePassword(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView modelandview = new ModelAndView();
		HttpSession session=request.getSession();
		ResourceMaster resource = (ResourceMaster)session.getAttribute("resource");
		String password = request.getParameter("pass");
		int count  = employeeServiceImpl.changePassword(resource, password);
		if(count == 1)
		{
			String body = pwdUpdateMail;
			String subject = "Password Update";
			List<String> recipients = new ArrayList<String>();
			List<String> cCopy = new ArrayList<String>();
			recipients.add(resource.getEnterpriseId());
			Mailer.triggerMail(body,subject,recipients,cCopy);
			modelandview.setViewName("Login");
		}
		return modelandview;
	}
	@RequestMapping("/holdOrRoleOff.htm")
	public ModelAndView holdOrRoleOff(HttpServletRequest request,HttpServletResponse response ) throws ParseException
	{
		ModelAndView modelandview = new ModelAndView();
		HttpSession session=request.getSession();
		ArrayList<ResourceMaster> employeesUnderSupervisor = new ArrayList<ResourceMaster>();
		ArrayList<ResourceMaster> employees = new ArrayList<ResourceMaster>();
		Map<Long,EmployeeProject> employeeAndProject = new HashMap<Long,EmployeeProject>();
		Map<Long,EmployeeHub> employeeAndHub = new HashMap<Long,EmployeeHub>();
		int count = 0;
		ResourceMaster resource = (ResourceMaster)session.getAttribute("resource");	
		Long employeeId = Long.parseLong(request.getParameter("employeeId"));		
		String potentialFutureRole = null;
		String roleOff = null;
		Date roleOffDate = null;
		String holdorroleoff = request.getParameter("holdorroleoff");	
		if(holdorroleoff.equalsIgnoreCase("Hold"))
		{
			potentialFutureRole = request.getParameter("potentialFutureRole");
			count = employeeServiceImpl.holdOrRoleOff(employeeId, potentialFutureRole, roleOffDate);
			if(count == 1)
				modelandview.addObject("holdcode", "success");
			else
				modelandview.addObject("holdcode", "failure");
		}
		else if(holdorroleoff.equalsIgnoreCase("RoleOff"))
		{
			roleOff = request.getParameter("roleOffDate");
			roleOffDate=new SimpleDateFormat("MM/dd/yyyy").parse(roleOff);
			count = employeeServiceImpl.holdOrRoleOff(employeeId, potentialFutureRole, roleOffDate);
			if(count == 1)
				modelandview.addObject("roleoffcode", "success");
			else
				modelandview.addObject("roleoffcode", "failure");

		}
		
		if(resource.getEnterpriseId().equalsIgnoreCase("admin"))
			employeesUnderSupervisor = employeeServiceImpl.allEmployeeDetails();
		else
			employeesUnderSupervisor = employeeServiceImpl.approve(resource.getEmployeeId());
		for(ResourceMaster employee:employeesUnderSupervisor)
		{
			
			EmployeeHub emphub = new EmployeeHub();
			emphub = employeeServiceImpl.getEmployeeOnHub(employee.getEmployeeId());
			if(emphub.getEmployeeId() != null)
			{
				EmployeeProject empproj = new EmployeeProject();
				empproj = projectserviceImpl.getEmployeeProjectRoleDetails(emphub.getEmployeeId());
				employeeAndProject.put(emphub.getEmployeeId(),empproj);
				employees.add(employeeServiceImpl.searchEmployee(employee.getEnterpriseId()));
				employeeAndHub.put(employee.getEmployeeId(),emphub);
			}
			
		}
		List<ProjectLocation> projectLocationDetails = projectserviceImpl.getAllProjectLocationDetails();
		session.setAttribute("projectLocationDetails",projectLocationDetails);
		session.setAttribute("employeeAndProject", employeeAndProject);	
		session.setAttribute("employeeAndHub", employeeAndHub);	
		session.setAttribute("employees", employees);
		modelandview.setViewName("employeeHub");
		return modelandview;
	}
	@RequestMapping("checkPassword.htm")
	  	public ModelAndView checkPassword(HttpServletRequest request,HttpServletResponse response)
	  	{
	  		ModelAndView modelandview = new ModelAndView();
	  		HttpSession session=request.getSession();
	  		int count  = 0;
	  		ResourceMaster resource = (ResourceMaster)session.getAttribute("resource");
	  		String password = request.getParameter("oldPassword");
	  		if(resource.getPassword().equals(password))
	  		{
	  			password = request.getParameter("password");
	  			count  = employeeServiceImpl.changePassword(resource, password);
	  			if(count == 1)
	  			{
	  				String body = pwdUpdateMail;
	  				String subject = "Password Update";
	  				List<String> recipients = new ArrayList<String>();
	  				List<String> cCopy = new ArrayList<String>();
	  				recipients.add(resource.getEnterpriseId());
	  				Mailer.triggerMail(body,subject,recipients,cCopy);
	  				modelandview.addObject("checkpass", "success");
	  				modelandview.setViewName("Login");
	  			}
	  			else
	  			{
	  				modelandview.addObject("checkpass","failure");
	  				modelandview.setViewName("updatePassword");
	  				return modelandview;
	  			}
	  				
	  		}
	  		else
	  		{
	  			modelandview.addObject("checkpass","incorrect");
	  			modelandview.setViewName("updatePassword");
	  			return modelandview;
	  		}
	  		
	  		
	  		
	  		return modelandview;
	  		
	  	}
	@RequestMapping(value = "/excelUpload.htm", method = RequestMethod.POST)
	public ModelAndView excelUpload(Model model, @RequestParam("excelfile") MultipartFile excelfile,HttpServletRequest request,HttpServletResponse response) throws IOException  {	
		ModelAndView modelandview = new ModelAndView();
		XSSFWorkbook workbook = new XSSFWorkbook(excelfile.getInputStream());
		XSSFSheet worksheet = workbook.getSheetAt(0);
		int i = 1;
		HttpSession session=request.getSession();
		ResourceMaster currentUser = (ResourceMaster)session.getAttribute("resource");
		String creatorName = currentUser.getEmployeeName();
		while (i <= worksheet.getLastRowNum()) {
			ResourceMaster resource = new ResourceMaster();
			XSSFRow row = worksheet.getRow(i++);
			resource.setEmployeeId((new Double(row.getCell(0).getNumericCellValue())).longValue());
			resource.setEmployeeName(row.getCell(2).getStringCellValue());
			resource.setEnterpriseId(row.getCell(4).getStringCellValue());
			String designation = row.getCell(5).getStringCellValue();
			CareerLevel careerLevel = careerLevelService.getCarrerLevelByDesignation(designation);
			Integer level = careerLevel.getLevel();
			resource.setLevel(level);
			
			String location = row.getCell(9).getStringCellValue();
			Integer locationId = employeeServiceImpl.getEmpLocationIdByName(location);
			if(locationId != null)
				resource.setLocationId(locationId);
			String projectName = null;
			Integer projectId = null;
			if(row.getCell(10) != null)
			{
				projectName = row.getCell(10).getStringCellValue();
			
			
				 projectId = projectserviceImpl.getProjectIdByName(projectName);
			}
			if(projectId != null)
			{
				resource.setProjectId(projectId);
				String hcscmail = "";
				hcscmail = row.getCell(6).getStringCellValue();
				if(hcscmail != null && !hcscmail.isEmpty())
					resource.setHcscMailId(hcscmail);
			}
			
			String technologyName =  row.getCell(14).getStringCellValue();
			Integer technologyId = technologyserviceImpl.getTechIdByName(technologyName);
			if(null == technologyId || technologyId == 0)
				technologyId = 1;
			resource.setTechnologyId(technologyId);
			
			Date rollOnDate = row.getCell(16).getDateCellValue();
			resource.setRoleOnDate(rollOnDate);
			
			Date rollOffDate = row.getCell(17).getDateCellValue();
			resource.setRoleOffDate(rollOffDate);			
			
				
			
			String[] roles = {"Default"};
			resource.setRoles(roles);
			String status = row.getCell(19).getStringCellValue();
			if(status.equalsIgnoreCase("active"))
				resource.setActive(true);
			else
				resource.setActive(false);
			String password = RandStrGenerator.getRandomString();
			int count = employeeServiceImpl.addNewEmployee(resource,password,creatorName);
			if(count == 1)
			{
				String body = defaultPwdMail + password;
				String subject = "Update your Password";
				List<String> recipients = new ArrayList<String>();
				List<String> cCopy = new ArrayList<String>();
				recipients.add(resource.getEnterpriseId());
				Mailer.triggerMail(body,subject,recipients,cCopy);
			}
			if(projectId != null)
			{
				Date bflexenddate = row.getCell(18).getDateCellValue();
				resource.setRoleOffDate(rollOffDate);		
				EmployeeProject employeeProject = new EmployeeProject();
				employeeProject.setEmployeeId(resource.getEmployeeId());
				String lanId = row.getCell(7).getStringCellValue();
				employeeProject.setLanId(lanId);
				employeeProject.setProjectId(projectId);				
				employeeProject.setRoleName("v0000007");
				employeeProject.setRoleStartDate(rollOnDate);
				employeeProject.setRoleEndDate(bflexenddate);
				projectserviceImpl.assignProject(employeeProject, creatorName, locationId);
			}
		}	
		modelandview.setViewName("employeeExcelUpload");
		return modelandview;
	}
	
	@RequestMapping(value = "/uploadPic", method = RequestMethod.POST)
	public ModelAndView uploadPic(Model model, FileUpload uploadItem,HttpServletRequest request,HttpServletResponse response) throws IOException {
		List<MultipartFile> files = uploadItem.getFileData();
		InputStream inputStream = null;
		ModelAndView modelandview = new ModelAndView();
		for(MultipartFile file : files)
		{
		if (file.getSize() > 0)
			inputStream = file.getInputStream();
		byte[] imageData = IOUtils.toByteArray(inputStream);
		HttpSession session = request.getSession();
		ResourceMaster resource = (ResourceMaster)session.getAttribute("resource");
		Long employeeId = resource.getEmployeeId();
		int count = 0;
		count = employeeServiceImpl.uploadEmployeePicture(imageData, employeeId);
		if(count == 1)
		{
			String body = profilePhotoMail;
			String subject = "Profile Picture Update";
			List<String> recipients = new ArrayList<String>();
			List<String> cCopy = new ArrayList<String>();
			recipients.add(resource.getEnterpriseId());
			Mailer.triggerMail(body,subject,recipients,cCopy);
			modelandview.addObject("photoUpload", "success");
		}
		else
			modelandview.addObject("photoUpload","failure");
		
		//byte[] encodeBase64 = Base64.getEncoder().encode(imageData);/*encode(imageData);*/
		byte[] encodeBase64 = Base64.encode(resource.getEmployeePhoto());
		String base64Encoded = new String(encodeBase64,"UTF-8");
		resource.setBase64Jmage(base64Encoded);
		session.setAttribute("resource", resource);
		}
		modelandview.setViewName("myPersonaldetails");
		return modelandview;
	}
	
	
	@RequestMapping(value = "/excelUpdate.htm", method = RequestMethod.POST)
	public ModelAndView excelUpdate(Model model, @RequestParam("excelfile") MultipartFile excelfile,HttpServletRequest request,HttpServletResponse response) throws IOException  {	
		ModelAndView modelandview = new ModelAndView();
		XSSFWorkbook workbook = new XSSFWorkbook(excelfile.getInputStream());
		XSSFSheet worksheet = workbook.getSheetAt(0);
		int i = 1;
		HttpSession session=request.getSession();
		ResourceMaster currentUser = (ResourceMaster)session.getAttribute("resource");
		String creatorName = currentUser.getEmployeeName();
		while (i <= worksheet.getLastRowNum()) {
			ResourceMaster resource = new ResourceMaster();
			XSSFRow row = worksheet.getRow(i++);
			Integer locationId = null;
			if(row.getCell(0) != null)
			{
				resource.setEmployeeId((new Double(row.getCell(0).getNumericCellValue())).longValue());
				String designation = row.getCell(1).getStringCellValue();
				CareerLevel careerLevel = careerLevelService.getCarrerLevelByDesignation(designation);
				Integer level = careerLevel.getLevel();
				resource.setLevel(level);
				if(row.getCell(3) != null)
				{
					String location = row.getCell(3).getStringCellValue();
				locationId = employeeServiceImpl.getEmpLocationIdByName(location);
				}
				if(locationId != null)
					resource.setLocationId(locationId);
				if(row.getCell(5) != null)
				{
				Date rollOffDate = row.getCell(5).getDateCellValue();
				resource.setRoleOffDate(rollOffDate);
				}
				if(row.getCell(7) != null)
				{
				String status = row.getCell(7).getStringCellValue();
				if(status.equalsIgnoreCase("active"))
					resource.setActive(true);
				else
					resource.setActive(false);
				}
				String projectName = null;
				Integer projectId = null;
				if(row.getCell(9) != null)
				{
					projectName = row.getCell(10).getStringCellValue();				
					projectId = projectserviceImpl.getProjectIdByName(projectName);
				}
				if(projectId != null)
					resource.setProjectId(projectId);
			}
			
			String projectName = null;
			Integer projectId = null;
			if(row.getCell(10) != null)
			{
				projectName = row.getCell(10).getStringCellValue();
			
			
				 projectId = projectserviceImpl.getProjectIdByName(projectName);
			}
			if(projectId != null)
			{
				resource.setProjectId(projectId);
				String hcscmail = "";
				hcscmail = row.getCell(6).getStringCellValue();
				if(hcscmail != null && !hcscmail.isEmpty())
					resource.setHcscMailId(hcscmail);
			}
						
			int count = employeeServiceImpl.updateEmployee(resource, creatorName);
			if(projectId != null)
			{
				if(row.getCell(6) != null)
				{
					Date bflexenddate = row.getCell(6).getDateCellValue();
					EmployeeProject employeeProject = new EmployeeProject();
					employeeProject.setEmployeeId(resource.getEmployeeId());
					employeeProject.setProjectId(projectId);				
					employeeProject.setRoleEndDate(bflexenddate);
					projectserviceImpl.assignProject(employeeProject, creatorName, locationId);
				}
			}
		}	
		modelandview.setViewName("employeeExcelUpdate");
		return modelandview;
	}
}
