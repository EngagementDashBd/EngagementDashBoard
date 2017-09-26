package com.acc.dao.usermanagement;

import java.util.List;

import com.acc.dto.PrivilegesDTO;

public interface MenuDao {
public List<PrivilegesDTO> getMenuByRoles(List<String> roles);
}