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
public class RolesDaoImpl extends AbstractDao implements RolesDaoFacade{

	public List<Roles> getAllRoles() {
		List<Roles> allRoles = new ArrayList<Roles>();
		Session session = getSession(); 
		Query query = session.createQuery("select e from Roles e");
		allRoles = query.list();
		return allRoles;
	}

	public Roles getRoleById(Integer id) {
		Roles role = new Roles();
		Session session = getSession();
		Query query = session.createQuery("select e from Roles e where e.id=:id");
		query.setParameter("id",id);
		List<Roles> roles = query.list();
		for(Roles role1 : roles)
			role = role1;
		return role;
	}

	public Integer updateRole(Roles role,String[] menus) {
		int count = 0;
		Session session = getSession();
		Query query = session.createQuery("update Roles e set e.name=:name where e.id=:id");
		query.setParameter("name",role.getName());
		query.setParameter("id",role.getId());
		count = query.executeUpdate();
		Query query1 = session.createQuery("delete from MenuRole e where e.roleId=:roleId");
		query1.setParameter("roleId",role.getId());
		query1.executeUpdate();
		for(int i = 0 ; i < menus.length ; i++ )
		{
			MenuRole menur = new MenuRole();
			menur.setMenuId(Integer.valueOf(menus[i]));
			menur.setRoleId(role.getId());
			session.save(menur);
		}
		return count;
	}

	public Integer addRole(Roles role, String[] menus) {
		int count = 0;
		Session session = getSession();
		Roles role1 = new Roles();
		role1.setName(role.getName());
		session.save(role1);
		Query query1 = session.createQuery("select e from Roles e where e.name=:name");
		query1.setParameter("name",role.getName());
		List<Roles> roleList =  query1.list();
		Roles role2 = new Roles();
		for(Roles r : roleList)
			role2 = r;
		for(int i = 0 ; i < menus.length ; i++ )
		{
			MenuRole menur = new MenuRole();
			menur.setMenuId(Integer.valueOf(menus[i]));
			menur.setRoleId(role2.getId());
			session.save(menur);
		}
		count = 1;
		return count;
	}

	public List<Integer> getMenuListByRole(Roles role) {
		Session session = getSession();
		Query query = session.createQuery("select e from MenuRole e where e.roleId=:roleId");
		query.setParameter("roleId", role.getId());
		List<MenuRole> menuRole = query.list();
		List<Integer> menuOfRole = new ArrayList<Integer>();
		for(MenuRole menur : menuRole)
		{
			menuOfRole.add(menur.getMenuId());
		}
		return menuOfRole;
	}

}
