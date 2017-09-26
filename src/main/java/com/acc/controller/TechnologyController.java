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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.acc.entity.Project;
import com.acc.entity.ResourceMaster;
import com.acc.entity.Technology;
import com.acc.service.TechnologyServiceFacade;
//import com.acc.service.ServiceFacade;

@Controller
public class TechnologyController {
	/*@Autowired
	ServiceFacade serviceImpl;*/
	@Autowired
	TechnologyServiceFacade technologyserviceImpl;
	@RequestMapping("/allTechnologyDetails.htm")
	public ModelAndView getAllTechnologyDetails(HttpServletRequest request,HttpServletResponse response )
	{
		ModelAndView modelandview = new ModelAndView();
		HttpSession session=request.getSession();
		List<Technology> technologies = new ArrayList<Technology>();
		technologies = technologyserviceImpl.getAllTechnology();
		session.setAttribute("technologies", technologies);
		modelandview.setViewName("technologyDetails");
		return modelandview;
	
	} 
	@RequestMapping("/deleteTechnology.htm")
	public ModelAndView deleteTechnology(HttpServletRequest request,HttpServletResponse response )
	{
		int count = 0;
		ModelAndView modelandview = new ModelAndView();
		HttpSession session=request.getSession();
		Integer technologyId = Integer.parseInt(request.getParameter("technologyid"));
		count = technologyserviceImpl.deleteTechnology(technologyId);
		List<Technology> technologies = new ArrayList<Technology>();
		technologies =technologyserviceImpl.getAllTechnology();
		session.setAttribute("technologies", technologies);
		modelandview.setViewName("technologyDetails");
		if(count == 1)
			modelandview.addObject("deletecode", "success");
		else
			modelandview.addObject("deletecode","failure");
		return modelandview;
	
	} 
	@RequestMapping(value="/addTechnology.htm",method=RequestMethod.POST)
	public ModelAndView addNewTechnology(@ModelAttribute Technology technology,HttpServletRequest request,HttpServletResponse response )
	{
		int count = 0;
		ModelAndView modelandview = new ModelAndView();
		HttpSession session = request.getSession();
		count = technologyserviceImpl.addTechnology(technology);
		List<Technology> technologies = new ArrayList<Technology>();
		technologies = technologyserviceImpl.getAllTechnology();
		session.setAttribute("technologies", technologies);
		modelandview.setViewName("technologyDetails");
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

}
