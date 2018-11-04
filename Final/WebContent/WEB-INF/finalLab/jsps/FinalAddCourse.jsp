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
	<form action="FinalAddCourse" method="post">
		Course: <input type="text" name="courseName">
		<select name="select">
			<option value="quarterCourses">Quarter</option>
			<option value="semesterCourses">Semester</option>
		</select>
		<input type="submit" value="Add">
	</form>
</body>
</html>