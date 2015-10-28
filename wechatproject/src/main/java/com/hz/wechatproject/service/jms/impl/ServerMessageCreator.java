package com.hz.wechatproject.service.jms.impl;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import com.hz.wechatproject.utils.CommonUtil;


@Component("serverMessageCreator")
public class ServerMessageCreator implements MessageCreator {
	
	private String message;
	
	public Message createMessage(Session session) throws JMSException {
		return session.createTextMessage(this.getMessage());
	}

	public String getMessage() {
		return CommonUtil.getEmptyString(message);
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
