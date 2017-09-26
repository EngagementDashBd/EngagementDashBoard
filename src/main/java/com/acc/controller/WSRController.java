package com.acc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.acc.entity.Project;
import com.acc.entity.ResourceMaster;
import com.acc.entity.ResourceTaskAndVocation;
import com.acc.entity.ResourceTaskHelper;
import com.acc.entity.WeeklyStatus;
import com.acc.service.EmployeeServiceFacade;
import com.acc.service.ProjectServiceFacade;

import com.acc.service.WSRServiceFacade;
import com.google.gson.Gson;
@Controller

public class WSRController {
	/*@Autowired
	ServiceFacade serviceImpl;*/
	@Autowired
	EmployeeServiceFacade employeeServiceImpl;
	@Autowired
	WSRServiceFacade wsrServiceImpl;
	@Autowired
	ProjectServiceFacade projectserviceImpl;
	
	@RequestMapping("saveWeeklyStatus.htm")
	public  @ResponseBody void  saveWeeklyStatus(@ModelAttribute("WEEKLYSTAT")  WeeklyStatus weeklyResource, HttpServletRequest request,HttpServletResponse response ) throws IOException
	{
		
		HttpSession session = request.getSession();
		List<ResourceMaster> resourcesInProject = new ArrayList<ResourceMaster>();
		ArrayList<ResourceMaster> employeeObjectswsr = new ArrayList<ResourceMaster>();
		
		int projectId = 0;
		String projectID= request.getParameter("applicationName");
		try
		{
			projectId= Integer.parseInt(projectID);
			session.setAttribute("proID", projectId);
			
		}
		catch(NumberFormatException e)
		{
				e.getMessage();
		}
		List<Project> allProjectName = new ArrayList<Project>();
		allProjectName = projectserviceImpl.getAllProjects();
		String ProjectName = projectserviceImpl.getProjectName(projectId);
	
		// setting session attribute : to be used by addresourceandvacation
		session.setAttribute("wsr", weeklyResource);
		session.setAttribute("appName", ProjectName);
		
		resourcesInProject = employeeServiceImpl.getEmployeeDetailsByProject(projectId);
	}
	
		
	
	
	
