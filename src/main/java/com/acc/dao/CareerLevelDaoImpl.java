package com.acc.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.acc.entity.CareerLevel;
@Repository
public class CareerLevelDaoImpl extends AbstractDao implements CareerLevelDaoFacade {

	public CareerLevel getCarrerLevelDetails(Integer level) {
		Session session = getSession();
		CareerLevel careerLevel = new CareerLevel();
		Query query = session.createQuery("select e from CareerLevel e where e.level=:level");
		query.setParameter("level", level);
		List<CareerLevel> careerlevel = query.list();
		for(CareerLevel career : careerlevel)
			careerLevel = career;
		return careerLevel;
	}

	public Integer updateCareerLevel(CareerLevel careerLevel) {
		Session session = getSession();
		Integer count = 0;
		Query query = session.createQuery("update CareerLevel e set e.shiftbAmount=:bamount , e.shiftcAmount=:camount where e.level=:level");
		query.setParameter("level", careerLevel.getLevel());
		query.setParameter("bamount", careerLevel.getShiftbAmount());
		query.setParameter("camount", careerLevel.getShiftcAmount());
		count  = query.executeUpdate();
		return count;
	}

	public List<CareerLevel> getAllLevels() {
		List<CareerLevel> levels = null;
		Session session = getSession();
		Query query = session.createQuery("select e from CareerLevel e");
		levels = query.list();
		return levels;
	}

	public CareerLevel getCarrerLevelByDesignation(String designation) {
		Session session = getSession();
		Query query = session.createQuery("select e from CareerLevel e where e.designation=:designation");
		query.setParameter("designation", designation);
		List<CareerLevel> careerLevels = query.list();
		CareerLevel level = new CareerLevel();
		for(CareerLevel careerLevel : careerLevels)
		{
			level.setDesignation(careerLevel.getDesignation());
			level.setLevel(careerLevel.getLevel());
			level.setShiftbAmount(careerLevel.getShiftbAmount());
			level.setShiftcAmount(careerLevel.getShiftcAmount());
		}
		return level;
	}

}
