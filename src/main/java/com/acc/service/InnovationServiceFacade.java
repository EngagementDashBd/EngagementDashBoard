package com.acc.service;

import java.util.List;
import java.util.Map;

import com.acc.entity.IdeaDevelopers;
import com.acc.entity.IdeaProgress;
import com.acc.entity.IdeaStatus;
import com.acc.entity.Innovation;

public interface InnovationServiceFacade {
	public Integer addIdea(Innovation idea);
	public List<Innovation> allIdeas();
	public List<Innovation> myIdeas(String enterpriseId);
	public String getIdeaStatus(Integer ideaId);
	public Integer setStatusOfIdea(Integer ideaId, String comments, String status , String reviewer);
	public int addDevelopers(Integer ideaId, String developer);
	public List<Innovation> getIdeasUnderDevelopment(String developer);
	public int addNote(IdeaProgress ideaProgress, String developer);
	public List<IdeaProgress> ideaProgress(Integer ideaId);
	public Innovation getIdeaDetailsById(Integer ideaId);
	public List<IdeaDevelopers> getDevelopersByIdeaId(Integer ideaId);
	public Map<String,String> getIdeaReviewStatus(Integer ideaId);
	public  List<Innovation> getIdeasByReviewers(String reviewer);
	public Integer changeStatus(Integer ideaId);
	public Integer markCompleted(Integer ideaId);
	}
