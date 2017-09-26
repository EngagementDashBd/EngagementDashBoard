package com.acc.service;

import java.util.List;

import com.acc.entity.Menu;
import com.acc.entity.MenuRole;

public interface MenuServiceFacade {
	public List<Menu> getAllMenu();
	public Integer updateMenu(Menu menu,String[] roleIds);
	public Menu fetchMenuDetails(Integer id);
	public Integer addMenu(Menu menu,String[] roleIds);
	public List<Integer> getRoleOfMenu(Menu menu);
}
