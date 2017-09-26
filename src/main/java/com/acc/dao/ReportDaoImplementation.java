package com.acc.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.acc.entity.CalendarData;
import com.acc.entity.CareerLevel;
import com.acc.entity.DayData;
import com.acc.entity.Portfolio;
import com.acc.entity.Program;
import com.acc.entity.Timesheet;
@Repository
public class ReportDaoImplementation extends AbstractDao implements ReportDao{

	public List<CalendarData> getCalendarData(Long employeeId, String startDate, String endDate) {
		CalendarData calendarData1 = new CalendarData();
		CalendarData calendarData2 = new CalendarData();
		List<CalendarData> calendarDataList = new ArrayList<CalendarData>();
		String[] monthName = { "january", "february", "march", "april", "may", "june", "july",
		        "august", "september", "october", "november", "december" };
		List<DayData> dayData = new ArrayList<DayData>();
		List<DayData> dayData2 = new ArrayList<DayData>();
		
		int startingDate = Integer.parseInt(startDate.substring(0, startDate.indexOf('/')));
		int startingMonth = Integer.parseInt(startDate.substring(startDate.indexOf('/') + 1,startDate.lastIndexOf('/')));
		String startMonth = monthName[startingMonth - 1];
		int startYear = Integer.parseInt(startDate.substring(startDate.lastIndexOf('/') + 1));
	
		int endingDate = Integer.parseInt(endDate.substring(0, endDate.indexOf('/')));
		int endingMonth = Integer.parseInt(endDate.substring(endDate.indexOf('/') + 1,endDate.lastIndexOf('/')));
		String endMonth = monthName[endingMonth - 1];
		int endYear = Integer.parseInt(endDate.substring(endDate.lastIndexOf('/') + 1));
		String firstDateRange, secondDateRange;
		System.out.println(endMonth + endYear);
		firstDateRange = "(t.date between " + startingDate + " and 30)";
		secondDateRange = "(t.date between 1 and " + endingDate+")";
		calendarData1.setEmployeeId(employeeId);
		calendarData1.setMonth(startMonth);
		calendarData1.setYear(startYear);
		String firstQuery = "select t from Timesheet t where t.employeeId=:employeeId and t.month=:month and t.year=:year and " + firstDateRange;
		Session session=getSession();
		Query query=session.createQuery(firstQuery);
		query.setParameter("employeeId", employeeId);
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
		calendarData1.setDayData(dayData);
		
		calendarData2.setEmployeeId(employeeId);
		calendarData2.setMonth(endMonth);
		calendarData2.setYear(endYear);
		String secondQuery = "select t from Timesheet t where t.employeeId=:employeeId and t.month=:month and t.year=:year and " + secondDateRange;
		Query query2=session.createQuery(secondQuery);
		query2.setParameter("employeeId", employeeId);
		query2.setParameter("month", endMonth);
		query2.setParameter("year", endYear);
		List<Timesheet> timesheet2=query2.list();
		for(Timesheet time2 : timesheet2)
		{
			DayData day_Data2 = new DayData();
			day_Data2.setDate(time2.getDate());
			day_Data2.setShift(time2.getShift());
			dayData2.add(day_Data2);
		}
		calendarData2.setDayData(dayData2);
		
		for(DayData d : calendarData2.getDayData())
		{
			System.out.println("Date : "+d.getDate() + "shift : " + d.getShift());
		}
		calendarDataList.add(calendarData1);
		calendarDataList.add(calendarData2);
		return calendarDataList;
	}
	public ArrayList<Integer> generateReport(String month, int year, int fortnight, long employeeId) throws ClassNotFoundException, SQLException {
		ArrayList<Integer> reportData = new ArrayList<Integer>();
		int aCount = 0, bCount = 0, cCount = 0;
		String shift="";
		Session session=getSession();
		Query query = null;
		
		if(fortnight == 1)
		{
			query = session.createQuery("select e from  Timesheet e where employeeId=:empid and month=:month and year=:year and (date between 1 and 15)");
		}
		else if(fortnight == 2)
		{
			query = session.createQuery("select e from  Timesheet e where employeeId=:empid and month=:month and year=:year and (date > 15)");
		}
		else if(fortnight == 3)
		{
			query=session.createQuery("select e from  Timesheet e where employeeId=:empid and month=:month and year=:year");
		}
		query.setParameter("empid", employeeId);
		query.setParameter("month", month);
		query.setParameter("year", year);
		List<Timesheet> empList=query.list();
		for(Timesheet shiftData : empList)
		{
			shift = shiftData.getShift();
			if("a".equals(shift))
				aCount++;
			if("b".equals(shift))
				bCount++;
			if("c".equals(shift))
				cCount++;				
		}
		reportData.add(aCount);
		reportData.add(bCount);
		reportData.add(cCount);
		return reportData;
	}
	public CalendarData getCalendarData(Long employeeId, int fortnight, String month, int year) throws ClassNotFoundException, SQLException {
		CalendarData calendarData = new CalendarData();
		calendarData.setEmployeeId(employeeId);
		calendarData.setMonth(month);
		calendarData.setYear(year);
		List<DayData> dayData = new ArrayList<DayData>();
		Session session=getSession();
			Query query=session.createQuery("select e from  Timesheet e where employeeId=:empId and month=:month and year=:year");
			query.setParameter("empId", employeeId);
			query.setParameter("month", month);
			query.setParameter("year", year);
			List<Timesheet> timesheet=query.list();
			for(Timesheet time : timesheet)
			{
				if(fortnight == 1 && time.getDate() <= 15 || fortnight == 2 && time.getDate() > 15 || fortnight == 3)
				{
					DayData day_Data = new DayData();
					day_Data.setDate(time.getDate());
					day_Data.setShift(time.getShift());
					dayData.add(day_Data);
					System.out.println("fortnight :"+fortnight );
					System.out.println("Date " + time.getDate());
				}
			}
			
		
		calendarData.setDayData(dayData);
		return calendarData;
	}
	public String getTeamName(Integer projectId) {
		Session session=getSession();
		String teamName = "";
		String programName = "";
		String protfolioName = "";
		int portfolioId = 0;
		Query query = session.createQuery("select e from Program e where e.programId in (select p.programId from Project p where p.id=:projectId)");
		query.setParameter("projectId", projectId);
		List<Program> programs = query.list();
		for(Program program : programs)
		{
			programName = program.getProgramName();
			portfolioId = program.getPortfolioId();
			
		}
		query = session.createQuery("select e from Portfolio e where e.portfolioId=:portfolioId");
		query.setParameter("portfolioId", portfolioId);
		List<Portfolio> portfolios = query.list();
		for(Portfolio portfolio : portfolios)
		{
			protfolioName = portfolio.getPortfolioName();
		}
		teamName = protfolioName + " "+ programName;
		return teamName;
	}
	public List<CareerLevel> getAllCareerLevels() {
		List<CareerLevel> allCareerLevels = new ArrayList<CareerLevel>();
		Session session=getSession();
		Query query = session.createQuery("select e from CareerLevel e");
		allCareerLevels = query.list();
		return allCareerLevels;
	}

}

