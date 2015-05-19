package com.bigroad;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bigroad.dao.impl.FileDaoImplTest;
import com.bigroad.service.personal.PersonFileServiceI;

public class fileMove
{
	private PersonFileServiceI  personfile;
 	Logger log=Logger.getLogger(FileDaoImplTest.class);
 	
    @Before
    public void before(){
        ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"spring.xml","spring-hibernate.xml"});
        personfile = (PersonFileServiceI) ac.getBean("personFile");    
    }
    
    @Test
    public void testMoveFile(){
	    //personfile.moveFile("1001", "1000", "file");
	}
    
    @Test
    public void testMoveFolder(){
	     // personfile.moveFile("2000", "3000", "folder");
	}

}
