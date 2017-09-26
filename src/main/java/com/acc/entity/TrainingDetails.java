package com.acc.entity;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Training_details")
public class TrainingDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3378142348987474896L;

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "training_id")
	Long trainingId;
	
	public Long getTrainingId() {
		return trainingId;
	}

	public void setTrainingId(Long trainingId) {
		this.trainingId = trainingId;
	}

	@Column(name = "enterprise_id")
	private String	enterpriseId;
	
	
	@Column(name = "topic_id")
	private Long topicId;
	
	@Column(name = "completion_status")
	Boolean completionStatus;
	
	@Column(name = "slide_number")
	private int slideNumber;
	
	@Column(name = "completion_date")
	Date completionDate;

	public Date getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
	}

	public String getenterpriseId() {
		return enterpriseId;
	}

	public void setenterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Long getTopicId() {
		return topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

	public Boolean getCompletionStatus() {
		return completionStatus;
	}

	public void setCompletionStatus(Boolean completionStatus) {
		this.completionStatus = completionStatus;
	}

	public int getslideNumber() {
		return slideNumber;
	}

	public void setslideNumber(int slideNumber) {
		this.slideNumber = slideNumber;
	}
}
