package com.bigroad.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.bigroad.dao.TAuthorizationDaoI;
import com.bigroad.model.db.TAuthorization;

@Repository("TAuthorizationDao")
public class TAuthorizationDaoImpl extends BaseDaoImpl<TAuthorization> implements TAuthorizationDaoI {

	@Override
	public boolean saveAuth(TAuthorization T) {
		// TODO Auto-generated method stub
		super.save(T);
		return true;
	}

	@Override
	public boolean updateAuthToken(TAuthorization T) {
		// TODO Auto-generated method stub
		super.update(T);
		return true;
	}

	@Override
	public String getTokenById(String userId) {
		// TODO Auto-generated method stub
		String sql="from TAuthorization t where t.userId='"+userId+"'";
		TAuthorization t=super.get(sql);
		if(t==null)
			return null;
		String token=t.getToken();
		return token;
	}

	@Override
	public boolean deleteById(String userId) {
		// TODO Auto-generated method stub
		String sql="delete TAuthorization t where t.userId=:userId";
		Map<String,Object> map =new HashMap<String, Object>(); 
		map.put("userId", userId);
		super.deleteById(sql, map);
		return true;
	}

	
}
