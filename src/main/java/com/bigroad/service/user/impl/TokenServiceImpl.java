package com.bigroad.service.user.impl;


import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigroad.dao.TAuthorizationDaoI;
import com.bigroad.model.db.TAuthorization;
import com.bigroad.service.user.TokenServiceI;
import com.bigroad.util.FreshToken;
import com.bigroad.util.TokenProcessor;

@Service("tokenService")
public class TokenServiceImpl implements TokenServiceI {

	@Autowired
	    private TAuthorizationDaoI tauth;
	@Override
	public boolean saveAuth(String UserId) {
		String yun=tauth.getTokenById(UserId);
		if(yun==null){
			String token =new TokenProcessor().generateToken(UserId,true);
			TAuthorization t=new TAuthorization();		
			t.setUserId(UserId);
			t.setUpdateDate(new Date());
			t.setToken(token);
			tauth.save(t);
		}
		return true;
	}

	@Override
	public boolean updateAuthToken(String UserId) {
		String token =new TokenProcessor().generateToken(UserId,true);
		TAuthorization t=new TAuthorization();
		t.setUserId(UserId);
		t.setUpdateDate(new Date());
		t.setToken(token);
		tauth.updateAuthToken(t);
		return true;
	}

	@Override
	public String getTokenById(String userId) {
		return tauth.getTokenById(userId);
	}

	@Override
	public boolean deleteById(String userId) {
		// TODO Auto-generated method stub
		return tauth.deleteById(userId);
	}

	
}
