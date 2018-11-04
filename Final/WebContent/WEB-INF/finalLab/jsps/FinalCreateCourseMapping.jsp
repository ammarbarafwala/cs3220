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
<form action="FinalCreateCourseMapping" method="post">
	<table border="1">
			<tr><th>Quarter Course</th><th>Semester Course</th></tr>
			<tr>
				<td>
					<c:forEach items="${ finalObject.quarterCourseList }" var="qCourse">
						<ul><li>${ qCourse} <input type="radio" name="qCourse" value="${ qCourse}"></li></ul>
					</c:forEach>
				</td>
				<td>
					<c:forEach items="${ finalObject.semesterCourseList }" var="sCourse">
						<ul><li>${ sCourse }<input type="radio" name="sCourse" value="${ sCourse}"></li></ul>
					</c:forEach>
				</td>
			</tr>
		</table>
		<p><input type="submit" value="Create Mapping"></p>
		</form>
</body>
</html>