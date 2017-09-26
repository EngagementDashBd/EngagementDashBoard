package com.acc.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.acc.dto.FileUpload;
import com.acc.entity.Attachment;
import com.acc.entity.ResourceMaster;
import com.acc.service.AttachmentService;


@Controller
public class AttachmentsController {
	@Autowired
	AttachmentService attachServ;
	@RequestMapping("storeAttachments.htm")
	public ModelAndView uploadFile(Model model, FileUpload uploadItem,HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		HttpSession session = request.getSession();
		ResourceMaster resource = (ResourceMaster)session.getAttribute("resource");
		ModelAndView modelandview = new ModelAndView();
		List<MultipartFile> files = uploadItem.getFileData();
		InputStream inputStream = null;
		for(MultipartFile file : files)
		{
			if (file.getSize() > 0)
				inputStream = file.getInputStream();
			byte[] fileData = IOUtils.toByteArray(inputStream);
			String fileName = file.getOriginalFilename();
			int position = fileName.lastIndexOf(".");
			String fileType = fileName.substring(position); 
		//String fileType = file.getContentType();
			String creatorId = resource.getEnterpriseId();
			Attachment attachment = new Attachment();
			attachment.setFileName(fileName);
			attachment.setFileType(fileType);
			attachment.setFileContent(fileData);
			attachment.setCreatedBy(creatorId);
			int count = 0;
			count = attachServ.uploadFile(attachment);
			if(count == 1)
				modelandview.addObject("fileUpload", "success");
			else
				modelandview.addObject("fileUpload","failure");
			session.setAttribute("resource", resource);
		}
		modelandview.setViewName("Attachments");
		return modelandview;
	}
	@RequestMapping(value = "/searchAttachment.htm", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView searchAttachment(HttpServletRequest request,HttpServletResponse response,Attachment attachment) throws IOException, ParseException
	{
		ModelAndView modelandview = new ModelAndView();
		Date startDate = null;
		Date endDate = null;
		String startingDate = request.getParameter("startDate");
		System.out.println(startingDate);
		String endingDate = request.getParameter("endDate");
		System.out.println(endingDate);
		if(startingDate.length() != 0)
			startDate = new SimpleDateFormat("dd/MM/yyyy").parse(startingDate); 
		if(endingDate.length() != 0)
			endDate = new SimpleDateFormat("dd/MM/yyyy").parse(endingDate);
		System.out.println(startDate + " "+endDate);
		List<Attachment> attachmentData = attachServ.searchAttachment(attachment,startDate,endDate);
		System.out.println(attachmentData);
		
		modelandview.setViewName("searchAttachment");
		modelandview.addObject("attachmentData", attachmentData);
		return modelandview;
	}
	@RequestMapping(value = "/downloadAttachment.htm", method = RequestMethod.GET)
	public void downloadAttachment(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		String attachmentName = request.getParameter("fileName");
		byte[] fileContent = attachServ.downloadAttachment(attachmentName);
		ByteArrayInputStream in = new ByteArrayInputStream(fileContent);
		OutputStream outStream = response.getOutputStream();
		String fileName = URLEncoder.encode(attachmentName, "UTF-8");
	    fileName = URLDecoder.decode(fileName, "ISO8859_1");
	    response.setContentType("application/x-msdownload");            
	    response.setHeader("Content-disposition", "attachment; filename="+ fileName);
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
         
        while ((bytesRead = in.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
       
	}

	
}

