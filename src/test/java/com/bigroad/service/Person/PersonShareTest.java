package com.bigroad.service.Person;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bigroad.model.res.DepartmentInfJson;
import com.bigroad.service.personal.PersonFileShareI;

public class PersonShareTest {

	private PersonFileShareI share;

	@Before
	public void before() {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				new String[] { "spring.xml" });
		share = (PersonFileShareI) ac.getBean("personFileShare");
	}

	@Test
	public void testShare() {
		List<DepartmentInfJson> departmentInfList = share.getAllUserInf();
		for (DepartmentInfJson d : departmentInfList) {
			System.out.println(d.getDepartmentName());
		}

	}

	@Test
	public void t() {
		String time = "20150502101545";
		SimpleDateFormat formatter1 = new SimpleDateFormat(
				"yyyy-HH-dd HH:mm:ss");
		SimpleDateFormat formatter2 = new SimpleDateFormat("yyyyHHddHHmmss");
		System.out.println(time);
		try {
			time = formatter1.format(formatter2.parse(time));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(time);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date time1 = null;
		try {
			time1 = sdf.parse(sdf.format(new Date()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(time1);
	}
}
