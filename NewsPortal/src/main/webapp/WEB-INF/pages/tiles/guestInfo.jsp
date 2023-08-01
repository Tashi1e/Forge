<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.locale}" />


<!-- guest info -->

<!-- <div class="body-title"> -->
<!-- 	 Latest News -->
<!-- </div> -->

<form>
	<c:forEach var="news" items="${requestScope.news}">
		<div class="single-news-wrapper">
			<div class="single-news-header-wrapper">
				<div class="news-title">
					<h3><c:out value="${news.title}" /></h3>
				</div>
				<div class="news-date">
<%-- 					<c:out value="${news.date}" /> --%>
						<fmt:timeZone value="${sessionScope.locale}">
						<fmt:formatDate type = "date" dateStyle = "short" value = "${news.date}"/>
						</fmt:timeZone>
				</div>

				<div class="news-content">
					<c:out value="${news.brief}" />
				</div>
			</div>
		</div>

	</c:forEach>
 
	<div class="no-news">
		<c:if test="${requestScope.news eq null}">
        No news.
	</c:if>
	</div>

</form>
