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
	    	personFileJson.setFileName(file.getFileName());
	    	personFileJson.setFileExtension(file.getFileExtension());	
	    	
            personFileListJsonList.add(personFileJson);
	    }
	    return personFileListJsonList;
	}
}