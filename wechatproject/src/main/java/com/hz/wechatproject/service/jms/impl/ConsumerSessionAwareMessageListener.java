package com.hz.wechatproject.service.jms.impl;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.command.ActiveMQObjectMessage;
import org.apache.log4j.Logger;
import org.htmlparser.util.ParserException;
import org.springframework.jms.listener.SessionAwareMessageListener;

import com.hz.wechatproject.service.jms.pojo.JMSMessageObject;
import com.hz.wechatproject.utils.CommonUtil;


public class ConsumerSessionAwareMessageListener implements SessionAwareMessageListener<ActiveMQObjectMessage> {

	private static Logger logger = Logger.getLogger(ConsumerSessionAwareMessageListener.class);

	private Destination toDestination;

	public void onMessage(TextMessage message, Session session)
			throws JMSException {
		
	}

	public Destination getToDestination() {
		return toDestination;
	}

	public void setToDestination(Destination toDestination) {
		this.toDestination = toDestination;
	}

	@Override
	public void onMessage(ActiveMQObjectMessage message, Session session)
			throws JMSException {
		String reviString=((JMSMessageObject)message.getObject()).getStringMessage();
		logger.debug("收到一条消息到ConsumerSessionAwareMessageListener");
		logger.debug("消息内容是：" + reviString);
//		MessageProducer producer = session.createProducer(toDestination);
//		Message textMessage = session.createTextMessage("ConsumerSessionAwareMessageListener收到消息..."+ );
//		producer.send(textMessage);
		
		try {
			CommonUtil.HTMLParseUtil.getContentOnClass(CommonUtil.HttpClientUtil.get("http://www.dytt8.net/"), "co_content8");
		} catch (ParserException e) {
			logger.error("error when parser",e);
		}
		
	}
}