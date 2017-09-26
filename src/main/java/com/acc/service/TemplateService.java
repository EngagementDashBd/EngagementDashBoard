package com.acc.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.acc.entity.ResourceMaster;
import com.acc.entity.Template;

public interface TemplateService {
	public int uploadFile(Template template);
	public byte[] downloadTemplate(String templateName);
	public List<Template> allTemplateDetails();
	public int deleteTemplate(Integer id);
	public int UpdateTemplateDetails(Template template);
	public Template fetchDetails(Integer id);
}
