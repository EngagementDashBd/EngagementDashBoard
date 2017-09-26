package com.acc.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.acc.entity.Holiday;

@Repository
public class HolidayDaoImpl extends AbstractDao implements HolidayDaoFacade {

	public List<Holiday> getAllHolidayData() {
		Session session=getSession();
		Query query=session.createQuery("select e from Holiday e");
		List<Holiday> allHolidayData = query.list();
		return allHolidayData;
	}

}
