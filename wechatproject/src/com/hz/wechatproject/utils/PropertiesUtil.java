package com.hz.wechatproject.utils;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertiesUtil {
	
	private static Logger logger=Logger.getLogger(PropertiesUtil.class);
	
	public static final String DBSOURCE_SQLITE_URL="dbsource.sqlite.url";
	
	private static Properties deployInfoPro;
	private static final String PROPERTY_FILE_NAME="wechat.properties";  
	
	static {
		deployInfoPro = new Properties();
		try {
			String path = PropertiesUtil.class.getResource("/").getPath();
			path = path.substring(1, path.indexOf("classes"));
			deployInfoPro.load(new FileInputStream(path+ PROPERTY_FILE_NAME));
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public static void refreshProperties() {
		deployInfoPro = new Properties();
		try {
			String path = PropertiesUtil.class.getResource("/").getPath();
			path = path.substring(1, path.indexOf("classes"));
			deployInfoPro.load(new FileInputStream(path+ PROPERTY_FILE_NAME));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Properties getProperties() {
		return deployInfoPro;
	}

	public static String getProperty(String key) {
		return deployInfoPro.getProperty(key, "");
	}
	
}
