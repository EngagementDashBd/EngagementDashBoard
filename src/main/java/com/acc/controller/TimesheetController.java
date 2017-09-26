package com.acc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.acc.entity.CalendarData;
import com.acc.entity.DayData;
import com.acc.entity.Holiday;
import com.acc.entity.ResourceMaster;

import com.acc.service.EmployeeServiceFacade;
import com.acc.service.HolidayServiceFacade;
//import com.acc.service.ServiceFacade;
import com.acc.service.TimesheetServiceFacade;
import com.google.gson.Gson;
@Controller
public class TimesheetController {
	/*@Autowired
	ServiceFacade serviceImpl;
	*/
	@Autowired
	EmployeeServiceFacade employeeServiceImpl;
	@Autowired
	TimesheetServiceFacade timesheetServiceImpl;
	@Autowired
	HolidayServiceFacade holidayServiceImpl;
	
	@RequestMapping(value = "/calendarstore.htm", method = RequestMethod.POST)
	public ModelAndView calendarDataStore(HttpServletRequest request,HttpServletResponse response)
	{
		HttpSession session=request.getSession();
		ResourceMaster resource = (ResourceMaster)session.getAttribute("resource");
		long employeeId = resource.getEmployeeId();
		int length = 0;
		ModelAndView modelandview = new ModelAndView();
		String flag = request.getParameter("submit");
		String monthPair = request.getParameter("chooseMonth").toLowerCase();
		String[] months = monthPair.split(" - ");
		String startMonth = months[0];
		String endMonth = months[1];
		String supervisor = employeeServiceImpl.getSupervisorEid(resource.getSupervisorId());
		
		//Mail
		
		List<String> recipients = new ArrayList<String>();
		recipients.add(supervisor + "@accenture.com");
		
		List<String> cc = new ArrayList<String>();
		cc.add(resource.getEnterpriseId() + "@accenture.com");
		
		String body = resource.getEnterpriseId() +  " has identified you as his/her timesheet approver. Kindly take necessary actions ASAP.";
		String subject = "Approve/Reject Timesheet";
		int year =  Integer.parseInt(request.getParameter("chooseYear"));
		if(startMonth.equalsIgnoreCase("january") || startMonth.equalsIgnoreCase("march") || startMonth.equalsIgnoreCase("may") || startMonth.equalsIgnoreCase("july") || startMonth.equalsIgnoreCase("august") || startMonth.equalsIgnoreCase("october") || startMonth.equalsIgnoreCase("december"))
			length = 31;
		else if(startMonth.equalsIgnoreCase("april") || startMonth.equalsIgnoreCase("june") || startMonth.equalsIgnoreCase("september") || startMonth.equalsIgnoreCase("november"))
			length = 30;
		else
		{
			if(year % 400 == 0)
				length = 29;
			else if(year % 100 == 0)
				length = 28;
			else if(year % 4 == 0)
				length = 29;
			else
				length = 28;
		}
		Map<Integer, String> shiftData1 = new HashMap<Integer, String>();
		String shift = null;
		for(int i = 16 ; i <= length ; i++)
		{
			shift = "shiftdetail"+i;
			shiftData1.put(i, request.getParameter(shift));
		
		}
		boolean submitPermit = timesheetServiceImpl.submitPermit(endMonth);
		if(submitPermit == true)
		{
			int count1 = timesheetServiceImpl.calendarDataStore(employeeId, year, startMonth, shiftData1,flag);
			Map<Integer, String> shiftData2 = new HashMap<Integer, String>();
			for(int i = 1 ; i <= 15 ; i++)
			{
				shift = "shiftdetail"+i;
				shiftData2.put(i, request.getParameter(shift));
			}
			if(endMonth.equalsIgnoreCase("january"))
				year = year + 1;
			int count2 = timesheetServiceImpl.calendarDataStore(employeeId, year, endMonth, shiftData2,flag);
			
			if(count1 == 1 && count2 == 1)
			{
				modelandview.addObject("code", "success");
				/*Mailer mailer = new Mailer();
				mailer.sendMail(recipients, body, subject, cc);*/
			}
			else
				modelandview.addObject("code", "failure");
		}
		else
			modelandview.addObject("submitPermit", "notAllowed");
		modelandview.setViewName("timesheet");
		return modelandview;
	}

