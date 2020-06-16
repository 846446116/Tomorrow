<%@ page language="java" import="java.util.*,cn.ly.bean.Comment"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>

<script type="text/javascript" 
src="${pageContext.request.contextPath }/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
/* 	$().ready(function(){
		
	}); */
	function toPages(currentPage){
		//alert(currentPage);
		var form = $("#serachForm");
		if(currentPage){
			
			//提交搜索条件所在的表单
			//获取表达中的值
			/* var name = $(".u_name").val();
			var sex = $(".u_sex[checked='checked']").val();
			var jifen1 = $(".jifen1").val();
			var jifen2 = $(".jifen2").val(); */
			
			$(".page").val(currentPage);
			//alert(form.action);
		}else{
			//$(".jumpPage").val();
			$(".page").val($(".jum").val());
		}
		form.submit();
	}
	
</script>

<body>
	<h2>用户列表</h2>
	<h4>选择搜索条件</h4>
	<form id="serachForm" action="${pageContext.request.contextPath }/comment?action=query" 
		method="post">
		<input name="page" class="page" type="hidden">
		名称：<select class="size" name="site" size="">
					<option></option>
			<option value="秋日森林">秋日森林</option>
			<option value="沙石堡">沙石堡</option>
			<option value="白树高地">白树高地</option>
			<option value="茅斯沼泽">茅斯沼泽</option>
			<option value="多贝雪山">多贝雪山</option>
			<option value="圣托帕尼">圣托帕尼</option>
			<option value="红杉镇">红杉镇</option>
			<option value="莱文市">莱文市</option>
			<option value="营地">营地</option>

		</select>
		所有权：<input class="exclusive" type="radio" name="exclusive" value="专属"
			<c:if test="${param.exclusive=='专属' }">checked="checked"</c:if>>专属
			<input type="radio" class="exclusive" name="exclusive" value="公共"
			<c:if test="${param.exclusive=='公共' }">checked="checked"</c:if>>公共|
		数量：<input name="num1" class="num1" value="${param.num1 }">---
		<input name="num2" class="num2" value="${param.num2 }">
		<!--因为num1在域对象request中 所以用el  -->
		<input type="submit" value="搜索">
	</form>
	<br>
	<%-- <%=request.getAttribute("woodList")%> --%>
	<%
		//List<Comment> commentList =(List<Comment>)request.getAttribute("commentList");
	%>
	<%-- <%=woods %> --%>
	<table>
		<thead>
			<tr>
				<td>序号</td>
				<td>名称</td>
				<td>价格</td>
				<td>数量</td>
				<td>产地</td>
				<td>所有权</td>
			</tr>
		</thead>
		<tbody>
			<%-- 		<% for(int i=0;i<cml.size();i++){%>
			<tr>
				<td><%=cml.get(i).getId() %></td>
				<td><%=cml.get(i).getName() %></td>
				<td><%=cml.get(i).getPrice() %></td>
				<td><%=cml.get(i).getNum() %></td>
				<td><a href="<%=request.getContextPath()%>/comment?atcion=del&id=<%=cml.get(i).getId()%>">删除</a></td>
				<td><a href="<%=request.getContextPath()%>/wood/dd?id=<%=cml.get(i).getId()%>">修改</a></td>
			</tr>
		<% } %> --%>

			<c:forEach items="${pages.list }" var="com">
				<tr>
					<td>${com.id }</td>
					<td>${com.name }</td>
					<td>${com.price }</td>
					<td>${com.num }</td>
					<td>${com.site }</td>
					<td>${com.exclusive }</td>
					<td><a
						href="${pageContext.request.contextPath }/comment?action=del&id=${com.id }">删除</a></td>
					<td><a
						href="${pageContext.request.contextPath }/wood/dd?id=${com.id }">修改</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<%-- 第一个思路 把每页数写出来
 <a href="${pageContext.request.contextPath }/comment?action=query&page=1">1</a>&nbsp;&nbsp;&nbsp;&nbsp;
<a href="${pageContext.request.contextPath }/comment?action=query&page=2">2</a>&nbsp;&nbsp;&nbsp;&nbsp;
<a href="${pageContext.request.contextPath }/comment?action=query&page=3">3</a>&nbsp;&nbsp;&nbsp;&nbsp;
 --%>

	<!--第二个思路 for循环  -->
	<%-- 
<c:forEach begin="1" end="${toPage }" var="page">
<a href="${pageContext.request.contextPath }/comment?action=query&page=${page }">${page }</a>&nbsp;&nbsp;
</c:forEach>
 --%>

	<!--第三个思路 控制下面跳转编号 由当前页面  -->
	<%-- 
 <c:if test="${currentPage-1>0 }">
 <a href="${pageContext.request.contextPath }/comment?action=query&page=${currentPage-1 }">${currentPage-1 }</a>&nbsp;&nbsp;
 </c:if>
<a href="${pageContext.request.contextPath }/comment?action=query&page=${currentPage }">${currentPage }</a>&nbsp;&nbsp;
<c:if test="${currentPage+1<=toPage }">
<a href="${pageContext.request.contextPath }/comment?action=query&page=${currentPage+1 }">${currentPage+1 }</a>&nbsp;&nbsp;
</c:if>
 --%>
	<!--第四个思路 把分页的数据存在一个类中  -->
	<a href="javaScript:void(0);" onclick="toPages(1)">首页</a>&nbsp;&nbsp;
	<a href="javaScript:void(0);" onclick="toPages(${pages.prePage })">上一页</a>&nbsp;&nbsp;
	<a href="javaScript:void(0);" onclick="toPages(${pages.prePage })">${pages.prePage }</a>&nbsp;&nbsp;
	<a href="javaScript:void(0);" onclick="toPages(${pages.currentPage })">${pages.currentPage }</a>&nbsp;&nbsp;
	<a href="javaScript:void(0);" onclick="toPages(${pages.nextPage })">${pages.nextPage }</a>&nbsp;&nbsp;
	<a href="javaScript:void(0);" onclick="toPages(${pages.nextPage })">下一页</a>&nbsp;&nbsp;
	<a href="javaScript:void(0);" onclick="toPages(${pages.toPage})">尾页</a>&nbsp;&nbsp;
	<h5>当前第${pages.currentPage }页,共${pages.toPage }页</h5>
	<form action="${pageContext.request.contextPath }/comment?action=query"
		method="post">
		<input name="jum" class="jum"> 
		<input type="button" onclick="toPages()" value="跳">
	</form>
</body>
</html>