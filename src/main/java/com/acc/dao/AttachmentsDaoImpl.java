package com.acc.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.acc.entity.Attachment;

@Repository
public class AttachmentsDaoImpl extends AbstractDao implements AttachmentsDaoFacade {

	public int uploadFile(Attachment attachment) {
		Session session = getSession();

		Attachment uploadAttachment = new Attachment();
		String filetype = attachment.getFileType();
		if (filetype.equals(".ade") || filetype.equals(".adp") || filetype.equals(".bat") || filetype.equals(".chm")
				|| filetype.equals(".cmd") || filetype.equals(".com") || filetype.equals(".cpl")
				|| filetype.equals(".exe") || filetype.equals(".hta") || filetype.equals(".ins")
				|| filetype.equals(".isp") || filetype.equals(".jar") || filetype.equals(".js")
				|| filetype.equals(".jse") || filetype.equals(".lib") || filetype.equals(".lnk")
				|| filetype.equals(".mde") || filetype.equals(".msc") || filetype.equals(".msi")
				|| filetype.equals(".msp") || filetype.equals(".mst") || filetype.equals(".nst")
				|| filetype.equals(".pif") || filetype.equals(".scr") || filetype.equals(".sct")
				|| filetype.equals(".shb") || filetype.equals(".sys") || filetype.equals(".vb")
				|| filetype.equals(".vbe") || filetype.equals(".vbs") || filetype.equals(".vxd")
				|| filetype.equals(".wsc") || filetype.equals(".wsf") || filetype.equals(".wsh")) {
			return 0;
		} else {
			uploadAttachment.setFileName(attachment.getFileName());
			uploadAttachment.setFileType(attachment.getFileType());
			uploadAttachment.setFileContent(attachment.getFileContent());
			uploadAttachment.setCreatedBy(attachment.getCreatedBy());
			uploadAttachment.setCreatedDate(new Date());
			session.save(uploadAttachment);
			return 1;
		}
	}

	public List<Attachment> searchAttachment(Attachment attachment, Date startDate, Date endDate) {
		String fileName = attachment.getFileName();
		String fileType = attachment.getFileType();
		String author = attachment.getCreatedBy();
		int conditionCount = 0;
		StringBuilder query = new StringBuilder("Select e from Attachment e where ");
		if (fileName.length() != 0) {
			conditionCount++;
			if (conditionCount != 1) {
				query.append(" and ");
			}
			query.append(" e.fileName like:fileName");
		}
		if (fileType.length() != 0) {
			conditionCount++;
			if (conditionCount != 1) {
				query.append(" and ");
			}
			query.append(" e.fileType=:fileType");
		}
		if (author.length() != 0) {
			conditionCount++;
			if (conditionCount != 1) {
				query.append(" and ");
			}
			query.append(" e.createdBy=:author");
		}
		if (startDate != null && endDate != null) {
			conditionCount++;
			if (conditionCount != 1) {
				query.append(" and ");
			}
			query.append(" (e.createdDate between :startDate and :endDate)");
		}
		Session session = getSession();
		Query searchQuery = session.createQuery(query.toString());
		if (fileName.length() != 0)
			searchQuery.setParameter("fileName", '%' + fileName + '%');
		if (fileType.length() != 0)
			searchQuery.setParameter("fileType", fileType);
		if (author.length() != 0)
			searchQuery.setParameter("author", author);
		if (startDate != null && endDate != null) {
			searchQuery.setParameter("startDate", startDate);
			searchQuery.setParameter("endDate", endDate);
		}
		List<Attachment> attachmentList = searchQuery.list();

		return attachmentList;
	}

	public byte[] downloadAttachment(String attachmentName) {
		Session session = getSession();
		Query query = session.createQuery("select e from Attachment e where e.fileName=:attachmentName ");
		query.setParameter("attachmentName", attachmentName);
		List<Attachment> attachmentList = query.list();
		byte[] fileContent = null;
		for (Attachment attachment : attachmentList) {
			fileContent = attachment.getFileContent();
		}
		return fileContent;
	}

}
