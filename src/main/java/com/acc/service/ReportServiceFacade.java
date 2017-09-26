package com.acc.service;

import java.util.ArrayList;
import java.util.List;

import com.acc.entity.CalendarData;
import com.acc.entity.CareerLevel;

public interface ReportServiceFacade {
	public ArrayList<Integer> generateReport(String month, int year, int fortnight, long employeeId);
	public CalendarData getCalendarData(long employeeId,int fortnight, String month, int year);
	//public List<CalendarData> getCalendarData(long employeeId,String startDate,String endDate);
	public String getTeamName(Integer projectId);
	public List<CareerLevel> getAllCareerLevels();
}

