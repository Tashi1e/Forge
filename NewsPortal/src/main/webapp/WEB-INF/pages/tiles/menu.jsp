<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="localization.locale" var="loc" />

<fmt:message bundle="${loc}" key="local.menu.news.management.title" var="news_management_title" />
<fmt:message bundle="${loc}" key="local.menu.newslist.name" var="news_list_link" />
<fmt:message bundle="${loc}" key="local.menu.addnews.name" var="add_news_link" />
<fmt:message bundle="${loc}" key="local.menu.deletenews.name" var="delete_news_link" />
<fmt:message bundle="${loc}" key="local.menu.back.link" var="back_link" />
<fmt:message bundle="${loc}" key="local.menu.news.search" var="search_ph" />

<style>
.menu_list li{
	padding-top: 7px;
}

.menu_list li a {
	color: black;
	text-shadow: 3px 3px 2px rgba(0, 0, 0, 0.4);
	text-decoration: none;
	font-family: "Times New Roman";
	font-size: 18px;
 	font-weight: bold;
	transition: all 200ms;
}

.menu_list li a:hover {
	padding-left: 15px;
}

form input, form button {
	border-style: solid;
	font-family: "Times New Roman";
	height: 27px;
	font-size: 18px;
 	border-color: black;
	background-color: transparent;
}

form #search_field {
	border-radius: 5px 0px 0px 5px;
	border-width: 1px 0px 1px 1px;
	width: 140px;
}

form #search_field:focus {
	outline: 0;
}

form #search_button {
 	position: absolute;
 	background-color: yellow;
 	border-color: black;
	border-radius: 0px 5px 5px 0px;
	border-width: 1px 1px 1px 0px;
 	height: 27px;
	width: 40px;
/* 	font-size: 22px; */
}

form #search_button:hover {
background-color: gold;
}
</style>

<div style="padding-left: 20px; padding-top: 5px;">
<%-- <span style="padding-left: 15px; font-size: 22px; font-weight: bold; text-decoration: underline">${news_management_title}</span>  --%>
<br>
<form action="controller" method="post">
<input type="hidden" name="command" value="go_to_news_list" />
<input type="search" name="keyword" id="search_field" placeholder="  ${search_ph}" />
<button id="search_button" type="submit">
<svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" fill="black" class="bi bi-search" viewBox="0 0 16 16">
  	<path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
</svg>
</button>
</form>
<br>
	<ul class="menu_list" style="list-style: none; text-align: left; padding-left: 0px">
		<li><form action="controller" method="post">
		<a href="controller?command=go_to_news_list">${news_list_link}</a>
		</form></li>
		<c:if test="${sessionScope.role eq 'admin'}">
			<li><a href="controller?command=go_to_add_edit_news_page&presentation=addNews">${add_news_link}</a></li>
			<li><form action="controller" method="post" id="delete_news_form">
			<input type="hidden" name="command" value="do_delete_news" />
			<input class="link_button" type="submit" value="${delete_news_link}" />
			</form></li>
		</c:if>
	</ul>
	<c:if test="${not(requestScope.presentation eq 'newsList')}">
	<ul class="menu_list" style="list-style: none; text-align: left; padding-left: 0px"><li>
	<a href="controller?command=go_to_news_list" style="position: absolute; bottom: 20px; left: 35px">&#8678  ${back_link}</a>
	</li></ul>
<%-- 	< class="link_button" value="&#8678  ${back_link}" style="position: absolute; bottom: 20px; left: 35px" onclick="history.back()" /> --%>
	</c:if>
	</div>


