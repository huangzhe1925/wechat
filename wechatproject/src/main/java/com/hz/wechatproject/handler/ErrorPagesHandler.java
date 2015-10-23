package com.hz.wechatproject.handler;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "errorpages")
public class ErrorPagesHandler {
	@RequestMapping(value = "wchatbrowseerror")
    public String wchatbrowseerror(Map<String,String> map) {  
        map.put("errormsg", "please open page in wechat browser");  
        return "errorpage";  
    }
	
	@RequestMapping(value = "accessdeniederror")
    public String accessdeniederror(Map<String,String> map) {  
        map.put("errormsg", "Access Denied");  
        return "errorpage";  
    }
}


//