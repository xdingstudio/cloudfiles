package com.bigroad.service.user;

import java.util.Date;

public interface TokenServiceI {
	//插入
	public boolean saveAuth(String UserId);
	//活动时更新
	public boolean updateAuthToken(String UserId);
	//获取token进行验证
	public String getTokenById(String userId);
	//退出时删除
	public boolean deleteById(String userId);

}
