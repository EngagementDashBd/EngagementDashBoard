package com.acc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.acc.entity.IdeaDevelopers;
import com.acc.entity.IdeaProgress;
import com.acc.entity.IdeaStatus;
import com.acc.entity.Innovation;
import com.acc.entity.ResourceMaster;
import com.acc.mailer.Mailer;
import com.acc.service.EmployeeServiceFacade;
import com.acc.service.InnovationServiceFacade;
import com.google.gson.Gson;


@Controller
public class InnovationController {
	@Autowired
	InnovationServiceFacade innovationServiceImpl;
	@Autowired
	EmployeeServiceFacade employeeServiceImpl;
	
	String addIdeaMail = " has added a new idea. Kindly take necessary actions.";
	
	@RequestMapping("/addIdea.htm") 
	public ModelAndView addIdea(@ModelAttribute Innovation idea,HttpServletRequest request)
	{
		List<ResourceMaster> superVisorName = 	employeeServiceImpl.allSupervisorDetails();
		ModelAndView modelAndView = new ModelAndView();
		HttpSession session = request.getSession();
		ResourceMaster employee = (ResourceMaster) session.getAttribute("resource");
		int count = innovationServiceImpl.addIdea(idea);
		if(count == 1)
		{
			String body = employee.getEnterpriseId() + addIdeaMail;
			String subject = "New Innovation Added";
			List<String> recipients = new ArrayList<String>();
			List<String> cCopy = new ArrayList<String>();
			recipients.add(employee.getSupervisorEnterpriseId());
			cCopy.add(employee.getEnterpriseId());
			Mailer.triggerMail(body,subject,recipients,cCopy);
			modelAndView.addObject("ideaCode", "success");
		}
		else
			modelAndView.addObject("ideaCode", "failure");
		modelAndView.addObject("superVisorName", superVisorName);
		modelAndView.setViewName("myProfile");
		return modelAndView;
	}
	
	@RequestMapping("/getAllIdeas.htm")
	public ModelAndView allIdeas(HttpServletRequest request, HttpServletResponse response)
	{
		System.out.println("starting gert");
		ModelAndView modelAndView = new ModelAndView();
		List<Innovation> ideas =  innovationServiceImpl.allIdeas();
		HttpSession session=request.getSession();
		session.setAttribute("idea", ideas);
		
		ResourceMaster employee = (ResourceMaster) session.getAttribute("resource");
		
		Map<Integer,String> ideaAndTeam = new HashMap<Integer,String>();
		Map<Integer,String> ideaAndStatus = new HashMap<Integer,String>();
		for(Innovation idea : ideas)
		{
			String team = "";
			ResourceMaster resource = employeeServiceImpl.searchEmployee(idea.getEnterpriseId());
			if(resource.getProjectId() != null)
				 team = resource.getProject().getProgram().getProgramName();
			ideaAndTeam.put(idea.getIdeaId(),team );
			String status = innovationServiceImpl.getIdeaStatus(idea.getIdeaId());
			ideaAndStatus.put(idea.getIdeaId(), status);
		}
	
		modelAndView.addObject("ideas", ideas);
		modelAndView.addObject("ideaAndTeam", ideaAndTeam);
		modelAndView.addObject("ideaAndStatus", ideaAndStatus);
		
		modelAndView.setViewName("allIdeas");
		
		return modelAndView;
	}

