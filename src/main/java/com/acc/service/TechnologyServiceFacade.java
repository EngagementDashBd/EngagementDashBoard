package com.acc.service;

import java.util.List;

import com.acc.entity.Technology;

public interface TechnologyServiceFacade {
	public List<Technology> getAllTechnology();
	public int deleteTechnology(Integer technologyId);
	public int addTechnology(Technology technology);
	public Integer getTechIdByName(String technologyName);
}
