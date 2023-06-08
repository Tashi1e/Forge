<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet" type="text/css" href="styles/header.css">

<div class="wrapper">
	<div class="newstitle">News management</div>


	<div class="local-link">

		<div align="right">

			<a href=""> en </a> &nbsp;&nbsp; 
			<a	href=""> ru </a>
		</div>

		<c:if test="${not (sessionScope.user eq 'active')}">

			<div align="right">
				<form action="controller" method="post" id="box">
			<input type="hidden" name="command" value="do_sign_in" />
			<input type="checkbox" id="checkbox" style="display: none" />
			<input type="button" value="Login" id="login" /> 
			<span class="ph-container"> 
				<input type="text" name="login" id="logName" class="text" placeholder=" " autocomplete="new-username" /> 
				<label for="logName">Login</label>
			</span> 
			<span class="ph-container"> 
			    <input type="password" name="password" id="password" class="text" placeholder=" " autocomplete="new-password" /> 
				<label for="password">Password</label>
			</span> 
			<input type="button" value="Sign-up" id="registration" />
					
					<br />
					<c:if test="${not (requestScope.AuthenticationError eq null)}">
						<font color="red"> 
						   <c:out value="${requestScope.AuthenticationError}" />
						</font> 
					</c:if>
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

<script type="text/javascript" src="script/header.js"></script>
