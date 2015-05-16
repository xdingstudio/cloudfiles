package com.bigroad.dao.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bigroad.dao.UserDaoI;
import com.bigroad.model.db.TFile;
import com.bigroad.model.db.TUser;




public class UserDaoImplTest {

	Logger log=Logger.getLogger(UserDaoImpl.class);
	UserDaoI userdao;
	@Before
	public void before(){
        ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"spring.xml","spring-hibernate.xml"});
        userdao = (UserDaoI) ac.getBean("userDao");
	}
	@Test
	public final void testGetUser() {
		;
	}

	@Test
	public final void testSaveUser() {
		
	}

	@Test
	public final void testGetUserbyDepart() {
		List<TUser> list=userdao.getUserbyDepart("");
		if(list.equals(null))
			log.info("user:123 recycle error!");
		else{
			log.info("user:123 recycle success!!!");
			for(TUser f:list){
				log.info("username:"+f.getUserName());
			}
		}
	}

}
