package com.acc.dao;

import java.util.Map;

import com.acc.entity.Program;

public interface StatisticsDaoFacade {
	public Map<String,Integer> getProgramWiseReport(Integer portfolioId);
	public Map<String,Integer> getProjectWiseReport(Integer programId);

}
