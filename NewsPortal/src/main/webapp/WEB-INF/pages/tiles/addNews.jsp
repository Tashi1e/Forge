<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="localization.local" var="loc" />

<div>
<form>

<p>
<input type="text" />
</p>

<p>
<textarea style="width: 100%; height: 50px">Brief</textarea>
</p>

<p>
<textarea style="width: 100%; height: 50px">Content</textarea>
</p>

<p>
<input />
</p>

</form>
</div>
