<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
.menu_list li{
	padding-top: 7px;
}

.menu_list li a {
	color: dimgrey;
	text-shadow: 3px 3px 2px rgba(255,255,0,0.3);
	text-decoration: none;
	font-family: "Times New Roman";
	font-size: 22px;
	font-weight: bold;
	transition: all 200ms;
/* 	font-style: italic; */
}

.menu_list li a:hover {	
	padding-left: 15px;
}
</style>
<!-- <div > -->
<!-- 	<div class="menu-title-wrapper"> -->
<!-- 		<div class="menu-title"> -->
<!-- 		       News -->
<!-- 		</div> -->
<!-- 	</div> -->
<br/><br/>
<!-- 	<div > -->
<!-- 		<div class="list-menu-wrapper" style="float: right;"> -->
			<ul class="menu_list" style="list-style: none; text-align: left">
				<li >
				<a href="controller?command=go_to_news_list">news list</a><br />
				</li>

				<c:if test="${sessionScope.role eq 'admin'}">
				   <li class="menu_list_elem">
				
				    <a href="">add news </a>
                
                   <br />
					
				</li></c:if>
			</ul>
<!-- 		</div> -->
		<div class="clear"></div>
<!-- 	</div> -->
	<!--  grey free space at the bottom of menu -->
	<div style="height: 25px;"></div>
<!-- </div> -->

