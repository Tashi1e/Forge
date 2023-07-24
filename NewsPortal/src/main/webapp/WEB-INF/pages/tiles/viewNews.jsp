<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="body-title">
	View News
</div>

<div class="add-table-margin">
	<table class="news_text_format">
		<tr>
			<td class="space_around_view_text"><div style="text-align: center;">
					<h3><c:out value="${requestScope.news.title }" /></h3>
				</div></td>
		</tr>
		<tr>
			<td class="space_around_view_text align_container">
				<div class="word-breaker align_left" >
					<c:out value="${requestScope.news.date }" />
					
				</div>
				<div class="word-breaker align_right"><br>
				<c:if test="${requestScope.author != null}">
				By ${requestScope.author}
				</c:if>
				<c:if test="${requestScope.author == null}">
				By unknown Author
				</c:if>
				</div>
			</td>
				
		</tr>
		<tr>
			<td class="space_around_view_text"><div class="word-breaker">
					<img src="${requestScope.news.image}" alt="img"
						style="width: 50%; height: auto; margin-left: 20px" align="right" />
					<c:out value="${requestScope.news.content }" />
				</div></td>
		</tr>
	</table>
</div>


<c:if test="${sessionScope.role eq 'admin' || sessionScope.role eq 'editor'}">
	<div class="view-news-grid-container">
		<form action="controller" method="post">
			<input type="hidden" name="command" value="do_edit_news" /> 
			<input type="hidden" name="id" value="${news.id}" /> 
			<input type="submit" class="button grey all_buttons_size" value="Edit" />
		</form>

		<form action="controller" method="post">
			<input type="hidden" name="command" value="do_delete_news" /> 
			<input type="hidden" name="id" value="${news.id}" /> 
			<input type="submit" class="button transperent all_buttons_size" value="Delete" />
		</form>
	</div>
</c:if>