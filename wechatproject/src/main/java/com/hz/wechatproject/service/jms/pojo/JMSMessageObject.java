package com.hz.wechatproject.service.jms.pojo;

import java.io.Serializable;

public class JMSMessageObject implements Serializable{

	private static final long serialVersionUID = 8564428672283886694L;
	
	
	public JMSMessageObject() {
	}
	
	public JMSMessageObject(String stringMessage) {
		this.stringMessage = stringMessage;
	}
	
	private String stringMessage;


	public String getStringMessage() {
		return stringMessage;
	}


	public void setStringMessage(String stringMessage) {
		this.stringMessage = stringMessage;
	}
	
	
	
}
