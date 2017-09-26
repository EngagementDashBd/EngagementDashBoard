package com.acc.service;

import java.util.List;

import com.acc.entity.Program;

public interface ProgramServiceFacade {
	public int addProgram(Program program,String creatorName);
	public List<Program> getAllProjectPrograms();
	public int deleteProgram(Integer programId);
	public Program getProgram(Integer programId);
	public String getProgramName(Integer programId);
	public int updateProgram(Program program);
}
