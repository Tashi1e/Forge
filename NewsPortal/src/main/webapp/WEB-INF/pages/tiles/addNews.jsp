<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="localization.local" var="loc" />

<style>
#edit_field{
	width: 95%;
	height: 70vh;
	padding-top: 20px;
}

label {
 	margin_top: 10px; 
 	font-weight: bold; 
 	font-style: italic; 
}

textarea#title {
	height: 1.2em;
}
	
textarea#brief {
	height: 3.6em;
}

textarea#content {
	height: 38vh;
}

input#publish {
	width: 100px;
}
</style>

<c:if test=""></c:if>

<div id="edit_field">
<form action="controller" method="post" >
<c:if test="${reqestScope.presentation == 'editNews'}">
<input type="hidden" name="command" value="do_edit_news" />
</c:if>
<c:if test="${reqestScope.presentation == 'addNews'}">
<input type="hidden" name="command" value="do_add_news" />
</c:if>
<p> 
<label for="title">Title</label>
<textarea name="title" class="text_edit" id="title" placeholder="Enteer Your title here">${requestScope.news.title}</textarea>
</p>
<p>
<label for="brief">Brief</label>
<textarea name="brief" class="text_edit" id="brief" placeholder="Enteer Your title here">${requestScope.news.brief}</textarea>
</p>
<p>
<label for="content">Content</label>
<textarea name="content" class="text_edit" id="content" placeholder="Enteer Your content here">${requestScope.news.content}</textarea>
</p>
<p>
<input type="submit" id="save_button" class="button grey all_buttons_size" value="Save" />
</p>

</form>
</div>
