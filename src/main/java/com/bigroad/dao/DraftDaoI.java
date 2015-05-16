package com.bigroad.dao;

import com.bigroad.model.db.TDraft;

public interface DraftDaoI {
	// 保存事务信息到草稿箱
	public boolean saveTDraft(TDraft td);
}
