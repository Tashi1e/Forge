<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="ISO-8859-1"%>
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


<h2 align = "center">${fillForm}</h2>

<form method="post" action="controller" >
<input type="hidden" name="command" value="do_registration">

<table class="regFormTable">
<tr>
<td>
<span class = "ph-container">
<input type="text" class="regFormText" name="firstname" id = "first" placeholder=" " autocomplete = "new-name">
<label for = "first">${firstname}</label>
</span>
</td>
<td>
<span class = "ph-container">
<input type="text" class="regFormText" name="lastname" id = "last" placeholder=" " autocomplete = "new-name">
<label for = "last">${lastname}</label>
</span>
</td>
</tr>

<tr>
<td>
<span class = "ph-container">
<input type="text" class="regFormText" name="nickname" id = "nick" placeholder=" " autocomplete = "new-name">
<label for = "nick">${nickname}</label>
</span>
</td>
<td>
<span class = "ph-container">
<input type="email" class="regFormText" name="email" id = "email" placeholder=" " required autocomplete = "new-email">
<label for = "email">${email}</label>
</span>
</td>
</tr>

<tr>
<td>
<span class = "ph-container">
<input type="text" class="regFormText" name="login" placeholder=" " autocomplete = "new-name">
<label for = "login">${login}</label>
</span>
</td>
<td style="padding: 20px">
</td>
</tr>

<tr>
<td>
<span class = "ph-container">
<input type="password" class="regFormText" name="password" id="pass" placeholder=" " required autocomplete = "new-password">
<label for="pass">${password}</label>
</span>
</td>
<td>
<span class = "ph-container">
<input type="password" class="regFormText" id="passRep" placeholder=" " required autocomplete = "new-password">
<label for="passRep">${repeatPass}</label>
</span>
</td>
</tr>
<tr>
<td>
<br>
<span>
<input type="submit" class="yellow regFormButton" value="${regButton}" />
</span>
</td>
<td>
<br>
<span>
<input type="button" class="transperent regFormButton close" value="${backButton}" />
</span>
</td>
</tr>
</table>
</form>
