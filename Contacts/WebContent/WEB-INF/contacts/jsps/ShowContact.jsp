<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h3><a href="MainPage">Contacts</a> - ${ name }</h3>
	<form action="ShowContact" method="post">
	<table border="1">
	
		<c:forEach items="${ contactsList }" var="list">
			<c:if test="${ list.number!=null }">
				<c:if test="${ list.name!='name' }">
					<input type="hidden" value="${ list.number }" name="name"/>
				</c:if>
				<tr>
					<th style="text-align: left;">${ list.name }</th>
					<td>${ list.number }</td>
					<td></td>
				</tr>
			</c:if>
		</c:forEach>
		
		<tr>
			<td><input type="text" name="cName"> </td>
			<td><input type="text" name="value"> </td>
			<td><input type="submit" value="Add"> </td>
		</tr>
	</table>
	</form>
</body>
</html>