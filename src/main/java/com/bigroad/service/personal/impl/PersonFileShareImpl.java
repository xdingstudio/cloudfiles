package com.bigroad.service.personal.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigroad.dao.impl.DepartmentDaoTestImpl;
import com.bigroad.dao.impl.SentShareDaoTestImpl;
import com.bigroad.dao.impl.ShareDaoTestImpl;
import com.bigroad.dao.impl.UserdaoTestImpl;
import com.bigroad.model.db.TDepartment;
import com.bigroad.model.db.TFile;
import com.bigroad.model.db.TSentShare;
import com.bigroad.model.db.TShare;
import com.bigroad.model.db.TUser;
import com.bigroad.model.res.DepartmentInfJson;
import com.bigroad.model.res.ShareJson;
import com.bigroad.model.res.UserInfJson;
import com.bigroad.service.personal.PersonFileShareI;

@Service("personFileShare")
public class PersonFileShareImpl implements PersonFileShareI {

	@Autowired
	private UserdaoTestImpl userDao;

	@Autowired
	private DepartmentDaoTestImpl departmentDao;

	@Autowired
	private ShareDaoTestImpl shareDao;
	
	@Autowired
	private SentShareDaoTestImpl sentShareDao;

	@Override
	// 保存分享信息,返回分享ID
	public String saveShareFile(String fileID, String sentUserID,
			List<String> receiveUserIDList, int fileType) {
		TFile tf = new TFile(fileID); 
		TShare ts = new TShare();

		ts.setFileId(fileID);
		ts.setFiletype(fileType);
		ts.setShareName(tf.getFileName());
		ts.getTUser().setUserId(sentUserID);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date time=null;
		try {
		   time= sdf.parse(sdf.format(new Date()));
		} catch (ParseException e) {
		   e.printStackTrace();
		}
		ts.setSentShareTime(time);
		String shareID=shareDao.saveTShare(ts);
		
		String sentShareUserName=sentShareDao.getUserNameBysentUserID(sentUserID);
		for (String receiveUserID : receiveUserIDList) {
			TSentShare tss = new TSentShare();
			tss.getTShare().setShareId(shareID);
			tss.getTUserBySentShareSentPersonId().setUserId(sentUserID);
			tss.getTUserBySentShareReceiveId().setUserId(receiveUserID);
			tss.setIsChecked(0);
			tss.setShareName(tf.getFileName());
			tss.setSentShareTime(time);
			tss.setShareName(sentShareUserName);
			sentShareDao.saveTSentShare(tss);
		}
		
		return shareID;
	}

	@Override
	// 查看我的分享文件
	public List<ShareJson> getMyShare(String userID) {
		List<TShare> shareList = shareDao.getShareList(userID);
		List<ShareJson> shareJsonList = new ArrayList<ShareJson>();
		for (TShare share : shareList) {
			ShareJson shareJson = new ShareJson();
			shareJson.setFileId(share.getFileId());
			shareJson.setFileName(share.getShareName());
			shareJson.setFiletype(share.getFiletype());
			shareJson.setSentShareTime(share.getSentShareTime());
			shareJson.setShareId(share.getShareId());
			shareJson.setSentShareSentPersonName(null);
			shareJsonList.add(shareJson);
		}
		return shareJsonList;
	}

	@Override
	// 查看分享给我的文件
	public List<ShareJson> getShareToMe(String userID) {
		List<TShare> receiveList = shareDao.getReceiveList(userID);
		List<ShareJson> receiveJsonList = new ArrayList<ShareJson>();

		for (TShare receive : receiveList) {
			ShareJson receiveJson = new ShareJson();
			receiveJson.setFileId(receive.getFileId());
			receiveJson.setFileName(receive.getShareName());
			receiveJson.setFiletype(receive.getFiletype());
			receiveJson.setSentShareTime(receive.getSentShareTime());
			receiveJson.setShareId(receive.getShareId());
			receiveJson.setSentShareSentPersonName(receive.getTUser().getUserName());
			receiveJsonList.add(receiveJson);

		}

		return receiveJsonList;
	}

	@Override
	public String deleteShare(String shareId) {
		// TODO Auto-generated method stub
		if (shareDao.deleteShareInfo(shareId)) {
			return "delete share success";
		} else
			return "delete share fail";
	}

	@Override
	public List<DepartmentInfJson> getAllUserInf() {
		// TODO Auto-generated method stub
		List<TDepartment> departmentList = departmentDao.getAllDepart();
		List<DepartmentInfJson> departmentJsonList = new ArrayList<DepartmentInfJson>();
		for (TDepartment d : departmentList) {
			DepartmentInfJson departmentInfJson = new DepartmentInfJson();

			departmentInfJson.setDepartmentName(d.getDepartmentName());

			List<UserInfJson> userInfList = new ArrayList<UserInfJson>();
			List<TUser> userList = userDao.getUserbyDepart(d.getDepartmentId());

			for (TUser u : userList) {
				UserInfJson userInfJson = new UserInfJson();
				userInfJson.setUserID(u.getUserId());
				userInfJson.setUserName(u.getUserName());
				userInfJson.setUserPhone(u.getUserTelephone());
				userInfJson.setUserEmail(u.getUserMailbox());
				userInfList.add(userInfJson);
			}
			departmentInfJson.setUserList(userInfList);

			departmentJsonList.add(departmentInfJson);
		}
		return departmentJsonList;
	}
}
