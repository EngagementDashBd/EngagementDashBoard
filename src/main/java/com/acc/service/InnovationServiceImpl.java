package com.acc.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acc.dao.InnovationDaoFacade;
import com.acc.entity.IdeaDevelopers;
import com.acc.entity.IdeaProgress;
import com.acc.entity.IdeaStatus;
import com.acc.entity.Innovation;
@Service
public class InnovationServiceImpl implements InnovationServiceFacade{
	@Autowired
	InnovationDaoFacade innovationDaoImpl;
	
	@Transactional
	public Integer addIdea(Innovation idea) {
		int count = 0;
		count = innovationDaoImpl.addIdea(idea);
		return count;
	}
	@Transactional(readOnly=true)
	public List<Innovation> allIdeas() {
		List<Innovation> ideas = innovationDaoImpl.allIdeas();
		return ideas;
	}
	@org.springframework.transaction.annotation.Transactional(readOnly=true)
	public String getIdeaStatus(Integer ideaId) {
		String status = innovationDaoImpl.getIdeaStatus(ideaId);
		return status;
	}
	@Transactional
	public Integer setStatusOfIdea(Integer ideaId, String comments, String status, String reviewer) {
		int count = innovationDaoImpl.setStatusOfIdea(ideaId, comments, status, reviewer);
		return count;
	}
	@org.springframework.transaction.annotation.Transactional(readOnly=true)
	public List<Innovation> myIdeas(String enterpriseId) {
		List<Innovation> ideas = innovationDaoImpl.myIdeas(enterpriseId);
		return ideas;
	}
	@Transactional
	public int addDevelopers(Integer ideaId, String developer) {
		int count = innovationDaoImpl.addDevelopers(ideaId, developer);
		return count;
	}
	@org.springframework.transaction.annotation.Transactional(readOnly=true)
	public List<Innovation> getIdeasUnderDevelopment(String developer) {
		List<Innovation> ideas = innovationDaoImpl.getIdeasUnderDevelopment(developer);
		return ideas;
	}
	@Transactional
	public int addNote(IdeaProgress ideaProgress, String developer) {
		int count = innovationDaoImpl.addNote(ideaProgress, developer);
		return count;
	}
	@org.springframework.transaction.annotation.Transactional(readOnly=true)
	public List<IdeaProgress> ideaProgress(Integer ideaId) {
		List<IdeaProgress> ideasProgress = innovationDaoImpl.ideaProgress(ideaId);
		return ideasProgress;
	}
	@org.springframework.transaction.annotation.Transactional(readOnly=true)
	public Innovation getIdeaDetailsById(Integer ideaId) {
		Innovation idea = innovationDaoImpl.getIdeaDetailsById(ideaId);
		return idea;
	}
	@org.springframework.transaction.annotation.Transactional(readOnly=true)
	public List<IdeaDevelopers> getDevelopersByIdeaId(Integer ideaId) {
		List<IdeaDevelopers> ideaDevelopers = innovationDaoImpl.getDevelopersByIdeaId(ideaId);
		return ideaDevelopers;
	}
	@Transactional
	public Map<String, String> getIdeaReviewStatus(Integer ideaId) {
		 Map<String,String> reviewByReviewers = innovationDaoImpl.getIdeaReviewStatus(ideaId);
		return reviewByReviewers;
	}
	@Transactional
	public  List<Innovation> getIdeasByReviewers(String reviewer) {
		 List<Innovation> ideasOfReviewer = innovationDaoImpl.getIdeasByReviewers(reviewer);
		return ideasOfReviewer;
	}
	@Transactional
	public Integer changeStatus(Integer ideaId) {
		Integer count = innovationDaoImpl.changeStatus(ideaId);
		return count;
	}
	@Transactional
	public Integer markCompleted(Integer ideaId) {
		Integer count = innovationDaoImpl.markCompleted(ideaId);
		return count;
	}
	
}
