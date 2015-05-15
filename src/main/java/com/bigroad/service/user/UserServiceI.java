package com.bigroad.service.user;

import java.io.Serializable;

import javax.print.DocFlavor.STRING;

import antlr.Token;

import com.bigroad.model.db.TUser;
import com.bigroad.resource.Login;

public interface UserServiceI {
	
   public String  getLogin(String logininf,String passwd);
   
   public void saveUserInf(TUser user);


}
