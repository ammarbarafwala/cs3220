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
		<p><a href="FinalAddCourse">Add Course</a> | <a href="FinalCreateCourseMapping">Create Course Mapping</a></p>
		<table border="1">
			<tr><th>Quarter Courses</th><th>Semester Courses</th><th>Course Mappings</th></tr>
			<tr>
				<td>
					<c:forEach items="${ finalObject.quarterCourseList }" var="qCourse">
						<ul><li>${ qCourse}</li></ul>
					</c:forEach>
				</td>
				<td>
					<c:forEach items="${ finalObject.semesterCourseList }" var="sCourse">
						<ul><li>${ sCourse }</li></ul>
					</c:forEach>
				</td>
				<td>
					<c:forEach items="${ finalObject.courseMapping }" var="cMapping">
						<ul><li>( ${ cMapping.quarterCourseName }, ${ cMapping.semesterCourseName } )</li></ul>
					</c:forEach>
				</td>
			</tr>
		</table>
	</body>
</html>