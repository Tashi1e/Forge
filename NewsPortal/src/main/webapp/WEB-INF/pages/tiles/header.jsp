<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.login.button.name" var="loginButton" />
<fmt:message bundle="${loc}" key="local.signup.button.name" var="signupButton" />
<fmt:message bundle="${loc}" key="local.signin.button.name" var="signinButton" />
<fmt:message bundle="${loc}" key="local.remember.button.name" var="rememberButton" />
<fmt:message bundle="${loc}" key="local.header" var="header" />

<link rel="stylesheet" type="text/css" href="./styles/loginFormStyles.css">

<div class="wrapper">
	<div class="newstitle">News management
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

			<a href="controller?local=en&command=go_to_base_page"> en </a> &nbsp;&nbsp; 
			<a href="controller?local=ru&command=go_to_base_page"> ru </a>
		</div>

		<c:if test="${not (sessionScope.user eq 'active')}">

			<div align="right">
				<form action="controller" method="post" id="box">
			<input type="hidden" name="command" value="go_to_registration_page" id="command" />
			<input type="checkbox" name="remember_me" id="checkbox" style="display: none" />
			<input type="hidden" value="${signinButton}" id="sign_in">
			<input type="button" value="${loginButton}" id="login" /> 
			<span class="ph-container"> 
				<input type="text" name="login" id="logName" class="text" placeholder=" " autocomplete="new-username" /> 
				<label for="logName">Login</label>
			</span> 
			<span class="ph-container"> 
			    <input type="password" name="password" id="password" class="text" placeholder=" " autocomplete="new-password" /> 
				<label for="password">Password</label>
			</span> 
			<input type="submit" value="${signupButton}" id="registration" />
			<input type="hidden" value="${rememberButton}" id="remember" />				
				</form>
			</div>

		</c:if>
		
		<c:if test="${sessionScope.user eq 'active'}">

			<div align="right">
			
			<form action="controller" method="post" id="box">
			<input type="hidden" name="command" value="do_sign_out" />
			<input type="submit" value="Sign Out" id="signOut" /> 
			<span class="ph-container"> 
			    <input type="text" class="text" /> 
			</span> 
					
				</form>
			
				<!-- <form action="controller" method="post" id="box">
					<input type="hidden" name="command" value="do_sign_out" /> 
					<input type="submit" value="Sign Out" id="signOut"/><br />
				</form> -->
			</div>

		</c:if>
	</div>

</div>

<script type="text/javascript" src="./script/loginForm.js"></script>
