<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<html>
<head>
<meta charset="UTF-8">
<title>Error</title>

<style type="text/css">

html {
background-image: url(images/error_background.jpg);
background-repeat: no-repeat;
background-position: center center;
background-attachment: fixed;
-webkit-background-size: cover;
-moz-background-size: cover;
-o-background-size: cover;
background-size: cover;
}

div{
font-family: "Times New Roman";
font-size:32px;
position: absolute;
top: 50%;
left: 50%;
margin-right: -50%;
transform: translate(-50%, -50%)
}

</style>

</head>

<body>
<div align="center">
<h1 align = "center"><font color="red">
<c:if test="${not (requestScope.errorCode eq null)}">
<fmt:message bundle="${loc}" key="local.error.code.${requestScope.errorCode}" />
</c:if>
<c:if test="${requestScope.errorCode eq null}">
Unknown Error!
</c:if>
</font></h1>
</div>
</body>
</html>