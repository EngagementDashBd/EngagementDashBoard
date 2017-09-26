package com.acc.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acc.dao.PortfolioDao;
import com.acc.dao.ProgramDao;
import com.acc.entity.Program;
@Service
public class ProgramServiceImplementation implements ProgramServiceFacade {
	@Autowired
	ProgramDao programdao;
	@Transactional
	public int addProgram(Program program,String creatorName) {
		int count = 0;
		try{
			count = programdao.addProgram(program,creatorName);
			
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
	public int deleteProgram(Integer programId) {
		int count = 0;
		try{
			count = programdao.deleteProgram(programId);
			
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
	public List<Program> getAllProjectPrograms() {
		List<Program> programs = new ArrayList<Program>();
		try{
			programs =programdao.getAllProjectPrograms();
			
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return programs;
	
	}

	@Transactional
	public Program getProgram(Integer programId) {
		Program program = new Program();
		try{
			program = programdao.getProgram(programId);
			
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return program;
	}

	@Transactional
	public String getProgramName(Integer programId) {
		String programName = null;
		try
		{
			programName = programdao.getProgramName(programId);
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return programName;
	}

	@Transactional
	public int updateProgram(Program program) {
		int count = 0;
		try{
			count = programdao.updateProgram(program);
			
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

}
