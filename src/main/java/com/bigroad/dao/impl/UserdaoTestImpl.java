package com.bigroad.dao.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.bigroad.model.db.TUser;

@Repository("userDao")
public class UserdaoTestImpl{




	public List<TUser> getUserbyDepart(String departmentID) {
		// TODO Auto-generated method stub
		List<TUser> userList =new ArrayList<>();
		TUser user = new TUser();
		user.setUserId("123");
		user.setUserName("刘红华");
		user.setUserTelephone("12345");
		user.setUserMailbox("lhh");
		userList.add(user);
		userList.add(user);
		return userList;
	}

	

}
