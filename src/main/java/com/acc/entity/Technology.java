package com.acc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="technology")
public class Technology implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2031054406908759366L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "technology_id")
	private int technologyId;
	
	@Column(name = "technology_name")
	private String technologyName;
	
	@Column(name = "technology_description")
	private String technologyDescription;
	

	public String getTechnologyDescription() {
		return technologyDescription;
	}

	public void setTechnologyDescription(String technologyDescription) {
		this.technologyDescription = technologyDescription;
	}

	public int getTechnologyId() {
		return technologyId;
	}

	public void setTechnologyId(int technologyId) {
		this.technologyId = technologyId;
	}

	public String getTechnologyName() {
		return technologyName;
	}

	public void setTechnologyName(String technologyName) {
		this.technologyName = technologyName;
	}
	
	

} 
