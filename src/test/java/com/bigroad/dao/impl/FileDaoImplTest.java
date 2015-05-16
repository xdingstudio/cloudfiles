package com.bigroad.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bigroad.dao.FileDaoI;
import com.bigroad.model.db.TFile;
import com.bigroad.service.user.UserServiceI;


public class FileDaoImplTest {

	 	private FileDaoI filedao;
	 	Logger log=Logger.getLogger(FileDaoImplTest.class);
	 	
	    @Before
	    public void before(){
	        ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"spring.xml","spring-hibernate.xml"});
	        filedao = (FileDaoI) ac.getBean("fileDao");
	    }
	    @Test
	    public void testGetrecycle(){
			List<TFile> list=filedao.getRecycle("123");
			if(list.equals(null))
				log.info("user:123 recycle error!");
			else{
				log.info("user:123 recycle success!!!");
				for(TFile f:list){
					log.info("fileID:"+f.getFileId()+"..fileName:"+f.getFileName());
				}
			}
		}
		@Test
		public void testGetByFolderID(){
			List<TFile> list=filedao.getByFolderID("134141");
			if(list.equals(null))
				log.info("'134141' folderID error!");
			else{
				log.info("'134141' folderID success!!!");
				for(TFile f:list){
					log.info("fileID:"+f.getFileId()+"..fileName:"+f.getFileName());
			    	}
			}
		}
	    
		@Test
		public void testSearchByfileExtension(){
			List<TFile> list =filedao.searchByfileExtension("xml", "123");
			if(list.equals(null))
				log.info("'user:123,type:xml' folderID error!");
				else{
					log.info("'user:123,type:xml' folderID success!!!");
					for(TFile f:list){
						log.info("fileID:"+f.getFileId()+"..fileName:"+f.getFileName());
		    		}
		    	}
		}
	    @Test
	    public void testGetRootFolderID(){
	    	String rootid=filedao.getRootFolderID("123").getFileId();
	    	if(rootid.equals(null))
	    		log.info("'123' getRootFolderID error!");
	    	else{
	    		log.info("'123' getRootFolderID success!!!");
		    	log.info(rootid);
		    	}
		  }
	    @Test
	    public void testgetAllChildMethod(){
	    	List<TFile> l=filedao.getAllChildFile("1111");
	    	for (TFile file : l){
	    		System.out.println(file.getFileId());
	    	}
	    }
	    


    }

