<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.login.button.name" var="loginButton" />
<fmt:message bundle="${loc}" key="local.signup.button.name" var="signupButton" />
<fmt:message bundle="${loc}" key="local.signin.button.name" var="signinButton" />
<fmt:message bundle="${loc}" key="local.remember.button.name" var="remembButton" />
<fmt:message bundle="${loc}" key="local.signout.button.name" var="signoutButton" />

<link rel="stylesheet" type="text/css" href="./styles/loginFormStyles123.css">

<div class="wrapper">
	<div class="newstitle"><fmt:message bundle="${loc}" key="local.header" />
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<span>

	<c:if test="${not (requestScope.AuthenticationError eq null)}">
						<font color="red"> 
						   <c:out value="${requestScope.AuthenticationError}" />
						</font> 
					</c:if>
</span>
</div>

	<div class="local-link">

		<div align="right">
<form>
			<a href="controller?local=en&command=${sessionScope.currentPage}"> <fmt:message bundle="${loc}" key="local.en.button.name" /> </a> 
			&nbsp;&nbsp; 
			<a href="controller?local=ru&command=${sessionScope.currentPage}"> <fmt:message bundle="${loc}" key="local.ru.button.name" /> </a>
			</form>
		</div>

		<c:if test="${not (sessionScope.user eq 'active')}">

			<div align="right">
				<form action="controller" method="post" id="box">
			<input type="hidden" name="command" value="do_sign_in" id="command" />
			<input type="checkbox" name="remember_me" value="${remembButton}" id="checkbox" style="display: none" />
			<input type="hidden" class = "login_btn" value="${signinButton}" id="signin">
			<input type="button" class = "login_btn" value="${loginButton}" id="login" /> 
			<span class="ph-container"> 
				<input type="text" name="login" id="logName" class="text" placeholder=" " autocomplete="new-username" /> 
				<label for="logName"><fmt:message bundle="${loc}" key="local.login.label.name" /></label>
			</span> 
			<span class="ph-container"> 
			    <input type="password" name="password" id="password" class="text" placeholder=" " autocomplete="new-password" /> 
				<label for="password"><fmt:message bundle="${loc}" key="local.password.label.name" /></label>
			</span> 
			<input type="submit" value="${signupButton}" class="registration_remember_btn" id="registration" />
			<input type="hidden" class="registration_remember_btn" id="remember" />				
				</form>
			</div>

		</c:if>
		
		<c:if test="${sessionScope.user eq 'active'}">

			<div align="right">
			<form action="controller" method="post" id="box">
			<input type="hidden" name="command" value="do_sign_out" />
			<br>
			<h4 align="right" style="text-shadow: 3px 3px 2px rgba(0,0,0,0.6)">${sessionScope.userNickName}</h4>
			<br>
			<input type="submit" value="${signoutButton}" id="signOut" /> 
				</form>
			</div>

		</c:if>
	</div>

</div>

<script type="text/javascript" src="./script/loginForms123.js"></script>
