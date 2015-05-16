package com.bigroad.model.res;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.bigroad.model.db.TPrivateMessage;
import com.bigroad.model.db.TTransactionMessage;

@XmlRootElement
public class TransactionInfJson
{
	private String transactionTitle;//事务标题
	private List<String> receiveIDList;//事务接收人ID
	private String attachmentFileID;//附件ID
	private String transactionContent;//事务内容
	private List<TTransactionMessage> MessageList;//事务留言
	
	public String getTransactionTitle( )
	{
		return transactionTitle;
	}
	public void setTransactionTitle(String transactionTitle )
	{
		this.transactionTitle = transactionTitle;
	}
	public List<String> getReceiveIDList( )
	{
		return receiveIDList;
	}
	public void setReceiveIDList(List<String> receiveIDList )
	{
		this.receiveIDList = receiveIDList;
	}
	public String getAttachmentFileID( )
	{
		return attachmentFileID;
	}
	public void setAttachmentFileID(String attachmentFileID )
	{
		this.attachmentFileID = attachmentFileID;
	}
	public String getTransactionContent( )
	{
		return transactionContent;
	}
	public void setTransactionContent(String transactionContent )
	{
		this.transactionContent = transactionContent;
	}
	public List<TTransactionMessage> getMessageList( )
	{
		return MessageList;
	}
	public void setMessageList(List<TTransactionMessage> messageList )
	{
		MessageList = messageList;
	}

}
