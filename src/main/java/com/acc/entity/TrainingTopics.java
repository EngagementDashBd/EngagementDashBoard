package com.acc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Training_topics")
public class TrainingTopics implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5041233633440535320L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "topic_id")
	Long topicId;
	
	@Column(name = "topic_name")
	private String topicName;
	
	@Column(name = "topic_description")
	private String topicDescription;
	
	@Column(name = "active")
	boolean active;

	public Long getTopicId() {
		return topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public String getTopicDescription() {
		return topicDescription;
	}

	public void setTopicDescription(String topicDescription) {
		this.topicDescription = topicDescription;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	
}
