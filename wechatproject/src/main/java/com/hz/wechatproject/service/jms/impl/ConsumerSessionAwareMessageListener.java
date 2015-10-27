package com.hz.wechatproject.service.jms.impl;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.springframework.jms.listener.SessionAwareMessageListener;


public class ConsumerSessionAwareMessageListener implements SessionAwareMessageListener<TextMessage> {

	private static Logger logger = Logger.getLogger(ConsumerSessionAwareMessageListener.class);

	private Destination toDestination;

	public void onMessage(TextMessage message, Session session)
			throws JMSException {
		logger.debug("收到一条消息到ConsumerSessionAwareMessageListener");
		logger.debug("消息内容是：" + message.getText());
		MessageProducer producer = session.createProducer(toDestination);
		Message textMessage = session.createTextMessage("ConsumerSessionAwareMessageListener收到消息..."+ message.getText());
		producer.send(textMessage);
	}

	public Destination getToDestination() {
		return toDestination;
	}

	public void setToDestination(Destination toDestination) {
		this.toDestination = toDestination;
	}
}