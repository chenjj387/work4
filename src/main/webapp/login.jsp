<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>用户登录</title>
</head>
<body>
<h2>用户登录</h2>
<%-- 显示错误信息（如果有的话） --%>
<% if (request.getAttribute("error") != null) { %>
<p style="color:red;"><%= request.getAttribute("error") %></p>
<% } %>
<form action="login" method="post">
    用户名: <input type="text" name="username" required><br/><br/>
    密&nbsp;&nbsp;&nbsp;码: <input type="password" name="password" required><br/><br/>
    <input type="submit" value="登录">
</form>
<p>还没有账户？<a href="register.jsp">立即注册</a></p>
</body>
</html>