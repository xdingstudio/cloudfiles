package com.bigroad.service.personal.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigroad.dao.FileDaoI;
import com.bigroad.dao.UserDaoI;
import com.bigroad.model.db.TFile;
import com.bigroad.model.db.TUser;
import com.bigroad.model.res.PersonFileJson;
import com.bigroad.service.personal.PersonFileServiceI;

@Service("personFile")
public class PersonFileServiceImpl implements PersonFileServiceI {

	
	
	 /**
     * 注入userDao
     */
    @Autowired
    private FileDaoI fileDao;
    
    @Autowired
    private UserDaoI userDao;
    
	@Override
	public List<PersonFileJson> getAllPersonFiles(String userID) {
		// TODO Auto-generated method stub
		TFile  folder = fileDao.getRootFolderID(userID);
		String rootFolderID =  folder.getFileId();
	    List<PersonFileJson> fileJsons = getchidFiles(rootFolderID);
	    PersonFileJson  root = new PersonFileJson();
	    root.setFileType(3);
	    root.setFileId(rootFolderID);
	    fileJsons.add(root);
	    return fileJsons;
	}

	@Override
    public	List<PersonFileJson> getchidFiles(String fileID){
		// TODO Auto-generated method stub
		 
			List<TFile> filelist= fileDao.getByFolderID(fileID);
			List<PersonFileJson> personFileList=new ArrayList<PersonFileJson>();
		
		    for(TFile file : filelist) {
		    	PersonFileJson personFile=new PersonFileJson();
		    	personFile.setFileId(file.getFileId());
		    	personFile.setFileName(file.getFileName());
		    	personFile.setFileType(file.getFileType());
		    	personFile.setFileExtension(file.getFileExtension());
		
		    	personFileList.add(personFile);
		    }
		    return personFileList;

	}

	@Override
	public List<PersonFileJson> getAllMusic(String userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PersonFileJson> getAllVedio(String userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PersonFileJson> getAllPicture(String userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PersonFileJson> getAllDocument(String userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PersonFileJson> getAllMyRecycle(String userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addNewFolder(String userID, String parentFileID,String folderName) {
		// TODO Auto-generated method stub
		
				TFile folderFile = new TFile();
				folderFile.setFileName(folderName);
				//folderFile.getTUser().setUserId(userID);
				TUser user = userDao.getUserbyID(userID);
				folderFile.setTUser(user);
				folderFile.setFileNewTime(new Date());
				folderFile.setFileType(1);
				folderFile.setFileExtension("folder");
				TFile tFile  = fileDao.getTfileByID(parentFileID);
				String path = tFile.getFilePath()+"/"+tFile.getFileName();
				folderFile.setFilePath(path);
				fileDao.saveFile(folderFile);
				return "success";
	}

	@Override
	public String deleteFile(String fileID, String fileType) {
		// TODO Auto-generated method stub
		if(fileType.equals("file") )
		{
			fileDao.deleteFile(fileID);
			return "delete success！";
		}
		else if(fileType.equals("folder"))
		{
			fileDao.deleteFolder(fileID);
			return "delete success！";
		}
		return "delete fail！";

	}

	@Override
	public String moveFile(String fileID, String targetFolderID, String fileType) {
		if(fileType.equals("file"))
		{
			fileDao.updatefileParentID(fileID, targetFolderID);
			
			return "move success！";
		}
		else if(fileType.equals("folder"))
		{
			fileDao.updatefolderParentID(fileID, targetFolderID);
			return "move success！";
		}
		else	return "move fail！";
	}

	@Override
	public String updateFileName(String fileID, String fileType,
			String fileNewName) {
		// TODO Auto-generated method stub
		if(fileType.equals("file"))
		{
			fileDao.updateFileName(fileID, fileNewName);
			return "update success！";
		}
		else if(fileType.equals("folder"))
		{
			fileDao.updateFolderName(fileID, fileNewName);
			return "update success！";
		}
		else	return "update fail！";
	}

	@Override
	public String saveFile(TFile f, String folderID, String userID) {
		// TODO Auto-generated method stub
		System.out.println(folderID+"--------------------------------------------");
		TFile folderFile=fileDao.getTfileByID(folderID);
		String folderName = folderFile.getFileName();
		String folderPath = folderFile.getFilePath();
		String path = folderPath+"/"+folderName;
		f.setFilePath(path);
		f.setFilePrimaryPath(path);
		TUser u = userDao.getUserbyID(userID);
		f.setTUser(u);
		f.setTFile(folderFile);
		fileDao.saveFile(f);
		return "success";
	}

}
