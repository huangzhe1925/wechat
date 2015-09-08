package com.hz.wechatproject.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hz.wechatproject.db.service.UserService;
import com.hz.wechatproject.pojo.User;

@Controller
@RequestMapping(value = "siteManage")
public class SiteManageHandler {

	@Autowired
	UserService userService;

	@ModelAttribute("user")
	public void initAccount(Model model) {
		if (!model.containsAttribute("user")) {
			model.addAttribute("user", new User());
		}

		for (User user : userService.getAllUser()) {
			System.out.println(user);
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
