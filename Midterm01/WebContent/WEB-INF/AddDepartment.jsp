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
	<h3><a href="MainPage">ECST Faculty</a> > Add Department</h3>
	<form method="post" action="AddDepartment">
		Department: <input type="text" name="name"/>
		<input type="submit" value="Add"/>
	</form>
</body>
</html>