	@RequestMapping(value = "/getCalendarData.do", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void getCalendarData(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		 String[] monthName = { "january", "february", "march", "april", "may", "june", "july",
			        "august", "september", "october", "november", "december" };
		int startMonthValue = Integer.parseInt(request.getParameter("startMonth"));
		int endMonthValue = Integer.parseInt(request.getParameter("endMonth"));
		String startMonth = monthName[startMonthValue];
		String endMonth = monthName[endMonthValue];
		int startYear = Integer.valueOf(request.getParameter("startYear"));
		int endYear = Integer.valueOf(request.getParameter("endYear"));
		long employeeId = Long.parseLong(request.getParameter("employeeId"));
		CalendarData calendarData = new CalendarData();
		calendarData = timesheetServiceImpl.getCalendarData(employeeId,startMonth,startYear,endMonth,endYear);
		List<Holiday> allHolidayData = holidayServiceImpl.getAllHolidayData();
		List<DayData> dayData = calendarData.getDayData();
		List<DayData> dayData1 = new ArrayList<DayData>();
		Boolean flag1 = false;
		Boolean flag2 = false;
		for(Holiday holiday : allHolidayData)
		{
			
			String[] array = holiday.getDate().toString().split("-");
			int year = Integer.valueOf(array[0]);
			int date = holiday.getDate().getDate();
			int month = holiday.getDate().getMonth();
			String reason = holiday.getReason();
			Boolean isOnShoreHoliday = holiday.getIsOnShoreHoliday();
			Boolean isOffShoreHoliday = holiday.getIsOffShoreHoliday();
			if((year == startYear && month != 0) || (year == endYear && month == 0))
			{
				if(month == startMonthValue)
				{
					if(date > 15)
					{
						for(DayData d : dayData)
						{
							if(d.getDate() == date)
							{
								//d.setIsHoliday(true);
								if(isOffShoreHoliday)
								{
									System.out.println("Offshore holiday scnd fortnight "+reason);
									d.setIsOffShoreHoliday(true);
								}
								else if(isOnShoreHoliday)
								{
									System.out.println("Onshore holiday scnd fortnight "+reason);
									d.setIsOnShoreHoliday(true);
								}
							}
							else
							{
								DayData data = new DayData();
								data.setDate(date);
								//data.setIsHoliday(true);
								if(isOffShoreHoliday)
								{
									System.out.println("Offshore holiday scnd fortnight "+reason);
									data.setIsOffShoreHoliday(true);
								}
								else if(isOnShoreHoliday)
								{
									System.out.println("Onshore holiday scnd fortnight "+reason);
									data.setIsOnShoreHoliday(true);
								}
								dayData1.add(data);
							}
						
						}
						if(dayData.isEmpty() || flag1 == true)
						{
							DayData data = new DayData();
							data.setDate(date);
							//data.setIsHoliday(true);
							if(isOffShoreHoliday)
							{
								System.out.println("Offshore holiday scnd fortnight "+reason);
								data.setIsOffShoreHoliday(true);
							}
							else if(isOnShoreHoliday)
							{
								System.out.println("Onshore holiday scnd fortnight "+reason);
								data.setIsOnShoreHoliday(true);
							}
							dayData.add(data);
							flag1 = true;
						}
					}
				}
				if(month == endMonthValue)
				{
				
					if(date <= 15)
					{
						for(DayData d : dayData)
						{
							if(d.getDate() == date)
							{
								//d.setIsHoliday(true);
								if(isOffShoreHoliday)
								{
									System.out.println("Offshore holiday frst fortnight "+reason);
									d.setIsOffShoreHoliday(true);
								}
								else if(isOnShoreHoliday)
								{
									System.out.println("Onshore holiday frst fortnight "+reason);
									d.setIsOnShoreHoliday(true);
								}
							} 
							else
							{
								DayData data = new DayData();
								data.setDate(date);
								//data.setIsHoliday(true);
								if(isOffShoreHoliday)
								{
									System.out.println("Offshore holiday scnd fortnight "+reason);
									data.setIsOffShoreHoliday(true);
								}
								else if(isOnShoreHoliday)
								{
									System.out.println("Onshore holiday scnd fortnight "+reason);
									data.setIsOnShoreHoliday(true);
								}
								dayData1.add(data);
							}
							
						}
						if(dayData.isEmpty() || flag2 == true)
						{
							DayData data = new DayData();
							data.setDate(date);
							//data.setIsHoliday(true);
							if(isOffShoreHoliday)
							{
								System.out.println("Offshore holiday frst fortnight "+reason);
								data.setIsOffShoreHoliday(true);
							}
							else if(isOnShoreHoliday)
							{
								System.out.println("Onshore holiday frst fortnight "+reason);
								data.setIsOnShoreHoliday(true);
							}
							dayData.add(data);
							flag2 = true;
						}
					}
				}
			}
			
		}
		for(DayData d : dayData1)
		{
			dayData.add(d);
		}
		calendarData.setDayData(dayData);
		System.out.println(calendarData.getMonth());
		Gson gson = new Gson();
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.println(gson.toJson(calendarData));
	}
	@RequestMapping(value = "approveOrReject.htm", method = RequestMethod.POST)
	public ModelAndView approveOrReject(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		 String[] monthName = { "january", "february", "march", "april", "may", "june", "july",
			        "august", "september", "october", "november", "december" };
		ModelAndView modelandview = new ModelAndView();
		int count = 0;
		String code = null;
		String buttonName = request.getParameter("submit");
		Long employeeId = Long.parseLong(request.getParameter("selectEmployee"));
		String monthPair = request.getParameter("chooseMonth").toLowerCase();
		String[] months = monthPair.split(" - ");
		String startMonth = months[0];
		String endMonth = months[1];
		int startYear = Integer.parseInt(request.getParameter("chooseYear"));
		int endYear = startYear;
		if(endMonth.equalsIgnoreCase("january"))
			endYear = startYear + 1;
		if(buttonName.equals("Approve"))
		{
			count = timesheetServiceImpl.approveTimesheet(employeeId, startMonth, startYear, endMonth, endYear);
			if(count == 1)
				code = "approved";
		}
		else if(buttonName.equals("Reject"))
		{
			count = timesheetServiceImpl.rejectTimesheet(employeeId, startMonth, startYear, endMonth, endYear);
			if(count == 1)
				code = "rejected";

		}
		HttpSession session=request.getSession();
		ResourceMaster resource = (ResourceMaster)session.getAttribute("resource");
		long supervisorId = resource.getEmployeeId();
		ArrayList<ResourceMaster> employeeObjects = new ArrayList<ResourceMaster>();
		employeeObjects = employeeServiceImpl.approve(supervisorId);
		modelandview.addObject("employees", employeeObjects);
		modelandview.addObject("code", code);
		modelandview.setViewName("approve");
		return modelandview;
	}
}
