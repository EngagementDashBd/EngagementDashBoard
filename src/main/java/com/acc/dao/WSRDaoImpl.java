package com.acc.dao;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.acc.entity.Project;
import com.acc.entity.ResourceMaster;
import com.acc.entity.ResourceTaskAndVocation;
import com.acc.entity.ResourceTaskHelper;
import com.acc.entity.WeeklyStatus;

@Repository
public class WSRDaoImpl extends AbstractDao implements WSRDaoFacade{
	
	int a=0;
	public int addResourceTask( WeeklyStatus weeklyStatus, ResourceTaskHelper resourceTask, String projectName ){
		{
		System.out.println("addResourceTask( WeeklyStatus weeklyStatus, ResourceTaskHelper resourceTask, String projectName ){ inside function");
			Session session=getSession();
			WeeklyStatus weeklyStatusDB= new WeeklyStatus();
			weeklyStatusDB.setApplicationName(projectName);
			weeklyStatusDB.setReleaseName(weeklyStatus.getReleaseName());
			weeklyStatusDB.setReleaseDate(weeklyStatus.getReleaseDate());
			weeklyStatusDB.setWeekStart(weeklyStatus.getWeekStart());
			weeklyStatusDB.setWeekend(weeklyStatus.getWeekend());
			weeklyStatusDB.setNextWeekPlan(weeklyStatus.getNextWeekPlan());
			weeklyStatusDB.setProjectAccomplishment(weeklyStatus.getProjectAccomplishment());
			weeklyStatusDB.setRisk(weeklyStatus.getRisk());
			
			//a=weeklyStatusDB.getWorkId();
			session.save(weeklyStatusDB);
			
		List<ResourceTaskAndVocation> rtaskGet = resourceTask.getResourceTaskList();
		for(ResourceTaskAndVocation rtaskDBSet: rtaskGet)
		{
			
			rtaskDBSet.setWorkId(weeklyStatusDB.getWorkId());
			
			session.save(rtaskDBSet);
		}

		
		return 1;
	}
	}

	public List<WeeklyStatus> getWSRRecordsByProject(String projectName)
		{
			
		Session session=getSession();
		
		List<WeeklyStatus> wsrRecords = new ArrayList<WeeklyStatus>();
		Query query=session.createQuery("select e from  WeeklyStatus e where e.applicationName=:projectName");
		query.setParameter("projectName",projectName);
		List<WeeklyStatus> wsrList = query.list();
		
		for(WeeklyStatus records: wsrList)
		{
			wsrRecords.add(records);
			
			
		}
		
		return wsrRecords;
		}

	public List<ResourceTaskAndVocation> getVacationdetailsByWorkID(int workid) {
		Session session=getSession();
		List<ResourceTaskAndVocation> workRecords = new ArrayList<ResourceTaskAndVocation>();
		Query query=session.createQuery("select e from  ResourceTaskAndVocation e where e.workId=:workID");
		query.setParameter("workID",workid);
		List<ResourceTaskAndVocation> workList = query.list();
		for(ResourceTaskAndVocation record: workList)
		{
			workRecords.add(record);
			
		}
		
		return workRecords;
	}

	public WeeklyStatus getWSRRecordsByWorkID(int workid) {
		Session session=getSession();
		WeeklyStatus wsrWorkRecords = new WeeklyStatus();
		
		Query query=session.createQuery("select e from  WeeklyStatus e where e.workId=:workID");
		query.setParameter("workID",workid);
		List<WeeklyStatus> wsrWorkList = query.list();
				for(WeeklyStatus record: wsrWorkList)
		{
					wsrWorkRecords=record;
			
		}
	
		
		return wsrWorkRecords;
	}

	@SuppressWarnings("null")
	public int addWeeklyStatus(WeeklyStatus weeklyResource, String projectName) {
		// TODO Auto-generated method stub
		System.out.println("addWeeklyStatus(WeeklyStatus weeklyResource, String projectName) pointer");
		Session session=getSession();
		WeeklyStatus weeklyStatusDB= new WeeklyStatus();
		weeklyStatusDB.setApplicationName(projectName);
		weeklyStatusDB.setReleaseName(weeklyResource.getReleaseName());
		weeklyStatusDB.setReleaseDate(weeklyResource.getReleaseDate());
		weeklyStatusDB.setWeekStart(weeklyResource.getWeekStart());
		weeklyStatusDB.setWeekend(weeklyResource.getWeekend());
		weeklyStatusDB.setNextWeekPlan(weeklyResource.getNextWeekPlan());
		weeklyStatusDB.setProjectAccomplishment(weeklyResource.getProjectAccomplishment());
		weeklyStatusDB.setRisk(weeklyResource.getRisk());
		
		
		
		//a=weeklyStatusDB.getWorkId();
	//	session.save(weeklyStatusDB);
		/*int workid= (Integer)weeklyStatusDB.getWorkId();*/
	
		/*session1.setAttribute("workid", workid);*/
		return 1;
	}

	
		
	}


