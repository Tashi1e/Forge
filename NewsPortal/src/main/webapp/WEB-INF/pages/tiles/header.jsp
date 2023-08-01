<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="localization.locale" var="loc" />

<fmt:message bundle="${loc}" key="local.login.button.name" var="loginButton" />
<fmt:message bundle="${loc}" key="local.signup.button.name" var="signupButton" />
<fmt:message bundle="${loc}" key="local.signin.button.name" var="signinButton" />
<fmt:message bundle="${loc}" key="local.remember.button.name" var="remembButton" />
<fmt:message bundle="${loc}" key="local.signout.button.name" var="signoutButton" />
<fmt:message bundle="${loc}" key="local.error.code.${sessionScope.errorCode}" var="errorMessage" />

<!-- <link rel="stylesheet" type="text/css" href="./styles/loginFormStyles123.css"> -->

<div class="header-grid-container">

	<div class="header-grid-item-logo">
		<img alt="News Portal" src="images/news_portal_logo.png"
			height="100px">
	</div>

	<div class="header-grid-item-message">
				<c:if test="${not (sessionScope.errorCode eq null)}">
					<font color="red"> 
						<c:out value="${errorMessage}" />
					</font> 
				</c:if>
	</div>

	<div class="header-grid-item" align="right">
		<form>
			<a href="controller?locale=en&command=do_change_locale" style="text-decoration: none">
			<img alt="" src="images/us.svg" style="height: 15px" />
			</a> 
			&nbsp;&nbsp; 
			<a href="controller?locale=ru&command=do_change_locale" style="text-decoration: none">
			<img alt="" src="images/ru.svg" style="height: 15px" />
			</a>
		</form>
	</div>
	
	<c:if test="${sessionScope.user_active == true}">
	<div class="header-grid-item" align="right" style="padding-top: 15px;">
	${sessionScope.user_info.nickName}
	</div>
	</c:if>
	
	<div class="header-grid-item-login" align="right">
	<c:if test="${sessionScope.user_active == false}">
				<form action="controller" method="post" id="box">
			<input type="hidden" name="command" value="go_to_registration_page" id="command" />
			<input type="checkbox" name="remember_me" value="${remembButton}" id="checkbox" style="display: none" />
			<input type="hidden" class = "button grey" value="${signinButton}" id="signin">
			<input type="button" class = "button transperent" value="${loginButton}" id="login" /> 
			<span class="ph-container"> 
				<input type="text" name="login" id="logName" class="text" placeholder=" " autocomplete="new-username" /> 
				<label for="logName"><fmt:message bundle="${loc}" key="local.login.label.name" /></label>
			</span> 
			<span class="ph-container"> 
			    <input type="password" name="password" id="password" class="text" placeholder=" " autocomplete="new-password" /> 
				<label for="password"><fmt:message bundle="${loc}" key="local.password.label.name" /></label>
			</span> 
			<input type="submit" value="${signupButton}" class="button yellow" id="registration" />
			<input type="hidden" class="registration_remember_btn" id="remember" />				
				</form>
		</c:if>
		
		<c:if test="${sessionScope.user_active == true}">
			<form action="controller" method="post" id="box">
			<input type="hidden" name="command" value="do_sign_out" />
<!-- 			<br> -->
<!-- 			<h4 align="right" style="text-shadow: 3px 3px 2px rgba(0,0,0,0.4)"> -->
<%-- 			${sessionScope.user_info.nickName} --%>
<!-- 			</h4> -->
<!-- 			<br> -->
			<input type="submit" class="button transperent" value="${signoutButton}" id="signOut" /> 
				</form>
		</c:if>
	</div>
	
</div>

<script type="text/javascript" src="./script/loginForms1234.js"></script>