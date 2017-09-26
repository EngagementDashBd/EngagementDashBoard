package com.acc.dao;

import java.util.List;

import com.acc.entity.CareerLevel;

public interface CareerLevelDaoFacade {
	public List<CareerLevel> getAllLevels();
	public CareerLevel getCarrerLevelDetails(Integer level);
	public Integer updateCareerLevel(CareerLevel careerLevel);
	public CareerLevel getCarrerLevelByDesignation(String designation);
}
