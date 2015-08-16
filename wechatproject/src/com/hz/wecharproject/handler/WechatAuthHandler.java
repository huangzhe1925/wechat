package com.hz.wecharproject.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hz.wecharproject.utils.WechatUtil;

@Controller
public class WechatAuthHandler {

	@RequestMapping(value = "wechatProcess")
	@ResponseBody
	public String wechatAuth(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");

		// 次即为接收到微信端发送过来的xml数据

		String result = "";
		/** 判断是否是微信接入激活验证，只有首次接入验证时才会收到echostr参数，此时需要把它直接返回 */
		String echostr = request.getParameter("echostr");
		if (echostr != null && echostr.length() > 1) {
			if (WechatUtil.checkSignature(request)) {
				return echostr;
			} else {
				System.out.println("Error checkSignature");
				return "Error";
			}
		} else {
			// 正常的微信处理流程
			result = WechatUtil.getXMLFromRequest(request);
			System.out.println(result);
			System.out.println();
		}
		return result;
	}

}
