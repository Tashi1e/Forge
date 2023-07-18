<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.menu.welcome" var="welcome_guest" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html lang="ru, en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="script/validation.js"></script>
<title>
<fmt:message bundle="${loc}" key="local.headline.title" />
</title>

<link rel="stylesheet" type="text/css" href="./styles/newsStyle21.css">

</head>
<body style="background-image: url(images/newspaper_background.jpg)">
	<div class="page" >
		<div class="header">
			<c:import url="/WEB-INF/pages/tiles/header.jsp" />
		</div>

		<div class="base-layout-wrapper">
			<div class="menu">

				<c:if test="${sessionScope.user_active == false}">
				<br/>
				<h4 style = "padding-left: 15px">
				    ${welcome_guest}
				    </h4> 
					<%-- <c:import url=""></c:import> --%>
				</c:if>
				<c:if test="${sessionScope.user_active == true}">
					<c:import url="/WEB-INF/pages/tiles/menu.jsp" />
				</c:if>
		</div>

		<div class="content" style="
		background-image: url(images/newspaper_design_cut1.jpg)">

				<c:if test="${sessionScope.user_active == false}">
					<c:import url="/WEB-INF/pages/tiles/guestInfo.jsp" />
				</c:if>
				<c:if test="${sessionScope.user_active == true}">
					<c:import url="/WEB-INF/pages/tiles/body.jsp" />
				</c:if>


			</div>
		</div>

		<div class="footer">

			<c:import url="/WEB-INF/pages/tiles/footer.jsp" />
		</div>
	</div>
</body>
</html>