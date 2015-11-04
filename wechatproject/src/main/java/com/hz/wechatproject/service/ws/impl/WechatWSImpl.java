package com.hz.wechatproject.service.ws.impl;

import javax.jws.WebService;

import org.htmlparser.util.ParserException;

import com.hz.wechatproject.service.ws.WechatWS;
import com.hz.wechatproject.utils.CommonUtil;


@WebService(endpointInterface="com.hz.wechatproject.service.ws.WechatWS")  
public class WechatWSImpl implements WechatWS{

	@Override
	public String getLatestMovie() {
		String result="";
		result=CommonUtil.UtilHttpClient.get("http://www.dytt8.net/");
		try {
			String html=CommonUtil.UtilHttpClient.get("http://www.dytt8.net/");
			result=CommonUtil.UtilHTMLParse.getNodeAsString(CommonUtil.UtilHTMLParse.getNodeListOnClass(html,"div", "co_content8"), 0);
		} catch (ParserException e) {
			e.printStackTrace();
		}
		return result;
	}

}
