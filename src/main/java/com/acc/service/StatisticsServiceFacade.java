package com.acc.service;

import java.util.Map;

import com.acc.entity.Program;

public interface StatisticsServiceFacade {
	public Map<String,Integer> getProgramWiseReport(Integer portfolioId);
	public Map<String,Integer> getProjectWiseReport(Integer programId);
}
