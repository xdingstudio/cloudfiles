package com.bigroad.dao.impl;

import org.springframework.stereotype.Repository;

import com.bigroad.model.db.TSentShare;

@Repository("sentShareDao")
public class SentShareDaoTestImpl {

	// 通过sentUserID获取对应的sentShareUserName
	public String getUserNameBysentUserID(String sentUserID) {
		return null;
	}

	// 保存发送分享信息
	public boolean saveTSentShare(TSentShare tss) {
		return true;
	}
}
