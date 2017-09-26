package com.acc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acc.dao.MenuDaoFacade;
import com.acc.entity.Menu;
import com.acc.entity.MenuRole;

@Service
public class MenuServiceImpl implements MenuServiceFacade {
	@Autowired
	MenuDaoFacade menuDao;
	@Transactional(readOnly=true)
	public List<Menu> getAllMenu() {
		List<Menu> menu = menuDao.getAllMenu();
		return menu;
	}
	@Transactional
	public Integer updateMenu(Menu menu,String[] roleIds) {
		int count = menuDao.updateMenu(menu,roleIds);
		return count;
	}
	@org.springframework.transaction.annotation.Transactional(readOnly=true)
	public Menu fetchMenuDetails(Integer id) {
		Menu menu = menuDao.fetchMenuDetails(id);
		return menu;
	}
	@Transactional
	public Integer addMenu(Menu menu,String[] roleIds) {
		int count = menuDao.addMenu(menu,roleIds);
		return count;
	}
	@Transactional
	public List<Integer> getRoleOfMenu(Menu menu) {
		List<Integer> rolesOfMenu = menuDao.getRoleOfMenu(menu);
		return rolesOfMenu;
	}

}
