package com.bigroad.dao.impl;

import java.util.List;

import com.bigroad.dao.DepartmentDaoI;
import com.bigroad.dao.impl.BaseDaoImpl;
import com.bigroad.model.db.TDepartment;

public class DepartmentDaoImpl extends BaseDaoImpl<TDepartment>implements DepartmentDaoI {

	@Override
	public List<TDepartment> getAllDepart() {
		// TODO Auto-generated method stub
		String hsqlstring ="from TDepartment";
		List<TDepartment> list =super.find(hsqlstring);
		return list;
	}

}
