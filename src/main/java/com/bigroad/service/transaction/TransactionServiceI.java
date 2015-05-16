package com.bigroad.service.transaction;

import java.util.List;

import com.bigroad.model.db.TTransactionMessage;
import com.bigroad.model.res.DepartmentInfJson;
import com.bigroad.model.res.TransactionInfJson;
import com.bigroad.model.res.TransactionPageJson;

public interface TransactionServiceI {
	// 进入事务处理模块，显示第一页接收的事务
	public List<TransactionPageJson> enterTransaction(String userID);

	// 分页查询接收的事务
	public List<TransactionPageJson> showReceiveList(int page);

	// 打开接收的事务，显示事务内容、附件和事务留言 
	public TransactionInfJson showReceiveInfo(String transactionID);

	// 进入已发出的事务模块，显示第一页发出的事务
	public List<TransactionPageJson> enterSentTransaction(String userID);

	// 分页查询发出的事务
	public List<TransactionPageJson> showSentList(int page);

	// 打开发出事务的办理详情，显示事务内容、附件、办理详情、事务留言
	public TransactionInfJson showSentInfo(String transactionID);

	// 进入创建事务模块，显示部门联系人
	public List<DepartmentInfJson> createTransaction();

	/*
	 * //保存并发送事务（保存事务到后台数据库） public boolean sentTransaction(TTransaction
	 * transaction,List<String> receiveID); //保存事务到草稿箱 public boolean
	 * saveToDrafts(TTransaction transaction);
	 */

	// 保存并发送事务（保存事务到后台数据库）（PS：先保存到事务表然后保存到发送事务表）
	public boolean sentTransaction(String transactionName,
			String transactionContent, String transactionAttachmentAddress,
			String sentTransactionUserID, List<String> receiveIDList);

	// 保存事务到草稿箱（PS：先保存到事务表然后保存到草稿箱）
	public boolean saveToDrafts(String transactionName,
			String transactionContent, String transactionAttachmentAddress,
			String sentTransactionUserID);

	// 打开草稿箱，显示第一页未发送的事务 
	public List<TransactionPageJson> enterDraftsList(String userID);

	// 分页查询未发送的事务 
	public List<TransactionPageJson> showDraftsList(int page);

	// 打开未发送的事务，编辑事务内容、附件
	public TransactionInfJson showDraftsInfo(String transactionID);

	// 删除事务，同时删除事务附件
	public String deleteTransaction(String transactionID);

	// 审核并关闭发送的事务(办理中：审核，已办理：关闭)
	public boolean closeSentTransaction(String sentTransactionID);

	// 发送事务留言
	public boolean sentTransactionMessage(TTransactionMessage message);

	/*
	 * // 结束办理事务 public boolean finishTransaction(String sentTransactionID);
	 */

	// 结束办理事务(相当于判断事务的处理进度（发给的所有人都已办理才能结束），当进度达到100%就可以结束办理事务)
	public int finishTransaction(String sentTransactioncolPersonID,String transactionID);
	
	// 点击用户名称，弹出私信对话框（先不做）
}
