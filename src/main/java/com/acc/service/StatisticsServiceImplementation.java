package com.acc.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acc.dao.StatisticsDaoFacade;
import com.acc.entity.Program;

@Service
public class StatisticsServiceImplementation implements StatisticsServiceFacade{
	@Autowired
	StatisticsDaoFacade statisticsDaoImpl;
	@Transactional(readOnly=true)
	public Map<String, Integer> getProgramWiseReport(Integer portfolioId) {
		Map<String, Integer> shiftAmount = statisticsDaoImpl.getProgramWiseReport(portfolioId);
		return shiftAmount;
	}
	@Transactional(readOnly=true)
	public Map<String, Integer> getProjectWiseReport(Integer programId) {
		Map<String, Integer> shiftAmount = statisticsDaoImpl.getProjectWiseReport(programId);
		return shiftAmount;
	}

}