	@RequestMapping(value= "/SeachSuper.htm",  method = RequestMethod.POST) 
	public  @ResponseBody void performSearch(@RequestParam("SUPID") String chars, HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		System.out.println("search");
		int careerLevel = 0;
		HttpSession session = request.getSession();
		ResourceMaster resourcemaster = (ResourceMaster) session.getAttribute("resource");
		/*if(request.getParameter("level") == null)
			careerLevel = 0;
		else if(resourcemaster.getEnterpriseId().equalsIgnoreCase("admin"))
			careerLevel = Integer.valueOf(request.getParameter("level"));
		else
			careerLevel = resourcemaster.getLevel();*/
		PrintWriter out = response.getWriter();
		ArrayList<String> enterpriseIds = new ArrayList<String>();
		enterpriseIds=    (ArrayList<String>) employeeServiceImpl.checkSupervisor(chars,careerLevel);
		Gson gson = new Gson();
		response.setContentType("application/json");
		out.println(gson.toJson(enterpriseIds));
	}
	
	
	@RequestMapping("/reviewIdea.htm")
	public ModelAndView reviewIdea(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView modelAndView = new ModelAndView();
		HttpSession session=request.getSession();
		ResourceMaster employee = (ResourceMaster) session.getAttribute("resource");
		Integer ideaId = Integer.valueOf(request.getParameter("ideaId"));
		String comments = request.getParameter("comments");
		String status = request.getParameter("approveOrReject");
		if(status.equalsIgnoreCase("approve"))
		{
/*			String body = "has been approved ";
			String subject = "Idea Review Result";
			List<String> recipients = new ArrayList<String>();
			List<String> cCopy = new ArrayList<String>();
			recipients.add(employee.getEnterpriseId());
			cCopy.add(employee.getSupervisorEnterpriseId());
			Mailer.triggerMail(body,subject,recipients,cCopy);*/
			status = "Approved";
		}
		else if(status.equalsIgnoreCase("reject"))
		{
			status = "Rejected";
		}
		else
		{
			status = "On Hold";
		}
		String reviewer = employee.getEnterpriseId();
		int count = innovationServiceImpl.setStatusOfIdea(ideaId, comments, status, reviewer);
		List<Innovation> ideas =  innovationServiceImpl.allIdeas();
		
		Map<Integer,String> ideaAndTeam = new HashMap<Integer,String>();
		Map<Integer,String> ideaAndStatus = new HashMap<Integer,String>();
		for(Innovation idea : ideas)
		{
			String team = "";
			ResourceMaster resource = employeeServiceImpl.searchEmployee(idea.getEnterpriseId());
			if(resource.getProjectId() != null)
				 team = resource.getProject().getProgram().getProgramName();
			ideaAndTeam.put(idea.getIdeaId(),team );
			String ideaStatus = innovationServiceImpl.getIdeaStatus(idea.getIdeaId());
			ideaAndStatus.put(idea.getIdeaId(), ideaStatus);
		}
		modelAndView.addObject("ideas", ideas);
		modelAndView.addObject("ideaAndTeam", ideaAndTeam);
		modelAndView.addObject("ideaAndStatus", ideaAndStatus);
		if(count == 1)
			modelAndView.addObject("reviewCode","success");
		else
			modelAndView.addObject("reviewCode","failure");
		modelAndView.setViewName("allIdeas");
		return modelAndView;
	}
	@RequestMapping("/getMyIdeas.htm")
	public ModelAndView myIdeas(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView modelAndView = new ModelAndView();
		HttpSession session=request.getSession();
		ResourceMaster employee = (ResourceMaster) session.getAttribute("resource");
		List<Innovation> ideas =  innovationServiceImpl.myIdeas(employee.getEnterpriseId());		
		Map<Integer,String> ideaAndTeam = new HashMap<Integer,String>();
		Map<Integer,String> ideaAndStatus = new HashMap<Integer,String>();
		for(Innovation idea : ideas)
		{
			String team = "";
			ResourceMaster resource = employeeServiceImpl.searchEmployee(idea.getEnterpriseId());
			if(resource.getProjectId() != null)
				 team = resource.getProject().getProgram().getProgramName();
			ideaAndTeam.put(idea.getIdeaId(),team );
			String status = innovationServiceImpl.getIdeaStatus(idea.getIdeaId());
			ideaAndStatus.put(idea.getIdeaId(), status);
		}
		modelAndView.addObject("page", "myidea");
		modelAndView.addObject("ideas", ideas);
		modelAndView.addObject("ideaAndTeam", ideaAndTeam);
		modelAndView.addObject("ideaAndStatus", ideaAndStatus);
		modelAndView.setViewName("allIdeas");
		return modelAndView;
	}
	@RequestMapping("/addDeveloper.htm")
	public ModelAndView addDevelopers(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView modelAndView = new ModelAndView();
		Integer ideaId = Integer.valueOf(request.getParameter("ideaId"));
		Integer count = Integer.valueOf(request.getParameter("index"));
		int counter = 0;
		for(int i = 0 ; i < count ; i++)
		{
			String id = "developer" + i;
			String developer = request.getParameter(id);
			if(developer.length() != 0)
			{				
				counter = innovationServiceImpl.addDevelopers(ideaId, developer);
			}
		}
		modelAndView = myIdeas(request,response);
		if(counter > 0)
			modelAndView.addObject("devcode", "success");
		else
			modelAndView.addObject("devcode", "failure");		
		return modelAndView;
	}
	@RequestMapping("/getMyIdeasUnderDevelopment.htm")
	public ModelAndView getMyIdeasUnderDevelopment(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView modelAndView = new ModelAndView();
		HttpSession session=request.getSession();
		ResourceMaster employee = (ResourceMaster) session.getAttribute("resource");
		String developerId = employee.getEnterpriseId();
		List<Innovation> ideas = innovationServiceImpl.getIdeasUnderDevelopment(developerId);
		modelAndView.addObject("ideasunderdevelopment", ideas);
		modelAndView.setViewName("ideasUnderDevelopment");
		return modelAndView;
	}
	@RequestMapping(value = "/addNote.htm" ,method=RequestMethod.POST)
	public ModelAndView addProgressNote(@ModelAttribute IdeaProgress ideaProgress, HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView modelAndView = new ModelAndView();
		HttpSession session=request.getSession();
		ResourceMaster employee = (ResourceMaster) session.getAttribute("resource");
		String developerId = employee.getEnterpriseId();
		List<Innovation> ideas = innovationServiceImpl.getIdeasUnderDevelopment(developerId);
		modelAndView.addObject("ideasunderdevelopment", ideas);
		int count = innovationServiceImpl.addNote(ideaProgress, developerId);
		int count1 = innovationServiceImpl.changeStatus(ideaProgress.getIdeaId());
		if(count == 1)
			modelAndView.addObject("code", "success");
		else
			modelAndView.addObject("code", "failure");	
		modelAndView.addObject("ideaProgress",null);
		modelAndView.setViewName("ideasUnderDevelopment");
		return modelAndView;
	}
	@RequestMapping("/getProgress.htm")
	public ModelAndView myProgress(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView modelAndView = new ModelAndView();
		Integer ideaId = Integer.valueOf(request.getParameter("ideaId"));
		HttpSession session=request.getSession();
		String page = request.getParameter("page");		
		ResourceMaster employee = (ResourceMaster) session.getAttribute("resource");
		String developerId = employee.getEnterpriseId();
		List<IdeaProgress> ideasProgress = innovationServiceImpl.ideaProgress(ideaId);
		modelAndView.addObject("ideaProgress", ideasProgress);
		modelAndView.addObject("progress", "yes");
		if(page == null)
		{
			List<Innovation> ideas = innovationServiceImpl.getIdeasUnderDevelopment(developerId);
			modelAndView.addObject("ideasunderdevelopment", ideas);
			modelAndView.setViewName("ideasUnderDevelopment");
		}
		else if(page.equalsIgnoreCase("myIdea"))
		{
			modelAndView = myIdeas(request,response);			
			modelAndView.addObject("ideaId", ideaId);
			modelAndView.addObject("ideaProgress", ideasProgress);
			modelAndView.addObject("progress", "yes");
		}
		
	
		return modelAndView;
	}
	@RequestMapping("/getIdeaDetails.htm")
	public ModelAndView getIdeaDetail(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView modelAndView = new ModelAndView();
		Integer ideaId = Integer.valueOf(request.getParameter("ideaId"));
		List<IdeaDevelopers> ideaDevelopers = innovationServiceImpl.getDevelopersByIdeaId(ideaId);
		List<ResourceMaster> developerDetails = new ArrayList<ResourceMaster>();
		for(IdeaDevelopers developers : ideaDevelopers)
		{
			ResourceMaster developer = employeeServiceImpl.searchEmployee(developers.getEnterpriseId());
			developerDetails.add(developer);
			
		}
		Innovation idea = innovationServiceImpl.getIdeaDetailsById(ideaId);
		String ideaStatus = innovationServiceImpl.getIdeaStatus(ideaId);
		modelAndView.addObject("idea", idea);
		modelAndView.addObject("developerDetails",developerDetails);
		modelAndView.addObject("ideaStatus",ideaStatus);
		modelAndView.setViewName("displayIdeaDetails");
		return modelAndView;
		
	}
	@RequestMapping("/getIdeasByReviewers.htm")
	public ModelAndView getIdeasByReviewers(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView modelAndView = new ModelAndView();
		HttpSession session=request.getSession();
		ResourceMaster employee = (ResourceMaster) session.getAttribute("resource");
		String developerId = employee.getEnterpriseId();
		List<Innovation> ideas = innovationServiceImpl.getIdeasByReviewers(developerId);
		Map<Integer,String> ideaAndTeam = new HashMap<Integer,String>();
		Map<Integer,String> ideaAndStatus = new HashMap<Integer,String>();
		for(Innovation idea : ideas)
		{
			String team = "";
			ResourceMaster resource = employeeServiceImpl.searchEmployee(idea.getEnterpriseId());
			if(resource.getProjectId() != null)
				 team = resource.getProject().getProgram().getProgramName();
			ideaAndTeam.put(idea.getIdeaId(),team );
			String status = innovationServiceImpl.getIdeaStatus(idea.getIdeaId());
			ideaAndStatus.put(idea.getIdeaId(), status);
		}
		modelAndView.addObject("ideaAndTeam", ideaAndTeam);
		modelAndView.addObject("ideaAndStatus", ideaAndStatus);
		modelAndView.addObject("ideas", ideas);
		modelAndView.setViewName("reviewIdeas");
		return modelAndView;
	}
	@RequestMapping("/markCompleted.htm")
	public ModelAndView markCompleted(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView modelAndView = new ModelAndView();
		Integer ideaId = Integer.valueOf(request.getParameter("ideaId"));
		int count = innovationServiceImpl.markCompleted(ideaId);
		modelAndView = myIdeas(request, response);
		return modelAndView;
	}
}
