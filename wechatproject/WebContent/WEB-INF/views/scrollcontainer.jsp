<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common.jsp"%>
<% 	
	pageContext.setAttribute("comId", "scrollcontainer");
	pageContext.setAttribute("mode", "3");
	pageContext.setAttribute("scrollDownAction", "");
	pageContext.setAttribute("scrollUpAction", "pageUpProductList(options)");
	pageContext.setAttribute("scrollerBottomHeight", "2");
	pageContext.setAttribute("scrollEndAction", "");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>scrollcontainer</title>
<link rel="stylesheet" href="${ctx}/css/scrollcontainerlib.css" type="text/css" />
<script type="text/javascript" src="${ctx}/js/scrollcontainer.js" ></script>
<script type="text/javascript" src="${ctx}/js/iscroll-probe-custom.js" ></script>
</head>

<body>
	<div id="${comId}_ScrollCompo_DIV">
		<input type="hidden" name="scrollComInfo" value='${comId}|${mode}|${scrollUpAction}|${scrollDownAction}|${scrollerBottomHeight}|${scrollEndAction}'>
	
		<div id="warpper" class="scrollwarpper">
			<div id="scroller" class="scrollscroller">
	
				<div class="pullDown" data-state=""  style="display:none;">
					<span class="pullDownIcon"></span><span class="pullDownLabel">下拉刷新...</span>
				</div>
				
				<div id="scrollContent">
				<ul>
				<li>1</li>
				<li>2</li>
				<li>3</li>
				<li>4</li>
				<li>5</li>
				<li>6</li>
				<li>7</li>
				<li>8</li>
				<li>9</li>
				<li>10</li>
				<li>00</li>
				<li>1</li>
				<li>2</li>
				<li>3</li>
				<li>4</li>
				<li>5</li>
				<li>6</li>
				<li>7</li>
				<li>8</li>
				<li>9</li>
				<li>10</li>
				<li>01</li>
				<li>1</li>
				<li>2</li>
				<li>3</li>
				<li>4</li>
				<li>5</li>
				<li>6</li>
				<li>7</li>
				<li>8</li>
				<li>9</li>
				<li>10</li>
				<li>02</li>
				<li>a</li>
				<li>b</li>
				<li>x</li>
				<li>c</li>
				<li>v</li>
				<li>n</li>
				<li>m</li>
				<li>,</li>
				<li>.</li>
				<li>/</li>
				<li>a</li>
				<li>s</li>
				<li>f</li>
				<li>d</li>
				<li>g</li>
				<li>h</li>
				<li>j</li>
				<li>k</li>
				<li>l</li>
				<li>;</li>
				
				</ul>
				</div>
				
				<div id="beforePullUpSpace">
				</div>
	
				<div class="pullUp" data-state="" style="display:none;">
					<span class="pullUpIcon"></span><span class="pullUpLabel">上拉加载更多...</span>
				</div>
	
				<div id="afterPullUpSpace">
				</div>
				
			</div>
		</div>
	</div>
	<script>
	$(function() {
		scrContJsUtil.getSccollObj('scrollcontainer').initScroller({probeType : 1});
	});
	</script>
</body>
</html>