package com.hz.wechatproject.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.hz.wechatproject.pojo.ReceiveXmlEntity;
import com.hz.wechatproject.service.WechatProcessService;
import com.hz.wechatproject.utils.CommonUtil;
import com.hz.wechatproject.utils.FormatXmlProcessUtil;
import com.hz.wechatproject.utils.ReceiveXmlProcessUtil;
import com.hz.wechatproject.utils.WechatUtil;

@Component("WechatProcessService")
public class WechatProcessServiceImpl implements WechatProcessService {

	private static Logger logger = Logger.getLogger(WechatProcessServiceImpl.class);

	@Override
	public String processWechatRequest(HttpServletRequest req) {
		String result = "";
		String echostr = req.getParameter("echostr");
		if (echostr != null && echostr.length() > 1) {
			logger.debug("checking Signature");
			if (WechatUtil.checkSignature(req)) {
				return echostr;
			} else {
				logger.error("Wrong Signature",
						new Exception("Wrong Signature"));
				return "Error";
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
		if(xmlEntity.getContent().equalsIgnoreCase("mov")){
			String html=CommonUtil.UtilHttpClient.get("http://www.dytt8.net/");
			response=CommonUtil.UtilHTMLParse.getNodeAsString(CommonUtil.UtilHTMLParse.getNodeListOnClass(html,"div", "co_content8"), 0);
			response=response.replaceAll(" ", "");
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
