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
	<h3>ECST Faculty</h3>
	<p><a href="AddDepartment">Add Department</a> | <a href="AddFaculty">Add Faculty</a></p>
	<table border="1">
		<tr>
			<th>Department</th>
			<th>Faculty</th>
		</tr>
		<c:forEach items="${ midterm01List }" var="list">
			<tr>
				<c:set var="length" value="${ fn:length(list.facultyNameList) }"/>
				<td rowspan="${ length }">${ list.departmentName }</td>
				<td>${ list.facultyNameList[0] }</td></tr>
				<c:forEach begin="1" var="facultyName" items="${ list.facultyNameList }">
					<tr><td>${ facultyName }</td></tr>
				</c:forEach>
		</c:forEach>
	</table>
</body>
</html>