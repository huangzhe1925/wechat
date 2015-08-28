package com.hz.wechatproject.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.hz.wechatproject.pojo.ReceiveXmlEntity;

public class ReceiveXmlProcessUtil {  
    public ReceiveXmlEntity getMsgEntity(String strXml){  
        ReceiveXmlEntity msg = null;  
        try {  
            if (strXml.length() <= 0 || strXml == null)  
                return null;  
               
            Document document = DocumentHelper.parseText(strXml);  
            Element root = document.getRootElement();  
            Iterator<?> iter = root.elementIterator();  
              
            msg = new ReceiveXmlEntity();  
            Class<?> c = Class.forName("com.hz.wecharproject.pojo.ReceiveXmlEntity");  
            msg = (ReceiveXmlEntity)c.newInstance();
              
            while(iter.hasNext()){  
                Element ele = (Element)iter.next();  
                Field field = c.getDeclaredField(ele.getName());  
                Method method = c.getDeclaredMethod("set"+ele.getName(), field.getType());  
                method.invoke(msg, ele.getText());  
            }  
        } catch (Exception e) {  
            System.out.println("xml format error: "+ strXml);  
            e.printStackTrace();  
        }  
        return msg;  
    }  
}  