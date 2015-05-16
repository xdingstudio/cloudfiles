package com.bigroad.service.personal;

import java.util.List;

import com.bigroad.model.res.FolderListJson;
import com.bigroad.model.res.ShareJson;
import com.bigroad.model.res.DepartmentInfJson;

public interface PersonFileShareI {

	//保存分享信息,返回分享ID
	String saveShareFile(String fileID, String sentUserID,List<String> receiveUserID, int fileType);

	// 查看我的分享文件
	List<ShareJson> getMyShare(String userID);

	// 查看分享给我的文件
	List<ShareJson> getShareToMe(String userID);

	
	// 取消分享,返回shareId
	String deleteShare(String shareId);

	
	//获得所有用户
	List<DepartmentInfJson> getAllUserInf();
	
}
