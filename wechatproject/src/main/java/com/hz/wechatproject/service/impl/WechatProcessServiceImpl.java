package com.hz.wechatproject.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.hz.wechatproject.pojo.ReceiveXmlEntity;
import com.hz.wechatproject.service.WechatProcessService;
import com.hz.wechatproject.utils.CommonUtil;
import com.hz.wechatproject.utils.FormatXmlProcessUtil;
import com.hz.wechatproject.utils.PropertiesUtil;
import com.hz.wechatproject.utils.ReceiveXmlProcessUtil;
import com.hz.wechatproject.utils.WechatUtil;

@Component("WechatProcessService")
public class WechatProcessServiceImpl implements WechatProcessService {

	private static Logger logger = Logger.getLogger(WechatProcessServiceImpl.class);
	
	public static String ECHO_STRING="echostr";
	
	public static String RETURN_ERROR="Error";
	
	public static String REQ_CMD_HOTEL_BOOKING="hotel";
	
	public static String REQ_CMD_MOV="mov";
	
	@Override
	public String processWechatRequest(HttpServletRequest req) {
		String result = "";
		String echostr = req.getParameter(ECHO_STRING);
		if (echostr != null && echostr.length() > 1) {
			logger.debug("checking Signature");
			if (WechatUtil.checkSignature(req)) {
				return echostr;
			} else {
				logger.error("Wrong Signature",new Exception("Wrong Signature"));
				return RETURN_ERROR;
			}
		} else {
			String xml = "";
			try {
				xml = WechatUtil.getXMLFromRequest(req);
				logger.debug("comein MSG: " + xml);
				result = processWechatMsg(xml);
				logger.debug("outgoing MSG: " + result);
				
			} catch (Exception e) {
				logger.error("Error When getXMLFromRequest", e);
			}
			
		}
		return result;
	}

	private String processWechatMsg(String xml) throws Exception {
		ReceiveXmlEntity xmlEntity = ReceiveXmlProcessUtil.getMsgEntity(xml);
		String response="";
		String requestCMD=xmlEntity.getContent();
		if(REQ_CMD_MOV.equalsIgnoreCase(requestCMD)){
			String html=CommonUtil.UtilHttpClient.get("http://www.dytt8.net/");
			response=CommonUtil.UtilHTMLParse.getNodeAsString(CommonUtil.UtilHTMLParse.getNodeListOnClass(html,"div", "co_content8"), 0);
			response=response.replaceAll(" ", "");
		}else if(REQ_CMD_HOTEL_BOOKING.equalsIgnoreCase(requestCMD)){
			String linkUrl=PropertiesUtil.getStringProperty(PropertiesUtil.PROP_HOTEL_BOOKING_SITE_URL);
			String linkLabel=PropertiesUtil.getStringProperty(PropertiesUtil.PROP_HOTEL_BOOKING_SITE_LABEL);
			if(!CommonUtil.isEmptyString(linkUrl)){
				response="<a href='"+linkUrl+"'>"+linkLabel+"</a>";
			}
		}//default request process
		else{
			String linkUrl=PropertiesUtil.getStringProperty(PropertiesUtil.PROP_HOTEL_BOOKING_SITE_URL);
			String linkLabel=PropertiesUtil.getStringProperty(PropertiesUtil.PROP_HOTEL_BOOKING_SITE_LABEL);
			if(!CommonUtil.isEmptyString(linkUrl)){
				response="<a href='"+linkUrl+"'>"+linkLabel+"</a>";
			}
		}
		
		return processWechatMsg(xmlEntity, response);
	}

	private String processWechatMsg(ReceiveXmlEntity xmlEntity, String response) {

		if (null == xmlEntity) {
			return "";
		}
		logger.debug(xmlEntity.toString());

		String result = "";

		result = FormatXmlProcessUtil.formatXmlAnswer(
				xmlEntity.getFromUserName(), xmlEntity.getToUserName(), response);

		return result;
	}

}
