package com.acc.service;

import com.acc.entity.ResourceMaster;

public interface LoginService {
	public ResourceMaster loginEmployee(String enterpriseId,String password);
	public int signupEmployee(String enterpriseId, String password);
}
