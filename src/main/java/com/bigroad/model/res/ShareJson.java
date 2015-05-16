package com.bigroad.model.res;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ShareJson {
	
	private String  shareId;
	private Date    sentShareTime;
	private Integer filetype;
	private String  fileId;
	private String  fileName;
	private String  sentShareSentPersonName;
	
	public String getSentShareSentPersonName() {
		return sentShareSentPersonName;
	}
	public void setSentShareSentPersonName(String sentShareSentPersonName) {
		this.sentShareSentPersonName = sentShareSentPersonName;
	}
	public String getShareId() {
		return shareId;
	}
	public void setShareId(String shareId) {
		this.shareId = shareId;
	}
	public Date getSentShareTime() {
		return sentShareTime;
	}
	public void setSentShareTime(Date sentShareTime) {
		this.sentShareTime = sentShareTime;
	}
	public Integer getFiletype() {
		return filetype;
	}
	public void setFiletype(Integer filetype) {
		this.filetype = filetype;
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
}
