package com.hz.wechatproject.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

public class CommonUtil {
	public static final String WECHAT_URL = "/wechatproject/wechat/wechatProcess";
	public static final String WECHAT_CONTEXT_PATH = "/wechatproject";
	public static final String SITE_MANAGE_LOGIN_URL = "/wechatproject/siteManage";
	public static final String SITE_MANAGE_URL = "/wechatproject/siteManage";
	public static final String ERRPR_PAGES_URI_PREFIX = "/wechatproject/errorpages";
	public static final String SECURITY_CHECK_URI = "/wechatproject/j_spring_security_check";

	public static final String STATIC_RESOURCE_UTI_CSS_PREFIX = "/wechatproject/css";
	public static final String STATIC_RESOURCE_UTI_JS_PREFIX = "/wechatproject/js";
	public static final String STATIC_RESOURCE_UTI_IMAGES_PREFIX = "/wechatproject/images";

	public static final String ACCESS_STRING_SEPERATOR = ",";
	public static final String ACCESS_ROLE_ROLE_USER = "ROLE_USER";
	public static final String ACCESS_NUM_ROLE_USER = "1";
	public static final String ACCESS_ROLE_ROLE_ADMIN = "ROLE_ADMIN";
	public static final String ACCESS_NUM_ROLE_ADMIN = "999";

	public static boolean isEmptyString(String str) {
		if (str == null || str.isEmpty()) {
			return true;
		}
		return false;
	}

	public static boolean isValidURI(String uri) {
		boolean retVal = false;
		if (CommonUtil.WECHAT_URL.equals(uri)) {
			retVal = true;
		} else if (uri.startsWith(CommonUtil.SITE_MANAGE_URL)) {
			retVal = true;
		} else if (CommonUtil.isResourceRequest(uri)) {
			retVal = true;
		} else if (uri.startsWith(CommonUtil.ERRPR_PAGES_URI_PREFIX)) {
			retVal = true;
		} else if (uri.startsWith(CommonUtil.SECURITY_CHECK_URI)) {
			retVal = true;
		}

		return retVal;
	}

	public static boolean isResourceRequest(String uri) {
		if (!isEmptyString(uri)) {
			if (uri.startsWith(STATIC_RESOURCE_UTI_CSS_PREFIX)
					|| uri.startsWith(STATIC_RESOURCE_UTI_JS_PREFIX)
					|| uri.startsWith(STATIC_RESOURCE_UTI_IMAGES_PREFIX)) {
				return true;
			}
		}
		return false;
	}

	public static List<String> splitStringAsList(String str, String seperator) {
		List<String> retList = new ArrayList<>();
		if (isEmptyString(str)) {
			return retList;
		}
		for (String part : str.split(seperator)) {
			retList.add(part);
		}

		return retList;
	}

	/**
	 * 运行shell脚本
	 * 
	 * @param shell
	 *            需要运行的shell脚本
	 */
	public static List<String> execShell(String shell) throws Exception {
		List<String> strList = new ArrayList<String>();
		Process process;
		Runtime rt = Runtime.getRuntime();
		process = rt.exec(shell);
		InputStreamReader ir = new InputStreamReader(process.getInputStream());
		LineNumberReader input = new LineNumberReader(ir);
		String line;
		process.waitFor();
		while ((line = input.readLine()) != null) {
			strList.add(line);
		}

		return strList;
	}

	/**
	 * 运行shell
	 * 
	 * @param shStr
	 *            需要执行的shell
	 * @return
	 * @throws IOException
	 */
	public static List<String> runShell(String shStr) throws Exception {
		List<String> strList = new ArrayList<String>();

		Process process;
		process = Runtime.getRuntime().exec(
				new String[] { "/bin/sh", "-c", shStr }, null, null);
		InputStreamReader ir = new InputStreamReader(process.getInputStream());
		LineNumberReader input = new LineNumberReader(ir);
		String line;
		process.waitFor();
		while ((line = input.readLine()) != null) {
			strList.add(line);
		}

		return strList;
	}

}
