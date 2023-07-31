<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.news.edit.button" var="edit_button" />
<fmt:message bundle="${loc}" key="local.news.delete.button" var="delete_button" />

<div class="add-table-margin">
	<table class="news_text_format">
		<tr>
			<td class="space_around_view_text"><div>
					<h3><c:out value="${requestScope.news.title}" /></h3>
				</div></td>
		</tr>
		<tr>
			<td class="space_around_view_text align_container">
				<div class="word-breaker align_left" >
					<c:out value="${requestScope.news.date}" />
					
				</div>
				<div class="word-breaker align_right"><br>
				<c:if test="${not(requestScope.author eq null)}">
				By ${requestScope.author}
				</c:if>
				<c:if test="${requestScope.author eq null}">
				By unknown Author
				</c:if>
				</div>
			</td>
				
		</tr>
		<tr>
			<td class="space_around_view_text"><div class="word-breaker">
					<img src="${requestScope.news.image}" alt="img"
						style="width: 50%; height: auto; margin-left: 20px" align="right" />
					<c:out value="${requestScope.news.content}" />
				</div></td>
		</tr>
	</table>
</div>

<c:if test="${sessionScope.role eq 'admin' || sessionScope.role eq 'editor'}">
	<div class="view-news-grid-container">
		<form action="controller" method="post">
			<input type="hidden" name="command" value="go_to_add_edit_news_page" /> 
			<input type="hidden" name="id" value="${requestScope.news.id}" /> 
			<input type="hidden" name="presentation" value="editNews" />
			<input type="submit" class="button grey" value="${edit_button}" />
		</form>

		<form action="controller" method="post">
			<input type="hidden" name="command" value="do_delete_news" /> 
			<input type="hidden" name="id" value="${requestScope.news.id}" />
			<input type="submit" class="button transperent" value="${delete_button}" />
		</form>
	</div>
</c:if>