	@RequestMapping("/addWeeklyStatus.htm")
	public ModelAndView  addWeeklyStatus(@ModelAttribute  WeeklyStatus weeklyResource1,@ModelAttribute ResourceTaskHelper resourceTask, HttpServletRequest request,HttpServletResponse response )
	{
		
		ModelAndView modelandview = new ModelAndView();
		HttpSession session = request.getSession();
		List<ResourceMaster> resourcesInProject = new ArrayList<ResourceMaster>();
		ArrayList<ResourceMaster> employeeObjectswsr = new ArrayList<ResourceMaster>();
		//session.setAttribute("weeklyResource", weeklyResource1);	
	//	session.setAttribute("a", weeklyResource1);	
		int projectId = 0;
		String projectID= request.getParameter("applicationName");
		try
		{
			projectId= Integer.parseInt(projectID);
		}
		catch(NumberFormatException e)
		{
			
			e.getMessage();
		}
		List<Project> allProjectName = new ArrayList<Project>();
		allProjectName = projectserviceImpl.getAllProjects();
		String ProjectName = projectserviceImpl.getProjectName(projectId);
		
		
		resourcesInProject = employeeServiceImpl.getEmployeeDetailsByProject(projectId);
		
		modelandview.addObject("employeesData", employeeObjectswsr);
		
		
		//System.out.println("asdf"+status.getWeekStart());
		
		
		
		//int count= wsrServiceImpl.addWeeklyStatus(weeklyResource, ProjectName);
		
		
		
		
		modelandview.addObject("resourcesInProject",resourcesInProject);
		modelandview.addObject("ProjectNames", allProjectName);
		modelandview.addObject("setTab", "resourceTab");
		modelandview.setViewName("WeeklyStatus");
		/*if(count == 1)
		{
			modelandview.addObject("code", "success");
		}
		else
		{	
			modelandview.addObject("code", "failure");	
		}*/
	
		return modelandview;
	}
	
	
	
	
	@RequestMapping("/addResourceTask.htm")
	public ModelAndView  AddResourceTask (@ModelAttribute ResourceTaskHelper resourceTask, HttpServletRequest request,HttpServletResponse response )
	{
	
		ModelAndView modelandview = new ModelAndView();
		HttpSession session = request.getSession();
		@SuppressWarnings("unused")
		WeeklyStatus status= (WeeklyStatus) session.getAttribute("wsr");
		String ProjName= (String) session.getAttribute("appName");
		//status.getApplicationName();
		List<ResourceMaster> resourcesInProject = new ArrayList<ResourceMaster>();
		ArrayList<ResourceMaster> employeeObjectswsr = new ArrayList<ResourceMaster>();
		
		System.out.println(status.getApplicationName());
		
	//	WeeklyStatus status = (WeeklyStatus)session.getAttribute("weeklyResource");
		
		int projectId = 0;
		String projectID= request.getParameter("applicationName");
		try
		{
			projectId= Integer.parseInt(projectID);
		}
		catch(NumberFormatException e)
		{
			
			e.getMessage();
		}
		List<Project> allProjectName = new ArrayList<Project>();
		allProjectName = projectserviceImpl.getAllProjects();
		String ProjectName =projectserviceImpl.getProjectName(projectId);
		
		
		resourcesInProject = employeeServiceImpl.getEmployeeDetailsByProject(projectId);
		
		modelandview.addObject("employeesData", employeeObjectswsr);
		
		
		
		int count=0;
		
	try
		{
				
		
		count = wsrServiceImpl.addResourceTask(status, resourceTask,ProjName );
		
		}
	catch(Exception e)
	{
		e.getMessage();
		
	}
		System.out.println("count after add resource");
		
		
		modelandview.addObject("resourcesInProject",resourcesInProject);
		modelandview.addObject("ProjectNames", allProjectName);
		modelandview.setViewName("WeeklyStatus");
		if(count == 1)
		{
			modelandview.addObject("code", "success");
		}
		else
		{	
			modelandview.addObject("code", "failure");	
		}
	
		return modelandview;
	}
		
	@RequestMapping("weeklyStatusReport.htm")
	public ModelAndView weeklyStatusReport( HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView modelandview= new ModelAndView();
		HttpSession session=request.getSession();
		List<ResourceMaster> resourcesInProject = new ArrayList<ResourceMaster>();
	
		ResourceMaster resource = (ResourceMaster)session.getAttribute("resource");
		
		List<Project> allProjectName = new ArrayList<Project>();
		allProjectName = projectserviceImpl.getAllProjects();
	
		modelandview.addObject("resource", resource);
		modelandview.addObject("ProjectNames", allProjectName);
		modelandview.addObject("resourcesInProject",resourcesInProject);
		modelandview.setViewName("WeeklyStatus");
		
		return   modelandview;
	}
	
