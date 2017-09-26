package com.acc.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.acc.entity.EmployeeHub;
import com.acc.entity.EmployeeProject;
import com.acc.entity.ResourceMaster;
@Repository
public class BatchDaoImpl extends AbstractDao implements BatchDaoFacade{

	public void moveEmployeeToHub() {
		System.out.println("firstmethod");
		Session session = getSession();
		Calendar calendar = Calendar.getInstance();
		Date today = calendar.getTime();
		System.out.println(today);
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date hubStartDate = calendar.getTime();
		Query query = session.createQuery("select e from EmployeeProject e where e.recommendToHold=:recommendToHold and e.roleEndDate=CURRENT_DATE()");
		query.setParameter("recommendToHold",false);
		List<EmployeeProject> roleOffEmployeeList = query.list();
		System.out.println(roleOffEmployeeList.size());
		if(roleOffEmployeeList.size() != 0)
		{
			for(EmployeeProject emp : roleOffEmployeeList)
			{
				/*System.out.println(emp.getEmployeeId());
				query = session.createQuery("update ResourceMaster e set e.employeeStatus=:employeeStatus where e.employeeId=:employeeId");
				 query.setParameter("employeeStatus","hub");
				 query.setParameter("employeeId",emp.getEmployeeId());
				 query.executeUpdate();*/
				 EmployeeHub employeeHub = new EmployeeHub();
				 employeeHub.setEmployeeId(emp.getEmployeeId());
				 employeeHub.setHubStartDate(hubStartDate);
				 employeeHub.setCreatedBy("Administrator");
				 session.save(employeeHub);
				 
			}
		}
	}

	public void setEmployeeStatus() {
		
		Session session = getSession();
		Query query3 = session.createQuery("select e from EmployeeHub e where e.hubStartDate=CURRENT_DATE()");
		List<EmployeeHub> employees = query3.list();
		System.out.println(employees.size());
		if(employees.size() != 0)
		{
			for(EmployeeHub employee : employees)
			{
				Query query = session.createQuery("update ResourceMaster e set e.employeeStatus=:employeeStatus where e.employeeId=:employeeId");
				 query.setParameter("employeeStatus","hub");
				 query.setParameter("employeeId",employee.getEmployeeId());
				 query.executeUpdate();
				 
				 Query query1 = session.createQuery("update EmployeeProject e set e.active=:active where e.employeeId=:employeeId");
				 query1.setParameter("active",false);
				 query1.setParameter("employeeId",employee.getEmployeeId());
				 query1.executeUpdate();
				 
				 Query query2 = session.createQuery("update EmployeeHub e set e.active=:active where e.employeeId=:employeeId");
				 query2.setParameter("active",true);
				 query2.setParameter("employeeId",employee.getEmployeeId());
				 query2.executeUpdate();
			}
		}
		
	}

	public void roleOffEmployee() {
		Session session = getSession();
		Query query = session.createQuery("select e from ResourceMaster e where e.roleOffDate=CURRENT_DATE()");
		List<ResourceMaster> employees = query.list();
		if(employees.size() != 0)
		{
			for(ResourceMaster employee : employees)
			{
				Query query2 = session.createQuery("update ResourceMaster e set e.employeeStatus=:employeeStatus,e.active=:active where e.employeeId=:employeeId");
				 query2.setParameter("active",false);
				 query2.setParameter("employeeStatus","roleoff");
				 query2.setParameter("employeeId",employee.getEmployeeId());
				 query2.executeUpdate();
				 		 
				 Query query1 = session.createQuery("update EmployeeHub e set e.active=:active where e.employeeId=:employeeId");
				 query1.setParameter("active",false);
				 query1.setParameter("employeeId",employee.getEmployeeId());
				 query1.executeUpdate();
			}
		}
		
	}
	public void setHubStatus()
	{
		Session session = getSession();
		Query query = session.createQuery("select e from EmployeeHub e where e.hubEndDate=CURRENT_DATE()");
		List<EmployeeHub> employees = query.list();
		if(employees.size() != 0)
		{
			for(EmployeeHub employee : employees)
			{
				Query query2 = session.createQuery("update ResourceMaster e set e.employeeStatus=:employeeStatus where e.employeeId=:employeeId");
				query2.setParameter("employeeStatus","active");
				 query2.setParameter("employeeId",employee.getEmployeeId());
				 query2.executeUpdate();
				 		 
				 Query query1 = session.createQuery("update EmployeeHub e set e.active=:active where e.employeeId=:employeeId");
				 query1.setParameter("active",false);
				 query1.setParameter("employeeId",employee.getEmployeeId());
				 query1.executeUpdate();
			}
		}
	}
	public void setProjectStatus()
	{
		Session session = getSession();
		Query query = session.createQuery("select e from EmployeeProject e where e.roleStartDate=CURRENT_DATE()");
		List<EmployeeProject> employees = query.list();
		if(employees.size() != 0)
		{
			for(EmployeeProject employee : employees)
			{
				Query query2 = session.createQuery("update EmployeeProject e set e.active=:active where e.employeeId=:employeeId");
				query2.setParameter("active",true);
				 query2.setParameter("employeeId",employee.getEmployeeId());
				 query2.executeUpdate();
			}
		}
	
	}

}
