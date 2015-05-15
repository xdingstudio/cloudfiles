package com.bigroad.util;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
@Repository("timeTask")
@Component  //import org.springframework.stereotype.Component;  
public class MyTestService {
	  @Scheduled(cron="0/5 * *  * * ? ")   //每5秒执行一次
     
      public void myTest(){
            System.out.println("进入测试");
      }

}
