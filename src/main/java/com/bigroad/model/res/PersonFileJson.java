package com.bigroad.model.res;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PersonFileJson {
	
	private int  fileType;
	private String  fileId;
	

	private String  fileName;
	
	private String  fileExtension;

	
	
	public int getFileType() {
		return fileType;
	}
	public void setFileType(int fileType) {
		this.fileType = fileType;
	}
	
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}


	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileExtension() {
		return fileExtension;
	}
	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

	
}
