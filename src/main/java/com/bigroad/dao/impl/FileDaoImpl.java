package com.bigroad.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bigroad.dao.FileDaoI;
import com.bigroad.model.db.TFile;

//@Repository("fileDao")
public class FileDaoImpl extends BaseDaoImpl<TFile> implements FileDaoI {

	@Override
	public List<TFile> getByFolderID(String folderID) {
		// TODO Auto-generated method stub
		String hqlstring="from TFile f where f.TFile.fileId='"+folderID+"'";
		System.out.println(hqlstring);
		List<TFile> filelist=super.find(hqlstring);
		return filelist;
	}
	public List<TFile> getRecycle(String UserID){
		String hqlstring="from TFile f where f.TUser.userId='"+UserID+"'and f.TFile.fileId='Recycle'";
		List<TFile> recyclelist=super.find(hqlstring);
		return recyclelist;
	 }
	
	@Override
	public String getRootFolderID(String UserID) {
		// TODO Auto-generated method stub
		String hqlstring="from TFile f where f.TUser.userId='"+UserID+"'and f.TFile.fileId='root'";
		List<TFile> RootFolderID=super.find(hqlstring);
		
		return RootFolderID.get(0).getFileId().toString();
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
		
		return true;
	}

	@Override
	public boolean updatefolderParentID(String fileID, String targetFolderID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateFileName(String fileID, String fileName) {
		// TODO Auto-generated method stub
	
		return true;
	}

	@Override
	public boolean updateFolderName(String folderID, String folderName) {
		// TODO Auto-generated method stub

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
		
		return true;
	}
	
	public TFile getUserByFile(String fileId) {
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
		TFile file = getUserByFile(fileID);
		String userID= file.getTUser().getUserId();
		String path = file.getFilePath()+file.getFileName()+"%";
		Map<String,Object> map =new HashMap<String, Object>(); 
		map.put("path", path);
		map.put("userId", userID);
		List<TFile> fileList = super.find(hqlString, map);
		return fileList;
	}
	@Override
	public String getFileUserID(String fileID) {
		// TODO Auto-generated method stub
		TFile file = getUserByFile(fileID);
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
