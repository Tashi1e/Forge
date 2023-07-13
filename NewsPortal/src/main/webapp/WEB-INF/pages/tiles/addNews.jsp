<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="localization.local" var="loc" />

<style>

textarea {
	width: 98%; 
	height: 50px;
	resize: none; 
	margin-right: 10px;
	background-color: transparent;
}

textarea#content{
	height: 500px;
}

#publish{
	border: 2px;
	border-radius: 5px;
	border-style: solid;
	border-color: grey;
	font-family: "Times New Roman";
	background-color: transparent;
	font-size: 16px;
	font-weight: bold;
	height: 30px;
	transition: all 200ms;
	color: rgb(90, 90, 100);
	width: 120px;
}
#publish:hover{
	scale: 1.02 1.1;
	border-color: rgb(105, 105, 115);
	background-color: rgb(105, 105, 115);
	color: white;
}
</style>

<div>
<form>

<p>
<input type="text" />
</p>
<br>
<p>
<textarea class="single-news-header-wrapper">Brief</textarea>
</p>
<br>
<p>
<textarea id="content" class="single-news-header-wrapper">Content</textarea>
</p>
<br>
<p>
<input type="submit" id="publish" value="Publish" />
</p>

</form>
</div>
