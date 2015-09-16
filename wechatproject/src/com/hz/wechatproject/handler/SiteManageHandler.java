package com.hz.wechatproject.handler;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.hz.wechatproject.db.service.UserService;
import com.hz.wechatproject.pojo.User;
import com.hz.wechatproject.utils.CommonUtil;

@Controller
@RequestMapping(value = "siteManage")
public class SiteManageHandler {

	@Resource(name="userService")
	UserService userService;

	@ModelAttribute("user")
	public void initAccount(Model model) {
		if (!model.containsAttribute("user")) {
			model.addAttribute("user", new User());
		}
	}

	@RequestMapping(value = "siteManageLogin")
	public ModelAndView siteMangeLogin(
			@RequestParam(value = "error", required = false) String error) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username or password!");
		}

		model.setViewName("siteManageLogin");

		return model;
	}

	@RequestMapping(value = "welcomepage")
	public String toWelcomePage() {
		return "welcome";
	}
	
	@RequestMapping(value = "deploypage")
	public String toDeployPage(){
		return "deploy";
	}
	
	@RequestMapping(value = "executeScript")
	@ResponseBody  
	public JSONPObject executeScript(String callbackparam){
		List<String> result=new ArrayList<String>();
		try {
			result.addAll(CommonUtil.execShell("cmd.exe /c dir"));
			result.addAll(CommonUtil.execShell("cmd.exe cd"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new JSONPObject(callbackparam, result);
	}

	// @RequestMapping(value = "validateUser", method = RequestMethod.POST)
	// public String validateUser(@ModelAttribute("user") User user) {
	//
	// System.out.println("name " + user.getUserName() + "  password "
	// + user.getUserPasswd());
	// if ("123".equals(user.getUserName())
	// && "123".equals(user.getUserPasswd())) {
	// return "welcome";
	// }
	// return "siteManageLogin";
	// }

}
