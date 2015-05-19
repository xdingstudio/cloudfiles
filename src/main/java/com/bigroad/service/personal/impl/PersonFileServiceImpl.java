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
import com.bigroad.model.res.filelistConvertToPersonFileJson;
import com.bigroad.service.personal.PersonFileServiceI;

@Service("personFile")
public class PersonFileServiceImpl implements PersonFileServiceI {
	private filelistConvertToPersonFileJson listToJson =new filelistConvertToPersonFileJson();
    @Autowired
    private FileDaoI fileDao;
    
    @Autowired
    private UserDaoI userDao;
    
	@Override
	public List<PersonFileJson> getAllPersonFiles(String userID) {
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
		 
		List<TFile> filelist= fileDao.getByFolderID(fileID);
		List<PersonFileJson> personFileListJsonList=listToJson.listToJson(filelist);
		return personFileListJsonList;
	}

	@Override
	public List<PersonFileJson> getAllMusic(String userID) {
		List<TFile> musiclist=fileDao.searchByfileExtension(".mp3", userID);
		List<TFile> musiclist1=fileDao.searchByfileExtension(".rm", userID);
		List<TFile> musiclist2=fileDao.searchByfileExtension(".wma", userID);
		musiclist.addAll(musiclist1);
		musiclist .addAll(musiclist2);
		List<PersonFileJson> personFileListJsonList=listToJson.listToJson(musiclist);
		return personFileListJsonList;
	}

	@Override
	public List<PersonFileJson> getAllVedio(String userID) {
		List<TFile> vediolist=fileDao.searchByfileExtension(".avi", userID);
		List<TFile> vediolist1=fileDao.searchByfileExtension(".mov", userID);
		List<TFile> vediolist2=fileDao.searchByfileExtension(".mpeg", userID);
		List<TFile> vediolist3=fileDao.searchByfileExtension(".rm", userID);
		List<TFile> vediolist4=fileDao.searchByfileExtension(".asf", userID);
		vediolist .addAll(vediolist1);
		vediolist .addAll(vediolist2);
		vediolist .addAll(vediolist3);
		vediolist .addAll(vediolist4);
		List<PersonFileJson> personFileListJsonList=listToJson.listToJson(vediolist);
		return personFileListJsonList;
	}

	@Override
	public List<PersonFileJson> getAllPicture(String userID) {
		List<TFile> picturelist=fileDao.searchByfileExtension(".bmp", userID);
		List<TFile> picturelist1=fileDao.searchByfileExtension(".gif", userID);
		List<TFile> picturelist2=fileDao.searchByfileExtension(".jpg", userID);
		List<TFile> picturelist3=fileDao.searchByfileExtension(".tiff", userID);
		List<TFile> picturelist4=fileDao.searchByfileExtension(".psd", userID);
		List<TFile> picturelist5=fileDao.searchByfileExtension(".png", userID);
		List<TFile> picturelist6=fileDao.searchByfileExtension(".swf", userID);
		List<TFile> picturelist7=fileDao.searchByfileExtension(".svg", userID);
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
		List<TFile> documentlist=fileDao.searchByfileExtension(".pdf", userID);
		List<TFile> documentlist1=fileDao.searchByfileExtension(".doc", userID);
		List<TFile> documentlist2=fileDao.searchByfileExtension(".ppt", userID);
		List<TFile> documentlist3=fileDao.searchByfileExtension(".txt", userID);
		List<TFile> documentlist4=fileDao.searchByfileExtension(".html", userID);
		List<TFile> documentlist5=fileDao.searchByfileExtension(".zip", userID);
		List<TFile> documentlist6=fileDao.searchByfileExtension(".rar", userID);
		documentlist.addAll(documentlist1);
		documentlist.addAll(documentlist2);
		documentlist.addAll(documentlist3);
		documentlist.addAll(documentlist4);
		documentlist.addAll(documentlist5);
		documentlist.addAll(documentlist6);
		List<PersonFileJson> personFileListJsonList=listToJson.listToJson(documentlist);
		return personFileListJsonList;
	}

	@Override
	public List<PersonFileJson> getAllMyRecycle(String userID) {
		TFile recycleFile = fileDao.getRecycleFolderID(userID);
		String recycleID=recycleFile.getFileId();
		List<TFile> myRecyclelist=fileDao.getByFolderID(recycleID);
		List<PersonFileJson> personFileListJsonList=listToJson.listToJson(myRecyclelist);
		return personFileListJsonList;
	}

	@Override
	public String addNewFolder(String userID, String parentFileID,String folderName) {
				TFile folderFile = new TFile();
				folderFile.setFileName(folderName);
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

	@Override
	public String moveFileToRecycle(String userID, String fileID,String fileType) {
		
		TFile recycleFile = fileDao.getRecycleFolderID(userID);
		String recycleID=recycleFile.getFileId();
		moveFile(fileID, recycleID, fileType);
		return "success";
	}

}
