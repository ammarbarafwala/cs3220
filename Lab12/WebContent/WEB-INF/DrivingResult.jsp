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
<table border="1">
	<tr>
		<th>Question</th>
		<th>Correct Answer</th>
		<th>Your Answer</th>
	</tr>
	<c:set var="correct" scope="session" value="${fn:length(lab12List)}"/>
	<c:forEach items="${lab12List}" var="list" varStatus="status">
	<tr>
	<td>${status.index+1}</td>
	<td>${list.correctAnswer}</td>
	<td>${lab12Result[status.index]}
		<c:if test="${lab12Result[status.index]!=list.correctAnswer}">(Incorrect)
			${correct=correct+1}</c:if></td>
	</tr>
</c:forEach>
</table>
<p>Your Score: ${correct}/${fn:length(lab12List)}</p>
<a href="DrivingTestBrowser">Start Over</a>
</body>
</html>