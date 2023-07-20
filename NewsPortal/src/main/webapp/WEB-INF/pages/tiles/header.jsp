
<link rel="stylesheet" type="text/css" href="./styles/loginFormStyles123.css">

<div class="header-grid-container">

	<div class="header-grid-item-logo">
		<img alt="News Portal" src="images/news_portal_logo.png"
			height="100px">
	</div>

	<div class="header-grid-item-message">2</div>

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
	<div class="header-grid-item" align="right">
	${sessionScope.user_info.nickName}
	</div>
	<div class="header-grid-item-login">5</div>
</div>