	@RequestMapping("/getResoursebyProjectID.htm") 
	public   @ ResponseBody void getResourseByProjectID( @RequestParam("PROID") String projectID, HttpServletResponse response) throws IOException
	{
		
		//System.out.println("inside the get resource projectID");
		//on the basis of project ID we have to search all the employee
		ModelAndView modelandview= new ModelAndView();
		List<ResourceMaster> resourcesInProject = new ArrayList<ResourceMaster>();
		PrintWriter out = response.getWriter();
		//System.out.println("chars" + chars);
		ArrayList<String> resource = new ArrayList<String>();
		int projectId= Integer.parseInt(projectID);

		
		resourcesInProject = employeeServiceImpl.getEmployeeDetailsByProject(projectId);
		//modelandview.addObject("resourcesInProject", resourcesInProject);

		//return new ModelAndView("resourceView");
		
	}
	@RequestMapping("addResourceTaskandVacation.htm")
	public ModelAndView addresourceprojectwise(/*@RequestParam("PROID") String PROID,*/ HttpServletRequest request,HttpServletResponse response )
	{
		String code=null;
		ModelAndView modelandview= new ModelAndView();
		HttpSession session=request.getSession();
		List<ResourceMaster> resourcesInProject = new ArrayList<ResourceMaster>();
		String projectID= request.getParameter("applicationName");
		int projectId=0;
		try
		{
			projectId= Integer.parseInt(projectID);
			session.setAttribute("proID", projectId);
			
		}
		catch(NumberFormatException e)
		{
				e.getMessage();
		}
		try
		{
		//int projectId=  (Integer) session.getAttribute("proID");
		resourcesInProject = employeeServiceImpl.getEmployeeDetailsByProject(projectId);
		}
		catch(Exception e)
		{
		
			 code="Invalid Session";
		
		}
		
		modelandview.addObject("code", code);
		modelandview.addObject("resourcesInProject", resourcesInProject);
		modelandview.setViewName("resourceView");
		return modelandview;
	
	} 
	@RequestMapping("getWsrRecords.htm")
	public ModelAndView getWsrRecordbyProjectID(@RequestParam("programNames") String projectNames, HttpServletRequest request,HttpServletResponse response )
	{
		ModelAndView modelandview= new ModelAndView();
	
		System.out.println("inside the getWst" +projectNames );
		List<ResourceMaster> resourcesInProject = new ArrayList<ResourceMaster>();
		
	
		List<WeeklyStatus> recordsByProjectName = new ArrayList<WeeklyStatus>();

		recordsByProjectName  = wsrServiceImpl.getWSRRecordsByProject(projectNames);
		System.out.println("wrs:" + recordsByProjectName);
	//	resourcesInProject = employeeServiceImpl.getEmployeeDetailsByProject(projectId);
		modelandview.addObject("recordsByProjectName", recordsByProjectName);
		modelandview.setViewName("resourceTaskReport");
		//modelandview.setViewName("WeeklyStatus");

		//System.out.println("inside the function"+ resource);
		return modelandview;
	
	}

	
	
	@RequestMapping("getResourceVacationDetails.htm")
	public ModelAndView getResourceVacationRecord(@RequestParam("PROID") String PROID, HttpServletRequest request,HttpServletResponse response )
	{
		ModelAndView modelandview= new ModelAndView();
		WeeklyStatus wrsRecordsByWorkID = new WeeklyStatus();
		List<ResourceTaskAndVocation> recordsByWorkID = new ArrayList<ResourceTaskAndVocation>();
	
		try
		{
		int workid= Integer.parseInt(PROID);
		recordsByWorkID  = wsrServiceImpl.getVacationdetailsByWorkID(workid);
		wrsRecordsByWorkID  = wsrServiceImpl.getWSRRecordsByWorkID(workid);
		System.out.println("wsr details"+ wrsRecordsByWorkID);
		}
		catch(Exception e)
		{
			System.out.println("error in the parse value"+ e.getMessage());
			
		}
		System.out.println("inside the controler "+ PROID);
		modelandview.addObject("recordsByWorkID", recordsByWorkID);
		modelandview.addObject("wrsRecordsByWorkID", wrsRecordsByWorkID);
		modelandview.setViewName("resourceVacation");
		return modelandview;
	
	}

	@RequestMapping("getWsrReport.htm")
	public ModelAndView getWSRRecods( HttpServletRequest request,HttpServletResponse response )
	{
		ModelAndView modelandview= new ModelAndView();
		List<Project> allProjectName = new ArrayList<Project>();
		allProjectName = projectserviceImpl.getAllProjects();
		System.out.println("all projects are "+allProjectName );
		modelandview.addObject("ProjectNames", allProjectName);
		modelandview.setViewName("wsrReportContainer");
		return modelandview;
	}
	
	
}
