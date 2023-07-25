<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.login.button.name" var="loginButton" />
<fmt:message bundle="${loc}" key="local.signup.button.name" var="signupButton" />
<fmt:message bundle="${loc}" key="local.signin.button.name" var="signinButton" />
<fmt:message bundle="${loc}" key="local.remember.button.name" var="remembButton" />
<fmt:message bundle="${loc}" key="local.signout.button.name" var="signoutButton" />
<fmt:message bundle="${loc}" key="local.error.code.${sessionScope.errorCode}" var="errorMessage" />
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration</title>
<link rel="stylesheet" type="text/css" href="styles/registrationFormStyle1.css">

</head>

<body style = "background-image: url(images/newspaper_background.jpg)">
<div align="center">

<c:if test="${sessionScope.errorCode eq null}">
<h2 align = "center">Please fill the registration form</h2>
</c:if>
<c:if test="${not(sessionScope.errorCode eq null)}">
<h2 align = "center">${errorMessage}</h2>
</c:if>

<form method="post" action="controller" class = "grid-container" >
<input type="hidden" name="command" value="do_registration">

<p class = "ph-container">
<input type="text" name="firstname" id = "first" placeholder=" " autocomplete = "new-name">
<label for = "first">First Name</label>
</p>
<p class = "ph-container">
<input type="text" name="lastname" id = "last" placeholder=" " autocomplete = "new-name">
<label for = "last">Last Name</label>
</p>

<p class = "ph-container">
<input type="text" name="nickname" id = "nick" placeholder=" " autocomplete = "new-name">
<label for = "nick">Nick Name</label>
</p>
<p class = "ph-container">
<input type="email" name="email" id = "email" placeholder=" " required autocomplete = "new-email">
<label for = "email">E-mail</label>
</p>

<p class = "ph-container">
<input type="text" name="login" id = "login" placeholder=" " autocomplete = "new-name">
<label for = "login">Login</label>
</p>
<br>

<p class = "ph-container">
<input type="password" name="password" id="pass" placeholder=" " required autocomplete = "new-password">
<label for="pass">Password</label>
</p>
<p class = "ph-container">
<input type="password" id="passRep" placeholder=" " required autocomplete = "new-password">
<label for="passRep">Repeat Password</label>
</p>
<p>
<button type="submit">Register</button>
</p>
<p>
<button type="button" onclick = "document.location = 'controller?command=go_to_base_page'">Back</button>
</p>
</form>
</div>
</body>
</html>