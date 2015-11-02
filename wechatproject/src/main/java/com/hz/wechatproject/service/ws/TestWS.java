package com.hz.wechatproject.service.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService()
public interface TestWS {
	@WebMethod
	public  @WebResult String sayHello(@WebParam String foo);
}