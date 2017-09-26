package com.acc.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.acc.entity.Portfolio;
import com.acc.entity.ResourceMaster;
import com.acc.service.PortfolioServiceFacade;
//import com.acc.service.ServiceFacade;
import com.google.gson.Gson;

import antlr.collections.List;
@Controller
public class PortfolioController {
	/*@Autowired
	ServiceFacade serviceImpl;*/
	@Autowired
	PortfolioServiceFacade portfolioserviceImpl;
	@RequestMapping("/portfolio.htm")
	public ModelAndView addPortfolioDetails(@ModelAttribute Portfolio portfolio, HttpServletRequest request,HttpServletResponse response )
	{
		int count = 0;
		ModelAndView modelandview = new ModelAndView();
		HttpSession session = request.getSession();
		ResourceMaster resource = (ResourceMaster)session.getAttribute("resource");
		String creatorName = resource.getEmployeeName();
		count = portfolioserviceImpl.addPortfolioDetails(portfolio,creatorName);
		ArrayList<Portfolio> portfolios = new ArrayList<Portfolio>();
		portfolios = portfolioserviceImpl.getPortfolioDetails();
		session.setAttribute("portfolios", portfolios);
		modelandview.setViewName("portfolioDetails");
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
	@RequestMapping("allPortfolioDetails.htm")
	public ModelAndView getPortfolioDetails(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView modelandview = new ModelAndView();
		HttpSession session = request.getSession();
		ArrayList<Portfolio> portfolios = new ArrayList<Portfolio>();
		portfolios = portfolioserviceImpl.getPortfolioDetails();
		session.setAttribute("portfolios", portfolios);
		modelandview.setViewName("portfolioDetails");
		return modelandview;
	}
	@RequestMapping("/fetchPortfolioDetails.htm")
	public ModelAndView fetchPortfolioDetails(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView modelandview = new ModelAndView();
		HttpSession session = request.getSession();
		Integer portfolioId = Integer.parseInt(request.getParameter("portfolioId"));
		Portfolio portfolio = portfolioserviceImpl.getPortfolio(portfolioId);
		session.setAttribute("portfolio", portfolio);
		modelandview.setViewName("updatePortfolio");
		return modelandview;
	}
	@RequestMapping("/updatePortfolio.htm")
	public ModelAndView updatePortfolioDetails(@ModelAttribute Portfolio portfolio, HttpServletRequest request, HttpServletResponse response )
	{
		int count = 0;
		ModelAndView modelandview = new ModelAndView();
		HttpSession session = request.getSession();
		count =portfolioserviceImpl.updatePortfolio(portfolio);
		ArrayList<Portfolio> portfolios = new ArrayList<Portfolio>();
		portfolios = portfolioserviceImpl.getPortfolioDetails();
		session.setAttribute("portfolios", portfolios);
		modelandview.setViewName("portfolioDetails");
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
	@RequestMapping("/deletePortfolio.htm")
	public ModelAndView deletePortfolio(HttpServletRequest request,HttpServletResponse response )
	{
		HttpSession session = request.getSession();
		ModelAndView modelandview = new ModelAndView();
		ArrayList<Portfolio>  portfolios= new ArrayList<Portfolio>();
		Integer portfolioId = Integer.parseInt(request.getParameter("portfolioId"));
		int count = portfolioserviceImpl.deletePortfolio(portfolioId);
		portfolios =portfolioserviceImpl.getPortfolioDetails();
		session.setAttribute("portfolios", portfolios);
		modelandview.setViewName("portfolioDetails");
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
	
}