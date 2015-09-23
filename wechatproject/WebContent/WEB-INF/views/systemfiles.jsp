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
<script src="${ctx}/js/bootstrap.min.js"></script>
</head>
<body>
	<h2 id="currPath"></h2>
	<table id="fileListTab" class="table table-condensed">
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
	
	<p id="fileContent" class="lead" style="display:none">
		<input type="button" id="bt" value="Back To List" /> <br/><br/>
		<textarea class="form-control" style="width:100%;height:80%;" id="con"></textarea>
	</p>
</body>
<script>
	var initData = ${data};

	function createNewList(data) {
		var filePathA=data.currPath.replace(/\\/g,'\\\\');
		$('#currPath').html(data.currPath);
		
		if(data.isFileContent!=null){
			$('#con').html('');
			$('#fileContent').show();
			$('#fileListTab').hide();
			$('#bt').unbind();
			$('#bt').click(function(){
				getsystemfiles(data.currPath,true);
			});
			$('#con').append(data.listItems[0].content);
		}else{
			$('#fileContent').hide();
			$('#fileListTab').show();
			
			var arrList=data.listItems;
			$('table tbody').html('');
			$('table tbody').append('<tr>' + getTableRow('..', '','', filePathA,true) + '</tr>');
			for (var index = 0; index < arrList.length; index++) {
				var fileName = arrList[index].fileName;
				var type = arrList[index].type == 1 ? 'File' : "Directory";
				var filePath = arrList[index].filePath;
				filePathA=filePath.replace(/\\/g,'\\\\');
				$('table tbody').append('<tr>' + getTableRow(fileName, type, filePath,filePathA,false) + '</tr>');

			};
		}
	}

	function getTableRow(fileName, type, filePath, filePathA,isParent) {
		if(filePath==null){
			filePath='';
		}
		var retStr = '<td><a href=\'javascript:getsystemfiles("' + filePathA+ '",'+isParent+');\'>' + fileName + '</a></td>';
		retStr += '<td>' + type + '</td>';
		retStr += '<td><a href=\'javascript:getsystemfiles("' + filePathA + '",'+isParent+');\'>'+ filePath + '</a></td>';
		return retStr;
	}

	function getsystemfiles(filePath,isParent) {
		if(filePath==null){
			filePath='';
		}
		var _data = {
			filePath : filePath,
			isParent: isParent
		};
		var _url = "${ctx}/siteManage/getsystemfiles";
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
				createNewList(data);
			}
		});
	}

	$(function() {
		createNewList(initData);
	});
</script>

</html>