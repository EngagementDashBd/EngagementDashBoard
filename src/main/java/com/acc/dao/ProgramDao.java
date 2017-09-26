package com.acc.dao;

import java.sql.SQLException;
import java.util.List;

import com.acc.entity.Program;

public interface ProgramDao {
	public int addProgram(Program program,String creatorName)throws ClassNotFoundException,SQLException;
	public List<Program> getAllProjectPrograms()throws ClassNotFoundException,SQLException;
	public int deleteProgram(Integer programId)throws ClassNotFoundException,SQLException;
	public Program getProgram(Integer programId)throws ClassNotFoundException,SQLException;
	public String getProgramName(Integer programId)throws ClassNotFoundException, SQLException;
	public int updateProgram(Program program)throws ClassNotFoundException, SQLException;

}
