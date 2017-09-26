package com.acc.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.acc.entity.Portfolio;

public interface PortfolioDao {
	public int addPortfolioDetails(Portfolio portfolio, String creatorName)throws ClassNotFoundException, SQLException;
	public ArrayList<Portfolio> getPortfolioDetails()throws ClassNotFoundException,SQLException;
	public Portfolio getPortfolio(Integer portfolioId)throws ClassNotFoundException,SQLException;
	public int updatePortfolio(Portfolio portfolio)throws ClassNotFoundException,SQLException;
	public int deletePortfolio(Integer portfolioId)throws ClassNotFoundException,SQLException;
	public String getPortfolioName(Integer portfolioId)throws ClassNotFoundException, SQLException;

}
