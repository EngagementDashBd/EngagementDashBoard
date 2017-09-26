package com.acc.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acc.dao.AttachmentsDaoFacade;
import com.acc.entity.Attachment;
@Service
public class AttachServImpl implements AttachmentService {
	@Autowired
	AttachmentsDaoFacade attachDao;
	@Transactional(readOnly=true)
	public int uploadFile(Attachment attachment) {
		int count = 0;
		count = attachDao.uploadFile(attachment);
		return count;
	}
	@Transactional(readOnly=true)
	public List<Attachment> searchAttachment(Attachment attachment, Date startDate, Date endDate) {
		List<Attachment> attachmentData = attachDao.searchAttachment(attachment, startDate, endDate);
		return attachmentData;
	}
	@Transactional(readOnly=true)
	public byte[] downloadAttachment(String attachmentName) {
		byte[] fileContent = attachDao.downloadAttachment(attachmentName);
		return fileContent;
	}


}
