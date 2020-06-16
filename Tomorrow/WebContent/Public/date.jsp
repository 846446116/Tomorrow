<%@ page language="java" import="cn.ly.bean.Comment" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>添加</title>
</head>
<body>
		<h2>修改</h2>
		<form action="<%=request.getContextPath()%>/comment?action=date" method='post' >
		${comment.name }:<br>
	 		<input type="hidden"  name="id" value=${comment.id }><br> 
	 		<input type="hidden"  name="name" value=${comment.name }><br> 
	<%-- 	名称：<input type="text" name='name' value="${comment.name }"><br/> --%>
		 价格：<input type="text" name='price' value="${comment.price }"><br/>
	 	 数量：<input type="text" name='num' value="${comment.num }"><br/>
	 	 	产地：<input type="text"  name='site' value="${comment.site }"> <br>
		所有权：     <input type="text"  name='exclusive' value="${comment.exclusive }"> <br>
		   <input type='submit' value='提交'> 
		   </form>
</body>
</html>