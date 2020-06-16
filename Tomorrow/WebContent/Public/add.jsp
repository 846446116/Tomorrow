<%@ page language="java" import="cn.ly.servlet.MenuServlet"  contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>添加</title>
</head>
<body>
	<h2>添加</h2>
	<form action="<%=request.getContextPath() %>/comment?action=add"  method="post">
	序号：<input type="text"  name='id'><br> 
	名称：<input type="text"  name='name'> <br>
	价格：<input type="text"  name='price'><br>
	数量：<input type="text"  name='num'> <br>
	产地：<input type="text"  name='site'> <br>
所有权：     <input type="text"  name='exclusive'> <br>
	<input type='submit' value='提交'> <form>
</body>
</html>