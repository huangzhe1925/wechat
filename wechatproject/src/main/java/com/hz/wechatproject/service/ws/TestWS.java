package com.hz.wechatproject.service.ws;

import javax.jws.WebService;

@WebService()
public interface TestWS {
	public String sayHello(String foo);
}