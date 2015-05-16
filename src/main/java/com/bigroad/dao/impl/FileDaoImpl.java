package com.bigroad.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Repository;

import com.bigroad.dao.FileDaoI;
import com.bigroad.model.db.TFile;

@Repository("fileDao")
public class FileDaoImpl extends BaseDaoImpl<TFile> implements FileDaoI {
    
	@Override
	public List<TFile> getByFolderID(String folderID) {
		// TODO Auto-generated method stub
		String hqlstring="from TFile f where f.TFile.fileId='"+folderID+"'";
		List<TFile> filelist=super.find(hqlstring);
		return filelist;
	}
	public List<TFile> getRecycle(String UserID){
		String hqlstring="from TFile f where f.TUser.userId='"+UserID+"'and f.TFile.fileId='Recycle'";
		List<TFile> recyclelist=super.find(hqlstring);
		return recyclelist;
	 }
	
	@Override
	public TFile  getRootFolderID(String UserID) {
		// TODO Auto-generated method stub
		String hqlstring="from TFile f where f.TUser.userId='"+UserID+"'and f.TFile.fileId='root'";
		List<TFile> list=super.find(hqlstring);
		
		return list.get(0);
	}

	@Override
	public List<TFile> searchByfileExtension(String Extension, String userID) {
		// TODO Auto-generated method stub
		String hqlString="from TFile f where f.fileExtension='"+Extension+"' and f.TUser.userId='"+userID+"'";
		List<TFile> f = super.find(hqlString);
		return f;
	}

	@Override
	public List<TFile> searchByFileMd5(String Md5) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				String hqlString="from TFile f where f.fileMd5=:Md5";
				List<TFile> filelist = super.find(hqlString);
				return filelist;
	}

	@Override
	public String getFileStoragePath(String fileID) {
		// TODO Auto-generated method stub
		String hqlstring="from TFile f where f.fileId=:fileID";
		List<TFile> filelist=super.find(hqlstring);		
		return filelist.get(0).getFileStoragePath().toString();
	}

	@Override
	public boolean updatefileParentID(String fileID, String targetFolderID) {
		TFile tf=getTfileByID(fileID);
		TFile tfd = getTfileByID(targetFolderID);
		String newFolderPath = tfd.getFilePath( ) + "/" + tfd.getFileName( );
		tf.setTFile(tfd);//更新文件父目录
		tf.setFilePath(newFolderPath);//更新文件路径
		super.update(tf);
		return true;
	}
	
	@Override
	public boolean updatefolderParentID(String folderID, String targetFolderID) {
		TFile tf=getTfileByID(folderID);//获得文件夹
		String tfPath=tf.getFilePath();//文件夹路径
		int tfPathLength=tfPath.length();
	    String foldeID=tf.getTFile().getFileId();//文件夹的父目录ID
	    TFile folderTFile=getTfileByID(foldeID);//获得文件夹的父文件夹
	    //String folderName=folderTFile.getFileName();//文件夹的父文件名（文件夹路径tfPath=folderPath+"/"+folderName）
	    String folderPath=folderTFile.getFilePath();//文件夹的父目录路径
	    TFile targetTFile=getTfileByID(targetFolderID);//欲移往的文件夹
	    String targetFileName=targetTFile.getFileName();//欲移往的文件夹名
	    String newPath=folderPath+"/"+targetFileName;//移动后的路径，用于作为下层孩子修改后的路径
	    	    
	    List<TFile> fileList=getAllChildFile(folderID);
		for (TFile file : fileList)
		{     
			
			String pc=file.getFilePath();//获得孩子文件的当前路径
			String pcSubString = pc.substring(tfPathLength);
			String subNewPath = newPath+pcSubString;
			file.setFilePath(subNewPath);
			super.update(file);
		} 
		updatefileParentID(folderID,targetFolderID);
		
		return true;
	}
	

	@Override
	public boolean updateFileName(String fileID, String fileName) {
		// TODO Auto-generated method stub
		TFile tf=getTfileByID(fileID);
		tf.setFileName(fileName);
		super.update(tf);
		return true;		
	}

	@Override
	public boolean updateFolderName(String folderID, String folderName) {
		// TODO Auto-generated method stub
		TFile tf=getTfileByID(folderID);
		String ppn=tf.getFileName();//获得修改前名字
		String pp=tf.getFilePath();//获取文件夹路径
		String ppp=pp+"/"+ppn;//文件夹 路径+修改前的名字，用于作为下层孩子原始路径
		int pppLength = ppp.length();
		String pf=pp+"/"+folderName;//文件夹 路径+修改后的名字，用于作为下层孩子修改后的路径
		
		List<TFile> fileList=getAllChildFile(folderID);
		for (TFile file : fileList)
		{     
			
			String pc=file.getFilePath();//获得孩子文件的当前路径
			
			String pcSubString = pc.substring(pppLength);
			String newPath = pf+pcSubString;
			file.setFilePath(newPath);
			super.update(file);
		} 
		tf.setFileName(folderName);
		super.update(tf);
		return true;
	}


	@Override
	public boolean saveFile(TFile file) {
		// TODO Auto-generated method stub
		super.save(file);
		return true;
	}

	@Override
	public boolean deleteFile(String fileID) {
		// TODO Auto-generated method stub
		String hqlstring="delete TFile f where f.fileId=:fileID";
		Map<String,Object> map =new HashMap<String, Object>(); 
		map.put("fileID", fileID);
		super.deleteById(hqlstring, map);
		
		return true;
	}

	@Override
	public boolean deleteFolder(String folderID) {
		// TODO Auto-generated method stub
		String hqlstring="delete TFile f where f.TFile.fileId=:folderID";
		Map<String,Object> map =new HashMap<String, Object>(); 
		map.put("folderID", folderID);
		super.deleteById(hqlstring, map);
		
		deleteFile(folderID);
		return true;
	}
	@Override
	public TFile getTfileByID(String fileId) {
		// TODO Auto-generated method stub
		String hqlString ="from TFile u where u.fileId=:fileId ";
		Map<String,Object> map =new HashMap<String, Object>(); 
		map.put("fileId", fileId);
		TFile file = super.get(hqlString, map);
		return file;
	}
	@Override
	public List<TFile> getAllChildFile(String fileID) {
		// TODO Auto-generated method stub
		String hqlString ="from TFile as f where f.filePath like :path and f.TUser.userId=:userId";
		TFile file = getTfileByID(fileID);
		String userID= file.getTUser().getUserId();
		String path = file.getFilePath()+"/"+file.getFileName()+"%";
		Map<String,Object> map =new HashMap<String, Object>(); 
		map.put("path", path);
		map.put("userId", userID);
		List<TFile> fileList = super.find(hqlString, map);
		return fileList;
	}
	@Override
	public String getFileUserID(String fileID) {
		// TODO Auto-generated method stub
		TFile file = getTfileByID(fileID);
		return file.getTUser().getUserId();
       
		
	}
	
	@Override
	public String getFilePath(String fileID) {
		// TODO Auto-generated method stub
		String hqlString = "from TFile f where f.fileId=:fileID";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fileId", fileID);
		TFile file = super.get(hqlString, map);
		return file.getFilePath();
	}

}
