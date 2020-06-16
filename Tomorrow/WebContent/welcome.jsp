<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>明日之后</title>
</head>
<body>
		<h1 align="center">欢迎查询明日</h1>
	<a href="${pageContext.request.contextPath }/menu?go1=wood">进入木材资源管理</a><br/>
	<a href="${pageContext.request.contextPath }/menu?go1=mine">进入矿产资源管理</a><br>
	<a href="${pageContext.request.contextPath }/menu?go1=fibre">进入麻类资源管理</a><br>
	<a href="${pageContext.request.contextPath }/menu?go1=special">进入特殊资源管理</a><br>
	<a href="${pageContext.request.contextPath }/menu?go1=monster">进入动物资源管理</a><br>
	
	<span>${pageContext.request.contextPath }</span>
</body>
</html>