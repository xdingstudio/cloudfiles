package com.bigroad.service.personal;

import java.util.List;

import com.bigroad.model.db.TFile;
import com.bigroad.model.res.PersonFileJson;


public interface PersonFileServiceI {
	
	List<PersonFileJson> getAllPersonFiles(String userID);
	List<PersonFileJson> getchidFiles(String fileID);
    List<PersonFileJson> getAllMusic(String userID);
    List<PersonFileJson> getAllVedio(String userID);
    List<PersonFileJson> getAllPicture(String userID);
    List<PersonFileJson> getAllDocument(String userID);
    List<PersonFileJson> getAllMyRecycle (String userID);

    String  addNewFolder(String userID ,String parentFileID ,String folderName);
    String  deleteFile(String fileID,String fileType);
    String  moveFile(String fileID,String targetFolderID,String fileType);
    String  updateFileName(String fileID,String fileType,String fileNewName);
    
   

}
