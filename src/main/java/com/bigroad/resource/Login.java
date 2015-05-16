package com.bigroad.resource;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bigroad.service.user.TokenServiceI;
import com.bigroad.service.user.UserServiceI;



@Component
@Path("/login") 

public class Login {
	  private static final Logger LOGGER = Logger.getLogger(Login.class);
	    @Autowired
        UserServiceI userService;
	    
	    @Autowired
	    TokenServiceI tokenService;
	    
	    
	    @POST
	    public Response login(
	    		@FormParam("logininf") String logininf,
	    		@FormParam("passwd") String passwd
	    		) throws IOException{
			
	       String userID=userService.getLogin(logininf.trim(), passwd.trim());
	     
	       System.out.println(logininf+"......."+passwd);
		   if (userID.equals("nouser")){
			     return Response
						 .ok()
						 .header("X-Auth-Token", "nouser")
						 .build();
		 	}
		   if (userID.equals("passwderror")){
			   return Response
						 .ok()
						 .header("X-Auth-Token", "passwderror")
						 .build();
		   }	   
			 else {
				 tokenService.saveAuth(userID);
				 String token=tokenService.getTokenById(userID);
				 return Response
						 .ok()
						 .header("X-Auth-Token", token)
						 .header("X-Auth-ID",  userID)
						 .build();
			 	
			}
	    
		}
	  

    
}
