package com.acc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acc.dao.CareerLevelDaoFacade;
import com.acc.entity.CareerLevel;
@Service
public class CareerLevelServiceImpl implements CareerLevelServiceFacade {
	@Autowired
	CareerLevelDaoFacade careerLevelDao;
	@Transactional(readOnly=true)
	public CareerLevel getCarrerLevelDetails(Integer level) {
		CareerLevel careerLevel = careerLevelDao.getCarrerLevelDetails(level);
		return careerLevel;
	}
	@Transactional
	public Integer updateCareerLevel(CareerLevel careerLevel) {
		Integer count = 0;
		count = careerLevelDao.updateCareerLevel(careerLevel);
		return count;
	}
	@Transactional(readOnly=true)
	public List<CareerLevel> getAllLevels() {
		List<CareerLevel> levels = careerLevelDao.getAllLevels();
		return levels;
	}
	@Transactional(readOnly=true)
	public CareerLevel getCarrerLevelByDesignation(String designation) {
		CareerLevel level = careerLevelDao.getCarrerLevelByDesignation(designation);
		return level;
	}

}
