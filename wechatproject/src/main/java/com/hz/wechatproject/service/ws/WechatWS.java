package com.hz.wechatproject.service.ws;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService()
public interface WechatWS {
	@WebMethod
	public @WebResult String getLatestMovie();
}