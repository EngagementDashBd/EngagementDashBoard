package com.acc.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acc.dao.TemplateDaoFacade;
import com.acc.entity.Project;
import com.acc.entity.ResourceMaster;
import com.acc.entity.Template;
@Service
public class TemplateServImpl implements TemplateService {
	@Autowired
	TemplateDaoFacade templateDao;
	@Transactional(readOnly=true)
	public int uploadFile(Template template) {
		int count = 0;
		count = templateDao.uploadFile(template);
		return count;
	}
	
	@Transactional(readOnly=true)
	public byte[] downloadTemplate(String templateName) {
		byte[] fileContent = templateDao.downloadTemplate(templateName);
		return fileContent;
	}

	@Transactional
	public List<Template> allTemplateDetails() {
		List<Template> allTemplatesData = new ArrayList<Template>();
		allTemplatesData = templateDao.allTemplateDetails();		
		return allTemplatesData;
	}

	@Transactional
	public int deleteTemplate(Integer id) {
		int count = 0;
		count = templateDao.deleteTemplate(id);
		return count;	}
	@Transactional
	public int UpdateTemplateDetails(Template template) {
		int count = 0;
		
		
			count = templateDao.UpdateTemplateDetails(template);
		
		
		return count;

}
	@Transactional
	public Template fetchDetails(Integer id) {
		Template template = templateDao.fetchDetails(id);
		return template;
	}

}
