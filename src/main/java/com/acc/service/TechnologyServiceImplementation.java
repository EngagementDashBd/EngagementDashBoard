package com.acc.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acc.dao.TechnologyDao;
import com.acc.dao.TimesheetDaoFacade;
import com.acc.entity.Technology;
@Service
public class TechnologyServiceImplementation implements TechnologyServiceFacade {
	@Autowired
	TechnologyDao technologyDao;
	@Transactional(readOnly=true)
	public List<Technology> getAllTechnology() {
		List<Technology> technologylist = new ArrayList<Technology>();
		try{
			technologylist = technologyDao.getAllTechnology();
			
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return technologylist;
	}

	@Transactional
	public int deleteTechnology(Integer technologyId) {
		int count = 0;
		try{
			count = technologyDao.deleteTechnology(technologyId);
			
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
	@Transactional(readOnly=true)
	public int addTechnology(Technology technology) {
		int count = 0;
		try{
			count = technologyDao.addTechnology(technology);
			
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
	@Transactional(readOnly=true)
	public Integer getTechIdByName(String technologyName) {
		Integer technologyId = 0;
		technologyId = technologyDao.getTechIdByName(technologyName);
		return technologyId;
	}


}
