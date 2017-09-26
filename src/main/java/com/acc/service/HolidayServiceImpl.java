package com.acc.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acc.dao.HolidayDaoFacade;
import com.acc.entity.Holiday;

@Service
public class HolidayServiceImpl implements HolidayServiceFacade {
	@Autowired
	HolidayDaoFacade holidayDao;
	@Transactional(readOnly=true)
	public List<Holiday> getAllHolidayData() {
		List<Holiday> allHolidayData = holidayDao.getAllHolidayData();
		return allHolidayData;
	}

}
