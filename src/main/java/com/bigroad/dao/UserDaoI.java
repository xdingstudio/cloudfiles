package com.bigroad.dao;

import java.util.List;

import com.bigroad.model.db.TFile;
import com.bigroad.model.db.TUser;
public interface UserDaoI extends BaseDAOI<TUser> {
	    
        public TUser getUser(String loginInf);
         
        public void saveUser(TUser user);
        
        public List<TUser> getUserbyDepart(String departmentID);
        public TUser getUserbyID(String userID);
 
}
