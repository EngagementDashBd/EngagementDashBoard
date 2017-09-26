package com.acc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.acc.entity.CareerLevel;
import com.acc.service.CareerLevelServiceFacade;

@Controller
public class CareerLevelController {
	@Autowired
	CareerLevelServiceFacade careerLevelService;
	@RequestMapping("/getAllLevels.htm")
	public ModelAndView getAllLevels()
	{
		ModelAndView modelandview = new ModelAndView();
		List<CareerLevel> levels = careerLevelService.getAllLevels();
		modelandview.addObject("levels", levels);
		modelandview.setViewName("careerLevelDetails");
		return modelandview;
	}
	@RequestMapping("/fetchLevelDetails.htm")
	public ModelAndView getLevelDetails(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView modelandview = new ModelAndView();
		Integer level = Integer.valueOf(request.getParameter("level"));
		CareerLevel CareerLevel =  careerLevelService.getCarrerLevelDetails(level);
		modelandview.addObject("CareerLevel", CareerLevel);
		modelandview.setViewName("updateCareerLevel");
		return modelandview;
	}
	@RequestMapping("/updateCareeeLevel.htm")
	public ModelAndView updateCareeeLevel(@ModelAttribute CareerLevel careerLevel, HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView modelandview = new ModelAndView();
		int count = careerLevelService.updateCareerLevel(careerLevel);
		modelandview = getAllLevels();
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
