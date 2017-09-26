package com.acc.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import com.acc.dao.DaoFacade;
import com.acc.dao.LoginDao;
import com.acc.entity.ResourceMaster;
@Service
public class LoginServiceImplementation implements LoginService{
	@Autowired
	LoginDao logindao;
	ResourceMaster resource = new ResourceMaster();
	@Transactional(readOnly=true)
	public ResourceMaster loginEmployee(String enterpriseId, String password) {
		
		try{
			resource = logindao.loginEmployee(enterpriseId,password);
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return resource;
	}

	@Transactional
	public int signupEmployee(String enterpriseId, String password) {
		int count = 0;
		try{
			count =logindao.signupEmployee(enterpriseId,password);
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return count;
	}
}

