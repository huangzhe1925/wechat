<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=yes">
<title>Test Http Proxy</title>
</head>
<body>

	<form action="${ctx}/siteManage/gettingUrl">
		<input id="url" name="url" value="http://www.baidu.com/" type="text">
		<button type="submit">Submit</button>
	</form>

</body>
</html>