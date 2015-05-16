package com.bigroad.dao;

import com.bigroad.model.db.TSentShare;

public interface SentShareDaoI {
	
	// 通过sentUserID获取对应的sentShareUserName
	public String getUserNameBysentUserID(String sentUserID);

	// 保存发送分享信息
	public boolean saveTSentShare(TSentShare tss);
}
