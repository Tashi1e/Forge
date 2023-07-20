<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<div class="body-title">
<!-- 	<a href="">News >> </a>  -->
	News List
</div>

<form action="" method="post">
	<c:forEach var="news" items="${requestScope.news}">
		<div class="single-news-wrapper">
			<div class="single-news-header-wrapper">
				<div class="news-title">
				<a href="controller?command=go_to_view_news&id=${news.id}" style="color:black; text-decoration:none">
					<h3><c:out value="${news.title}" /></h3>
					</a>
				</div>
				<div class="news-date">
					<c:out value="${news.date}" />
				</div>

				<div class="news-content">
					<c:out value="${news.brief}" />
				</div>
				<div class="news-link-to-wrapper">
					<div class="link-position">
						<c:if test="${sessionScope.role eq 'admin'}">
						      <a href="controller?command=go_to_edit_news_page&id=${news.id}&presentation=editNews" style="color:black; text-decoration:none">
						      	<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="grey" class="bi bi-pencil-square" viewBox="0 0 16 16">
  								<path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 
  								9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
  								<path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 
  								1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
								</svg>
						      </a> 
   					         <input type="checkbox" name="newsId" value="${news.id}" id="chbx${news.id}" form="delete_news_form" style="display: none"/>
   					         <label for="chbx${news.id}" style="position: relative; display: inline-block;">

   					         <span>
   					                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="grey" class="bi bi-app" viewBox="0 0 16 16">
  									<path d="M11 2a3 3 0 0 1 3 3v6a3 3 0 0 1-3 3H5a3 3 0 0 1-3-3V5a3 3 0 0 1 3-3h6zM5 1a4 4 0 0 0-4 4v6a4 
  									4 0 0 0 4 4h6a4 4 0 0 0 4-4V5a4 4 0 0 0-4-4H5z"/>
									</svg>
									</span>
									<span style="position: absolute; left: 0 ; font-size: 18px; visibility: hidden" id="news_check_icon_label_${news.id}">
									<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="grey" class="bi bi-check-lg" viewBox="0 0 16 16">
  									<path d="M12.736 3.97a.733.733 0 0 1 1.047 0c.286.289.29.756.01 1.05L7.88 12.01a.733.733 0 0 1-1.065.02L3.217 
  									8.384a.757.757 0 0 1 0-1.06.733.733 0 0 1 1.047 0l3.052 3.093 5.4-6.425a.247.247 0 0 1 .02-.022Z"/>
									</svg>
									</span>
							</label>
							
							<script type="text/javascript">
									let checkBox${news.id} = document.getElementById('chbx' + ${news.id});
									let checkIcon${news.id} = document.getElementById('news_check_icon_label_' + ${news.id});
										checkBox${news.id}.onclick = function(){
										if (checkBox${news.id}.checked) {
											checkIcon${news.id}.style.visibility = 'visible';
										} else {
											checkIcon${news.id}.style.visibility = 'hidden';
										}
									}
							</script>
							
   					    </c:if>
					</div>
				</div>

			</div>
		</div>

	</c:forEach>

	<!-- 	<logic:notEmpty name="newsForm" property="newsList">
		<div class="delete-button-position">
			<html:submit>
				<bean:message key="locale.newslink.deletebutton" />
			</html:submit>
		</div>
	</logic:notEmpty> -->

	<div class="no-news">
		<c:if test="${requestScope.news == null}">
        No news.
	</c:if>
	</div>
</form>


