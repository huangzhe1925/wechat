package com.hz.wechatproject.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import com.hz.wechatproject.pojo.ReceiveXmlEntity;

public class WechatUtil {

	public static final String TOKEN="19890402";
	
	 public static String processWechatMag(String xml){  
	        ReceiveXmlEntity xmlEntity = new ReceiveXmlProcessUtil().getMsgEntity(xml);  
	          
	        String result = "";  
	        if("text".endsWith(xmlEntity.getMsgType())){  
	            result = xmlEntity.getContent()+"    "+xml;  
	        }  
	          
	        result = new FormatXmlProcessUtil().formatXmlAnswer(xmlEntity.getFromUserName(), xmlEntity.getToUserName(), result);  
	          
	        return result;  
	    }  
	
	public static String getXMLFromRequest(HttpServletRequest request)
			throws IOException {
		StringBuffer sb = new StringBuffer();
		InputStream is = request.getInputStream();
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		String s = "";
		while ((s = br.readLine()) != null) {
			sb.append(s);
		}
		String xml = sb.toString();
		return xml;
	}

	public static boolean checkSignature(HttpServletRequest request) {
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String token = TOKEN;
		String[] tmpArr = { token, timestamp, nonce };
		Arrays.sort(tmpArr);
		StringBuffer tmpStrBuff = new StringBuffer();  
        for(String arr:tmpArr){  
        	tmpStrBuff.append(arr);  
        }  
        
		String tmpStr = SHA1Encode(tmpStrBuff.toString());
		if (tmpStr.equalsIgnoreCase(signature)) {
			return true;
		} else {
			return false;
		}
	}

	public static String SHA1Encode(String sourceString) {
		String resultString = null;
		try {
			resultString = new String(sourceString);
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			resultString = byte2hexString(md.digest(resultString.getBytes()));
		} catch (Exception ex) {
		}
		return resultString;
	}

	public static final String byte2hexString(byte[] bytes) {
		StringBuffer buf = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			if (((int) bytes[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString((int) bytes[i] & 0xff, 16));
		}
		return buf.toString().toUpperCase();
	}
}
