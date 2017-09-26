package com.acc.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.acc.entity.CalendarData;
import com.acc.entity.CareerLevel;

public interface ReportDao {
	public CalendarData getCalendarData(Long employeeId, int fortnight, String month, int year)throws ClassNotFoundException,SQLException;
	public List<CalendarData> getCalendarData(Long employeeId,String startDate,String endDate);
	public ArrayList<Integer> generateReport(String month, int year, int fortnight, long employeeId)throws ClassNotFoundException,SQLException;
	public String getTeamName(Integer projectId);
	public List<CareerLevel> getAllCareerLevels();
}
