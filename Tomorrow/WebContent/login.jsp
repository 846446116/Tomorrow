<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body  background="img/拉姆.jpg">
     <div style=" height: 200px; width: 760px; position: absolute; left:50%; margin-left: -380px; top: 50%; margin-top: -100px;">
		<form action="${pageContext.request.contextPath }/transfer" method="post">
			<table align="center" border="1">
		<tr>
			<td>用户名:</td>
			<td><input type="text" name="name"></td>
		</tr>
		<tr>
			<td>密&nbsp;&nbsp;码:</td>
			<td><input type="text" name="password"></td>
		</tr>
		<tr>
			<td><input type="submit" value="登录" name="login"></td>
			<td><input type="reset" value="重置" name="reset"></td>
		</tr>
	</table>
	<p align="center"><a href="registered.jsp" color=blue>注册用户</a></p>
	<p align="center"><a href="dispose.jsp" color=red>销毁用户</a></p>
		</form>


	</div>

</body>
</html>