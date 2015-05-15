package com.bigroad.dao.impl;


import java.util.List;

import com.bigroad.dao.SentShareDaoI;
import com.bigroad.model.db.TFile;
import com.bigroad.model.db.TSentShare;

public class SentShareDaoImpl extends BaseDaoImpl<TSentShare>implements SentShareDaoI {

	@Override
	public String getUserNameBysentUserID(String sentUserID) {
		// TODO Auto-generated method stub
		String hsqlstring="from TSentShare tss where TUserBySentShareSentPersonId='"+sentUserID+"'";
		List<TSentShare> list =super.find(hsqlstring);
		return list.get(0).getSentShareUserName();
	}

	@Override
	public boolean saveTSentShare(TSentShare tss) {
		// TODO Auto-generated method stub
		super.save(tss);	
		return false;
	}

}
