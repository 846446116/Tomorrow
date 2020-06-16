<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body align="center">
	<h2>新用户注册</h2>
	<form action="${pageContext.request.contextPath }/loginservlet" method="post">
		<table align="center">
			<tr align="right">
		 		<td>请输入用户名:</td>
				<td><input type="text" name="username" autofocus="autofocus"></td>
			</tr>
			<tr align="right">
				<td>请输入密码:</td>
				<td><input type="text" name="userpassword"></td>
			</tr>
			<tr align="right">
				<td>请输入确认密码:</td>
				<td><input type="text" name=refill></td>
			</tr>
		</table>
			<input type="submit" name=register value="注册" >
			<input type="reset" name=refill value="清空" >
		</form>

</body>
</html>