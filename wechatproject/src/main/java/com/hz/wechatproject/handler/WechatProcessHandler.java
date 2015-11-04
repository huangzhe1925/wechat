package com.hz.wechatproject.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hz.wechatproject.utils.WechatUtil;

@Controller
@RequestMapping(value = "wechat")
public class WechatProcessHandler {

	private static Logger logger = Logger.getLogger(WechatProcessHandler.class);
	
	@RequestMapping(value = "wechatProcess")
	@ResponseBody
	public String wechatAuth(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");


		String result = "";
		String echostr = request.getParameter("echostr");
		if (echostr != null && echostr.length() > 1) {
			if (WechatUtil.checkSignature(request)) {
				return echostr;
			} else {
				logger.error("Error checkSignature",new Exception("Error checkSignature"));
				return "Error";
			}
		} else {
			String xml = WechatUtil.getXMLFromRequest(request);
			logger.debug("comein MSG: "+xml);
			result=WechatUtil.processWechatMag(xml);
			logger.debug("outgoing MSG: "+result);
		}
		return result;
	}

}
