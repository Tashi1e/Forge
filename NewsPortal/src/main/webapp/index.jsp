<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.login.button.name" var="loginButon" />
<fmt:message bundle="${loc}" key="local.signup.button.name" var="sinupButton" />

<html>
<body>
	<c:redirect url="controller?command=go_to_base_page"/>
</body>
</html>