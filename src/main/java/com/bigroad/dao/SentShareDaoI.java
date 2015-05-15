package com.bigroad.dao;

import com.bigroad.model.db.TSentShare;

public interface SentShareDaoI {
	public String getUserNameBysentUserID(String sentUserID);
	public boolean saveTSentShare(TSentShare tss);
}
