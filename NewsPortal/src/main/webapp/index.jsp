<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<body>
${sessionScope.firstEnter = true}
	<c:redirect url="controller?command=go_to_base_page"/>
</body>
</html>