package com.acc.dao;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.acc.entity.Project;
import com.acc.entity.ResourceMaster;
import com.acc.entity.Template;
@Repository
public class TemplateDaoImpl extends AbstractDao implements TemplateDaoFacade {

	public int uploadFile(Template template) {
		Session session = getSession();

		Template uploadTemplate = new Template();
		uploadTemplate.setTemplateName(template.getTemplateName());
		uploadTemplate.setTemplateFile(template.getTemplateFile());
		session.save(uploadTemplate);
			return 1;
		}
	

	public byte[] downloadTemplate(String templateName) {
		Session session = getSession();
		Query query = session.createQuery("select e from Template e where e.templateName=:templateName ");
		query.setParameter("templateName", templateName);
		List<Template> templateList = query.list();
		byte[] fileContent = null;
		for (Template template : templateList) {
			fileContent = template.getTemplateFile();
		}
		return fileContent;
	}
	
	
	
	public List<Template> allTemplateDetails() {
		
		Session session=getSession();
		Query query=session.createQuery("select e from Template e");
		
		List<Template> tempList=query.list();
		
		return tempList;
	}


	public int deleteTemplate(Integer id) 
	{
		Session session = getSession();
		int count = 0;
		Query query = session.createQuery("delete from Template p where p.id=:id");
		query.setParameter("id", id);

		count = query.executeUpdate();
		return count;
	}
	public int UpdateTemplateDetails(Template template)  {
		Session session = getSession();
		int count = 0;
		Query query = session.createQuery(
				"update Template p set  p.templateName=:templateName, p.templateFile=:templateFile where p.id=:id");
		query.setParameter("templateName", template.getTemplateName());
		query.setParameter("templateFile", template.getTemplateFile());
		

		query.setParameter("id", template.getId());
		count = query.executeUpdate();
		return count;
	}


	public Template fetchDetails(Integer id) {
		Session session=getSession();
		Template template = new Template();
		Query query=session.createQuery("select e from Template e where e.id=:id");
		query.setParameter("id", id);		
		List<Template> tempList=query.list();
		for(Template t : tempList)
			template = t;
		return template;
		
	}

}
