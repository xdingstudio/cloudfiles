package com.bigroad.dao;

import com.bigroad.model.db.TSentTransaction;

public interface SentTransactionDaoI {
	// 保存发送事务信息
	public boolean saveTSentTransaction(TSentTransaction tst);
	
	//通过sentTransactionID,更新事务状态transactionState（0 未接收 1 办理中 2  已办理）
	public boolean updateTSentTransaction(String sentTransactionID);
	
	//通过 sentTransactionPersonID以及transactionID统计进度信息(通过transactionState查看已办理的占的比重)
	public int getTransactionProgress(String sentTransactioncolPersonID,String transactionID);
	
}
