package com.acc.service;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.acc.dao.PortfolioDao;
import com.acc.entity.Portfolio;

@Service
public class PortfolioServiceImplementation implements PortfolioServiceFacade{
	@Autowired
	PortfolioDao portfoliodao;
	@Transactional(readOnly=true)
	public int addPortfolioDetails(Portfolio portfolio, String creatorName) {
		int count = 0;
		try
		{
			count = portfoliodao.addPortfolioDetails(portfolio,creatorName);
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return count;
	}

	@Transactional(readOnly=true)
	public ArrayList<Portfolio> getPortfolioDetails()
	{
		ArrayList<Portfolio> portfolios = new ArrayList<Portfolio>();
		try{
			portfolios = portfoliodao.getPortfolioDetails();
			
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return portfolios;
	}

	@Transactional(readOnly=true)
	public Portfolio getPortfolio(Integer portfolioId) {
		Portfolio portfolio = new Portfolio();
		try{
			portfolio = portfoliodao.getPortfolio(portfolioId);
			
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return portfolio;
	}
	@Transactional
	public int updatePortfolio(Portfolio portfolio) {
		int count = 0;
		try{
			count = portfoliodao.updatePortfolio(portfolio);
			
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return count;
	}
	@Transactional
	public int deletePortfolio(Integer portfolioId) {
		int count = 0;
		try{
			count = portfoliodao.deletePortfolio(portfolioId);
			
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return count;
		}

	@Transactional(readOnly=true)
	public String getPortfolioName(Integer portfolioId) {
		String portfolioName = null;
		try
		{
			portfolioName = portfoliodao.getPortfolioName(portfolioId);
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return portfolioName;
	}

}
