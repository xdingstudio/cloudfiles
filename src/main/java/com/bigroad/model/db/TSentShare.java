package com.bigroad.model.db;

// Generated 2015-4-25 13:01:25 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * TSentShare generated by hbm2java
 */
@Entity
@Table(name = "T_sentShare", catalog = "CloudFiles")
public class TSentShare implements java.io.Serializable {

	private String sentShareId;
	private TShare TShare;
	private TUser TUserBySentShareSentPersonId;
	private TUser TUserBySentShareReceiveId;
	private Integer isChecked;
	private Date sentShareTime;
	private String shareName;
	private String sentShareUserName;

	public TSentShare() {
	}

	public TSentShare(String sentShareId) {
		this.sentShareId = sentShareId;
	}

	public TSentShare(String sentShareId, TShare TShare,
			TUser TUserBySentShareSentPersonId,
			TUser TUserBySentShareReceiveId, Integer isChecked,
			Date sentShareTime, String shareName, String sentShareUserName) {
		this.sentShareId = sentShareId;
		this.TShare = TShare;
		this.TUserBySentShareSentPersonId = TUserBySentShareSentPersonId;
		this.TUserBySentShareReceiveId = TUserBySentShareReceiveId;
		this.isChecked = isChecked;
		this.sentShareTime = sentShareTime;
		this.shareName = shareName;
		this.sentShareUserName = sentShareUserName;
	}

	@Id
	@GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name = "sentShareID", unique = true, nullable = false, length = 100)
	public String getSentShareId() {
		return this.sentShareId;
	}

	public void setSentShareId(String sentShareId) {
		this.sentShareId = sentShareId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "shareID")
	public TShare getTShare() {
		return this.TShare;
	}

	public void setTShare(TShare TShare) {
		this.TShare = TShare;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sentShareSentPersonID")
	public TUser getTUserBySentShareSentPersonId() {
		return this.TUserBySentShareSentPersonId;
	}

	public void setTUserBySentShareSentPersonId(
			TUser TUserBySentShareSentPersonId) {
		this.TUserBySentShareSentPersonId = TUserBySentShareSentPersonId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sentShareReceiveID")
	public TUser getTUserBySentShareReceiveId() {
		return this.TUserBySentShareReceiveId;
	}

	public void setTUserBySentShareReceiveId(TUser TUserBySentShareReceiveId) {
		this.TUserBySentShareReceiveId = TUserBySentShareReceiveId;
	}

	@Column(name = "isChecked")
	public Integer getIsChecked() {
		return this.isChecked;
	}

	public void setIsChecked(Integer isChecked) {
		this.isChecked = isChecked;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "sentShareTime", length = 19)
	public Date getSentShareTime() {
		return this.sentShareTime;
	}

	public void setSentShareTime(Date sentShareTime) {
		this.sentShareTime = sentShareTime;
	}

	@Column(name = "shareName", length = 100)
	public String getShareName() {
		return this.shareName;
	}

	public void setShareName(String shareName) {
		this.shareName = shareName;
	}

	@Column(name = "sentShareUserName", length = 100)
	public String getSentShareUserName() {
		return this.sentShareUserName;
	}

	public void setSentShareUserName(String sentShareUserName) {
		this.sentShareUserName = sentShareUserName;
	}

}
