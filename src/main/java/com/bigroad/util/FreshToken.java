package com.bigroad.util;

import java.util.Timer;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
	


public class FreshToken implements ServletContextListener {
	
	private static Timer timer;
	//private static DeleteImageTask task=new JinTask();


	public void contextDestroyed(ServletContextEvent arg0) {

	}


	public void contextInitialized(ServletContextEvent arg0) {
	// logger.debug("调用contextInitialized方法");
     	try {

	        timer=new Timer(true);
			timer = new Timer();
			timer.schedule(new JinTask(), 2000, 10000);
			}
			catch(Exception e){
				System.out.println("yichang");
			}
	}

}