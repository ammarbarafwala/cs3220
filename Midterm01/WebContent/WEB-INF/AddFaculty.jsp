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
<h3><a href="MainPage">ECST Faculty</a> > Add Faculty</h3>
	<form action="AddFaculty" method="post">
		<table border="1">
			<tr>
				<th>Department</th>
				<td>
					<select name="departmentName">
					<c:forEach items="${midterm01List}" var="list" varStatus="status">
						<option value="${status.index}">${list.departmentName}</option>
					</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>Name</th>
				<td><input type="text" name="name"/></td>
			</tr>
			<tr>
				<th>Chairperson:</th>
				<td><input type="checkbox" name="chairperson" value="yes"/></td>
			</tr>
			<tr>
			<td colspan="2"><input type="submit" value="Add"/></td>
			</tr>
		</table>
	</form>
</body>
</html>