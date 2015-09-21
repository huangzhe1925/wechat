package com.hz.wechatproject.tags;

import javax.servlet.jsp.tagext.TagSupport;

public abstract class WCTagSupport extends TagSupport {

	private static final long serialVersionUID = -3285542123484301362L;

	protected abstract void renderJS() throws Exception;
	
	protected abstract void renderCSS() throws Exception;
	
	protected abstract void renderHTML() throws Exception;
	
	protected abstract void initTag();
	
}
