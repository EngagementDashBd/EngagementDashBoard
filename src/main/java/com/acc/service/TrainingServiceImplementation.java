package com.acc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acc.dao.TrainingDetailsDAO;
import com.acc.entity.TrainingDetails;

@Service
public class TrainingServiceImplementation implements TrainingServiceFacade{

	@Autowired
	TrainingDetailsDAO trainingdao;
	
	@Transactional(readOnly=true)
	public int getSlideNumber(String enterpriseId, Long topicId) {
		// TODO Auto-generated method stub
		int count = 0;
		try{
			count = trainingdao.getSlideNumber(enterpriseId, topicId);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return count;
	}

	@Transactional
	public int updateSlideNumber(String enterpriseId, Long topicId, int slideNumber){
		int count = 0;
		try{
			count = trainingdao.updateSlideNumber(enterpriseId, topicId, slideNumber);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return count;
	}
	

	@Transactional
	public int completeStatus(String enterpriseId, Long topicId) {
		// TODO Auto-generated method stub
		int count = 0;
		try{
			count = trainingdao.completeStatus(enterpriseId, topicId);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return count;
	}
	
	@Transactional
	public List<TrainingDetails> fetchDetails(String enterpriseId) {
		List<TrainingDetails> fetchDetail = trainingdao.fetchDetails(enterpriseId);
		return fetchDetail;
	}
	
}
