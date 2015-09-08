package com.hz.wechatproject.utils;

public class CommonUtil {
	public static final String WECHAT_URL = "/wechatproject/wechat/wechatProcess";
	public static final String WECHAT_CONTEXT_PATH = "/wechatproject";
	public static final String SITE_MANAGE_LOGIN_URL = "/wechatproject/siteManage/siteManageLogin";
	public static final String SITE_MANAGE_URL = "/wechatproject/siteManage";
	public static final String ERRPR_PAGES_URI_PREFIX = "/wechatproject/errorpages";
	public static final String SECURITY_CHECK_URI="/wechatproject/j_spring_security_check";

	public static final String STATIC_RESOURCE_UTI_CSS_PREFIX = "/wechatproject/css";
	public static final String STATIC_RESOURCE_UTI_JS_PREFIX = "/wechatproject/js";
	public static final String STATIC_RESOURCE_UTI_IMAGES_PREFIX = "/wechatproject/images";
	
	

	public static boolean isEmptyString(String str) {
		if (str == null || str.isEmpty()) {
			return true;
		}
		return false;
	}
	
	public static boolean isValidURI(String uri){
		
		boolean retVal=false;
		if (CommonUtil.WECHAT_URL.equals(uri)) {
			retVal=true;
        }else if(uri.startsWith(CommonUtil.SITE_MANAGE_URL)){
        	retVal=true;
        }else if(CommonUtil.isResourceRequest(uri)){
        	retVal=true;
        }else if(uri.startsWith(CommonUtil.ERRPR_PAGES_URI_PREFIX)){
        	retVal=true;
        }else if(uri.startsWith(CommonUtil.SECURITY_CHECK_URI)){
        	retVal=true;
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

}
