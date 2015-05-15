package com.bigroad.service.user.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigroad.dao.UserDaoI;
import com.bigroad.model.db.TUser;
import com.bigroad.service.user.UserServiceI;


@Service("userService")
public class UserServiceImpl implements UserServiceI {

	 /**
     * 注入userDao
     */
    @Autowired
    private UserDaoI userDao;

	@Override
	public String getLogin(String logininf, String passwd) {
		// TODO Auto-generated method stub
        		TUser user =userDao.getUser(logininf);
        		if (user==null)
        			return "nouser";
        		if(!user.getUserPassword().equals(passwd))
        			return "passwderror";
        		else
        			return user.getUserId();
	}

	@Override
	public void saveUserInf(TUser user) {
		// TODO Auto-generated method stub
		userDao.save(user);
		
	}
    

}
