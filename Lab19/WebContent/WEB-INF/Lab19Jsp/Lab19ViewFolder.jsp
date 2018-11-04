<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<c:choose>
		<c:when test="${fn:length(lab19List)>0}">
		<c:forEach items="${ lab19List }" var="arr">
			<c:choose>
				<c:when test="${ arr.folder }">
					<p><a href="Lab19ViewFolder?id=${ arr.id }">\ ${ arr.name }</a></p>
				</c:when>
				<c:otherwise>
					<p>${ arr.name }</p>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		</c:when>
		<c:otherwise>
			<p>This folder is empty</p>
		</c:otherwise>
	</c:choose>
</body>
</html>