package com.acc.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.acc.service.BatchServiceFacade;



@EnableScheduling
public class RoleOffSchedule
{
	@Autowired
	BatchServiceFacade batchServiceImpl;
	
	@Scheduled(cron = "0 59 23 1/1 * ? ")
	 public void cronTask()
	 {		
		System.out.println("scheduler");		
		batchServiceImpl.moveEmployeeToHub();
		batchServiceImpl.setHubStatus();
				    
	 }
	
	@Scheduled(cron = "0 0 0 1/1 * ? ")
	 public void cronTask1()
	 {		
		System.out.println("scheduler2");		
		batchServiceImpl.setEmployeeStatus();
		batchServiceImpl.roleOffEmployee();
		batchServiceImpl.setProjectStatus();
		    
	 }
 }
	
	


