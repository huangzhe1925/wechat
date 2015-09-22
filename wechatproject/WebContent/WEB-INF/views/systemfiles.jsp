<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=yes">
<title>SYSTEM FILE</title>
<link rel='stylesheet' type='text/css' href='${ctx}/css/bootstrap.min.css'>
</head>
<body>
	<table class="table table-condensed">
		<thead>
			<tr>
				<th>File Name</th>
				<th>File Type</th>
				<th>File Path</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
</body>
<script>
	var initData='${data}';
	initData=$.parseJSON(initData);
	
	function createNewList(arrList){
		$('table tbody').html('');
		for(var index=0;index<arrList.length;index++){
			var fileName=arrList[index].fileName;
			var type = arrList[index].type==1?'File':"Directory";
			var filePath = arrList[index].filePath;
			$('table tbody').append('<tr><td>'+fileName+'</td><td>'+type+'</td><td>'+filePath+'</td></tr>');
			
		}
	}
	
	$(function(){
		createNewList(initData.listItems);
	});

</script>

</html>