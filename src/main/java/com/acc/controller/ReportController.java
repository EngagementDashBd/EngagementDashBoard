package com.acc.controller;

import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.acc.entity.CalendarData;
import com.acc.entity.CareerLevel;
import com.acc.entity.DayData;
import com.acc.entity.EmployeeProject;
import com.acc.entity.Portfolio;
import com.acc.entity.Program;
import com.acc.entity.Project;
import com.acc.entity.ProjectLocation;
import com.acc.entity.ResourceMaster;
import com.acc.service.EmployeeServiceFacade;
import com.acc.service.PortfolioServiceFacade;
import com.acc.service.ProgramServiceFacade;
import com.acc.service.ProjectServiceFacade;
import com.acc.service.ReportServiceFacade;
//import com.acc.service.ServiceFacade;
@Controller
public class ReportController {
	/*@Autowired
	ServiceFacade serviceImpl;*/
	@Autowired
	ProjectServiceFacade projectserviceImpl;
	@Autowired
	EmployeeServiceFacade employeeServiceImpl;
	@Autowired
	ReportServiceFacade reportServiceImpl;
	@Autowired
	PortfolioServiceFacade portfolioserviceImpl;
	@Autowired
	ProgramServiceFacade programserviceImpl;
	@RequestMapping(value = "/generateReport.xls", method = RequestMethod.POST)
	public ModelAndView generateReport(HttpServletRequest request,HttpServletResponse response)
	{
		HttpSession session=request.getSession();
		ResourceMaster resource = (ResourceMaster)session.getAttribute("resource");
		ArrayList<ResourceMaster> employeeObjects = new ArrayList<ResourceMaster>();
		long supervisorId = resource.getEmployeeId();
		ModelAndView modelandview = new ModelAndView();
		String month = request.getParameter("monthName");
		int position = month.indexOf(',');
		int	year = Integer.parseInt(month.substring(position + 1));
		month = (month.substring(0, position)).toLowerCase();
		String reportType = request.getParameter("submit");
		List<CalendarData> calendarDataList = new ArrayList<CalendarData>();
		if(resource.getEnterpriseId().equalsIgnoreCase("admin"))
			employeeObjects = employeeServiceImpl.allEmployeeDetails();
		else
			employeeObjects = employeeServiceImpl.approve(supervisorId);
		Map<ResourceMaster, ArrayList> empObjectAndData = new HashMap();
		Map<Long, String> employeeAndTeam = new HashMap<Long, String>();
		int monthReportFortnightValue = 3;
		for(ResourceMaster employee : employeeObjects)
		{
			ArrayList<Integer> reportData = reportServiceImpl.generateReport(month,year,monthReportFortnightValue,employee.getEmployeeId());
			CalendarData calendarData = reportServiceImpl.getCalendarData(employee.getEmployeeId(),monthReportFortnightValue, month, year);
			calendarDataList.add(calendarData);
			empObjectAndData.put(employee, reportData);
			String teamName = reportServiceImpl.getTeamName(employee.getProjectId());
			employeeAndTeam.put(employee.getEmployeeId(), teamName);
		}
		List<CareerLevel> allCareerLevels = new ArrayList<CareerLevel>();
		allCareerLevels = reportServiceImpl.getAllCareerLevels();
		List<Program> allPrograms =programserviceImpl.getAllProjectPrograms();
		ArrayList<Portfolio> allPortfolios = portfolioserviceImpl.getPortfolioDetails();
		List<Project> allProjects = projectserviceImpl.getAllProjects();
		List<ProjectLocation> projectLocationDetails = projectserviceImpl.getAllProjectLocationDetails();
		modelandview.addObject("projectLocationDetails", projectLocationDetails);
		modelandview.addObject("empObjectAndData", empObjectAndData);
		modelandview.addObject("month", month);
		modelandview.addObject("allCareerLevels", allCareerLevels);
		modelandview.addObject("calendarDataList", calendarDataList);
		modelandview.addObject("allPrograms", allPrograms);
		modelandview.addObject("allPortfolios", allPortfolios);
		modelandview.addObject("allProjects",allProjects);
		modelandview.addObject("employeeAndTeam", employeeAndTeam);
		if(reportType.equals("Download as PDF"))
			modelandview.setViewName("pdfView");
		else
			modelandview.setViewName("excelView");
		return modelandview;
		
	}
	@RequestMapping(value = "/generateMidRangeReport.xls", method = RequestMethod.POST)
	public ModelAndView generateMidRangeReport(HttpServletRequest request,HttpServletResponse response) throws ParseException
	{
		HttpSession session=request.getSession();
		ResourceMaster resource = (ResourceMaster)session.getAttribute("resource");
		long supervisorId = resource.getEmployeeId();
		ModelAndView modelandview = new ModelAndView();
		ArrayList<ResourceMaster> employeeObjects = new ArrayList<ResourceMaster>();
		String startMonth = request.getParameter("startMonthName");
		String endMonth = request.getParameter("endMonthName");
		
		String monthRange = startMonth + " to " + endMonth;
		int startPosition = startMonth.indexOf(',');
		int	startYear = Integer.parseInt(startMonth.substring(startPosition + 1));
		startMonth = (startMonth.substring(0, startPosition)).toLowerCase();
		
		int endposition = endMonth.indexOf(',');
		int	endYear = Integer.parseInt(endMonth.substring(endposition + 1));
		endMonth = (endMonth.substring(0, endposition)).toLowerCase();			
		String reportType = request.getParameter("submit");
		List<CalendarData> calendarDataList = new ArrayList<CalendarData>();
		List<String> roles = employeeServiceImpl.getRoles(resource.getEmployeeId());
		if(roles.contains("ADMIN"))
			employeeObjects = employeeServiceImpl.allEmployeeDetails();
		else
			employeeObjects = employeeServiceImpl.approve(supervisorId);
		Map<ResourceMaster, ArrayList> empObjectAndData = new HashMap();
		Map<Long, String> employeeAndTeam = new HashMap<Long, String>();
		for(ResourceMaster employee : employeeObjects)
		{
			CalendarData calendarData1 = reportServiceImpl.getCalendarData(employee.getEmployeeId(),2, startMonth, startYear);
			CalendarData calendarData2 = reportServiceImpl.getCalendarData(employee.getEmployeeId(), 1, endMonth, endYear);
			/*List<DayData> dayData1 = calendarData1.getDayData();
			List<DayData> dayData2 = calendarData2.getDayData();
			for(DayData d : dayData2)
			{
				dayData1.add(d);
			}*/
			//calendarData1.setDayData(dayData1);
			calendarDataList.add(calendarData1);
			calendarDataList.add(calendarData2);
			
			ArrayList<Integer> reportData1 = reportServiceImpl.generateReport(startMonth,startYear,2,employee.getEmployeeId());
			ArrayList<Integer> reportData2 =reportServiceImpl.generateReport(endMonth,endYear,1,employee.getEmployeeId());
			for(int i = 0; i < reportData1.size() ; i++)
			{
				int sum = reportData1.get(i) + reportData2.get(i); 
				reportData1.set(i, sum);
			}
			empObjectAndData.put(employee, reportData1);
			String teamName = reportServiceImpl.getTeamName(employee.getProjectId());
			employeeAndTeam.put(employee.getEmployeeId(), teamName);
		}
		List<CareerLevel> allCareerLevels = new ArrayList<CareerLevel>();
		allCareerLevels = reportServiceImpl.getAllCareerLevels();
		List<Program> allPrograms = programserviceImpl.getAllProjectPrograms();
		ArrayList<Portfolio> allPortfolios = portfolioserviceImpl.getPortfolioDetails();
		List<Project> allProjects = projectserviceImpl.getAllProjects();
		List<ProjectLocation> projectLocationDetails = projectserviceImpl.getAllProjectLocationDetails();
		modelandview.addObject("projectLocationDetails", projectLocationDetails);
		modelandview.addObject("empObjectAndData", empObjectAndData);
		modelandview.addObject("month", monthRange);
		modelandview.addObject("allCareerLevels", allCareerLevels);
		modelandview.addObject("calendarDataList", calendarDataList);
		modelandview.addObject("allPrograms", allPrograms);
		modelandview.addObject("allPortfolios", allPortfolios);
		modelandview.addObject("allProjects",allProjects);
		modelandview.addObject("employeeAndTeam", employeeAndTeam);
		if(reportType.equals("Download as PDF"))
			modelandview.setViewName("pdfView");
		else
			modelandview.setViewName("excelView");
		return modelandview;
	}
	/*@RequestMapping(value = "/employeeReport.htm", method = RequestMethod.POST)
	public ModelAndView generateEmployeeReport(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView modelandview = new ModelAndView();
		HttpSession session=request.getSession();
		Map<Long,String> employeeSupervisor = (Map<Long, String>) session.getAttribute("employeeSupervisor");
		Map<Long,EmployeeProject> employeeAndProject =  (Map<Long, EmployeeProject>) session.getAttribute("employeeAndProject");
		ArrayList<ResourceMaster> employees = (ArrayList<ResourceMaster>) session.getAttribute("employees");
		ArrayList<ResourceMaster> supervisors = (ArrayList<ResourceMaster>) session.getAttribute("supervisors");
		return modelandview;
	}*/
	

}
