package com.bigroad.model.res;

import java.util.ArrayList;
import java.util.List;
import com.bigroad.model.db.TFile;

public class filelistConvertToPersonFileJson {

	public List<PersonFileJson> listToJson(List<TFile> filelist) {
		
		List<PersonFileJson> personFileListJsonList=new ArrayList<PersonFileJson>();
	    for(TFile file : filelist) {
	    	PersonFileJson personFileJson=new PersonFileJson();
	    	personFileJson.setFileType(file.getFileType());
	    	personFileJson.setFileId(file.getFileId());
	    	personFileJson.setFolderId(file.getTFile().getFileId());
	    	personFileJson.setUserId(file.getTUser().getUserId());
	    	personFileJson.setFileName(file.getFileName());
	    	personFileJson.setFileNewTime(file.getFileNewTime());	
	    	personFileJson.setFileSize(file.getFileSize());
	    	personFileJson.setFileExtension(file.getFileExtension());
	    	personFileJson.setFileStoragePath(file.getFileStoragePath());
            personFileListJsonList.add(personFileJson);
	    }
	    return personFileListJsonList;
	}
}
