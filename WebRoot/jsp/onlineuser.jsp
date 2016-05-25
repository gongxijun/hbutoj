<%--
  Created by IntelliJ IDEA.
  User: gxjun
  Date: 2016/5/25
  Time: 23:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">

<html>
<head>
    <base href="<%=basePath%>">
    <title>HBUT-OnlineUser</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="expires" content="-1">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta http-equiv="keywords" content="HBUT,ACM,OnlineJudge,Program Contest">
    <meta http-equiv="description" content="HuBei University of Technology Online Judge System for ACM">
    <link href="css/styles.css" type="text/css" rel="stylesheet">
    <link rel="Shortcut Icon" href="./img/ico/favicon.ico" type="image/x-icon" />
    <script type="text/javascript" src="js/jquery-1.7.1.js"></script>
    <script type="text/javascript" src="js/hbutoj.js"></script>


</head>

<body>
<jsp:include page="/jsp/head.jsp"></jsp:include>
<div id="body">
    <div id="sidebar">
        <jsp:include page="/jsp/sidebar.jsp"></jsp:include>
    </div>
    <div id="content" class="content-with-sidebar round0123">
<div class="sidebox roundbox">
    <div class="roundbox-lt">&nbsp;</div>
    <div class="roundbox-rt">&nbsp;</div>
    <div class="top-link" style="border-bottom: 1px solid #b9b9b9;">
        <div class="title-sidebox" style="width: 100%"><s:text name="sidebar.onlineusers"/> (<span
                class="user-sum">0</span>)
        </div>
    </div>
    <div class="sidebar-online-users" style="padding: 6px;">
        <img alt="Loading..." src='img/loader.gif'/>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $.post(
                "onlineUsers",
                function (json) {
                    if (json.success != true) {
                        alert(json["error"]);
                        return;
                    }
                    var size = 0;
                    var opt = "";

                    for (var i in json.online_users) {

                        if (json.online_users[i].statusFlag == 0) {
                            continue;
                        }
                        size++;

                        opt += "<b><a style='font-size:12px; padding:0 10 0 0px;' title='Last visit at " +
                        json.online_users[i].lastAccessTime + "' href='profile/" + json.online_users[i].username + "'>" +
                        i + "</a></b><br>";
                    }

                    $(".user-sum").html(size);
                    if (size == 0) {
                        opt = "<span style='font: 11px/26px Monaco, monospace;color: #454545;'>No user online.</span>";
                    }
                    $("div.sidebar-online-users").html(opt);
                },
                "json"
        );
    });
</script>
</div>
</div>
</body>
</html>
