package com.hz.wecharproject.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "siteManage")
public class SiteManageHandler {

	@RequestMapping(value = "siteManageLogin")
	public String siteMangeLogin() {
		return "siteManageLogin";
	}
	
	
}
