package com.acc.service;

import java.util.ArrayList;

import com.acc.entity.Portfolio;

public interface PortfolioServiceFacade {
	public int addPortfolioDetails(Portfolio portfolio, String creatorName);
	public ArrayList<Portfolio> getPortfolioDetails();
	public Portfolio getPortfolio(Integer portfolioId);
	public int updatePortfolio(Portfolio portfolio);
	public int deletePortfolio(Integer portfolioId);
	public String getPortfolioName(Integer portfolioId);

}
