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

	<input id="wanttedurl" value="http://www.baidu.com/" type="text">
	<button id="getUrl">get URL</button>
	<div id="content"></div>

	<script>
		function changeBtnState(state) {
			$('#getUrl').attr('disabled', !state);
		}
		$('#getUrl').click(function() {
			changeBtnState(false);
			console.log($('#wanttedurl').val());
			exeGetUrl($('#wanttedurl').val());
		});

		
		function exeGetUrl(wanttedurl) {
			var _data = {
				url : wanttedurl
			};
			var _url = "${ctx}/siteManage/gettingUrl";
			$.ajax({
				url : _url,
				type : 'get',
				data : _data,
				dataType : "jsonp",
				async : true,
				jsonp : "callbackparam",
				error : function(XHR, textStatus, errorThrown) {
					console.log('加载失败');
					changeBtnState(true);
				},
				success : function(data) {
					changeBtnState(true);
					console.log(data);
					$("body").empty().html(data);
					//$("#content").html(data);
				}
			});
		};
	</script>

</body>
</html>