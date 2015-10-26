package com.hz.wechatproject.service.jms;

public interface JMSMessageSender {
	
	public Object SendMessageObject(Object obj);
	
	public String sendMessageString(String str);
}
