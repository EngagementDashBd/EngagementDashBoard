package com.acc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="location")
public class Location  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5802643814446774115L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long locationId;
	@Column(name = "name")
	String locationName;
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
}
