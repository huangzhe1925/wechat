package com.hz.wechatproject.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.hz.wechatproject.db.pojo.User;
import com.hz.wechatproject.db.service.UserService;
import com.hz.wechatproject.pojo.JMSMessagePOJO;
import com.hz.wechatproject.pojo.ModelExcuteScriptPOJO;
import com.hz.wechatproject.pojo.ModelSystemFilesPOJO;
import com.hz.wechatproject.service.jms.impl.ProducerServiceImpl;
import com.hz.wechatproject.utils.CommonUtil;
import com.hz.wechatproject.utils.JMSMessagerHelper;
import com.hz.wechatproject.utils.PropertiesUtil;

@Controller
@RequestMapping(value = "siteManage")
public class SiteManageHandler {

	private static Logger logger = Logger.getLogger(SiteManageHandler.class);

	@Resource(name = "userService")
	UserService userService;

	// @ModelAttribute("user")
	// public void initAccount(Model model) {
	// if (!model.containsAttribute("user")) {
	// model.addAttribute("user", new User());
	// }
	// }

	@RequestMapping(value = "siteManageLogin")
	public ModelAndView siteMangeLogin(
			@RequestParam(value = "error", required = false) String error,
			ModelAndView model) {
		
		logger.debug(PropertiesUtil.getStringProperty(PropertiesUtil.DBSOURCE_SQLITE_URL));

		if (!model.getModel().containsKey("user")) {
			model.getModel().put("user", new User());
		}
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
	public String toDeployPage() {
		return "deploy";
	}

	@RequestMapping(value = "testScroll")
	public String testScroll() {
		return "scrollcontainer";
	}

	@RequestMapping(value = "testHttpProxy")
	public String testHttpProxy() {
		return "httpproxy";
	}
	
	@RequestMapping(value = "testJMSSender")
	public String testJMSSender() {
		return "testJMS";
	}

	@RequestMapping(value = "gettingUrl")
	public void gettingUrl(HttpServletRequest req, HttpServletResponse res) {
		logger.debug("start to getting url " + req.getParameter("url"));
		String content = CommonUtil.HttpClientUtil.get(req.getParameter("url"));
		try {
			res.getWriter().append(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "executeScript")
	@ResponseBody
	public JSONPObject executeScript(@RequestParam String callbackparam,
			@ModelAttribute ModelExcuteScriptPOJO data) {
		List<String> result = new ArrayList<String>();
		logger.debug("start to execute script " + data.getMethod());
		result.addAll(CommonUtil.execShellMethod(data.getMethod()));
		return new JSONPObject(callbackparam, result);
	}

	@RequestMapping(value = "systemfiles")
	public ModelAndView systemfiles(ModelAndView model) {
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		result.put("listItems", CommonUtil.getSystemFiles(null));
		result.put("currPath", "/");
		ObjectMapper mapper = new ObjectMapper();
		String data = "";
		try {
			data = mapper.writeValueAsString(result);
			//data=data.replace("\\", "\\\\");
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		model.getModel().put("data", data);
		model.setViewName("systemfiles");
		return model;
	}

	@RequestMapping(value = "getsystemfiles")
	@ResponseBody
	public JSONPObject getsystemfiles(@RequestParam String callbackparam,
			@ModelAttribute ModelSystemFilesPOJO data , @RequestParam(required=false) Boolean isParent) {
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		if(isParent){
			data=CommonUtil.getParentSysFilePath(data);
		}
		
		List<ModelSystemFilesPOJO> listItems=CommonUtil.getSystemFiles(data);
		if(CommonUtil.isSystemFileContentPOJO(listItems)){
			result.put("isFileContent",true);
		}
		result.put("listItems",listItems);
		result.put("currPath", data==null?"/":data.getFilePath());
		return new JSONPObject(callbackparam, result);
	}
	
	@Autowired
	private ProducerServiceImpl producerService;
	
	@RequestMapping(value = "sendJMSMessage")
	@ResponseBody
	public JSONPObject sendJMSMessage(@RequestParam String callbackparam, @ModelAttribute JMSMessagePOJO data, HttpServletRequest req ) {
		Map<String, Object> result = new LinkedHashMap<String, Object>();
//		CommonUtil.getObjFromSpringContainer(req,"jmsTemplate");
//		JMSMessagerHelper.getInst().sendJMSMessage(data.getMessage());
		producerService.sendMessage(data.getMessage());
		result.put("isSuccess","success");
		return new JSONPObject(callbackparam, result);
	}
	

}
