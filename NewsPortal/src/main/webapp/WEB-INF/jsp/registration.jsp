<!--<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration</title>
<link rel="stylesheet" type="text/css" href="styles/registrationFormStyle.css">

</head>

<body>
<div align="center">
<h1 align = "center">Please fill the registration form</h1>
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
<!-- <p class = "ph-container"> -->
<!-- <input type="text" name="birth" id = "birth" placeholder=" " -->
<!--  onfocus="(this.type='date')" onblur="if(this.value==''){this.type='text'}"> -->
<!-- <label for = "birth">Birth Date</label> -->
<!-- </p> -->
<!-- <p class = "ph-container"> -->
<!-- <input type="text" name="country" id="country" placeholder=" " autocomplete="new-country"> -->
<!-- <label for="tel">Country</label> -->
<!-- </p> -->
<!-- <p class = "ph-container"> -->
<!-- <input type="tel" name="tel" id="tel" placeholder=" " autocomplete = "new-tel"> -->
<!-- <label for="tel">Phone Number</label> -->
<!-- </p> -->
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
<button type="button" onclick = "document.location = 'index.jsp'">Back</button>
</p>
</form>
</div>
</body>
</html>