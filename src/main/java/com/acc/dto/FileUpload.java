package com.acc.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class FileUpload {
	private String filename;
	private List<MultipartFile> fileData;
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public List<MultipartFile> getFileData() {
		return fileData;
	}
	public void setFileData(List<MultipartFile> fileData) {
		this.fileData = fileData;
	}
	


}
