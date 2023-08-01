<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="localization.locale" var="loc" />

<fmt:message bundle="${loc}" key="local.firstname.label" var="firstname" />
<fmt:message bundle="${loc}" key="local.lastname.label" var="lastname" />
<fmt:message bundle="${loc}" key="local.nickname.label" var="nickname" />
<fmt:message bundle="${loc}" key="local.email.label" var="email" />
<fmt:message bundle="${loc}" key="local.login.label.name" var="login" />
<fmt:message bundle="${loc}" key="local.password.label.name" var="password" />
<fmt:message bundle="${loc}" key="local.repeat.password.label" var="repeatPass" />
<fmt:message bundle="${loc}" key="local.fill.form.message" var="fillForm" />
<fmt:message bundle="${loc}" key="local.register.button.name" var="regButton" />
<fmt:message bundle="${loc}" key="local.menu.back.link" var="backButton" />
<fmt:message bundle="${loc}" key="local.error.code.${sessionScope.errorCode}" var="errorMessage" />
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration</title>
<link rel="stylesheet" type="text/css" href="styles/registrationFormStyle1.css">

</head>

<body style = "background-image: url(images/newspaper_background.jpg)">

		

<div align="center" >
		<span style="margin-left: 80%; padding: 1px">
			<a href="controller?locale=en&command=do_change_locale" style="text-decoration: none">
			<img alt="EN" src="images/us.svg" style="height: 15px" />
			</a> 
			&nbsp;&nbsp; 
			<a href="controller?locale=ru&command=do_change_locale" style="text-decoration: none">
			<img alt="RU" src="images/ru.svg" style="height: 15px" />
			</a>
		</span>

<c:if test="${sessionScope.errorCode eq null}">
<h2 align = "center">${fillForm}</h2>
</c:if>
<c:if test="${not(sessionScope.errorCode eq null)}">
<h2 align = "center">${errorMessage}</h2>
</c:if>

<form method="post" action="controller" class = "grid-container" >
<input type="hidden" name="command" value="do_registration">

<p class = "ph-container">
<input type="text" name="firstname" id = "first" placeholder=" " autocomplete = "new-name">
<label for = "first">${firstname}</label>
</p>
<p class = "ph-container">
<input type="text" name="lastname" id = "last" placeholder=" " autocomplete = "new-name">
<label for = "last">${lastname}</label>
</p>

<p class = "ph-container">
<input type="text" name="nickname" id = "nick" placeholder=" " autocomplete = "new-name">
<label for = "nick">${nickname}</label>
</p>
<p class = "ph-container">
<input type="email" name="email" id = "email" placeholder=" " required autocomplete = "new-email">
<label for = "email">${email}</label>
</p>

<p class = "ph-container">
<input type="text" name="login" id = "login" placeholder=" " autocomplete = "new-name">
<label for = "login">${login}</label>
</p>
<br>

<p class = "ph-container">
<input type="password" name="password" id="pass" placeholder=" " required autocomplete = "new-password">
<label for="pass">${password}</label>
</p>
<p class = "ph-container">
<input type="password" id="passRep" placeholder=" " required autocomplete = "new-password">
<label for="passRep">${repeatPass}</label>
</p>
<p>
<button type="submit">${regButton}</button>
</p>
<p>
<button type="button" onclick = "document.location = 'controller?command=go_to_base_page'">${backButton}</button>
</p>
</form>
</div>
</body>
</html>