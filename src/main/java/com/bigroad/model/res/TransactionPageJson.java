package com.bigroad.model.res;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.bigroad.model.db.TSentTransaction;


@XmlRootElement
public class TransactionPageJson
{
	List<TSentTransaction> TransactionList;
	int pageNo;
	int pageSize;
	
	public List<TSentTransaction> getTransactionList( )
	{
		return TransactionList;
	}
	public void setTransactionList(List<TSentTransaction> transactionList )
	{
		TransactionList = transactionList;
	}
	public int getPageNo( )
	{
		return pageNo;
	}
	public void setPageNo(int pageNo )
	{
		this.pageNo = pageNo;
	}
	public int getPageSize( )
	{
		return pageSize;
	}
	public void setPageSize(int pageSize )
	{
		this.pageSize = pageSize;
	}

}
