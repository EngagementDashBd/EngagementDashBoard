package com.acc.dao;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.acc.entity.Authority;
import com.acc.entity.IdeaDevelopers;
import com.acc.entity.IdeaProgress;
import com.acc.entity.IdeaReviewer;
import com.acc.entity.IdeaStatus;
import com.acc.entity.Innovation;
import com.acc.entity.ResourceMaster;

@Repository
public class InnovationDaoImpl extends AbstractDao implements InnovationDaoFacade{

	public Integer addIdea(Innovation idea) {
		int count = 0;
		Integer id = null;
		Session session = getSession();
		Innovation innovation = new Innovation();
		innovation.setEnterpriseId(idea.getEnterpriseId());
		innovation.setIdeaTitle(idea.getIdeaTitle());
		innovation.setIdeaDescription(idea.getIdeaDescription());
		innovation.setCategory(idea.getCategory());
		innovation.setSubCategory(idea.getSubCategory());
		innovation.setiCurrentEffort( idea.getiCurrentEffort());
		innovation.setiPlannedEffort(idea.getiPlannedEffort());
		innovation.setiEffortSaving(idea.getiEffortSaving());
		innovation.setiBusinessSaving(idea.getiBusinessSaving());
		innovation.setApproverAndSuperVisors(idea.getApproverAndSuperVisors());
		session.save(innovation);
		Query query = session.createQuery("select e from Innovation e where e.IdeaTitle=:ideaTitle");
		query.setParameter("ideaTitle",idea.getIdeaTitle());
		List<Innovation> ideaList = query.list();
		for(Innovation i : ideaList)
		{
			id = i.getIdeaId();
		}
		String[] reviewers = idea.getApproverAndSuperVisors().split(",");
		for(int i=0;i<reviewers.length;i++)
		{
			Long employeeId = null;
			IdeaReviewer ideaReviewer = new IdeaReviewer();
			ideaReviewer.setIdeaId(id);
			ideaReviewer.setReviewer(reviewers[i]);
			session.save(ideaReviewer);
			Query query1 = session.createQuery("select e from ResourceMaster e where e.enterpriseId=:eid");
			query1.setParameter("eid",reviewers[i]);
			List<ResourceMaster> empList = query1.list();
			for(ResourceMaster emp : empList)
			{
				employeeId = emp.getEmployeeId();
			}
			Query query2 = session.createQuery("select e from Authority e where e.employeeId=:eid and e.authority=:authority");
			query2.setParameter("eid",employeeId.toString());
			query2.setParameter("authority","IDEA_REVIEW");
			List<Authority> auth = query2.list();
			if(auth.isEmpty())
			{
			Authority authority = new Authority();
			authority.setEmployeeId(employeeId.toString());
			authority.setAuthority("IDEA_REVIEW");
			session.save(authority);
			}
		}
		count = 1;
		return count;
	}

	public List<Innovation> allIdeas() {
		List<Innovation> ideas = new ArrayList<Innovation>();
		Session session = getSession();
		Query query = session.createQuery("select e from Innovation e");
		ideas = query.list();
		for(Innovation idea:ideas )
		{
			System.out.println("asdfsd"+idea.getiBusinessSaving()+
			idea.getiCurrentEffort() +"idea title"+ idea.getIdeaTitle()); 
			
			
		}
		return ideas;
	}

	public String getIdeaStatus(Integer ideaId) {
		List<IdeaReviewer> ideaStatus = new ArrayList<IdeaReviewer>();
		String status = null;
		Session session = getSession();
		Query query = session.createQuery("select e from IdeaReviewer e where e.ideaId=:ideaId");
		query.setParameter("ideaId", ideaId);
		ideaStatus = query.list();
		int x = 0;
	
		for(IdeaReviewer stat : ideaStatus)
		{
			if(stat.getStatus()== null)
			{
				x = 1;
				break;
			}
			if(stat.getStatus().equalsIgnoreCase("under development") || stat.getStatus().equalsIgnoreCase("Completed") || stat.getStatus().equalsIgnoreCase("rejected") || stat.getStatus().equalsIgnoreCase("on hold"))
				return stat.getStatus();
		
			
		}
		if(x == 0)
		 status = "Approved";
		else
			status = "Approval Pending";
			
		return status;
	}

	public Integer setStatusOfIdea(Integer ideaId, String comments, String status, String reviewer) {
		List<IdeaStatus> ideaStatusList = new ArrayList<IdeaStatus>();
		Session session = getSession();
		int count = 0;
		/*Query query = session.createQuery("select e from IdeaStatus e where e.ideaId=:ideaId and e.enterpriseId=:enterpriseId");
		query.setParameter("ideaId", ideaId);
		query.setParameter("enterpriseId", reviewer);
		ideaStatusList = query.list();
		if(ideaStatusList.isEmpty())
		{*/
			IdeaStatus ideaStatus = new IdeaStatus();
			ideaStatus.setIdeaId(ideaId);
			ideaStatus.setEnterpriseId(reviewer);
			ideaStatus.setComments(comments);
			ideaStatus.setStatus(status);
			session.save(ideaStatus);
			count = 1;
		/*}
		else
		{
			Query query1 = session.createQuery("update IdeaStatus e set e.status=:status,e.comments=:comments,e.enterpriseId=:enterpriseId where e.ideaId=:ideaId");
			query1.setParameter("status",status);
			query1.setParameter("comments",comments);
			query1.setParameter("enterpriseId",reviewer);
			query1.setParameter("ideaId", ideaId);
			count = query1.executeUpdate();
		}*/
		Query query2 = session.createQuery("update IdeaReviewer e set e.status=:status where e.reviewer=:reviewer");
		query2.setParameter("status",status);
		query2.setParameter("reviewer",reviewer);
		count = query2.executeUpdate();
		return count;
	}

