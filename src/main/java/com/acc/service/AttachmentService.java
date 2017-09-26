package com.acc.service;

import java.util.Date;
import java.util.List;

import com.acc.entity.Attachment;

public interface AttachmentService {
	public int uploadFile(Attachment attachment);
	public List<Attachment> searchAttachment(Attachment attachment, Date startDate, Date endDate);
	public byte[] downloadAttachment(String attachmentName);
}
