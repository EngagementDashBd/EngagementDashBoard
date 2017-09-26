package com.acc.entity;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "weeklyStatus")
public class WeeklyStatus implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3400862867506106641L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "WORK_id")
	Integer workId;

	@Column(name = "application_name")
	private String	applicationName;
	
	
	@Column(name = "release_name")
	private String	releaseName;
	
	@Column(name = "release_date")
	private String	releaseDate;
	
	@Column(name = "week_start")
	private String	weekStart;
	
	@Column(name = "week_end")
	private String	weekend;
	
	@Column(name = "next_week_plan")
	private String	nextWeekPlan;
	
	@Column(name = "risk")
	private String 	risk; 
			
	@Column(name = "accomplishment")
	private String 	projectAccomplishment; 
	
	

	public String getApplicationName() {
		return applicationName;
	}




	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	
	
	public Integer getWorkId() {
		return workId;
	}




	public void setWorkId(Integer workId) {
		this.workId = workId;
	}




	public String getReleaseName() {
		return releaseName;
	}




	public void setReleaseName(String releaseName) {
		this.releaseName = releaseName;
	}




	public String getReleaseDate() {
		return releaseDate;
	}




	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}




	public String getWeekStart() {
		return weekStart;
	}




	public void setWeekStart(String weekStart) {
		this.weekStart = weekStart;
	}




	public String getWeekend() {
		return weekend;
	}




	public void setWeekend(String weekend) {
		this.weekend = weekend;
	}




	public String getNextWeekPlan() {
		return nextWeekPlan;
	}




	public void setNextWeekPlan(String nextWeekPlan) {
		this.nextWeekPlan = nextWeekPlan;
	}




	public String getRisk() {
		return risk;
	}




	public void setRisk(String risk) {
		this.risk = risk;
	}




	public String getProjectAccomplishment() {
		return projectAccomplishment;
	}




	public void setProjectAccomplishment(String projectAccomplishment) {
		this.projectAccomplishment = projectAccomplishment;
	}




	public WeeklyStatus()
	{
		
	}
	
		

}
