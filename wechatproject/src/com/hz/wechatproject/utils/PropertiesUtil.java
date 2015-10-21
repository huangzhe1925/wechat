package com.hz.wechatproject.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class PropertiesUtil {

	private static Logger logger = Logger.getLogger(PropertiesUtil.class);

	private static PropertiesConfiguration propertiesConfiguration;

	public static final String PROPERTY_FILE_NAME = "wechat.properties";

	public static final String DBSOURCE_SQLITE_URL = "dbsource.sqlite.url";


	static {
		loadProperties();
	}
	

	public static void loadProperties() {
		try {
			Resource resource = new ClassPathResource(PROPERTY_FILE_NAME);
			URL resourceURL;
			resourceURL = resource.getURL();
			propertiesConfiguration = new PropertiesConfiguration(resourceURL);
			propertiesConfiguration.setReloadingStrategy(new FileChangedReloadingStrategy());
		} catch (Exception e) {
			logger.error(e);
			propertiesConfiguration = new PropertiesConfiguration();
		}
	}

	public static PropertiesConfiguration getPropertiesConfiguration() {
		return propertiesConfiguration;
	}

	public static String getStringProperty(String key) {
		return propertiesConfiguration.getString(key);
	}
	
	public static Integer getIntProperty(String key) {
		return propertiesConfiguration.getInteger(key, null);
	}
	
	public static Object getObjProperty(String key){
		return propertiesConfiguration.getInteger(key, null);
	}
	
	
	public static void main(String args[]){
		System.out.println(PropertiesUtil.getStringProperty(DBSOURCE_SQLITE_URL));
	}

}
