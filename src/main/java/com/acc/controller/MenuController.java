package com.acc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.acc.entity.Menu;
import com.acc.entity.MenuRole;
import com.acc.entity.Roles;
import com.acc.service.MenuServiceFacade;
import com.acc.service.RolesFacade;

@Controller
public class MenuController {
	@Autowired
	MenuServiceFacade menuService;
	@Autowired
	RolesFacade roleService;
	@RequestMapping("/getAllMenu.htm")
	public ModelAndView getAllMenu()
	{
		ModelAndView modelandview = new ModelAndView();
		List<Menu> menu = menuService.getAllMenu();
		modelandview.addObject("menu", menu);
		modelandview.setViewName("menuDetails");
		return modelandview;
	}
	@RequestMapping("/fetchMenuDetails.htm")
	public ModelAndView fetchMenuDetails(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView modelandview = new ModelAndView();
		Integer id = Integer.valueOf(request.getParameter("menuId"));
		Menu menu = menuService.fetchMenuDetails(id);
		modelandview.addObject("menu",menu);
		List<Roles> allRoles = roleService.getAllRoles();
		modelandview.addObject("roles", allRoles);
		List<Integer> rolesOfMenu = menuService.getRoleOfMenu(menu);
		modelandview.addObject("rolesOfMenu", rolesOfMenu);
		List<Integer> roleList = new ArrayList<Integer>(); 
		for(Roles mr : allRoles)
		{
			roleList.add(mr.getId());
		}
		modelandview.addObject("roleList", roleList);
		modelandview.setViewName("updateMenu");
		return modelandview;
	}
	@RequestMapping("/updateMenu")
	public ModelAndView updateMenu(@ModelAttribute Menu menu,HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView modelandview = new ModelAndView();
		String roles = request.getParameter("roleList");
		String[] roleIds = roles.split(",");
		Integer count = menuService.updateMenu(menu,roleIds);		
		if(count == 1)
			modelandview.addObject("code","success");
		else
			modelandview.addObject("code","failure");
		modelandview = getAllMenu();
		return modelandview;
	}
	@RequestMapping("/addMenu")
	public ModelAndView addMenu(@ModelAttribute Menu menu,HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView modelandview = new ModelAndView();
		String roles = request.getParameter("roleList");
		String[] roleIds = roles.split(",");
		Integer count = menuService.addMenu(menu,roleIds);		
		if(count == 1)
			modelandview.addObject("code","success");
		else
			modelandview.addObject("code","failure");
		modelandview = getAllMenu();
		return modelandview;
	}
	
}
