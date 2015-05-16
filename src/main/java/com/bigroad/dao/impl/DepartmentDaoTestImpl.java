package com.bigroad.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.bigroad.model.db.TDepartment;



@Repository("departmentDao")
public class DepartmentDaoTestImpl {

	
	public List<TDepartment> getAllDepart() {
		// TODO Auto-generated method stub
		List<TDepartment> List =new ArrayList<>();
		TDepartment td = new TDepartment();
		td.setDepartmentId("123");
		td.setDepartmentName("技术部");	
		List.add(td);
		TDepartment td1 = new TDepartment();
		td1.setDepartmentId("12");
		td1.setDepartmentName("测试部");
		List.add(td1);
		return List;
	}

}
