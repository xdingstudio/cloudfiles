package com.bigroad.dao.impl;

import org.springframework.stereotype.Repository;

import com.bigroad.model.db.TTransaction;

@Repository("transactionDao")
public class TransactionDaoTestImpl {
	// 保存事务信息，返回transactionID
	public String saveTTransaction(TTransaction tt) {
		return null;
	}
	//删除事务信息
	public boolean deleteTransactionInfo(String transactionID){
		return false;
	}
}
