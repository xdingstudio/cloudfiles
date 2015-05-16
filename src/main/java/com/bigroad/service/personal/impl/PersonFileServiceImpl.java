package com.bigroad.service.personal.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigroad.dao.FileDaoI;
import com.bigroad.model.db.TFile;
import com.bigroad.model.res.PersonFileJson;
import com.bigroad.model.res.filelistConvertToPersonFileJson;
import com.bigroad.service.personal.PersonFileServiceI;

//@Service("personFile")
public class PersonFileServiceImpl implements PersonFileServiceI {

	private filelistConvertToPersonFileJson listToJson =new filelistConvertToPersonFileJson();
	
	 /**
     * 注入fileDao
     */
    @Autowired
    private FileDaoI fileDao;
    
	@Override
	public List<PersonFileJson> getAllPersonFiles(String userID) {
		String rootID= fileDao.getRootFolderID(userID);
		List<TFile> filelist= fileDao.getByFolderID(rootID);
		List<PersonFileJson> personFileListJsonList=listToJson.listToJson(filelist);
		return personFileListJsonList;
	}

	@Override
    public	List<PersonFileJson> getchidFiles(String fileID){
			List<TFile> filelist= fileDao.getByFolderID(fileID);
			List<PersonFileJson> personFileListJsonList=listToJson.listToJson(filelist);
			return personFileListJsonList;
	}

	@Override
	public List<PersonFileJson> getAllMusic(String userID) {
		List<TFile> musiclist=fileDao.searchByfileExtension("mp3", userID);
		List<TFile> musiclist1=fileDao.searchByfileExtension("rm", userID);
		List<TFile> musiclist2=fileDao.searchByfileExtension("wma", userID);
		musiclist.addAll(musiclist1);
		musiclist .addAll(musiclist2);
		List<PersonFileJson> personFileListJsonList=listToJson.listToJson(musiclist);
		return personFileListJsonList;
	}

	@Override
	public List<PersonFileJson> getAllVedio(String userID) {
		List<TFile> vediolist=fileDao.searchByfileExtension("avi", userID);
		List<TFile> vediolist1=fileDao.searchByfileExtension("mov", userID);
		List<TFile> vediolist2=fileDao.searchByfileExtension("mpeg", userID);
		List<TFile> vediolist3=fileDao.searchByfileExtension("rm", userID);
		List<TFile> vediolist4=fileDao.searchByfileExtension("asf", userID);
		vediolist .addAll(vediolist1);
		vediolist .addAll(vediolist2);
		vediolist .addAll(vediolist3);
		vediolist .addAll(vediolist4);
		List<PersonFileJson> personFileListJsonList=listToJson.listToJson(vediolist);
		return personFileListJsonList;
	}

	@Override
	public List<PersonFileJson> getAllPicture(String userID) {
		List<TFile> picturelist=fileDao.searchByfileExtension("bmp", userID);
		List<TFile> picturelist1=fileDao.searchByfileExtension("gif", userID);
		List<TFile> picturelist2=fileDao.searchByfileExtension("jpeg", userID);
		List<TFile> picturelist3=fileDao.searchByfileExtension("tiff", userID);
		List<TFile> picturelist4=fileDao.searchByfileExtension("psd", userID);
		List<TFile> picturelist5=fileDao.searchByfileExtension("png", userID);
		List<TFile> picturelist6=fileDao.searchByfileExtension("swf", userID);
		List<TFile> picturelist7=fileDao.searchByfileExtension("svg", userID);
		picturelist.addAll(picturelist1);
		picturelist.addAll(picturelist2);
		picturelist.addAll(picturelist3);
		picturelist.addAll(picturelist4);
		picturelist.addAll(picturelist5);
		picturelist.addAll(picturelist6);
		picturelist.addAll(picturelist7);
		List<PersonFileJson> personFileListJsonList=listToJson.listToJson(picturelist);
		return personFileListJsonList;
	}

	@Override
	public List<PersonFileJson> getAllDocument(String userID) {
		List<TFile> documentlist=fileDao.searchByfileExtension("pdf", userID);
		List<TFile> documentlist1=fileDao.searchByfileExtension("doc", userID);
		List<TFile> documentlist2=fileDao.searchByfileExtension("ppt", userID);
		List<TFile> documentlist3=fileDao.searchByfileExtension("txt", userID);
		List<TFile> documentlist4=fileDao.searchByfileExtension("html", userID);
		documentlist.addAll(documentlist1);
		documentlist.addAll(documentlist2);
		documentlist.addAll(documentlist3);
		documentlist.addAll(documentlist4);
		List<PersonFileJson> personFileListJsonList=listToJson.listToJson(documentlist);
		return personFileListJsonList;
	}

	@Override
	public List<PersonFileJson> getAllMyRecycle(String userID) {
		List<TFile> myRecyclelist= fileDao.getRecycle(userID);
		List<PersonFileJson> personFileListJsonList=listToJson.listToJson(myRecyclelist);
		return personFileListJsonList;
	}

	@Override
	public String addNewFolder(String userID, String parentFileID,String folderName) {
		TFile folder=new TFile();
		folder.getTUser().setUserId(userID);
		folder.getTFile().setFileId(parentFileID);
		folder.setFileName(folderName);
		
		String parentPath=fileDao.getFilePath(parentFileID);
	    String folderPath=parentPath+"/"+folderName;
		folder.setFilePath(folderPath);
		folder.setFilePrimaryPath(folderPath);
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date time=null;
		try {
		   time= sdf.parse(sdf.format(new Date()));
		} catch (ParseException e) {
		   e.printStackTrace();
		}
		folder.setFileNewTime(time);
		
		fileDao.saveFile(folder);
		return "create success！";
	}

	@Override
	public String deleteFile(String fileID, int fileType) {
		if(fileType == 0)
		{
			fileDao.deleteFile(fileID);
		}
		else
		{
			fileDao.deleteFolder(fileID);
		}
		return "delete success！";
	}

	@Override
	public String moveFile(String fileID, String targetFolderID, int fileType) {
		if(fileType == 0)
		{
			fileDao.updatefileParentID(fileID, targetFolderID);
		}
		else
		{
			fileDao.updatefolderParentID(fileID, targetFolderID);
		}
		return "move success！";
	}

	@Override
	public String updateFileName(String fileID, int fileType,
			String fileNewName) {
		if(fileType == 0)
		{
			fileDao.updateFileName(fileID, fileNewName);
		}
		else
		{
			fileDao.updateFolderName(fileID, fileNewName);
		}
		return "update success！";
	}

}
