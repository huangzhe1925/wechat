package com.hz.wechatproject.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.hz.wechatproject.db.service.UserService;
import com.hz.wechatproject.pojo.ModelExcuteScriptPOJO;
import com.hz.wechatproject.pojo.User;
import com.hz.wechatproject.utils.CommonUtil;

@Controller
@RequestMapping(value = "siteManage")
public class SiteManageHandler {
	
	private static Logger logger = Logger.getLogger(SiteManageHandler.class); 

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
	
	@RequestMapping(value = "testScroll")
	public String testScroll(){
		return "scrollcontainer";
	}
	
	@RequestMapping(value = "testHttpProxy")
	public String testHttpProxy(){
		return "httpproxy";
	}
	
	@RequestMapping(value = "gettingUrl")
	public void gettingUrl(HttpServletRequest req,HttpServletResponse res){
		logger.debug("start to getting url "+req.getParameter("url"));
		String content=CommonUtil.HttpClientUtil.get(req.getParameter("url"));
		try {
			res.getWriter().append(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(value = "executeScript")
	@ResponseBody
	public JSONPObject executeScript(@RequestParam String callbackparam,@ModelAttribute ModelExcuteScriptPOJO data){
		List<String> result=new ArrayList<String>();
		logger.debug("start to execute script "+data.getMethod());
		result.addAll(CommonUtil.execShellMethod(data.getMethod()));
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
