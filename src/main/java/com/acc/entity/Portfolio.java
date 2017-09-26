package com.acc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "project_portfolio")
public class Portfolio implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -161795690459309168L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	Integer portfolioId;
	@Column(name = "name")
	String portfolioName;
	@Column(name = "description")
	String portfolioDescription;
	
	@Column(name = "created_by")
	String createdBy;
	
	
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getPortfolioName() {
		return portfolioName;
	}
	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}
	public String getPortfolioDescription() {
		return portfolioDescription;
	}
	public void setPortfolioDescription(String portfolioDescription) {
		this.portfolioDescription = portfolioDescription;
	}
	
	public Integer getPortfolioId() {
		return portfolioId;
	}
	public void setPortfolioId(Integer portfolioId) {
		this.portfolioId = portfolioId;
	}
	
	
}

