<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>用户注册</title>
</head>
<body>
<h2>用户注册</h2>
<%-- 显示错误信息（如果有的话） --%>
<% if (request.getAttribute("error") != null) { %>
<p style="color:red;"><%= request.getAttribute("error") %></p>
<% } %>
<form action="register" method="post">
    用户名: <input type="text" name="username" required><br/><br/>
    密&nbsp;&nbsp;&nbsp;码: <input type="password" name="password" required><br/><br/>
    邮&nbsp;&nbsp;&nbsp;箱: <input type="email" name="email" required><br/><br/>
    <input type="submit" value="注册">
</form>
<p>已有账户？<a href="login.jsp">立即登录</a></p>
</body>
</html>