package com.bigroad.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.metamodel.domain.Superclass;
import org.springframework.stereotype.Repository;

import com.bigroad.dao.UserDaoI;
import com.bigroad.model.db.TFile;
import com.bigroad.model.db.TUser;

@Repository("userDao")
public   class UserDaoImpl extends BaseDaoImpl<TUser> implements UserDaoI{
	
	 
   
	@Override
	public TUser getUser(String loginInf) {
		// TODO Auto-generated method stub
		String hqlString ="from TUser u where u.userTelephone=:phone or u.userMailbox=:mail ";
		Map<String,Object> map =new HashMap<String, Object>(); 
		map.put("phone", loginInf);
		map.put("mail", loginInf);
		TUser user = super.get(hqlString, map);
		return user;
	}

	@Override
	public void saveUser(TUser user) {
		// TODO Auto-generated method stub
		super.save(user);	
		
	}

	@Override
	public List<TUser> getUserbyDepart(String departmentID) {
		// TODO Auto-generated method stub
		String hqlstring="select TUsers from TDepartment d where d.departmentId='"+departmentID+"'";
		List<TUser> filelist=super.find(hqlstring);
		return filelist;
	}

	
	@Override
	public TUser getUserbyID(String userID) {
		// TODO Auto-generated method stub
		String hqlString ="from TUser u where u.userId=:userId";
		Map<String,Object> map =new HashMap<String, Object>(); 
		map.put("userId", userID);
		TUser user = super.get(hqlString, map);
		return user;
	}

    
	    
 
}
