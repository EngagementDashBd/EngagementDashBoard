package com.acc.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.acc.entity.Technology;
@Repository
public class TechnologyDaoImplementation extends AbstractDao implements TechnologyDao{

	public List<Technology> getAllTechnology() throws ClassNotFoundException, SQLException {
		List<Technology> technologylist= new ArrayList<Technology>();
		Session session=getSession();
		Query query=session.createQuery("select e from  Technology e ");
		technologylist = query.list();
		return technologylist;
	}
	public int deleteTechnology(Integer technologyId) throws ClassNotFoundException, SQLException {
		int count = 0;
		Session session=getSession();
		Query query=session.createQuery("delete from Technology t where t.technologyId=:technologyId");
		query.setParameter("technologyId",technologyId);
		count = query.executeUpdate();
		return count;
	}
	public int addTechnology(Technology technology) throws ClassNotFoundException, SQLException {
		Session session=getSession();
		int count = 0;
		Query query = session.createQuery("select e from Technology e where e.technologyName=:technologyName");
		query.setParameter("technologyName", technology.getTechnologyName());
		List<Technology> technologyList=query.list();
		if(technologyList.isEmpty())
		{
			Technology newtechnology = new Technology();
			newtechnology.setTechnologyName(technology.getTechnologyName());
			newtechnology.setTechnologyDescription(technology.getTechnologyDescription());
			session.save(newtechnology);
			count = 1;
		}
		return count;
	}
	public int deleteProgram(Integer programId) throws ClassNotFoundException, SQLException {
		int count = 0;
		Session session=getSession();
		Query query=session.createQuery("delete from Program p where p.programId=:programId");
		query.setParameter("programId",programId);
		count = query.executeUpdate();
		return count;
	}
	public Integer getTechIdByName(String technologyName) {
		Integer technologyId = 0;
		Session session=getSession();
		Query query=session.createQuery("select e from Technology e where e.technologyName=:technologyName");
		query.setParameter("technologyName", technologyName);
		List<Technology> technologyList=query.list();
		for(Technology technology : technologyList)
		{
			technologyId = technology.getTechnologyId();
		}
		return technologyId;
	}
}
