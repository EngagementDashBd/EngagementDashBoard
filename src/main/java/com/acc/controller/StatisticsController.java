package com.acc.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.acc.entity.Portfolio;
import com.acc.entity.Program;
import com.acc.entity.Project;
import com.acc.service.PortfolioServiceFacade;
import com.acc.service.ProgramServiceFacade;
import com.acc.service.ProjectServiceFacade;
//import com.acc.service.ServiceFacade;
import com.acc.service.StatisticsServiceFacade;
import com.acc.service.StatisticsServiceFacade1;
import com.google.gson.Gson;
@Controller
public class StatisticsController {
	/*@Autowired
	ServiceFacade serviceImpl;*/
	@Autowired
	ProjectServiceFacade projectserviceImpl;
	@Autowired
	StatisticsServiceFacade statisticsServiceImpl;
	@Autowired
	PortfolioServiceFacade portfolioserviceImpl;
	@Autowired
	ProgramServiceFacade programserviceImpl;
	@Autowired
	StatisticsServiceFacade1 statisticsServiceImpl1;
	
	@RequestMapping("/statistics.htm")
	public String getPieChart(Model model){
		
		return "statistics";
	}

	@RequestMapping("/shiftsdata.htm")
	public void statistics(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		Map<String, Integer> shiftCount = statisticsServiceImpl1.statistics();
		String columns="{\"cols\": [{\"label\":\"ShiftType\",\"type\":\"string\"},{\"label\":\"days\",\"type\":\"number\"} ], ";
		String rows = "\"rows\":[";
		for(int i = 0 ; i < 3 ; i++)
		{
			String shiftName = (String)shiftCount.keySet().toArray()[i];
			String shift = "\""+shiftName+"\"";
			System.out.println(shift);
			int shiftValue = shiftCount.get(shiftName);
			rows = rows + "{\"c\":[{\"v\":"+shift+"},{\"v\":" + shiftValue+"}]},";
		}
		rows = rows + "]}";
		response.setContentType("application/json");
		PrintWriter pw=response.getWriter();
		pw.println(columns+rows);
			
	}
	@RequestMapping("/projectwiseStatistics.htm")
	public ModelAndView getPieChart(HttpServletRequest request, HttpServletResponse response){
		HttpSession session=request.getSession();
		System.out.println("fjh");
		ModelAndView modelandview = new ModelAndView();
		List<Program> allPrograms =  programserviceImpl.getAllProjectPrograms();
		ArrayList<Portfolio> allPortfolios =  portfolioserviceImpl.getPortfolioDetails();
		List<Project> allProjects = projectserviceImpl.getAllProjects();
		modelandview.addObject("allPrograms", allPrograms);
		modelandview.addObject("allPortfolios", allPortfolios);
		modelandview.addObject("allProjects",allProjects);
		modelandview.setViewName("ProjectwiseStatistics");
		return modelandview;
	}
	@RequestMapping("programWiseShiftsData.htm")
	public void getProgramWiseShiftData(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		Integer portfolioId = Integer.valueOf(request.getParameter("portfolioId"));
		//List<Program> programs = serviceImpl.getAllProjectPrograms();
		Map<String,Integer> shiftAmount = statisticsServiceImpl.getProgramWiseReport(portfolioId);
		//Map<String, Integer> shiftCount = serviceImpl.statistics();
		String columns="{\"cols\": [{\"label\":\"Program Name\",\"type\":\"string\"},{\"label\":\"Amount\",\"type\":\"number\"} ], ";
		String rows = "\"rows\":[";
		for(int i = 0 ; i < shiftAmount.size() ; i++)
		{
			String programname = (String)shiftAmount.keySet().toArray()[i];
			String programName = "\""+programname+"\"";
			System.out.println(programName);
			int amount = shiftAmount.get(programname);
			System.out.println(amount);
			rows = rows + "{\"c\":[{\"v\":"+programName+"},{\"v\":" + amount+"}]},";
		}
		rows = rows + "]}";
		response.setContentType("application/json");
		PrintWriter pw=response.getWriter();
		pw.println(columns+rows);
	}
	@RequestMapping("projectWiseShiftsData.htm")
	public void getProjectWiseShiftData(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		
		Integer programId = Integer.valueOf(request.getParameter("programId"));
		Map<String,Integer> shiftAmount = statisticsServiceImpl.getProjectWiseReport(programId);
		String columns="{\"cols\": [{\"label\":\"Project Name\",\"type\":\"string\"},{\"label\":\"Amount\",\"type\":\"number\"} ], ";
		String rows = "\"rows\":[";
		for(int i = 0 ; i < shiftAmount.size() ; i++)
		{
			String projectname = (String)shiftAmount.keySet().toArray()[i];
			String projectName = "\""+projectname+"\"";
			System.out.println(projectName);
			int amount = shiftAmount.get(projectname);
			System.out.println(amount);
			rows = rows + "{\"c\":[{\"v\":"+projectName+"},{\"v\":" + amount+"}]},";
		}
		rows = rows + "]}";
		response.setContentType("application/json");
		PrintWriter pw=response.getWriter();
		pw.println(columns+rows);
	}
	
}
