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

		// �μ�Ϊ���յ�΢�Ŷ˷��͹�����xml����

		String result = "";
		/** �ж��Ƿ���΢�Ž��뼤����֤��ֻ���״ν�����֤ʱ�Ż��յ�echostr��������ʱ��Ҫ����ֱ�ӷ��� */
		String echostr = request.getParameter("echostr");
		if (echostr != null && echostr.length() > 1) {
			if (WechatUtil.checkSignature(request)) {
				return echostr;
			} else {
				System.out.println("Error checkSignature");
				return "Error";
			}
		} else {
			// ������΢�Ŵ�������
			result = WechatUtil.getXMLFromRequest(request);
			System.out.println(result);
			System.out.println();
		}
		return result;
	}

}
