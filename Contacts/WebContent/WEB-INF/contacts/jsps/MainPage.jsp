<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
	<body>
		<h3>Contacts</h3>
		<a href="AddAContact">Add A Contact</a>
		<table border="1">
		<c:set var="c" value="0"/>
			<c:forEach items="${ contactsList }" var="list">
				<c:if test="${ !fn:startsWith(fn:toUpperCase(list.name),c) }">
					<tr><th align="left" colspan="2">
						${ c=fn:toUpperCase(fn:substring(list.name,0,1)) }
						</th>
					</tr>
				</c:if>
				<tr>
					<td><a href="ShowContact?name=${ list.name }">${ list.name }</a></td><td><a href="tel:${ list.number }">${ list.number }</a></td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>