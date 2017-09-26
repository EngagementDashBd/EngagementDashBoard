package com.acc.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.acc.entity.Portfolio;
@Repository
public class PortfolioDaoImplementation extends AbstractDao implements PortfolioDao {

	public int addPortfolioDetails(Portfolio portfolio,String creatorName) throws ClassNotFoundException, SQLException {
		int count = 0;
		Session session = getSession();
		String portfolioName = portfolio.getPortfolioName();
		Query query=session.createQuery("select e from  Portfolio e where e.portfolioName=:portfolioName");
		query.setParameter("portfolioName", portfolioName);
		List<Portfolio> portfolioList = new ArrayList<Portfolio>();
		portfolioList = query.list();
		if(portfolioList.isEmpty())
		{
			Portfolio newPortfolio = new Portfolio();
			newPortfolio.setPortfolioName(portfolio.getPortfolioName());
			newPortfolio.setPortfolioDescription(portfolio.getPortfolioDescription());
		
			newPortfolio.setCreatedBy(creatorName);
			session.save(newPortfolio);
			count=1;
		}
		
		return count;
	}
	public ArrayList<Portfolio> getPortfolioDetails() throws ClassNotFoundException, SQLException {
		Session session=getSession();
		ArrayList<Portfolio> portfolios = new ArrayList<Portfolio>();
		  Query query=session.createQuery("select e from Portfolio e");
		  portfolios = (ArrayList<Portfolio>) query.list();
		return portfolios;
	}

	public Portfolio getPortfolio(Integer portfolioId) throws ClassNotFoundException, SQLException {
		List<Portfolio> portfoliolist = new ArrayList<Portfolio>();
		Session session=getSession();
		Query query=session.createQuery("select e from  Portfolio e where e.portfolioId=:portfolioId ");
		query.setParameter("portfolioId",portfolioId);
		portfoliolist = query.list();
		Portfolio portfolio = new Portfolio();
		for(Portfolio portfolios:portfoliolist)
		{
			portfolio = portfolios;
		}
		
		
		return portfolio;
	}

	public int updatePortfolio(Portfolio portfolio) throws ClassNotFoundException, SQLException {
		Session session=getSession();
		int count = 0;
		Query query=session.createQuery("update Portfolio r set  r.portfolioName=:portfolioName, r.portfolioDescription=:portfolioDescription where r.portfolioId=:portfolioId");
		query.setParameter("portfolioName", portfolio.getPortfolioName());
		query.setParameter("portfolioDescription", portfolio.getPortfolioDescription());
		query.setParameter("portfolioId", portfolio.getPortfolioId());
		count = query.executeUpdate();
		return count;
	}
	public int deletePortfolio(Integer portfolioId) throws ClassNotFoundException, SQLException {
		int count = 0;
		Session session=getSession();
		Query query=session.createQuery("delete from Portfolio p where p.portfolioId=:portfolioId");
		query.setParameter("portfolioId",portfolioId);
		count = query.executeUpdate();
		return count;
	}
	public String getPortfolioName(Integer portfolioId) throws ClassNotFoundException, SQLException {
		String portfolioName="";
		List<Portfolio> portfolios = new ArrayList<Portfolio>();
		Session session=getSession();
		Query query=session.createQuery("select e from  Portfolio e where e.portfolioId=:portfolioId ");
		query.setParameter("portfolioId",portfolioId);
		portfolios = query.list();
		for(Portfolio portfolio:portfolios)
		{
			portfolioName = portfolio.getPortfolioName();
			
		}
		
		
		return portfolioName;
	}

}
