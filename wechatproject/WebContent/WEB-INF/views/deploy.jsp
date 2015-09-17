<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=yes">
<title>Deploy</title>
</head>
<body>
fff
	<button id="gitupdate">Git Update</button>
	<br>
	<button id="deploy">Deploy</button>
	<br>
	<div id="content"></div>

	<script>
		function changeBtnState(state) {
			$('#gitupdate').attr('disabled', !state);
			$('#deploy').attr('disabled', !state);
		}
		$('#gitupdate').click(function() {
			changeBtnState(false);
			exeDeploy("gitupdate");
		});

		$('#deploy').click(function() {
			changeBtnState(false);
			exeDeploy("deploy");
		});

		function exeDeploy(method) {
			var _data = {
				method : method
			};
			var _url = "${ctx}/siteManage/executeScript";
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
					changeBtnState(true);
					for (var index = 0; index < data.length; index++) {
						$("#content").append(data[index] + "<br>");
					}
				}
			});
		};
	</script>

</body>
</html>