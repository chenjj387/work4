<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.model.User" %>
<%@ page session="true" %>
<html>
<head>
    <title>欢迎</title>
</head>
<body>
<%
    // 从会话中获取用户信息
    User currentUser = (User) session.getAttribute("currentUser");
    if (currentUser != null) {
%>
<h2>欢迎您, <%= currentUser.getUsername() %>！</h2>
<p>登录成功！</p>
<p><a href="logout.jsp">注销</a></p>
<%
    } else {
        // 如果没有登录，重定向回登录页面
        response.sendRedirect("login.jsp");
    }
%>
</body>
</html>