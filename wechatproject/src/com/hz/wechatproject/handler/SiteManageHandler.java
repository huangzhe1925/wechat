package com.hz.wechatproject.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hz.wechatproject.utils.DBDao;

@Controller
@RequestMapping(value = "siteManage")
public class SiteManageHandler {
	
	@Autowired
	DBDao dbDao;

	@ModelAttribute("user")  
	public void initAccount(Model model) {  
		if(!model.containsAttribute("user")){
			model.addAttribute("user",new UserLoginInfo()); 
		}
	}  
	
	
	@RequestMapping(value = "siteManageLogin")
	public String siteMangeLogin() {
		return "siteManageLogin";
	}
	
	
	@RequestMapping(value = "validateUser" ,method=RequestMethod.POST)
	public String validateUser(@ModelAttribute("user") UserLoginInfo user) {
		
		System.out.println("name "+user.getName()+"  password "+user.getPassword());
		if("123".equals(user.getName())&& "123".equals(user.getPassword())){
			return "welcome";
		}
		return "siteManageLogin";
	}
	
}

class UserLoginInfo{
	String name;
	String password;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
