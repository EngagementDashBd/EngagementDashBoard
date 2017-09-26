package com.acc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acc.dao.RolesDaoFacade;
import com.acc.entity.Roles;
@Service
public class RolesServiceImpl implements RolesFacade {
	
	@Autowired
	RolesDaoFacade rolesDao;
	@Transactional(readOnly=true)
	public List<Roles> getAllRoles() {
		List<Roles> allRoles = rolesDao.getAllRoles();
		return allRoles;
	}
	@Transactional
	public Roles getRoleById(Integer id) {
		Roles role = rolesDao.getRoleById(id);
		return role;
	}
	@Transactional
	public Integer updateRole(Roles role,String[] menus) {
		int count = rolesDao.updateRole(role,menus);
		return count;
	}
	@Transactional
	public Integer addRole(Roles role, String[] menus) {
		int count = rolesDao.addRole(role, menus);
		return count;
	}
	@Transactional
	public List<Integer> getMenuListByRole(Roles role) {
		List<Integer> menuOfRole = rolesDao.getMenuListByRole(role);
		return menuOfRole;
	}

}
