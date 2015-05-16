package com.bigroad.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.bigroad.model.db.TShare;

@Repository("shareDao")
public class ShareDaoTestImpl {
	// 保存分享信息,返回分享ID
	public String saveShareInfo(int fileType, String fileID) {
		return "11111";
	}

	// 发送分享信息
	public boolean sentShareInfo(String shareID, String sentUserID,
			String receiveUserID) {
		return true;
	}

	// 查询userID发出的分享列表
	public List<TShare> getShareList(String userID) {
		List<TShare> shareList = new ArrayList<TShare>();

		return shareList;
	}

	// 查询userID收到的分享列表
	public List<TShare> getReceiveList(String userID) {
		List<TShare> receiveList = new ArrayList<TShare>();
		return receiveList;
	}

	// 删除分享信息
	public boolean deleteShareInfo(String shareID) {
		return true;
	}

	// 保存分享信息，返回shareID
	public String saveTShare(TShare ts) {
		return null;
	}
	
}
