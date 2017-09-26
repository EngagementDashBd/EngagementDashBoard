package com.acc.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.acc.entity.Menu;
import com.acc.entity.MenuRole;
import com.acc.entity.Roles;

@Repository
public class MenuDaoImplementation extends AbstractDao implements MenuDaoFacade {

	public List<Menu> getAllMenu() {
		Session session = getSession();
		Query query = session.createQuery("select e from Menu e");
		List<Menu> menu = null;
		menu = query.list();
		return menu;
	}

	public Integer updateMenu(Menu menu,String[] roleIds) {
		Session session = getSession();
		Integer menuId = null;
		Query query = session.createQuery("update Menu e set e.parentId=:parentId,e.url=:url where e.id=:id");
		query.setParameter("id",menu.getId());
		query.setParameter("parentId",menu.getParentId());
		query.setParameter("url",menu.getUrl());
		int count = query.executeUpdate();
		Query query1 = session.createQuery("delete from MenuRole e where e.menuId=:menuId");
		query1.setParameter("menuId", menu.getId());
		query1.executeUpdate();
		for(int i=0; i<roleIds.length;i++)
		{
			MenuRole mrole = new MenuRole();
			mrole.setMenuId(menu.getId());
			mrole.setRoleId(Integer.valueOf(roleIds[i]));
			session.save(mrole);
			
		}
		return count;
	}

	public Menu fetchMenuDetails(Integer id) {
		Session session = getSession();
		List<Menu> menus = null;
		Query query = session.createQuery("select e from Menu e where e.id=:id");
		query.setParameter("id",id);
		menus = query.list();
		Menu menu = new Menu();
		for(Menu m : menus)
			menu = m;
		return menu;
	}

	public Integer addMenu(Menu menu,String[] roleIds) {
		int count = 0;
		Integer menuId = null;
		Session session = getSession();
		Menu menu1 = new Menu();
		menu1.setName(menu.getName());;
		menu1.setParentId(menu.getParentId());
		menu1.setUrl(menu.getUrl());
		session.save(menu1);
		Query query = session.createQuery("select e from Menu e where e.name=:name");
		query.setParameter("name", menu.getName());
		List<Menu> menuList = query.list();
		for(Menu menu2 : menuList)
			menuId = menu2.getId();
		for(int i=0; i<roleIds.length;i++)
		{
			MenuRole mrole = new MenuRole();
			mrole.setMenuId(menuId);
			mrole.setRoleId(Integer.valueOf(roleIds[i]));
			session.save(mrole);
			
		}
		count = 1;
		return count;
	}

	public List<Integer> getRoleOfMenu(Menu menu) {
		Session session = getSession();
		Query query = session.createQuery("select e from MenuRole e where e.menuId=:menuId");
		query.setParameter("menuId", menu.getId());
		List<Integer> rolesOfMenu = new ArrayList<Integer>();
		List<MenuRole> mrs = query.list();
		for(MenuRole mr : mrs)
			rolesOfMenu.add(mr.getId());
		return rolesOfMenu;
	}

}
