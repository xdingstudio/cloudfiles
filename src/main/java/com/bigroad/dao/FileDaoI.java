package com.bigroad.dao;

import java.util.List;

import com.bigroad.model.db.TFile;

public interface FileDaoI {
	   
	
	  
	  
//	  根据文件ID查询文件用户ID
	  public String getFileUserID(String fileID) ;	  
//	  根据文件ID查询文件路径
	  public String getFilePath(String fileID);
	  
//	  根据文件夹ID查询文件夹下的全部文件
	  public List<TFile> getByFolderID(String folderID);
//	  根据文件夹ID查询文件夹下的全部文件和子目录中的全部文件
	  public List<TFile> getAllChildFile(String folderID) ;
	  
//	  查询用户的根目录
	  public TFile  getRootFolderID(String UserID);
//	  查询用户的回收站目录
	  public TFile  getRecycleFolderID(String UserID);

//	  根据用户ID和文件后缀名查询用户所属的对应类型文件
	  public List<TFile> searchByfileExtension(String Extension,String userID);
	  
//	  根据文件ID查找文件的服务器存储路径
	  public String  getFileStoragePath(String fileID);
//	  根据MD5值查找文件，返回文件列表
	  public List<TFile> searchByFileMd5(String Md5);
	  
//	  更新文件名字
	  public boolean updateFileName(String fileID ,String fileName);
//	  更新文件夹名字
	  public boolean updateFolderName(String folderID ,String folderName);

/*
//	  更新文件或文件夹的逻辑路径
	  public boolean updateFilePath(String fileID);
*/
	  
//	  移动文件， 将fileID对应的文件移动到targetFolderID对应的文件夹下
	  public boolean updatefileParentID(String fileID ,String targetFolderID);
//	  移动文件夹，将folderID对应的文件夹移动到targetFolderID对应的文件夹下
	  public boolean updatefolderParentID(String folderID ,String targetFolderID);
	  
//	  创建文件或文件夹
	  public boolean saveFile(TFile file);
//	  删除文件
	  public boolean  deleteFile(String fileID);
//	  删除文件夹
	  public boolean  deleteFolder(String folderID);
	  
	  public   TFile getTfileByID(String fileId) ;
	  
}
