package com.acc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.acc.entity.ResourceMaster;
import com.acc.entity.TrainingDetails;
import com.acc.service.TrainingServiceFacade;
import com.google.gson.Gson;

@Controller
public class TrainingController {

	@Autowired
	TrainingServiceFacade trainingserv;
	
	@RequestMapping(value = "/getSlide.htm", method = RequestMethod.GET)
	public @ResponseBody void getSlide(@RequestParam("topicId") Long topicId, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int slideNumber = 1;
		HttpSession session = request.getSession();
		ResourceMaster resource = (ResourceMaster) session.getAttribute("resource");
		String enterpriseId = resource.getEnterpriseId();
		//System.out.println("Inside Controller - getSlide function topicId & EntpID " + topicId + " " + enterpriseId);
		slideNumber = trainingserv.getSlideNumber(enterpriseId, topicId);
		Gson gson = new Gson();
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.println(gson.toJson(slideNumber));
	}

	@RequestMapping(value = "/updateSlide.htm", method = RequestMethod.POST)
	@ResponseBody
	public int updateSlide(@RequestParam("topicId") Long topicId, @RequestParam("slideNumber") int slideNumber,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		int flag = 0;
		HttpSession session = request.getSession();
		ResourceMaster resource = (ResourceMaster) session.getAttribute("resource");
		String enterpriseId = resource.getEnterpriseId();
		/*System.out.println("Inside Controller - updateSlide function topicId, slidenumber & EntpID " + topicId + " "
				+ slideNumber + " " + enterpriseId);*/
		flag = trainingserv.updateSlideNumber(enterpriseId, topicId, slideNumber);

		return flag;
	}

	
	@RequestMapping(value = "/completeStatus.htm", method = RequestMethod.POST)
	@ResponseBody
	public int completeStatus(@RequestParam("topicId") Long topicId, HttpServletRequest request, HttpServletResponse response) throws Exception {
		int flag = 0;
		HttpSession session = request.getSession();
		ResourceMaster resource = (ResourceMaster) session.getAttribute("resource");
		String enterpriseId = resource.getEnterpriseId();
		//System.out.println("Inside Controller - updateSlide function topicId & EntpID " + topicId + " " + enterpriseId);
		flag = trainingserv.completeStatus(enterpriseId, topicId);

		return flag;
	}
	
	@RequestMapping(value = "/learningDashboard.htm")
	public ModelAndView learningDashboard(HttpServletRequest request,HttpServletResponse response) throws IOException, ParseException
	{
		HttpSession session = request.getSession();
		ResourceMaster resource = (ResourceMaster) session.getAttribute("resource");
		String enterpriseId = resource.getEnterpriseId();
		String employeeName = resource.getEmployeeName();
		Long employeeId = resource.getEmployeeId();
		List<TrainingDetails> learningData = trainingserv.fetchDetails(enterpriseId);
		ModelAndView modelandview = new ModelAndView();
		modelandview.addObject("learningData", learningData);
		modelandview.addObject("enterpriseId", enterpriseId);
		modelandview.addObject("employeeName", employeeName);
		modelandview.addObject("employeeId", employeeId);
		modelandview.setViewName("/trainings/learningDashboard");
		return modelandview;
	}
	
	@RequestMapping(value = "/mandatory.htm")
	public ModelAndView mandatory() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/trainings/mandatory");
		return mv;
	}
	
	@RequestMapping(value = "/additional.htm")
	public ModelAndView additional() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/trainings/additional");
		return mv;
	}
	
	@RequestMapping(value = "/hcscMenu.htm")
	public ModelAndView hcscMenu() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/trainings/index");
		return mv;
	}

	@RequestMapping(value = "/bcp.htm")
	public ModelAndView bcp() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/trainings/bcp");
		return mv;
	}

	@RequestMapping(value = "/navritiMenu.htm")
	public ModelAndView navritiMenu() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/trainings/navritiMenu");
		return mv;
	}

	@RequestMapping(value = "/hcsc.htm")
	public ModelAndView hcsc() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/trainings/hcsc");
		return mv;
	}

	@RequestMapping(value = "/health.htm")
	public ModelAndView health() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/trainings/health");
		return mv;
	}
	
	@RequestMapping(value = "/continuous.htm")
	public ModelAndView continuous() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/trainings/continuous");
		return mv;
	}
	
	@RequestMapping(value = "/healthOverviewMenu.htm")
	public ModelAndView healthOverviewMenu() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/trainings/healthOverviewMenu");
		return mv;
	}

	@RequestMapping(value = "/informationsecurity.htm")
	public ModelAndView informationsecurity() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/trainings/informationsecurity");
		return mv;
	}

	@RequestMapping(value = "/organization.htm")
	public ModelAndView organization() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/trainings/organization");
		return mv;
	}

	@RequestMapping(value = "/healthcareday1.htm")
	public ModelAndView healthcareday1() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/trainings/healthcareday1");
		return mv;
	}

	@RequestMapping(value = "/healthcareday2.htm")
	public ModelAndView healthcareday2() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/trainings/healthcareday2");
		return mv;
	}

	@RequestMapping(value = "/healthcareday3.htm")
	public ModelAndView healthcareday3() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/trainings/healthcareday3");
		return mv;
	}

	@RequestMapping(value = "/healthcareday4.htm")
	public ModelAndView healthcareday4() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/trainings/healthcareday4");
		return mv;
	}

	@RequestMapping(value = "/healthcareday5.htm")
	public ModelAndView healthcareday5() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/trainings/healthcareday5");
		return mv;
	}

	@RequestMapping(value = "/healthcareday6.htm")
	public ModelAndView healthcareday6() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/trainings/healthcareday6");
		return mv;
	}

	@RequestMapping(value = "/toolassets.htm")
	public ModelAndView toolassets() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/trainings/toolassets");
		return mv;
	}

	@RequestMapping(value = "/technology.htm")
	public ModelAndView technology() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/trainings/technology");
		return mv;
	}

	@RequestMapping(value = "/navriti.htm")
	public ModelAndView navriti() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/trainings/navriti");
		return mv;
	}

}
