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
<form action="DrivingTestBrowser" method="post">
<p>${question.description}</p>
<p>1. ${question.answerA}<input type="radio" name="choice" value="1"/></p>
<p>2. ${question.answerB}<input type="radio" name="choice" value="2"/></p>
<p>3. ${question.answerC}<input type="radio" name="choice" value="3"/></p>
<input type="hidden" value="${index+1}" name="index"/>
<input type="submit" value="<c:choose><c:when test="${index<fn:length(lab12List)-1}">Next</c:when><c:otherwise>Finish</c:otherwise></c:choose>">
</form>
</body>
</html>