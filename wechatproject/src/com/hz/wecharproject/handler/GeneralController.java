package com.hz.wecharproject.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller  
public class GeneralController {  
  
    @RequestMapping(value = "testJson")  
    @ResponseBody  
    public Map<String, String> addUser() {  
        Map<String, String> map = new HashMap<String, String>(1);  
        map.put("success", "true");  
        return map;  
    }
  
}  
