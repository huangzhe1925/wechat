package com.hz.wechatproject.service.jms.impl;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import com.hz.wechatproject.service.jms.pojo.JMSMessageObject;


@Component("serverMessageCreator")
public class ServerMessageCreator implements MessageCreator {
	
	private JMSMessageObject message;
	
	public Message createMessage(Session session) throws JMSException {
		Message result=session.createObjectMessage(message);
		return result;
	}

	public JMSMessageObject getMessage() {
		return message;
	}

	public void setMessage(JMSMessageObject message) {
		this.message = message;
	}


}
