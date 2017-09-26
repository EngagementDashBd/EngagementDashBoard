package com.acc.dao;

import java.sql.SQLException;
import java.util.Map;

import com.acc.entity.CalendarData;

public interface TimesheetDaoFacade {
	public int calendarDataStore(Long employeeId, int year, String month, Map<Integer, String>  shiftData, String flag);
	public CalendarData getCalendarData(long employeeId, String startMonth, int startYear, String endMonth, int endYear);
	public int approveTimesheet(Long employeeId, String startMonth, int startYear, String endMonth, int endYear);
	public int rejectTimesheet(Long employeeId, String startMonth, int startYear, String endMonth, int endYear);
	public boolean submitPermit(String endMonth);
}
