<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="body-title">
	<a href="">News >> </a> View News
</div>

<div class="add-table-margin">
	<table class="news_text_format">
		<tr>
<!-- 			<td class="space_around_title_text">News Title</td> -->

			<td class="space_around_view_text"><div style="text-align: center;">
					<h3><c:out value="${requestScope.news.title }" /></h3>
				</div></td>
		</tr>
		<tr>
<!-- 			<td class="space_around_title_text">News Date</td> -->

			<td class="space_around_view_text align_container">
				<div class="word-breaker align_left" >
					<c:out value="${requestScope.news.newsDate }" />
					
				</div>
				<div class="word-breaker align_right"><br>
						By unknown Author
				</div>
			</td>
				
		</tr>
<!-- 		<tr> -->
<!-- 			<td class="space_around_title_text">Brief</td> -->
<!-- 			<td class="space_around_view_text"><div class="word-breaker"> -->
<%-- 					<c:out value="${requestScope.news.briefNews }" /> --%>
<!-- 				</div></td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td class="space_around_title_text">Image</td> -->
<!-- 			<td class="space_around_view_text"><div class="word-breaker"> -->
<%-- 					<img src="${requestScope.news.imagePath}" alt="img" --%>
<!-- 						style="width: 100%; height: auto" /> -->
<!-- 				</div></td> -->
<!-- 		</tr> -->
		<tr>
<!-- 			<td class="space_around_title_text">Content</td> -->
			<td class="space_around_view_text"><div class="word-breaker">
					<img src="${requestScope.news.imagePath}" alt="img"
						style="width: 50%; height: auto; margin-left: 20px" align="right" />
					<c:out value="${requestScope.news.content }" />
				</div></td>
		</tr>
	</table>
</div>


<c:if test="${sessionScope.role eq 'admin'}">
	<div class="first-view-button">
		<form action="controller" method="post">
			<input type="hidden" name="command" value="edit" /> <input
				type="hidden" name="id" value="${news.idNews}" /> <input
				type="submit" value="Edit" />
		</form>
	</div>

	<div class="second-view-button">
		<form action="controller" method="post">
			<input type="hidden" name="command" value="delete" /> <input
				type="hidden" name="id" value="${news.idNews}" /> <input
				type="submit" value="Delete" />
		</form>
	</div>
</c:if>