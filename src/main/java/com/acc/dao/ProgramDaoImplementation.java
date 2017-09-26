package com.acc.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.acc.entity.Program;
@Repository
public class ProgramDaoImplementation extends AbstractDao implements ProgramDao{
	public int addProgram(Program program,String creatorName) throws ClassNotFoundException, SQLException {
		Session session=getSession();
		Program newProgram = new Program();
		newProgram.setProgramName(program.getProgramName());
		newProgram.setDescription(program.getDescription());
		newProgram.setPortfolioId(program.getPortfolioId());
		newProgram.setCreatedBy(creatorName);
		session.save(newProgram);
		return 1;
	}
	public List<Program> getAllProjectPrograms()throws ClassNotFoundException, SQLException {
		Session session=getSession();
		List<Program> programs = new ArrayList<Program>();
		  Query query=session.createQuery("select e from Program e");
		  programs =  query.list();
		return programs;
	}

	public int deleteProgram(Integer programId) throws ClassNotFoundException, SQLException {
		int count = 0;
		Session session=getSession();
		Query query=session.createQuery("delete from Program p where p.programId=:programId");
		query.setParameter("programId",programId);
		count = query.executeUpdate();
		return count;
	}

	public Program getProgram(Integer programId) throws ClassNotFoundException, SQLException {
		List<Program> Programlist = new ArrayList<Program>();
		Session session=getSession();
		Query query=session.createQuery("select e from  Program e where e.programId=:programId ");
		query.setParameter("programId",programId);
		Programlist = query.list();
		Program program = new Program();
		for(Program programs:Programlist)
		{
			program = programs;
		}
		
		
		return program;
	}
	public String getProgramName(Integer programId) throws ClassNotFoundException, SQLException {
		String programName="";
		List<Program> programs = new ArrayList<Program>();
		Session session=getSession();
		Query query=session.createQuery("select e from  Program e where e.programId=:programId ");
		query.setParameter("programId",programId);
		programs = query.list();
		for(Program program:programs)
		{
			programName = program.getProgramName();
			
		}
		
		
		return programName;
	}

	public int updateProgram(Program program) throws ClassNotFoundException, SQLException {
		Session session=getSession();
		int count = 0;
		Query query=session.createQuery("update Program r set  r.programName=:programName, r.description=:programDescription, r.portfolioId=:portfolioId where r.programId=:programId");
		query.setParameter("programName", program.getProgramName());
		query.setParameter("programDescription", program.getDescription());
		query.setParameter("portfolioId", program.getPortfolioId());
		query.setParameter("programId", program.getProgramId());

		count = query.executeUpdate();
		return count;
	}
}
