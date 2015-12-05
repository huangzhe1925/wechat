package com.hz.wechatproject.utils;

import java.util.Date;

import org.apache.log4j.Logger;

import com.hz.wechatproject.pojo.ReceiveXmlEntity;

public class FormatXmlProcessUtil {
	
	private static Logger logger = Logger.getLogger(FormatXmlProcessUtil.class);
	
    public static String formatTextAnswer(ReceiveXmlEntity xmlEntity, String content) {
    	if (null == xmlEntity) {
			return "";
		}
		logger.debug(xmlEntity.toString());
		String toUser=xmlEntity.getFromUserName();
		String fromUser=xmlEntity.getToUserName();

        StringBuffer sb = new StringBuffer();  
        Date date = new Date();  
        sb.append("<xml><ToUserName><![CDATA[");  
        sb.append(fromUser);  
        sb.append("]]></ToUserName><FromUserName><![CDATA[");  
        sb.append(toUser);  
        sb.append("]]></FromUserName><CreateTime>");  
        sb.append(date.getTime());  
        sb.append("</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[");  
        sb.append(content);  
        sb.append("]]></Content></xml>");  
        return sb.toString();  
    }
    
    public static String formatLinkAnswer(ReceiveXmlEntity xmlEntity, String title,String link,String desc) {
    	if (null == xmlEntity) {
			return "";
		}
		logger.debug(xmlEntity.toString());
		String toUser=xmlEntity.getFromUserName();
		String fromUser=xmlEntity.getToUserName();

    	
    	StringBuffer sb = new StringBuffer();  
    	Date date = new Date();  
    	sb.append("<xml><ToUserName><![CDATA[");  
    	sb.append(toUser);  
    	sb.append("]]></ToUserName><FromUserName><![CDATA[");  
    	sb.append(fromUser);  
    	sb.append("]]></FromUserName><CreateTime>");  
    	sb.append(date.getTime());  
    	sb.append("</CreateTime><MsgType><![CDATA[link]]></MsgType><Title><![CDATA[");  
    	sb.append(title);  
    	sb.append("]]></Title><Description><![CDATA[");
    	sb.append(desc);
    	sb.append("]]></Description><Url><![CDATA[");
    	sb.append(link);
    	sb.append("]]></Url></xml>");  
    	return sb.toString();  
    }  

}  