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
@Table(name = "holiday")
public class Holiday implements Serializable {
	;
	/**
	 * 
	 */
	private static final long serialVersionUID = -3757960228194473326L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	Integer id;
	@Column(name = "date")
	Date date;
	@Column(name = "reason")
	String reason;
	@Column(name = "is_on_shore_holiday")
	Boolean isOnShoreHoliday;
	@Column(name = "is_off_shore_holiday")
	Boolean isOffShoreHoliday;
	public Boolean getIsOnShoreHoliday() {
		return isOnShoreHoliday;
	}
	public void setIsOnShoreHoliday(Boolean isOnShoreHoliday) {
		this.isOnShoreHoliday = isOnShoreHoliday;
	}
	public Boolean getIsOffShoreHoliday() {
		return isOffShoreHoliday;
	}
	public void setIsOffShoreHoliday(Boolean isOffShoreHoliday) {
		this.isOffShoreHoliday = isOffShoreHoliday;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	

}
