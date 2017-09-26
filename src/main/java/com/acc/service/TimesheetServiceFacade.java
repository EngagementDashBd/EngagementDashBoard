package com.acc.service;

import java.util.Map;

import com.acc.entity.CalendarData;

public interface TimesheetServiceFacade {
	public int calendarDataStore(long employeeId, int year, String month, Map<Integer, String> shiftData, String flag);
	public CalendarData getCalendarData(long employeeId,String startMonth,int startYear,String endMonth,int endYear);
	public int approveTimesheet(Long employeeId, String startMonth, int startYear, String endMonth, int endYear);
	public int rejectTimesheet(Long employeeId, String startMonth, int startYear, String endMonth, int endYear);
	public boolean submitPermit(String endMonth);
}
