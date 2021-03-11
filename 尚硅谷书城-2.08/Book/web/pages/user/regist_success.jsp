<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>

<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
</style>
</head>
<body>
		<div id="header">
				<img class="logo_img" alt="" src="static/img/logo.gif" >
				<span class="wel_word"></span>
				<div>
					<span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临尚硅谷书城</span>
					<a href="http://localhost:8080/Book/pages/order/order.jsp">我的订单</a>
					<a href="http://localhost:8080/Book/index.jsp">注销</a>&nbsp;&nbsp;
					<a href="http://localhost:8080/Book/index.jsp">返回</a>
				</div>
		</div>
		
		<div id="main">
		
			<h1>注册成功! <a href="index.jsp">转到主页</a></h1>
	
		</div>

		<%@ include file="/pages/common/foot.jsp"%>

</body>
</html>