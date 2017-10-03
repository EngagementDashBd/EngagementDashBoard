package com.acc.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.acc.dto.FileUpload;
import com.acc.entity.Project;
import com.acc.entity.ResourceMaster;
import com.acc.service.TemplateService;
import com.acc.entity.Template;

@Controller
public class TemplateController {
	@Autowired
	TemplateService tempServ;
	
	

	@RequestMapping("/storeTemplate.htm")
	public ModelAndView uploadFile(Model model, FileUpload uploadItem, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		ModelAndView modelandview = new ModelAndView();
		InputStream inputStream = null;
		List<MultipartFile> files = uploadItem.getFileData();
		for (MultipartFile file : files) {
			if (file.getSize() > 0)
				inputStream = file.getInputStream();
			byte[] fileData = IOUtils.toByteArray(inputStream);
			String originalfileName = file.getOriginalFilename();
			int position = originalfileName.lastIndexOf(".");
			String fileType = originalfileName.substring(position);
			
			Template template = new Template();
			template.setTemplateName(originalfileName);
			template.setFileType(fileType);
			template.setTemplateFile(fileData);
			tempServ.uploadFile(template);
		}
		modelandview = allTemplateDetails(request,response);
		return modelandview;
	}

	@RequestMapping(value = "/downloadTemplate.htm", method = RequestMethod.GET)
	public void downloadAttachment(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String templateName = request.getParameter("templateName");
		byte[] fileContent = tempServ.downloadTemplate(templateName);
		if(fileContent != null)
		{
			ByteArrayInputStream in = new ByteArrayInputStream(fileContent);
			OutputStream outStream = response.getOutputStream();
			String fileName = URLEncoder.encode(templateName, "UTF-8");
			fileName = URLDecoder.decode(fileName, "ISO8859_1");
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-disposition", "template; filename=" + fileName);
			byte[] buffer = new byte[4096];
			int bytesRead = -1;
	
			while ((bytesRead = in.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}
		}
		else if(templateName.contains("Upload"))
		{
			response.sendRedirect("redirect.htm?pageName=employeeExcelUpload&error=filenotfound");
		}
		else
		{
			response.sendRedirect("redirect.htm?pageName=employeeExcelUpdate&error=filenotfound");
		}
	}

	@RequestMapping("/allTemplateDetails.htm")
	public ModelAndView allTemplateDetails(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelandview = new ModelAndView();
		HttpSession session = request.getSession();
		List<Template> templates = new ArrayList<Template>();
		templates = tempServ.allTemplateDetails();
		modelandview.addObject("templates", templates);
		modelandview.setViewName("allTemplate");
		return modelandview;
	}

	
	
	@RequestMapping("/deleteTemplate.htm")
	public ModelAndView deleteTemplate(HttpServletRequest request,HttpServletResponse response )
	{
		
		ModelAndView modelandview = new ModelAndView();
		List<Template> templates = new ArrayList<Template>(); 
		Integer Id = Integer.parseInt(request.getParameter("templateId"));
		int count = tempServ.deleteTemplate(Id);
		HttpSession session = request.getSession();
		templates =  tempServ.allTemplateDetails();
		session.setAttribute("templates", templates);
		if(count == 1)
		{
			
			modelandview.addObject("deletecode","success");
		}
		else
		{
			modelandview.addObject("deletecode","failure");
		}
		modelandview.setViewName("allTemplate");
		return modelandview;
	
	}
	
	@RequestMapping("/fetchTemplateDetails.htm")
	public ModelAndView fetchTemplateDetails( HttpServletRequest request, HttpServletResponse response )
	{
		ModelAndView modelandview = new ModelAndView();
		HttpSession session = request.getSession();
		Integer Id = Integer.parseInt(request.getParameter("templateId"));
		Template template =  tempServ.fetchDetails(Id);
		modelandview.addObject("template",template);
		modelandview.setViewName("UpdateAllTemplates");
		return modelandview;
	}
	
	
	@RequestMapping("/updateTemplateDetails.htm")
	public ModelAndView UpdateTemplateDetails( @ModelAttribute Template template,HttpServletRequest request, HttpServletResponse response )
	{
		int count = 0;
		ModelAndView modelandview = new ModelAndView();
		HttpSession session = request.getSession();
				count = tempServ.UpdateTemplateDetails(template);
		ArrayList<Template> templates = new ArrayList<Template>();
		templates = (ArrayList<Template>)  tempServ.allTemplateDetails();
		session.setAttribute("templates",templates);
		
		
		modelandview.setViewName("allTemplate");
		if(count == 1)
		{
			
			modelandview.addObject("updatecode","success");
		}
		else
		{
			modelandview.addObject("updatecode","failure");
		}
		return modelandview;
	}
}