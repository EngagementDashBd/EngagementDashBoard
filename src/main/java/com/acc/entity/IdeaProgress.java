package com.acc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="idea_progressnote")
public class IdeaProgress {
	
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		@Column(name = "id")
		Integer id;
		@Column(name = "idea_id")
		Integer ideaId;
		@Column(name = "developer_id")
		String enterpriseId;
		@Column(name = "progress")
		String progress;
		@Column(name = "created_time")
		Date creationDate;
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public Integer getIdeaId() {
			return ideaId;
		}
		public void setIdeaId(Integer ideaId) {
			this.ideaId = ideaId;
		}
		public String getEnterpriseId() {
			return enterpriseId;
		}
		public void setEnterpriseId(String enterpriseId) {
			this.enterpriseId = enterpriseId;
		}
		public String getProgress() {
			return progress;
		}
		public void setProgress(String progress) {
			this.progress = progress;
		}
		public Date getCreationDate() {
			return creationDate;
		}
		public void setCreationDate(Date creationDate) {
			this.creationDate = creationDate;
		}
		
		
}
