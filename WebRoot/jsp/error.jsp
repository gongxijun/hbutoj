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
    <title>ERROR - HBUT Online Judge</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="expires" content="-1">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta http-equiv="keywords" content="HBUT,ACM,OnlineJudge,Program Contest">
    <meta http-equiv="description" content="HuBei University of Technology Online Judge System for ACM">
    <link rel="Shortcut Icon" href="./img/ico/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <script type="text/javascript" src="js/jquery-1.7.1.js"></script>
    <script type="text/javascript" src="js/hbutoj.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
                    $("#s_type u").bind("click", function () {
                                $("#t").attr("value", $(this).attr("rel")),
                                        $("#s_type u.curr").removeClass("curr"),
                                        $(this).addClass("curr")
                            }
                    )
                }
        );
    </script>


</head>

<body>
<s:include value="head.jsp"/>
<div id="body">

    <div id="content">
        <div class="content" style="margin: 12px auto;">
            <s:text name="errror_info"/>:
            <b><span style="color:red"><s:fielderror></s:fielderror> <br/>
	    <s:actionerror/><br/>
	    <s:property value="tip"/>
	   </span> </b>
        </div>
    </div>
    <jsp:include page="/jsp/footer.jsp"></jsp:include>
</div>
</body>
</html>
