package com.bigroad.model.res;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DepartmentInfJson {
      private String departmentName ;
      private List<UserInfJson> userList;
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public List<UserInfJson> getUserList() {
		return userList;
	}
	public void setUserList(List<UserInfJson> userList) {
		this.userList = userList;
	}
       
	
}
