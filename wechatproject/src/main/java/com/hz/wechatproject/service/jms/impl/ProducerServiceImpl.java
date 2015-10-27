package com.hz.wechatproject.service.jms.impl;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import com.hz.wechatproject.service.jms.ProducerService;

@Component
public class ProducerServiceImpl implements ProducerService {

	private static Logger logger = Logger.getLogger(ProducerServiceImpl.class);

	@Autowired
	@Qualifier("jmsTemplate")
	private JmsTemplate jmsTemplate;

	@Autowired
	@Qualifier("queueDestination")
	public Destination destination;

	public void sendMessage(Destination destination, final String message) {
		logger.debug("---------------生产者发送消息-----------------");
		logger.debug("---------------生产者发了一个消息：" + message);
		jmsTemplate.send(destination, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message);
			}
		});
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
