package com.bigroad.dao;

import java.util.List;

import com.bigroad.model.db.TShare;

public interface ShareDaoI
{
	//保存分享信息,返回分享ID
	public String saveShareInfo(String filetype, String fileID);
	//发送分享信息
	public boolean sentShareInfo(String shareID, String sentUserID, String receiveUserID);
	//查询userID的分享列表
	public List<TShare> getShareList(String userID);
	//查询userID收到的分享列表
	public List<TShare> getReceiveList(String userID);
	//删除分享信息
	public boolean deleteShareInfo(String shareID);
    //保存分享信息，返回shareID
	public String saveTShare(TShare ts);
}
