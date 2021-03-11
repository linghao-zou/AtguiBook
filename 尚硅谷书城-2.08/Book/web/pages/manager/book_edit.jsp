<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>编辑图书</title>
<link type="text/css" rel="stylesheet" href="http://localhost:8080/Book/static/css/style.css" >
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
	
	input {
		text-align: center;
	}
</style>
</head>
<body>
		<div id="header">
			<img class="logo_img" alt="" src="http://localhost:8080/Book/static/img/logo.gif" >
			<span class="wel_word">编辑图书</span>
			<div>
				<a href="http://localhost:8080/Book/bookServlet?action=page">图书管理</a>
				<a href="http://localhost:8080/Book/pages/manager/order_manager.jsp">订单管理</a>
				<a href="http://localhost:8080/Book/index.jsp">返回商城</a>
			</div>
		</div>
		
		<div id="main">
			<form action="http://localhost:8080/Book/bookServlet">
				<input type="hidden" name="pageNo" value="${param.pageNo}">
				<input type="hidden" name="action" value="${param.method}">
				<input type="hidden" name="id" value="${requestScope.book.id}">
				<table>
					<tr>
						<td>名称</td>
						<td>价格</td>
						<td>作者</td>
						<td>销量</td>
						<td>库存</td>
						<td colspan="2">操作</td>
					</tr>		
					<tr>
						<td><input name="book_name" type="text" value="${requestScope.book.name}"/></td>
						<td><input name="book_price" type="text" value="${requestScope.book.price}"/></td>
						<td><input name="book_author" type="text" value="${requestScope.book.author}"/></td>
						<td><input name="book_sales" type="text" value="${requestScope.book.sales}"/></td>
						<td><input name="book_stock" type="text" value="${requestScope.book.stock}"/></td>
						<td><input type="submit" value="提交"/></td>
					</tr>	
				</table>
			</form>
			
	
		</div>

		<%@ include file="/pages/common/foot.jsp"%>

</body>
</html>