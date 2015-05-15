package com.bigroad.service.user.impl;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import com.bigroad.dao.impl.FileDaoImplTest;
import com.bigroad.service.user.TokenServiceI;

public class TokenServiceImplTest {

	private TokenServiceI tos;
 	Logger log=Logger.getLogger(FileDaoImplTest.class);
 	
    @Before
    public void before(){
        ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"spring.xml","spring-hibernate.xml"});
        tos = (TokenServiceI) ac.getBean("tokenService");
    }
	@Test
	public void testSaveAuth() {
		tos.saveAuth("123312");
	}

	@Test
	public void testUpdateAuthToken() {
		
	}

	@Test
	public void testGetTokenById() {
		
	}

	@Test
	public void testDeleteById() {
		
	}

}
