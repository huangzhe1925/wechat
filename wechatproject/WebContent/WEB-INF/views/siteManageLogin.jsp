<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ include file="/WEB-INF/views/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page</title>
</head>
<body>
<form:form action="${ctx}/siteManage/validateUser" method="post" modelAttribute="user">  
    <table>  
        <tr> 
            <td>Name:</td><td><form:input path="userName"/></td>  
        </tr>  
        <tr>  
            <td>Password:</td><td><form:password path="userPasswd"/></td>  
        </tr>  
        <tr>  
            <td colspan="2"><input type="submit" value="submit"/></td>  
        </tr>  
    </table>  
</form:form>  
</body>
</html>