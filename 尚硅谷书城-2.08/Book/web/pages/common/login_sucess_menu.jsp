<%--
  Created by IntelliJ IDEA.
  User: zoulinghao
  Date: 2021/2/5
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临尚硅谷书城</span>
    <a href="http://localhost:8080/Book/pages/order/order.jsp">我的订单</a>
    <a href="http://localhost:8080/Book/userServlet?action=logout">注销</a>&nbsp;&nbsp;
    <a href="http://localhost:8080/Book/index.jsp">返回</a>
</div>
