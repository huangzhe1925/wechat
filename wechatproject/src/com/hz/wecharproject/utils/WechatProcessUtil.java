package com.hz.wecharproject.utils;

import com.hz.wecharproject.pojo.ReceiveXmlEntity;

public class WechatProcessUtil {  
    /** 
     * ��������xml����ȡ���ܻظ������ͨ��ͼ�������api�ӿڣ� 
     * @param xml ���յ���΢������ 
     * @return  ���յĽ��������xml��ʽ���ݣ� 
     */  
    public String processWechatMag(String xml){  
        /** ����xml���� */  
        ReceiveXmlEntity xmlEntity = new ReceiveXmlProcessUtil().getMsgEntity(xml);  
          
        /** ���ı���ϢΪ��������ͼ�������api�ӿڣ���ȡ�ظ����� */  
        String result = "";  
        if("text".endsWith(xmlEntity.getMsgType())){  
            result = xmlEntity.getContent();  
        }  
          
        /** ��ʱ������û�������ǡ���á����ھ�������Ĺ���֮��resultΪ����Ҳ�á����Ƶ�����  
         *  ��Ϊ���ջظ���΢�ŵ�Ҳ��xml��ʽ�����ݣ�������Ҫ�����װΪ�ı����ͷ�����Ϣ 
         * */  
        result = new FormatXmlProcessUtil().formatXmlAnswer(xmlEntity.getFromUserName(), xmlEntity.getToUserName(), result);  
          
        return result;  
    }  
}  