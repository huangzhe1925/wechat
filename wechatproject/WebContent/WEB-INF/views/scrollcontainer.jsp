<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=yes">
<title>scrollcontainer</title>
</head>
<body>
<wctags:navBtn comId="any" />
<wctags:scrollContainer comId="testId" mode="3" scrollerBottomHeight="2" pullDownLabel="请下拉" pullUpLabel="请上拉" scrollDownAction="onScrollDown(options)" scrollUpAction="onScrollUp(options)">
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
				</ul>
</wctags:scrollContainer>

<script>
$(function() {
	scrContJsUtil.getSccollObj('testId').initScroller({probeType : 1});
});

function onScrollDown(){
	console.log("onScrollDown");
	scrContJsUtil.getSccollObj('testId').refreshScroll();
}

function onScrollUp(){
	console.log("onScrollUp");
	scrContJsUtil.getSccollObj('testId').refreshScroll();
	
}
</script>
				
</body>
</html>