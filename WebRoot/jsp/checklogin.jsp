<%@ page language="java" import="com.hbut.user.service.UserServiceImpl" pageEncoding="UTF-8" errorPage="error.jsp" %>
<%@page import="com.hbut.user.vo.User" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    //LoginService ls = new LoginServiceImple();
    UserServiceImpl us = new UserServiceImpl();
    //System.out.println("+check+" + username + " " + password);
    User u = us.checkLogin(username, password);
    //System.out.println("+login+");
    if (u != null) {
        out.println(1); //success

        //System.out.println("--login 1-"+username);
        session.setAttribute("session_username", username);
        //us.userLogin(uname);  //只能放在这，不能放在service中。。。。。？
    } else {
        //out.println(0);
        //System.out.println("--login 0-"+username);
    }
%>
