package com.bigroad;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bigroad.dao.impl.FileDaoImplTest;
import com.bigroad.model.db.TFile;
import com.bigroad.service.personal.PersonFileServiceI;

public class folderSave {

 	private PersonFileServiceI  personfile;
 	Logger log=Logger.getLogger(FileDaoImplTest.class);
 	
    @Before
    public void before(){
        ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"spring.xml","spring-hibernate.xml"});
        personfile = (PersonFileServiceI) ac.getBean("personFile");    
    }
    
    @Test
    public void testSave(){
	      personfile.addNewFolder("123", "1111", "话花花");
	      
	}
}
