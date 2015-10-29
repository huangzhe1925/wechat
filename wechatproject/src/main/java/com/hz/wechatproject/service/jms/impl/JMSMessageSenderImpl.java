package com.hz.wechatproject.service.jms.impl;

import javax.jms.Destination;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.hz.wechatproject.service.jms.JMSMessageSender;
import com.hz.wechatproject.service.jms.pojo.JMSMessageObject;

@Component("JMSProducer")
public class JMSMessageSenderImpl implements JMSMessageSender {

	private static Logger logger = Logger.getLogger(JMSMessageSenderImpl.class);

	@Autowired
	@Qualifier("jmsTemplate")
	private JmsTemplate jmsTemplate;

	@Autowired
	@Qualifier("topicDestination")
	private Destination destination;
	
	@Autowired
	@Qualifier("serverMessageCreator")
	private ServerMessageCreator serverMessageCreator;

	public void sendMessage(Destination destination, final String message) {
		logger.debug("---------------Server发送消息-----------------");
		logger.debug("---------------Server发了一个消息：" + message);
		serverMessageCreator.setMessage(new JMSMessageObject(message));
		try{
			jmsTemplate.send(destination,serverMessageCreator );
		}catch(Exception ex){
			logger.error("Error when Server sending JMS message",ex);
		}
	}

	public void sendMessage(final String msg) {
		sendMessage(this.destination, msg);
	}

	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public Destination getDestination() {
		return destination;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}

}
