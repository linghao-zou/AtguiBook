<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
	<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
	<script type="text/javascript">
		$(function () {
			$("a.deleteClass").click(function () {
				return confirm("你确定要删除[" + $(this).parent().parent().find("td:first").text() + "]");
			});
			$("#searchPageBTn").click(function (){
				var pageNo = $("#pn_input").val();
				//javaScript语言中提供了一个Location地址栏对象 他有一个属性
				// href 他可以获取浏览器地址栏中的地址
				location.href="http://localhost:8080/Book/bookServlet?action=page&pageNo="+pageNo;


			});
		});
	</script>
<link type="text/css" rel="stylesheet" href="http://localhost:8080/Book/static/css/style.css" >
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="http://localhost:8080/Book/static/img/logo.gif" >
			<span class="wel_word">图书管理系统</span>
			<div>
				<a href="${requestScope.page.url}">图书管理</a>
				<a href="http://localhost:8080/Book/pages/manager/order_manager.jsp">订单管理</a>
				<a href="http://localhost:8080/Book/index.jsp">返回商城</a>
			</div>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>		

			<c:forEach items="${requestScope.page.items}" var="book">
				<tr>
					<td>${book.name}</td>
					<td>${book.price}</td>
					<td>${book.author}</td>
					<td>${book.sales}</td>
					<td>${book.stock}</td>
					<td><a  href="http://localhost:8080/Book/bookServlet?action=getBook&id=${book.id}&method=update&pageNo=${requestScope.page.pageNo}">修改</a></td>
					<td><a class="deleteClass" href="http://localhost:8080/Book/bookServlet?action=delete&id=${book.id}&pageNo=${requestScope.page.pageNo}">删除</a></td>
				</tr>
			</c:forEach>

			
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="http://localhost:8080/Book/pages/manager/book_edit.jsp?method=add&pageNo=${requestScope.page.pageTotal}">添加图书</a></td>
			</tr>	
		</table>

		<%@include file="/pages/common/page_nav.jsp"%>

	</div>

	<%@ include file="/pages/common/foot.jsp"%>

</body>
</html>