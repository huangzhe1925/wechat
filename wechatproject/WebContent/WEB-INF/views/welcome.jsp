<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=yes">  
<title>Welcome</title>
<script type="text/javascript" src="${ctx}/js/jquery-2.1.4.min.js" ></script>
<script type="text/javascript" src="${ctx}/js/navbutton.js" ></script>
<script type="text/javascript" src="${ctx}/js/hammer.min.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/css/navbutton.css">
</head>
<body >
<div style="position:absolute;left:200px;top:200px">
<a href="#">touch me</a>
</div>
<div id="naviBtnWarpper" class="naviBtnWarpper">
	<div class=box style="display:none" data-status='close'>
		<!-- The center cover layer -->
		<div class="centercover"></div>
		<!-- The center button -->
	</div>
	
	<div class="centerbtn"><a href='javascript:void(0);'><span>Button</span></a></div>

</div>

</body>
</html>