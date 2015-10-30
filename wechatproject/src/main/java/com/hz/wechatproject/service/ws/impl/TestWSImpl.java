package com.hz.wechatproject.service.ws.impl;

import javax.jws.WebService;

import com.hz.wechatproject.service.ws.TestWS;

@WebService(endpointInterface="com.hz.wechatproject.service.ws.TestWS")  
public class TestWSImpl implements TestWS {

	public String sayHello(String foo) {
		return "你好 " + foo;
	}

}