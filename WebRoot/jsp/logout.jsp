<%@ page language="java" pageEncoding="UTF-8" errorPage="error.jsp" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%
    session.removeAttribute("session_username");
    session.invalidate();
%>
