package com.acc.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.acc.entity.Roles;
import com.acc.service.MenuServiceFacade;
import com.acc.service.RolesFacade;

@Controller
public class RoleController {
	@Autowired
	RolesFacade roleService;
	@Autowired
	MenuServiceFacade menuService;
	@RequestMapping("getAllRoles.htm")
	public void getAllRoles(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		List<Roles> allRoles = roleService.getAllRoles();
		List<String> allRoleNames = new ArrayList<String>();
		for(Roles role : allRoles)
		{
			allRoleNames.add(role.getName());
		}
		System.out.println(allRoles);
		response.setContentType("application/json");
		PrintWriter pw = response.getWriter();
		pw.println(allRoleNames);
	}
	
	@RequestMapping("getAllRole.htm")
	public ModelAndView getAllRole(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		ModelAndView modelandview = new ModelAndView();
		List<Roles> allRoles = roleService.getAllRoles();
		modelandview.addObject("roles", allRoles);
		modelandview.setViewName("roleDetails");
		return modelandview;
	}
	
	@RequestMapping("fetchRoleDetails.htm")
	public ModelAndView getRoleById(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		ModelAndView modelandview = new ModelAndView();
		Integer roleId = Integer.valueOf(request.getParameter("roleId"));
		Roles role = roleService.getRoleById(roleId);
		modelandview.addObject("role", role);
		List<Integer> menuOfRole = roleService.getMenuListByRole(role);
		modelandview.addObject("menuOfRole",menuOfRole);
		List<Menu> menuList = menuService.getAllMenu();
		List<Integer> menuIds = new ArrayList<Integer>();
		for(Menu m : menuList)
		{
			menuIds.add(m.getId());
		}
		modelandview.addObject("menuList", menuIds);
		modelandview.addObject("menu", menuList);
		modelandview.setViewName("updateRoleDetails");
		return modelandview;
	}
	
	@RequestMapping("updateRole.htm")
	public ModelAndView updateRole(@ModelAttribute Roles role ,HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		ModelAndView modelandview = new ModelAndView();
		String menuList = request.getParameter("menuList");
		String[] menus = menuList.split(",");
		int count = roleService.updateRole(role,menus);
		modelandview = getAllRole(request, response);
		return modelandview;
	}
	@RequestMapping("addRole.htm")
	public ModelAndView addRole(@ModelAttribute Roles role ,HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		ModelAndView modelandview = new ModelAndView();
		String menuList = request.getParameter("menuList");
		String[] menus = menuList.split(",");
		int count = roleService.addRole(role,menus);
		modelandview = getAllRole(request, response);		
		return modelandview;
	}
}
