package com.bigroad.service.transaction.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigroad.dao.impl.DepartmentDaoTestImpl;
import com.bigroad.dao.impl.DraftDaoTestImpl;
import com.bigroad.dao.impl.SentTransactionDaoTestImpl;
import com.bigroad.dao.impl.TransactionDaoTestImpl;
import com.bigroad.dao.impl.UserdaoTestImpl;
import com.bigroad.model.db.TDepartment;
import com.bigroad.model.db.TDraft;
import com.bigroad.model.db.TSentTransaction;
import com.bigroad.model.db.TTransaction;
import com.bigroad.model.db.TTransactionMessage;
import com.bigroad.model.db.TUser;
import com.bigroad.model.res.DepartmentInfJson;
import com.bigroad.model.res.TransactionInfJson;
import com.bigroad.model.res.TransactionPageJson;
import com.bigroad.model.res.UserInfJson;
import com.bigroad.service.transaction.TransactionServiceI;

@Service("transactionService")
public class TransactionServiceImpl implements TransactionServiceI {

	@Autowired
	private UserdaoTestImpl userDao;

	@Autowired
	private DepartmentDaoTestImpl departmentDao;

	@Autowired
	private TransactionDaoTestImpl transactionDao;

	@Autowired
	private SentTransactionDaoTestImpl sentTransactionDao;

	@Autowired
	private DraftDaoTestImpl draftDao;

	@Override
	public List<TransactionPageJson> enterTransaction(String userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TransactionPageJson> showReceiveList(int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TransactionInfJson showReceiveInfo(String transactionID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TransactionPageJson> enterSentTransaction(String userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TransactionPageJson> showSentList(int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TransactionInfJson showSentInfo(String transactionID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	// 进入创建事务模块，显示部门联系人
	public List<DepartmentInfJson> createTransaction() {
		List<TDepartment> departmentList = departmentDao.getAllDepart();
		List<DepartmentInfJson> departmentJsonList = new ArrayList<DepartmentInfJson>();
		for (TDepartment d : departmentList) {
			DepartmentInfJson departmentInfJson = new DepartmentInfJson();

			departmentInfJson.setDepartmentName(d.getDepartmentName());

			List<UserInfJson> userInfList = new ArrayList<UserInfJson>();
			List<TUser> userList = userDao.getUserbyDepart(d.getDepartmentId());

			for (TUser u : userList) {
				UserInfJson userInfJson = new UserInfJson();
				userInfJson.setUserID(u.getUserId());
				userInfJson.setUserName(u.getUserName());
				userInfJson.setUserPhone(u.getUserTelephone());
				userInfJson.setUserEmail(u.getUserMailbox());
				userInfList.add(userInfJson);
			}
			departmentInfJson.setUserList(userInfList);

			departmentJsonList.add(departmentInfJson);
		}
		return departmentJsonList;
	}

	@Override
	// 保存并发送事务（保存事务到后台数据库）（PS：先保存到事务表然后保存到发送事务表）
	public boolean sentTransaction(String transactionName,
			String transactionContent, String transactionAttachmentAddress,
			String sentTransactionUserID, List<String> receiveIDList) {
		TTransaction tt = new TTransaction();
		tt.setTransactionName(transactionName);
		tt.setTransactionContent(transactionContent);
		tt.setTransactionAttachmentAddress(transactionAttachmentAddress);
		tt.getTUser().setUserId(sentTransactionUserID);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date time = null;
		try {
			time = sdf.parse(sdf.format(new Date()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		tt.setSentTransactionTime(time);
		String transactionID = transactionDao.saveTTransaction(tt);
		for (String receiveID : receiveIDList) {
			TSentTransaction tst = new TSentTransaction();
			tst.getTUserBySentTransactioncolPersonId().setUserId(
					sentTransactionUserID);
			tst.getTUserByReceiveTransactionPersonId().setUserId(receiveID);
			tst.getTTransaction().setTransactionId(transactionID);
			tst.setSentTransactionTime(time);
			tst.setTransactionName(transactionName);
			tst.setTransactionState(0);
			sentTransactionDao.saveTSentTransaction(tst);
		}
		return true;
	}

	@Override
	// 保存事务到草稿箱（PS：先保存到事务表然后保存到草稿箱）
	public boolean saveToDrafts(String transactionName,
			String transactionContent, String transactionAttachmentAddress,
			String sentTransactionUserID) {
		TTransaction tt = new TTransaction();
		tt.setTransactionName(transactionName);
		tt.setTransactionContent(transactionContent);
		tt.setTransactionAttachmentAddress(transactionAttachmentAddress);
		tt.getTUser().setUserId(sentTransactionUserID);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date time = null;
		try {
			time = sdf.parse(sdf.format(new Date()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		tt.setSentTransactionTime(time);
		String transactionID = transactionDao.saveTTransaction(tt);
		TDraft td = new TDraft();
		td.getTUser().setUserId(sentTransactionUserID);
		td.setDraftTransactionId(transactionID);
		td.setDraftNewTime(time);
		draftDao.saveTDraft(td);
		return true;
	}

	@Override
	public List<TransactionPageJson> enterDraftsList(String userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TransactionPageJson> showDraftsList(int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TransactionInfJson showDraftsInfo(String transactionID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	// 删除事务，同时删除事务附件
	public String deleteTransaction(String transactionID) {
		if (transactionDao.deleteTransactionInfo(transactionID)) {
			return "delete share success";
		} else
			return "delete share fail";
	}

	@Override
	// 审核并关闭发送的事务(办理中：审核，已办理：关闭)
	public boolean closeSentTransaction(String sentTransactionID) {
		sentTransactionDao.updateTSentTransaction(sentTransactionID);
		return true;
	}

	@Override
	public boolean sentTransactionMessage(TTransactionMessage message) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	// 结束办理事务(相当于判断事务的处理进度（发给的所有人都已办理才能结束），当进度达到100%就可以结束办理事务)
	public int finishTransaction(String sentTransactioncolPersonID,
			String transactionID) {
		int progress=sentTransactionDao.getTransactionProgress(sentTransactioncolPersonID, transactionID);
		return progress;
	}

}
