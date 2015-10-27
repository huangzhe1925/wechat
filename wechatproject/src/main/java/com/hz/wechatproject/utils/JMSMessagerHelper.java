package com.hz.wechatproject.utils;

import javax.annotation.Resource;

import com.hz.wechatproject.service.jms.impl.ProducerServiceImpl;

public class JMSMessagerHelper {

	private JMSMessagerHelper() {
		this.producerService=new ProducerServiceImpl();
	}

	private static JMSMessagerHelper instance;

	public static JMSMessagerHelper getInst() {
		if (instance == null) {
			synchronized (JMSMessagerHelper.class) {
				if (instance == null) {
					instance = new JMSMessagerHelper();
					
				}
			}
		}
		return instance;
	}

	@Resource(name="JMSProducer")
	private ProducerServiceImpl producerService;

	public String sendJMSMessage(String msg) {
		producerService.sendMessage(msg);
		return "test";
	}

	public ProducerServiceImpl getProducerService() {
		return producerService;
	}

	public void setProducerService(ProducerServiceImpl producerService) {
		this.producerService = producerService;
	}

}
