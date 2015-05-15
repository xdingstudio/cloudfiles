package com.bigroad.service.personal;

import java.util.List;

import com.bigroad.model.res.FolderListJson;
import com.bigroad.model.res.ShareJson;
import com.bigroad.model.res.DepartmentInfJson;

public interface PersonFileShareI {

	// 分享文件，返回分享ID
	String shareFile(String fileID, String sentUserID,List<String> receiveUserID, String fileType);

	// 查看我的分享文件
	List<ShareJson> getMyShare(String userID);

	// 查看分享给我的文件
	List<ShareJson> getShareToMe(String userID);

	// 转存分享文件  folderID 为目标文件夹ID
	String saveToMyFile(String fileID, String fileType, String userID,String folderID);
	   
	// 取消分享,返回shareId
	String deleteShare(String shareId);
	
	//获得目录下文件夹
	List<FolderListJson> getFolderID(String userID,String parentFolderID);
	
	//获得所有用户
	List<DepartmentInfJson> getAllUserInf();
	
}
