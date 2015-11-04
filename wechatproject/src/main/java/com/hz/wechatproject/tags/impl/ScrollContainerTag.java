package com.hz.wechatproject.tags.impl;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import com.hz.wechatproject.tags.WCTagSupport;
import com.hz.wechatproject.utils.*;


public class ScrollContainerTag  extends WCTagSupport{

	private static final long serialVersionUID = -4455023891865276808L;

	protected JspWriter out;
	protected String ctx;
	
	private String comId="defaultId";
	private Integer mode=3;
	private String scrollDownAction;
	private String scrollUpAction;
	private Integer scrollerBottomHeight=2;
	private String scrollEndAction;
	
	private String pullDownLabel="下拉刷新...";
	private String pullUpLabel="上拉加载更多...";
	

	@Override
	protected void renderJS() throws Exception {
		StringBuilder jsScript =new StringBuilder(); 
		jsScript.append("<script type='text/javascript' src='" + ctx+ "/js/iscroll-probe-custom.js'></script>");
		jsScript.append("<script type='text/javascript' src='" + ctx+ "/js/scrollcontainer.js' ></script>");
		out.append(jsScript);
	}

	@Override
	protected void renderCSS() throws Exception {
		String cssScript = "<link rel='stylesheet' href='"+ctx+"/css/scrollcontainerlib.css' type='text/css' />";
		out.append(cssScript);
	}

	private void renderBeforeHTML() throws Exception {
		StringBuilder html=new StringBuilder();
		html.append("<div id='"+comId+"_ScrollCompo_DIV'>");
		html.append("<input type='hidden' name='scrollComInfo' value='"+comId+"|"+mode+"|"+CommonUtil.getEmptyString(scrollUpAction)+"|");
		html.append(CommonUtil.getEmptyString(scrollDownAction)+"|"+scrollerBottomHeight+"|"+CommonUtil.getEmptyString(scrollEndAction)+"'>");
		html.append("<div id='warpper' class='scrollwarpper'>");
		html.append("<div id='scroller' class='scrollscroller'>");
		html.append("<div class='pullDown' data-state=''  style='display:none;'>");
		html.append("<span class='pullDownIcon'></span><span class='pullDownLabel'>"+pullDownLabel+"</span>");
		html.append("</div>");
		html.append("<div id='scrollContent'>");
		out.append(html.toString());
	}
	
	private void renderAfterHTML() throws Exception {
		StringBuilder html=new StringBuilder();
		html.append("</div>");
		html.append("<div id='beforePullUpSpace'>");
		html.append("</div>");
		html.append("<div class='pullUp' data-state='' style='display:none;'>");
		html.append("<span class='pullUpIcon'></span><span class='pullUpLabel'>"+pullUpLabel+"</span>");
		html.append("</div>");
		html.append("<div id='afterPullUpSpace'>");
		html.append("</div>");
		html.append("</div>");
		html.append("</div>");
		html.append("</div>");
		html.append("</div>");
			
		out.append(html.toString());
				
	}
	
	@Override
	protected void initTag() {
		out = this.pageContext.getOut();
		ctx = this.pageContext.getServletContext().getContextPath();
	}
	
	@Override
	public int doStartTag() throws JspException {
		initTag();
		try {
			renderCSS();
			renderBeforeHTML();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public int doAfterBody() throws JspException {
		try {
			renderAfterHTML();
			renderJS();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	public String getComId() {
		return comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

	public Integer getMode() {
		return mode;
	}

	public void setMode(Integer mode) {
		this.mode = mode;
	}

	public String getScrollDownAction() {
		return scrollDownAction;
	}

	public void setScrollDownAction(String scrollDownAction) {
		this.scrollDownAction = scrollDownAction;
	}

	public String getScrollUpAction() {
		return scrollUpAction;
	}

	public void setScrollUpAction(String scrollUpAction) {
		this.scrollUpAction = scrollUpAction;
	}

	public Integer getScrollerBottomHeight() {
		return scrollerBottomHeight;
	}

	public void setScrollerBottomHeight(Integer scrollerBottomHeight) {
		this.scrollerBottomHeight = scrollerBottomHeight;
	}

	public String getScrollEndAction() {
		return scrollEndAction;
	}

	public void setScrollEndAction(String scrollEndAction) {
		this.scrollEndAction = scrollEndAction;
	}

	public String getPullDownLabel() {
		return pullDownLabel;
	}

	public void setPullDownLabel(String pullDownLabel) {
		this.pullDownLabel = pullDownLabel;
	}

	public String getPullUpLabel() {
		return pullUpLabel;
	}

	public void setPullUpLabel(String pullUpLabel) {
		this.pullUpLabel = pullUpLabel;
	}


	

}
