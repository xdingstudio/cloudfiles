package com.bigroad.dao;

import com.bigroad.model.db.TTransaction;

public interface TransactionDaoI {
	    //保存事务信息，返回transactionID
		public String saveTTransaction(TTransaction tt);
		//删除事务信息
		public boolean deleteTransactionInfo(String transactionID);
}
