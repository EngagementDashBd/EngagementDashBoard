package com.acc.service;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acc.dao.TimesheetDaoFacade;
import com.acc.entity.CalendarData;

@Service
public class TimesheetServiceImplementation implements TimesheetServiceFacade{

	@Autowired
	TimesheetDaoFacade timesheetDao;
	@Transactional(readOnly=true)
	public CalendarData getCalendarData(long employeeId, String startMonth, int startYear, String endMonth,
			int endYear) {
		CalendarData calendarData = timesheetDao.getCalendarData(employeeId, startMonth, startYear, endMonth, endYear);
		return calendarData;
	}
	
	@Transactional
	public int calendarDataStore(long employeeId, int year, String month, Map<Integer, String> shiftData, String flag) {
		int count = 0;
		count = timesheetDao.calendarDataStore(employeeId, year, month, shiftData, flag);
		return count;
	}

	@Transactional
	public int approveTimesheet(Long employeeId, String startMonth, int startYear, String endMonth, int endYear) {
		int count = 0;
		count = timesheetDao.approveTimesheet(employeeId, startMonth, startYear, endMonth, endYear);
		return count;
	}

	@Transactional
	public int rejectTimesheet(Long employeeId, String startMonth, int startYear, String endMonth, int endYear) {
		int count = 0;
		count = timesheetDao.rejectTimesheet(employeeId, startMonth, startYear, endMonth, endYear);
		return count;
	}
	@Transactional
	public boolean submitPermit(String endMonth) {
		boolean submitPermit = timesheetDao.submitPermit(endMonth);
		return submitPermit;
	}



}
