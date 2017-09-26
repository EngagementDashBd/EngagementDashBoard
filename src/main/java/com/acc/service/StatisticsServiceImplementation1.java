package com.acc.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acc.dao.StatisticsDao1;

//import com.acc.dao.StatisticsDao;

@Service
public class StatisticsServiceImplementation1 implements StatisticsServiceFacade1  {
	@Autowired
	StatisticsDao1 statisticsdao1;
	@Transactional(readOnly=true)
	public   Map<String,Integer> statistics() {
		 Map<String,Integer> shiftCount = new HashMap<String, Integer>();
		try
		{
			shiftCount = statisticsdao1.statistics();
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return shiftCount;
	}
}
