package com.acc.dao;

import java.sql.SQLException;

import com.acc.dto.LoginUser;
import com.acc.entity.ResourceMaster;

public interface LoginDao {
public LoginUser getUser(String username);
public ResourceMaster loginEmployee(String enterpriseId,String password) throws ClassNotFoundException, SQLException;
public int signupEmployee(String enterpriseId, String password) throws ClassNotFoundException, SQLException;
}
