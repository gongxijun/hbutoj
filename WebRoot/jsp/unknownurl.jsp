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
    <title>Welcome to HBUT Online Judge System</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="expires" content="-1">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta http-equiv="keywords" content="HBUT,ACM,OnlineJudge,Program Contest">
    <meta http-equiv="description" content="HuBei University of Technology Online Judge System for ACM">
    <link rel="Shortcut Icon" href="./img/ico/favicon.ico" type="image/x-icon" />
    <link href="css/styles.css" type="text/css" rel="stylesheet">
    <script type="text/javascript" src="js/jquery-1.3.2.js"></script>
    <script type="text/javascript" src="js/hbutoj.js"></script>
    <script type="text/javascript">
    </script>
</head>

<body>
<jsp:include page="head.jsp"></jsp:include>
<div id="body">
    <div id="content" class="content-with-sidebar round0123" style="position: relative">
        <b><s:text name="unknow_url"></s:text></b>
    </div>
    <jsp:include page="/jsp/footer.jsp"></jsp:include>
</div>
</body>
</html>
