package com.acc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="project_location")
public class ProjectLocation implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8925427463118043249L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	Integer id;
	@Column(name = "portfolio_id")
	Integer portfolioId;
	@Column(name = "location_id")
	Integer locationId;
	@Column(name = "billable_wbs")
	String billableWbs;
	@Column(name = "non_billable_wbs")
	String nonBillableWbs;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPortfolioId() {
		return portfolioId;
	}
	public void setPortfolioId(Integer portfolioId) {
		this.portfolioId = portfolioId;
	}
	public Integer getLocationId() {
		return locationId;
	}
	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}
	public String getBillableWbs() {
		return billableWbs;
	}
	public void setBillableWbs(String billableWbs) {
		this.billableWbs = billableWbs;
	}
	public String getNonBillableWbs() {
		return nonBillableWbs;
	}
	public void setNonBillableWbs(String nonBillableWbs) {
		this.nonBillableWbs = nonBillableWbs;
	}

}
