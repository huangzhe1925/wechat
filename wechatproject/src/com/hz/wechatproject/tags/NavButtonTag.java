package com.hz.wechatproject.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

public class NavButtonTag extends WCTagSupport {
	private static final long serialVersionUID = 1212862803772561211L;
	
	private JspWriter out;
	private String ctx;
	//fake , dont need this attr for now 
	private String comId;
	
	protected void renderJS() throws Exception{
		String jsScript="<script type='text/javascript' src='"+ctx+"/js/navbutton.js' ></script>";
		jsScript+="<script type='text/javascript' src='"+ctx+"/js/hammer.min.js'></script>";
		out.append(jsScript);
	}
	
	protected void renderCSS() throws Exception{
		String cssScript="<link rel='stylesheet' type='text/css' href='"+ctx+"/css/navbutton.css'>";
		out.append(cssScript);
	}
	
	protected void renderHTML() throws Exception{
		String html="<div id='naviBtnWarpper' class='naviBtnWarpper'>"
				+ "<div class=box style='display:none' data-status='close'>"
				+ "<!-- The center cover layer --><div class='centercover'></div>"
				+ "<!-- The center button --></div>"
				+ "<div class='centerbtn'><a href='javascript:void(0);'><span>Button</span></a></div>"
				+"</div>";
		out.append(html);
	}
	

	@Override
	protected void initTag(){
		out= this.pageContext.getOut();		
		ctx=this.pageContext.getServletContext().getContextPath();
	}

	@Override
	public int doStartTag() throws JspException {
		initTag();
		try {
			renderCSS();
			renderHTML();
			renderJS();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}

	@Override
	public int doAfterBody() throws JspException {
		return super.doAfterBody();
	}

	@Override
	public int doEndTag() throws JspException {

		return super.doEndTag();
	}
	
	
	public String getComId() {
		return comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

}
