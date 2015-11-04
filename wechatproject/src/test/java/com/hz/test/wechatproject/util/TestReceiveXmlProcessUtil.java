package com.hz.test.wechatproject.util;

import com.hz.wechatproject.pojo.ReceiveXmlEntity;
import com.hz.wechatproject.utils.ReceiveXmlProcessUtil;



public class TestReceiveXmlProcessUtil {

	public static void main(String args[]){
		StringBuilder sb=new StringBuilder();
		sb.append("<xml><ToUserName><![CDATA[toUser]]></ToUserName>");
		sb.append("<FromUserName><![CDATA[fromUser]]></FromUserName>");
		sb.append("<CreateTime>12345678</CreateTime>");
		sb.append("<MsgType><![CDATA[text]]></MsgType>");
		sb.append("<Content><![CDATA[你好]]></Content></xml>");
		
		
		ReceiveXmlEntity en=ReceiveXmlProcessUtil.getMsgEntity(sb.toString());
		System.out.println(en);
		
	}
}
