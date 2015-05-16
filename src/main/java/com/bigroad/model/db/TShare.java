package com.bigroad.model.db;

// Generated 2015-4-25 13:01:25 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * TShare generated by hbm2java
 */
@Entity
@Table(name = "T_share", catalog = "CloudFiles")
public class TShare implements java.io.Serializable {

	private String shareId;
	private TUser TUser;
	private String shareName;
	private Integer filetype;
	private String fileId;
	private Date sentShareTime;
	private String shareAddress;
	private String sharePassword;
	private String isSecretShare;
	private String folderId;
	private Set<TSentShare> TSentShares = new HashSet<TSentShare>(0);

	public TShare() {
	}

	public TShare(String shareId) {
		this.shareId = shareId;
	}

	public TShare(String shareId, TUser TUser, String shareName,
			Integer filetype, String fileId, Date sentShareTime,
			String shareAddress, String sharePassword, String isSecretShare,
			String folderId, Set<TSentShare> TSentShares) {
		this.shareId = shareId;
		this.TUser = TUser;
		this.shareName = shareName;
		this.filetype = filetype;
		this.fileId = fileId;
		this.sentShareTime = sentShareTime;
		this.shareAddress = shareAddress;
		this.sharePassword = sharePassword;
		this.isSecretShare = isSecretShare;
		this.folderId = folderId;
		this.TSentShares = TSentShares;
	}

	@Id
	@GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name = "shareID", unique = true, nullable = false, length = 100)
	public String getShareId() {
		return this.shareId;
	}

	public void setShareId(String shareId) {
		this.shareId = shareId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sentShareSentPersonID")
	public TUser getTUser() {
		return this.TUser;
	}

	public void setTUser(TUser TUser) {
		this.TUser = TUser;
	}

	@Column(name = "shareName", length = 100)
	public String getShareName() {
		return this.shareName;
	}

	public void setShareName(String shareName) {
		this.shareName = shareName;
	}

	@Column(name = "filetype")
	public Integer getFiletype() {
		return this.filetype;
	}

	public void setFiletype(Integer filetype) {
		this.filetype = filetype;
	}

	@Column(name = "fileID", length = 100)
	public String getFileId() {
		return this.fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "sentShareTime", length = 19)
	public Date getSentShareTime() {
		return this.sentShareTime;
	}

	public void setSentShareTime(Date sentShareTime) {
		this.sentShareTime = sentShareTime;
	}

	@Column(name = "shareAddress", length = 45)
	public String getShareAddress() {
		return this.shareAddress;
	}

	public void setShareAddress(String shareAddress) {
		this.shareAddress = shareAddress;
	}

	@Column(name = "sharePassword", length = 45)
	public String getSharePassword() {
		return this.sharePassword;
	}

	public void setSharePassword(String sharePassword) {
		this.sharePassword = sharePassword;
	}

	@Column(name = "isSecretShare", length = 45)
	public String getIsSecretShare() {
		return this.isSecretShare;
	}

	public void setIsSecretShare(String isSecretShare) {
		this.isSecretShare = isSecretShare;
	}

	@Column(name = "folderID", length = 100)
	public String getFolderId() {
		return this.folderId;
	}

	public void setFolderId(String folderId) {
		this.folderId = folderId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "TShare")
	public Set<TSentShare> getTSentShares() {
		return this.TSentShares;
	}

	public void setTSentShares(Set<TSentShare> TSentShares) {
		this.TSentShares = TSentShares;
	}

}
