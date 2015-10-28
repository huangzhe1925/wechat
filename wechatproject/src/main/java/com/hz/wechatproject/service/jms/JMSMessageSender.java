package com.hz.wechatproject.service.jms;

import javax.jms.Destination;

public interface JMSMessageSender {
	public void sendMessage(Destination destination, final String message);
	public void sendMessage(final String message);
}
