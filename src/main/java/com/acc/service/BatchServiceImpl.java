package com.acc.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acc.dao.BatchDaoFacade;

@Service
public class BatchServiceImpl implements BatchServiceFacade {
	@Autowired
	BatchDaoFacade batchDao;
	@Transactional(readOnly=true)
	public void moveEmployeeToHub() {
		batchDao.moveEmployeeToHub();
		
	}
	@Transactional
	public void setEmployeeStatus() {
		batchDao.setEmployeeStatus();
	}
	@Transactional
	public void roleOffEmployee() {
		batchDao.roleOffEmployee();
		
	}
	@Transactional
	public void setHubStatus() {
		batchDao.setHubStatus();
		
	}
	@Transactional
	public void setProjectStatus() {
		batchDao.setProjectStatus();
		
	}

}
