package com.acc.dao;

import java.util.List;

import com.acc.entity.ResourceTaskAndVocation;
import com.acc.entity.ResourceTaskHelper;
import com.acc.entity.WeeklyStatus;

public interface WSRDaoFacade {
	public int addResourceTask(WeeklyStatus weeklyStatus, ResourceTaskHelper resourceTask, String ProjectName);
	public int addWeeklyStatus(WeeklyStatus weeklyResource,  String projectName);
	public List<WeeklyStatus> getWSRRecordsByProject(String projectName);
	public List<ResourceTaskAndVocation> getVacationdetailsByWorkID(int workid);
	public WeeklyStatus getWSRRecordsByWorkID(int workid);


}
