package com.bigroad.dao.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transaction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import com.bigroad.model.db.TAuthorization;

public class TokenTimerUpDaoImpl{

	
	
	
	 private SessionFactory sessionFactory;
	 public SessionFactory getSessionFactory() {
         return sessionFactory;
     }
     @Autowired
     public void setSessionFactory(SessionFactory sessionFactory) {
            this.sessionFactory = sessionFactory;
     }
     private Session getCurrentSession() {
            return this.sessionFactory.openSession();
     }
	
	public boolean updateList() {
	     
	     Session s=getCurrentSession();
		long now =new Date().getTime()/1000;
		System.out.println(now);
		
		
		String sql="from TAuthorization t";
		org.hibernate.Transaction t=null;
		 List<TAuthorization> list=null;
		   try{  
	            //准备数据  
	            t = s.beginTransaction();  
	            list=s.createQuery(sql).list();
	            t.commit();  
	       }catch(Exception err){  
	            t.rollback();  
	            err.printStackTrace();  
	      }finally{  
	         
	      }
		
	//	List<TAuthorization> list=getList();
		if(list==null)
		{
			System.out.println("111111111111");
			return true;
		}
		for (TAuthorization  l : list) {
			if((now-l.getUpdateDate().getTime()/1000)>3600)
			{
				   try{  
			            //准备数据  
			            t = s.beginTransaction();  
			            s.delete(l);
			            t.commit();  
			       }catch(Exception err){  
			            t.rollback();  
			            err.printStackTrace();  
			      }finally{  
			         
			      }
			}
		}
		s.close();
		return true;
	}
	
	public List<TAuthorization> getList(){
	

		return null;
	}

}
