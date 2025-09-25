<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%
    // 使当前会话失效
    session.invalidate();
    // 重定向回首页或登录页面
    response.sendRedirect("index.jsp");
%>