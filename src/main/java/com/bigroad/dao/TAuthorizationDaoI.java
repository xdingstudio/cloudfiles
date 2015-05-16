package com.bigroad.dao;

import java.util.Date;

import com.bigroad.model.db.TAuthorization;
import com.bigroad.model.db.TUser;

public interface TAuthorizationDaoI extends BaseDAOI<TAuthorization>  {
	//插入
	public boolean saveAuth(TAuthorization T);
	//活动时更新
	public boolean updateAuthToken(TAuthorization T);
	//获取token进行验证
	public String getTokenById(String userId);
	//退出时删除
	public boolean deleteById(String userId);
}
