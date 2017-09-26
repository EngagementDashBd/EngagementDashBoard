package com.acc.service;

import java.util.List;

import com.acc.entity.Roles;

public interface RolesFacade {
	public List<Roles> getAllRoles();
	public Roles getRoleById(Integer id);
	public Integer updateRole(Roles role,String[] menus);
	public Integer addRole(Roles role,String[] menus);
	public List<Integer> getMenuListByRole(Roles role);  
}
