package com.acc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.acc.entity.Portfolio;
import com.acc.entity.Program;
import com.acc.entity.ResourceMaster;
import com.acc.service.PortfolioServiceFacade;
import com.acc.service.ProgramServiceFacade;
//import com.acc.service.ServiceFacade;
@Controller
public class ProgramController {
	/*@Autowired
	ServiceFacade serviceImpl;*/
	@Autowired
	PortfolioServiceFacade portfolioserviceImpl;
	@Autowired
	ProgramServiceFacade programserviceImpl;
	@RequestMapping("/getportfolio.htm")
	public ModelAndView getPortfolio(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
			HttpSession session = request.getSession();
			ModelAndView modelandview = new ModelAndView();
			ArrayList<Portfolio> portfolios = portfolioserviceImpl.getPortfolioDetails();
			session.setAttribute("portfolios", portfolios);
			modelandview.setViewName("program");
		
			return modelandview;
	}
	@RequestMapping("/allProgramDetails.htm")
	public ModelAndView getAllPrograms(HttpServletRequest request,HttpServletResponse response)
	{
		HttpSession session = request.getSession();
		ModelAndView modelandview = new ModelAndView();
		List<Program> programs = programserviceImpl.getAllProjectPrograms();
		session.setAttribute("programs", programs);
		modelandview.setViewName("programDetails");
		return modelandview;
	}
	
	@RequestMapping("/addProgram.htm")
	public ModelAndView addProgram(@ModelAttribute Program program,HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		
			ModelAndView modelandview = new ModelAndView();
			HttpSession session = request.getSession();
			ResourceMaster resource = (ResourceMaster)session.getAttribute("resource");
			String creatorName = resource.getEmployeeName();
			int count = 0;
			ArrayList<Portfolio> portfolios = portfolioserviceImpl.getPortfolioDetails();
			modelandview.addObject("portfolios", portfolios);
			count = programserviceImpl.addProgram(program,creatorName);
			List<Program> programs = programserviceImpl.getAllProjectPrograms();
			session.setAttribute("programs", programs);
			modelandview.setViewName("programDetails");
			

			if(count == 1)
			{
				modelandview.addObject("code","success");
			}
			else
			{
				modelandview.addObject("code","failure");
			}
		
			return modelandview;
	}
	@RequestMapping("/deleteProgram.htm")
	public ModelAndView deletePortfolio(HttpServletRequest request,HttpServletResponse response )
	{
		HttpSession session = request.getSession();
		ModelAndView modelandview = new ModelAndView();
		ArrayList<Program>  programs= new ArrayList<Program>();
		Integer programId = Integer.parseInt(request.getParameter("programId"));
		int count = programserviceImpl.deleteProgram(programId);
		programs = (ArrayList<Program>)programserviceImpl.getAllProjectPrograms();
		session.setAttribute("programs", programs);
		modelandview.setViewName("programDetails");
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
	@RequestMapping("/fetchProgramDetails.htm")
	public ModelAndView fetchPortfolioDetails(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView modelandview = new ModelAndView();
		HttpSession session = request.getSession();
		Integer programId = Integer.parseInt(request.getParameter("programId"));
		Program program = programserviceImpl.getProgram(programId);
		String portfolioName = portfolioserviceImpl.getPortfolioName(program.getPortfolioId());
		ArrayList<Portfolio> portfolios = portfolioserviceImpl.getPortfolioDetails();
		session.setAttribute("portfolios", portfolios);
		session.setAttribute("program", program);
		session.setAttribute("portfolioName", portfolioName);
		modelandview.setViewName("updateProgram");
		return modelandview;
	}
	@RequestMapping("/updateProgram.htm")
	public ModelAndView updatePortfolioDetails(@ModelAttribute Program program, HttpServletRequest request, HttpServletResponse response )
	{
		int count = 0;
		ModelAndView modelandview = new ModelAndView();
		HttpSession session = request.getSession();
		count = programserviceImpl.updateProgram(program);
		List<Program> programs = programserviceImpl.getAllProjectPrograms();
		session.setAttribute("programs", programs);
		modelandview.setViewName("programDetails");
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
	


}


