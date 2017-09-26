package com.acc.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.acc.entity.TrainingDetails;
import com.acc.entity.TrainingTopics;
@SuppressWarnings("unchecked")
@Repository
public class TrainingDetailsDAOImpl extends AbstractDao implements TrainingDetailsDAO {

	public int trainingDetails(String enterpriseID) {
		// TODO Auto-generated method stub
		int flag = 1;
		try {
			//System.out.println("Inside trainingDetails function");
			Session session = getSession();
			Query query = session.createQuery("select t from TrainingTopics t where t.active=:active");
			query.setParameter("active", true);
			List<TrainingTopics> topicList = query.list();
			for (TrainingTopics trainingtopics : topicList) {

				TrainingDetails trainingDetails = new TrainingDetails();
				trainingDetails.setenterpriseId(enterpriseID);
				trainingDetails.setTopicId(trainingtopics.getTopicId());
				session.save(trainingDetails);

			}
		} catch (Exception e) {
			flag = 0;
			e.printStackTrace();
		}
		return flag;
	}

	public int getSlideNumber(String enterpriseId, Long topicId) {
		int slideNumber = 1;
		Session session = getSession();
		//System.out.println("Inside getSlideNumber function, TopicId & EnterpId"+ topicId +" "+enterpriseId);
		Query query = session
				.createQuery("select t from TrainingDetails t where t.enterpriseId=:entpId and t.topicId=:topicId");
		query.setParameter("entpId", enterpriseId);
		query.setParameter("topicId", topicId);
		List<TrainingDetails> topicList = query.list();
		for (TrainingDetails trainingDetails : topicList) {
			slideNumber = trainingDetails.getslideNumber();
		}
		return slideNumber;
	}

	public int updateSlideNumber(String enterpriseId, Long topicId, int slideNumber) {
		// TODO Auto-generated method stub
		int count = 0;
		Session session = getSession();
		slideNumber++;
		//System.out.println("Inside updateSlideNumber function, TopicId, slidenumber & EnterpId"+ topicId +" "+slideNumber+" "+enterpriseId);
		Query query = session.createQuery(
				"update TrainingDetails t set  t.slideNumber=:slideNumber where t.enterpriseId=:enterpriseId AND t.topicId=:topicId");
		query.setParameter("slideNumber", slideNumber);
		query.setParameter("enterpriseId", enterpriseId);
		query.setParameter("topicId", topicId);
		
		count = query.executeUpdate();
		return count;
	}

	public int completeStatus(String enterpriseId, Long topicId){
		int count = 0;
		Session session = getSession();
		Query query = session.createQuery(
				"update TrainingDetails t set t.slideNumber=:slideNumber, t.completionStatus=:completionStatus, t.completionDate=:completionDate where t.enterpriseId=:enterpriseId AND t.topicId=:topicId");
		query.setParameter("slideNumber", 1);
		query.setParameter("completionStatus", true);
		query.setParameter("completionDate", new Date());
		query.setParameter("enterpriseId", enterpriseId);
		query.setParameter("topicId", topicId);
		
		count = query.executeUpdate();
		return count;
	}

	public List<TrainingDetails> fetchDetails(String enterpriseId) {
		Session session = getSession();
		Query query = session.createQuery("select e from TrainingDetails e where e.enterpriseId=:enterpriseId");
		query.setParameter("enterpriseId", enterpriseId);
		List<TrainingDetails> learnData = query.list();
		return learnData;
	}
	
}
