package com.hz.wecharproject.utils;

import com.hz.wecharproject.pojo.ReceiveXmlEntity;

public class WechatProcessUtil {  

    public static String processWechatMag(String xml){  
        ReceiveXmlEntity xmlEntity = new ReceiveXmlProcessUtil().getMsgEntity(xml);  
          
        String result = "";  
        if("text".endsWith(xmlEntity.getMsgType())){  
            result = xmlEntity.getContent()+"    "+xml;  
        }  
          
        result = new FormatXmlProcessUtil().formatXmlAnswer(xmlEntity.getFromUserName(), xmlEntity.getToUserName(), result);  
          
        return result;  
    }  
}  