package com.bigroad.dao.impl;

import org.springframework.stereotype.Repository;

import com.bigroad.model.db.TDraft;

@Repository("draftDao")
public class DraftDaoTestImpl {
	// 保存事务信息到草稿箱
	public boolean saveTDraft(TDraft td) {
		return false;
	}
}
