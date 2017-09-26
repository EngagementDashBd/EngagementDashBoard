package com.acc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="career_level")
public class CareerLevel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2770058742879837370L;
	/**
	 * 
	 */
	
	@Id
	@Column(name = "career_level")
	private Integer level;
	@Column(name = "shiftb_amount")
	private Integer shiftbAmount;
	@Column(name = "shiftc_amount")
	private Integer shiftcAmount;
	@Column(name = "designation")
	String designation;
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getShiftbAmount() {
		return shiftbAmount;
	}
	public void setShiftbAmount(Integer shiftbAmount) {
		this.shiftbAmount = shiftbAmount;
	}
	public Integer getShiftcAmount() {
		return shiftcAmount;
	}
	public void setShiftcAmount(Integer shiftcAmount) {
		this.shiftcAmount = shiftcAmount;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
}
