package com.acc.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.acc.dao.ReportDao;
import com.acc.entity.CalendarData;
import com.acc.entity.CareerLevel;
import com.acc.entity.ResourceMaster;
@Service

	
public class ReportServiceImplementation implements ReportServiceFacade {
	@Autowired
	ReportDao reportdao;
	
	@Transactional(readOnly=true)
	public ArrayList<Integer> generateReport(String month, int year, int fortnight, long employeeId) {
		ArrayList<Integer> reportData = new ArrayList<Integer>();
		try{
			reportData = reportdao.generateReport(month, year, fortnight, employeeId);
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return reportData;
	}

	@Transactional(readOnly=true)
	public CalendarData getCalendarData(long employeeId, int fortnight, String month, int year) {
		CalendarData calendarData = new CalendarData();
		try{
			calendarData = reportdao.getCalendarData(employeeId, fortnight, month, year);
			
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return calendarData;
	}
	/*@Transactional
	public List<CalendarData> getCalendarData(long employeeId, String startDate, String endDate) {
	
		List<CalendarData> calendarDataList = reportdao.getCalendarData(employeeId, startDate, endDate);
		
		return calendarDataList;
	}*/
	@Transactional(readOnly=true)
	public List<CareerLevel> getAllCareerLevels() {
		List<CareerLevel> allCareerLevels = new ArrayList<CareerLevel>();
		allCareerLevels = reportdao.getAllCareerLevels();
		return allCareerLevels;
	}
	@Transactional(readOnly=true)
	public String getTeamName(Integer projectId) {
		String teamName =reportdao.getTeamName(projectId);
		return teamName;
	}

}
