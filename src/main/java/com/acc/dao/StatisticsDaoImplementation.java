package com.acc.dao;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.acc.entity.DayData;
import com.acc.entity.Program;
import com.acc.entity.Project;
import com.acc.entity.ResourceMaster;
import com.acc.entity.Timesheet;

@Repository
public class StatisticsDaoImplementation extends AbstractDao implements StatisticsDaoFacade{

	public Map<String, Integer> getProgramWiseReport(Integer portfolioId) {
		Map<String, Integer> shiftAmount = new HashMap<String, Integer>();
		Session session=getSession();
		int bcount = 0;
		int ccount = 0;
		String[] monthName = { "january", "february", "march", "april", "may", "june", "july",
		        "august", "september", "october", "november", "december" };
		Calendar calendar = Calendar.getInstance();
		String startMonth = null;
		String endMonth = null;
		int date = calendar.get(Calendar.DATE);
		 int year = calendar.get(Calendar.YEAR);
		 int month = calendar.get(Calendar.MONTH);
		 int startYear = year;
		 int endYear = year;
		if(date > 15)
		{
			startMonth = monthName[(calendar.get(Calendar.MONTH) - 1)%12];
			endMonth = monthName[(calendar.get(Calendar.MONTH))%12];
			
		}
		else
		{
			startMonth = monthName[(calendar.get(Calendar.MONTH) - 2)%12];
			endMonth = monthName[(calendar.get(Calendar.MONTH) - 1)%12];
		}
		if(date > 15 && month == 0)
		{
			startYear = year - 1;
			endYear = year;
		}
		if(date < 15 && month == 0)
		{
			startYear = year - 1;
			endYear = year - 1;
		}
		
		 
		  	
		
		//select * from employee where project_id in(select  project_id from projects where program_id in(select id from project_program where portfolio_id = 2));
		Query query = session.createQuery("select x from Program x where x.portfolioId=:portfolioId");
		query.setParameter("portfolioId", portfolioId);		
		List<Program> programs = query.list();
		for(Program program : programs)
		{
			System.out.println(program.getProgramName());
			int amount = 0;
			Query query3 = session.createQuery("select e from ResourceMaster e where e.projectId in (select v.id from Project v where v.programId=:programId)");
			query3.setParameter("programId",program.getProgramId());
			List<ResourceMaster> resources = query3.list();
			for(ResourceMaster employee : resources)
			{
				System.out.println(employee.getEmployeeName());
				Query query1=session.createQuery("select e from  Timesheet e where employeeId=:empId and month=:month and year=:year and date > 15");
				query1.setParameter("empId", employee.getEmployeeId());
				query1.setParameter("month", startMonth);
				query1.setParameter("year", startYear);
				List<Timesheet> timesheet=query1.list();
				for(Timesheet time : timesheet)
				{
					if(time.getShift().equals("b"))
						amount += employee.getCareerLevel().getShiftbAmount();
					if(time.getShift().equals("c"))
						amount += employee.getCareerLevel().getShiftcAmount();
						
				}
				
				Query query2=session.createQuery("select e from  Timesheet e where employeeId=:empId and month=:month and year=:year and date <= 15");
				query2.setParameter("empId", employee.getEmployeeId());
				query2.setParameter("month", endMonth);
				query2.setParameter("year", endYear);
				List<Timesheet> timesheet2 = query2.list();
				for(Timesheet time : timesheet2)
				{
					if(time.getShift().equals('b'))
						amount += employee.getCareerLevel().getShiftbAmount();
					if(time.getShift().equals('c'))
						amount += employee.getCareerLevel().getShiftcAmount();
						
				}
			}
			shiftAmount.put(program.getProgramName(), amount);
			
		}
		
		return shiftAmount;
	}

	public Map<String, Integer> getProjectWiseReport(Integer programId) {
		Map<String, Integer> shiftAmount = new HashMap<String, Integer>();
		Session session=getSession();
		int bcount = 0;
		int ccount = 0;
		String[] monthName = { "january", "february", "march", "april", "may", "june", "july",
		        "august", "september", "october", "november", "december" };
		Calendar calendar = Calendar.getInstance();
		String startMonth = null;
		String endMonth = null;
		int date = calendar.get(Calendar.DATE);
		 int year = calendar.get(Calendar.YEAR);
		 int month = calendar.get(Calendar.MONTH);
		 int startYear = year;
		 int endYear = year;
		if(date > 15)
		{
			startMonth = monthName[(calendar.get(Calendar.MONTH) - 1)%12];
			endMonth = monthName[(calendar.get(Calendar.MONTH))%12];
			
		}
		else
		{
			startMonth = monthName[(calendar.get(Calendar.MONTH) - 2)%12];
			endMonth = monthName[(calendar.get(Calendar.MONTH) - 1)%12];
		}
		if(date > 15 && month == 0)
		{
			startYear = year - 1;
			endYear = year;
		}
		if(date < 15 && month == 0)
		{
			startYear = year - 1;
			endYear = year - 1;
		}
		Query query = session.createQuery("select x from Project x where x.programId=:programId");
		query.setParameter("programId", programId);		
		List<Project> projects = query.list();
		for(Project project : projects)
		{
			int amount = 0;
			Query query1 = session.createQuery("select x from ResourceMaster x where x.projectId=:projectId");
			query1.setParameter("projectId",project.getId());
			List<ResourceMaster> resources = query1.list();
			for(ResourceMaster employee : resources)
			{
				System.out.println(employee.getEmployeeName());
				Query query2=session.createQuery("select e from  Timesheet e where employeeId=:empId and month=:month and year=:year and date > 15");
				query2.setParameter("empId", employee.getEmployeeId());
				query2.setParameter("month", startMonth);
				query2.setParameter("year", startYear);
				List<Timesheet> timesheet=query2.list();
				for(Timesheet time : timesheet)
				{
					if(time.getShift().equals("b"))
						amount += employee.getCareerLevel().getShiftbAmount();
					if(time.getShift().equals("c"))
						amount += employee.getCareerLevel().getShiftcAmount();
						
				}
				
				Query query3=session.createQuery("select e from  Timesheet e where employeeId=:empId and month=:month and year=:year and date <= 15");
				query3.setParameter("empId", employee.getEmployeeId());
				query3.setParameter("month", endMonth);
				query3.setParameter("year", endYear);
				List<Timesheet> timesheet2 = query3.list();
				for(Timesheet time : timesheet2)
				{
					if(time.getShift().equals('b'))
						amount += employee.getCareerLevel().getShiftbAmount();
					if(time.getShift().equals('c'))
						amount += employee.getCareerLevel().getShiftcAmount();
						
				}
			}
			shiftAmount.put(project.getProjectName(), amount);
			
		}
		return shiftAmount;
	}

}