	public List<Innovation> myIdeas(String enterpriseId) {
		List<Innovation> ideas = new ArrayList<Innovation>();
		Session session = getSession();
		Query query = session.createQuery("select e from Innovation e where e.enterpriseId=:enterpriseId");
		query.setParameter("enterpriseId", enterpriseId);
		ideas = query.list();
		return ideas;
	}

	public int addDevelopers(Integer ideaId, String developer) {
		int count = 0;
		Session session = getSession();
		IdeaDevelopers developers = new IdeaDevelopers();
		developers.setIdeaId(ideaId);
		developers.setEnterpriseId(developer);
		session.save(developers);
		count = 1;
		return count;
	}

	public List<Innovation> getIdeasUnderDevelopment(String developer) {
		List<Innovation> ideas= new ArrayList<Innovation>();
		Session session = getSession();
		Query query = session.createQuery("select e from Innovation e where e.ideaId in(select x.ideaId from IdeaDevelopers x where x.enterpriseId=:enterpriseId)");
		query.setParameter("enterpriseId", developer);
		ideas = query.list();
		return ideas;
	}

	public int addNote(IdeaProgress ideaProgress, String developer) {
		int count = 0;
		Session session = getSession();
		Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();
		IdeaProgress progress = new IdeaProgress();
		progress.setIdeaId(ideaProgress.getIdeaId());
		progress.setEnterpriseId(developer);
		progress.setCreationDate(today);
		progress.setProgress(ideaProgress.getProgress());
		session.save(progress);
		count = 1;
		return count;
	}

	public List<IdeaProgress> ideaProgress(Integer ideaId) {
		List<IdeaProgress> ideasProgress = new ArrayList<IdeaProgress>();
		Session session = getSession();
		Query query = session.createQuery("select e from IdeaProgress e where e.ideaId=:ideaId");
		query.setParameter("ideaId", ideaId);
		ideasProgress = query.list();
		return ideasProgress;
	}

	public Innovation getIdeaDetailsById(Integer ideaId) {
		Session session = getSession();
		Innovation idea = new Innovation();
		Query query = session.createQuery("select e from Innovation e where e.ideaId=:ideaId");
		query.setParameter("ideaId", ideaId);
		List<Innovation> ideas = query.list();
		for(Innovation id : ideas)
			idea = id;
		return idea;
	}

	public List<IdeaDevelopers> getDevelopersByIdeaId(Integer ideaId) {
		Session session = getSession();
		Query query = session.createQuery("select e from IdeaDevelopers e where e.ideaId=:ideaId");
		query.setParameter("ideaId", ideaId);
		List<IdeaDevelopers> ideaDevelopers = query.list();
		return ideaDevelopers;
	}

	public Map<String, String> getIdeaReviewStatus(Integer ideaId) {
		Map<String,String> reviewByReviewers = new HashMap<String,String>();
		Session session = getSession();
		Query query = session.createQuery("select e from IdeaReviewer e where e.ideaId=:ideaId");
		query.setParameter("ideaId", ideaId);
		List<IdeaReviewer> reviewers = query.list();
		for(IdeaReviewer ir : reviewers)
		{
			reviewByReviewers.put(ir.getReviewer(), ir.getStatus());
		}
		return reviewByReviewers;
	}

	public List<Innovation> getIdeasByReviewers(String reviewer) {
		Session session = getSession();
		Query query = session.createQuery("select g from Innovation g  where g.ideaId in(select e.ideaId from IdeaReviewer e where e.reviewer=:reviewer)");
		query.setParameter("reviewer", reviewer);
		List<Innovation> ideasOfReviewer = query.list();		
		return ideasOfReviewer;
	}

	public Integer changeStatus(Integer ideaId) {
		Session session = getSession();
		int count = 0;
		Query query = session.createQuery("update IdeaReviewer e set e.status=:status where e.ideaId=:ideaId");
		query.setParameter("status", "Under Development");
		query.setParameter("ideaId", ideaId);
		query.executeUpdate();
		Query query1 = session.createQuery("update IdeaStatus e set e.status=:status where e.ideaId=:ideaId");
		query1.setParameter("status", "Under Development");
		query1.setParameter("ideaId", ideaId);
		query1.executeUpdate();
		count = 1;
		return count;
	}

	public Integer markCompleted(Integer ideaId) {
		int count = 0;
		Session session = getSession();
		Query query = session.createQuery("update IdeaReviewer e set e.status=:status where e.ideaId=:ideaId");
		query.setParameter("status", "Completed");
		query.setParameter("ideaId", ideaId);
		query.executeUpdate();
		Query query1 = session.createQuery("update IdeaStatus e set e.status=:status where e.ideaId=:ideaId");
		query1.setParameter("status", "Completed");
		query1.setParameter("ideaId", ideaId);
		query1.executeUpdate();
		count = 1;
		return count;
		
	}

	
}
