<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="<c:url value='/style/style.css'/>">
<meta charset='ISO-8859-1'>

<title>Insert title here</title>
</head>
<body>
	<h2>Edit Folder Name</h2>	
	<form method='post' action='Rename06'>
		<p>Edit Name: <input type='text' name='name' value='${param.name}'/></p> 
		<input type='hidden' name='id' value='${param.id}'>
		<input type='hidden' name='parentId' value='${param.parentId}'>
		<input type='submit' value='Save'/>
	</form>
</body>
</html>