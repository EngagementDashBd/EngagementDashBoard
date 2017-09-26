package com.acc.dao;

import java.sql.SQLException;
import java.util.List;

import com.acc.entity.Technology;

public interface TechnologyDao {
	public List<Technology> getAllTechnology()throws ClassNotFoundException,SQLException;
	public int deleteTechnology(Integer technologyId)throws ClassNotFoundException,SQLException;
	public int addTechnology(Technology technology)throws ClassNotFoundException,SQLException;
	public Integer getTechIdByName(String technologyName);

}
