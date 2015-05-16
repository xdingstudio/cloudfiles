package com.bigroad.model.res;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class TransactionListJson
{
	private String serialNumber;//流水号
	private String	transactionID;//事务ID
	private String transactionTitle;//事务标题
	private String sentUserName;//发送人姓名
	private Date  sentTime;//发送时间
	private int transactionState;//事务状态
	
	public String getSerialNumber( )
	{
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber )
	{
		this.serialNumber = serialNumber;
	}
	public String getTransactionTitle( )
	{
		return transactionTitle;
	}
	public void setTransactionTitle(String transactionTitle )
	{
		this.transactionTitle = transactionTitle;
	}
	public String getSentUserName( )
	{
		return sentUserName;
	}
	public void setSentUserName(String sentUserName )
	{
		this.sentUserName = sentUserName;
	}
	public Date getSentTime( )
	{
		return sentTime;
	}
	public void setSentTime(Date sentTime )
	{
		this.sentTime = sentTime;
	}
	public String getTransactionID( )
	{
		return transactionID;
	}
	public void setTransactionID(String transactionID )
	{
		this.transactionID = transactionID;
	}
	public int getTransactionState( )
	{
		return transactionState;
	}
	public void setTransactionState(int transactionState )
	{
		this.transactionState = transactionState;
	}

}
