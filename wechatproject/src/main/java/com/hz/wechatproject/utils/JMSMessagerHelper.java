package com.hz.wechatproject.utils;

import org.springframework.beans.factory.annotation.Autowired;

import com.hz.wechatproject.service.jms.ProducerService;

public class JMSMessagerHelper {

	@Autowired
	private static ProducerService producerService;

	public static String sendJMSMessage(String msg) {
		producerService.sendMessage(msg);
		return "test";
	}

}
