<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Deploy</title>
</head>
<body>
	<button id="test">Test Deploy</button>
<div id="content"></div>

	<script>
		$('#test').click(function() {
			testDeploy();
		});

		function testDeploy() {
			var _data = {};
			var _url = "${ctx}/siteManage/executeScript";
			$.ajax({
				url : _url,
				type : 'get',
				data : JSON.stringify(_data),
				dataType : "jsonp",
				async : false,
				jsonp : "callbackparam",
				error : function(XHR, textStatus, errorThrown) {
					console.log('加载失败');

				},
				success : function(data) {
					$("#content").html(data);
				}
			});
		};
	</script>

</body>
</html>