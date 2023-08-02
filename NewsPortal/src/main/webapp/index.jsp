<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<body>
	<c:if test="${not(sessionScope.firstEnter eq null)}">
	<c:redirect url="controller?command=do_sign_in"/>
	</c:if>
	<c:if test="${sessionScope.firstEnter eq null}">
	<c:redirect url="controller?command=go_to_base_page"/>
	</c:if>
</body>
</html>