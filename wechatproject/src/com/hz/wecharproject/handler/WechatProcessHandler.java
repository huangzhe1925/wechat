package com.hz.wecharproject.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hz.wecharproject.utils.WechatProcessUtil;
import com.hz.wecharproject.utils.WechatUtil;

@Controller
@RequestMapping(value = "wechat")
public class WechatProcessHandler {

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
				System.out.println("Error checkSignature");
				return "Error";
			}
		} else {
			String xml = WechatUtil.getXMLFromRequest(request);
			System.out.println("comein MSG: "+xml);
			System.out.println();
			result=WechatProcessUtil.processWechatMag(xml);
			System.out.println("outgoing MSG: "+result);
			System.out.println();
		}
		return result;
	}

}
