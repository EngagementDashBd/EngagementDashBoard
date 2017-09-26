package com.acc.service.usermanagement;

import java.util.List;

import com.acc.dto.PrivilegesDTO;


public interface PrivilegesService {
	public List<PrivilegesDTO> getMenuList(List<String> role);
}
