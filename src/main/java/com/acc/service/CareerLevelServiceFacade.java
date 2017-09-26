package com.acc.service;

import java.util.List;

import com.acc.entity.CareerLevel;

public interface CareerLevelServiceFacade {
	public CareerLevel getCarrerLevelDetails(Integer level);
	public Integer updateCareerLevel(CareerLevel careerLevel);
	public List<CareerLevel> getAllLevels();
	public CareerLevel getCarrerLevelByDesignation(String designation);
	
}
