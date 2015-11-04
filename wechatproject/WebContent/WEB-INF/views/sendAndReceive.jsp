<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=yes">
<title>Test JMS</title>
<link rel='stylesheet' type='text/css'
	href='${ctx}/css/bootstrap.min.css'>
<script src="${ctx}/js/bootstrap.min.js"></script>
</head>
<body>
	<wctags:navBtn comId="any" />
	<p id="msgContent" class="lead">
		<span><input type="button" id="bt" value="Send" /> &nbsp;&nbsp;<input type="input" id="method" value="httpClient" /></span><br />
		<textarea class="form-control" style="width: 100%; height: 40%;" id="sender"></textarea>
		<br />
		<textarea class="form-control" style="width: 100%; height: 40%;" id="receiver"></textarea>
	</p>
	<script>
		$(function() {
			$('#bt').click(function() {
				sendMessageToJMS();
			});
		});

		function sendMessageToJMS() {
			var _url = "${ctx}/siteManage/sendMessages";
			var _data = {
				message : $('#sender').val(),
				method: $('#method').val()
			};
			$.ajax({
				url : _url,
				type : 'get',
				data : _data,
				dataType : "jsonp",
				async : true,
				jsonp : "callbackparam",
				error : function(XHR, textStatus, errorThrown) {
					console.log('加载失败');
				},
				success : function(data) {
					console.log('加载成功');
					$("#receiver").append(data.resultStr);
				}
			});
		}
	</script>


</body>
</html>