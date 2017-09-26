package com.acc.service;

import java.util.List;

import com.acc.entity.TrainingDetails;

public interface TrainingServiceFacade {
	
	public int getSlideNumber(String enterpriseId ,Long topicId);
	public int updateSlideNumber(String enterpriseId, Long topicId, int slideNumber);
	public int completeStatus(String enterpriseId, Long topicId);
	public List<TrainingDetails> fetchDetails(String enterpriseId);
}
