package com.bigroad.model.res;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import com.bigroad.model.db.TUser;

@XmlRootElement
public class ShareJson {
	
	private String  shareId;
	private Date    sentShareTime;
	private Integer filetype;
	private String  fileId;
	private String  fileName;
	private String  shareSentPersonName;
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
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

	public String getShareSentPersonName() {
		return shareSentPersonName;
	}

	public void setShareSentPersonName(String shareSentPersonName) {
		this.shareSentPersonName = shareSentPersonName;
	}

}
