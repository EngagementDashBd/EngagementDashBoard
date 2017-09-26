package com.acc.dao;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.acc.entity.CalendarData;
import com.acc.entity.DayData;
import com.acc.entity.Timesheet;

@Repository
public class TimesheetDaoImplementation extends AbstractDao implements TimesheetDaoFacade{

	public CalendarData getCalendarData(long employeeId, String startMonth, int startYear, String endMonth,
			int endYear) {
		CalendarData calendarData = new CalendarData();
		calendarData.setEmployeeId(employeeId);
		calendarData.setMonth(startMonth + "-" + endMonth);
		calendarData.setYear(startYear);
		List<DayData> dayData = new ArrayList<DayData>();
		Session session=getSession();
		Query query=session.createQuery("select e from  Timesheet e where employeeId=:empId and month=:month and year=:year and date > 15");
		query.setParameter("empId", employeeId);
		query.setParameter("month", startMonth);
		query.setParameter("year", startYear);
		List<Timesheet> timesheet=query.list();
		for(Timesheet time : timesheet)
		{
			DayData day_Data = new DayData();
			day_Data.setDate(time.getDate());
			day_Data.setShift(time.getShift());
			dayData.add(day_Data);
		}
		query=session.createQuery("select e from  Timesheet e where employeeId=:empId and month=:month and year=:year and date <= 15");
		query.setParameter("empId", employeeId);
		query.setParameter("month", endMonth);
		query.setParameter("year", endYear);
		List<Timesheet> timesheet2 = query.list();
		for(Timesheet time : timesheet2)
		{
			DayData day_Data = new DayData();
			day_Data.setDate(time.getDate());
			day_Data.setShift(time.getShift());
			dayData.add(day_Data);
		}
		calendarData.setDayData(dayData);
		return calendarData;
	}
	public int calendarDataStore(Long employeeId, int year, String month, Map<Integer, String>  shiftData, String flag)
		 {
		int count;
		Session session=getSession();
		if(flag.equals("update")){
			 for(Map.Entry<Integer, String> data : shiftData.entrySet())
			 {
				 if(data.getValue().equals("b") || data.getValue().equals("c"))
				{
					 Query checkQuery = session.createQuery("select t from Timesheet t where t.employeeId=:empId and t.date=:date and t.month=:month and t.year=:year");
					 checkQuery.setParameter("empId", employeeId);
					 checkQuery.setParameter("date",data.getKey());
					 checkQuery.setParameter("month",month);
					 checkQuery.setParameter("year",year);
					 List<Timesheet> timesheetList = checkQuery.list();
					 if(timesheetList.isEmpty())
					 {
						 Timesheet timesheet = new Timesheet();
						 timesheet.setEmployeeId(employeeId);
						 timesheet.setMonth(month);
						 timesheet.setYear(year);
						 timesheet.setDate(data.getKey());
						 timesheet.setShift(data.getValue());
						 timesheet.setStatus("submitted");
						 session.save(timesheet);
					 }
					 else
					 {
						 Query query = session.createQuery("update Timesheet t set t.shift=:shift, t.status=:status where  t.employeeId=:empId and t.date=:date and t.month=:month and t.year=:year");
						 query.setParameter("shift",data.getValue());
						 query.setParameter("empId", employeeId);
						 query.setParameter("date",data.getKey());
						 query.setParameter("month",month);
						 query.setParameter("year",year);
						 query.setParameter("status","submitted");
					 	 query.executeUpdate();
					 }
				}
			 }
		}
		else
		{	
			for(Map.Entry<Integer, String> data : shiftData.entrySet())
			{
				if(data.getValue().equals("b") || data.getValue().equals("c"))
				{
					Timesheet timesheet = new Timesheet();
					timesheet.setEmployeeId(employeeId);
					timesheet.setMonth(month);
					timesheet.setYear(year);
					timesheet.setDate(data.getKey());
					timesheet.setShift(data.getValue());
					timesheet.setStatus("submitted");
					session.save(timesheet);
				}
			}
		}
		 return 1;
	}
	public int approveTimesheet(Long employeeId, String startMonth, int startYear, String endMonth, int endYear) {
		int count1 = 0;
		int count2 = 0;
		Session session=getSession();
		Query query = session.createQuery("update Timesheet t set t.status=:status where t.employeeId=:employeeId and t.month=:month and t.year=:year and t.date > 15)");
		query.setParameter("status", "approved");
		query.setParameter("employeeId", employeeId);
		query.setParameter("month", startMonth);
		query.setParameter("year", startYear);
		count1 = query.executeUpdate();
		query = session.createQuery("update Timesheet t set t.status=:status where t.employeeId=:employeeId and t.month=:month and t.year=:year and (t.date between 1 and 15)");
		query.setParameter("status", "approved");
		query.setParameter("employeeId", employeeId);
		query.setParameter("month", endMonth);
		query.setParameter("year", endYear);
		count2 = query.executeUpdate();
		int count = 0;
		if(count1 >= 1 || count2 >= 1)
			count = 1;
		return count;
	}
	public int rejectTimesheet(Long employeeId, String startMonth, int startYear, String endMonth, int endYear) {
		int count1 = 0;
		int count2 = 0;
		Session session=getSession();
		Query query = session.createQuery("update Timesheet t set t.status=:status where t.employeeId=:employeeId and t.month=:month and t.year=:year and t.date > 15)");
		query.setParameter("status", "rejected");
		query.setParameter("employeeId", employeeId);
		query.setParameter("month", startMonth);
		query.setParameter("year", startYear);
		count1 = query.executeUpdate();
		query = session.createQuery("update Timesheet t set t.status=:status where t.employeeId=:employeeId and t.month=:month and t.year=:year and (t.date between 1 and 15)");
		query.setParameter("status", "rejected");
		query.setParameter("employeeId", employeeId);
		query.setParameter("month", endMonth);
		query.setParameter("year", endYear);
		count2 = query.executeUpdate();
		int count = 0;
		if(count1 >= 1 && count2 >= 1)
			count = 1;
		return count;
	}
	public boolean submitPermit(String endMonth) {
		boolean submitPermit = true;
		Date date = new Date();
		 String[] monthName = { "january", "february", "march", "april", "may", "june", "july",
			        "august", "september", "october", "november", "december" };
		int systemDate = date.getDate();
		int systemMonth = date.getMonth();
		int actualMonth = 0;
		for(int i = 0 ; i < monthName.length ; i++)
		{
			if(monthName[i].equalsIgnoreCase(endMonth))
				actualMonth = i;
		}
		String sysMonth = monthName[systemMonth];
		System.out.println("Sysdate : " + systemDate + "SysMOnth: "+systemMonth+ "sysMonth :"+ sysMonth + "endMonth: "+endMonth);
		if(actualMonth < systemMonth || (actualMonth == systemMonth && systemDate > 12))
		{
			System.out.println("false.............................");
			submitPermit = false;
		}
		return submitPermit;
	}

}
