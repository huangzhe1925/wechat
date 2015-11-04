package com.hz.wechatproject.handler;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hz.wechatproject.service.WechatProcessService;

@Controller
@RequestMapping(value = "wechat")
public class WechatProcessHandler {

	@Resource(name = "WechatProcessService")
	private WechatProcessService wechatProcessService;
	
	@RequestMapping(value = "wechatProcess")
	@ResponseBody
	public String wechatAuth(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");

		String result = "";
		result=wechatProcessService.processWechatRequest(request);
		
		return result;
	}

}
